package cn.agilean.demo.loan;

public class LoanApprovalResult {
	private boolean elibibilityCheckPass;
	private int approvedYears;
	private double approvedAmount;
	private double approvedInterests;
	
	public LoanApprovalResult(boolean elibibilityCheckPass,
	 int approvedYears,
	 double approvedAmount,
	 double approvedInterests){
		this.elibibilityCheckPass = elibibilityCheckPass;
		this.approvedAmount = approvedAmount;
		this.approvedYears = approvedYears;
		this.approvedInterests = approvedInterests;
	}
	public boolean isElibibilityCheckPass(){
		return elibibilityCheckPass;
	};
	public double getApprovedAmount(){
		return approvedAmount;
	}
	public int getApprovedYears(){
		return approvedYears;
	}
	public double getApprovedInterests(){
		return approvedInterests;
	}
	
}
