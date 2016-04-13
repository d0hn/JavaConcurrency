import java.io.*;

public class banksim_main {
	/**
	 * <h1> Concurrent Programming Task </h1>
	 * <strong> AccessCard Scenario </strong>
	 * <p> Banking system developed in order to prevent
	 * a fraud by executing threads concurrently and prevent
	 * the issue of race conditioning </p>
	 * 
	 * <p> This project has 3 main classes, each of them does a different job.
	 * <ul>
	 * <li> <strong>banksim_main</strong> - Consists of 1 main method, which takes the arguments passed by user
	 * saves them as variables and passes them on. However, if 2 arguments are not 
	 * supplied, user will see an error message. Arguments from this method are passed onto banksim_account class.</li>
	 * <li> <strong>banksim_account</strong> - Consists of 7 methods. There are methods responsible for handling 
	 * deposits, withdrawals, storing and printing out results. </li>
	 * <li> <strong>banksim_cards</strong> - Lastly, banksim_cards is responsible for thread execution</li>
	 * </ul> 
	 * </p>
	 * 
	 * @author Maciej Koniarek
	 * @version 1.0
	 * @see banksim_account
	 * @see banksim_cards
	 */

	
	public static void main(String[] args) {
		/**
		 * <h2>Main method</h2>
		 * 
		 * <p>Firstly arguments are passed to the function and are saved as
		 * variables.</p>
		 * 
		 * <p>Parse arguments </p>
		 * @see args[0] - Type of Int, defines number of AccessCards
		 * @see arga[1] - Type of Int, initiates starting balance
		 * 
		 * 
		 */
		if (args.length == 2) {
		int accessCards = Integer.parseInt(args[0]);
		int initialBalance = Integer.parseInt(args[1]);
		banksim_account account = new banksim_account(initialBalance);
		account.executeCards(accessCards);
		System.out.println("Account Balance: " + account.Balance());
		account.printTransaction();
		System.out.println("\n\nCompleted");
		}
		else {
			System.err.println("Error parsing arguments.");
		}
		
	}	
}
