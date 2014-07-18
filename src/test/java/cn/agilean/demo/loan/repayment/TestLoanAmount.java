package cn.agilean.demo.loan.repayment;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.PersonID;

public class TestLoanAmount {
	LoanApplyDataFolder dataFolder ;
	Borrower primaryBorrower;
	InterestsPolicy interestsPolicy;
	LoanAmountApproval approval;
	DateTimeService dateTimeService;
	@Before
	public void setUp(){
		dataFolder =new LoanApplyDataFolder();
		dataFolder.setTotalPrice (1300000);
		dataFolder.setHouseBuildDate(new DateTime("2010-01-01"));
		dataFolder.setFirstPayment(300000);
		dataFolder.setLoanAppliedYears(20);
		
		primaryBorrower = new Borrower(new PersonID("310101200001010000"));
		
		primaryBorrower.setSuitesNum(1);
		dataFolder.setPrimaryBorrower(primaryBorrower);

		interestsPolicy = new InterestsPolicy(4.7);
		dateTimeService = Mockito.mock(DateTimeService.class);
		approval = new LoanAmountApproval(dateTimeService);
		approval.setInterestPolicy(interestsPolicy);
		
	}
	@Test
	public void LoanAmountEqualsApplyWhenIncomeIsSufficent()
	{
		primaryBorrower.setMonthlyIncome(6435*3);
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("2010-01-01"));
		assertEquals(1000000, approval.getAmount(dataFolder),0.01);
	}

	

	@Test
	public void LoanAmountEqualsApplyWhenIncomeIsNotSufficent()
	{
		primaryBorrower.setMonthlyIncome(6000*3);
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("2010-01-01"));
		assertEquals(932406.58, approval.getAmount(dataFolder),0.01);
	}

		
}
