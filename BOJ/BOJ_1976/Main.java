import java.util.*;
import java.io.*;
import java.lang.Math;

public class Main {
    public static int N;
    public static int M;
    public static int[] parent;

    public static int find_root(int x) {
        if(x == parent[x]) return x;
        return parent[x] = find_root(parent[x]);
    }

    public static void merge(int x, int y) {
        x = find_root(x);
        y = find_root(y);

        if(x > y) parent[x] = y;
        else parent[y] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        // Init
        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int inp = Integer.parseInt(st.nextToken());

                if(inp == 1) merge(i, j);
            }
        }

        // Travel Plan
        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        boolean able = true;
        for(int i = 1; i < M; i++) {
            int cur = Integer.parseInt(st.nextToken());

            int p_root = find_root(prev);
            int c_root = find_root(cur);

            if(p_root == c_root) prev = cur;
            else {
                able = false;
                break;
            }
        }

        if(able) sb.append("YES\n");
        else sb.append("NO\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}