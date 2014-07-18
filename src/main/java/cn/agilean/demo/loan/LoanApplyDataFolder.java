package cn.agilean.demo.loan;

import java.util.ArrayList;

public class LoanApplyDataFolder {
	int years;
	int suitesNum;
	private double totalPrice;
	private double firstPayment;
	private PersonID primaryBorrowerID;
	private ArrayList<CoBorrower> coBorrowers;
	public LoanApplyDataFolder(){
		coBorrowers = new ArrayList<CoBorrower>();
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
	
	public LoanApplyDataFolder setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public LoanApplyDataFolder setFirstPayment(double firstPayment) {
		this.firstPayment = firstPayment;
		return this;
	}

	public double getFirstPayment() {
		return this.firstPayment;
	}
	
	public LoanApplyDataFolder setPrimaryBorrowerID(PersonID primaryBorrowerID){
		this.primaryBorrowerID =primaryBorrowerID;
		return this; 
	}
	public PersonID getPrimaryBorrowerID() {
		return primaryBorrowerID;
	}
	
	public void addCoBorrower(CoBorrower coBorrower) {
		this.coBorrowers.add(coBorrower);
	}
	public ArrayList<CoBorrower> getCoBorrowers() {
		return this.coBorrowers;
	}
}
