package cn.agilean.demo.loan.eligibilityStrategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.PersonID;
import cn.agilean.demo.loan.eligibility.CreditQueryResult;
import cn.agilean.demo.loan.eligibility.CreditService;
import cn.agilean.demo.loan.eligibility.LoanEligibilityApproval;
import cn.agilean.demo.loan.eligibility.strategies.CreditStrategy;
import cn.agilean.demo.loan.eligibility.strategies.ThirdSuiteForbiddenStrategy;

public class TestEligibilityBySuiteNum {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	Borrower primaryBorrower;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		eligibilityApproval.addStrategy(new ThirdSuiteForbiddenStrategy());
		primaryBorrower = new Borrower(new PersonID("310101yyyymmdd1234"));
		loanDataFolder.setPrimaryBorrower(primaryBorrower);
	
	}

	@Test
	public void ForbiddenThirdSuiteLoan()
	{
		primaryBorrower.setSuitesNum(3);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void LessThanThirdSuiteLoanCouldBeAccepted()
	{
		primaryBorrower.setSuitesNum(2);
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}
	
}
