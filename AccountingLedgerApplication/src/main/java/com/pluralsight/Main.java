package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        List<Transactions> transactions = new ArrayList<>();

        boolean running = true;
        while(running){
            System.out.println("Welcome, please choose one of the following: ");
            System.out.println("D.Add Deposit");
            System.out.println("P.Make Payment (Debit) ");
            System.out.println("L.Ledger");
            System.out.println("X.Exit");

            String choice = userInput.nextLine().toUpperCase();
            switch (choice){
                case "D":
                    addDeposit(userInput, transactions);
                    break;
                case "P":
                    makePayment(userInput,transactions);
                    break;
                case "L":
                    viewLedger(transactions);
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        Transactions.saveTransactions(transactions);
        userInput.close();

    }

    private static void addDeposit(Scanner userInput, List<Transactions> transactions)
    {
        System.out.println("Enter deposit amount: ");
        double amount = userInput.nextDouble();
        userInput.nextLine();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.println("Enter deposit description: ");
        String description = userInput.nextLine();

        System.out.println("Enter deposit vendor: ");
        String vendor = userInput.nextLine();

        Transactions deposit = new Transactions(date, time, description, vendor, amount);
        transactions.add(deposit);
        System.out.println("Deposit added successfully :) ");

    }

    private static void makePayment(Scanner userInput, List<Transactions> transactions)
    {
        System.out.println("Enter checking account number: ");
        double accountNumber = userInput.nextDouble();

        System.out.println("Enter payment amount: ");
        double amount = userInput.nextDouble();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transactions payment = new Transactions(date, time, accountNumber, amount );
        transactions.add(payment);
        System.out.println("Payment made successfully :) ");

    }

    private static void viewLedger(List<Transactions> transactions)
    {
        
    }
}