package cn.agilean.demo.loan.repayment;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestMonthlyRepaymentAmount {
	

	@Test
	public void MonthlyRepaymentAmount()
	{
		InterestsPolicy interestsPolicy = new InterestsPolicy(4.7);
		double monthlyInterestRatio = interestsPolicy.getMonthlyInterestRatio(1);
		assertEquals(6434.961, PaymentCalculator.calcMonthPaymentFromCaptial(1000000,240,monthlyInterestRatio),0.01);
	}

	@Test
	public void CalculateCapitalFromMonthlyRepayment()
	{
		InterestsPolicy interestsPolicy = new InterestsPolicy(4.7);
		
		double monthlyInterestRatio = interestsPolicy.getMonthlyInterestRatio(1);
		assertEquals(1000000, PaymentCalculator.calcCaptialFromMonthPayment(6434.961,240,monthlyInterestRatio),0.01);
	}
	
}
