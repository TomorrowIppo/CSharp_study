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

        // Var N, M Init
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        long[][] dist = new long[N + 1][N + 1];
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                dist[i][j] = INF;

        int[][] nxt = new int[N + 1][N + 1];

        // input
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u, v, d;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            dist[u][v] = Math.min(dist[u][v], d);
            nxt[u][v] = v;
        }

        for(int i = 1; i <= N; i++) dist[i][i] = 0;

        // solve (Floyd-Warshall)
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        nxt[i][j] = nxt[i][k];
                    }
                }
            }
        }

        // output 1 (Dist Value)
        for(int i = 1; i<= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(dist[i][j] == INF) sb.append("0 ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // output 2 (Path)
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(dist[i][j] == INF || dist[i][j] == 0) sb.append("0");
                else {
                    ArrayList<Integer> path = new ArrayList<>();
                    int start = i;
                    
                    // i to j 경로 추가
                    while(start != j) {
                        path.add(start);
                        start = nxt[start][j];
                    }
                    path.add(j);

                    sb.append(path.size()).append(" ");
                    for(int k = 0; k < path.size(); k++) {
                        sb.append(path.get(k)).append(" ");
                    }
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}