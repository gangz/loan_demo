var express    = require('express');
var bodyParser = require('body-parser');
var java = require("java");
var eligibility = require("./eligibility.js");

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

router.route('/creditcheck/:level')
	.get(function(req,res){
		var result = eligibility.checkCredit(req.params.level);
		res.json({ 'eligibility status': result });	
	})
	
// on routes that end in /bears
// ----------------------------------------------------
router.route('/bears')

	// create a bear (accessed at POST http://localhost:8080/bears)
	.post(function(req, res) {
		
		var bear = new Bear();		// create a new instance of the Bear model
		bear.name = req.body.name;  // set the bears name (comes from the
									// request)

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
		req.
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


// REGISTER OUR ROUTES -------------------------------
app.use('/api', router);
}