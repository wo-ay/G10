'use strict';

const db = uniCloud.database();
const collection = db.collection("course_task")

exports.main = async (event, context) => {
	//event为客户端上传的参数
	let res = await collection.where({
		course_id:event.course_id,
		teacher_idnum:event.teacher_id
	}).get()
	
	//返回数据给客户端
	return {
		data:res.data
	}
};
