'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)

	let course_count = await db.collection('course_tea').where({
		id: event.id,
		idnum: event.idnum,
		pwd: event.pwd,
	}).count()

	if (course_count.total == 0) {
		return {
			msg: "此课程不存在",
			course_count: 0
		}
	} else {

		//返回数据给客户端
		return {
			code: 200,
			msg: '获取审核表成功',
			// data: checktable.data
			data: res.data,
			course: course_count

		}
	}

};
