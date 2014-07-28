package cn.agilean.demo.loan;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.agilean.demo.loan.eligibility.LocalCreditService;
import cn.agilean.demo.loan.repayment.InterestsPolicy;


public class TestLoanApprovalFacade {
	
	private static final String PRIMARY_BORROWER_ID = "310101200001010000";
	private static final int GENDER_MALE = 0;
	private static final int PRIMARY_BORROWER_SUITES_NUM = 1;
	private static final int PRIMARY_BORROWER_MONTHLY_INCOME = 30000;
	private static final int PRIMARY_BORROWER_EXISTING_DEBTS = 0;

	private static final double BASE_INTERESTS_POINTS = 4.7;

	private static final int TOTAL_PRICE = 1300000;
	private static final int FIRST_PAYMENT = 400000;
	private static final int APPLIED_LOAN_YEARS = 20;
	
	private static final String HOUSE_BUILD_DATE = "2010-01-01";

	@Test
	public void test() {

		LoanApplyDataFolder dataFolder = createApplyDataFolder();
		setPrimaryBorrower(dataFolder);
		
		InterestsPolicy interestsPolicy = createInterestsPolicy();
		LocalCreditService creditService = createCreditService();
		
		LoanApprovalFacade facade = new LoanApprovalFacade();
		
		LoanApprovalResult result = facade.approve(dataFolder, interestsPolicy,creditService);
		assertEquals(true, result.isElibibilityCheckPass());
		assertEquals(TOTAL_PRICE-FIRST_PAYMENT, result.getApprovedAmount(),0.01);
		assertEquals(BASE_INTERESTS_POINTS, result.getApprovedInterests(),0.001);
		assertEquals(APPLIED_LOAN_YEARS, result.getApprovedYears());
	}

	private LocalCreditService createCreditService() {
		LocalCreditService creditService = new LocalCreditService();
		creditService.updateCreditRecord(PRIMARY_BORROWER_ID, "A");
		return creditService;
	}

	private InterestsPolicy createInterestsPolicy() {
		InterestsPolicy interestsPolicy = new InterestsPolicy(BASE_INTERESTS_POINTS);
		return interestsPolicy;
	}

	private void setPrimaryBorrower(LoanApplyDataFolder dataFolder) {
		Borrower primaryBorrower = new Borrower(PRIMARY_BORROWER_ID,
				GENDER_MALE,
				PRIMARY_BORROWER_SUITES_NUM,
				PRIMARY_BORROWER_MONTHLY_INCOME,
				PRIMARY_BORROWER_EXISTING_DEBTS,
				true);
		dataFolder.setPrimaryBorrower(primaryBorrower);

	}

	private LoanApplyDataFolder createApplyDataFolder() {
		LoanApplyDataFolder dataFolder =new LoanApplyDataFolder();
		dataFolder.setTotalPrice (TOTAL_PRICE);
		dataFolder.setHouseBuildDate(HOUSE_BUILD_DATE);
		dataFolder.setFirstPayment(FIRST_PAYMENT);
		dataFolder.setLoanAppliedYears(APPLIED_LOAN_YEARS);
		return dataFolder;
	}

}
