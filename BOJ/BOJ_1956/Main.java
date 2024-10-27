import java.util.*;
import java.io.*;
import java.lang.Math;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        long[][] dist = new long[V + 1][V + 1];
        for(int i = 1; i <= V; i++)
            for(int j = 1; j <= V; j++)
            {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a, b, c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dist[a][b] = c;
        }

        // solve (Floyd-Warshall)
        for(int k = 1; k <= V; k++) 
            for(int i = 1; i <= V; i++) 
                for(int j = 1; j <= V; j++) 
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        
        // output
        long res = -1;
        for(int start = 1; start <= V; start++)
            for(int pass = 1; pass <= V; pass++) {
                if(dist[start][pass] != 0 && dist[start][pass] != INF && dist[pass][start] != 0 && dist[pass][start] != INF) {
                    if(res == -1) res = (dist[start][pass] + dist[pass][start]);
                    else res = Math.min(res, dist[start][pass] + dist[pass][start]);
                }    
            }
        
        sb.append(res);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}