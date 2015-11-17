/**
 * Created by Administrator on 2015/11/17.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {
    //data field
    /** Return value from the public add method */
    protected boolean addReturn;
    /** Return value from the public delete method */
    protected E deleteReturn;

    protected int size;

    protected int height;
    /**
     * Starter method add.
     * pre: The object to insert must implement the Comparable interface.
     * @param item The local object being inserted
     * @return true if the object is inserted, false if the object already exists in the tree
     */
    @Override
    public boolean add(E item) {
        root = add(root, item);
        size();
        height();
        return addReturn;
    }

    /**
     * Recursive add method.
     * post: The data field addReturn is set true if the item is added to the tree,
     * false if the item is already in the tree.
     * @param root The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the inserted item
     */
    private Node<E> add(Node<E> root, E item) {
        if (root == null) {
            addReturn = true;
            return new Node<E>(item);
        }
        int r = item.compareTo(root.data);
        if (r == 0) {
            addReturn = false;
            return root;
        } else if (r < 0) {
            root.left = add(root.left, item);
            return root;
        } else {
            root.right = add(root.right, item);
            return root;
        }
    }

    /**
     * Return true. if the tree contains the item,
     * or false if the tree does not contain the item.
     * @param target the item to be found
     * @return true if the item is found, or false, if not found
     */
    @Override
    public boolean contain(E target) {
       E reuslt =  find(target);
        if (reuslt == null)
        return false;
        else return true;
    }

    /**
     * Starter method find.
     * pre: The target object must implement the Comparable interface
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     */

    @Override
    public E find(E target) {
        return find(root, target);
    }

    /**
     * Recursive find method.
     * @param root The local subtree's root
     * @param target The object being sought
     * @return The object, if found, otherwise null
     */

    private E find(Node<E> root, E target) {
        if (root == null) {
            return null;
        }

        int compresult = target.compareTo(root.data);
        if (compresult == 0) {
            return root.data;
        } else if (compresult < 0) {
            return find(root.left, target);
        } else return find(root.right, target);
    }

    /**
     * Find the node that is the inorder predecessor and replace it
     * with its left child ( if any).
     * post: The inorder predecessor is removed from the tree.
     * @param parent The parent of possible inorder predecessor
     * @return The data in the inorder predecessor
     */
    private E findLargestChild(Node<E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(root.right);
        }
    }

    /**
     * Starter method delete.
     * post: The object is not in the tree.
     * @param target The object to be deleted
     * @return The object deleted from the tree or null if the object was not in the tree
     * @throws ClassCastException if target does not implement Comparable
     */
    @Override
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Recursive delete method.
     * post: The item is equal to the deleted item as it was stored in the tree
     * or null if the item was not found.
     * @param root The root of the current subtree
     * @param target The item to be deleted
     * @return The modified root that does not contain the item
     */

    private Node<E> delete(Node<E> root, E target) {
        if (root == null) {
            deleteReturn = null;
            return root;
        }
        int r = target.compareTo(root.data);

        if (r < 0) {
            root.left = delete(root.left, target);
            return root;
        } else if (r > 0) {
            root.right = delete(root.right, target);
            return root;
        } else {
            deleteReturn = root.data;
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                if (root.left.right == null) {
                    root.data = root.left.data;
                    root.left = root.left.left;
                    return root;
                } else {
                    root.data = findLargestChild(root.left);
                    return root;
                }
            }
        }
    }

    /**
     * Return true, if the target stored in the tree is deleted,
     * or return false if the target is not found.
     * @param target The item to be deleted
     * @return true if the target stored in the tree is found,
     * or false if the target is not found
     */
    @Override
    public boolean remove(E target) {
        E result = delete(target);
        if (result == null)
        return false;
        else return true;
    }

    /**
     * Starter method size.
     * @return size of tree
     */
    @Override
    public int size() {
        size = size(root);
        return size;
    }

    /**
     * Recursive size method.
     * @param root The local root
     * @return the size of tree
     */
    private int size(Node<E> root) {
        if (root == null) return 0;
        int n = 1;
        return 1 + size(root.left) + size(root.right);
    }

    /**
     * Starter Method Height
     * @return Height of tree
     */
    @Override
    public int height(){
        height = height(root);
        return height;
    }

    /**
     * Recursive height method.
     * @param root the local root
     * @return the height of tree
     */
    private int height(Node<E> root) {
        if (root == null) return -1;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else return rightHeight + 1;
    }

    /**
     * Clear the whole tree.
     */
    @Override
    public void clear(){
        root = null;
        size = 0;
        height = 0;
    }
}
