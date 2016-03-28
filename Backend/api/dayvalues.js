var async = require('asyncawait/async'),
await = require('asyncawait/await'),
DayValueModel = require('../model/dayvalue'),
UsersModel = require('../model/user');

function formatDayValue(dayValue, createdby) {
	return {
		id : dayValue.id,
		date : dayValue.date,
		gratefulfor : dayValue.gratefulfor,
		whatwouldimprove : dayValue.whatwouldimprove,
		amazingtoday : dayValue.amazingtoday,
		howmakebetter : dayValue.howmakebetter,
		text : dayValue.text,
		createdby : {
			username : createdby.username,
			email : createdby.email
		}
	};
}

exports.createDayValue = async(function (req, res, next) {
		var dayValueData = req.body;
		var dayValue = await(DayValueModel.findOne({
					date : dayValueData.date
				}).exec());

		if (dayValue) {
			return next(new HttpError(409, 'Value for this day already exists.'));
		}

		console.log("logging request" + JSON.stringify(req));
		dayValue.createdby = req.username.id;

		try {
			await(DayValueModel.create(dayValueData));
			return res.send();
		} catch (ex) {
			return next(new HttpError(400, ex.message))
		}
	});

exports.getDayValue = async(function (req, res, next) {
		try {
			var date = req.params.date;

			var dayValue = await(DayValueModel.findOne({
						date : date
					}).exec());
			if (!dayValue) {
				return next(new HttpError(404, 'Day value not found'));
			}
			var createdby = await(UsersModel.findById(dayValue.createdby));
			return res.json(formatDayValue(dayValue, createdby));
		} catch (ex) {
			return next(ex);
		}
	});

exports.listDayValues = async(function (req, res, next) {
		var dayValues = await(DayValueModel.find().exec());
		if (!dayValues) {
			return res.json([]);
		}
		var jsonObject = await(dayValues.map(async(function (dayValue) {
						var createdby = await(UsersModel.findById(dayValue.createdby));
						return formatDayValue(dayValue, createdby);
					})));
		return res.json(jsonObject);
	});
