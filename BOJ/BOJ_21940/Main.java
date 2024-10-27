import java.util.*;
import java.io.*;
import java.lang.Math;

public class Main {
    private static final long INF = 987654321L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // Variables Init
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[][] dist = new long[n + 1][n + 1];
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }

        for(int i = 0; i < m; i++) {
            int u, v, d;
            
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            dist[u][v] = Math.min(dist[u][v], d);
        }

        int K = Integer.parseInt(br.readLine());
        ArrayList<Integer> homes = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int inp = Integer.parseInt(st.nextToken());
            homes.add(inp);
        }

        //solve (Floyd-Warshall)
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dist[i][k] == INF || dist[k][j] == INF) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 1 ~ n 을 passing point로 두고 비교
        int res = 0;
        long sum = INF;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int pass = 1; pass <= n; pass++) {
            long tmp = 0L;
            boolean is_able = true;
            for(Integer home : homes) {
                if(dist[home][pass] == INF || dist[home][pass] == INF) { is_able = false; continue; }

                tmp = Math.max(tmp, dist[home][pass] + dist[pass][home]);
            }
            if(!is_able) continue;

            //sb.append("pass : ").append(pass).append(", tmp : ").append(tmp).append("\n");
        
            if(tmp == sum) ans.add(pass);
            if(tmp < sum) {
                ans.clear();
                sum = tmp;
                ans.add(pass);
            }
        }

        for(Integer val : ans) sb.append(val).append(" ");
        
        // output
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}