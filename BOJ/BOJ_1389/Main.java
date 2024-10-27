import java.util.*;
import java.io.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // var N, M Init
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N + 1][N + 1];
        int[] friend_cnt = new int[N + 1];
        int INF = 1000000000;

        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                dist[i][j] = INF;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dist[x][y] = 1; dist[y][x] = 1;
        }   

        // solve (Floyd-Warshall)
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(dist[i][k] != INF && dist[k][j] != INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // counting friend_cnt
        int res = 0;
        for(int i = 1; i <= N; i++)
            res += dist[1][i];
        int idx = 1;

        for(int i = 1; i <= N; i++) {
            int sum = 0;
            for(int j = 1; j <= N; j++) {
                if(dist[i][j] == INF || i == j) continue;

                sum += dist[i][j];
            }
            if(res > sum) {
                idx = i;
                res = sum;
            }
            //sb.append(sum).append("\n");
        }
        sb.append(idx);

        // output Result
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}