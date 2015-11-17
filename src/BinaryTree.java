import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by Administrator on 2015/11/12.
 */
public class BinaryTree<E> implements Serializable {
    protected Node<E> root;

    /**
     * Create a private Node class.
     * @param <E> Element
     */
    protected class Node<E> {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        /**
         * Define a Node.
         * @param data local element
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
        public String toString() {
            return data.toString();
        }
    }
    public BinaryTree() {
        root = null;
    }

    /**
     * Constructs a new BinaryTree with Node as its root.
     * @param root
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new BinaryTree with data in its root leftTree
     * as its left subtree and rightTree as its right subtree.
     * @param data
     * @param leftTree
     * @param rightTree
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }
    /**
     * Return the left subtree
     * @return the left subtree or null if either the root or
     * the left subtree is null.
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right subtree
     * @return the right subtree or null if either the root or
     * the right subtree is null;
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Get the data of root.
     * @return the data of root
     */
    public E getData() {
        return root.data;
    }

    /**
     * Determine whether this tree is leaf.
     * @return true if root has no children
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     *
     * @return String of the tree
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Perform a pre-order traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++ ) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth+1, sb);
            preOrderTraverse(node.right, depth+1, sb);
        }
    }

    /**
     * Method to read a binary tree.
     * pre; The input consists of a pre-order traversal
     * of the binary tree. The line "null" indicates a null tree.
     * @param scan the Scanner attached to the input file.
     * @return The binary tree
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        // Read a line and trim leading and trailing spaces.
        String data = scan.next();
        if (data.equals(null)) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data,leftTree,rightTree);
        }
    }

    /**
     * Preform a pre-order traversal in line.
     * @param node The local root
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverseLine(Node<E> node, StringBuilder sb) {
        if (node == null) {
            sb.append("null ");
        } else {
            sb.append(node.toString());
            sb.append(" ");
            preOrderTraverseLine(node.left, sb);
            preOrderTraverseLine(node.right, sb);
        }
    }

    /**
     * Return a string showing pre-order traversal in line.
     * @return a string of pre-order traversal in line.
     */
    public String displayPreOrderTraverseAsLine() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverseLine(root, sb);
        return sb.toString();
    }

    /**
     * Preform a in order traverse.
     * @param node The local root
     * @param sb The string buffer to save the output
     */
    private void inOrderTraverse(Node<E> node, StringBuilder sb) {

        if(node != null) {
            inOrderTraverse(node.left, sb);
            sb.append(node.toString());
            sb.append(" ");
            inOrderTraverse(node.right, sb);
        }
    }

    /**
     * Return a string of in-order traverse.
     * @return a string of in-order traverse
     */
    public String displayInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderTraverse(root, sb);
        return sb.toString();
    }
    public String displayPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderTravers(root, sb);
        return sb.toString();
    }

    /**
     * Preform a post-order traverse.
     * @param node The local root
     * @param sb The string buffer to save the output
     */
    private void postOrderTravers(Node<E> node, StringBuilder sb) {
        if (node != null) {
            postOrderTravers(node.left, sb);
            postOrderTravers(node.right, sb);
            sb.append(node.toString());
            sb.append(" ");
        }
    }
}
