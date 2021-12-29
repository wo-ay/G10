<template>
	<view class="zai-box">
		<image src="../../static/logo.jpg" mode='aspectFit' class="zai-logo"></image>

		<view class="zai-form">
			<!-- <select-lay :zindex="1211" :value="tval" name="name" placeholder="请选择身份" :options="datalist"
				@selectitem="selectitem" id="select">
			</select-lay> -->
			<!-- <view class="title">实时获取输入值：{{inputrole}},{{inputid}},{{inputpwd}}</view> -->
			<!-- <view class="role" v-if="inputrole==t">身份：老师</view>
			<view class="role" v-if="inputrole==s">身份：学生</view>
			<view class="role" v-if="inputrole==m">身份：管理员</view> -->
			<!-- <input class="zai-input" @input="onKeyInputidnum" placeholder-class placeholder="请输入教工号/学号"  v-model="inputteacherid"/> -->
			<input class="zai-input" placeholder-class placeholder="请输入教工号" v-model="inputidnum" />
			<input class="zai-input" placeholder-class password placeholder="请输入密码" v-model="inputpwd" />

			<!-- <view class="zai-label-forget">忘记密码？</view> -->
			<button class="zai-btn" @click="login()">立即登录</button>
			<navigator url="../register/register" hover-class="none" class="zai-label">还没有账号？点此注册.</navigator>


		</view>
	</view>
</template>

<script>
	import selectlay from "../../components/uni-transition/uni-transition.vue"
	export default {
		data() {
			return {
				//模拟数据列表
				datalist: [],
				//模拟初始数据
				tval: "value2",
				arrays: ['---请选择---', '教师', '学生', '管理员'],
				index: 0,
				inputid: '教师',
				inputidnum: '',
				inputpwd: '',
				statu: '',
				data: '',
				msg: '',
				ifpass: '',
				id:'教师'
			}
			idnum: ''
		},
		onLoad() {
			this.idnum = uni.getStorageSync("globalUser");
			console.log(this.idnum)
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
				}
			];
		},

		methods: {
			selectitem(index, item) {
				// console.log(item)
				if (index >= 0) {
					this.tval = item.value;
					this.inputid = item.value;
				} else {
					this.tval = ""
				}
			},
			// onKeyInputidnum: function(event) {
			//     //    this.inputteacherid = event.target.value
			// 	   // console.log("test-------",this.inputValue)
			// },
			login() {


				// if (this.inputrole == '') {
				// 	uni.showToast({
				// 		title: '请选择身份',
				// 		icon: 'none',
				// 		duration: 1000
				// 	});
				// 	return false;
				// }
				if (this.inputid == '') {
					uni.showToast({
						title: '请输入教工号/学号',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.inputpwd == '') {
					uni.showToast({
						title: '请输入密码',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				uniCloud.callFunction({
					name: "judge",
					data: {

						id: this.inputid,
						idnum: this.inputidnum,
						pwd: this.inputpwd,
					}
				}).then((res) => {
					const {
						result
					} = res
					this.ifpass = result.ifpass
					if (this.ifpass == 1) {
						console.log("okok")
						uni.setStorageSync("globalUser", this.inputidnum);
						uni.setStorageSync("globalUserId", this.inputid);
						if (this.inputid == "学生") {
							uni.switchTab({
								url: '../course/course?idnum=' + this.inputidnum
							});
						}
						else if(this.inputid == "教师"){
							uni.switchTab({
								url: '../teacher_course/teacher_course?idnum=' + this.inputidnum
							});
						}
						else if(this.inputid == "管理员"){
							uni.navigateTo({
								url: '../check/check' 
							});
						}
					} else {
						console.log("nonoo"),
							uni.showToast({
								title: '输入的账号或密码不正确',
								icon: 'none',
								duration: 1000
							});
					}


				})



			},

		},
		components: {
			selectlay
		}
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
		margin-top: 100upx;
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
		margin-top: 130rpx;
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}

	.zai-label-forget {
		margin-top: -5rpx;
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}

	.zai-btn {
		top: 160rpx;
		background: #228B22;
		color: #fff;
		border: 0;
		border-radius: 100upx;
		font-size: 36upx;
	}

	.zai-btn:after {
		border: 0;
	}

	/*按钮点击效果*/
	.zai-btn.button-hover {
		transform: translate(1upx, 1upx);
	}
</style>
