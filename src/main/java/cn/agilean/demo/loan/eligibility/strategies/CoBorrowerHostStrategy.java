package cn.agilean.demo.loan.eligibility.strategies;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.Relation;
import cn.agilean.demo.loan.eligibility.EligibilityStrategy;

public class CoBorrowerHostStrategy implements EligibilityStrategy {

	public boolean approve(LoanApplyDataFolder dataFolder) {
		for (Borrower coBorowser:dataFolder.getCoBorrowers()){
			if (coBorowser.getRelation() != Relation.WIFE &&
				coBorowser.getRelation() != Relation.HUSBAND)
			{
				if (!coBorowser.isHost())
					return false;
			}
		}
		return true;
	}

}
