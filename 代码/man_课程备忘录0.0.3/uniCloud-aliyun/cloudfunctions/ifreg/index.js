'use strict';
const db = uniCloud.database()

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)


	let res = await db.collection('check').where({
		id: event.id,
		idnum: event.idnum,
		pwd: event.pwd,
	}).get()
	//返回数据给客户端
	return {
		code: 200,
		msg: '获取审核表成功',
		// data: checktable.data
		data:res.data,
		ifpass: res.data[0].ifpass,
		
		
	}
};
