/**
 * Created by Administrator on 2015/11/17.
 */
public class Test {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        String[] sarr = {"Cambridge", "Crapo", "Guangzhou", "Bryn Mawr", "Boring", "Hell",
                "Walla Walla", "Surprise", "Joseph", "Romance", "Mars", "Nuttsville",
                "Rough and Ready", "Dynamite", "Good Grief"};
        for (String str : sarr) {
            bst.add(str);
        }
        System.out.println("size:" + bst.size() + "  height: " + bst.height() + "  " + bst.displayInOrder() +"\n -------- \n" + bst.displayPreOrderTraverseAsLine() + "\n ---------- \n" +
                bst.displayPostOrder());
        bst.clear();
        System.out.println("BST Size: " + bst.size() + " " + "BST Height: " + bst.height());
    }
}
