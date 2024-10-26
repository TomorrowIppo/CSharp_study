import java.util.*;
import java.io.*;

class Student {
    private int math;
    private int sci;
    private int eng;

    public Student(int math, int sci, int eng) {
        this.math = math;
        this.sci = sci;
        this.eng = eng;
    }

    @Override
    public String toString() {
        int avg = (this.math + this.sci + this.eng) / 3;
        return "평균은 " + avg;
    }
}

public class Main {
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        System.out.println("수학, 과학, 영어 순으로 3개의 점수 입력 >> ");
        
        st = new StringTokenizer(br.readLine());
        int[] grade = new int[3];
        for(int i = 0; i < 3; i++) {
            grade[i] = Integer.parseInt(st.nextToken());
        }

        Student std = new Student(grade[0], grade[1], grade[2]);
        System.out.println(std);

        br.close();
    }
}
