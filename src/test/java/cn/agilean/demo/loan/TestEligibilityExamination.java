package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEligibilityExamination {
	@Test
	public void LoanPeriodMoreThanThirtyYearShouldBeRejected()
	{
		LoanApplyDataFolder r = new LoanApplyDataFolder();
		r.setLoanPeriod(31);
		assertEquals(false,r.approval());
	}
	
	@Test
	public void LoanPeriodLessOrEqualThirtyYearCouldBeAccepted()
	{
		LoanApplyDataFolder r = new LoanApplyDataFolder();
		r.setLoanPeriod(30);
		assertEquals(true,r.approval());
	}
	
//	@Test
//	public void 
//	

}
