package cn.agilean.demo.loan.eligibilityStrategy;

import cn.agilean.demo.loan.CoBorrower;
import cn.agilean.demo.loan.IEligibilityStrategy;
import cn.agilean.demo.loan.LoanApplyDataFolder;
import cn.agilean.demo.loan.Relation;

public class CoBorrowerHostStrategy implements IEligibilityStrategy {

	public boolean approve(LoanApplyDataFolder dataFolder) {
		for (CoBorrower coBorowser:dataFolder.getCoBorrowers()){
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
