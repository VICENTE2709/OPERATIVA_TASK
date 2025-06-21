package graph;

import java.util.*;

public class LongestPathDAG {
    public static long[] longestPath(int n, int source, List<int[]>[] edges) {
        int[] indegree = new int[n];
        for (int u = 0; u < n; u++) {
            for (int[] e : edges[u]) {
                indegree[e[0]]++;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indegree[i] == 0) q.offer(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int[] e : edges[u]) {
                indegree[e[0]]--;
                if (indegree[e[0]] == 0) q.offer(e[0]);
            }
        }
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[source] = 0;
        for (int u : order) {
            if (dist[u] == Long.MIN_VALUE) continue;
            for (int[] e : edges[u]) {
                int v = e[0];
                int w = e[1];
                if (dist[v] < dist[u] + w) {
                    dist[v] = dist[u] + w;
                }
            }
        }
        return dist;
    }
}
