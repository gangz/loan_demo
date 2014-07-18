package cn.agilean.demo.loan.eligibilityStrategy;

import cn.agilean.demo.loan.Borrower;
import cn.agilean.demo.loan.IEligibilityStrategy;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.Relation;

public class CoBorrowerRelationStrategy implements IEligibilityStrategy {

	public boolean approve(LoanApplyDataFolder dataFolder) {
		for (Borrower coBorowser:dataFolder.getCoBorrowers()){
			if (coBorowser.getRelation()==Relation.OTHER)
				return false;
		}
		return true;
	}

}
