package graph;

import java.util.*;

public class MaxFlow {
    public static int maxFlow(int n, int s, int t, int[][] capacity) {
        int[][] flow = new int[n][n];
        int maxFlow = 0;
        while (true) {
            int[] parent = new int[n];
            Arrays.fill(parent, -1);
            parent[s] = s;
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(s);
            while (!q.isEmpty() && parent[t] == -1) {
                int u = q.poll();
                for (int v = 0; v < n; v++) {
                    if (parent[v] == -1 && capacity[u][v] - flow[u][v] > 0) {
                        parent[v] = u;
                        q.offer(v);
                    }
                }
            }
            if (parent[t] == -1) break;
            int aug = Integer.MAX_VALUE;
            int v = t;
            while (v != s) {
                int u = parent[v];
                aug = Math.min(aug, capacity[u][v] - flow[u][v]);
                v = u;
            }
            v = t;
            while (v != s) {
                int u = parent[v];
                flow[u][v] += aug;
                flow[v][u] -= aug;
                v = u;
            }
            maxFlow += aug;
        }
        return maxFlow;
    }
}
