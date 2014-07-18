package cn.agilean.demo.loan;

public class CreditQueryResult {

	public static final String LEVEL_A = "A";
	public static final String LEVEL_B = "B";
	public static final String LEVEL_C = "C";
	public static final String LEVEL_D = "D";
	
	public static final String OK = "OK";
	public static final String STATUS_NO_DATA = "No Data";
	
	String level;
	String status;
	
	
	public String queryStatus(){
		return status;
	}
	
	public String getLevel() {
		return level;
	}
	
	public CreditQueryResult(String level, String status){
		this.level = level;
		this.status = status;
	}
}
