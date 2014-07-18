package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestEligibilityByFirstPaymentPercent {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		eligibilityApproval.addStrategy(new FirstPaymentStrategy());
	}

	@Test
	public void FirstPaymentShouldReachThirtyPercentForFirstSuite()
	{
		loanDataFolder.setTotalPrice(100)
		              .setFirstPayment(29.999999);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
}
