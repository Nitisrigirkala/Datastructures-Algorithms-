import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class dijkstra {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String graphSizeLine = br.readLine();
        int numberOfGraphs = Integer.parseInt(graphSizeLine.split(" ")[0]);
        int graphNumber = 0;
        while (numberOfGraphs-- > 0) {
            br.readLine();
            String str = br.readLine();
            int graphSize=0;
            if(str!=null) {
                graphSize = Integer.parseInt(String.valueOf(str.substring((str.lastIndexOf(".")) + 1, str.indexOf('}')))) + 1;
            }
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
            if(edgeLine!=null && edgeLine.contains("--"))
            {
                graphNumber++;
                g.dijkastraPath(graphNumber);
            }
        }



    }
}