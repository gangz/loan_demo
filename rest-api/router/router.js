var express    = require('express');
var bodyParser = require('body-parser');
var java = require("java");

var eligibility = require("../app/eligibility.js");
var interest = require('../app/interest.js');
var repayment = require('../app/repayment.js');
var period = require('../app/period.js');

module.exports = exports = function(app) { 
	

// create our router
var router = express.Router();

router.use(function(req, res, next) {
	console.log('RESTful HTTP request received.');
	next();
});

// test route to make sure everything is working (accessed at GET
// http://localhost:8080/api)
router.get('/', function(req, res) {
	res.json({ message: 'Welcome to RESTful API of loan demo' });
	
});

router.route('/check/credit/:level')
	.get(function(req,res){
		var result = eligibility.checkCredit(req.params.level);
		res.json({ 'eligibility status': result });	
	})
	
router.route('/check/suites/:suiteCount')
	.get(function(req,res){
		var result = eligibility.checkSuiteCount(req.params.suiteCount);
		res.json({ 'eligibility status': result });	
	})	
	
router.route('/check/co-loanee/:relation/:isHost')
	.get(function(req,res){
		var result = eligibility.checkCoBorrower(req.params.relation,req.params.isHost);
		res.json({ 'eligibility status': result });	
	})		
	
router.route('/interest-policy/:suiteCount')
	.get(function(req,res){
		var result = interest.getPolicy(req.params.suiteCount);
		res.json({ 'interest strategy': result });	
	})	
	
router.route('/max/month-repayment/:income/:exist_debts')
	.get(function(req,res){
		var result = repayment.getMonthLimit(req.params.income,req.params.exist_debts,0,0);
		res.json({ 'maximum monthly repayment': result });	
	})	
	
router.route('/max/month-repayment/:income/:exist_debts/:co_income/:co_exist_debts')
	.get(function(req,res){
		var result = repayment.getMonthLimit(req.params.income,req.params.exist_debts,
				req.params.co_income,req.params.co_exist_debts);
		res.json({ 'maximum monthly repayment': result });	
	})		
	
router.route('/max/period/:age/:gender/:houseAge')	
	.get(function(req,res){
		var result = period.getPeriodLimit(req.params.age,req.params.gender,
				req.params.houseAge);
		res.json({ 'maximum loan period': result });	
	})	
// REGISTER OUR ROUTES -------------------------------
app.use('/api', router);
}