package cn.agilean.demo.loan;

public class LoanYearsApproval {
	
	public int getLoanApprovedYears(LoanApplyDataFolder dataFolder){
		int approveYears = maxLeftEarnableYears(dataFolder.getPrimaryBorrower());
		approveYears = (approveYears>30)?30:approveYears;
		approveYears = (approveYears>dataFolder.getAppliedYears())?dataFolder.getAppliedYears():approveYears;
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
}
