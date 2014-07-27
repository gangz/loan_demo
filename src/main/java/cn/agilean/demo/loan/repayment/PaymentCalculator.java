package cn.agilean.demo.loan.repayment;

public class PaymentCalculator {

	public static double calcMonthPaymentFromCaptial(int capital, int months,double monthlyInterestRatio) {
		double monthlyPayment = capital *monthlyInterestRatio * (Math.pow((1+monthlyInterestRatio),months))/
				((Math.pow((1+ monthlyInterestRatio), months)-1));

		return monthlyPayment;
	}

	public static double calcCaptialFromMonthPayment(double monthlyPayment, int months, double monthlyInterestRatio) {
		double capital =  monthlyPayment * ((Math.pow((1+ monthlyInterestRatio), months)-1)) /
				(monthlyInterestRatio * (Math.pow((1+monthlyInterestRatio),months)));

		return capital;
	}

}
