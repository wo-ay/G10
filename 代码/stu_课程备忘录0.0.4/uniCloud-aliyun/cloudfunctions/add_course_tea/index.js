'use strict';
const db = uniCloud.database()
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	/* 最后都会在coourse_stu表中填加一条记录，表明这个学生与课程有联系*/
	let which_course = await db.collection('course').where({
		/* 获取刚添加的课程id号 */
		course_day: event.course_day,
		start_time: event.start_time,
		end_time: event.end_time,
		course_add: event.course_add,
	}).get()

	for (let i = 0; i < 1000; i++) {
		if (i == 800) {
			let result = await db.collection('course_tea').add({
				course_id: which_course.data[0]._id,
				course_name: event.course_name,
				teacher_idnum: event.idnum,
				course_day: event.course_day,
				start_time: event.start_time,
				end_time: event.end_time,
			})
		}
	}









	// let res = await db.collection('course_stu').add({
	// 	course_id: which_course.data[0]._id,
	// 	course_name: event.course_name,
	// 	stu_idnum: event.idnum,

	// })


	//返回数据给客户端
	return {
		courseid: which_course.data[0]._id,
		msg: '添加课程学生的联系'
	}
};
