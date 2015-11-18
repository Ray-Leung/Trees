import java.io.PrintStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by Administrator on 2015/11/18.
 */
public class HuffmanTree<E> extends BinaryTree<E> implements Serializable{

    private  BinaryTree<HuffData> huffTree;

    public static class HuffData implements Serializable {
        private double weight;
        private Character symbol;

        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }
    }
        private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffData>> {
            @Override
            public int compare(BinaryTree<HuffData> left, BinaryTree<HuffData> right) {
                double weightL = left.getData().weight;
                double weightR = right.getData().weight;
                return Double.compare(weightL, weightR);
            }
        }
    public void buildTree(HuffData[] symbols) {
        KWPriorityQueue<BinaryTree<HuffData>> huffQueue = new KWPriorityQueue<>(new CompareHuffmanTrees());
        for (HuffData nextSymbol : symbols) {
            BinaryTree<HuffData> hTree = new BinaryTree<>(nextSymbol, null, null);
            huffQueue.offer(hTree);
        }
        while (huffQueue.size() > 1) {
            BinaryTree<HuffData> left = huffQueue.poll();
            BinaryTree<HuffData> right = huffQueue.poll();
            double wightL = left.getData().weight;
            double withtR = right.getData().weight;
            HuffData sum = new HuffData(wightL + withtR, null);
            BinaryTree<HuffData> parent = new BinaryTree<>(sum, left, right);
        }
        huffTree = huffQueue.poll();
    }

    public String decode(String message) {
        StringBuilder sb = new StringBuilder();
        BinaryTree<HuffData> currTree = new BinaryTree<>();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '1') {
                currTree = currTree.getRightSubtree();
            } else {
                currTree = currTree.getLeftSubtree();
            }
            if (currTree.isLeaf()) {
                HuffData hdata = currTree.getData();
                sb.append(hdata.symbol);
                currTree = huffTree;
            }
        }
        return sb.toString();
    }

    private void printCode(PrintStream out) {

    }
}
