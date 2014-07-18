package cn.agilean.demo.loan.eligibilityStrategy;

import cn.agilean.demo.loan.CreditQueryResult;
import cn.agilean.demo.loan.ICreditService;
import cn.agilean.demo.loan.IEligibilityStrategy;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.PersonID;

public class CreditStrategy implements IEligibilityStrategy {
	ICreditService creditService;
	public boolean approve(LoanApplyDataFolder dataFolder) {
		PersonID id = dataFolder.getPrimaryBorrower().getID();
		CreditQueryResult creditResult =getCredit(id); 
		if (creditResult.getLevel() ==creditResult.LEVEL_A ||
			creditResult.getLevel() ==creditResult.LEVEL_B  ||
			creditResult.getLevel() ==creditResult.LEVEL_C  ) return true;
		return false;
	}

	private CreditQueryResult getCredit(PersonID id) {
		return creditService.getCredit(id);
	}

	public void setCreditService(ICreditService creditService) {
		this.creditService  = creditService;
	}
}
