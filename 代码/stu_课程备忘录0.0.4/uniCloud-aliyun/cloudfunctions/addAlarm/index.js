'use strict';

const db = uniCloud.database();
const collection = db.collection("alarm");

exports.main = async (event, context) => {
	//该闹钟是否存在
	let res1 = await collection.where({
		alarm_title:event.alarm_title,
		alarm_content:event.alarm_content,
		dateTime : new Date(+new Date(event.dateTime) + 8 * 3600 *1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,''),
		user_id:event.user_id
	}).get()
	console.log(res1)
	if (res1.affectedDocs != 0) {
		return "该闹钟已存在";
	}
	
	let res2 = await collection.add({
		alarm_title:event.alarm_title,
		alarm_content:event.alarm_content,
		dateTime : new Date(+new Date(event.dateTime) + 8 * 3600 *1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,''),
		user_id:event.user_id
	})
	return res2;
	
};
