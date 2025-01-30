import java.util.*;
import java.io.*;

public class Main {
    public static int N, M, K;
    public static int[] parent;
    public static int[] cost;
    public static boolean[] friendly;

    public static int find_root(int x) {
        if(x == parent[x]) return x;
        return parent[x] = find_root(parent[x]);
    }

    public static boolean is_union(int x, int y) {
        x = find_root(x);
        y = find_root(y);

        return x == y;
    }

    public static void merge(int x, int y) {
        x = find_root(x);
        y = find_root(y);

        if(cost[x] > cost[y]) parent[x] = y;
        else parent[y] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // Init var
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        cost = new int[N + 1];
        friendly = new boolean[N + 1];

        for(int i = 0; i <= N; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) cost[i] = Integer.parseInt(st.nextToken());

        // Relationship
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            merge(u, v);
        }

        // merge가 제대로 안 된 경우를 대비해 한 번 더 merge
        

        int total = 0;
        boolean able = true;
        for(int i = 1; i <= N; i++) find_root(i);   // root 갱신을 다시 해줘서 버그 방지 (중요)

        for(int i = 1; i <= N; i++) {
            if(friendly[parent[i]]) continue;   // 이미 친구면 건너뛰기
            
            total += cost[parent[i]];
            friendly[parent[i]] = true;

            if(K - total < 0) { // 친구비 계산했을 때, 소지한 비용을 넘어선다면 (친구를 살 수 없다면)
                able = false;
                break;
            }
        }
        if(able) sb.append(total);
        else sb.append("Oh no");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}