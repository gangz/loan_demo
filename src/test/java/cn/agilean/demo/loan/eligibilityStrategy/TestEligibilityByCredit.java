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

public class TestEligibilityByCredit {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	CreditService creditService;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		loanDataFolder.setPrimaryBorrower(new Borrower(new PersonID("310101yyyymmdd1234")));
		creditService = Mockito.mock(CreditService.class);
		
		CreditStrategy creditStrategy = new CreditStrategy();
		creditStrategy.setCreditService(creditService);
		eligibilityApproval.addStrategy(creditStrategy);
	}


	@Test
	public void CreditLevelDShouldBeRejected()
	{
		
		CreditQueryResult result = new CreditQueryResult(CreditQueryResult.LEVEL_D, CreditQueryResult.OK);
		Mockito.when(creditService.getCredit((PersonID)(Mockito.any()))).thenReturn(result);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void CreditLevelCCouldBeAccepted()
	{
		CreditQueryResult result = new CreditQueryResult(CreditQueryResult.LEVEL_C, CreditQueryResult.OK);
		Mockito.when(creditService.getCredit((PersonID)(Mockito.any()))).thenReturn(result);
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}

	@Test
	public void NoCreditDataShouldBeRejected()
	{
		CreditQueryResult result = new CreditQueryResult(null, CreditQueryResult.STATUS_NO_DATA);
		Mockito.when(creditService.getCredit((PersonID)(Mockito.any()))).thenReturn(result);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
}
