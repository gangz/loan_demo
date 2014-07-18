package cn.agilean.demo.loan.repayment;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.Gender;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.PersonID;
import cn.agilean.demo.loan.eligibility.CreditQueryResult;
import cn.agilean.demo.loan.eligibility.CreditService;
import cn.agilean.demo.loan.eligibility.LoanEligibilityApproval;
import cn.agilean.demo.loan.eligibility.strategies.CreditStrategy;

public class TestMonthlyRepaymentAmount {
	

	@Test
	public void MonthlyRepaymentAmount()
	{
		PaymentCalculator calculator = new PaymentCalculator();
		
		InterestsPolicy interestsPolicy = new InterestsPolicy(4.7);
		double monthlyInterestRatio = interestsPolicy.getMonthlyInterestRatio(1);
		assertEquals(6434.961, calculator.getMonthPayment(1000000,240,monthlyInterestRatio),0.01);
	}

	
	
}
