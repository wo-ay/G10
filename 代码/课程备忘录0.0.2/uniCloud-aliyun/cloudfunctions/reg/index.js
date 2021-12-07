'use strict';
 const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {
    const collection = db.collection('check') //对holle数据库的获取；
    //event为客户端上传的参数
    console.log('event : ', event)
    
    // 添加数据
    let res = await collection.add({
        name: event.name,
		id: event.id,
		idnum: event.idnum,
		email: event.email,
		tel: event.tel,
		pwd: event.pwd,
		ifpass:0,
    })
    
    return {
        code: 200,
        msg: '查询成功',
        data: res
    }
}