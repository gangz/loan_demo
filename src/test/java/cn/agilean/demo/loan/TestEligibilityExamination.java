package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEligibilityExamination {
	LoanApplyDataFolder r;
	@Before
	public void setUp(){
		r = new LoanApplyDataFolder();
	}
	@Test
	public void LoanPeriodMoreThanThirtyYearShouldBeRejected()
	{
		r.setLoanPeriod(31);
		assertEquals(false,r.approval());
	}
	
	@Test
	public void LoanPeriodLessOrEqualThirtyYearCouldBeAccepted()
	{
		r.setLoanPeriod(30);
		assertEquals(true,r.approval());
	}
	
	@Test
	public void ForbiddenThirdSuiteLoan()
	{
		r.setSuitesNum(3);
		assertEquals(false, r.approval());
	}
	//@Test
	public void FirstPaymentShouldReachThirtyPercentForFirstSuite()
	{
		r.setSuitesNum(1);
		r.setTotalPrice(100);
		r.setFirstPayment(30);
		assertEquals(true, r.approval());
	}

}
