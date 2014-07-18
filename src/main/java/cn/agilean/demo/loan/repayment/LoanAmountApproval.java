package cn.agilean.demo.loan.repayment;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.LoanApplyDataFolder;

public class LoanAmountApproval {
	InterestsPolicy interestsPolicy;
	PaymentCalculator calculator;
	LoanYearsApproval loanYearsApproval;
	public LoanAmountApproval(DateTimeService dateTimeService) {
		calculator = new PaymentCalculator();
		loanYearsApproval = new LoanYearsApproval();
		loanYearsApproval.setDateTimeService(dateTimeService);
	}
	
	public void setInterestPolicy(InterestsPolicy interestsPolicy) {
		this.interestsPolicy = interestsPolicy;
	}
	public double getAmount(LoanApplyDataFolder dataFolder) {
		double amount = dataFolder.getAppliedAmount();
		double totalIncome = dataFolder.getPrimaryBorrower().getMonthlyIncome();
		for(Borrower coBorrower:dataFolder.getCoBorrowers()){
			totalIncome +=coBorrower.getMonthlyIncome();
		}
		double canBeUsedInPayment = totalIncome/3;
		int repaymentMonths = loanYearsApproval.getLoanApprovedYears(dataFolder)*12;
		
		double repayableAmount = calculator.getCaptial(canBeUsedInPayment, repaymentMonths, interestsPolicy.getMonthlyInterestRatio(dataFolder.getPrimaryBorrower().getSuiteNum())); 
		if (repayableAmount<amount) amount = repayableAmount;
		return  amount;
	}

}
