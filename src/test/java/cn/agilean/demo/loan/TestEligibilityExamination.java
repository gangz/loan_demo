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
//	//@Test
//	public void FirstPaymentShouldReachThirtyPercentForFirstSuite()
//	{
//		r.setSuitesNum(1);
//		r.setTotalPrice(100);
//		r.setFirstPayment(30);
//		assertEquals(true, r.approval());
//	}

}
