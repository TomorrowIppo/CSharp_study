import java.util.*;
import java.io.*;
import java.lang.Math;

/* 
 * n 범위가 작으니, Floyd-Warshall로 모든 정점으로부터의 최단거리를 구한 뒤,
 * dfs로 모든 정점 순회 최단거리를 구한다.
*/

public class Main {
    private static final long INF = 987654321L;
    public static long ans = INF;
    public static int n;
    public static boolean[] visit = new boolean[11];
    public static long[][] dist = new long[11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // var input
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) 
                dist[i][j] = Integer.parseInt(st.nextToken());
        }

        // solve (Floyd-Warshall)
        for(int pass = 0; pass < n; pass++) 
            for(int i = 0; i < n; i++) 
                for(int j = 0; j < n; j++) 
                    dist[i][j] = Math.min(dist[i][j], dist[i][pass] + dist[pass][j]);
        
        visit[k] = true;
        dfs(0, k, 0);
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int level, int cur, long sum) {
        if(level == n - 1) {
            ans = Math.min(ans, sum);
            return ;
        }

        for(int i = 0; i < n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                dfs(level + 1, i, sum + dist[cur][i]);
                visit[i] = false;
            }
        }
    }
}