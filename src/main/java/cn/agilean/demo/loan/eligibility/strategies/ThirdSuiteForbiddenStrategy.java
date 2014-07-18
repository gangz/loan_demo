package cn.agilean.demo.loan.eligibility.strategies;

import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.eligibility.EligibilityStrategy;

public class ThirdSuiteForbiddenStrategy implements EligibilityStrategy {
	public boolean approve(LoanApplyDataFolder dataFolder) {
		if (dataFolder.getPrimaryBorrower().getSuiteNum()>=3) return false;
		return true;
	}
}
