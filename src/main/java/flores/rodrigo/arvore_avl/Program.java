package flores.rodrigo.arvore_avl;

public class Program {
    public static void main(String[] args) {

        ArvoreAvl tree = new ArvoreAvl();
        tree.insertNode(5);
        tree.insertNode(51);
        tree.insertNode(1);
        tree.insertNode(7);
        tree.insertNode(9);
        tree.insertNode(11);
        tree.insertNode(3);
        tree.insertNode(8);
        tree.insertNode(2);
        tree.printTree();
        tree.removeNode(3);
        tree.printTree();
        tree.removeNode(9);
        tree.printTree();

    }

}

