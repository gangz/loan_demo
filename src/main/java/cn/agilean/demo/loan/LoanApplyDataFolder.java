package cn.agilean.demo.loan;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class LoanApplyDataFolder {
	int appliedYears;
	private double firstPayment;
	
	private double totalPrice;
	private DateTime buildDate;

	
	private Borrower primaryBorrower;
	private ArrayList<Borrower> coBorrowers;
	
	public LoanApplyDataFolder(){
		coBorrowers = new ArrayList<Borrower>();
	}
	public LoanApplyDataFolder setLoanAppliedYears(int appliedYears) {
		this.appliedYears = appliedYears;
		return this;
	}
	
	public int getAppliedYears(){
		return appliedYears;
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
	
	public LoanApplyDataFolder setPrimaryBorrower(Borrower primaryBorrower){
		this.primaryBorrower =primaryBorrower;
		return this; 
	}
	public Borrower getPrimaryBorrower() {
		return primaryBorrower;
	}
	
	public void addCoBorrower(Borrower coBorrower) {
		this.coBorrowers.add(coBorrower);
	}
	public ArrayList<Borrower> getCoBorrowers() {
		return this.coBorrowers;
	}
	public void setHouseBuildDate(DateTime buildDate) {
		this.buildDate = buildDate;  
	}
	
	public void setHouseBuildDate(String buildDate) {
		this.buildDate = new DateTime(buildDate);  
	}
	
	public DateTime getHouseBuildDate() {
		return this.buildDate;
	}
	public double getAppliedAmount() {
		return totalPrice-firstPayment;
	}


}
