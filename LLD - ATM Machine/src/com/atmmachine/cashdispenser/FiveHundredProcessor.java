package com.atmmachine.cashdispenser;

import java.math.BigDecimal;

public class FiveHundredProcessor extends CashWithdrawProcessor {

    private static final BigDecimal DENOMINATION = new BigDecimal(500);

    public FiveHundredProcessor(CashWithdrawProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public void dispense(BigDecimal amount) {
        BigDecimal[] result = amount.divideAndRemainder(DENOMINATION);

        int noteCount = result[0].intValue();
        BigDecimal remainingAmount = result[1];

        if (noteCount > 0) {
            System.out.println("Dispensing " + noteCount + " x ₹500");
        }

        if (remainingAmount.compareTo(BigDecimal.ZERO) > 0 && nextProcessor != null) {
            nextProcessor.dispense(remainingAmount);
        }

    }
}
