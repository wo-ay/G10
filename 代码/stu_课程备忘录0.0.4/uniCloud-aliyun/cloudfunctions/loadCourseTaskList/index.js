'use strict';

const db = uniCloud.database();
const collection = db.collection("course_task")
const collection2 = db.collection("course_task_stu")
const dbcmd = db.command

// 课程任务列表
// 根据course_id获取该课程的所有任务，用当前时间进行筛选，除去ddl已经到了的，获取到一个task的列表

// 再用stu_id和task_id查找该列表中finish = 0的,在根据task_id显示数据

exports.main = async (event, context) => {
	const $ = dbcmd.aggregate
	let res = await collection.where({
		course_id:event.course_id
		// task_ddl:dbcmd.gt(new Date())
		},
		dbcmd.expr(
		$.gt(['$time',$.dateFromString({
			task_ddl: new Date('2022-02-02').toISOString()
        })])
		)
		).get()
	
	// console.log(res.data)
	
	// let listTable = []
	
	// //找这个学生没有完成的任务
	// for (let i = 0; i < res.data.length; i++) {
	// 	let res2 = await collection2.where({
	// 		task_id:res.data[i]._id,
	// 		stu_idnum:event.stu_id,
	// 		iffinish: "0"
	// 	}).get()
	// 	listTable.push({
	// 		task_id:res2.data[0]
	// 	})
	// }
	
	// let resTable = []
	
	// for (let i = 0; i < listTable.length;i++) {
	// 	let res3 = await collection.where({
	// 		_id:listTable[i].task_id
	// 	}).get()
	// 	resTable.push(res3.data[0])
	// }
	
	return {
		data: res.data
	}
};
