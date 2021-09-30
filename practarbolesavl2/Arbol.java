/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jesus
 */
public class Arbol {
     protected Nodo root;
    
    /**
     * Checks if the tree is empty.
     * 
     * @return {@code true} if the tree is empty.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the number of nodes.
     * 
     * @return the number of nodes.
     */
    public int size() {
        return size(root);
    }

    /**
     * Returns the number of nodes in the subtree.
     * 
     * @param x the subtree
     * 
     * @return the number of nodes in the subtree
     */
    private int size(Nodo x) {
        if (x == null) return 0;
        return x.size;
    }

    /**
     * Returns the height of the internal AVL tree. It is assumed that the
     * height of an empty tree is -1 and the height of a tree with just one node
     * is 0.
     * 
     * @return the height of the internal AVL tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Returns the height of the subtree.
     * 
     * @param x the subtree
     * 
     * @return the height of the subtree.
     */
    private int height(Nodo x) {
        if (x == null) return -1;
        return x.height;
    }

     /**
     * Inserts the specified value in the tree
     * 
     * @param val the value
     */
    public void put(int val) {
        root = put(root, val);
    }
    
    /**
     * Recorre el árbol para encontrar un valor, devolviendo su nodo o null si no
     * lo encuentra
     * @param val
     * @param x
     * @return 
     */
    public Nodo find(int val, Nodo x)
    {
        if (x==null)
        {
            return null;
        }
        
        if (x.value==val)
        {
            return x;
        }
        else if (val < x.value)
        {
            return find(val,x.left);
        }
        else
        {
            return find(val,x.right);
        }
    }

    /**
     * Inserts the value in the subtree. 
     * @param x the subtree
     * @param val the value
     * @return the subtree
     */
    private Nodo put(Nodo x, int val) {
        if (x == null) return new Nodo(val, 0, 1);
        
        if (val < x.value) {
            x.left = put(x.left, val);
        }
        else if (val > x.value) {
            x.right = put(x.right, val);
        }
        else {
            x.value = val;
            return x;
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * Restores the AVL tree property of the subtree.
     * 
     * @param x the subtree
     * @return the subtree with restored AVL property
     */
    private Nodo balance(Nodo x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.right) < 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        }
        else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.left) > 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     * 
     * @param x the subtree
     * @return the balance factor of the subtree
     */
    private int balanceFactor(Nodo x) {
        return height(x.right) - height(x.left);
    }

    /**
     * Rotates the given subtree to the right.
     * 
     * @param x the subtree
     * @return the right rotated subtree
     */
    private Nodo rotateRight(Nodo x) {
        Nodo y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    /**
     * Rotates the given subtree to the left.
     * 
     * @param x the subtree
     * @return the left rotated subtree
     */
    private Nodo rotateLeft(Nodo x) {
        Nodo y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    /**
     * Removes the specified value and its associated node
     * @param val the value
     */
    public void delete(int val) {
        root = delete(root, val);
    }

    /**
     * Removes the specified value and its associated node from the given
     * subtree.
     * 
     * @param x the subtree
     * @param val the value
     * @return the updated subtree
     */
    private Nodo delete(Nodo x, int val) {
        if (val < x.value) {
            x.left = delete(x.left, val);
        }
        else if (val > x.value) {
            x.right = delete(x.right, val);
        }
        else {
            if (x.left == null) {
                return x.right;
            }
            else if (x.right == null) {
                return x.left;
            }
            else {
                Nodo y = x;
                x = min(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }  
     
    /**
     * Removes the smallest node from the given subtree.
     * 
     * @param x the subtree
     * @return the updated subtree
     */
    private Nodo deleteMin(Nodo x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }
    
    /**
     * Returns the smallest node in the subtree.
     * 
     * @param x the subtree
     * @return the node with the smallest key in the subtree
     */
    private Nodo min(Nodo x) {
        if (x.left == null) return x;
        return min(x.left);
    }
    
    /**
     * Recorre el árbol en InOrden: I - R - D
     * @param n 
     */
    public void inOrden(Nodo n)
    {
        if (n!=null)
        {
            inOrden(n.left);
            System.out.print(n.value + ", ");
            inOrden(n.right);
        }
    }
    
    /**
     * Recorre el árbol en PreOrden: R - I - D
     * @param n 
     */
    public void preOrden(Nodo n)
    {
        if (n!=null)
        {
            System.out.print(n.value + ", ");
            preOrden(n.left);
            preOrden(n.right);
        }
    }
    
    /**
     * Recorre el árbol en PostOrden: I - D - R
     * @param n 
     */
    public void postOrden(Nodo n)
    {
        if (n!=null)
        {
            postOrden(n.left);
            postOrden(n.right);
            System.out.print(n.value + ", ");
        }
    }
}