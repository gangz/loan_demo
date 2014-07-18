package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestEligibilityExamination {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		loanDataFolder.setLoanPeriod(5)
					  .setSuitesNum(1);
	}
	@Test
	public void LoanPeriodMoreThanThirtyYearShouldBeRejected()
	{
		eligibilityApproval.addStrategy(new LoanPeriodStrategy());
		loanDataFolder.setLoanPeriod(31);
		assertEquals(false,eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void LoanPeriodLessOrEqualThirtyYearCouldBeAccepted()
	{
		eligibilityApproval.addStrategy(new LoanPeriodStrategy());
		loanDataFolder.setLoanPeriod(30);
		assertEquals(true,eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void ForbiddenThirdSuiteLoan()
	{

		eligibilityApproval.addStrategy(new ThirdSuiteForbiddenStrategy());
		loanDataFolder.setSuitesNum(3);
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void LessThanThirdSuiteLoanCouldBeAccepted()
	{
		eligibilityApproval.addStrategy(new ThirdSuiteForbiddenStrategy());
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
		loanDataFolder.setPrimaryBorrowerID("310101yyyymmdd1234");
		ICreditService creditService = Mockito.mock(ICreditService.class);
		
		CreditStrategy creditStrategy = new CreditStrategy();
		creditStrategy.setCreditService(creditService);
		eligibilityApproval.addStrategy(creditStrategy);
		

		Mockito.when(creditService.getCreditLevel(Mockito.anyString())).thenReturn("D");
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}

}
