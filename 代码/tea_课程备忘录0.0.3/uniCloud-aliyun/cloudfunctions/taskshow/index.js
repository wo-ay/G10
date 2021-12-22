'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)


	if (event.id == '学生') {
		let checktable = await db.collection('personal_task').where({
			idnum: event.idnum,

			// iffinish: event.iffinish ,
			ing: event.ing

		}).get()
		return {
			code: 200,
			msg: '获取审核表成功',
			data: checktable.data
		}
	} else if (event.id == '教师') {
		let checktable = await db.collection('course_task').where({
			teacher_idnum: event.idnum,

			// iffinish: event.iffinish ,
			// ing: event.ing

		}).get()
		return {
			code: 200,
			msg: '获取审核表成功',
			data: checktable.data
		}
	}

	//返回数据给客户端

};
