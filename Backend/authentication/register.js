var LocalStrategy = require('passport-local').Strategy;
var UsersModel = require('../model/users');
var security = require('../authentication/security');

module.exports = function (passport) {

	passport.use('signup', new LocalStrategy({
			passReqToCallback : true
		},
			function (req, username, password, done) {

			findOrCreateUser = function () {
				UsersModel.findOne({
					'username' : username
				}, function (err, user) {
					if (err) {
						console.log('Error during registration: ' + err);
						return done(err);
					}
					if (user) {
						console.log('User already exists');
						return done(null, false, req.flash('message', 'User Already Exists'));
					} else {
						var newUser = new UsersModel();
						newUser.username = username;
						newUser.password = security.createHash(password);
						newUser.email = req.param('email');

						newUser.save(function (err) {
							if (err) {
								console.log('Error during Saving user: ' + err);
								throw err;
							}
							console.log('User Registration succesful');
							return done(null, newUser);
						});
					}
				});
			};
			process.nextTick(findOrCreateUser);
		}));
}
