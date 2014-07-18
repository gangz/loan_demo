package cn.agilean.demo.loan.repayment;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.CreditQueryResult;
import cn.agilean.demo.loan.Gender;
import cn.agilean.demo.loan.ICreditService;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.LoanEligibilityApproval;
import cn.agilean.demo.loan.PersonID;
import cn.agilean.demo.loan.eligibilityStrategy.CreditStrategy;

public class TestRepaymentYears {
	LoanApplyDataFolder loanDataFolder;
	Borrower primaryBorrower;
	DateTimeService dateTimeService;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		primaryBorrower = new Borrower(new PersonID("310101195001010000"));//1950-01-01
		loanDataFolder.setPrimaryBorrower(primaryBorrower);
		
		primaryBorrower.setGender(Gender.MALE);

		dateTimeService = Mockito.mock(DateTimeService.class);
		primaryBorrower.setDateTimeService(dateTimeService);
		
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("2015-01-01"));
	}


	@Test
	public void ApplyMoreThanThirtyYearsShouldOnlyApproveThirtyYears()
	{
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("1950-01-01"));
		loanDataFolder.setLoanAppliedYears(31);
		assertEquals(30, loanDataFolder.getLoanApprovedYears());
	}

	@Test
	public void ApplyLessThanThirtyYearsShouldApproveActualYears()
	{
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("1950-01-01"));
		loanDataFolder.setLoanAppliedYears(29);
		assertEquals(29, loanDataFolder.getLoanApprovedYears());
	}
	
	@Test
	public void RepaymentYearsShouldNotExceed65ForMalePrimaryBorrower()
	{
		
		loanDataFolder.setLoanAppliedYears(29);
		assertEquals(0, loanDataFolder.getLoanApprovedYears());
	}
	
}
