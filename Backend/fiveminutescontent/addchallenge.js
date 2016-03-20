var ChallengesModel = require('../model/challenges');

module.exports = function (req) {
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
