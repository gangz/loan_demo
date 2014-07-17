package cn.agilean.demo.loan;

public class ThirdSuiteForbiddenStrategy implements IEligibilityStrategy {
	public boolean approve(LoanApplyDataFolder dataFolder) {
		if (dataFolder.getSuitesNum()>=3) return false;
		return true;
	}
}
