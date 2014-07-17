package cn.agilean.demo.loan;

public interface IEligibilityStrategy {
	boolean approve(LoanApplyDataFolder dataFolder);
}
