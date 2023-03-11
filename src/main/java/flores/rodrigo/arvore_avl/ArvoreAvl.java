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
            insertAsChildNode(parent.getRight(), newNode);
    }

    private void insertAsLeftChild(Node parent, Node newNode) {
        if (isNull(parent.getLeft()))
            parent.setLeft(newNode);
        else
            insertAsChildNode(parent.getLeft(), newNode);
    }

    public void removeNode(Integer key) {
        Node node = findNode(key);
        if (isNull(node)) {
            return;
        }
       removeNode(node);

    }

    private void removeNode(Node node) {
        if (node.isLeaf()) {
            removeLeaf(node);
        } else if (node.hasSingleChild()) {
            removeParentOfOne(node);
        } else {
            removeByCopy(node);
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

    private void removeByCopy(Node node) {
        Node leftChild = node.getLeft();
        Node rightChild = node.getRight();
        Node largestOnTheLeft = getLargestOnSubtree(leftChild);
        removeNode(largestOnTheLeft);
        Node parent = findParent(node);
        parent.insertChild(largestOnTheLeft);
        largestOnTheLeft.insertChildren(leftChild, rightChild);
        largestOnTheLeft.removeChild(largestOnTheLeft); // in case the largest on the left was the one on the left.
    }

    private Node getLargestOnSubtree(Node node) {
        Node rightNode = node.getRight();
        return isNull(rightNode) ? node : getLargestOnSubtree(rightNode);
    }

    public void printTree() {
        if (isNull(root)) {
            System.out.println("the tree is empty");
        } else {
        printNode(root, "");

        }

    }

    private void printNode(Node node, String prefix) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + "─ " + node.getKey());

        if (node.getLeft() != null || node.getRight() != null) {
            if (node.getLeft() != null) {
                printNode(node.getLeft(), prefix + "    └── ");
            }

            if (node.getRight() != null) {
                printNode(node.getRight(), prefix + "    ├── ");
            }
        }

//        if (nonNull(node.getRight())) {
//            printNode(node.getRight(), degree + 1);
//            for (int i = 0; i < degree; i++)
//                System.out.print(" ");
//        }
//        for (int i = 0; i < degree; i++)
//            System.out.print("   ");
//        System.out.println(node.getKey());
//        if (nonNull(node.getLeft())) {
//            for (int i = 0; i < degree; i++)
//                System.out.print("   ");
//            System.out.println(" \\");
//            printNode(node.getLeft(), degree + 1);
//        }

//        System.out.println(currentPath + "-" + node.getKey());
//        if (nonNull(node.getLeft()))
//        printNode(node.getLeft(), currentPath + " |");
//        if (nonNull(node.getRight()))
//        printNode(node.getRight(), currentPath + "  ");
    }
}
