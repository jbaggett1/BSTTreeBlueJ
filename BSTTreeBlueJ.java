import java.util.*;
import java.util.ArrayList;


/**
 * Jillian Baggett 
 * Class AVLTree is a basic implementaion of Adelson-Velskii and
 * Landis' Balanced Binary Search Tree.
 */

public class BSTTreeBlueJ <E extends Comparable<? super E>> {
    protected Node root;
    protected int size;
    //public static void main(String[] args)
    //{ BSTTreeBlueJ<Integer> example = new BSTTreeBlueJ<Integer>();
    //  example.insert(6);
    //  example.insert(12);
     // example.insert(8);
     // example.remove(12);
     // System.out.println("List is " + example.printTree().toString());
    //}

    public int getSize()
    { return this.size;
    }
    /**
     * Construct an empty BSTTree
     */
    public BSTTreeBlueJ() {
        // not necessary, but explicit stating root starts at null
        this.root = null;
        this.size = 0;
    }

    /**
     * Insert the element into this AVLTree.
     * @param element the element to insert into the tree. Duplicates are
     * allowed
     */
    public void insert(E element) {
        this.root = insert(this.root, element);
        this.size++;
    }

    /**
     * Remove the element from this AVLTree.
     * @param element the element to remove
     */
    public void remove(E element) {
        this.root = remove(this.root, element);
        this.size--;
    }

    /**
     * Check if this tree contains the element.
     * @return true if this tree contains the element, false otherwise
     */
    public boolean contains(E element) {
        return contains(this.root, element);
    }

    /**
     * Return the minimum elemnent in this tree.
     * @return the mininum element in this tree
     */
    public E findMin() {
        return findMin(this.root);
    }

    /*
     * A private helper method for insertion.
     * By taking a Node as a parameter, we can write this method
     * recursively, continuing to call insert on subtrees until the element
     * can be inserted.
     * @param node the root of some subtree of this AVLTree
     * @param element the element to insert into this subtree
     */
    protected Node insert(Node node, E element) {
        if(node == null) {
            return new Node(element);
        }
        // if element is less than the value contained by node...
        if(element.compareTo(node.element) < 0) {
            // insert element into the left subtree
            node.left = insert(node.left, element);
        } else {
            // insert element into the right subtree
            node.right = insert(node.right, element);
        }
        // update this node's height using the private helper method
        // height().
        node.height = this.height(node);
        return node;
    }

    /*
     * A private helper method for removal.
     * By taking a Node as a parameter, we can write this method
     * recursively, continuing to call remove on subtrees until the element
     * is removed.
     * @param node the root of some subtree of this AVLTree
     * @param element the element to remove from this subtree
     */
    protected Node remove(Node node, E element) {
        
        if (node == null)
        {
            return node;
        }
        System.out.println("Comparison: " + element.compareTo(node.element));
        if (element.compareTo(node.element) < 0)
        {
            node.left = remove(node.left, element);
        }
        else if (element.compareTo(node.element) > 0)
        {
            node.right = remove(node.right, element);   
        }
        else // (node.element == element)
        {   //if node has no children
            if (node.left == null)
            { return node.right;}
            //if node has two children
            else if (node.right == null)
            {
                return node.left;
            }
            else
            {
             node.element = minValue(node.right);
             node.right = remove(node.right, node.element);
              
            }
        } 
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public E minValue(Node node)
    { E minValue = node.element;
        while(node.left != null)
        { minValue = node.left.element;
          node = node.left;
        }
        node = null;
        return minValue;
    }
    /*
     * As for insert and remove, a private helper is used for a recursive
     * implementation.
     * @param element the element to search for
     * @param node the root of the subtree to search in
     * @return true if this subtree contains the element, false otherwise
     */
    private boolean contains(Node node, E element) {
        if(node == null) {
            return false;
        }
        if(element.compareTo(node.element) == 0) {
            return true;
        }
        if(element.compareTo(node.element) < 0) {
            return contains(node.left, element);
        } else {
            return contains(node.right, element);
        }
    }

    /*
     * Return the minimum element in the subtree rooted at node
     * @param node the root of the subtree
     * @return the minimum element in this subtree
     */
    protected E findMin(Node node) {

        //recursive loop till value is found
        if (node.left == null){
            return node.element;
        }
        else{
            return findMin(node.left);
        }
    }

    /*
     * Private helper method to calculate the height of a node. A node's
     * height is the larger of its left and right subtree's heights plus
     * one. To make this calculation consistent and easy, we define
     * height of an empty subtree is -1.
     * @param node the node to calculate the height of
     * @return its height as determined by the heights of its subtrees
     */
    protected int height(Node node) {
        // if the left child is null, its height is -1, otherwise, retrieve
        // its height
        if (node == null)
        { return -1;
        }
        int leftHeight = (node.left == null ? -1 : node.left.height);
        // same
        int rightHeight = (node.right == null ? -1 : node.right.height);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    protected ArrayList<Integer> printTree()
    {   
        ArrayList<Integer> treeString = new ArrayList<Integer>();
        
        int h = height(this.root);
        //System.out.print("Height is: " + h );
        for (int i = 0; i <= h; i++)
             printFullLevel(root, i, treeString);
        
        return treeString;
    }
    protected ArrayList<Integer> printFullLevel(Node root, int level, ArrayList<Integer> treeString)
    {
        if (root == null)
            return treeString;
        if (level == 0)
            
            { 
            treeString.add(Integer.parseInt(root.element.toString()));
             
            System.out.print(root.element + " ");
            }
        else if (level >= 1)
        {
            printFullLevel(root.left, level-1, treeString);
            printFullLevel(root.right, level-1, treeString);
        }
        return treeString;
    }

    protected class Node {
        // since this is a private inner class, and the outer AVLTree class
        // will need to freely modify the connections and update the height
        // of its nodes, the following three variables are not private.
        Node left;
        Node right;
        int height;
        E element;

        /**
         * Construct an AVLTreeNode. At instantiation, each node has no
         * children and therefore a height of 0.
         * @param element the element that this node contains
         */
        public Node(E element) {
            this.left = null;
            this.right = null;
            this.height = 0;
            this.element = element;
        }
    }
}