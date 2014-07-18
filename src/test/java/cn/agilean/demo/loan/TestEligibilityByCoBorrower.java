package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestEligibilityByCoBorrower {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	ICreditService creditService;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		loanDataFolder.setPrimaryBorrowerID(new PersonID("310101yyyymmdd1234"));
		
		creditService = Mockito.mock(ICreditService.class);
		
		CoBorrowerRelationStrategy strategy = new CoBorrowerRelationStrategy();
		eligibilityApproval.addStrategy(strategy);
	}


	@Test
	public void NotDirectRelationCoBorrowerShouldBeRejected()
	{
		loanDataFolder.addCoBorrower(
				new CoBorrower(new PersonID("310101yyyymmdd0001"),Relation.OTHER)
				);
		
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void DirectRelationCoBorrowerShouldBeRejected()
	{
		loanDataFolder.addCoBorrower(
				new CoBorrower(new PersonID("310101yyyymmdd0001"),Relation.WIFE)
				);		
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}
	
	
}
