'use strict';

const db = uniCloud.database();
const collection = db.collection("course_task")
const dbcmd = db.command

exports.main = async (event, context) => {
	
	let res = await collection.where({
		course_id:event.course_id,
		task_ddl:dbcmd.gt(new Date().getTime())
	}).get()

	
	return {
		data:res.data
	}
};
