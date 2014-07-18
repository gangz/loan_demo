package cn.agilean.demo.loan.eligibilityStrategy;

import cn.agilean.demo.loan.IEligibilityStrategy;
import cn.agilean.demo.loan.LoanApplyDataFolder;

public class ThirdSuiteForbiddenStrategy implements IEligibilityStrategy {
	public boolean approve(LoanApplyDataFolder dataFolder) {
		if (dataFolder.getPrimaryBorrower().getSuiteNum()>=3) return false;
		return true;
	}
}
