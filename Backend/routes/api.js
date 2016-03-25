var express = require('express'),
router = express.Router(),
UserApi = require('../api/users');

module.exports = function(router) {
    router.route('/api/users')
        .get(UserApi.listUsers)
        .post(UserApi.register);

    return router;
};