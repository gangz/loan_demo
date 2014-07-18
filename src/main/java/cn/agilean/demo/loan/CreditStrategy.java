package cn.agilean.demo.loan;

public class CreditStrategy implements IEligibilityStrategy {
	ICreditService creditService;
	public boolean approve(LoanApplyDataFolder dataFolder) {
		String id = dataFolder.getPrimaryBorrowerID();
		String creditLevel =getCreditStrategy(id); 
		if (creditLevel =="A" ||
			creditLevel=="B" ||
			creditLevel=="C" ) return true;
		return false;
	}

	private String getCreditStrategy(String id) {
		if (creditService==null) return "fail";
		return creditService.getCreditLevel(id);
	}

	public void setCreditService(ICreditService creditService) {
		this.creditService  = creditService;
	}
}
