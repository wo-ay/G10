'use strict';
 const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {
    const collection = db.collection('personal_task') //对holle数据库的获取；
    //event为客户端上传的参数
    console.log('event : ', event)
    
    // 添加数据
    let res = await collection.add({
		idnum:event.idnum,
        task_course:event.task_course,
		task_title: event.task_title,
		task_content: event.task_content,
		task_ddl: new Date(+new Date(event.task_ddl) + 8 * 3600 *1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,''),
		iffinish:"未完成",
		ing:0
    })
    
    return {
        code: 200,
        msg: '查询成功',
        data: res
    }
}