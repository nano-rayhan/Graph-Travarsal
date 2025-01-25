import java.util.Scanner;

public class square {
    public static int pwr(int v,int n){
        if(n==0){
            return 1;
        }
        int x = pwr(v, n-1);
        return v * x;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value: ");
        int v = sc.nextInt();
        System.out.print("Enter the power: ");
        int n = sc.nextInt();
        System.out.println("The Squre : " + pwr(v,n));
    }
}
