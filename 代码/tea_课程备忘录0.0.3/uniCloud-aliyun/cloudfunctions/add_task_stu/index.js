'use strict';
const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {
	const collection = db.collection('course_task_stu') //对holle数据库的获取；
	const $ = db.command.aggregate
	//event为客户端上传的参数
	console.log('event : ', event)




	let res = await collection.add({

		course_id: event.course_id,

		task_id: event.task_id,
		task_title: event.task_title,
		stu_idnum: event.stu_idnum,
		iffinish: "0",
	})


	return {
		code: 200,
		msg: '添加成功',
		stu_len: $.size('$event.stu_list')
	}
	6

}
