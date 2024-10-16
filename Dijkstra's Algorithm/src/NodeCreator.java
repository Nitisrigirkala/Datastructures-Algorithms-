public class NodeCreator implements Comparable<NodeCreator> {
    int node;
    float totalweight;

    NodeCreator(int node, float totalweight) {
        this.node = node;
        this.totalweight = totalweight;
    }

    public int compareTo(NodeCreator node2) {
        if (this.totalweight < node2.totalweight) {
            return -1;
        } else {
            return 1;
        }
    }

}
