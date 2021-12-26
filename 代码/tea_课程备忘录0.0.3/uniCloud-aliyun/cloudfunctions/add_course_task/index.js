'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)

	/* 该课程是否和该教师无关联 */

	let result = await db.collection('course_task').add({
		teacher_idnum: event.teacher_idnum,
		course_id: event.task_course,
		task_title: event.task_title,
		task_ddl: event.task_ddl,
		task_content: event.task_content,
		course_name: event.course_name
	})
	

	return {
		code: 200,
		msg: '添加任务成功',
		// data: checktable.data,
		course_count: 1,
		// course_id: course.data[0].course_id,
		// task_id: task.data[0]._id

	}



};
