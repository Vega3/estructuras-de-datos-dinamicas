import java.util.Scanner;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    public static TreeNode insertNode(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.value) {
            root.left = insertNode(root.left, value);
        } else if (value > root.value) {
            root.right = insertNode(root.right, value);
        }
        return root;
    }

    public static TreeNode buildTree() {
        Scanner scanner = new Scanner(System.in);
        TreeNode root = null;

        while (true) {
            System.out.print("Ingrese '1' para un árbol infinito, '0' para un árbol vacio, o 'X' para detenerse: ");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("X")) {
                break;
            } else if (choice.equals("0")) {
                root = null;
                break;
            } else if (choice.equals("1")) {
                try {
                    while (!choice.equals("X") || !choice.equals("x")) {
                        System.out.print("Ingrese un entero positivo para el nodo: ");
                        int value = Integer.parseInt(scanner.nextLine());
                        if (value > 0) {
                            root = insertNode(root, value);
                        } else {
                            System.out.println("El valor debe ser un entero positivo.");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Ingrese un entero positivo o 'X' para detenerse.");
                }
            } else {
                System.out.println("Opción no válida. Ingrese '0', '1' o 'X'.");
            }
        }

        return root;
    }

    public static int countLeaves(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeaves(node.left) + countLeaves(node.right);
    }

    public static int treeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = treeHeight(node.left);
        int rightHeight = treeHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isComplete(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return true;
        }
        if (node.left != null && node.right != null) {
            return isComplete(node.left) && isComplete(node.right);
        }
        return false;
    }

    public static int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static boolean searchValue(TreeNode node, int targetValue) {
        if (node == null) {
            return false;
        }
        if (node.value == targetValue) {
            return true;
        }
        boolean leftResult = searchValue(node.left, targetValue);
        boolean rightResult = searchValue(node.right, targetValue);
        return leftResult || rightResult;
    }    

    public static void main(String[] args) {
        TreeNode root = buildTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de operaciones:");
            System.out.println("1. Recorrido PostOrder");
            System.out.println("2. Recorrido PreOrder");
            System.out.println("3. Recorrido InOrder");
            System.out.println("4. Número de Hojas");
            System.out.println("5. Altura");
            System.out.println("6. Es completo");
            System.out.println("7. Número de Nodos");
            System.out.println("8. Para encontrar un valor especifico en el arbol");
            System.out.println("X. Salir");
            System.out.print("Seleccione una opción: ");
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("1")) {
                System.out.println("Recorrido PostOrder:");
                postorderTraversal(root);
            } else if (choice.equals("2")) {
                System.out.println("Recorrido PreOrder:");
                preorderTraversal(root);
            } else if (choice.equals("3")) {
                System.out.println("Recorrido InOrder:");
                inorderTraversal(root);
            } else if (choice.equals("4")) {
                System.out.println("Número de Hojas: " + countLeaves(root));
            } else if (choice.equals("5")) {
                System.out.println("Altura del árbol: " + treeHeight(root));
            } else if (choice.equals("6")) {
                if (isComplete(root)) {
                    System.out.println("El árbol es completo.");
                } else {
                    System.out.println("El árbol no es completo.");
                }
            } else if (choice.equals("7")) {
                System.out.println("Número de Nodos: " + countNodes(root));
            } else if (choice.equals("X") || choice.equals("x")) {
                break;
            } else if (choice.equals("8")) {
                System.out.print("Ingrese el valor que desea buscar en el árbol: ");
                int targetValue = Integer.parseInt(scanner.nextLine());
                if (searchValue(root, targetValue)) {
                    System.out.println("El valor " + targetValue + " se encuentra en el árbol.");
                } else {
                    System.out.println("El valor " + targetValue + " no se encuentra en el árbol.");
                }
            }
        
             else {
                System.out.println("Opción no válida. Seleccione una opción del menú o 'X' para salir.");
            }
        }
    }

    public static void postorderTraversal(TreeNode node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.value + " ");
        }
    }

    public static void preorderTraversal(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    public static void inorderTraversal(TreeNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.value + " ");
            inorderTraversal(node.right);
        }
    }
}
