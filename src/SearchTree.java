/**
 * Created by Administrator on 2015/11/17.
 */
public interface SearchTree<E> {
    boolean add(E item);

    boolean contain(E target);

    E find(E target);

    E delete(E target);

    boolean remove(E target);

    /**
     * @return the number of items in the tree
     */
    int size();
    /**
     * empty the tree of all nodes and set size and height to 0
     */
    void clear();

    /**
     * height can be determined by the lowest depth that an
     * add() has put a node,  or the lowest depth that a
     * traversal has visited.
     * @return the height the tree, 0 means null root
     */
    int height();
}
