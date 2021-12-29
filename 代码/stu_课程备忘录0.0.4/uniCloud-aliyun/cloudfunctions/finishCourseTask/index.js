'use strict';

const db = uniCloud.database();
const collection = db.collection("course_task_stu")

exports.main = async (event, context) => {
	
	let res = await collection.where({
		task_id:event._id,
		stu_idnum:event.stu_id
	}).update({
		iffinish:"1"
	})
	
	return res
};
