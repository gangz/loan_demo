package cn.agilean.demo.loan;

public class CreditStrategy implements IEligibilityStrategy {
	ICreditService creditService;
	public boolean approve(LoanApplyDataFolder dataFolder) {
		PersonID id = dataFolder.getPrimaryBorrowerID();
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
