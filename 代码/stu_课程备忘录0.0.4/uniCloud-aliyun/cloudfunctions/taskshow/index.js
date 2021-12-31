'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	
	
	
	let checktable = await db.collection('personal_task').where({
		idnum: event.idnum,	
		
		// iffinish: event.iffinish ,
		// ing: event.ing
		
	}).get()
	//返回数据给客户端
	return {
		code:200,
		msg:'获取审核表成功',
		data:checktable.data
	}
};
