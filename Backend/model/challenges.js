var mongoose = require('mongoose'),
	config = require('./config');

mongoose.connect(config.get('mongoose:uri'));
var db = mongoose.connection;

module.exports = mongoose.model('ChallengesModel',{
	id: String,
	createdby: String,
    text: { type: String, required: true},
	date: { type: Date, required: true}
});