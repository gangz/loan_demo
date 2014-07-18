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
import cn.agilean.demo.loan.eligibility.strategies.CoBorrowerRelationStrategy;

public class TestEligibilityByCoBorrowerRelation {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	CreditService creditService;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		loanDataFolder.setPrimaryBorrower(new Borrower(new PersonID("310101yyyymmdd1234")));
		
		creditService = Mockito.mock(CreditService.class);
		
		CoBorrowerRelationStrategy strategy = new CoBorrowerRelationStrategy();
		eligibilityApproval.addStrategy(strategy);
	}


	@Test
	public void NotDirectRelationCoBorrowerShouldBeRejected()
	{
		loanDataFolder.addCoBorrower(
				new Borrower(new PersonID("310101yyyymmdd0001"),Relation.OTHER,true)
				);
		
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void DirectRelationCoBorrowerShouldBeRejected()
	{
		loanDataFolder.addCoBorrower(
				new Borrower(new PersonID("310101yyyymmdd0001"),Relation.WIFE,true)
				);		
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}
	
	
}
