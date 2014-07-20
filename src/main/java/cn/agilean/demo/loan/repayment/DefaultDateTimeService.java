package cn.agilean.demo.loan.repayment;

import org.joda.time.DateTime;

public class DefaultDateTimeService implements DateTimeService {

	public DateTime now() {
		return DateTime.now();
	}

}
