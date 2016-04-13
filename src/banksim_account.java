import java.util.ArrayList;
import java.util.Arrays;

public class banksim_account {
	/**
	 * <h1> Bank simulator - Account Class</h1>
	 * <h2> banksim_account class </h2>
	 * <p> banksim_account class is responsible for all queries
	 * in regards of transactions. Account class synchronises
	 * withdrawals and deposits. Also, results are stored in an ArrayList
	 * and then printed out right at the end where all threads
	 * completed their execution and only 1 thread is left running (main).
	 *  </p>
	 * @author Maciej Koniarek
	 * @version 1.0
	 * 
	 */
	
	//AccountBalance
	private int AccountBalance;
	//ArrayList TranDet used to store all transaction details
	public ArrayList<String> TranDet = new ArrayList<String> ();

	/**
	 * <h2>Banksim Account</h2>
	 * Uses balance passed from banksim_main and
	 * stores it as Account Balance
	 * @param balance - Set balance
	 */
	banksim_account(int balance) {		
		AccountBalance = balance;
		//System.out.println(balance);	
	}	
	/**
	 * <h2>Return Account Balance</h2>
	 * @return AccountBalance
	 */
	public int Balance() {
		return this.AccountBalance; 
	}
	/**
	 * <h2>Withdrawal</h2>
	 * 
	 * <p>Withdraws object receives an int amount variable
	 * Substracts the right amount operand from left AccountBalance
	 * operand.</p>
	 * 
	 * <p>Wait() tells calling thread to give up the monitor and go to
	 * Sleep of 200ms defined by constant SLEEP_DELAY, until other
	 * thread enters the same monitor and calls notify()</p>
	 * 
	 * <p>Passes current AccountBalance, type of transaction
	 * and amount which is being withdrawn from the account to the storeTransaction
	 * method</p>
	 * <p>wait() - Guarded lock - Suspend current thread</p>
	 * @see banksim_cards
	 * @see banksim_cards SLEEP_DELAY
	 * @see banksim_account storeTransaction
	 * @param amount - Variable that is passed from banksim_cards
	 * 
	 */
	public synchronized void withdraw(int amount) {
		if (amount < AccountBalance) {
			AccountBalance -= amount;
		}
		else {
			try {
			wait(); // Wait in the same class object, enter sleep, call notify
			}
			catch (InterruptedException e) {
				System.err.println("Interrupted Exception! Thread terminated!");
			}
		}
		//counter++;
		storeTransaction(AccountBalance, "withdraw", amount);
	}
	/**
	 * <h2>Deposits</h2>
	 * 
	 * <p>Deposits object receives amount int amount variable
	 * Add amount operand to AccountBalance operand.
	 * notify() wakes up the first thread that called wait</p>
	 * 
	 * <p>Passes current account balance, type of transaction and amount
	 * that is being deposited</p>
	 * <p>notify() - Guarded lock - Status changed wake up previous lock</p>
	 * @see banksim_cards
	 * @see banksim_account storeTransaction
	 * @param amount - Variable that is passed from banksim_cards
	 * 
	 */
	
	public synchronized void deposit(int amount) {
		AccountBalance += amount;
		notify(); // Notify in the same class object, wake up thread that called wait
		//counter++;
		//System.out.println(AccountBalance);
		storeTransaction(AccountBalance, "deposit", amount);

	}
	/**
	 * <h2>Store Transaction Details</h2>
	 * 
	 * <p>Receives all details of the transactions like
	 * Balance in INT format
	 * transactionType in String format
	 * amount in INT format</p>
	 * 
	 * <p>All variables are converted to string and stored in an ArrayList
	 * Before they are stored, transaction type checks whether they are
	 * withdrawals, if not they are passed as deposits.</p>
	 * <ul>
	 * <li>String format assigns spaces for each string to achieve nice formating instead
	 * of using "\t".</li>
	 * <li>% X s - Reserve X spaces for type of string - where X can be any number and "s" represents type of string.
	 * \n - after each string added, break the line for new results</li>
	 * <li>
	 * Lastly, add the data to an arraylist TranDet
	 * </li>
	 * </ul>
	 * @see TranDet - ArrayList used to store all details
	 * @param Balance - Account Balance
	 * @param transactionType - Type of transaction either deposit or withdraw
	 * @param amount - Amount being processed
	 * 
	 * 
	 */
	public synchronized void storeTransaction(int Balance, String transactionType, int amount) {
		long threadId = Thread.currentThread().getId();
		String BalanceST = Integer.valueOf(Balance).toString();
		String ThreadST = Long.valueOf(threadId).toString();
		String AmountST = Integer.valueOf(amount).toString();
		String AddData = null;
		//ThreadST +"\t" + AmountST +"\t \t" + BalanceST + "\n" ;
		//			AddData = ThreadST +"\t\t" + AmountST +"\t" + BalanceST + "\n";

		if (transactionType == "withdraw") {
			AddData = String.format("(%1s)%6s%35s \n", ThreadST, AmountST, BalanceST);
		}
		else {
			AddData = String.format("(%1s)%22s%19s \n", ThreadST, AmountST, BalanceST);
		}
		TranDet.add(AddData);
		//System.out.println(Balance);
	}
	/**
	 * <h2>Print Transaction</h2>
	 * 
	 * <p>Firstly it prints out column headers
	 * Then for each item in the TranDet ArrayList get the results from current position</p>
	 * <p>
	 * And print them out with transaction id + 1 (Purely for more esthetic look so it starts with 1 instead of 0)
	 * </p>
	 */
	public void printTransaction() {
		System.out.printf("Transaction \t Withdrawal \t Deposit \t Balance \n");

		for (int i = 0; i < TranDet.size(); i++) {
			int transactionid = i +1;
			
		System.out.printf(transactionid + "\t" + TranDet.get(i));
		}

	}
	/**
	 * <h2> Execute cards </h2>
	 * <p>Execute each thread as by checking the number of accessCards
	 * For each accessCard, execute new thread.</p>
	 * 
	 * <p>While there are still accessCards running wait with execution of other methods,
	 * once done and Main method is the only one that's active Print out the results
	 * </p>
	 * @see banksim_main
	 * @param accessCards - Amount of accesscards 
	 */
	public void executeCards(int accessCards) {
		for (int i = 0; i < accessCards; i++) {
            (new Thread(new banksim_cards(this))).start();
        }	       

		while(Thread.activeCount() != 1) {
		
		}
	}	
}
