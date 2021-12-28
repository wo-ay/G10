<template>
	
	<view>
		<view class="checklist">
			<uni-list >
				<uni-list >
					
					<view v-for="item in checktable" >
						<view v-if="item.ifpass==1">  <!-- ifpass 0表示未审核，1表示通过 -1表示未通过 -->
							<uni-list-item :title="item.id+'--'+item.name"  :note="item.idnum" rightText="已通过"></uni-list-item>
						</view>
						<view v-if="item.ifpass==2">  <!-- ifpass 0表示未审核，1表示通过 -1表示未通过 -->
							<uni-list-item :title="item.id+'--'+item.name"  :note="item.idnum" rightText="已拒绝"></uni-list-item>
						</view>
					</view>
				    
				</uni-list>
		        
		    </uni-list>
			
			
			<!-- <unicloud-db v-slot:default="{data, loading, error, options}" collection="check"  :getone="true" >
			  <view>
			    {{ data}}
			  </view>
			</unicloud-db> -->
		</view>
		
		<!-- <view class="obtain">
			<button @click="obtain()"> 获取申请列表</button>
		</view> -->
		<image src="../../static/bg2.png" class="bg"></image>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list:[
					{
						id:31901236,
						idnum:"学生"
					},
					{
						id:31901245,
						idnum:"学生"
					},
					{
						id:31901246,
						idnum:"学生"
					},
					{
						id:31901222,
						idnum:"学生"
					},
					{
						id:31901222,
						idnum:"学生"
					}
					
				],
				checktable:[]
			}
		},
		
		onLoad(){
			this.getchecktable()
		},
		onPullDownRefresh() {
			//监听下拉刷新动作的执行方法，每次手动下拉刷新都会执行一次
			console.log('refresh');
			this.getchecktable();
			
		
			setTimeout(function() {
				uni.stopPullDownRefresh(); //停止下拉刷新动画
			}, 1000);
		},
		methods: {
			todetail(idnum){
				// console.log(data)
				this.$emit("click",event);
				uni.navigateTo({
					url:'../check_detail/check_detail?idnum='+idnum
				})
				
			},
			obtain: function(){
				const db = uniCloud.database();
				db.collection("check").add({
					"idnum":"111",
					"name":"wddddd",
					
				})
			},
			handleLoad(date){
				this.list=date
			},
			getchecktable(){
				uniCloud.callFunction({
					name:'checkshow'
				}).then((res)=>{
					const {result}=res
					this.checktable=result.data
				})
			}
		},
		
		
	}
</script>

<style>
	.bg{
		width: 55%;
		margin: 300rpx auto;
	}
	.obtain{
		margin: 100rpx auto;
		width: 400rpx;
		margin-bottom: 100rpx;
	}
	.checklist{
		/* display: flex; */
	}
</style>
