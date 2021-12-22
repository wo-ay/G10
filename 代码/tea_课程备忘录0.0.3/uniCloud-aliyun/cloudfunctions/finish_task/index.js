'use strict';
 const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {
	
    
    let collection = db.collection('personal_task')
    let res = await collection.where({_id:event.task_id})
      .update({
        ing:0,
		iffinish: "已完成",
      }).then((res) => {
            uni.showToast({
                title: '更新成功'
            })
        })
    
    return {
        code: 200,
        msg: '查询成功',
        data: res
    }
}