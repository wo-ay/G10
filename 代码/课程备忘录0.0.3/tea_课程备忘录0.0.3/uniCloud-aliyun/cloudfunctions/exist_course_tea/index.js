'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)


	/* 判断该课程是否和该教师有关联 */
	let course_count = await db.collection('course_tea').where({
		course_day: event.course_day,
		start_time: event.start_time,
		end_time: event.end_time,
	}).count()


	/* 该课程是否和该教师无关联 */
	if (course_count.total == 0) {

		return {
			msg: "此教师的该课程不存在",
			course_count: 0
		}
	} else {

		for (let i = 0; i < 2000; i++) {
			if (i == 1990) {

				/* 获取该课程的编号 */
				let course = await db.collection('course_tea').where({
					course_day: event.course_day,
					start_time: event.start_time,
					end_time: event.end_time,
					// task_ddl:event.task_ddl,
				}).get()

				while (i < 9000) {
					i++;

					if (i == 8000) {



						let result = await db.collection('course_task').add({
							course_name: event.task_course,
							teacher_idnum: event.teacher_idnum,
							course_id: course.data[0].course_id,
							task_title: event.task_title,
							task_ddl: event.task_ddl,
							task_content: event.task_content,

						})

						//返回数据给客户端
						return {
							code: 200,
							msg: '获取审核表成功',
							// data: checktable.data
							course_count: 1

						}

					}
				}
			}
		}









	}

};
