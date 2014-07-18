package cn.agilean.demo.loan;

public class LoanYearsApproval {
	
	private static final int MAX_LOAN_YEARS = 30;
	private static final int MAX_REPAYMENT_AGE_FEMALE = 60;
	private static final int MAX_REPAYMENT_AGE_MALE = 65;

	public int getLoanApprovedYears(LoanApplyDataFolder dataFolder){
		int approveYears = maxLeftEarnableYears(dataFolder.getPrimaryBorrower());
		approveYears = (approveYears>MAX_LOAN_YEARS)?MAX_LOAN_YEARS:approveYears;
		approveYears = (approveYears>dataFolder.getAppliedYears())?dataFolder.getAppliedYears():approveYears;
		return approveYears;
	}
	
	private int maxLeftEarnableYears(Borrower borrower) {
		int leftEarnableYears;
		if (borrower.getGender()==Gender.MALE)
			leftEarnableYears = MAX_REPAYMENT_AGE_MALE-borrower.getAge();
		else
			leftEarnableYears = MAX_REPAYMENT_AGE_FEMALE-borrower.getAge();
		if (leftEarnableYears <0)
			leftEarnableYears = 0;
		return leftEarnableYears;
	}
}
