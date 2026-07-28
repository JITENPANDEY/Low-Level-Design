package com.atmmachine.cashdispenser;

import java.math.BigDecimal;

public class OneHundredProcessor extends CashWithdrawProcessor {

    private static final BigDecimal DENOMINATION = BigDecimal.valueOf(100);

    public OneHundredProcessor(CashWithdrawProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void dispense(BigDecimal amount) {
        BigDecimal[] result = amount.divideAndRemainder(DENOMINATION);

        int noteCount = result[0].intValue();
        BigDecimal remainingAmount = result[1];

        if (noteCount > 0) {
            System.out.println("Dispensing " + noteCount + " x ₹100");
        }

        if (remainingAmount.compareTo(BigDecimal.ZERO) > 0 && nextProcessor != null) {
            nextProcessor.dispense(remainingAmount);
        }
    }
}
