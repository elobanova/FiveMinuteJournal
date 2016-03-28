var mongoose = require('mongoose'),
	config = require('./config');

mongoose.connect(config.get('mongoose:uri'));
var db = mongoose.connection;

db.on('error', function (err) {
    console.log('Connection error:', err.message);
});
db.once('open', function callback () {
    console.log("Connected to database.");
});