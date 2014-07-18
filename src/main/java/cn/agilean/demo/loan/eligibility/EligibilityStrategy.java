package cn.agilean.demo.loan.eligibility;

import cn.agilean.demo.loan.LoanApplyDataFolder;

public interface EligibilityStrategy {
	boolean approve(LoanApplyDataFolder dataFolder);
}
