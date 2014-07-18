package cn.agilean.demo.loan;

import java.util.ArrayList;

public class LoanEligibilityApproval {
	ArrayList<IEligibilityStrategy> strategies;

	public LoanEligibilityApproval(){
		strategies = new ArrayList<IEligibilityStrategy>();
	}
	
	public boolean approve(LoanApplyDataFolder dataFolder) {
		for(IEligibilityStrategy strategy:strategies)
		{
			if (!strategy.approve(dataFolder)) return false;
		}
		return true;
	}

	public void addStrategy(IEligibilityStrategy strategy) {
		strategies.add(strategy);
	}

}
