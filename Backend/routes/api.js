var express = require('express'),
router = express.Router(),
UserApi = require('../api/users'),
DayValueApi = require('../api/dayvalues');

module.exports = function (router) {
	router.route('/api/users')
	.get(UserApi.listUsers)
	.post(UserApi.register);

	router.route('/api/dayvalues')
	.post(DayValueApi.createDayValue)
	.get(DayValueApi.listDayValues);
	
	router.route('/api/owndayvalues')
	.get(DayValueApi.listOwnDayValues);
	
	router.route('/api/dayvalues/:id')
	.get(DayValueApi.getDayValue);

	return router;
};
