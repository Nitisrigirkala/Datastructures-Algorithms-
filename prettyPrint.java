import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class prettyPrint {
//I affirm that i did this assignment by myself and didn't copy from anyone
//Name: Nitisri Dharmender Girkala
//Date:04/15/2024
    public static void main(String[] args) throws FileNotFoundException {
        // Extract the input file name and width from command line arguments.
        String inputFileName = args[0];
        String widthText = args[1];
        int width = Integer.parseInt(widthText);
    // Create a file object for the input file and read from it using Scanner.
        File fileObj = new File(inputFileName);
        try (Scanner file = new Scanner(fileObj)) {
            // Create an ArrayList to store words read from each line of the input file.
            ArrayList<String> words = new ArrayList<String>();
            System.out.println();
            System.out.println();
            System.out.printf("*** Pretty Print %s", inputFileName);
            System.out.println();
            System.out.printf("*** Width=%s", widthText);
            System.out.println();
             // Loop through each line of the input file.   
            while (file.hasNextLine()) {
                String line = file.nextLine();
                // If the line is empty, calculate the cost and path for the words read so far and clear the words ArrayList.
                if (line.equals("")) {
                    if (words.size() != 0) {
                        claCostAndPath(words, width);
                        words.clear();
                        System.out.println();
                    }
                } else {
                // If the line is not empty, split it into words and add them to the words ArrayList.
                    String[] lineWords = line.trim().split(" ");
                    Collections.addAll(words, lineWords);
                }
                

            }
        }
        System.out.println("*** Asg 7 by Nitisri Girkala");
    }
    //I affirm that i did this assignment by myself and didn't copy from anyone
//Name: Nitisri Dharmender Girkala
//Date:04/16/2024
// This is a helper method to calculate the cost and path of the words ArrayList given a specified width.
    private static void claCostAndPath(ArrayList<String> words, int width) {
        int len = words.size();
        ArrayList<Integer> cost = new ArrayList<Integer>();
        ArrayList<Integer> lineEndIndex = new ArrayList<Integer>();
         // Reverse the order of the words ArrayList.
        for (int i = 0; i < words.size() / 2; i++) {
            String temp = words.get(i);
            words.set(i, words.get(words.size() - i - 1));
            words.set(words.size() - i - 1, temp);
        }
// Add the first line to the cost and lineEndIndex ArrayLists.
        cost.add(0);
        lineEndIndex.add(words.size() - 1);
        int availableLineLength = width;
// Loop through each word in the words ArrayList and calculate the cost and path of each line.
        for (int i = 1; i < len; i++) {
            String word = words.get(i);
            availableLineLength = width - word.length();
            int lineMinimumCost = Integer.MAX_VALUE;
            int next = 0;
            for (int j = i - 1; j >= -1; j--) {
                if (availableLineLength <= -1) {
                    break;
                }
                 // If there are no more words before the current one, set the next index to the end of the list and the cost to 0
                if (j == -1) {
                    next = words.size();
                    lineMinimumCost = 0;
                } else {
                    // Calculate the cost of breaking the line at the current word, and update the minimum cost and next index if necessary 
                    if ((int) Math.pow(availableLineLength, 2) + cost.get(j) <= lineMinimumCost) {
                        next = words.size() - j - 1;
                        lineMinimumCost = (int) (Math.pow(availableLineLength, 2) + cost.get(j));
                    }
                }
                // Update the available line length by subtracting the length of the current word and the space after it (if not at the beginning)
                if (j != -1)
                    availableLineLength = availableLineLength - 1 - words.get(j).length();
            }
             // Add the minimum cost and next index to their respective lists   
            cost.add(lineMinimumCost);
            lineEndIndex.add(next);
        }


        int a = lineEndIndex.get(words.size() - 1);

        for (int i = words.size() - 1; i >= 0; i--) {
         // If the current index is the line break index, update the line break index and print a newline character
            if (i == words.size() - a - 1) {
                a = lineEndIndex.get(i);
                System.out.println();
            }
            System.out.print(words.get(i) + " ");
        }
        System.out.println();
        System.out.printf("===[%d]", cost.get(words.size() - 1));
        System.out.println();
    }
}

