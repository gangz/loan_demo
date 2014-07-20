var java = require("java");
java.classpath.push('loan.jar');

var DataFolder =java.import('cn.agilean.demo.loan.LoanApplyDataFolder');
var Borrower = java.import('cn.agilean.demo.loan.Borrower');

var InterestsPolicy =  java.import('cn.agilean.demo.loan.repayment.InterestsPolicy') ;
var LoanAmountApproval = java.import('cn.agilean.demo.loan.repayment.LoanAmountApproval') ;
var LocalCreditService = java.import('cn.agilean.demo.loan.eligibility.LocalCreditService') ;
var LoanApprovalFacade = java.import('cn.agilean.demo.loan.LoanApprovalFacade') ;
var LoanApprovalResult = java.import('cn.agilean.demo.loan.LoanApprovalResult') ;

var CreditQueryResult = java.import('cn.agilean.demo.loan.eligibility.CreditQueryResult');
var PersonID = java.import('cn.agilean.demo.loan.PersonID');

function isValidID(s)
{
	var reg = new RegExp("^[0-9]{18}$");
	
	return reg.test(s);
	
}

function isValidCredit(s)
{
	return true;
}

function errorMessage(req, res, errorMsg)
{
   res.send(errorMsg);
}
function isdigit(s)
{
	var reg = new RegExp("^[0-9]+$");
	return reg.test(s);
	
}


function createDatafolder(totalPrice, buildDate,firstPayment , loanAppliedYears, primaryBorrower)
{

	var dataFolder = new DataFolder();
	dataFolder.setTotalPriceSync(Number(totalPrice));
	dataFolder.setHouseBuildDateSync(buildDate);
	dataFolder.setFirstPaymentSync(Number(firstPayment));
	dataFolder.setLoanAppliedYearsSync(Number(loanAppliedYears));

	dataFolder.setPrimaryBorrowerSync(primaryBorrower);
	return dataFolder;
}

function createPrimaryBorrower(id, gender,currentSuite,
								monthly_income,current_debts){
	var gender_id = 1;
	if (gender=='男') gender_id = 0;
	primaryBorrower = new Borrower(id,gender_id,
					Number(currentSuite)+1,
					Number(monthly_income),
					Number(current_debts),
					true);
	return primaryBorrower;
}

function createInterestsPolicy(base_ratio)
{
	return new InterestsPolicy(Number(base_ratio));
}

module.exports = function(app) {
	app.get('/', function(req, res) {
	  res.render('index', {
		title: '房贷审核演示程序',
		base_interests: '4.7',
		the_primary_borrower_name: '',
		the_primary_borrower_id: '310101200001010000',
		the_primary_borrower_gender: '男',
		the_primary_borrower_suites: '1',
		the_primary_borrower_credit: 'A',
		the_primary_borrower_monthly_income: '30000',
		the_primary_borrower_current_debts:'0',
		the_total_price: '1300000',
		the_first_payment: '300000',
		the_house_build_date: '2010-01-01',
		the_applied_years: '20',
		the_approve_result:'尚未审核',
		the_approved_amount:'',
		the_approved_interests:'',
		the_approved_years:'',
	   });
	});
	
    app.get('/rules', function(req, res) {
      res.render('rules', {
	    title: '房贷审核演示程序',
      });
    });;
	
  app.post('/', function(req, res) {
    var primary_borrower_name = req.body['primary_borrower_name'];
	var primary_borrower_id = req.body['primary_borrower_id'];
	var primary_borrower_gender  = req.body['primary_borrower_gender'];
	var primary_borrower_suites = req.body['primary_borrower_suites'];
	var primary_borrower_credit = req.body['primary_borrower_credit'];
	var primary_borrower_monthly_income = req.body['primary_borrower_monthly_income'];
	var primary_borrower_current_debts = req.body['primary_borrower_current_debts'];
	var total_price = req.body['total_price'];
	var first_payment = req.body['first_payment'];
	var house_build_date = req.body['house_build_date'];
	var base_interest = req.body['base_interest'];
	var applied_years = req.body['applied_years'];
	var errorMsg = "";
	
    if (primary_borrower_id == "") {
	  errorMsg += '请输入主贷人身份证号<br>';
    }else if(!isValidID(primary_borrower_id)) {
      errorMsg += '身份证号应该为18位数字<br>';
	}
	
	if (primary_borrower_suites ==""){
      errorMsg += '请输入主贷人当前房产数量<br>';
	}else if (!isdigit(primary_borrower_suites)){
      errorMsg += '当前房产数量必须为数字<br>';
	}
	

	if (primary_borrower_credit ==""){
      errorMsg += '请输入主贷人的信用评级<br>';
	}else if (!isValidCredit(primary_borrower_credit)){
      errorMsg += '信用评级必须为A、B、C或D<br>';
	  return;	
	}
	
	
	if (total_price ==""){
      errorMsg += '请输入房屋总价<br>';
	}else if (!isdigit(total_price)){
      errorMsg += '房屋总价必须为数字<br>';
	}
	
	if (first_payment ==""){
      errorMsg += '请输入首付金额<br>';
	}else if (!isdigit(first_payment)){
      errorMsg += '首付金额必须为数字<br>';
	}
	
	if (house_build_date ==""){
      errorMsg += '请输入房屋建造日期<br>';
	}
	
	if (errorMsg!="")
	{
		//errorMessage(req,res,"数据输入错误<hr>" +errorMsg);
		//return;
	}
	
	var primaryBorrower = createPrimaryBorrower(primary_borrower_id, primary_borrower_gender,primary_borrower_suites,
	                                            primary_borrower_monthly_income,primary_borrower_current_debts);
	var dataFolder = createDatafolder(total_price, house_build_date,first_payment , applied_years, primaryBorrower);
	var interest_policy = createInterestsPolicy(base_interest);
	
	var	creditService = new LocalCreditService();
	creditService.updateCreditRecordSync(primary_borrower_id,primary_borrower_credit);
		
	var loanApprovalFacade = new LoanApprovalFacade();
	var loanApproveResult = loanApprovalFacade.approveSync(dataFolder, interest_policy,creditService);

	var isElibibilityCheckPass = loanApproveResult.isElibibilityCheckPassSync();
	var approvedAmount = loanApproveResult.getApprovedAmountSync();
	var approvedInterests = loanApproveResult.getApprovedInterestsSync();
	var approvedYears = loanApproveResult.getApprovedYearsSync();
	
	res.render('index',{title:'审核结果',
						base_interests: base_interest,
						the_primary_borrower_name: primary_borrower_name,
						the_primary_borrower_id: primary_borrower_id,
						the_primary_borrower_gender: primary_borrower_gender,
						the_primary_borrower_suites: primary_borrower_suites,
						the_primary_borrower_credit: primary_borrower_credit,
						the_primary_borrower_monthly_income: primary_borrower_monthly_income,
						the_primary_borrower_current_debts:primary_borrower_current_debts,
						the_total_price: total_price,
						the_first_payment: first_payment,
						the_house_build_date: house_build_date,
						the_approve_result:isElibibilityCheckPass,
						the_applied_years: applied_years,
						the_approved_amount:approvedAmount,
						the_approved_interests:approvedInterests,
						the_approved_years:approvedYears}); 
    
  });
}

