<template>
	<view>
		
		<view class="main">
			<view class="hint">手机号修改：</view>
			<view class="task" id="task_course">
				<textarea value="" placeholder="178584227617"></textarea>
			</view>
			<view class="hint">邮箱修改：</view>
			<view class="task" id="task_title">
				<textarea value="" placeholder="91910000@qq.com"></textarea>
			</view>
			
			<!-- <view class="task" id="task_content">
				<textarea value="" placeholder="请输入任务内容"></textarea>
			</view> -->
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
	.hint{
		margin-left: 30rpx;
	}
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
		height: 950rpx;
	}
	#submit{
		margin: 40px;
		
	}
</style>

