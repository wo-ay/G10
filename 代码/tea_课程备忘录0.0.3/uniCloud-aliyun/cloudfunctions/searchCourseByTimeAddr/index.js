'use strict';

const db = uniCloud.database();
const collection = db.collection("course")

exports.main = async (event, context) => {
	
	let res = await collection.where({
		course_day:event.course_day,
		start_time:event.start_time,
		course_add:event.course_add
	}).get()
	
	return {
		data:res.data
	}
};
