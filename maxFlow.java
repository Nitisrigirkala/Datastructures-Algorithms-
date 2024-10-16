import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
//I Nitisri Girkala accept honor of pleage that i did this Code by myself.
class Graph {
    private int size;

    private float[][] adj;
    private float[][] flo;
    private float[] f;
    private int[] hight;
// Constructor of the Graph class, initializes the graph with given size
    Graph(int size) {
        this.size = size;
        adj = new float[size][size];
        flo = new float[size][size];
        f = new float[size];
        hight = new int[size];
        for (int i = 0; i < size; i++) {
            f[i] = 0.0f;
            hight[i] = 0;
            for (int j = 0; j < size; j++) {
                flo[i][j] = Float.MIN_VALUE;
                adj[i][j] = Float.MIN_VALUE;
            }
        }
    }   
    // Method to add an edge to the graph
    //Name:Nitisri Dharmender Girkala
    //Date: 04/27/2023
    void addEdge(int source, int destination, float weight) {
        adj[source][destination] = weight;
    }
// Method to perform Push-Relabel algorithm on the graph
 //Name:Nitisri Dharmender Girkala
    //Date: 04/27/2023
    void PushRelaible(int graphNumber) {
        hight[0] = size;
        int j = 0;
        // Initializing flow values for edges emanating from source vertex
        while (j < size) {
            if (adj[0][j] != Float.MIN_VALUE) {
                flo[0][j] = adj[0][j];
                f[j] += adj[0][j];
                adj[j][0] = 0.0f;
                flo[j][0] = -adj[0][j];
            }
            j++;
        }
 // Perform iterations until all nodes have excess flow of 0
        while (true) {
            int i = -1;
            j = 1;
            while (j < size - 1) {
                if (f[j] > 0) {
                    i = j;
                    break;
                }
                j++;
            }
            if (i < 0) {
                break;
            }
            j = 0;
            Boolean isupdateWidth = push(j, i, true);
            updateWidth(i, isupdateWidth);
        }
         // Printing maximum flow for the graph
        StringBuffer bfr = new StringBuffer("");
        bfr.append("    Preflow-push => ");
        bfr.append(Math.round(f[size - 1] * 1000.0) / 1000.0);
        bfr.append("\n");
        if (size <= 10) {
            Float f = 0.000001f;
            for (int i = 0; i < size; i++) {
                bfr.append("\t" + i + ":");
            }
            bfr.append("\n");
            for (int i = 0; i < size; i++) {
                bfr.append(i + ":\t");
                j = 0;
                while (j < size) {
                    if (flo[i][j] <= f) {
                        bfr.append("-\t");
                    } else {
                        bfr.append(Math.round(flo[i][j] * 1000.0) / 1000.0 + "\t");
                    }
                    j++;
                }
                bfr.append("\n");
            }
        }
        System.out.println(bfr.toString());
    }
// Method to update the height of a node and its excess flow
 //Name:Nitisri Dharmender Girkala
    //Date: 04/28/2023
    private void updateWidth(int i, Boolean isupdateWidth) {
        if (isupdateWidth) {
            var maximum = Integer.MAX_VALUE;
            for (int k = 0; k < adj[i].length; k++) {
                if (adj[i][k] != Float.MIN_VALUE) {
                    if (flo[i][k] == adj[i][k]) {
                        continue;
                    }
                    if (hight[k] < maximum) {
                        maximum = hight[k];
                        hight[i] = maximum + 1;
                    }
                }
            }
        }
    }
// Method to push flow from node i to node j
 //Name:Nitisri Dharmender Girkala
    //Date: 04/29/2023
    private Boolean push(int j, int i, Boolean isrelable) {
        while (j < adj[i].length) {
            if (adj[i][j] != Float.MIN_VALUE) {
                if (flo[i][j] == (adj[i][j])) {
                    j++;
                    continue;
                }
                if (hight[i] > hight[j]) {
                    updateflow(j, i);
                    isrelable = false;
                    break;
                }
            }
            j++;
        }
        return isrelable;
    }
 //Name:Nitisri Dharmender Girkala
    //Date: 04/30/2023
    private void updateflow(int j, int i) {
           // Calculate the maximum flow value that can be sent from i to j
        var val = Math.min((adj[i][j] - flo[i][j]), f[i]);
            // Update the flow and residual capacities
        f[i] -= val;
        f[j] += val;
        flo[i][j] += val;
          // Check if there is a reverse edge from j to i
        if (flo[j][i] != Float.MIN_VALUE) {
            flo[j][i] -= val;
        } else {
             // Create a new reverse edge with capacity val
            flo[j][i] = 0.0f;
            adj[j][i] = val;
        }
    }

    boolean pathfromstot(float Graph[][], int s, int t, int parent[]) {
        // Initialize a linked list to keep track of visited vertices
        LinkedList<Integer> list = new LinkedList<Integer>();
        
// Initialize an array to keep track of visited vertices
        boolean visited[] = new boolean[size];
        for (int i = 0; i < size; i++) {
            visited[i] = false;
        }
        // Add the source to the linked list and mark it as visited
        list.add(0);
        visited[s] = true;
        parent[0] = -1;
        // Traverse the graph using breadth-first search
        while (list.size() > 0) {
            int u = list.poll();
            for (int v = 0; v < size; v++) {
                if (!visited[v]
                        && Graph[u][v] > 0 && v == t) {
                    parent[v] = u;
                    return true;
                }
                if (visited[v] == false
                        && Graph[u][v] > 0) {
                            // If an unvisited vertex is found, add it to the list, set its parent and mark it as visited
                    list.add(v);
                    parent[v] = u;
                    visited[v] = true;

                }
            }
        }
        return false;
    }
 //Name:Nitisri Dharmender Girkala
    //Date: 05/01/2023
// This method computes the maximum flow of a graph using the Ford-Fulkerson algorithm.
    public float fordFulkerson(int graphNumber) {
        System.out.println("G " + graphNumber + "’s Maximum Flow:");
        int u, v;

        float[][] graph = new float[size][size];
        float[][] adj2 = new float[size][size];
        int s = 0;
        int t = size - 1;
// Copy the input graph to a new graph and an adjacency matrix
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                if (adj[i][j] == Float.MIN_VALUE) {
                    graph[i][j] = 0f;
                    adj2[i][j] = 0f;
                }
                graph[i][j] = adj[i][j];
                adj2[i][j] = adj[i][j];
            }
        }
        // Initialize the graph, parent, and visited arrays
        float Graph[][] = new float[size][size];

        for (u = 0; u < size; u++) {
            for (v = 0; v < size; v++) {
                Graph[u][v] = adj2[u][v];
            }
        }

        int parent[] = new int[size];

        float max_flow = 0f;
        boolean[] visited = new boolean[Graph.length];
// Print the input graph if its size is less than 11
        if (size < 11) {
            for (int i = 0; i < size; i++) {
                System.out.print("\t" + i + ":");
            }
            System.out.println();
            float f = 0.000001f;
            for (int i = 0; i < size; i++) {
                System.out.print(i + ":\t");
                for (int j = 0; j < size; j++) {
                    if (Graph[i][j] >= f)
                        System.out.printf("%.3f\t", Graph[i][j], 3);
                    else {
                        System.out.printf("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        // Run the Ford-Fulkerson algorithm while there is a path from s to t
        while (pathfromstot(Graph, s, t, parent) == true) {
            float path_flow = Float.MAX_VALUE;
            // Find the minimum residual capacity of the edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, Graph[u][v]);
            }
// Update the residual capacity of the edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                Graph[u][v] -= path_flow;
            }
// Update the residual capacity of the edges in the opposite direction
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                Graph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }
// Print the adjacency matrix and the maximum flow if the size of the graph is less than 11
        System.out.println("    Ford-Fulkerson => " + max_flow);
        if (size < 11) {
            for (int i = 0; i < size; i++) {
                System.out.print("\t" + i + ":");
            }
            System.out.println();
            float f = 0.000001f;
            for (int i = 0; i < size; i++) {
                System.out.print(i + ":\t");
                for (int j = 0; j < size; j++) {

                    if (adj2[i][j] == 0f && Graph[i][j] > 0f) {
                        if (Graph[i][j] == 0)
                            System.out.print("-\t");
                        else
                            System.out.printf("%.3f\t", Graph[i][j], 3);
                    } else if ((adj2[i][j] >= Graph[i][j]) && adj2[i][j] != Graph[i][j]
                            && adj2[i][j] - Graph[i][j] != 0f) {
                        if (adj2[i][j] - Graph[i][j] >= f)
                            System.out.printf("%.3f\t", adj2[i][j] - Graph[i][j], 3);
                        else {
                            System.out.printf("-\t");
                        }
                    } else {
                        System.out.printf("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        return max_flow;
    }

}
 //Name:Nitisri Dharmender Girkala
    //Date: 05/02/2023
public class maxFlowNitiSri {
    public static void main(String[] args) throws IOException {
        System.out.println("Maximum flow from vertex 0 to vertex n-1 in " + args[0] + " |V|=n");
          // Read the input file
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));
         // Read the number of graphs
        String graphSizeLine = br.readLine();
        int numberOfGraphs = Integer.parseInt(graphSizeLine.split(" ")[0]);
        int graphNumber = 0;
           // Process each graph
        while (numberOfGraphs-- > 0) {
            br.readLine();
            String str = br.readLine();
            int graphSize = 0;
            if (str != null) {
                   // Extract the graph size from the string
                graphSize = Integer
                        .parseInt(String.valueOf(str.substring((str.lastIndexOf(".")) + 1, str.indexOf('}')))) + 1;
            }
              // Create a new graph with the given size
            Graph g = new Graph(graphSize);
            String edgeLine;
            br.readLine();
            while ((edgeLine = br.readLine()) != null && !edgeLine.contains("-")) {
                edgeLine = edgeLine.replaceAll("[\\s(){}]", "");
                String[] parts = edgeLine.split(",");
                int vertex1 = Integer.parseInt(parts[0]);
                int vertex2 = Integer.parseInt(parts[1]);
                float weight = Float.parseFloat(parts[2]);
                g.addEdge(vertex1, vertex2, weight);
            }
            // Check if the graph contains a Push-Relabel problem
            if (edgeLine != null && edgeLine.contains("--")) {
                graphNumber++;
                g.fordFulkerson(graphNumber);
                g.PushRelaible(graphNumber);
            }
        }

    }
}