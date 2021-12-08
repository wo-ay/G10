'use strict';
const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {


	let collection = db.collection('student')
	let res = await collection.where({
		idnum: event.idnum,
	}).update({
		email: event.email,

	})

	return {
		code: 200,
		msg: '邮箱修改成功',
		data: res
	}
}
