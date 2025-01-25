import java.util.Scanner;

public class modular {

    public static int mod(int divident, int divisor){
        if(divident < divisor){
            return divident;
        }
        return mod(divident - divisor, divisor);
    }
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter divident:");
        int divident = sc.nextInt();
        System.out.print("Enter divisor:");
        int divisor = sc.nextInt();

        System.out.println(mod(divident, divisor));
    }
}
