'use strict';

const db = uniCloud.database();
const collection = db.collection("course_task_stu")
// const collection2 = db.collection("course_task_stu")
const dbcmd = db.command

// 课程任务列表
// 根据course_id获取该课程的所有任务，用当前时间进行筛选，除去ddl已经到了的，获取到一个task的列表

// 再用stu_id和task_id查找该列表中finish = 0的,在根据task_id显示数据



//获取task_id列表
exports.main = async (event, context) => {
	// const $ = dbcmd.aggregate
	let res = await collection.where({
		
		course_id: event.course_id,
		stu_idnum: event.stu_id,
		
		iffinish:"0"
		// task_ddl:dbcmd.gt(new Date())
	}).get()

	return {
		data: res.data
	}
};
