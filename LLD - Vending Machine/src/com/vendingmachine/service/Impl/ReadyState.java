package com.vendingmachine.service.Impl;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.dto.Currency;
import com.vendingmachine.dto.Product;
import com.vendingmachine.service.IVendingMachineState;

import java.util.Arrays;
import java.util.List;

public class ReadyState implements IVendingMachineState {
    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected: " + product.getName() + ". Please insert coins or notes to proceed.");
    }

    @Override
    public void insertMoney(Currency money) {
        vendingMachine.addAmount(money.getValue());
        System.out.println("Coin inserted: " + money.getValue() + ". Total inserted: " + vendingMachine.getTotalAmount());

        // Check if enough amount is inserted to dispense the product
        Product selectedProduct = vendingMachine.getSelectedProduct();
        if (selectedProduct != null && vendingMachine.getTotalAmount() >= selectedProduct.getPrice()) {
            vendingMachine.setCurrentState(vendingMachine.getDispensingState());
            vendingMachine.dispenseProduct();
        } else {
            System.out.println("Insufficient amount. Please insert more coins or notes.");
            System.out.println("Current total amount: " + vendingMachine.getTotalAmount());
            System.out.println("Selected product price: " + (selectedProduct != null ? selectedProduct.getPrice() : "N/A"));
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please insert coins or notes to dispense the selected product.");
    }

    @Override
    public void returnChange() {
        double change = vendingMachine.getTotalAmount();
        if (change > 0) {
            System.out.println("Returning change: " + change);
            vendingMachine.setTotalAmount(0);
            vendingMachine.setCurrentState(vendingMachine.getIdleState());
        } else {
            System.out.println("No change to return.");
        }
    }
}
