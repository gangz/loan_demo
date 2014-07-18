package cn.agilean.demo.loan;

public class Borrower {
	PersonID id;
	Relation relation;
	boolean isHost;
	int suitesNum;

	public Borrower(PersonID id)
	{
		this.id = id;
		this.relation = Relation.SELF;
		this.isHost = true;
	}
	public Borrower(PersonID id, Relation relation, boolean isHost) {
		this.id = id;
		this.relation = relation;
		this.isHost = isHost;
	}
	
	public boolean isHost(){
		return isHost;
	}
	public Relation getRelation() {
		return relation;
	}

	public int getSuiteNum(){
		return suitesNum;
	}
	public PersonID getID() {
		return id;
	}
	
	public void setSuitesNum(int suitesNum) {
		this.suitesNum = suitesNum;
	}

	public int getSuitesNum(){
		return suitesNum;
	}
}
