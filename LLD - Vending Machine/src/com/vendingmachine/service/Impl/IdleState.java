package com.vendingmachine.service.Impl;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.dto.Currency;
import com.vendingmachine.dto.Product;
import com.vendingmachine.service.IVendingMachineState;

public class IdleState implements IVendingMachineState {
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        if(!vendingMachine.getInventory().isProductAvailable(product)) {
            System.out.println("Product not available. Please select another product.");
        } else {
            vendingMachine.setSelectedProduct(product);
            vendingMachine.setCurrentState(vendingMachine.getReadyState());
            System.out.println("Product selected: " + product.getName());
        }
    }

    @Override
    public void insertMoney(Currency money) {
        System.out.println("Please select a product before inserting coins.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("No product selected. Please select a product first.");
    }

    @Override
    public void returnChange() {
        System.out.println("No transaction in progress. No change to return.");
    }
}
