package cn.agilean.demo.loan;

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
