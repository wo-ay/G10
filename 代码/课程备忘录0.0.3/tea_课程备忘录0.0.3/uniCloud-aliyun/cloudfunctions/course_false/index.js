'use strict';
const db = uniCloud.database()
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	
	let course_exist = await db.collection('course_tea').where({
		/* 查看当前课程表中是否存在该课程 */
		// idnum: event.idnum,
		start_time: dbCmd.
	}).count()
	//返回数据给客户端
	return {
		code:200,
		msg:'获取审核表成功',
		coursenum: course_exist.total
	}
};
