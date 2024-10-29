import java.util.*;
import java.io.*;
import java.lang.Math;

public class Main {
    public static long INF = Long.MAX_VALUE;
    public static int N, M, T;
    public static long[][] dist;
    public static int[][] path;

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
        path = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u, v, h;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            dist[u][v] = Math.min(dist[u][v], h);
            path[u][v] = v;
        }

        // Floyd-Warshall
        for(int pass = 1; pass <= N; pass++)
            for(int i = 1; i <= N; i++)
                for(int j = 1; j <= N; j++) {
                    if(dist[i][pass] + dist[pass][j] < dist[i][j]) {
                        dist[i][j] = dist[i][pass] + dist[pass][j];
                        path[i][j] = path[i][pass];
                    }
                }
        
        // path
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int s, e;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            // 갈 수 없는 경우 스킵
            if(dist[s][e] == INF || dist[s][e] == 0) { sb.append("-1\n"); continue; }

            while(s != e) {
                
            }
        }
    }
}