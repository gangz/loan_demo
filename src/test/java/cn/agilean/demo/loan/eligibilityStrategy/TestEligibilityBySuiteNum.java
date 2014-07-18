package cn.agilean.demo.loan.eligibilityStrategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cn.agilean.demo.loan.CreditQueryResult;
import cn.agilean.demo.loan.ICreditService;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.LoanEligibilityApproval;
import cn.agilean.demo.loan.PersonID;
import cn.agilean.demo.loan.eligibilityStrategy.CreditStrategy;
import cn.agilean.demo.loan.eligibilityStrategy.ThirdSuiteForbiddenStrategy;

public class TestEligibilityBySuiteNum {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		eligibilityApproval.addStrategy(new ThirdSuiteForbiddenStrategy());
		
	}

	@Test
	public void ForbiddenThirdSuiteLoan()
	{
		loanDataFolder.setSuitesNum(3);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void LessThanThirdSuiteLoanCouldBeAccepted()
	{
		loanDataFolder.setSuitesNum(2);
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void FirstPaymentShouldReachThirtyPercentForFirstSuite()
	{
		eligibilityApproval.addStrategy(new FirstPaymentStrategy());
		loanDataFolder.setTotalPrice(100)
		              .setFirstPayment(29.999999);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void CreditLevelDShouldBeRejected()
	{
		loanDataFolder.setPrimaryBorrowerID(new PersonID("310101yyyymmdd1234"));
		ICreditService creditService = Mockito.mock(ICreditService.class);
		
		CreditStrategy creditStrategy = new CreditStrategy();
		creditStrategy.setCreditService(creditService);
		eligibilityApproval.addStrategy(creditStrategy);
		
		CreditQueryResult result = new CreditQueryResult(CreditQueryResult.LEVEL_D, CreditQueryResult.OK);
		Mockito.when(creditService.getCredit((PersonID)(Mockito.any()))).thenReturn(result);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void CreditLevelCCouldBeAccepted()
	{
		loanDataFolder.setPrimaryBorrowerID(new PersonID("310101yyyymmdd1234"));
		ICreditService creditService = Mockito.mock(ICreditService.class);
		
		CreditStrategy creditStrategy = new CreditStrategy();
		creditStrategy.setCreditService(creditService);
		eligibilityApproval.addStrategy(creditStrategy);
		
		CreditQueryResult result = new CreditQueryResult(CreditQueryResult.LEVEL_C, CreditQueryResult.OK);
		Mockito.when(creditService.getCredit((PersonID)(Mockito.any()))).thenReturn(result);
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}

	@Test
	public void NoCreditDataShouldBeRejected()
	{
		loanDataFolder.setPrimaryBorrowerID(new PersonID("310101yyyymmdd1234"));
		ICreditService creditService = Mockito.mock(ICreditService.class);
		
		CreditStrategy creditStrategy = new CreditStrategy();
		creditStrategy.setCreditService(creditService);
		eligibilityApproval.addStrategy(creditStrategy);
		
		CreditQueryResult result = new CreditQueryResult(null, CreditQueryResult.STATUS_NO_DATA);
		Mockito.when(creditService.getCredit((PersonID)(Mockito.any()))).thenReturn(result);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
}
