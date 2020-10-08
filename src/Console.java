import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * This class represents the console.
 *
 * @author Bo Zhang
 * @version 1.0
 */
public class Console {
    public static void main(String[] args) {
        Queue<Node> nodeQueue = new LinkedBlockingDeque<>();

        try (Scanner scan = new Scanner(new File("names/small.txt"))) {
            while (scan.hasNextLine()) {
                String name = scan.nextLine(); // get the name
                Node node = new Node(name);    // create a node with the name
                nodeQueue.offer(node);         // put the node in the queue
            }
        } catch (FileNotFoundException message) {
            System.out.println("Error: " + message);
        }

        while (nodeQueue.size() != 1) {
            Node node = new Node();
            node.setLeft(nodeQueue.poll());
            node.setRight(nodeQueue.poll());
            nodeQueue.offer(node);
        }

        Node root = nodeQueue.poll();

        System.out.print("Post-order initial tree: ");
        printPostOrder(root);
        System.out.println();

        Node winner = setWinners(root);

        System.out.print("Tree with winners: ");
        printPostOrder(root);
        System.out.println("\n");

        printResults(root);
    }

    /**
     * Prints each node of the binary tree in post order form.
     *
     * @param current  the current node
     */
    public static void printPostOrder(Node current) {
        if (current.getLeft() != null) {
            printPostOrder(current.getLeft());
        }

        if (current.getRight() != null) {
            printPostOrder(current.getRight());
        }

        System.out.print(current.getData() + " ");
    }

    /**
     * Sets winners of each round.
     *
     * @param current  the current node
     * @return the current node
     */
    public static Node setWinners(Node current) {
        if (current.getLeft() == null && current.getRight() == null) {
            return current;
        }

        if (current.getLeft().getData().equals("?")) {
            setWinners(current.getLeft());
        }

        if (current.getRight().getData().equals("?")) {
            setWinners(current.getRight());
        }

        current.setData(pickRandomWinnerNode(current).getData());

        return current;
    }

    /**
     * Generates a random winner.
     * Randomly pick a child, either left node or right node.
     *
     * @param node  the parent node
     * @return the winner node
     */
    public static Node pickRandomWinnerNode(Node node) {
        Random random = new Random();
        int num = random.nextInt(2);

        if (num == 0) {
            return node.getLeft();
        } else {
            return node.getRight();
        }
    }

    /**
     * Prints the results of each round.
     *
     * @param root  the root node
     */
    public static void printResults(Node root) {
        int height = height(root);

        Queue<Node> queue = new LinkedBlockingDeque<>();
        queue.offer(root);

        Node node0 = queue.poll();
        System.out.println("Winner: " + node0.getData());
        addChildren(queue, node0);

        while (!queue.isEmpty()) {
            int numNodes = queue.size();
            System.out.print("Round #" + (height-1) + ": ");

            while (numNodes > 1) {
                Node node1 = queue.poll();
                Node node2 = queue.poll();

                if (node1.getIsWinner()) {
                    System.out.print(node1.getData() + "(W) - " + node2.getData() + ", ");
                } else {
                    System.out.print(node1.getData() + " - " + node2.getData() + "(W), ");
                }

                addChildren(queue, node1);
                addChildren(queue, node2);

                numNodes = numNodes - 2;
            }
            height--;
            System.out.println();
        }
    }

    /**
     * Adds the children of the given node to the queue.
     *
     * @param queue  the queue keeping track of the entrants
     * @param node  the parent node
     */
    public static void addChildren(Queue queue, Node node) {
        if (node.getLeft() != null) {
            if (node.getData().equals(node.getLeft().getData())) {
                node.getLeft().setIsWinner(true);
            }
            queue.offer(node.getLeft());
        }

        if (node.getRight() != null) {
            if (node.getData().equals(node.getRight().getData())) {
                node.getRight().setIsWinner(true);
            }
            queue.offer(node.getRight());
        }
    }

    /**
     * Returns the height starting from the root.
     *
     * @param root  the root node
     * @return the height
     */
    public static int height(Node root) {
        if (root == null) return 0;

        return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }
}
