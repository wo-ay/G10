<template>
	<view>
		
		<view >
			<uni-list >
				<view v-for="item in list" @click="todetail()">
					<uni-list-item :title="'闹钟时间' + item.data" note="提醒内容" ></uni-list-item>
				</view>
		        
		    </uni-list>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list:[
					{
						index:1,
						data:1
					},
					{
						index:2,
						data:2
					},
					{
						index:3,
						data:3
					},
					{
						index:4,
						data:4
					},
					{
						index:5,
						data:5
					}
					
				]
			}
		},
		
		methods: {
			todetail: function(){
				console.log("test")
				uni.navigateTo({
					url:'../alarm_detail/alarm_detail'
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