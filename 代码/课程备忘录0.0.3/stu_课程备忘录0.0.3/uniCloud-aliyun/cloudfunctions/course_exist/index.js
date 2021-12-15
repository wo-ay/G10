'use strict';
const db = uniCloud.database()
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	
	let course_exist = await db.collection('course').where({
		/* 查看当前课程表中是否存在该课程 */
		// idnum: event.idnum,
		course_name: event.course_name,
		course_day: event.course_day,
		start_time: event.start_time,
		end_time: event.end_time,
		course_add: event.course_add,
	}).count()
	//返回数据给客户端
	return {
		code:200,
		msg:'获取审核表成功',
		coursenum: course_exist.total
	}
};
