package cn.agilean.demo.loan;

public class LoanApplyDataFolder {
	int years;
	public void setLoanPeriod(int years) {
		this.years = years;
	}

	public boolean approval() {
		if (years>30) return false;
		return true;
	}

}
