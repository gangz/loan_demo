package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEligibilityExamination {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		loanDataFolder.setLoanPeriod(5)
					  .setSuitesNum(1);
	}
	@Test
	public void LoanPeriodMoreThanThirtyYearShouldBeRejected()
	{
		loanDataFolder.setLoanPeriod(31);
		assertEquals(false,eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void LoanPeriodLessOrEqualThirtyYearCouldBeAccepted()
	{
		loanDataFolder.setLoanPeriod(30);
		assertEquals(true,eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void ForbiddenThirdSuiteLoan()
	{
		loanDataFolder.setSuitesNum(3);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void LessThanThirdSuiteLoanCouldBeAccepted()
	{
		loanDataFolder.setSuitesNum(2);
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void FirstPaymentShouldReachThirtyPercentForFirstSuite()
	{
		loanDataFolder.setTotalPrice(100)
		              .setFirstPayment(29.999999);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}

}
