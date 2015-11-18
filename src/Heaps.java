/**
 * Created by Administrator on 2015/11/18.
 */
import java.util.*;
public class Heaps<AnyType extends Comparable<AnyType>> {
    private static final int CAPACITY = 2;

    private int size;
    private AnyType[] heap;

    public Heaps() {
        size = 0;
        heap = (AnyType[]) new Comparable[CAPACITY];
    }

    public Heaps(AnyType[] arr) {
        size = arr.length;
        heap = (AnyType[]) new Comparable[size];
    }
}
