var express = require('express'),
config = require('../model/config'),
addchallenge = require('../fiveminutescontent/addchallenge'),
listchallenge = require('../fiveminutescontent/listchallenge'),
router = express.Router();

var isAuthenticated = function (req, res, next) {
	if (req.isAuthenticated())
		return next();
	res.redirect('/');
}

module.exports = function (passport) {
	router.get('/', function (req, res) {
		var jsonResponse = new Object();
		jsonResponse.message = req.flash('message');
		res.render('index', jsonResponse);
	});

	router.post('/login', passport.authenticate('login', {
			successRedirect : '/home',
			failureRedirect : '/',
			failureFlash : true
		}));

	router.get('/signup', function (req, res) {
		var jsonResponse = new Object();
		jsonResponse.message = req.flash('message');
		res.render('register', jsonResponse);
	});

	router.post('/signup', passport.authenticate('signup', {
			successRedirect : '/home',
			failureRedirect : '/signup',
			failureFlash : true
		}));

	router.get('/home', isAuthenticated, function (req, res) {
		var jsonResponse = new Object();
		jsonResponse.user = req.user;
		jsonResponse.scripts = ['/script.js'];
		res.render('home', jsonResponse);
	});

	router.get('/signout', function (req, res) {
		req.logout();
		res.redirect('/');
	});

	router.post('/weeklychallenge', function (req, res) {
		console.log("posting challenge");
		addchallenge(req);
		res.redirect('/challenges');
	});

	router.get('/challenges', function (req, res) {
		listchallenge(function (jsonResponse) {
			res.render('challenges', jsonResponse);
		});
	});
	return router;
}
