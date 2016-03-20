var ChallengesModel = require('../model/challenges');

module.exports = function (callback) {

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
