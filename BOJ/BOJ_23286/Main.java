import java.util.*;
import java.io.*;
import java.lang.Math;

public class Main {
    public static long INF = 987654321L;
    public static int N, M, T;
    public static long[][] dist;
    public static boolean[] visit;
    public static long MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // Input & Init.
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        dist = new long[N + 1][N + 1];
        visit = new boolean[N + 1];
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                dist[i][j] = INF;
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u, v, h;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            dist[u][v] = Math.min(dist[u][v], h);
        }

        // Floyd-Warshall
        for(int pass = 1; pass <= N; pass++)
            for(int i = 1; i <= N; i++)
                for(int j = 1; j <= N; j++) {
                    if(dist[i][pass] == INF || dist[pass][j] == INF) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][pass] + dist[pass][i]);
                }
                    
        // for(int i = 1; i <= N; i++) {
        //     for(int j = 1; j <= N; j++) {
        //         if(dist[i][j] == INF) sb.append("0").append(" ");
        //         else sb.append(dist[i][j]).append(" ");
        //     }
        //     sb.append("\n");
        // }


        // dfs
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            MAX = -1L;

            int s, e;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            visit[s] = true;
            dfs(s, e, -1L);
            visit[s] = false;

            // for(int j = 1; j <= N; j++)
            //     sb.append(visit[j]).append(" ");
            // sb.append("\n");
            sb.append(MAX).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void dfs(int cur, int goal, long max) {
        if(cur == goal) { 
            if(MAX != -1 && max != -1) MAX = Math.min(MAX, max);
            else MAX = max;

            return ;
        }

        for(int to = 1; to <= N; to++) {
            if(cur == to || visit[to] || dist[cur][to] == INF) continue;

            long nxt_max = Math.max(max, dist[cur][to]);
            visit[to] = true;
            dfs(to, goal, nxt_max);
            visit[to] = false;
        }
    }
}