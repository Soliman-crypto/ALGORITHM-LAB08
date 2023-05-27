package For;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreeOfBinarySearch {
    private AVLTree.Node root;

    public TreeOfBinarySearch() {
    }

    public TreeOfBinarySearch(AVLTree.Node root) {
        this.root = root;
    }

    public int getNumberOfNodes(AVLTree.Node node) {
        if (node == null) return 0;
        return 1 + getNumberOfNodes(node.getLeft()) + getNumberOfNodes(node.getRight());
    }

    public int numberOfNodes() {
        return getNumberOfNodes(root);
    }

    public int getHeight(AVLTree.Node node) {
        if (node == null) return 0;
        int l = getHeight(node.getLeft());
        int r = getHeight(node.getRight());
        return Math.max(l, r) + 1;
    }

    public boolean isBST(AVLTree.Node node, int low, int high) {
        if (node == null) return true;
        if (node.getKey() <= low || node.getKey() >= high) {
            return false;
        }
        return isBST(node.getLeft(), low, node.getKey()) && isBST(node.getRight(), node.getKey(), high);
    }

    public AVLTree.Node searchByKey(AVLTree.Node node, int key) {
        if (node == null || node.getKey() == key) {
            return node;
        }
        if (key < node.getKey()) {
            return searchByKey(node.getLeft(), key);
        }
        return searchByKey(node.getRight(), key);
    }


    public AVLTree.Node getMin(AVLTree.Node node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    public AVLTree.Node getMax(AVLTree.Node node) {
        while (node.getRight() != null)
            node = node.getRight();
        return node;
    }


    public void deleteSubTree(int key) {
        AVLTree.Node n = searchByKey(root, key);
        if (n == null) return;
        AVLTree.Node p = n.getParent();
        if (p.getLeft() != null && p.getLeft().getKey() == key)
            p.setLeft(null);
        if (p.getRight() != null && p.getRight().getKey() == key)
            p.setRight(null);
    }

    private AVLTree.Node deleteRecursive(AVLTree.Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key == node.getKey()) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            int smallestValue = getMin(node.getRight()).getKey();
            node.setKey(smallestValue);
            node.setRight(deleteRecursive(node.getRight(), smallestValue));
            return node;
        }
        if (key < node.getKey()) {
            node.setLeft(deleteRecursive(node.getLeft(), key));
            return node;
        }
        node.setRight(deleteRecursive(node.getRight(), key));
        return node;
    }

    private AVLTree.Node insert(AVLTree.Node node, int key) {
        if (node == null) {
            return new AVLTree.Node();
        }
        if (key < node.getKey()) {
            node.setLeft(insert(node.getLeft(), key));
        } else if (key > node.getKey()) {
            node.setRight(insert(node.getRight(), key));
        } else { //  already exists
            return node;
        }
        return node;
    }

    public AVLTree.Node insert(int key) {
        return insert(root, key);
    }


    public void readFromFile(String path) {

        try (Scanner scanner = new Scanner(new File(path))) {
            int n = scanner.nextInt();
            AVLTree.Node[] nodes = new AVLTree.Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new AVLTree.Node();
            }
            for (int i = 0; i < n; i++) {
                nodes[i].setKey(scanner.nextInt());
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                if (left != 0) {
                    nodes[i].setLeft(nodes[left - 1]);
                    nodes[left - 1].setParent(nodes[i]);
                }
                if (right != 0) {
                    nodes[i].setRight(nodes[right - 1]);
                    nodes[right - 1].setParent(nodes[i]);
                }
            }
            root = nodes[0];
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public AVLTree.Node getRoot() {
        return root;
    }

    public void setRoot(AVLTree.Node root) {
        this.root = root;
    }
}
