package cn.agilean.demo.loan.repayment;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestMonthlyRepaymentAmount {
	

	@Test
	public void MonthlyRepaymentAmount()
	{
		PaymentCalculator calculator = new PaymentCalculator();
		
		InterestsPolicy interestsPolicy = new InterestsPolicy(4.7);
		double monthlyInterestRatio = interestsPolicy.getMonthlyInterestRatio(1);
		assertEquals(6434.961, calculator.getMonthPayment(1000000,240,monthlyInterestRatio),0.01);
	}

	@Test
	public void CalculateCapitalFromMonthlyRepayment()
	{
		PaymentCalculator calculator = new PaymentCalculator();
		InterestsPolicy interestsPolicy = new InterestsPolicy(4.7);
		
		double monthlyInterestRatio = interestsPolicy.getMonthlyInterestRatio(1);
		assertEquals(1000000, calculator.getCaptial(6434.961,240,monthlyInterestRatio),0.01);
	}
	
}
