package cn.agilean.demo.loan.eligibilityStrategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.PersonID;
import cn.agilean.demo.loan.Relation;
import cn.agilean.demo.loan.eligibility.CreditService;
import cn.agilean.demo.loan.eligibility.LoanEligibilityApproval;
import cn.agilean.demo.loan.eligibility.strategies.CoBorrowerHostStrategy;

public class TestEligibilityByCoBorrowerShouldBeHost {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	CreditService creditService;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		loanDataFolder.setPrimaryBorrower(new Borrower(new PersonID("310101yyyymmdd1234")));
		
		creditService = Mockito.mock(CreditService.class);
		
		CoBorrowerHostStrategy strategy = new CoBorrowerHostStrategy();
		eligibilityApproval.addStrategy(strategy);
	}


	@Test
	public void NoHusbandWifeRelationAndNotHostShouldBeRejected()
	{
		loanDataFolder.addCoBorrower(
				new Borrower(new PersonID("310101yyyymmdd0001"),Relation.SISTER,false)
				);
		
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void HusbandWifeRelationDoNotNeedHost()
	{
		loanDataFolder.addCoBorrower(
				new Borrower(new PersonID("310101yyyymmdd0001"),Relation.WIFE,false)
				);
		
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}
	
	
	
}
