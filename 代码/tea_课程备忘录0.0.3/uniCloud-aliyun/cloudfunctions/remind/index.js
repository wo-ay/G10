'use strict';
const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {
	const collection = db.collection('course_task') //对holle数据库的获取；
	// const $ = db.command.aggregate
	//event为客户端上传的参数
	console.log('event : ', event)




	let res = await collection.where({
		_id: event.task_id
	}).update({
		if_remind:1
	})


	return {
		code: 200,
		msg: '提醒成功',
		// stu_len: $.size('$event.stu_list')
	}
	6

}
