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

public class TestLoanAmount {
	@Test
	public void LoanAmountEqualsApplyWhenIncomeIsSufficent()
	{
		LoanApplyDataFolder dataFolder =new LoanApplyDataFolder();
		dataFolder.setTotalPrice (1300000);
		dataFolder.setHouseBuildDate(new DateTime("2010-01-01"));
		dataFolder.setFirstPayment(300000);
		dataFolder.setLoanAppliedYears(20);
		
		Borrower primaryBorrower = new Borrower(new PersonID("310101200001010000"));
		primaryBorrower.setMonthlyIncome(6435*3);
		primaryBorrower.setSuitesNum(1);
		
		dataFolder.setPrimaryBorrower(primaryBorrower);
		
		InterestsPolicy interestsPolicy = new InterestsPolicy(4.7);
		
		DateTimeService dateTimeService = Mockito.mock(DateTimeService.class);
		LoanAmountApproval approval = new LoanAmountApproval(dateTimeService);
		approval.setInterestPolicy(interestsPolicy);
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("2010-01-01"));
		assertEquals(1000000, approval.getAmount(dataFolder),0.01);
	}

		
}
