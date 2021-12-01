<template>
	<view>
		<view class="content">
			<view class="detail">
				身份：
				<view class="detailvalue">{{id}}</view>
			</view>
			<view class="detail">
				姓名：
				<view class="detailvalue">{{name}}</view>
			</view>
			<view class="detail">
				教工号/学号：
				<view class="detailvalue">{{idnum}}</view>
			</view>
			<view class="detail">
				手机号：
				<view class="detailvalue">{{tel}}
			</view></view>
			<view class="detail">
				邮箱：
				<view class="detailvalue">{{email}}</view>
			</view>
		</view>
		<view class="decide">
			<view class="button" @click="edit()">
				<button>编辑</button>
			</view>
		</view>
		
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				id:"stu",
				name:"wdz",
				idnum:"31901236",
				tel:"17858422671",
				email:"91272222@qq.com"
			}
		},
		methods: {
			edit:function(){
				uni.navigateTo({
					url:'../editI/editI'
				})
			}
		}
	}
</script>

<style>
	.content{
		margin-top: 30rpx;
		margin-left: 40rpx;
	}
	.detail{
		font-size:60rpx;
		margin-top: 20rpx;
	}
	.detailvalue{
		text-decoration: underline;
		color: #006400;
	}
	.button{
		width: 400rpx;
		display:block;
		margin:40rpx auto	
	}
	.decide{
		margin-top: 120rpx;
	}
</style>
