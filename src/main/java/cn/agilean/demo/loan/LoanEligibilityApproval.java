package cn.agilean.demo.loan;

public class LoanEligibilityApproval {

	public boolean approve(LoanApplyDataFolder r) {
		if (r.getLoanPeriod()>30) return false;
		if (r.getSuitesNum()>=3) return false;
		return true;
	}

}
