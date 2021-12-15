<template>
	<view>
		<view >	
<!-- 			<view id="1">
				<button type="primary" @click="loadAlarm()">加载</button>
			</view> -->
			<uni-list>
				<uni-list >
					<view v-for="item in list" @click="toDetail(item._id)">
						<uni-list-item :title="item.dateTime" :note="item.alarm_title" ></uni-list-item>
					</view>
				</uni-list>	
			</uni-list>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user_id:"",
				list:[]
			}
		},
		
		onLoad(){
			this.user_id = uni.getStorageSync("globalUser");
			this.loadAlarm()
		},
		
		onShow(){
			this.loadAlarm()
		},
		
		methods: {
			
			toDetail(_id){
				//监听
				this.$emit("click",event);
				uni.navigateTo({
					url:'../alarm_detail/alarm_detail?_id=' + _id
					//url:'../alarm_detail/alarm_detail?alarm_title=' + i + '&alarm_content=' + c
					//url:'../alarm_detail/alarm_detail?alarm_title=' + list[index].alarm_title + '&alarm_content=' + list[index].alarm_content
				})
			},
			
			loadAlarm() {
				uniCloud.callFunction({
					name:"loadAlarm",
					data:{
						user_id:this.user_id
					}
				}).then((res)=>{
					const {result} = res
					this.list = result.data
				})
			}
		},
		
		onNavigationBarButtonTap:function(e){
		 //    console.log(e.text);//提交
			// console.log(e.fontSize);//16px
			uni.navigateTo({
				url:'../addalarm/addalarm'
			})
		}
	}
</script>

<style>

</style>