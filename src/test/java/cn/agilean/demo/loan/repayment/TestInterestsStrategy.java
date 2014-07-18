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
