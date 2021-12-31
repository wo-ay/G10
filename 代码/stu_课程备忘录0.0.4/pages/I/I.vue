
</script>

<template>

	<view>
		<view class="content">
			<link rel="stylesheet" href="//at.alicdn.com/t/font_2971072_u0os1dwc1wk.css">


			<view class="detail">

				<i class="iconfont icon-shenfen" style="margin-top: 10rpx;margin-right: 10px;"></i>

			
				<view class="detailvalue">身份：{{id}}</view>
			</view>
			<view class="detail">
				<i class="iconfont icon-geren1" style="margin-top: 10rpx;margin-right: 10px;"></i>
				
				<view class="detailvalue">
					<view class="detailvalue">姓名：{{name}}</view>
					
				</view>
			</view>
			<view class="detail">
				<i class="iconfont icon-gonghao" style="margin-top: 10rpx;margin-right: 10px;"></i>
				
				<view class="detailvalue">学号：{{idnum}}</view>
			</view>
			<view class="detail">
				<i class="iconfont icon-shouji1" style="margin-top: 10rpx;margin-right: 10px;"></i>
				
				<view class="detailvalue">手机：{{tel}}
				</view>
			</view>
			<view class="detail">
				<i class="iconfont icon-youxiang3" style="margin-top: 10rpx;margin-right: 10px;"></i>
				
				<view class="detailvalue" >邮箱：{{email}}</view>
			</view>
		</view>


		<button @click="edit()" class="btn">编辑</button>




	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: "学生",
				name: "",
				idnum: '',
				tel: "",
				email: "",

			}
		},
		onShow() {
			this.idnum = uni.getStorageSync("globalUser");
			console.log(this.idnum)
			this.get_stu_info()
		},
		onPullDownRefresh() {
			//监听下拉刷新动作的执行方法，每次手动下拉刷新都会执行一次
			console.log('refresh');
			this.get_stu_info()
		
			setTimeout(function() {
				uni.stopPullDownRefresh(); //停止下拉刷新动画
			}, 1000);
		},
		
		methods: {
			edit: function() {
				uni.navigateTo({
					url: '../EditI/EditI'
				})
			},

			get_stu_info: function() {
				uniCloud.callFunction({
					name: "get_stu_info",
					data: {

						idnum: this.idnum,

					},
				}).then((res) => {
					const {
						result
					} = res


					this.name = result.data[0].name,
						this.idnum = result.data[0].idnum,
						this.tel = result.data[0].tel,
						this.email = result.data[0].email



				})
			}
		}
	}
</script>

<style type="text/css">
	.icon {
		width: 1.2em;
		height: 1.2em;
		vertical-align: -0.15em;
		fill: currentColor;
		overflow: hidden;
		margin-right: 5px;
		
	}

	.content {
		margin-top: 30rpx;

	}
	.detailvalue_item1{
		float: left;
	}
	.detailvalue_item2{
		float: ri;
	}
	.detail {
		font-size: 40rpx;
		margin-top: 20rpx;
		margin-right: 30rpx;
		margin-left: 30rpx;
		padding: 20rpx;
		/* background-color: #F2F2F2; */
		background-color: #F2F2F2;
		border-radius: 20rpx;
		display: flex;
		flex-direction: row;
	}

	.detailvalue {
		/* text-decoration: underline; */
		color: #3F536E;
	}

	.btn {
		width: 400rpx;
		display: block;
		margin: 40rpx auto;
		background-color: #7CCD7C;
		color: #FFFFFF;
	}
</style>
