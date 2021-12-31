<template>
	<view>
		<!-- <view class = "page_name">
			<p>任务详情</p>
		</view> -->
		<view class = "task_title">
			<p>{{task_title}}</p>
		</view>
		<view class = "course_name">
			<p>{{course_name}}</p>
		</view>
		<view class = "task_content">
			<p>{{task_content}}</p>
		</view>
		
		<view class = "end_time">
			<p>截止时间：{{task_ddl}}</p>
		</view>
		<view class = "finish">
			<button class = "finish" type = "primary" @click="finished()">完成</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				_id : "",
				course_name:"",
				task_title:"",
				task_content:"",
				task_ddl:""
				
			}
		},
		
		onLoad(option){
			
			this._id = option._id
			console.log(this._id)
			this.getCourseTaskDetail();
		},
		onShow() {

			this.getCourseTaskDetail();
		},
		
		methods: {
			getCourseTaskDetail() {
				uniCloud.callFunction({
					name:'getCourseTaskDetail',
					data: {
						_id:this._id 
					}
				}).then((res)=>{
					const {result} = res
					this.course_name = result.data[0].course_name
					this.task_title = result.data[0].task_title
					this.task_ddl = result.data[0].task_ddl
					this.task_content = result.data[0].task_content
					console.log(this.course_name)
					console.log(this.task_title)
					console.log(this.task_ddl)
					console.log(this.task_content)
					
				})
			},
			finished() {
				console.log('任务完成')
				uniCloud.callFunction({
					name:"finishCourseTask",
					data:{
						//任务的_id
						_id:this._id,
						stu_id:uni.getStorageSync("globalUser")
					},
					success(res) {
						uni.showToast({
							title: "任务完成!",
							icon: "success",
							duration: 2000
						});
						setTimeout(function() {
							uni.navigateBack()
						}, 2000);
					}
				})
			}
		}
	}
</script>

<style>
	.page_name{
		color: #808080;
		height: 130rpx;
		font-size: 40rpx;
		font-weight: 550;
		margin-left: 280rpx;
		margin-top: 25rpx;
	}
	.task_title{
		margin-left: 30rpx;
		font-weight: 600;
		font-size: 40rpx;
	}
	.course_name{
		margin-left: 30rpx;
		color: #18BC37;
		margin-top: 5px;
	}
	.task_content{
		margin-top: 50rpx;
		margin-left: 50rpx;
	}
	.end_time {
		margin-left: 300rpx;
		margin-top: 30rpx;
	}
	.finish{
		margin-top: 50px;
		margin-left: 70rpx;
		margin-right: 70rpx;
		
	}
</style>
