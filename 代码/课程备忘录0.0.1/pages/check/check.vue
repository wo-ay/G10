<template>
	
	<view>
		<view >
			<uni-list >
				<uni-list >
					
					<view v-for="item in list" @click="todetail(item.data)">
						<uni-list-item :title="item.id" :note="item.idnum" ></uni-list-item>
					</view>
				    
				</uni-list>
		        
		    </uni-list>
		</view>
		
		
		
		<!-- <unicloud-db v-slot:default="{data, loading, error, options}" collection="check" field="idnum" :getone="true" where="name=='wdzz'">
		  <view>
		    {{ data}}
		  </view>
		</unicloud-db> -->
		<view class="obtain">
			<button @click="obtain()"> 获取申请列表</button>
		</view>
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
					
				]
			}
		},
		
		methods: {
			todetail: function(){
				// console.log(data)
				this.$emit("click",event);
				uni.navigateTo({
					url:'../check_detail/check_detail'
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
			}
		},
		
	}
</script>

<style>
	.checkitem{
		display:inline-block;
	}
	.obtain{
		margin: 100rpx auto;
		width: 400rpx;
	}
</style>
