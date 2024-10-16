import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PrintingClass {
    public void printing(List<Float> distance, int[] previous, int graphNumber, int size, float[][] adj)
    {
        System.out.println("G"+graphNumber+"'s"+" "+"shortest path from 0 to"+" "+(size-1)+":");
        LinkedList<Integer> finalOutput = new LinkedList<>();
        if(!(distance.get(size-1) == Float.MAX_VALUE))
        {
            int sink = size-1;
            while(!finalOutput.contains(0)){
                finalOutput.add(previous[sink]);
                sink = previous[sink];
            }
            int start =finalOutput.size()-1;
            while (!(finalOutput.size()==1)){
                System.out.printf("     (%3d, %3d, %6.3f) --> %7.3f%n",finalOutput.get(start),finalOutput.get(start-1),adj[finalOutput.get(start)][finalOutput.get(start-1)],distance.get(finalOutput.get(start-1)));
                start = start-1;
                finalOutput.remove(start+1);
            }
            System.out.printf("     (%3d, %3d, %6.3f) --> %7.3f%n",finalOutput.get(0),(size - 1),adj[finalOutput.get(0)][size - 1],distance.get(size - 1));
        }
        else {
            System.out.println("     *** There is no path.");
            System.out.println();
        }
        }

}
