<template>
	<view>
		<!-- <view class="rate">
			<cmd-circle cid="circle30" type="dashboard" :percent="this.rate" style="margin-top: 10px;margin-left: 10rpx;"></cmd-circle>
		</view>
		 -->
		    
		<view class="main" >
			<link rel="stylesheet" href="http://at.alicdn.com/t/font_2971072_u0os1dwc1wk.css">

			<view class="task" id="task_course">
				<i class="iconfont icon-wodekecheng" style="margin-top: 10rpx;margin-right: 10px;"></i>

				<textarea :value="'任务课程   '+this.task_course" placeholder="任务课程" ></textarea>
			</view>
			
			<view class="task" id="task_ddl">
				<i class="iconfont icon-59" style="margin-top: 10rpx;margin-right: 10px;"></i>
				<textarea :value="'截止日期   '+this.task_ddl" placeholder="截止日期"></textarea>
			</view>
			<view class="task" id="task_title">
				<i class="iconfont icon-biaoti" style="margin-top: 10rpx;margin-right: 10px;"></i>
				<textarea :value="'任务标题   '+this.task_title" placeholder="任务标题"></textarea>
			</view>
			
			<view class="task" id="task_content">
				<i class="iconfont icon-fuwuneirong" style="margin-top: 10rpx;margin-right: 10px;"></i>
				<textarea :value="'任务内容   '+this.task_content" placeholder="任务内容"></textarea>
			</view>
			
			<view id="submit">
				<button style="border-radius: 10rpx;" @click="finish()">完成</button>
			</view>
			
		</view>
		
		
	</view>
</template>

<script>
	import cmdCircle from "@/components/cmd-circle/cmd-circle.vue"
	export default {
		components: {cmdCircle},
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
				task_content:"",
				rate:""
				
			}
		},
		 
		onLoad(option) {
			this.taskid = option.taskid;
			// console.log(option)
			console.log(option.taskid)
			this.gettaskdetail();
			// this.rate= option.rate
		},
		methods: {
			finish: function(){
				uniCloud.callFunction({
					name:"finish_task",
					data:{
						task_id:this.taskid
					}
				}).then((res) => {
					uni.showToast({
						title: '任务完成',
						icon: 'success',
						duration: 1000
					})
				})
			},
			
			gettaskdetail:function(){
				uniCloud.callFunction({
					name: 'taskdetailshow',
					data: {
						taskid: this.taskid,
						id:'学生'
					}
				}).then((res) => {
					const {
						result
					} = res
					// console.log("success" + result)
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
		margin-top: 40rpx;
	}
	.rate{
		background: url(../../components/rig_login/static/zaizai-login/register.png) repeat fixed right;
	}
	.task{
		height: 80rpx;
		/* border:#006400; */
		background-color: #F2F2F2;
		border-radius: 20rpx;
		/* border-style:groove; */
		margin: 15rpx;
		padding-top: 22rpx;
		padding-left: 8rpx;
		display: flex;
		flex-direction: row;
	}
	
	#task_content{
		height: 900rpx;
	}
	
	#submit{
		margin: 40px;
		
	}
	.icon { 
		
		width: 1em; 
		height: 1em; 
		vertical-align: -0.15em; 
		fill: currentColor; 
		overflow: hidden; 
	} 
	
</style>
