package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transactions
{
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transactions(LocalDate date, LocalTime time, String description, String vendor, double amount)
    {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }


    //Getters and Setters


    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public LocalTime getTime()
    {
        return time;
    }

    public void setTime(LocalTime time)
    {
        this.time = time;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }


    // Method to format transaction as a CSV line
    public String toCSVString()
    {
        return String.format("%s | %s | %s | %s | %.2f",
                date, time, description, vendor, amount);
    }


    // Method to create Transaction object from CSV line
    public static Transactions fromCSVString(String csvLine)
    {
        String[] parts = csvLine.split(" \\| ");
        LocalDate date = LocalDate.parse(parts[0]);
        LocalTime time = LocalTime.parse(parts[1]);
        String description = parts[2];
        String vendor = parts[3];
        double amount = Double.parseDouble(parts[4]);
        return new Transactions(date, time, description, vendor, amount);
    }


    // Method to save transactions to the CSV file
    public static void saveTransactions(List<Transactions> transactions)
    {
        File file = new File("Files/transactions.csv");

        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter writer = new PrintWriter(fileWriter)) {
            for (Transactions transaction : transactions) {
                writer.println(transaction.toCSVString());
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println("Error writing transactions to file: " + ex.getMessage());

        }
    }

    // Method to load transactions from the CSV file
    public static List<Transactions> loadTransactions()
    {
        List<Transactions> transactions = new ArrayList<>();
        File file = new File("Files/transactions.csv");

        try (Scanner userInput = new Scanner(file)) {
            while (userInput.hasNextLine()) {
                String line = userInput.nextLine().trim();
                if (!line.isEmpty()) { // Skip empty lines
                    Transactions transaction = Transactions.fromCSVString(line);
                    transactions.add(transaction);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return transactions;
    }
}


