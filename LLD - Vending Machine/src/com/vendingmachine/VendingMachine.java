package com.vendingmachine;

import com.vendingmachine.dto.Currency;
import com.vendingmachine.dto.Product;
import com.vendingmachine.inventory.Inventory;
import com.vendingmachine.service.IVendingMachineState;
import com.vendingmachine.service.Impl.DispensingState;
import com.vendingmachine.service.Impl.IdleState;
import com.vendingmachine.service.Impl.ReadyState;
import com.vendingmachine.service.Impl.ReturningChangeState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Data
public class VendingMachine {

    private static VendingMachine instance;

    private final Inventory inventory = new Inventory();

    private final IVendingMachineState idleState;
    private final IVendingMachineState readyState;
    private final IVendingMachineState dispensingState;
    private final IVendingMachineState returningChangeState;

    private IVendingMachineState currentState;
    private Product selectedProduct;
    private double totalAmount;

    private VendingMachine() {
        // Initialize states
        idleState = new IdleState(this);
        readyState = new ReadyState(this);
        dispensingState = new DispensingState(this);
        returningChangeState = new ReturningChangeState(this);

        // Set initial state
        currentState = idleState;
    }

    public static VendingMachine getInstance() {
        if (instance == null) {
            synchronized (VendingMachine.class) {
                if (instance == null) {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }

    public void addAmount(double amount) {
        this.totalAmount += amount;
    }

    public Product addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price);
        if (currentState instanceof IdleState) {
            inventory.addProduct(product, quantity);
        } else {
            throw new IllegalStateException("Cannot add product in the current state: " + currentState.getClass().getSimpleName());
        }
        return product;
    }

    public void insertMoney(Currency money) {
        List<Double> accepted = Arrays.asList(1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0);
        if (!accepted.contains(money.getValue())) {
            System.out.println("Invalid denomination: " + money.getValue());
            throw new IllegalArgumentException("Invalid denomination: " + money.getValue());
        }
        currentState.insertMoney(money);
    }

    public void selectProduct(Product product) {
        currentState.selectProduct(product);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    public void resetMachine() {
        this.currentState = idleState;
        this.selectedProduct = null;
        this.totalAmount = 0;
    }
}
