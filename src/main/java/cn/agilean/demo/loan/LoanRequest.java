package cn.agilean.demo.loan;

public class LoanRequest {
	int years;
	public void setYears(int years) {
		this.years = years;
	}

	public boolean approval() {
		if (years>30) return false;
		return true;
	}

}
