
public class Nodo {
    protected int value;       // the associated value
    protected int height;      // height of the subtree
    protected int size;        // number of nodes in subtree
    protected Nodo left;       // left subtree
    protected Nodo right;      // right subtree

    public Nodo(int val, int height, int size) {
        this.value = val;
        this.size = size;
        this.height = height;
    }
}