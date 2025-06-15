package com.vendingmachine.service;

import com.vendingmachine.dto.Currency;
import com.vendingmachine.dto.Product;

public interface IVendingMachineState {
    void selectProduct(Product product);

    void insertMoney(Currency money);

    void dispenseProduct();

    void returnChange();
}
