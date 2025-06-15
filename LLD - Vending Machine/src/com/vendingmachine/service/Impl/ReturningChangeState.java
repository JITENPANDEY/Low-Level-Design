package com.vendingmachine.service.Impl;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.dto.Currency;
import com.vendingmachine.dto.Product;
import com.vendingmachine.service.IVendingMachineState;
import com.vendingmachine.utility.ChangeCalculator;

import java.util.List;

public class ReturningChangeState implements IVendingMachineState {

    private final VendingMachine vendingMachine;

    public ReturningChangeState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void selectProduct(Product product) {
        System.out.println("Please wait while we return your change. You can select a product after that.");
    }

    @Override
    public void insertMoney(Currency money) {
        System.out.println("Please wait while we return your change. You can insert money after that.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please wait while we return your change. You can dispense a product after that.");
    }

    @Override
    public void returnChange() {
        double change = vendingMachine.getTotalAmount() - vendingMachine.getSelectedProduct().getPrice();
        List<Currency> changeCurrencies = null;
        try {
            changeCurrencies = ChangeCalculator.getChange(change);
            System.out.println("Returning change: " + changeCurrencies);
        } catch (Exception e) {
            System.out.println("Error calculating change: " + e.getMessage());
            System.out.println("Returning your money: " + vendingMachine.getTotalAmount());
        } finally {
            vendingMachine.resetMachine();
        }
        System.out.println("---------------------Change returned. You can now select a new product-----------------");
    }
}
