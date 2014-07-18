package cn.agilean.demo.loan;

public class CoBorrower {
	PersonID coBorrowerID;
	Relation relation;
	boolean isHost;
	public CoBorrower(PersonID coBorrowerID, Relation relation, boolean isHost) {
		this.coBorrowerID = coBorrowerID;
		this.relation = relation;
		this.isHost = isHost;
	}
	
	public boolean isHost(){
		return isHost;
	}
	public Relation getRelation() {
		return relation;
	}
	
}
