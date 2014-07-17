package cn.agilean.demo.loan;

public class FirstPaymentStrategy implements IEligibilityStrategy {
	public boolean approve(LoanApplyDataFolder dataFolder) {
		if (getFirstPaymentPercent(dataFolder)<30) return false;
		return true;
	}

	private double getFirstPaymentPercent(LoanApplyDataFolder dataFolder) {
		return dataFolder.getFirstPayment()/dataFolder.getTotalPrice();
	}
}
