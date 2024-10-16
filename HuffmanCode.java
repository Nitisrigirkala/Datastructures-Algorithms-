import java.util.*;

class HuffmanCode {
    static HuffmanNode root = null;
//Define a nested class HuffmanNode to represent nodes in the Huffman tree
    static class HuffmanNode {
        int data;
        char c;
        HuffmanNode left;
        HuffmanNode right;
        int height;
        int numNodes;
        List<Character> alphabets = new ArrayList<>();
        // Constructor for HuffmanNode
        public HuffmanNode(char c, int data) {
            this.c = c;
            this.data = data;
            this.left = null;
            this.right = null;
        }   
        // Check if a node is a leaf node   
        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

    // Calculate the sum of ASCII values if it not a leaf node
        public int calculateAsciiSum() {
            if (this.isLeaf()) {
                return this.c;
            } else {
                return this.left.calculateAsciiSum() + this.right.calculateAsciiSum();
            }
        }
    }
// Constructor for HuffmanCode
    public HuffmanCode(char[] a, int[] f) {
        HuffmanTree(a, f);
    }
// Build the Huffman tree
    public static void HuffmanTree(char[] charArray, int[] charfreq) {
// Use a priority queue to store Huffman nodes
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(
                new Comparator<HuffmanNode>() {
                    @Override
                    public int compare(HuffmanNode x, HuffmanNode y) {
                        if (x.data > y.data)
                            return 1;
                        return -1;

                    }
                });
// Create a Huffman node for each character in the input array
        for (int i = 0; i < charArray.length; i++) {
            HuffmanNode hn = new HuffmanNode('W', 0);
            hn.c = charArray[i];
            hn.data = charfreq[i];
            hn.left = null;
            hn.right = null;
            hn.height = 0;
            hn.numNodes = 1;
            hn.alphabets.add(charArray[i]);
            q.add(hn);
        }
 // Build the Huffman tree by combining nodes with the smallest frequencies
        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode('W', 0);
            f.data = x.data + y.data;
            f.c = '-';
            f.height = Math.max(x.height, y.height) + 1;
            f.numNodes = x.numNodes + y.numNodes;
            f.alphabets.addAll(x.alphabets);
            f.alphabets.addAll(y.alphabets);
            q.add(f);
// Choose the child nodes based on their heights, number of nodes, and ASCII values
            if (x.height != y.height) {
                f.left = (x.height > y.height) ? y : x;
                f.right = (x.height < y.height) ? y : x;
            } else if (x.numNodes != y.numNodes) {
                f.left = (x.numNodes > y.numNodes) ? y : x;
                f.right = (x.numNodes < y.numNodes) ? y : x;
            } else {
                f.left = (x.c > y.c) ? y : x;
                f.right = (x.c < y.c) ? y : x;
            }
        }
        root = q.poll();
    }

    // 2nd part
    //The printCodeWords() method calls the printCodeWordsHelper()
    public void printCodeWords() {
        printCodeWordsHelper(root, "");
    }
// The printCodeWordsHelper method recursively traverses the tree and prints out the code word for each leaf node
    public static void printCodeWordsHelper(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }
        if (root.isLeaf()) {
            System.out.printf("%c[%d]:%s (%d)\n", root.c, (int) root.c, code, root.data);
            return;
        }
        // Traverse left subtree with "0" appended to the code
        printCodeWordsHelper(root.left, code + "0");
        // Traverse right subtree with "1" appended to the code
        printCodeWordsHelper(root.right, code + "1");
    }

        // 3rd part
         // Encode a given text using the Huffman tree
        public String encode(String text) {
            StringBuilder encoded = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                 // Get the Huffman code for the character and append to the encoded string
                String code = getCode(root, c, "");
                encoded.append(code);
            }
            return encoded.toString();
        }
  // Get the Huffman code for a given character
        public static String getCode(HuffmanNode root, char c, String code) {
            if (root == null) {
                return "";
            }
            if (root.isLeaf() && root.c == c) {
                return code;
            }
             // Traverse left subtree with "0" appended to the code
            String leftCode = getCode(root.left, c, code + "0");
            if (!leftCode.isEmpty()) {
                return leftCode;
            }
             // Traverse right subtree with "1" appended to the code
            String rightCode = getCode(root.right, c, code + "1");
            if (!rightCode.isEmpty()) {
                return rightCode;
            }
            return "";
        }
     // Decode a given Huffman code string using the Huffman tree
        public String decode(String codeString) {
            StringBuilder sb = new StringBuilder();
            HuffmanNode curr = root;
            for (int i = 0; i < codeString.length(); i++) {
                char c = codeString.charAt(i);
                 // Traverse left subtree for '0', right subtree for '1'
                if (c == '0') {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }   
                // If we reach a leaf node, append the character and reset to the root of the tree    
                if (curr.isLeaf()) {
                    sb.append(curr.c);
                    curr = root;
                }
            }
            return sb.toString();
        }
    }