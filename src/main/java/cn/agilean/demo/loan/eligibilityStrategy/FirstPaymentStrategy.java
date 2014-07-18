package cn.agilean.demo.loan.eligibilityStrategy;

import cn.agilean.demo.loan.IEligibilityStrategy;
import cn.agilean.demo.loan.LoanApplyDataFolder;

public class FirstPaymentStrategy implements IEligibilityStrategy {
	public boolean approve(LoanApplyDataFolder dataFolder) {
		if (getFirstPaymentPercent(dataFolder)<30) return false;
		return true;
	}

	private double getFirstPaymentPercent(LoanApplyDataFolder dataFolder) {
		return dataFolder.getFirstPayment()/dataFolder.getTotalPrice();
	}
}
