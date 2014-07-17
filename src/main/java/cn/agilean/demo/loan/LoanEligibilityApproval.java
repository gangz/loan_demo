package cn.agilean.demo.loan;

import java.util.ArrayList;

public class LoanEligibilityApproval {
	ArrayList<IEligibilityStrategy> strategies;
	public LoanEligibilityApproval(){
		strategies = new ArrayList<IEligibilityStrategy>();
		strategies.add(new LoanPeriodStrategy());
		strategies.add(new ThirdSuiteForbiddenStrategy());
		strategies.add(new FirstPaymentStrategy());
	}
	
	public boolean approve(LoanApplyDataFolder dataFolder) {
		for(IEligibilityStrategy strategy:strategies)
		{
			if (!strategy.approve(dataFolder)) return false;
		}
		return true;
	}

}
