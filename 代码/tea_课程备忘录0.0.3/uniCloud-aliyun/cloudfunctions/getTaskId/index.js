'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)


	let task = await db.collection('course_task').where({
		// course_name: event.task_course,
		teacher_idnum: event.teacher_idnum,
		// course_id: course.data[0].course_id,
		task_title: event.task_title,
		task_ddl: event.task_ddl,
		task_content: event.task_content,
	}).get()

	return {
		code: 200,
		msg: '获取task_id成功',
		data: task.data[0]._id
		// course_count: 1,
		// task_id: task.data[0]._id

	}
};
