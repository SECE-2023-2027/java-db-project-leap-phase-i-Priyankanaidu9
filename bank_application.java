import java.util.*;
public class bank_application {
    public static int deposit(Scanner sc, int bal)
    {
        System.out.println("Enter amount to be deposited");
        int amt = sc.nextInt();
        bal += amt;
        System.out.println("Amount deposited successfully");
        return bal;
    }

    public static int withdraw(Scanner sc, int bal)
    {
        System.out.println("Enter amount to be withdrawn");
        int amt_w = sc.nextInt();
        if(amt_w < bal)
        {
        bal -= amt_w;
        System.out.println("Amount withdrawn successfully");
        }
        else{
            System.out.println("Insufficient Balance");
        }
        return bal;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int pin = 1995;
        int balance = 1000;
        System.out.println("Welcome to Banking application\nPlease enter your pin");
        int user_input = sc.nextInt();

        if(user_input == pin)
        {
            while (true)
            { 
            System.out.println("1. Deposit\n2. Withdraw\n3. Check Balance\n4.Exit");
            int ch = sc.nextInt();
            switch(ch)
            {
                case 1:
                balance = deposit(sc, balance);
                break;

                case 2:
                balance = withdraw(sc, balance);
                break;

                case 3:
                System.out.println("Balance : " +balance);
                break;

                case 4:
                System.out.println("Thank you! Exiting successfully...");
                System.exit(0);
                break;

                default:
                System.out.println("Invalid!");
                break;

            }
        }
    }
    else{
        System.out.println("Incorrect pin");
    }
    }

}
