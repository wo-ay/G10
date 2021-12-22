<script src="http://at.alicdn.com/t/font_2971072_04vv2tcbr43c.js">
</script>

<template>

	<view>
		<view class="content">
			


			<view class="detail">

				<svg class="icon" aria-hidden="true">
					<use xlink:href="#icon-shenfenxuanze"></use>
				</svg>

			
				<view class="detailvalue">身份：{{id}}</view>
			</view>
			<view class="detail">
				<svg class="icon" aria-hidden="true">
					<use xlink:href="#icon-geren1"></use>
				</svg>
				
				<view class="detailvalue">
					<view class="detailvalue">姓名：{{name}}</view>
					
				</view>
			</view>
			<view class="detail">
				<svg class="icon" aria-hidden="true">
					<use xlink:href="#icon-shenfen"></use>
				</svg>
				
				<view class="detailvalue">工号：{{idnum}}</view>
			</view>
			<view class="detail">
				<svg class="icon" aria-hidden="true">
					<use xlink:href="#icon-shouji1"></use>
				</svg>
				
				<view class="detailvalue">手机：{{tel}}
				</view>
			</view>
			<view class="detail">
				<svg class="icon" aria-hidden="true">
					<use xlink:href="#icon-youxiang3"></use>
				</svg>
				
				<view class="detailvalue">邮箱：{{email}}</view>
			</view>
		</view>


		<button @click="edit()" class="btn">编辑</button>




	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: "教师",
				name: "",
				idnum: '',
				tel: "",
				email: "",

			}
		},
		onShow() {
			this.idnum = uni.getStorageSync("globalUser");
			console.log(this.idnum)
			this.get_tea_info()
		},
		methods: {
			edit: function() {
				uni.navigateTo({
					url: '../editI/editI'
				})
			},

			get_tea_info: function() {
				uniCloud.callFunction({
					name: "get_tea_info",
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
