import graph.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Ruta maxima (DAG)");
            System.out.println("2. Metodo de Dijkstra");
            System.out.println("3. Metodo de Floyd");
            System.out.println("4. Flujo maximo");
            System.out.println("5. Salir");
            System.out.print("Seleccione opcion: ");
            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case 1: longestPathMenu(); break;
                case 2: dijkstraMenu(); break;
                case 3: floydMenu(); break;
                case 4: maxFlowMenu(); break;
                case 5: return;
                default: System.out.println("Opcion invalida");
            }
            System.out.println();
        }
    }

    private static void longestPathMenu() {
        System.out.print("Numero de vertices: ");
        int n = Integer.parseInt(scanner.nextLine());
        List<int[]>[] edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        System.out.print("Numero de aristas: ");
        int m = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < m; i++) {
            System.out.print("u v peso: ");
            String[] parts = scanner.nextLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            edges[u].add(new int[]{v, w});
        }
        System.out.print("Vertice origen: ");
        int s = Integer.parseInt(scanner.nextLine());
        long[] dist = LongestPathDAG.longestPath(n, s, edges);
        System.out.println("Distancias maximas desde " + s + ":");
        for (int i = 0; i < n; i++) System.out.println(i + ": " + dist[i]);
    }

    private static void dijkstraMenu() {
        System.out.print("Numero de vertices: ");
        int n = Integer.parseInt(scanner.nextLine());
        List<int[]>[] edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        System.out.print("Numero de aristas: ");
        int m = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < m; i++) {
            System.out.print("u v peso: ");
            String[] parts = scanner.nextLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            edges[u].add(new int[]{v, w});
        }
        System.out.print("Vertice origen: ");
        int s = Integer.parseInt(scanner.nextLine());
        long[] dist = Dijkstra.shortestPath(n, s, edges);
        System.out.println("Distancias minimas desde " + s + ":");
        for (int i = 0; i < n; i++) System.out.println(i + ": " + dist[i]);
    }

    private static void floydMenu() {
        System.out.print("Numero de vertices: ");
        int n = Integer.parseInt(scanner.nextLine());
        long[][] dist = new long[n][n];
        long INF = Long.MAX_VALUE / 4;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        System.out.print("Numero de aristas: ");
        int m = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < m; i++) {
            System.out.print("u v peso: ");
            String[] parts = scanner.nextLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            dist[u][v] = Math.min(dist[u][v], w);
        }
        long[][] result = FloydWarshall.allPairsShortestPath(dist);
        System.out.println("Matriz de distancias minimas:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (result[i][j] >= INF/2) System.out.print("INF ");
                else System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void maxFlowMenu() {
        System.out.print("Numero de vertices: ");
        int n = Integer.parseInt(scanner.nextLine());
        int[][] capacity = new int[n][n];
        System.out.print("Numero de aristas: ");
        int m = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < m; i++) {
            System.out.print("u v capacidad: ");
            String[] parts = scanner.nextLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int c = Integer.parseInt(parts[2]);
            capacity[u][v] = c;
        }
        System.out.print("Fuente: ");
        int s = Integer.parseInt(scanner.nextLine());
        System.out.print("Sumidero: ");
        int t = Integer.parseInt(scanner.nextLine());
        int flow = MaxFlow.maxFlow(n, s, t, capacity);
        System.out.println("Flujo maximo: " + flow);
    }
}
