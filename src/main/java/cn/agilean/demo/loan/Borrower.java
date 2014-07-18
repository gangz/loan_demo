package cn.agilean.demo.loan;

import org.joda.time.DateTime;

import cn.agilean.demo.loan.repayment.DateTimeService;


public class Borrower {
	PersonID id;
	Gender gender;

	Relation relation;
	
	boolean isHost;
	int suitesNum;

	DateTimeService dateTimeService;
	public void setDateTimeService(DateTimeService dateTimeService)
	{
		this.dateTimeService = dateTimeService;
	}
	
	public Borrower(PersonID id)
	{
		this.id = id;
		this.relation = Relation.SELF;
		this.isHost = true;
		this.gender = Gender.FEMALE;
	}
	
	public Borrower(PersonID id, Relation relation, boolean isHost) {
		this.id = id;
		this.relation = relation;
		this.isHost = isHost;
		this.gender = Gender.FEMALE;
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

	public int getAge() {
		
		DateTime now = dateTimeService.now();
		int nowYear = now.getYear();
		
		String chinaID = id.getID();
		String birthDateString = chinaID.substring(6,10) + "-" +
								 chinaID.substring(10,12)+ "-" +
								 chinaID.substring(12,14);
		DateTime birthDate = DateTime.parse(birthDateString);
		int birthYear = birthDate.getYear();
		
		return nowYear-birthYear;
	}

}
