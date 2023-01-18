package BankingConsole;
import java.io.PrintStream;
import java.util.*;
import java.io.FileOutputStream;


public class RBI {
	public static void main(String[] args) {
		System.out.println("*******************#Welcome to National Banking System#******************");
		System.out.println("\n");
		System.out.println("Do you want to open an account: 1. Yes 2. No");
		
		Scanner user = new Scanner(System.in);
		String choice = user.nextLine();
		
		if(choice.equalsIgnoreCase("Yes")) {
			OpenAccount obj = new OpenAccount();
			obj.createAccount();
		}
		if(choice.equalsIgnoreCase("No")) {
			BankAccount obj1= new BankAccount();
			obj1.showMenu();
		}
	}

}
class OpenAccount{
	String Name;
	int AccountNum;
	String AccountType;
	String dob;
	String Bank;
	void createAccount() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Which Bank You want to create your account : 1. SBI 2. PNB 3. ICIC ");
		int chooseBank = sc.nextInt();
		if(chooseBank == 1) {
			
			Bank = "SBI";
		}
		if (chooseBank == 2) {
			Bank = "PNB";
		}
		if(chooseBank == 3) {
			Bank = "ICIC";
		}
		System.out.println("Enter Your Name : ");
		sc.nextLine();
		Name = sc.nextLine();
		
		System.out.println(" Enter your date of birth :");
		
		dob = sc.nextLine();
		
		System.out.println("What type of account you want to open : 1. Saving 2.Current ");
		int choice = sc.nextInt();
		if(choice == 1) {
			AccountType = "Saving";
		}
		if(choice == 2) {
			AccountType = "Current";
		}
		System.out.println("Congratulations !You have successfully opened your account with following details");
		System.out.println("Bank :" + Bank);
		System.out.println("Name :" + Name);
		System.out.println("DOB :" +dob);
		System.out.println("Account type :" + AccountType);
		System.out.println("Account Number :" + Math.random());
		System.out.println("\n");
		BankAccount obj1 = new BankAccount();
		obj1.showMenu();
		sc.close();
	}
}
class BankAccount{
	int balance;
	int previousTransaction;
	String CustomerName;
	String CustomerId;
	String AccountType;
	double totalInterest;
	
	void calculateInterest(double balance) {
		System.out.println("What type of account you have : 1.Saving 2. Current.");
		Scanner sc= new Scanner(System.in);
		int choice = sc.nextInt();
		if(choice == 1) {
			AccountType = "Saving";
			int r = 5;
			int t;
			System.out.println("Enter the  time of interest ");
			t = sc.nextInt();
			double finalAmount = balance *(1+r *t);
			totalInterest = finalAmount - balance;
			System.out.println("Your total interest is : " + totalInterest);
		}
		if(choice == 2) {
			AccountType = "Current";
			int r = 8;
			int t,n;
			System.out.println("Enter the time to calculate interest :");
			t= sc.nextInt();
			System.out.println("Input frequency that interest has been compund in a year");
			n= sc.nextInt();
			double finalAmount = balance *(Math.pow((1 +r/n), n*t));
			totalInterest = finalAmount- balance;
			System.out.println("Total Interest earned is : " + totalInterest);
			sc.close();
		}
	}
	void deposit(int amount) {
		if(amount != 0) {
			balance = balance +amount;
			System.out.println("After deposit total balance is :" +balance);
			previousTransaction = amount;
		}
	}
	void withdraw(int amount) {
		if (amount!=0) {
			balance = balance - amount;
			System.out.println("Balance after withdrawn:" +balance);
			previousTransaction = --amount;
		}
	}
	void getPreviousTransaction() {
		FileOutputStream out;
		PrintStream p;
		try {
			out = new FileOutputStream("C://Users//Laxmi//OneDrive//Desktop//p_Transaction.txt");
			p = new PrintStream(out);
			if(previousTransaction>0) {
				p.append("Deposited: " +previousTransaction);
			}else if(previousTransaction<0) {
				p.append("Withdrawn: " +previousTransaction);
			}else {
				System.out.println("No transaction occured!!!");
			}
			p.close();
		}catch(Exception e) {
			System.out.println("Error in printing side!!");
		}
	}

void showMenu() {
	char option = '\0';
	Scanner sc = new Scanner(System.in);
	System.out.println("Welcome to the menu");
	System.out.println("\n");
	System.out.println("A. Check Balance");
	System.out.println("B. Deposit Amount");
	System.out.println("C. Withdraw Amount");
	System.out.println("D. See Previous Transaction");
	System.out.println("E. Calculate Interest");
	System.out.println("F. Exit");
	do {
		System.out.println("####################################");
		System.out.println("Choose an option");
		System.out.println("####################################");
		option = sc.next().charAt(0);
		System.out.println("\n");
		
		switch(option) {
		case 'A' :
			System.out.println("========================================");
			System.out.println("Balance =" +balance);
			System.out.println("\n");
			 break;
		case 'B' :
			System.out.println("========================================");
			System.out.println("Enter an amount to deposit:");
			int amount= sc.nextInt();
			deposit(amount);
			System.out.println("\n");
			 break;
		case 'C' :
			System.out.println("========================================");
			System.out.println("Enter an amount to withdraw:");
			int amount_1= sc.nextInt();
			withdraw(amount_1);
			System.out.println("\n");
			 break;
		case 'D' :
			System.out.println("========================================");
			getPreviousTransaction();
			System.out.println("\n");
			 break;
		case 'E' :
			System.out.println("========================================");
			calculateInterest(balance);
			System.out.println("\n");
			break;
		case 'F' :
			System.out.println("+++++++++++++++++++++++++++++++++++++++++");
			break;
			default :
				System.out.println("Entered Invalid option!!. Please enter Agin");
				break;
		}
	}while(option != 'F');
	System.out.println("!!!!!!!!!!!(Thank you for using our services)!!!!!!!!!!!!!");
	sc.close();

	}
}
