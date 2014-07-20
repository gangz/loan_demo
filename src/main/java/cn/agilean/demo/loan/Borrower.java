package cn.agilean.demo.loan;

import org.joda.time.DateTime;

public class Borrower {
	PersonID id;
	Gender gender;

	Relation relation;
	
	boolean isHost;
	int suitesNum;
	private double monthlyIncome;
	private double existingDebts;

	public Borrower(String id,int gender_id, int suitesNum,
			double monthlyIncome, double existingDebts,boolean isHost)
	{
		this.id = new PersonID(id);
		gender = Gender.FEMALE;
		if (gender_id==0)  gender = Gender.MALE;
		this.suitesNum = suitesNum;
		this.monthlyIncome = monthlyIncome;
		this.existingDebts = existingDebts;
		this.isHost = isHost;
	}

	
	private void setDefaultValue() {
		this.relation = Relation.SELF;
		this.isHost = true;
		this.gender = Gender.FEMALE;
		this.suitesNum = 1;
		this.monthlyIncome = 0;
	}
	

	public Borrower(PersonID id)
	{
		this.id = id;
		setDefaultValue();
	}
	
	public Borrower(PersonID id, Relation relation, boolean isHost) {
		this.id = id;
		this.relation = relation;
		this.isHost = isHost;
		this.gender = Gender.FEMALE;
		this.suitesNum = 1;
		this.monthlyIncome = 0;
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
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Gender getGender(){
		return this.gender;
	}

	public void setID(PersonID id) {
		this.id = id;
	}

	public DateTime getBirthDate() {
		String chinaID = id.getID();
		String birthDateString = chinaID.substring(6,10) + "-" +
								 chinaID.substring(10,12)+ "-" +
								 chinaID.substring(12,14);
		return DateTime.parse(birthDateString);
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public double getMonthlyIncome() {
		return this.monthlyIncome;
	}

	public void setExistingDebts(int existingDebts) {
		this.existingDebts = existingDebts;
	}

	public double getExistingDebts(){
		return this.existingDebts;
	}
}
