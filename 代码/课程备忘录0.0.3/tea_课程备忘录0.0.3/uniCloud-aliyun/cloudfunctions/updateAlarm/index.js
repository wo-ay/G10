'use strict';

const db = uniCloud.database();
const collection = db.collection("alarm");

exports.main = async (event, context) => {
	
	let res = await collection.where({
		// alarm_title:event.alarm_title,
		// alarm_content:event.alarm_content,
		// dateTime : new Date(+new Date(event.dateTime) + 8 * 3600 *1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')
		_id:event._id
	}).update({
		alarm_title:event.alarm_title,
		alarm_content:event.alarm_content,
		dateTime: new Date(+new Date(event.dateTime) + 8 * 3600 *1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')
	})
	
	return res
};
