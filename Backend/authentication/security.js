var bCrypt = require('bcrypt-nodejs');

exports.createHash = function (password) {
	return bCrypt.hashSync(password, bCrypt.genSaltSync(10), null);
}