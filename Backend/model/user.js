var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var UsersModel = new Schema({
   id: String,
	username: { type: String, required: true, unique: true},
    email: { type: String, required: true},
	password: { type: String, required: true}
});

module.exports = mongoose.model('UsersModel', UsersModel);