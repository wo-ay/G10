<template>
	<view>
		<uni-list>
			<uni-list>
				<view v-for="item in course_task_list" @click="toDetail(item._id)">
					<uni-list-item :title = "'@'+item.course_name+'：'+item.task_title" :note="'截止时间：' + item.task_ddl"></uni-list-item>
				</view>
			</uni-list>
		</uni-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				course_id:"",
				course_task_list:[]
			}
		},
		
		onLoad(option){
			this.course_id = option._id
			this.getCourseTaskList();
		},
		
		// onShow(option){
		// 	this.course_id = option._id
		// 	this.getCourseTaskList();
		// },
		
		methods: {
			getCourseTaskList() {
				console.log(this.course_id)
				uniCloud.callFunction({
					name:"loadCourseTaskList",
					data:{
						course_id:this.course_id
					}
				}).then((res)=>{
					const{result} = res
					this.course_task_list = result.data
					
				})
			}
		}
	}
</script>

<style>

</style>
