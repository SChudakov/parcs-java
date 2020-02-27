import parcs.*;

public class MaximumCliqueFinder implements AM {
    public void run(AMInfo info) {
        Graph graph = (Graph) info.parent.readObject();
        int numberOfPoints = info.parent.readInt();
        int pointId = info.parent.readInt();
        System.out.println("started point " + pointId);

        int numberOfVertices = graph.getNumOfVertices();
        int subsetsLimit = 1 << numberOfVertices;

        int result = 0;
        for (int mask = 0; mask < subsetsLimit; ++mask) {
            if (mask % numberOfPoints == pointId) {
                boolean clique = true;
                for (int i = 0; i < numberOfVertices && clique; ++i) {
                    if ((mask & (1 << i)) > 0) {
                        for (int j = i + 1; j < numberOfVertices && clique; ++j) {
                            if ((mask & (1 << j)) > 0) {
                                if (!graph.connected(i, j)) {
                                    clique = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (clique) {
                    int cliqueSize = 0;
                    for (int i = 0; i < numberOfVertices; ++i) {
                        if ((mask & (1 << i)) > 0) {
                            ++cliqueSize;
                        }
                    }
                    result = cliqueSize;
                }
            }
        }

        info.parent.write(result);
    }
}