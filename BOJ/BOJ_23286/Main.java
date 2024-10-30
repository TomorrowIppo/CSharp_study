import java.util.*;
import java.io.*;
import java.lang.Math;

public class Main {
    public static long INF = 987654321L;
    public static int N, M, T;
    public static long[][] dist;
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

                    // 기존 루트에서의 최대 허들과 새로운 루트에서 최대 허들 중 최솟값을 고르기.
                    dist[i][j] = Math.min(dist[i][j], Math.max(dist[i][pass], dist[pass][j]));
                }
        
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int s, e;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            if(dist[s][e] == INF) sb.append(-1).append("\n");
            else sb.append(dist[s][e]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}