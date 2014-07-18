package cn.agilean.demo.loan;

public class CoBorrowerRelationStrategy implements IEligibilityStrategy {

	public boolean approve(LoanApplyDataFolder dataFolder) {
		for (CoBorrower coBorowser:dataFolder.getCoBorrowers()){
			if (coBorowser.getRelation()==Relation.OTHER)
				return false;
		}
		return true;
	}

}
