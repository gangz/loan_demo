package cn.agilean.demo.loan;

import cn.agilean.demo.loan.eligibility.CreditService;
import cn.agilean.demo.loan.eligibility.LoanEligibilityApproval;
import cn.agilean.demo.loan.eligibility.strategies.CoBorrowerHostStrategy;
import cn.agilean.demo.loan.eligibility.strategies.CoBorrowerRelationStrategy;
import cn.agilean.demo.loan.eligibility.strategies.CreditStrategy;
import cn.agilean.demo.loan.eligibility.strategies.FirstPaymentStrategy;
import cn.agilean.demo.loan.eligibility.strategies.ThirdSuiteForbiddenStrategy;
import cn.agilean.demo.loan.repayment.InterestsPolicy;
import cn.agilean.demo.loan.repayment.LoanAmountApproval;
import cn.agilean.demo.loan.repayment.LoanYearsApproval;

public class LoanApprovalFacade {
	
	
	public LoanApprovalResult approve(LoanApplyDataFolder dataFolder,
			InterestsPolicy interestsPolicy,
			CreditService creditService) {
		LoanYearsApproval yearsApproval = new LoanYearsApproval();
		LoanAmountApproval amountApproval = new LoanAmountApproval();
		amountApproval.setInterestPolicy(interestsPolicy);
		LoanEligibilityApproval eligibilityApproval = new LoanEligibilityApproval();
		eligibilityApproval.addStrategy(new ThirdSuiteForbiddenStrategy());
		CreditStrategy creditStrategy = new CreditStrategy();
		creditStrategy.setCreditService(creditService);
		//eligibilityApproval.addStrategy(creditStrategy);
		
		eligibilityApproval.addStrategy(new FirstPaymentStrategy());
		eligibilityApproval.addStrategy(new CoBorrowerHostStrategy());
		eligibilityApproval.addStrategy(new CoBorrowerRelationStrategy());
		
		if (!eligibilityApproval.approve(dataFolder))
			return new LoanApprovalResult(false, 0, 0, 0);
		
			
		int approvedYears = yearsApproval.getLoanApprovedYears(dataFolder);
		double approvedAmount = amountApproval.getAmount(dataFolder);
		double approvedInterests = interestsPolicy.getBasePoint(dataFolder.getPrimaryBorrower().suitesNum);
		
		
		return new LoanApprovalResult(true, approvedYears, approvedAmount, approvedInterests);
	}

	
}
