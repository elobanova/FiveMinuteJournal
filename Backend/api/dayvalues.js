var async = require('asyncawait/async'),
await = require('asyncawait/await'),
DayValueModel = require('../model/dayvalue');

exports.createDayValue = async(function (req, res, next) {
    var dayValueData = req.body;
    var dayValue = await(DayValueModel.findOne({date: dayValueData.date}).exec());

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