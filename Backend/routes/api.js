var express = require('express'),
router = express.Router(),
UserApi = require('../api/users'),
DayValueApi = require('../api/dayvalues');

module.exports = function (router) {
	router.route('/api/users')
	.get(UserApi.listUsers)
	.post(UserApi.register);

	router.route('/api/dayvalue')
	.post(DayValueApi.createDayValue);

	return router;
};
