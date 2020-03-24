import java.util.*;

//        Scanner sc = new Scanner(System.in);
//
//        String[] nums;
//        nums = sc.nextLine().split(",");
//        for (int i = 0; i < nums.length; i++)
//            System.out.println(nums[i]);

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        double res = 0;
        if (n == 0)
            res = 0;
        else if (m == 0)
            res = 1;
        else {
            res += (double) n / (n + m);
            int i = n;
            int j = m;
            while (n + m > 3) {
                double a = (double) m / (n + m) * (m - 1) / (n + m - 1) * n / (n + m - 2) * (n - 1) / (n + m - 3);
                res += a;
                m -= 2;
                n--;
            }
            while (i + j > 3) {
                double b = (double) j / (i + j) * (j - 1) / (i + j - 1) * (j - 2) / (i + j - 2) * i / (i + j - 3);
                res += b;
                j -= 3;
            }
        }
        System.out.printf("%.4f", res);
    }
}

