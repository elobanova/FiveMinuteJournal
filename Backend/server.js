var express = require('express'),
	path = require('path'),
	logger = require('morgan'),
	cookieParser = require('cookie-parser'),
	bodyParser = require('body-parser'),
	app = express(),
	passport = require('passport'),
	expressSession = require('express-session'),
	flash = require('connect-flash'),
	initPassport = require('./authentication/init'),
	routes = require('./routes/forwarding')(passport),
	config = require('./model/config'),
	fs = require('fs'),
	options = {
		key: fs.readFileSync('./private.pem'),
		cert: fs.readFileSync('./public.pem')
	};
	https = require('https'),
	server = https.createServer(options, app),
	io = require('socket.io').listen(server),
	bCrypt = require('bcrypt-nodejs');

require('./model/mongoose');
app.set('views', path.join(__dirname, 'view'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());
app.use(cookieParser());
app.use(expressSession({secret: 'mySecretKey'}));
app.use(passport.initialize());
app.use(passport.session());
app.use(flash());
initPassport(passport);
app.use('/', routes);
app.use(express.static(__dirname + '/public'));

app.use(function(req, res, next) {
    var err = new Error('Not Found');
	console.log('--------------' + err);
    err.status = 404;
    next(err);
});

io.on('connection', function(socket) {
	console.log('a user connected');
});

server.listen(config.get('port'), function () {
	var host = server.address().address;
	var port = server.address().port;

	console.log('Listening at http://%s:%s', host, port);
});

module.exports = app;
