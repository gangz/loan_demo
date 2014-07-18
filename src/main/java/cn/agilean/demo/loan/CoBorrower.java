package cn.agilean.demo.loan;

public class CoBorrower {
	PersonID coBorrowerID;
	Relation relation;
	public CoBorrower(PersonID coBorrowerID, Relation relation) {
		this.coBorrowerID = coBorrowerID;
		this.relation = relation;
	}
	public Relation getRelation() {
		return relation;
	}
	
}
