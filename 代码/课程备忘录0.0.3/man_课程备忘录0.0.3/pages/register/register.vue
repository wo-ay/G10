<template>
	<view class="zai-box">
		<image src="../../static/logo.jpg" mode='aspectFit' class="zai-logo"></image>

		<view class="zai-form">

			<select-lay :zindex="1211" :value="tval" name="name" placeholder="请选择身份" :options="datalist"
				@selectitem="selectitem" id="select">
			</select-lay>
			<input class="zai-input" placeholder-class placeholder="请输入教工/学生姓名" v-model="regname" />
			<input class="zai-input" placeholder-class placeholder="请输入教工号/学号" v-model="regidnum" />
			<input class="zai-input" placeholder-class placeholder="请输入验证手机号" v-model="regtel" />
			<input class="zai-input" placeholder-class placeholder="请输入验证邮箱" v-model="regemail" />
			<input class="zai-input" placeholder-class password placeholder="请输入密码" v-model="regpwd" />
			<input class="zai-input" placeholder-class password placeholder="请再输入一次密码" v-model="regpwd2" />
			<!-- <view>"身份："+{{regid}} +"name:"+{{regname}}</view> -->
			<button class="zai-btn" @click="reg()">立即注册</button>
			<navigator url="../zaizai-login/index" open-type='navigateBack' hover-class="none" class="zai-label">
				已有账号，点此去登录.</navigator>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//模拟数据列表
				datalist: [],
				//模拟初始数据
				tval: "value2",
				arrays: ['---请选择---', '教师', '学生', '管理员'],
				index: 0,
				regid: "",
				regname: "",
				regidnum: "",
				regtel: "",
				regemail: "",
				regpwd: "",
				regpwd2: "",
				ifpass:'',
			}
		},
		onReady() {
			this.datalist = [{
					label: "教师",
					value: "教师"
				},
				{
					label: "学生",
					value: "学生"
				},
				{
					label: "管理员",
					value: "管理员"
				},
			];
		},
		methods: {
			selectitem(index, item) {
				console.log(item)
				if (index >= 0) {
					this.tval = item.value;
					this.regid = item.value;
				} else {
					this.tval = ""
				}
			},
			reg() {
				if (this.regid == '') {
					uni.showToast({
						title: '选择身份',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.regname == '') {
					uni.showToast({
						title: '请输入姓名',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.regtel == '') {
					uni.showToast({
						title: '请输入手机号',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.regtel.length != 11) {
					uni.showToast({
						title: '请输入正确格式的手机号',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				
				if (this.regemail == '') {
					uni.showToast({
						title: '请输入邮箱',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				
				if (this.regpwd == '') {
					uni.showToast({
						title: '请输入密码',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.regpwd2 == '') {
					uni.showToast({
						title: '请再输入一次密码',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				
				if (this.regpwd != this.regpwd2) {
					uni.showToast({
						title: '两次输入的密码不一致',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				uniCloud.callFunction({
					name:"ifreg",
					data:{
						
						id:this.regid,
						idnum:this.regidnum,
						// pwd:this.inputpwd,
					}
				}).then((res)=>{
					const {result}=res
					this.ifpass=result.ifpass
					if(this.ifpass==1){
						console.log("该教工号/学号已被注册"),
						uni.showToast({
							title: '该教工号/学号已被注册',
							icon: 'none',
							duration: 1000
						});
						
					}
				})
				if(this.ifpass!=1){
					uniCloud.callFunction({
						name: 'reg', // 云函数名字
						// 传输数据
						data: {
							id: this.regid,
							name: this.regname,
							idnum: this.regidnum,
							tel: this.regtel,
							email: this.regemail,
							pwd:this.regpwd,
					
						},
						// 成功
						success(res) {
							console.log("success");
							uni.showToast({
								title: '注册申请发送成功',
								icon: 'none',
								duration: 1000
							});
							uni.navigateTo({
								url: "../login/login"
							})
						},
						//失败
						fail(e) {
							console.log(e);
						}
					})
				}
				// uniCloud.callFunction({
				// 	name: 'reg', // 云函数名字
				// 	// 传输数据
				// 	data: {
				// 		id: this.regid,
				// 		name: this.regname,
				// 		idnum: this.regidnum,
				// 		tel: this.regtel,
				// 		email: this.regemail,
				// 		pwd:this.regpwd,

				// 	},
				// 	// 成功
				// 	success(res) {
				// 		console.log("success");
				// 		uni.showToast({
				// 			title: '注册申请发送成功',
				// 			icon: 'none',
				// 			duration: 1000
				// 		});
				// 		uni.navigateTo({
				// 			url: "../login/login"
				// 		})
				// 	},
				// 	//失败
				// 	fail(e) {
				// 		console.log(e);
				// 	}
				// })
			}




		},
	}
</script>

<style>
	.zai-box {
		padding: 0 100upx;
		position: relative;
	}

	.zai-logo {
		top: 20rpx;
		width: 100%;
		height: 310upx;
	}

	.zai-title {
		position: absolute;
		top: 0;
		line-height: 360upx;
		font-size: 68upx;
		color: #fff;
		text-align: center;
		width: 100%;
		margin-left: -100upx;
	}

	.zai-form {
		margin-top: 60upx;
	}

	.zai-input {
		background: #e2f5fc;
		margin-top: 30upx;
		border-radius: 100upx;
		padding: 20upx 40upx;
		font-size: 36upx;
	}

	.input-placeholder,
	.zai-input {
		color: #94afce;
	}

	.zai-label {
		margin-top: 10rpx;
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}

	.zai-btn {
		top: 10rpx;
		background: #228B22;
		color: #fff;
		border: 0;
		border-radius: 100upx;
		font-size: 36upx;
		margin-top: 60upx;
	}

	.zai-btn:after {
		border: 0;
	}

	/*按钮点击效果*/
	.zai-btn.button-hover {
		transform: translate(1upx, 1upx);
	}
</style>
