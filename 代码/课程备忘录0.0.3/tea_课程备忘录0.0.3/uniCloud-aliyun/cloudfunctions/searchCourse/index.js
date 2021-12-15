'use strict';

const db = uniCloud.database();
const collection = db.collection("course")

exports.main = async (event, context) => {
	let res = await collection.where({
		_id:event.course_id
	}).get()
	
	return {
		data:res.data
	}
};
