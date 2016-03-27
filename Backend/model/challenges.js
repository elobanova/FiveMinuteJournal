var mongoose = require('mongoose'),
	config = require('./config');

module.exports = mongoose.model('ChallengesModel',{
	id: String,
	createdby: String,
    text: { type: String, required: true},
	date: { type: Date, required: true}
});