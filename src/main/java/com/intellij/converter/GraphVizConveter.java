package com.intellij.converter;

import com.google.gson.Gson;
import com.intellij.model.*;
import com.sun.tools.javac.util.Pair;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Ivan Chirkov
 */
public class GraphVizConveter {
    private static final Map<String, String> names = new HashMap<String, String>();
    private static int i = 1;
    private static int m_i = 1;
    private static int fs_i = 1;
    private static final List<Pair<String, String>> edges = new ArrayList<Pair<String, String>>();
    private static final List<String> nodes = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Please specify input filename as first parameter and output filename as second parameter " +
                    "or omit second parameter to send output to standard output stream");
            return;
        }

        readModel(args[0]);

        if (args.length < 2) {
            printGraph(System.out);
        } else {
            PrintStream printStream = new PrintStream(new File(args[1]));
            printGraph(printStream);
            printStream.close();
        }
    }

    private static void printGraph(PrintStream outStream) {
        outStream.println("digraph test1 {");
        for (Pair<String, String> edge : edges) {
            outStream.println(String.format("%s -> %s;", edge.fst, names.get(edge.snd)));
        }
        for (String node : nodes) {
            outStream.println(node);
        }
        outStream.println("}");
    }

    private static void readModel(String path) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(path)));
        Gson gson = new Gson();
        WProject project = gson.fromJson(json, WProject.class);
        for (WModule module : project.modules) {
            String moduleNode = getNodeIdByModule(module);
            for (SpringWFacet facet : module.facets) {
                for (WFileSet fileset : facet.filesets) {
                    String filesetNode = getNodeIdByFileset(fileset);
                    Set<String> topNodes = new LinkedHashSet<String>();
                    Set<String> includedNodes = new LinkedHashSet<String>();
                    for (WModel model : fileset.models) {
                        topNodes.add(model.name);
                        String nodeIdFrom = getNodeIdByModel(model);
                        for (WSpringDependency dependency : model.dependencies) {
                            edges.add(new Pair<String, String>(nodeIdFrom, dependency.modelName));
                            includedNodes.add(dependency.modelName);
                        }
                    }
                    for (String includedNode : includedNodes) {
                        topNodes.remove(includedNode);
                    }
                    for (String topNode : topNodes) {
                        edges.add(new Pair<String, String>(filesetNode, topNode));
                    }
                    edges.add(new Pair<String, String>(moduleNode, fileset.name));
                }
            }
        }
    }

    private static String getNodeIdByModule(WModule module) {
        String nodeId = names.get(module.name);
        if (nodeId == null) {
            nodeId = "m" + m_i;
            m_i++;
            names.put(module.name, nodeId);
            nodes.add(String.format("%s [label=\"%s\",shape=box,fillcolor=\"%s\",style=\"filled," +
                                            "rounded\"];",
                                    nodeId,
                                    module.name,
                                    "yellow"));
        }
        return nodeId;
    }

    private static String getNodeIdByFileset(WFileSet fileSet) {
        String nodeId = names.get(fileSet.name);
        if (nodeId == null) {
            nodeId = "fs" + fs_i;
            fs_i++;
            names.put(fileSet.name, nodeId);
            nodes.add(String.format("%s [label=\"%s\",shape=box,fillcolor=\"%s\",style=\"filled," +
                                            "rounded\"];",
                                    nodeId,
                                    fileSet.name,
                                    "cadetblue1"));
        }
        return nodeId;
    }

    private static String getNodeIdByModel(WModel model) {
        String nodeId = names.get(model.name);
        if (nodeId == null) {
            nodeId = "a" + i;
            i++;
            names.put(model.name, nodeId);
            String shortName;
            if (model.type == ModelType.XML) {
                shortName = model.name.substring(model.name.lastIndexOf("/") + 1);
            } else {
                shortName = model.name.substring(model.name.lastIndexOf(".")+1);
//                shortName = model.name.substring(0, model.name.lastIndexOf("."));
            }
            nodes.add(String.format("%s [label=\"%s\",shape=box,fillcolor=\"%s\",style=\"filled," +
                                            "rounded\"];",
                                    nodeId,
                                    shortName,
                                    model.type == ModelType.XML ? "green" : "red"));
        }
        return nodeId;
    }
}
