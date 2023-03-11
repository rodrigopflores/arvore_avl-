package flores.rodrigo.arvore_avl;

import lombok.Data;

import static java.util.Objects.*;

@Data
public class Node {
    private Integer key;
    private Node left;
    private Node right;

    public Node(Integer key) {
        this.key = key;
    }

    public void insertChild(Node node) {
        if (node.isGreaterThan(this)) {
            right = node;
        } else {
            left = node;
        }
    }

    public void insertChildren(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node removeChild(Node node) {
        Node removed = null;
        if (node.equals(left)) {
            removed = left;
            left = null;
        } else if (node.equals(right)) {
            removed = right;
            right = null;
        }
        return removed;
    }

    public void removeChildren() {
        left = null;
        right = null;
    }

    public boolean isGreaterThan(Node node) {
        return key.compareTo(node.getKey()) > 0;
    }

    public boolean hasChild(Node node) {
        return node.equals(left) || node.equals(right);
    }

    public boolean isLeaf() {
        return isNull(left) && isNull(right);
    }

    public boolean hasSingleChild() {
        return Boolean.logicalXor(isNull(left), isNull(right));
    }

    public Node getSingleChild() {
        return nonNull(left) ? left : right;
    }

    public boolean contains(Integer key) {
        return this.key.equals(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return key.equals(node.key);
    }

    @Override
    public int hashCode() {
        return hash(key);
    }
}
