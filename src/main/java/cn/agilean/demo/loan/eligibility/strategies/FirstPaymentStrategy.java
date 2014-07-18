package cn.agilean.demo.loan.eligibility.strategies;

import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.eligibility.EligibilityStrategy;

public class FirstPaymentStrategy implements EligibilityStrategy {
	public boolean approve(LoanApplyDataFolder dataFolder) {
		if (getFirstPaymentPercent(dataFolder)<30) return false;
		return true;
	}

	private double getFirstPaymentPercent(LoanApplyDataFolder dataFolder) {
		return dataFolder.getFirstPayment()/dataFolder.getTotalPrice();
	}
}
