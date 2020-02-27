import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class Graph implements Serializable{
    private int numOfVertices;
    private List<Set<Integer>> graph;

    public int getNumOfVertices(){return numOfVertices;}
    public List<Set<Integer>> getGraph(){return graph;}

    public Graph(List<Set<Integer>> graph){
        this.graph = graph;
        this.numOfVertices = graph.size();
    }

    boolean connected(int source, int target){
        assert source >= 0 && source < numOfVertices;
        assert target >= 0 && target < numOfVertices;
        return graph.get(source).contains(target);
    }
}
