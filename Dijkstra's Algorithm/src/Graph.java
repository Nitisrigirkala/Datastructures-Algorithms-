import java.util.*;

public class Graph {
    private int size;

    private float[][] adj;


    Graph(int size) {
        this.size = size;
        adj = new float[size][size];
    }


    void addEdge(int source, int destination, float weight) {
        adj[source][destination] = weight;
    }


    void dijkastraPath(int graphNumber) {
        List<Float> distance = new LinkedList<>();
        PrintingClass p = new PrintingClass();
        Stack<Integer> visited = new Stack<>();
        for (int i = 0; i < size; i++) {
            distance.add(Float.MAX_VALUE);
        }
        distance.set(0, 0.0f);


        PriorityQueue<NodeCreator> priorityResult = new PriorityQueue<>();
        priorityResult.add(new NodeCreator(0, 0));

        int[] previous = new int[size];

        while (!priorityResult.isEmpty()) {
            NodeCreator startNode = priorityResult.poll();

            int vertex = startNode.node;
            if (distance.get(vertex) != startNode.totalweight) {
                continue;
            }
            visited.push(startNode.node);

            for (int i = 1; i < adj.length; i++) {
                float dist = adj[startNode.node][i];
                if (dist != 0.0) {
                    if (!(visited.contains(i))) {
                        float updatedDistance = 0;
                        updatedDistance = distance.get(vertex) + dist;
                        if (updatedDistance < distance.get(i)){
                            distance.set(i, updatedDistance);
                            previous[i] = startNode.node;
                        }
                        priorityResult.add(new NodeCreator(i, updatedDistance));
                        
                    }
                }
            }
        }
        p.printing(distance,previous,graphNumber,size,adj);

    }

}




