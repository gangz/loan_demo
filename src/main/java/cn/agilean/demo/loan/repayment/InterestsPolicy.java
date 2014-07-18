package cn.agilean.demo.loan.repayment;

public class InterestsPolicy {
	double point;
	public InterestsPolicy(double point) {
		this.point = point;
	}
	public double getBasePoint(int suiteCount) {
		if (suiteCount==1)
			return point;
		else if(suiteCount==2)
			return point *110 /100;
		return 0.0;
	}
	public double getMonthlyInterestRatio(int suiteCount) {
		return getBasePoint(suiteCount)/12/100;
	}
}
