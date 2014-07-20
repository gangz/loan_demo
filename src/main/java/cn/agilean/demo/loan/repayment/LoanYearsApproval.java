package cn.agilean.demo.loan.repayment;

import org.joda.time.DateTime;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.Gender;
import cn.agilean.demo.loan.LoanApplyDataFolder;

public class LoanYearsApproval {
	
	private static final int MAX_LOAN_YEARS = 30;
	private static final int MAX_REPAYMENT_AGE_FEMALE = 60;
	private static final int MAX_REPAYMENT_AGE_MALE = 65;

	private DateTimeService dateTimeService;
	public void setDateTimeService(DateTimeService dateTimeService)
	{
		this.dateTimeService = dateTimeService;
	}
	
	public LoanYearsApproval()
	{
		this.dateTimeService = new DefaultDateTimeService();
	}
	
	public int getLoanApprovedYears(LoanApplyDataFolder dataFolder){
		int approveYears = maxLeftEarnableYears(dataFolder.getPrimaryBorrower());
		approveYears = (approveYears>MAX_LOAN_YEARS)?MAX_LOAN_YEARS:approveYears;
		approveYears = (approveYears>dataFolder.getAppliedYears())?dataFolder.getAppliedYears():approveYears;
		approveYears = (approveYears>maxLeftHouseValueableYears(dataFolder))?maxLeftHouseValueableYears(dataFolder):approveYears;
		return approveYears;
	}
	
	private int maxLeftHouseValueableYears(LoanApplyDataFolder dataFolder) {
		
		return 40-getHouseAge(dataFolder);
	}

	private int getHouseAge(LoanApplyDataFolder dataFolder) {
		
		DateTime now = dateTimeService.now();
		int nowYear = now.getYear();

		int birthYear = dataFolder.getHouseBuildDate().getYear();
	
		return nowYear-birthYear;
	}

	private int maxLeftEarnableYears(Borrower borrower) {
		int leftEarnableYears;
		if (borrower.getGender()==Gender.MALE)
			leftEarnableYears = MAX_REPAYMENT_AGE_MALE-getAge(borrower);
		else
			leftEarnableYears = MAX_REPAYMENT_AGE_FEMALE-getAge(borrower);
		if (leftEarnableYears <0)
			leftEarnableYears = 0;
		return leftEarnableYears;
	}

	private int getAge(Borrower borrower) {
			
		DateTime now = dateTimeService.now();
		int nowYear = now.getYear();

		DateTime birthDate =borrower.getBirthDate();
		int birthYear = birthDate.getYear();
		
		return nowYear-birthYear;
	}
}
