'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)

	if (event.id == "学生") {
		let res = await db.collection('personal_task').where({
			_id: event.taskid,

		}).get()
		return {
			code: 200,
			msg: '获取审核表成功',
			// data: checktable.data
			data: res.data,
		}
	}

	if (event.id == "教师") {
		let res = await db.collection('course_task').where({
			_id: event.taskid,

		}).get()
		return {
			code: 200,
			msg: '获取审核表成功',
			// data: checktable.data
			data: res.data,
		}
	}
	//返回数据给客户端
	
};
