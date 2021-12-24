'use strict';

const db = uniCloud.database();
const collection = db.collection("alarm");

exports.main = async (event, context) => {
	let res = await collection.where({
		_id:event._id
	}).remove()
	
	return res
};
