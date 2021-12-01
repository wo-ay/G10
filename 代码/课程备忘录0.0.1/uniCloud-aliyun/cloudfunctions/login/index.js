'use strict';

const db = uniCloud.database();
exports.main = async (event, context) => {
	
	
	
	//返回数据给客户端
	// return event
	const student = db.collection('student');
	let res = await student.where({
		name:"wdz"
	}).get()
	//event为客户端上传的参数
	console.log('event : ', event)
	return{
		// content:"成功"
		code:200,
		msg:"我的名字："+event.name+"我的年龄："+event.age,
		context:context
	}
};
