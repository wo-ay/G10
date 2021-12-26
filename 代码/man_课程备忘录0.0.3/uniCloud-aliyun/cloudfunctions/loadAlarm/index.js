'use strict';

const db = uniCloud.database();
const collection = db.collection("alarm")

exports.main = async (event, context) => {
	
	let res = await collection.where({
		user_id:event.user_id
	}).get()
	//console.log(res.data[0].dateTime)
	//返回数据给客户端
	return {
		data:res.data
	}
};
