<template>
	<view>
		
		<view class="main">
			<view class="task" id="task_course">
				<textarea value="" placeholder="请输入课程名(选填)"></textarea>
			</view>
			
			<long-date 
			    type="between" 
			    getDayNum="10" 
			    :openStatus="true" 
			    startTime="2018-10-21" 
			    endTime="2019-06-01" 
			    chooesMaxDay="8"
			    @select="Time">
			</long-date>
			<!-- <view class="task" id="task_title">
				<textarea value="" placeholder="请输入任务"></textarea>
			</view> -->

			<view class="task" id="task_content">
				<textarea value="" placeholder="请输入提醒内容"></textarea>
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
			Time(val) {
				console.log(val);
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
		height: 750rpx;
	}
	#submit{
		margin: 40px;
		
	}
</style>
