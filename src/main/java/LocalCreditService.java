import java.util.HashMap;

import cn.agilean.demo.loan.PersonID;
import cn.agilean.demo.loan.eligibility.CreditQueryResult;
import cn.agilean.demo.loan.eligibility.CreditService;


public class LocalCreditService implements CreditService {
	HashMap<String, String> creditRepository;
	public LocalCreditService(){
		creditRepository = new HashMap<String, String>();
	}
	public void updateCreditRecord(String id, String level){
		creditRepository.remove(id);
		creditRepository.put(id, level);
	}
	public CreditQueryResult getCredit(PersonID id) {
		if (creditRepository.get(id.getID())==null)
			return  new CreditQueryResult(CreditQueryResult.LEVEL_D,CreditQueryResult.STATUS_NO_DATA);
		return new CreditQueryResult(creditRepository.get(id.getID()),CreditQueryResult.STATUS_NO_DATA);
	}

}
