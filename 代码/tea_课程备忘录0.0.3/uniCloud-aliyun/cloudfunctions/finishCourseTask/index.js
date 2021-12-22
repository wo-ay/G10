'use strict';

const db = uniCloud.database();
const collection = db.collection("course_task_stu")

exports.main = async (event, context) => {
	
	let res = await collection.where({
		course_task_id:event._id,
		stu_id:event.stu_id
	}).update({
		task_state:"1"
	})
	
	return res
};
