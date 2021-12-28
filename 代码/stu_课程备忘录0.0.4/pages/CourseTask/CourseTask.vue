<template>
	<view>
		<uni-list>
			<uni-list>
				<view v-for="item in course_task_list" @click="loadCourseTaskDetail(item._id)" >
					<uni-list-item :title = "'@'+item.course_name+'：'+item.task_title" :note="'截止时间：' + item.task_ddl" show-arrow="true" ></uni-list-item>
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
			// this.course_id = '61b6079bb804f80001909fcc'
			this.getCourseTaskList();
		},
		
		onShow(){
			// this.course_id = option._id
			 this.getCourseTaskList();
		},
		
		methods: {
			getCourseTaskList() {
				console.log(this.course_id)
				console.log(uni.getStorageSync("globalUser"))
				uniCloud.callFunction({
					name:"loadCourseTaskList",
					data:{
						course_id:this.course_id,
						stu_id:uni.getStorageSync("globalUser")
					}
				}).then((res)=>{
					
					const{result} = res
					this.course_task_list = result.data
					console.log("结果是啥")
					// console.log(this.course_task_list[0])
					console.log(this.course_task_list.length)
					
				})
			},
			
			loadCourseTaskDetail(_id) {
				// this.$emit("click",event)
				uni.navigateTo({
					url:'../CourseTaskDetail/CourseTaskDetail?_id=' + _id
				})
			}
		}
	}
</script>

<style>

</style>
