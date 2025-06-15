package com.vendingmachine.service.Impl;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.dto.Currency;
import com.vendingmachine.dto.Product;
import com.vendingmachine.service.IVendingMachineState;

public class DispensingState implements IVendingMachineState {

    private final VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product is already being dispensed.");
    }

    @Override
    public void insertMoney(Currency money) {
        System.out.println("Cannot insert money while dispensing product.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Dispensing product...");
        Product selectedProduct = vendingMachine.getSelectedProduct();
        if (selectedProduct == null) {
            System.out.println("No product selected to dispense.");
            vendingMachine.setCurrentState(vendingMachine.getIdleState());
            return;
        }
        if (!vendingMachine.getInventory().isProductAvailable(selectedProduct)) {
            System.out.println("Selected product is not available.");
            vendingMachine.returnChange();
            vendingMachine.setCurrentState(vendingMachine.getIdleState());
            return;
        }
        vendingMachine.getInventory().reduceQuantity(selectedProduct);
        System.out.println("Product dispensed: " + selectedProduct.getName());
        vendingMachine.setCurrentState(vendingMachine.getReturningChangeState());
        vendingMachine.returnChange();
    }

    @Override
    public void returnChange() {
        System.out.println("Cannot return change while dispensing product.");
    }
}
