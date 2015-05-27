var eligibility = require("../../rest-api/app/eligibility.js");
var interest = require('../../rest-api/app/interest.js');
var repayment = require('../../rest-api/app/repayment.js');
var period = require('../../rest-api/app/period.js');


function errorMessage(req, res, errorMsg)
{
   res.send(errorMsg);
}
function isdigit(s)
{
	var reg = new RegExp("^[0-9]+$");
	return reg.test(s);
	
}




function relationTranslate(relation){
	switch(relation)
	{
	case '夫妻':
		return Relation.WIFE;
	case '姐妹':
		return Relation.SISTER;
	case '兄弟':
		return Relation.BROTHER;
	case '父母':
		return Relation.FATHER;
	case '子女':
		return Relation.SON;
	}
		
	return Relation.OTHER;
} 


module.exports = function(app) {
	app.get('/', function(req, res) {
	  res.render('index', {
		title: '房贷审核演示程序',
		the_primary_borrower_age: '1',
		the_primary_borrower_gender:'male',
		the_primary_borrower_credit:'a',
		the_primary_borrower_suites: '1',
		the_primary_borrower_monthly_income: '3000',
		the_primary_borrower_current_debts: '100',
		the_house_age: '20',
		the_co_borrower_age: '',
		the_co_borrower_gender:'',
		the_co_borrower_monthly_income: '',
		the_co_borrower_current_debts: '',
		the_co_borrower_is_host: '',
		the_co_borrower_relation: '',
		
		the_check_result_credit: '',
		the_check_result_suites: '',
		the_co_borrower_check_result: '',
		the_approved_interests: '',
		the_approved_years: '',
		the_approved_amount: ''

	   });
	});
	
    app.get('/rules', function(req, res) {
    	
      res.render('rules', {
	    title: '房贷审核演示程序',
      });
    });;
	
	
  app.post('/', function(req, res) {
	  
	 var primary_borrower_age =req.body['primary_borrower_age'];
     var primary_borrower_gender =req.body['primary_borrower_gender'];
     var primary_borrower_credit =req.body['primary_borrower_credit'];
     var primary_borrower_suites =req.body['primary_borrower_suites'];
     var primary_borrower_monthly_income =req.body['primary_borrower_monthly_income'];
     var primary_borrower_current_debts =req.body['primary_borrower_current_debts'];
     var house_age =req.body['house_age'];
     var co_borrower_age =req.body['co_borrower_age'];
     var co_borrower_gender =req.body['co_borrower_gender'];
     var co_borrower_monthly_income =req.body['co_borrower_monthly_income'];
     var co_borrower_current_debts =req.body['co_borrower_current_debts'];
     var co_borrower_is_host =req.body['co_borrower_is_host'];
     var co_borrower_relation =req.body['co_borrower_relation'];

	                                                                        
	  
	  var co_borrower_check_result = '尚未审核';
	  if (co_borrower_is_host!='' && co_borrower_relation!='')
		  co_borrower_check_result = eligibility.checkCoBorrower(co_borrower_relation,co_borrower_is_host);
	  
	  var check_result_credit = '尚未审核';
	  if (primary_borrower_credit!='')
		  check_result_credit = eligibility.checkCredit(primary_borrower_credit);
	  
	  var check_result_suites = '尚未审核'; 
	  if (isdigit(primary_borrower_suites))
		  check_result_suites = eligibility.checkSuiteCount(primary_borrower_suites);	  
	  
	  var approved_interests = '尚未审核';
	  if (isdigit(primary_borrower_suites))
		  approved_interests = interest.getPolicy(primary_borrower_suites);
	  
	  var approved_years = '尚未审核';
	  if (isdigit(primary_borrower_age)&&isdigit(house_age)&&primary_borrower_gender!='')
		   approved_years = period.getPeriodLimit(primary_borrower_age,primary_borrower_gender,house_age);
	  
	  var approved_amount = '尚未审核';	
	  if (isdigit(primary_borrower_monthly_income) &&
	      isdigit(primary_borrower_current_debts))
	  {
		  if (isdigit(co_borrower_monthly_income) &&
			      isdigit(co_borrower_current_debts))
		  {
			  approved_amount = repayment.getMonthLimit(primary_borrower_monthly_income,primary_borrower_current_debts,co_borrower_monthly_income,co_borrower_current_debts); 
		  }
		  else
			  approved_amount = repayment.getMonthLimit(primary_borrower_monthly_income,primary_borrower_current_debts,0,0);
	  }
		  
	  
	  

	  
			  res.render('index', {
				title: '审核结果',
				
				the_primary_borrower_age: primary_borrower_age,
				the_primary_borrower_gender: primary_borrower_gender,
				the_primary_borrower_credit: primary_borrower_credit,
				the_primary_borrower_suites: primary_borrower_suites,
				the_primary_borrower_monthly_income: primary_borrower_monthly_income,
				the_primary_borrower_current_debts: primary_borrower_current_debts,
				the_house_age: house_age,
				the_co_borrower_age: co_borrower_age,
				the_co_borrower_gender: co_borrower_gender,
				the_co_borrower_monthly_income: co_borrower_monthly_income,
				the_co_borrower_current_debts: co_borrower_current_debts,
				the_co_borrower_is_host: co_borrower_is_host,
				the_co_borrower_relation: co_borrower_relation,

				

				the_check_result_credit: check_result_credit,
				the_check_result_suites: check_result_suites,
				the_co_borrower_check_result: co_borrower_check_result,
				the_approved_interests: approved_interests,
				the_approved_years: approved_years,
				the_approved_amount:approved_amount

			   });
  });
}

