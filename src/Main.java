import accountManage.Account;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account  account = new Account();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("=== ĐĂNG NHẬP HỆ THỐNG ===");
            System.out.print("Username: ");
            String user = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

        }

    }
}