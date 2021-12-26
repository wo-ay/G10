'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)


	let finish_count = await db.collection('course_task_stu').where({
	  task_id: event.task_id,
	  iffinish: "1"
	}).count()
	
	let all_count = await db.collection('course_task_stu').where({
	  task_id: event.task_id,
	  
	}).count()
	
	let remind = await db.collection('course_task').where({
	  _id: event.task_id,
	  
	}).get()
	//返回数据给客户端
	return {
		code: 200,
		msg: '获取完成率成功',
		// data: checktable.data
		// finish_count:finish_count.total,
		// all_count:all_count.total,
		data:finish_count.total/all_count.total,
		if_remind:remind.data[0].if_remind
		
	}
};
