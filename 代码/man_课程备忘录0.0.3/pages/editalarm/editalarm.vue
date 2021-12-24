<template>
	<view>
		
		<view class="main">
			<view class="task" id="task_course">
				<textarea value="" placeholder="请输入课程名" class="text"></textarea>
			</view>
			<view class="task" id="task_title">
				<textarea value="" placeholder="请输入任务" class="text"></textarea>
			</view>
			
			<view class="task" id="task_content">
				<textarea value="" placeholder="请输入任务内容" class="text"></textarea>
			</view>
		</view>
		
		<view id="submit">
			<button>提交</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
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
		background-color: #F2F2F2;
		border-radius: 20rpx;
		border-style:groove;
		margin: 20rpx;
	}
	.text{
		
		margin-top: 15rpx;
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
