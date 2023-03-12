package flores.rodrigo.arvore_avl;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Program {
    public static void main(String[] args) {

        ArvoreAvl tree = new ArvoreAvl();
        tree.insertNode(7);
        tree.insertNode(3);
        tree.insertNode(2);
        tree.insertNode(9);
        tree.insertNode(15);
        tree.insertNode(19);
        tree.removeNode(9);

startConsole();
    }

    private static void startConsole() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Digite a opção desejada:");
            System.out.println("1. Inserir valor");
            System.out.println("2. Remover valor ");
            System.out.println("3. Imprimir a árvore");
            System.out.println("4. Sair");
            Integer choice;
            try {
                choice = scanner.nextInt();
            }catch(InputMismatchException e) {
                choice = -1 ;
                scanner.nextLine();
            }
            switch (choice) {
                case 1: insertValue(); break;
                case 2: removeValue(); break;
                case 3: printTree(); break;
                case 4: exit(0);
                default: System.out.println("Opção inválida");
            }
        }
    }


    private static void insertValue() {

    }
    private static void removeValue() {
    }
    private static void printTree() {
    }
}
