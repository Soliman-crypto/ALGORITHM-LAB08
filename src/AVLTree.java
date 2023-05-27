import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AVLTree {
    private Node root;

    public AVLTree() {}

    public AVLTree(Node root) {
        this.root = root;
    }

    public void readFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            int n = scanner.nextInt();
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node();
            }
            for (int i = 0; i < n; i++) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                if (left != 0) {
                    nodes[i].setLeft(nodes[left - 1]);
                }
                if (right != 0) {
                    nodes[i].setRight(nodes[right - 1]);
                }
            }
            root = nodes[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node inorderTraversal(Node node, FileWriter fileWriter) throws IOException {
        if (node != null) {
            inorderTraversal(node.getLeft(), fileWriter);
            if (fileWriter != null) {
                fileWriter.write(node.getKey() + " " + ((node.getLeft() != null) ? node.getLeft().getKey() : 0) +" " + ((node.getRight() != null) ? node.getRight().getKey() : 0) + "\n");
            }
            inorderTraversal(node.getRight(), fileWriter);
        }
        return node;
    }

    public Node rotateLeft(Node node) {
        Node newNode = node.getRight();
        node.setRight(newNode.getLeft());
        newNode.setLeft(node);
        updateHeight(node);
        updateHeight(newNode);
        return newNode;
    }

    public Node rotateRight(Node node) {
        Node newNode = node.getLeft();
        node.setLeft(newNode.getRight());
        newNode.setRight(node);
        updateHeight(node);
        updateHeight(newNode);
        return newNode;
    }

    public Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.getRight().getRight()) > height(z.getRight().getLeft())) {
                z = rotateLeft(z);
            } else {
                z.setRight(rotateRight(z.getRight()));
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.getLeft().getLeft()) > height(z.getLeft().getRight())) {
                z = rotateRight(z);
            } else {
                z.setLeft(rotateLeft(z.getLeft()));
                z = rotateRight(z);
            }
        }
        return z;
    }

    private boolean height(Node right) {
        return false;
    }

    public int getHeight(Node node) {
        if (node == null){
            return -1;
        }
        return node.getHeight();
    }

    public int updateHeight(Node node) {
        if (node == null) {
            return -1;
        }
        int left = updateHeight(node.getLeft());
        int right = updateHeight(node.getRight());
        node.setHeight(Math.max(left, right) + 1);
        return node.getHeight();
    }

    public int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.getRight()) - getHeight(node.getLeft());
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static class Node {
        private int key;
        private int height;
        private Node left;
        private Node right;

        public Node() {
            this.key = key;
            this.height = 0;
            this.left = null;
            this.right = null;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return null;
        }

        public void setParent(Node node) {
        }
    }
}