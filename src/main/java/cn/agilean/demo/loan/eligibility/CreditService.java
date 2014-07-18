package cn.agilean.demo.loan.eligibility;

import cn.agilean.demo.loan.PersonID;

public interface CreditService {
	CreditQueryResult getCredit(PersonID id);
}
