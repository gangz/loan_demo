var express    = require('express');
var bodyParser = require('body-parser');
var java = require("java");

var eligibility = require("./eligibility.js");
var interest = require('./interest.js');

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
	
// REGISTER OUR ROUTES -------------------------------
app.use('/api', router);
}