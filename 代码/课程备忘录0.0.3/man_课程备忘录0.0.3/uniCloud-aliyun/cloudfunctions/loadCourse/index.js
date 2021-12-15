'use strict';

const db = uniCloud.database();
const collection = db.collection("course_stu")

exports.main = async (event, context) => {
	let res = await collection.where({
		stu_idnum:event.stu_idnum
	}).get()
	
	return {
		data:res.data
	}
	
};
