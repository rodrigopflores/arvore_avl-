package flores.rodrigo.arvore_avl;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Program {
    public static void main(String[] args) {


startConsole();
    }

    private static void startConsole() {
        Scanner scanner = new Scanner(System.in);
        AvlTree tree = new AvlTree();
        while (true) {
            System.out.println("Digite a opção desejada:");
            System.out.println("1. Inserir valor");
            System.out.println("2. Remover valor ");
            System.out.println("3. Imprimir a árvore");
            System.out.println("4. Sair");
            System.out.print("Opção: ");

            Integer choice;
            try {
                choice = scanner.nextInt();
            }catch(InputMismatchException e) {
                choice = -1 ;
                scanner.nextLine();
            }
            switch (choice) {
                case 1: insertValue(scanner, tree); break;
                case 2: removeValue(scanner, tree); break;
                case 3: printTree(tree); break;
                case 4: exit(0);
                default: System.out.println("Opção inválida");
            }
        }
    }


    private static void insertValue(Scanner scanner, AvlTree tree) {
        System.out.print("Digite o valor a ser inserido: ");
        Integer value;
        try {
            value = scanner.nextInt();
        }catch(InputMismatchException e) {
            System.out.println("Valor inválido");
            return;
        }
        tree.insertNode(value);
    }
    private static void removeValue(Scanner scanner, AvlTree tree) {
        System.out.print("Digite o valor a ser removido: ");
        Integer value;
        try {
            value = scanner.nextInt();
        }catch(InputMismatchException e) {
            System.out.println("Valor inválido");
            return;
        }
        tree.removeNode(value);
    }
    private static void printTree(AvlTree tree) {
        tree.printTree();
    }
}
