var async = require('asyncawait/async'),
await = require('asyncawait/await'),
UserModel = require('../model/users'),
security = require('../authentication/security');

exports.register = async(function (req, res, next) {
    var requestBody = req.body;
    var user = await(UserModel.findOne({
        username: requestBody.username
    }).exec());
    if (user) {
        return next(new HttpError(409, 'A user with name ' + requestBody.username + ', is already registered.'));
    }
    var newUser = new UserModel();
	newUser.email = requestBody.email;
    newUser.username = requestBody.username;
    newUser.password = security.createHash(requestBody.password);

    await(UserModel.create(newUser));
    return res.status(201).send();
});

exports.listUsers = async(function (req, res, next) {
    var users = await(UserModel.find().exec());

    return res.json(users.map(function (user) {
        return {
            email: user.email,
            username: user.username,
            id: user.id,
            password: user.password
        }
    }));
});