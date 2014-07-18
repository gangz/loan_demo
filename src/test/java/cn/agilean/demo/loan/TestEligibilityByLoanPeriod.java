package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestEligibilityByLoanPeriod {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		eligibilityApproval.addStrategy(new LoanPeriodStrategy());
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
	
}
