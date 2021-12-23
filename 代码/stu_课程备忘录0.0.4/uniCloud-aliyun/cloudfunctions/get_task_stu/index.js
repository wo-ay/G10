'use strict';
const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {
	const collection = db.collection('course_task_stu') //对holle数据库的获取；
	//event为客户端上传的参数
	console.log('event : ', event)

	// 添加数据
	const collectionn = db.collection('course_stu')

	let stu = await collectionn.where({
		course_id: event.course_id,

	}).get()
	
	return {
		code: 200,
		msg: '获取审核表成功',
		// data: checktable.data
		id: "学生",
		
		stu_list:stu.data,

	}





}
