<template>
	<view class = "all">
		
		<view class="main" >
			<view class="Alarm" id="Alarm_title">
				<input class = "Alarm_input" placeholder="闹钟标题" v-model="Alarm_title"></input>
				<!-- <input v-model="inputSerialNum" style="padding: 10rpx;font-size: 30rpx;" class="uni-input" focus placeholder="请输入值" /> -->
			</view>
		
			<view class="Alarm" id="Alarm_content">
				<textarea class = "Alarm_input" placeholder="请输入闹钟内容" v-model="Alarm_content"></textarea>
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
				<button type="primary" @click="updateAlarm()">修改</button>
			</view>
			<view id="delete">
				<button type="primary" @click="deleteAlarm()">删除</button>
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
				_id:"",
				Alarm_title:"",
				Alarm_content:"",
				dateTime : ''
			}
		},
		
		onLoad(option) {
			this._id = option._id
			this.getAlarmDetail(option);
		},
		
		methods: {
			getAlarmDetail(option) {
				uniCloud.callFunction({
					name:'getAlarmDetail',
					data:{
						_id : this._id
					}
				}).then((res)=>{
					const {result} = res
					this.Alarm_title = result.data[0].Alarm_title
					this.Alarm_content = result.data[0].Alarm_content
					console.log(this.Alarm_title)
				})
			},
			
			Time(val) {
				//console.log(1)
				this.dateTime = val
				//console.log(val)
			},
			
			updateAlarm() {
				//console.log("执行了吗")
				//console.log(this.dateTime)
				uniCloud.callFunction({
					name:"updateAlarm",
					data:{
						_id:this._id,
						Alarm_title:this.Alarm_title,
						Alarm_content:this.Alarm_content,
						dateTime:this.dateTime
					},
					success(res) {
						
					},
					fail(res) {
						console.log(res)
					}
				})
				uni.switchTab({
					url:'../Alarm/Alarm'
				})
				
			},
			
			deleteAlarm() {
				uniCloud.callFunction({
					name:"deleteAlarm",
					data:{
						_id:this._id
					},
					success(res) {
						console.log(res)
					},
					fail(res) {
						console.log(res)
					}
				}),
				
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
	.Alarm{
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
	.Alarm_input {
		font-size: 38rpx;
	}
	
	#Alarm_title {
		
	}
	
	#Alarm_content{
		height: 200rpx;
	}
	
	#submit{
		margin-left: 50px;
		margin-right:50px;
		margin-top: 30px;
		
	}
	#delete{
		margin-left: 50px;
		margin-right:50px;
		margin-top: 20px;
		
	}
	
</style>
