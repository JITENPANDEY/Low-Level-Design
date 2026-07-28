import com.atmmachine.model.ATM;
import com.atmmachine.model.Account;
import com.atmmachine.model.Card;
import com.atmmachine.model.TransactionRequestContext;
import com.atmmachine.model.TransactionType;
import com.atmmachine.service.AuthenticationService;
import com.atmmachine.cashdispenser.CashWithdrawProcessor;
import com.atmmachine.service.IBankingService;
import com.atmmachine.service.impl.AuthenticateServiceImpl;
import com.atmmachine.service.impl.BankingServiceImpl;
import com.atmmachine.cashdispenser.FiveHundredProcessor;
import com.atmmachine.cashdispenser.OneHundredProcessor;
import com.atmmachine.cashdispenser.TwoHundredProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ATMMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the ATM Machine!");
        //account creation & Card creation
        ATM atm = initializeATM();

        Account account =  createAccount();

        Card card = createCard(account);

        atm.insertCard(card);
        // Enter Pin
        boolean authenticated = atm.authenticatePin("1234");
        if(authenticated) {
            //Check Balance
            atm.selectTransaction(TransactionRequestContext.builder().atm(atm).transactionType(TransactionType.BALANCE_INQUIRY).build());
            // Withdraw Cash
            atm.selectTransaction(TransactionRequestContext.builder().atm(atm).transactionType(TransactionType.CASH_WITHDRAWAL).amount(new BigDecimal("1800")).build());
            // Deposit Cash
            atm.selectTransaction(TransactionRequestContext.builder().atm(atm).transactionType(TransactionType.DEPOSIT).amount(new BigDecimal("2100")).build());
            // Check Balance
            atm.selectTransaction(TransactionRequestContext.builder().atm(atm).transactionType(TransactionType.BALANCE_INQUIRY).build());
        } else {
            System.out.println("Authentication failed. Please check your PIN and try again.");
        }
        // Eject card
        atm.ejectCard();
    }

    private static ATM initializeATM() {
        IBankingService bankingService = new BankingServiceImpl();
        AuthenticationService authenticationService = new AuthenticateServiceImpl();
        CashWithdrawProcessor cashWithdrawProcessor = createCashProcessorChain();
        return new ATM(cashWithdrawProcessor, authenticationService, bankingService);
    }

    private static Account createAccount() {
        return new Account("ACC123", new BigDecimal("10000.00"));
    }

    private static Card createCard(Account account) {
        return new Card("324252345687", "1234", LocalDate.of(2027, 12, 31), account);
    }

    private static CashWithdrawProcessor createCashProcessorChain() {
        return new FiveHundredProcessor(new TwoHundredProcessor(new OneHundredProcessor(null)));
    }
}