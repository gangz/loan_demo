package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cn.agilean.demo.loan.repayment.DateTimeService;
import cn.agilean.demo.loan.repayment.InterestsPolicy;
import cn.agilean.demo.loan.repayment.LoanAmountApproval;
import cn.agilean.demo.loan.repayment.LoanYearsApproval;

public class TestLoanApprovalFacade {
	LoanApplyDataFolder dataFolder ;
	InterestsPolicy interestsPolicy;
	LocalCreditService creditService;
	
	@Before
	public void setUp(){
		dataFolder =new LoanApplyDataFolder();
		dataFolder.setTotalPrice (1300000);
		dataFolder.setHouseBuildDate("2010-01-01");
		dataFolder.setFirstPayment(400000);
		dataFolder.setLoanAppliedYears(20);
		
		Borrower primaryBorrower = new Borrower("310101200001010000",
				0,
				1,
				30000,
				0,
				true);
		
		dataFolder.setPrimaryBorrower(primaryBorrower);
		interestsPolicy = new InterestsPolicy(4.7);
		creditService = new LocalCreditService();
		
		creditService.updateCreditRecord("310101200001010000", "A");
	}
	
	@Test
	public void test() {
		LoanApprovalFacade facade = new LoanApprovalFacade();
		
		LoanApprovalResult result = facade.approve(dataFolder, interestsPolicy,creditService);
		assertEquals(true, result.isElibibilityCheckPass());
		assertEquals(300000, result.getApprovedAmount(),0.01);
		assertEquals(4.7, result.getApprovedInterests(),0.001);
		assertEquals(20, result.getApprovedYears());
	}

}
