import java.io.*;
import java.util.StringTokenizer;

// Java PS 연습

public class Main {
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // Variables
        int n = Integer.parseInt(br.readLine());
        int[][] adj = new int[n + 1][n + 1];    // 기본적으로 0으로 채워짐
    
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 1; j <= n; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 1; k <= n; k++) 
            for(int i = 1; i <= n; i++) 
                for(int j = 1; j <= n; j++) 
                    if(adj[i][k] == 1 && adj[k][j] == 1) adj[i][j] = 1;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(adj[i][j] == 1) sb.append(1).append(" ");
                else sb.append(0).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}