package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestEligibilityByCoBorrowerShouldBeHost {
	LoanApplyDataFolder loanDataFolder;
	LoanEligibilityApproval eligibilityApproval;
	ICreditService creditService;
	@Before
	public void setUp(){
		loanDataFolder = new LoanApplyDataFolder();
		eligibilityApproval = new LoanEligibilityApproval();
		loanDataFolder.setPrimaryBorrowerID(new PersonID("310101yyyymmdd1234"));
		
		creditService = Mockito.mock(ICreditService.class);
		
		CoBorrowerHostStrategy strategy = new CoBorrowerHostStrategy();
		eligibilityApproval.addStrategy(strategy);
	}


	@Test
	public void NoHusbandWifeRelationAndNotHostShouldBeRejected()
	{
		loanDataFolder.addCoBorrower(
				new CoBorrower(new PersonID("310101yyyymmdd0001"),Relation.SISTER,false)
				);
		
		assertEquals(false, eligibilityApproval.approve(loanDataFolder));
	}
	
	@Test
	public void HusbandWifeRelationDoNotNeedHost()
	{
		loanDataFolder.addCoBorrower(
				new CoBorrower(new PersonID("310101yyyymmdd0001"),Relation.WIFE,false)
				);
		
		assertEquals(true, eligibilityApproval.approve(loanDataFolder));
	}
	
	
	
}
