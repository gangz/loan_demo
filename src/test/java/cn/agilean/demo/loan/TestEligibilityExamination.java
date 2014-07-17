package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEligibilityExamination {
	@Test
	public void LoanRequestMoreThanThirtyYearShouldBeRejected()
	{
		LoanRequest r = new LoanRequest();
		r.setYears(31);
		assertEquals(false,r.approval());
	}
	
	@Test
	public void LoanRequestLessOrEqualThirtyYearCouldBeAccepted()
	{
		LoanRequest r = new LoanRequest();
		r.setYears(30);
		assertEquals(true,r.approval());
	}
	

}
