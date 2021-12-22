'use strict';

const db = uniCloud.database();
const collection = db.collection("course_task")

exports.main = async (event, context) => {
	let res= await collection.where({
		_id:event._id
	}).get()
	
	//返回数据给客户端
	return {
		data:res.data
	}
};
