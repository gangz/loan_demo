package cn.agilean.demo.loan;

public class LoanApplyDataFolder {
	int years;
	int suitesNum;
	
	public LoanApplyDataFolder(){
		
	}
	public LoanApplyDataFolder setLoanPeriod(int years) {
		this.years = years;
		return this;
	}

	public int getLoanPeriod(){
		return years;
	}
	
	public LoanApplyDataFolder setSuitesNum(int suitesNum) {
		this.suitesNum = suitesNum;
		return this;
	}

	public int getSuitesNum(){
		return suitesNum;
	}
	
	public void setTotalPrice(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setFirstPayment(int i) {
		// TODO Auto-generated method stub
		
	}

}
