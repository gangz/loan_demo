package cn.agilean.demo.loan;

public class LoanApplyDataFolder {
	int years;
	int suitesNum;
	public void setLoanPeriod(int years) {
		this.years = years;
	}

	public boolean approval() {
		if (years>30) return false;
		if (suitesNum>=3) return false;
		return true;
	}

	public void setSuitesNum(int suitesNum) {
		this.suitesNum = suitesNum;
		
	}

	public void setTotalPrice(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setFirstPayment(int i) {
		// TODO Auto-generated method stub
		
	}

}
