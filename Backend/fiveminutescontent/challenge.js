var ChallengesModel = require('../model/challenges');

exports.addchallenge = function (req) {
	var challenge = ChallengesModel({
			createdby : req.user,
			text : req.param('challenge'),
			date : req.param('week')
		});

	challenge.save(function (err) {
		if (err) {
			console.log('Error during saving challenge: ' + err);
			throw err;
		}

		console.log('Challenge created!');
	});
}

exports.listchallenge = function (callback) {

	ChallengesModel.find({}, function (err, challenges) {
		if (err) {
			console.log('Error during retrieving challenges: ' + err);
			throw err;
		}

		console.log(challenges);
		
		var jsonResponse = {challenges: challenges};
		callback(jsonResponse);
	});
}