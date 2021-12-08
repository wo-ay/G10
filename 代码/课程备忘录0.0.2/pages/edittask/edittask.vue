<template>
	<view>
		
		<view class="main">
			
			<view class="task" id="task_course">
				<textarea v-model="task_course" placeholder="请输入课程名" class="text"></textarea>
			</view>
			<view class="task" id="task_title">
				<textarea v-model="task_title" placeholder="请输入任务" class="text"></textarea>
			</view>
			<long-date
			    type="between" 
			    getDayNum="10" 
			    :openStatus="true" 
			    startTime="2021-12-03" 
			    endTime="2022-02-01" 
			    chooesMaxDay="8"
			    @select="Time">
			</long-date>
			<view class="task" id="task_content">
				<textarea v-model="task_content" placeholder="请输入任务内容" class="text"></textarea>
			</view>
		</view>
		
		<view id="submit">
			<button @click="submit_task()">提交</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				task_course:'',
				task_title:'',
				task_ddl:'',
				task_content:'',
				iffinish:'',
				idnum:'',
				task_ddl:"",
			}
		},
		onLoad() {
			this.idnum=uni.getStorageSync("globalUser");
			// console.log(this.idnum);
			
		
			
		},
		methods: {
			Time(val) {
				console.log(val);
				this.task_ddl=val;
			},
			submit_task: function(){
				uniCloud.callFunction({
					name:"add_personaltask",
					data: {
						task_course: this.task_course,
						idnum: this.idnum,
						task_title: this.task_title,
						task_content: this.task_content,
						task_ddl:this.task_ddl,
										
					},
					// 成功
					success(res) {
						console.log("success");
						uni.showToast({
							title: '添加任务成功',
							icon: 'none',
							duration: 1000
						});
						
					},
					//失败
					fail(e) {
						console.log(e);
					}
				})
				uni.switchTab({
					url: '../task/task'
				});
			}
		}
	}
</script>

<style>
	.main{
		margin-top: 80rpx;
	}
	.task{
		height: 80rpx;
		background-color: #F2F2F2;
		border-radius: 20rpx;

		margin: 20rpx;
	}
	.text{
		padding-top: 15rpx;
		margin-top: 20rpx;
		margin-right: 30rpx;
		margin-left: 20rpx;
	}
	
	#task_content{
		height: 950rpx;
	}
	#submit{
		margin: 40px;
		
	}
</style>
