package cn.agilean.demo.loan.eligibility;

import java.util.ArrayList;

import cn.agilean.demo.loan.LoanApplyDataFolder;

public class LoanEligibilityApproval {
	ArrayList<EligibilityStrategy> strategies;

	public LoanEligibilityApproval(){
		strategies = new ArrayList<EligibilityStrategy>();
	}
	
	public boolean approve(LoanApplyDataFolder dataFolder) {
		for(EligibilityStrategy strategy:strategies)
		{
			if (!strategy.approve(dataFolder)) return false;
		}
		return true;
	}

	public void addStrategy(EligibilityStrategy strategy) {
		strategies.add(strategy);
	}

}
