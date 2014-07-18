package cn.agilean.demo.loan.repayment;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestInterestsStrategy {
	@Test
	public void FirstSuiteUseBaseInterests()
	{
		InterestsPolicy interestsPolicy = new InterestsPolicy(10);
		assertEquals(10.0, interestsPolicy.getBasePoint(1),0.0001);
	}

	@Test
	public void SecondSuiteUse110PercentInterest()
	{
		InterestsPolicy interestsPolicy = new InterestsPolicy(10);
		assertEquals(11.0, interestsPolicy.getBasePoint(2),0.0001);
	}
	
}
