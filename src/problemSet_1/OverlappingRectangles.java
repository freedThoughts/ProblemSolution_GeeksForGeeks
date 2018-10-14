package problemSet_1;

import java.util.Scanner;

public class OverlappingRectangles {
    public static void main(String[] a) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int test = 0; test < testCases; test++){
            String[] s = in.nextLine().split(" ");
            int x1 = Integer.valueOf(s[0]);
            int y1 = Integer.valueOf(s[1]);
            int x2 = Integer.valueOf(s[2]);
            int y2 = Integer.valueOf(s[3]);

            s = in.nextLine().split(" ");
            int x3 = Integer.valueOf(s[0]);
            int y3 = Integer.valueOf(s[1]);
            int x4 = Integer.valueOf(s[2]);
            int y4 = Integer.valueOf(s[3]);

/*            if(((x1 <= x3 && x3 <= x2) || (x1 <= x4 && x4 <= x2)) &&
                    ((y2 <= y3 && y3 <= y1) || (y2 <= y4 && y4 <= y1)))
                answers[test] = 1;*/

            if(!(x2 > x3 || x4 > x1 || y2 > y3 || y4 > y1))
                answers[test] = 1;
        }
        for(int ans : answers)
            System.out.println(answers[ans]);
    }
}
