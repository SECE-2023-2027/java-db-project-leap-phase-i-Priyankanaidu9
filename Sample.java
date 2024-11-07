package com.org.bank;
import java.util.*;


public class program6 {
    public static void main(String args[])
    {
        System.out.println("1. Create");
        System.out.println("2.Listuser");
        Scanner in=new Scanner(System.in);
        int a=in.nextInt();
        if(a==1)
        {
            System.out.println("Enter Name: ");
            String name=in.next();
            System.out.println("Enter account number: ");
            String account=in.next();
            System.out.println("Enter amount: ");
            int amount=in.nextInt();

            HashMap<String,Object> values=new HashMap<>();
            values.put("name",name);
            values.put("account",account);
            values.put("amount",amount);

            ArrayList<Object> al=new ArrayList<>();

        }
    }
}