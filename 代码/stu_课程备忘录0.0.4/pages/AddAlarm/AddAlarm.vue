<template>
	<view class = "all">
		
		<view class="main" >
			<view class="alarm" id="alarm_title">
				<input class = "alarm_input" placeholder="闹钟标题" v-model="alarm_title"></input>
				<!-- <input v-model="inputSerialNum" style="padding: 10rpx;font-size: 30rpx;" class="uni-input" focus placeholder="请输入值" /> -->
			</view>
		
			<view class="alarm" id="alarm_content">
				<textarea class = "alarm_input" placeholder="请输入闹钟内容" v-model="alarm_content"></textarea>
			</view>
			
			<long-date
			    type="day" 
			    getDayNum="10" 
			    :openStatus="true" 
			    startTime="2021-10-01"
			    endTime="2022-06-01" 
			    chooesMaxDay="8"
			    @select="Time">
			</long-date>
			
			
			<view id="submit">
				<button type="primary" @click="addAlarm()">添加</button>
			</view>
			
		</view>
		
		
	</view>
</template>

<script>
		//导入子组件
	import longDate from "@/components/long-date/long-date.vue"

	export default {
		components: {
			longDate
		},
		data() {
			return {
				alarm_title:"",
				alarm_content:"",
				dateTime : '',
				user_id:"",
				
			}
		},
		onLoad() {
			this.user_id = uni.getStorageSync("globalUser")
		},
		methods: {
			Time(val) {
				//console.log(1)
				this.dateTime = val
				//console.log(val)
			},
			
			addAlarm() {
				var alarmMills = new Date(this.dateTime).getTime()
				var nowMills = new Date().getTime()
				console.log(alarmMills)
				console.log(nowMills)
				if (alarmMills - nowMills < 0) {
					uni.showToast({
						title: '闹钟时间不能小于当前时间',
						icon: 'none',
						duration:1000
					})
					return false;
				}
				
				uniCloud.callFunction({
					name:"addAlarm",
					data:{
						alarm_title:this.alarm_title,
						alarm_content:this.alarm_content,
						dateTime:this.dateTime,
						user_id:this.user_id
					},
					success(res) {
						console.log(res)
					},
					fail(res) {
						console.log(res)
					}
				})
				
				uni.switchTab({
					url:'../Alarm/Alarm'
				})
			}
		}
	}
</script>

<style>
	
	.main{
		background-color: #FFFFFF;
		height: 1200rpx;
		margin-top: 30px;
	}
	.alarm{
		background-color: #F2F2F2;
		height: 70rpx;
		/* border:#c9daf8; */
		border-radius: 20rpx;
		/* border-style:groove;*/
		margin-top: 30rpx;
		margin-left: 20rpx;
		margin-right: 20rpx;
		padding-left: 30rpx;
		padding-right: 30rpx;
		padding-top: 15rpx;
		
	}
	.alarm_input {
		font-size: 38rpx;
	}
	
	#alarm_title {
		
	}
	
	#alarm_content{
		height: 200rpx;
	}
	
	#submit{
		margin: 40px;
		
	}
	
</style>
