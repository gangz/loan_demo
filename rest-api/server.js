var express    = require('express');
var bodyParser = require('body-parser');
var app        = express();

// configure app
app.use(bodyParser());

var port     = process.env.PORT || 3001; // set our port

var router = require('./router/router.js');
router(app);

app.listen(port);
console.log('Magic happens on port ' + port);
