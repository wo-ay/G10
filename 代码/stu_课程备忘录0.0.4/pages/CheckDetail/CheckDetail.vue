<template>
	<view>


		<!-- <view class="content">
			<view class="detail">
				身份：
				<view class="detailvalue">{{this.id}}</view>
			</view>
			<view class="detail">
				姓名：
				<view class="detailvalue">{{}}</view>
			</view>
			<view class="detail">
				教工号/学号：
				<view class="detailvalue">{{this.idNum}}</view>
			</view>
			<view class="detail">
				手机号：
				<view class="detailvalue">{{this.tel}}
				</view>
			</view>
			<view class="detail">
				邮箱：
				<view class="detailvalue">{{this.email}}</view>
			</view>
		</view>
		<view class="decide">
			<view class="button">
				<button>通过</button>
			</view>
			<view class="button">
				<button>拒绝</button>
			</view>
		</view>
 -->
		<!-- <view v-for="item in info" > -->
			<!-- <uni-list-item :title="item.id+'--'+item.name"  :note="item.idNum" ></uni-list-item> -->
			<view class="content">
				<view class="detail">
					身份：
					<view class="detailvalue">{{this.id}}</view>
				</view>
				
				<view class="detail">
					姓名：
					<view class="detailvalue">{{this.name}}</view>
				</view>
				<view class="detail">
					教工号/学号：
					<view class="detailvalue">{{this.idNum}}</view>
				</view>
				<view class="detail">
					手机号：
					<view class="detailvalue">{{this.tel}}
					</view>
				</view>
				<view class="detail">
					邮箱：
					<view class="detailvalue">{{this.email}}</view>
				</view>
			</view>
		<!-- </view> -->
		<view class="decide">
			<view class="button" >
				<button @click="pass()">通过</button>
			</view>
			<view class="button">
				<button @click="refuse()">拒绝</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: "",
				name: "",
				idNum: "",
				tel: "",
				email: "",
				info: [],
				pwd:"",
			}
		},
		
		onLoad(option) {
			this.idNum = option.idNum;
			// console.log(option)
			this.getcheckdetail(option);
		},
		methods: {
			getcheckdetail(option) {
				uniCloud.callFunction({
					name: 'checkdetailshow',
					data: {
						idNum: this.idNum
					}
				}).then((res) => {
					const {
						result
					} = res
					console.log("success" + result)
					// this.info=result.data
					this.info = result.data
					this.id = result.data[0].id
					this.name = result.data[0].name
					this.idNum = result.data[0].idNum
					this.tel = result.data[0].tel
					this.email = result.data[0].email
					this.pwd = result.data[0].pwd
					console.log(this.id)
					// this.name=result.data.name
					// this.tel=result.data.tel
					// this.email=result.data.email
				})
			},
			
			pass(){
				uniCloud.callFunction({
					name: 'passreg',
					data: {
						idNum: this.idNum
					}
				})
				
				if(this.id=="学生"){
					uniCloud.callFunction({
						name: 'addstu', // 云函数名字
						// 传输数据
						data: {
							
							name: this.name,
							idNum: this.idNum,
							tel: this.tel,
							email: this.email,
							pwd:this.pwd,
					
						},
						// 成功
						success(res) {
							console.log("success");
							uni.showToast({
								title: '注册申请发送成功',
								icon: 'none',
								duration: 1000
							});
							
						},
						//失败
						fail(e) {
							console.log(e);
						}
					})
				}
				
				if(this.id=="教师"){
					uniCloud.callFunction({
						name: 'addtea', // 云函数名字
						// 传输数据
						data: {
							
							name: this.name,
							idNum: this.idNum,
							tel: this.tel,
							email: this.email,
							pwd:this.pwd,
					
						},
						// 成功
						success(res) {
							console.log("success");
							uni.showToast({
								title: '注册申请发送成功',
								icon: 'none',
								duration: 1000
							});
							
						},
						//失败
						fail(e) {
							console.log(e);
						}
					})
				}
				
				
				uni.navigateTo({
					url:'../Check/Check'
				})
				
			},
			refuse(){
				uniCloud.callFunction({
					name: 'refusereg',
					data: {
						idNum: this.idNum
					}
				})
				uni.navigateTo({
					url:'../Check/Check'
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

	.button {
		width: 400rpx;
		display: block;
		margin: 40rpx auto
	}

	.decide {
		margin-top: 120rpx;
	}
</style>
