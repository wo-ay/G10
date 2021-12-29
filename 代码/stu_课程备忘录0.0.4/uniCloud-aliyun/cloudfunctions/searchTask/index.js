'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)


	let res = await db.collection('course_task').where({
	  _id: event.task_id,
	  
	}).get()
	//返回数据给客户端
	return {
		code: 200,
		msg: '课程任务表成功',
		// data: checktable.data
		id: "学生",
		
		data:res.data,
		
		
	}
};
