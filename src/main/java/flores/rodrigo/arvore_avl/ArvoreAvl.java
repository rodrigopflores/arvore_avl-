package flores.rodrigo.arvore_avl;

import lombok.Data;

import static java.util.Objects.*;

@Data
public class ArvoreAvl {
    private Node root;

    public Node findNode(Integer key) {
        Node target = new Node(key);
        return searchSubTree(root, target);
    }

    private Node searchSubTree(Node node, Node target) {
        return (isNull(node) || node.equals(target)) ? node : searchChildren(node, target);
    }

    private Node searchChildren(Node parent, Node target) {
        return parent.isGreaterThan(target) ? searchSubTree(parent.getLeft(), target) : searchSubTree(parent.getRight(), target);
    }

    public Node findParent(Node node) {
        return (isNull(root) || root.equals(node)) ? null : checkParentsChildren(root, node);
    }

    private Node checkParentsChildren(Node parent, Node node) {
        return isNull(parent) || parent.hasChild(node) ? parent : selectParentChild(parent, node);
    }

    private Node selectParentChild(Node parent, Node node) {
        return parent.isGreaterThan(node) ? checkParentsChildren(parent.getLeft(), node) : checkParentsChildren(parent.getRight(), node);
    }

    public void insertNode(Integer newKey) {
        requireNonNull(newKey);
        Node newNode = new Node(newKey);

        if (isNull(root))
            root = newNode;
        else
            insertAsChildNode(root, newNode);
    }

    private void insertAsChildNode(Node parent, Node newNode) {
        if (newNode.isGreaterThan(parent))
            insertAsRightChild(parent, newNode);
        else
            insertAsLeftChild(parent, newNode);
    }

    private void insertAsRightChild(Node parent, Node newNode) {
        if (isNull(parent.getRight()))
            parent.setRight(newNode);
        else
            insertAsChildNode(parent, newNode);
    }

    private void insertAsLeftChild(Node parent, Node newNode) {
        if (isNull(parent.getLeft()))
            parent.setLeft(newNode);
        else
            insertAsChildNode(parent, newNode);
    }

    public Node removeNode(Integer key) {
        Node node = findNode(key);
        if (isNull(node)) {
            return null;
        }
        return removeNode(node);

    }

    private Node removeNode(Node node) {
        if (node.isLeaf()) {
            return removeLeaf(node);
        } else if (node.hasSingleChild()) {
            return removeParentOfOne(node);
        }
    }

    private void removeParentOfOne(Node node) {
        Node grandfather = findParent(node);
        Node child = node.getSingleChild();
        grandfather.insertChild(child);
    }

    private void removeLeaf(Node node) {
        Node parent = findParent(node);
        parent.removeChild(node);
    }

    private Node removeParentOfToo(Node node) {
        Node leftChild = node.getLeft();
        Node largestOnTheLeft = getLargestOnSubtree(node.getLeft());
    }

    private Node getLargestOnSubtree(Node node) {
        Node rightNode = node.getRight();
        return isNull(rightNode) ? node : rightNode;
    }
}
