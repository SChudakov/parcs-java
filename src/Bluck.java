import parcs.*;

import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class Bluck {
    private static int numberOfPoints = 2;

    public static void main(String[] args) throws Exception {
        assert numberOfPoints > 0;
        task curtask = new task();
        curtask.addJarFile("MC.jar");
        Graph graph = loadGraph(curtask.findFile("input_mc"));
        AMInfo info = new AMInfo(curtask, null);

        List<point> points = new ArrayList<>();
        List<channel> chans = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; ++i) {
            System.out.println("craete point " + i);

            point p = info.createPoint();
            channel c = p.createChannel();
            p.execute("MaximumCliqueFinder");
            c.write(graph);
            c.write(i);
            points.add(p);
            chans.add(c);
        }

        System.out.println("Waiting for result...");
        long maximumCliqueSize = Integer.MIN_VALUE;
        for (channel c : chans) {
            maximumCliqueSize = Math.max(maximumCliqueSize, c.readInt());
        }

        System.out.println("Result: " + maximumCliqueSize);
        curtask.end();
    }

    public static Graph loadGraph(String filename) throws Exception {
        List<Set<Integer>> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Set<Integer> resultEntry = new HashSet<>();
                String[] successors = line.split(" ");
                for (String successor : successors) {
                    resultEntry.add(Integer.parseInt(successor));
                }
                result.add(resultEntry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Graph(result);
    }
}
