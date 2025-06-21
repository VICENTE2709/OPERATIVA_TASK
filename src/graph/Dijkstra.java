package graph;

import java.util.*;

public class Dijkstra {
    public static long[] shortestPath(int n, int source, List<int[]>[] edges) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[source] = 0;
        boolean[] used = new boolean[n];
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[]{source, 0});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            if (used[u]) continue;
            used[u] = true;
            for (int[] e : edges[u]) {
                int v = e[0];
                int w = e[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.add(new long[]{v, dist[v]});
                }
            }
        }
        return dist;
    }
}
