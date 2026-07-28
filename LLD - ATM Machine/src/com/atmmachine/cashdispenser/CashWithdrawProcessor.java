package com.atmmachine.cashdispenser;

import java.math.BigDecimal;

public abstract class CashWithdrawProcessor {

    protected final CashWithdrawProcessor nextProcessor;

    protected CashWithdrawProcessor(CashWithdrawProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract void dispense(BigDecimal amount);
}
