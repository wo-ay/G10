<template>
	<view>
		
		<view class="main" >
			
			<view class="task" id="task_course">
				<textarea :value="this.task_course" placeholder="请输入指定任务的课程"></textarea>
			</view>
			
			<view class="task" id="task_ddl">
				<textarea :value="this.task_ddl" placeholder="请输入截止日期"></textarea>
			</view>
			<view class="task" id="task_title">
				<textarea :value="this.task_title" placeholder="请输入任务标题"></textarea>
			</view>
			
			<view class="task" id="task_content">
				<textarea :value="this.task_content" placeholder="请输入任务内容"></textarea>
			</view>
			
			<view id="submit">
				<button>提交</button>
			</view>
			
		</view>
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// list:[
				// 	{
				// 		id:1,
				// 		course:软件工程,
				// 		title:课程预备项目,
				// 		content:完成预备作业,
				// 	},
				// 	{
				// 		id:2,
				// 		course:高级程序设计,
				// 		title:实验3,
				// 		content:完成实验3,
				// 	},
				// 	{
				// 		id:3,
				// 		course:项目管理,
				// 		title:大作业1,
				// 		content:完成大作业,
				// 	},
				// 	{
				// 		id:4,
				// 		course:项目实践,
				// 		title:课程实践项目,
				// 		content:完成大作业,
				// 	},
					
					
				// ],
				taskid:"",
				task_course:"",
				task_ddl:"",
				task_title:"",
				task_content:""
				
				
			}
		},
		 
		onLoad(option) {
			this.taskid = option.taskid;
			// console.log(option)
			console.log(option.taskid)
			this.gettaskdetail();
		},
		methods: {
			
			gettaskdetail:function(){
				uniCloud.callFunction({
					name: 'taskdetailshow',
					data: {
						taskid: this.taskid,
						
					}
				}).then((res) => {
					const {
						result
					} = res
					console.log("success" + result)
					// this.info=result.data
					this.task_course = result.data[0].task_course
					this.task_ddl = result.data[0].task_ddl
					this.task_title = result.data[0].task_title
					this.task_content = result.data[0].task_content
					
					console.log(this.id)
					// this.name=result.data.name
					// this.tel=result.data.tel
					// this.email=result.data.email
				})
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
		border:#006400;
		border-radius: 20rpx;
		border-style:groove;
		margin: 20rpx;
	}
	
	#task_content{
		height: 900rpx;
	}
	
	#submit{
		margin: 40px;
		
	}
	
</style>
