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
				</view>
			</view>
			<view class="detail">
				邮箱：
				<view class="detailvalue">{{email}}</view>
			</view>
		</view>
		

			<button @click="edit()" class="btn">编辑</button>




	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: "",
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
		methods: {
			edit: function() {
				uni.navigateTo({
					url: '../editI/editI'
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


					this.id = result.id,
						this.name = result.data[0].name,
						this.idnum = result.data[0].idnum,
						this.tel = result.data[0].tel,
						this.email = result.data[0].email



				})
			}
		}
	}
</script>

<style>
	.content {
		margin-top: 30rpx;

	}

	.detail {
		font-size: 40rpx;
		margin-top: 20rpx;
		margin-right: 30rpx;
		margin-left: 30rpx;
		padding: 20rpx;
		background-color: #F2F2F2;
		border-radius: 20rpx;

	}

	.detailvalue {
		text-decoration: underline;
		color: #006400;
	}

	.btn {
		width: 400rpx;
		display: block;
		margin: 40rpx auto;
		background-color:#7CCD7C;
		color: #FFFFFF;
	}

	

	
</style>
