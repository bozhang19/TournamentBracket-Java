/**
 * This class represents a person node.
 *
 * @author Bo Zhang
 * @version 1.0
 */
public class Node {
    private String data;
    private Node left, right;
    private boolean isWinner;

    /**
     * Creates a empty person node object.
     */
    public Node() {
        data = "?";
    }

    /**
     * Creates a person node object with given string value.
     *
     * @param value  the string value
     */
    public Node(String value) {
        data = value;
    }

    /**
     * Creates a person node object with given string value, left, and right node.
     *
     * @param value  the string value
     * @param left  the left node
     * @param right  the right node
     */
    public Node(String value, Node left, Node right) {
        data = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the data field of the node.
     *
     * @return data  the data field
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data field of the node with given data.
     *
     * @param data  the new data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Returns the left node of this node.
     *
     * @return left  the left node
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the left node to the given node.
     *
     * @param left  the new left node
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Returns the right node of this node.
     *
     * @return right  the right node
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets the right node to the given node.
     *
     * @param right  the new right node
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Checks if this node is a winner.
     *
     * @return true if this node is a winner, false otherwise
     */
    public boolean getIsWinner() {
        return isWinner;
    }

    /**
     * Sets the boolean field to the given boolean value.
     *
     * @param isWinner  the new boolean value
     */
    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    /**
     * Returns a string representation of this node.
     *
     * @return a String
     */
    public String toString() {
        String leftString = (left == null) ? "null" : left.data.toString();
        String rightString = (right == null) ? "null" : right.data.toString();

        return leftString + " <-- " + data + " --> " + rightString;
    }
}
