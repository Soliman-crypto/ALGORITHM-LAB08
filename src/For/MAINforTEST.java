package For;

public class MAINforTEST {

    public static void main(String[] args) {
        AVLTree.Node f = new AVLTree.Node();
        AVLTree.Node g = new AVLTree.Node();
        AVLTree.Node h = new AVLTree.Node();
        AVLTree.Node i = new AVLTree.Node();
        AVLTree.Node d = new AVLTree.Node();
        AVLTree.Node e = new AVLTree.Node();
        AVLTree.Node c = new AVLTree.Node();
        AVLTree.Node b = new AVLTree.Node();
        AVLTree.Node a = new AVLTree.Node();

        TreeOfBinarySearch tree = new TreeOfBinarySearch(a);

        System.out.println("Количество вершин в поддереве " + tree.getNumberOfNodes(b));
        System.out.println("Во всем дереве количество вершин = " + tree.numberOfNodes());

        System.out.println("Высота = " + tree.getHeight(a));
        System.out.println("Поиск значения: " + tree.searchByKey(a, 7));

        System.out.println("Это дерево поиска: " + tree.isBST(a, Integer.MIN_VALUE, Integer.MAX_VALUE));

        System.out.println("Min = " + tree.getMin(a));
        System.out.println("Max = " + tree.getMax(a));


        TreeOfBinarySearch treeFromFile = new TreeOfBinarySearch();
        treeFromFile.readFromFile("input.txt");
        System.out.println("Во всем дереве количество вершин = " + treeFromFile.numberOfNodes());

        System.out.println("Высота = " + treeFromFile.getHeight(treeFromFile.getRoot()));





    }
}