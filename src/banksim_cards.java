
public class banksim_cards implements Runnable{
/**
 * <h1> Bank simulator - Cards Class</h1>
 * <h2> banksim_cards class </h2>
 * 
 * <p> banksim_cards class is responsible for starting 
 * new threads. Class uses "implements Runnable" for thread execution. </p>
 * 
 * @see java.lang.Runnable#run()
 * 
 * @author Maciej Koniarek
 * @version 1.0
 */

	
	private banksim_account Account;
	private int accountBalance;
	/**
	 * <h2>Banksim Cards</h2>
	 * <p>Sets accountBalance to current accountBalance</p>
	 * @param accountBalance reference variable accountBalance
	 */
	public banksim_cards(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	/**
	 * 
	 * @param Account - Get account
	 */
	public banksim_cards(banksim_account Account) {
		this.Account = Account;
	}
	
	/**
	 * <h2>Run executes all thread</h2>
	 * 
	 * <p>Firstly, all variables are initialized, such as amount and threadId
	 * To keep the code neat, I decided to store (int)Math.random()*10) as a variable.
	 * <ul>
	 * <li>This method uses 200ms sleep and transaction limit of 20</li>
	 * <li>If there is an excepction in which thread cannot complete it's action
	 * e.g. is blocked, Error is thrown.</li>
	 * 
	 * <li>Lastly, Sysout prints out current thread number with balance that was either
	 * deposited or withdrawn from the account.</li>
	 * </ul>
	 * </p>
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		//accountBalance = 0;
		int amount;
		long threadId = Thread.currentThread().getId();
		amount = (int)(Math.random()*10);
		//banksim_account account = new banksim_account.deposit();
		//banksim_account deposit = new banksim_account();
		for (int i = 0; i < 20; i++) {
		
 			if (Math.random() > 0.5 ) {
				//banksim_account.withdraw((int)(Math.random() * 10));
				Account.withdraw(amount);
				accountBalance += amount;
			}
			else {
				//banksim_account.deposit((int)(Math.random()*10));
				Account.deposit(amount);
				accountBalance -= amount;
			}
			
			try {
				Thread.sleep(200);
			} catch(InterruptedException e) {
				System.err.println("Interrupted Exception! Thread terminated!");
			}
			System.out.println("Thread: " + threadId + " " + accountBalance);
			
		}
		// TODO Auto-generated method stub		
	}
}
