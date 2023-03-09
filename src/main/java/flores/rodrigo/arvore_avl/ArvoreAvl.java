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

    public void removeNode(Integer key) {

    }
}
