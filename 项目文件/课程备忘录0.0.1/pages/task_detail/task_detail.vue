<template>
	<view>
		
		<view class="main" >
			<view class="task" id="task_course">
				<textarea value="软件工程" placeholder="请输入指定任务的课程"></textarea>
			</view>
			
			<view class="task" id="task_ddl">
				<textarea value="2021-12-01" placeholder="请输入截止日期"></textarea>
			</view>
			<view class="task" id="task_title">
				<textarea value="课程预备作业" placeholder="请输入任务标题"></textarea>
			</view>
			
			<view class="task" id="task_content">
				<textarea value="完成课程预备作业" placeholder="请输入任务内容"></textarea>
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
				list:[
					{
						id:1,
						course:软件工程,
						title:课程预备项目,
						content:完成预备作业,
					},
					{
						id:2,
						course:高级程序设计,
						title:实验3,
						content:完成实验3,
					},
					{
						id:3,
						course:项目管理,
						title:大作业1,
						content:完成大作业,
					},
					{
						id:4,
						course:项目实践,
						title:课程实践项目,
						content:完成大作业,
					}
					
					
				]
			}
		},
		 
		
		methods: {
			

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
