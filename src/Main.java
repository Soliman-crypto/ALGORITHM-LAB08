import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        AVLTree tree = new AVLTree();
        tree.readFile("src\\input\\input.txt");
        System.out.println("--------------------------------------");
        FileWriter fileWriter = new FileWriter("src\\input\\input1.txt");
        fileWriter.write(" " + "\n");
        tree.setRoot(tree.inorderTraversal(tree.getRoot(), fileWriter));
        fileWriter.close();






    }
}