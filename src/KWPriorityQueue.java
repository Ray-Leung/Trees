import java.util.*;

/**
 * Created by Administrator on 2015/11/18.
 */
public class KWPriorityQueue<E> extends AbstractQueue<E>  implements Queue<E> {

    public ArrayList<E> data;
    Comparator<E> comparator = null;

    public KWPriorityQueue() {
        data = new ArrayList<>();
    }

    public KWPriorityQueue(Comparator<E> comparator) {
        data = new ArrayList<>();
        this.comparator = comparator;
    }
    @Override
    public Iterator<E> iterator() {
        return new itr();
    }

    private final class itr implements Iterator<E> {

         int cursor = 0;

        @Override
        public boolean hasNext() {
            return (cursor < size());
        }

        @Override
        public E next() {
            return data.get(cursor++);
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    /**
     * Insert an item into the priority queue.
     * pre: The ArrayList data is in heap order
     * post: The item is in the priority queue and
     * data is in heap order
     * @param item The item to be inserted
     * @return true, if the item is inserted, or false if not yet
     */
    @Override
    public boolean offer(E item) {
        data.add(item);
        int child = data.size()-1;
        int parent = (child - 1) / 2;

        while (parent >= 0 && compare(data.get(parent), data.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1)/2;
        }
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty())
            return null;

        E result = data.get(0);
        if (data.size() == 1) {
            data.remove(0);
            return result;
        }
        data.set(0, data.remove(data.size() - 1));
        int parent = 0;
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= data.size()) break;
            int rightChild = leftChild + 1;
            int minChild = leftChild;
            if (rightChild < data.size() && compare(data.get(leftChild), data.get(rightChild)) > 0) {
                minChild = rightChild;
            }
            if (compare(data.get(parent), data.get(minChild)) > 0) {
                swap(parent, minChild);
                parent = minChild;
            } else break;
        }
        return result;
    }

    @Override
    public E peek() {
        return data.get(0);
    }

    /**
     * Compare two items using either a Comparator object's compare method
     * or their natural ordering using method compareTo.
     * @param left left One item
     * @param right right The other item
     * @return Negative int if left less than right, 0 if left equals right
     *          positive, if left > right
     */
    private int compare(E left, E right) {
        if (comparator != null) {
            return comparator.compare(left,right);
        } else
        return ((Comparable<E>) left).compareTo(right);
    }
    private void swap(int i, int j) {
        E temp = data.get(j);
        data.set(j, data.get(i));
        data.set(i, temp);
    }
}
