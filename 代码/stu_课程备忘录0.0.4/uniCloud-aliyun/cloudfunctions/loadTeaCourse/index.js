'use strict';

const db = uniCloud.database();
const collection = db.collection("course_tea")

exports.main = async (event, context) => {
	let res = await collection.where({
		teacher_idnum:event.tea_idnum
	}).get()
	
	return {
		data:res.data
	}
	
};
