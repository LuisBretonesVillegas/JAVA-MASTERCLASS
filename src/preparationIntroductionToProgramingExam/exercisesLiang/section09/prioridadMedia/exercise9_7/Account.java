package preparationIntroductionToProgramingExam.exercisesLiang.section09.prioridadMedia.exercise9_7;

import java.util.Date;

public class Account {
    public static void main(String[] args) {
        Account myAccount = new Account(1122, 20000, 4.5);
        myAccount.withdraw(2500);
        myAccount.deposit(3000);
        System.out.println(myAccount.getBalance() + " " + myAccount.getMonthlyInterest() + " " + myAccount.getDateCreated());
    }

    private int id;
    private double balance, annualInterestRate;
    private Date dateCreated = new Date();

    public Account() {
        id = 0;
        balance = 0;
        annualInterestRate = 0;
    }

    public Account(int id, double balance, double annualInterestRate) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    public double getMonthlyInterest() {
        return balance * (getMonthlyInterestRate() / 100);
    }

    public void withdraw(double withdraw) {
        balance -= withdraw;
    }

    public void deposit(double deposit) {
        balance += deposit;
    }
}
