var mongoose = require('mongoose'),
	config = require('./config'),
	Schema = mongoose.Schema;

var DayValue = new Schema({
	id: String,
	createdby: {type: Schema.ObjectId, required: true},
    text: { type: String, required: true},
	date: { type: Date, required: true},
	gratefulfor: {type: [String]},
	whatwouldimprove: {type: [String]},
	amazingtoday: {type: [String]},
	howmakebetter: {type: [String]}
});

DayValue.pre('save', function (next) {
    if (!this.date) {
        this.date = Date.now();
    }
    next();
});

module.exports = mongoose.model('DayValue', DayValue);