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
		double canBeUsedInPayment = totalIncome(dataFolder)/3-totalDebts(dataFolder);
		int repaymentMonths = loanYearsApproval.getLoanApprovedYears(dataFolder)*12;
		
		double repayableAmount = calculator.getCaptial(canBeUsedInPayment, repaymentMonths, interestsPolicy.getMonthlyInterestRatio(dataFolder.getPrimaryBorrower().getSuiteNum())); 
        return repayableAmount<dataFolder.getAppliedAmount()? repayableAmount:dataFolder.getAppliedAmount();
	}

	private double totalDebts(LoanApplyDataFolder dataFolder) {
		double totalExistingDebts = dataFolder.getPrimaryBorrower().getExistingDebts();
		for(Borrower coBorrower:dataFolder.getCoBorrowers()){
			totalExistingDebts+=coBorrower.getExistingDebts();
		}
		return totalExistingDebts;
	}

	private double totalIncome(LoanApplyDataFolder dataFolder) {
		double totalIncome = dataFolder.getPrimaryBorrower().getMonthlyIncome();
		for(Borrower coBorrower:dataFolder.getCoBorrowers()){
			totalIncome +=coBorrower.getMonthlyIncome();
		}
		return totalIncome;
	}

}
