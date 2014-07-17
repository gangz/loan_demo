package cn.agilean.demo.loan;

public class LoanPeriodStrategy implements IEligibilityStrategy {

	
	public boolean approve(LoanApplyDataFolder dataFolder) {
		if (dataFolder.getLoanPeriod()>30) return false;
		return true;
	}
}
