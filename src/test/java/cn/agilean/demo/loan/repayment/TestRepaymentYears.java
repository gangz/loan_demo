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

public class TestRepaymentYears {
	LoanApplyDataFolder loanDataFolder;
	Borrower primaryBorrower;
	DateTimeService dateTimeService;
	LoanYearsApproval loanYearsApproval;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		primaryBorrower = new Borrower(new PersonID("310101195001010000"));//1950-01-01
		loanDataFolder.setPrimaryBorrower(primaryBorrower);
		
		loanYearsApproval = new LoanYearsApproval();
		primaryBorrower.setGender(Gender.MALE);

		dateTimeService = Mockito.mock(DateTimeService.class);
		loanYearsApproval.setDateTimeService(dateTimeService);
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("1950-01-01"));

		loanDataFolder.setHouseBuildDate(new DateTime("2009-01-01"));
	}


	@Test
	public void ApplyMoreThanThirtyYearsShouldOnlyApproveThirtyYears()
	{
		loanDataFolder.setLoanAppliedYears(31);
		assertEquals(30, loanYearsApproval.getLoanApprovedYears(loanDataFolder));
	}

	@Test
	public void ApplyLessThanThirtyYearsShouldApproveActualYears()
	{
		loanDataFolder.setLoanAppliedYears(29);
		assertEquals(29, loanYearsApproval.getLoanApprovedYears(loanDataFolder));
	}
	
	@Test
	public void RepaymentYearsShouldNotExceed65ForMalePrimaryBorrower()
	{
		
		loanDataFolder.setLoanAppliedYears(29);
		
		//Set borrower to 64 years old
		primaryBorrower.setGender(Gender.MALE);
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("2014-01-01"));
		
		assertEquals(1, loanYearsApproval.getLoanApprovedYears(loanDataFolder));
	}
	
	@Test
	public void RepaymentYearsShouldNotExceed60ForFeMalePrimaryBorrower()
	{
		loanDataFolder.setLoanAppliedYears(29);

		//Set borrower to 64 years old
		primaryBorrower.setGender(Gender.FEMALE);
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("2009-01-01"));
		
		assertEquals(1, loanYearsApproval.getLoanApprovedYears(loanDataFolder));
	}
	

	@Test
	public void RepaymentYearsShouldNotExceed40YearForHouseAge()
	{
		loanDataFolder.setLoanAppliedYears(29);
		loanDataFolder.setHouseBuildDate(new DateTime("1970-01-01"));
		Mockito.when(dateTimeService.now()).thenReturn(new DateTime("2009-01-01"));
		
		assertEquals(1, loanYearsApproval.getLoanApprovedYears(loanDataFolder));
	}
}
