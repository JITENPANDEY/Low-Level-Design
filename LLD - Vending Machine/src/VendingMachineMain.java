import com.vendingmachine.VendingMachine;
import com.vendingmachine.dto.Coin;
import com.vendingmachine.dto.Note;
import com.vendingmachine.dto.Product;

public class VendingMachineMain {
    public static void main(String[] args) {

        VendingMachine vendingMachine = VendingMachine.getInstance();

        //add some products to the vending machine
        Product coke = vendingMachine.addProduct("Coke", 20.25, 20);
        Product pepsi = vendingMachine.addProduct("Pepsi", 40, 20);
        Product water = vendingMachine.addProduct("Water", 15, 20);
        Product chips = vendingMachine.addProduct("Chips", 5, 20);

        //simulate some operations
        vendingMachine.selectProduct(coke);

        //insert coins and notes
        vendingMachine.insertMoney(Coin.ONE);
        vendingMachine.insertMoney(Note.TEN);
        vendingMachine.insertMoney(Note.HUNDRED);
        //automatically dispense the product if enough amount is inserted and return change
    }
}