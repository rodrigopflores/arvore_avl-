package flores.rodrigo.arvore_avl;

import lombok.Data;

import static java.util.Objects.*;

@Data
public class AvlTree {
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
        Node newNode = new Node(newKey);
        if (findNode(newKey) != null) {
            System.out.println("Valor já inserido");
            return;
        };
        if (isNull(root))
            root = newNode;
        else
            insertAsChildNode(root, newNode);
        printTree();
        balanceTree();
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
        printTree();
        balanceTree();
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
        if (isNull(parent)) {
            root = largestOnTheLeft;
        } else {

        parent.insertChild(largestOnTheLeft);
        }
        largestOnTheLeft.insertChildren(leftChild, rightChild);
        largestOnTheLeft.removeChild(largestOnTheLeft); // in case the largest on the left was the one on the left.
    }

    private Node getLargestOnSubtree(Node node) {
        Node rightNode = node.getRight();
        return isNull(rightNode) ? node : getLargestOnSubtree(rightNode);
    }

    public void printTree() {
        System.out.println("\nÁrvore:");
        if (isNull(root)) {
            System.out.println("A árvore está vazia\n");
        } else {
            System.out.println("RT:" +root.getKey());
        printNode(root, "");
            System.out.println();
        }

    }

    private void printNode(Node node, String prefix) {
        if (node == null) {
            return;
        }


        Node left = node.getLeft();
        Node right = node.getRight();
        if (left != null && right != null) {
                System.out.println(prefix + "├──L:"+ left.getKey());
                printNode(left, prefix + "│   ");

                System.out.println(prefix + "└──R:"+ right.getKey());

                printNode(right, prefix + "    ");
        }else if (nonNull(left))  {
            System.out.println(prefix + "└──L:"+ left.getKey() );
            printNode(left, prefix + "    ");

        }else if (nonNull(right)) {
            System.out.println(prefix + "└──R:"+ right.getKey());

            printNode(right, prefix + "    ");

        }

    }

    public  void balanceTree() {
        balanceNode(root);
    }

    private Integer balanceNode(Node node) {
        if (isNull(node)) {
            return 0;
        }
        Integer leftBF = balanceNode(node.getLeft());
        Integer rightBF = balanceNode(node.getRight());
        Integer balanceFactor = getSubtreeHeight(node.getLeft()) - getSubtreeHeight(node.getRight());
        if (balanceFactor > 1) {
            rotateToTheRight(node, leftBF);
            System.out.println("Nova configuração");
            printTree();
        }
        if (balanceFactor < -1) {
            rotateToTheLeft(node, rightBF);
            System.out.println("Nova configuração");
            printTree();
        } else {
            return balanceFactor;
        }
        return getSubtreeHeight(node.getLeft()) - getSubtreeHeight(node.getRight());

    }

    private void rotateToTheLeft(Node node, Integer rightBF) {
        if (rightBF < 0) {
            System.out.println("Nó " + node.getKey() + " desbalanceado, fazendo uma rotação simples à esquerda.");
            simpleLeftRotation(node);
        } else {
            System.out.println("Nó " + node.getKey() + " desbalanceado, fazendo uma rotação dupla à esquerda.");
            doubleLeftRotation(node);
        }
    }

    private void doubleLeftRotation(Node node) {
        simpleRightRotation(node.getRight());
        simpleLeftRotation(node);
    }

    private void simpleLeftRotation(Node node) {
        if (node ==null) return;
        Node parent = findParent(node);
        Node rightChild = node.getRight();
        if (isNull(parent)) {
            root = rightChild;
        } else if (parent.getKey() > node.getKey())  {

            parent.setLeft(rightChild);
        } else {
            parent.setRight(rightChild);
        }
        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);
    }

    private void rotateToTheRight(Node node, Integer leftBF) {
        if (leftBF > 0) {
            System.out.println("Nó " + node.getKey() + " desbalanceado, fazendo uma rotação simples à direita.");
            simpleRightRotation(node);
        } else {
            System.out.println("Nó " + node.getKey() + " desbalanceado, fazendo uma rotação dupla à esquerda.");
            doubleRightRotation(node);
        }
    }

    private void doubleRightRotation(Node node) {
        simpleLeftRotation(node.getLeft());
        simpleRightRotation(node);
    }

    private void simpleRightRotation(Node node) {
        if (node ==null) return;
        Node parent = findParent(node);
        Node leftChild = node.getLeft();
        if (isNull(parent)) {
            root = leftChild;
        } else if (parent.getKey() > node.getKey())  {

            parent.setLeft(leftChild);
        } else {
            parent.setRight(leftChild);
        }
        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);
    }

    private Integer getSubtreeHeight(Node node) {
        if (isNull(node)) {
            return 0;
        }
        return Math.max(getSubtreeHeight(node.getLeft()), getSubtreeHeight(node.getRight())) + 1;
//        Integer left = isNull(node.getLeft()) ? 0 : getSubtreeHeight(node.getLeft());
//        Integer right = isNull(node.getRight()) ? 0 : getSubtreeHeight(node.getRight());
//        return Math.max(left, right) + 1;
    }

}
