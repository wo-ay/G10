'use strict';

const db = uniCloud.database();
const collection = db.collection("alarm")

exports.main = async (event, context) => {
	
	//console.log("收到了")
	//增加数据
	//new Date(+new Date(date_time)).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')
	//console.log(new Date(event.dateTime))
		
	let res = await collection.add({
		dateTime : new Date(+new Date(event.dateTime) + 8 * 3600 *1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')
	})
	
	return res;
	
};
