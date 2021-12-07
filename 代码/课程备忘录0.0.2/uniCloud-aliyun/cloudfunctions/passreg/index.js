'use strict';
 const db = uniCloud.database() //对数据库的对象获取；
exports.main = async (event, context) => {
	
    
    let collection = db.collection('check')
    let res = await collection.where({idnum:event.idnum})
      .update({
        ifpass: 1,
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