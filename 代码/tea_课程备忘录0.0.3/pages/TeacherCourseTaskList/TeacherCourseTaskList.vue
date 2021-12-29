<template>
	<view>
		<uni-list>
			<uni-list>
				<view v-for="item in course_task_list" @click="getTeacherCourseTaskDetail(item._id)">
					<uni-list-item :title = "'@'+item.course_name+'：'+item.task_title" :note="'截止时间：' + item.task_ddl" show-arrow="true" ></uni-list-item>
				</view>
			</uni-list>
		</uni-list>
		<view v-if="course_task_list.length <= 0 " class="tip" style="width: 30%; margin: 120rpx auto;">
			暂无课程任务！
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				course_id:"",
				teacher_id:"",
				course_task_list:[]
			}
		},
		
		onLoad(option) {
			this.teacher_id = uni.getStorageSync("globalUser")
			this.course_id = option._id
			this.getCourseTaskList();
		},
		onShow(){
			this.getCourseTaskList();
		},
		onNavigationBarButtonTap: function(e) {
			console.log(e.text); //提交
			console.log(e.fontSize); //16px
			if (e.text == "+") {
				uni.navigateTo({
					url: '../editcoursetask/editcoursetask?courseid='+this.course_id
				})
			}
			
		
		},
		methods: {
			getCourseTaskList(){
				console.log(this.course_id)
				console.log(this.teacher_id)
				uniCloud.callFunction({
					name:"getTeacherCourseTaskList",
					data:{
						course_id:this.course_id,
						teacher_id:this.teacher_id
					}
				}).then((res)=>{
					const{result} = res
					this.course_task_list = result.data
				})
			},
			getTeacherCourseTaskDetail(_id) {
				this.$emit("click",event)
				uni.navigateTo({
					url:"../task_detail/task_detail?taskid=" + _id
				})
			}
			
		}
	}
</script>

<style>
	/* .tip{
		width: 30%; 
		margin: 120rpx auto;
	} */
</style>
