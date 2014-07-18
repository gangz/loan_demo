package cn.agilean.demo.loan;

import java.util.ArrayList;

import cn.agilean.demo.loan.repayment.DateTimeService;

public class LoanApplyDataFolder {
	int appliedYears;
	private double totalPrice;
	private double firstPayment;
	
	private Borrower primaryBorrower;
	private ArrayList<Borrower> coBorrowers;
	
	public LoanApplyDataFolder(){
		coBorrowers = new ArrayList<Borrower>();
	}
	public LoanApplyDataFolder setLoanAppliedYears(int appliedYears) {
		this.appliedYears = appliedYears;
		return this;
	}
	
	public int getLoanAppliedYears(){
		return appliedYears;
	}
	
	public int getLoanApprovedYears(){
		int approveYears = maxLeftEarnableYears(primaryBorrower);
		approveYears = (approveYears>30)?30:approveYears;
		approveYears = (approveYears>appliedYears)?appliedYears:approveYears;
		return approveYears;
	}
	
	private int maxLeftEarnableYears(Borrower borrower) {
		int leftEarnableYears;
		if (borrower.getGender()==Gender.MALE)
			leftEarnableYears = 65-borrower.getAge();
		else
			leftEarnableYears = 60-borrower.getAge();
		if (leftEarnableYears <0)
			leftEarnableYears = 0;
		return leftEarnableYears;
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

}
