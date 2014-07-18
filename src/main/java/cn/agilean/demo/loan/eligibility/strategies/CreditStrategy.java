package cn.agilean.demo.loan.eligibility.strategies;

import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.PersonID;
import cn.agilean.demo.loan.eligibility.CreditQueryResult;
import cn.agilean.demo.loan.eligibility.CreditService;
import cn.agilean.demo.loan.eligibility.EligibilityStrategy;

public class CreditStrategy implements EligibilityStrategy {
	CreditService creditService;
	public boolean approve(LoanApplyDataFolder dataFolder) {
		PersonID id = dataFolder.getPrimaryBorrower().getID();
		CreditQueryResult creditResult =getCredit(id); 
		if (creditResult.getLevel() ==CreditQueryResult.LEVEL_A ||
			creditResult.getLevel() ==CreditQueryResult.LEVEL_B  ||
			creditResult.getLevel() ==CreditQueryResult.LEVEL_C  ) return true;
		return false;
	}

	private CreditQueryResult getCredit(PersonID id) {
		return creditService.getCredit(id);
	}

	public void setCreditService(CreditService creditService) {
		this.creditService  = creditService;
	}
}
