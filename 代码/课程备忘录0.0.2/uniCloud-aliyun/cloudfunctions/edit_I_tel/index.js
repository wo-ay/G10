'use strict';
 const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {
	
    
    let collection = db.collection('student')
    let res = await collection.where({
		idnum:event.idnum,
		}).update({
        tel: event.tel,
		
      })
    
    return {
        code: 200,
        msg: '查询成功',
        data: res
    }
}