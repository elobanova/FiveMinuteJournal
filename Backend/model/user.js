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

module.exports = mongoose.model('UsersModel',{
	id: String,
	username: { type: String, required: true, unique: true},
    email: { type: String, required: true},
	password: { type: String, required: true}
});