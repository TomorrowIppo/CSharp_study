import java.lang.Math;
import java.io.*;
import java.util.*;

public class Main {
    public static int n, m, k;
    public static int INF = Integer.MAX_VALUE;
    public static int[][] adj;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // n, m 입력 및 전처리
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        adj = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
            {
                adj[i][j] = (i == j) ? 0 : INF;
            }

        // 길 입력
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u, v, b;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            adj[u][v] = 0;

            adj[v][u] = (b == 0) ? 1 : 0;   // 일방통행이면 다리를 설치해야하므로 비용 1 추가
        }

        // Floyd-Warshall
        for(int pass = 1; pass <= n; pass++) 
            for(int i = 1; i <= n; i++)
                for(int j = 1; j <= n; j++)
                {   
                    if(i == j || adj[i][pass] == INF || adj[pass][j] == INF) continue;

                    adj[i][j] = Math.min(adj[i][j], adj[i][pass] + adj[pass][j]);
                }

        
        // k개의 query 처리
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int s, e;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            sb.append(adj[s][e]).append("\n");
        }

        // output
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}