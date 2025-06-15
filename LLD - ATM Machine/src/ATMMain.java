import com.atmmachine.atm.ATM;
import com.atmmachine.model.Account;
import com.atmmachine.model.Card;
import com.atmmachine.service.IBankingService;
import com.atmmachine.service.ICashDispenser;
import com.atmmachine.service.impl.BankingServiceImpl;
import com.atmmachine.service.impl.CashDispenserImpl;

public class ATMMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the ATM Machine!");
        //account creation & Card creation
        Account acc = new Account("ACC123", 20000);
        Card card = new Card("324252345687", 1234, acc.getAccountNumber());

        // Initialize the banking service and cash dispenser
        IBankingService bankingService = new BankingServiceImpl();
        ICashDispenser cashDispenser = new CashDispenserImpl((int) acc.getBalance());

        ATM atm = new ATM(bankingService, cashDispenser);

        // Add account to the banking service
        bankingService.addAccount(acc);

        // Insert card and authenticate
        atm.insertCard(card, 1234);
        // Check balance
        atm.checkBalance();
        // Withdraw money
        atm.withdraw(5000);
        // Check balance after withdrawal
        atm.checkBalance();
        // Deposit money
        atm.deposit(2000);
        // Check balance after deposit
        atm.checkBalance();
        // Eject card
        atm.ejectCard();
    }
}