var java = require("java");
java.classpath.push('../target/loandemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar');

var InterestsPolicy =  java.import('cn.agilean.demo.loan.repayment.InterestsPolicy') ;

var Borrower = java.import('cn.agilean.demo.loan.Borrower');
var DataFolder =java.import('cn.agilean.demo.loan.LoanApplyDataFolder');
var LocalCreditService = java.import('cn.agilean.demo.loan.eligibility.LocalCreditService') ;
var LoanApprovalFacade = java.import('cn.agilean.demo.loan.LoanApprovalFacade') ;
var LoanApprovalResult = java.import('cn.agilean.demo.loan.LoanApprovalResult') ;
var Relation  = java.import('cn.agilean.demo.loan.Relation') ;

module.exports = function() {
	// create our router
	var router = express.Router();

	// middleware to use for all requests
	router.use(function(req, res, next) {
		// do logging
		console.log('Received Request.');
		next();
	});

	// test route to make sure everything is working (accessed at GET http://localhost:8080/api)
	router.get('/', function(req, res) {
		res.json({ message: 'Hello! Welcome to loan demo RESTful API' });	
	});

	// on routes that end in /bears
	// ----------------------------------------------------
	router.route('/bears')

		// create a bear (accessed at POST http://localhost:8080/bears)
		.post(function(req, res) {
			
			var bear = new Bear();		// create a new instance of the Bear model
			bear.name = req.body.name;  // set the bears name (comes from the request)

			bear.save(function(err) {
				if (err)
					res.send(err);

				res.json({ message: 'Bear created!' });
			});

			
		})

		// get all the bears (accessed at GET http://localhost:8080/api/bears)
		.get(function(req, res) {
			Bear.find(function(err, bears) {
				if (err)
					res.send(err);

				res.json(bears);
			});
		});

	// on routes that end in /bears/:bear_id
	// ----------------------------------------------------
	router.route('/bears/:bear_id')

		// get the bear with that id
		.get(function(req, res) {
			Bear.findById(req.params.bear_id, function(err, bear) {
				if (err)
					res.send(err);
				res.json(bear);
			});
		})

		// update the bear with this id
		.put(function(req, res) {
			Bear.findById(req.params.bear_id, function(err, bear) {

				if (err)
					res.send(err);

				bear.name = req.body.name;
				bear.save(function(err) {
					if (err)
						res.send(err);

					res.json({ message: 'Bear updated!' });
				});

			});
		})

		// delete the bear with this id
		.delete(function(req, res) {
			Bear.remove({
				_id: req.params.bear_id
			}, function(err, bear) {
				if (err)
					res.send(err);

				res.json({ message: 'Successfully deleted' });
			});
		});


	// Gangz: My code

	//on routes that end in /bears
	//----------------------------------------------------
	router.route('/check')

		// create a bear (accessed at POST http://localhost:8080/bears)
		.post(function(req, res) {
			
			var bear = new Bear();		// create a new instance of the Bear model
			bear.name = req.body.name;  // set the bears name (comes from the request)

			bear.save(function(err) {
				if (err)
					res.send(err);

				res.json({ message: 'Bear created!' });
			});

			
		})

		// get all the bears (accessed at GET http://localhost:8080/api/bears)
		.get(function(req, res) {
			Bear.find(function(err, bears) {
				if (err)
					res.send(err);

				res.json(bears);
			});
		});

	//on routes that end in /bears/:bear_id
	//----------------------------------------------------
	router.route('/check/:bear_id')

		// get the bear with that id
		.get(function(req, res) {
			Bear.findById(req.params.bear_id, function(err, bear) {
				if (err)
					res.send(err);
				res.json(bear);
			});
		})

		// update the bear with this id
		.put(function(req, res) {
			Bear.findById(req.params.bear_id, function(err, bear) {

				if (err)
					res.send(err);

				bear.name = req.body.name;
				bear.save(function(err) {
					if (err)
						res.send(err);

					res.json({ message: 'Bear updated!' });
				});

			});
		})

		// delete the bear with this id
		.delete(function(req, res) {
			Bear.remove({
				_id: req.params.bear_id
			}, function(err, bear) {
				if (err)
					res.send(err);

				res.json({ message: 'Successfully deleted' });
			});
		});
}



