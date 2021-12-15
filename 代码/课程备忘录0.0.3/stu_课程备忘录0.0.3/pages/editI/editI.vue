<template>
	<view>

		<view class="main">
			<view class="hint">手机号修改：</view>
			<view class="task" id="task_course">
				<textarea v-model="tel" :placeholder="this.old_tel" class="text"></textarea>
			</view>


			<view class="hint">邮箱修改：</view>
			<view class="task" id="task_title">
				<textarea v-model="email" :placeholder="this.old_email" class="text"></textarea>
			</view>

			<!-- <view class="task" id="task_content">
				<textarea value="" placeholder="请输入任务内容"></textarea>
			</view> -->
		</view>

		<view id="submit">
			<button @click="submit()">提交</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				idnum: '',
				tel: '',
				email: '',
				old_tle: '',
				old_email: '',

			}
		},
		onShow() {
			this.idnum = uni.getStorageSync("globalUser");
			console.log(this.idnum)
			this.old()
		},
		methods: {
			submit: function() {

				if (this.tel == '' && this.email == '') {
					uni.showToast({
						title: '请至少修改一项',
						icon: 'none',
						duration: 1000
					});
				} else {
					if (this.tel.length != 0) {
						if (this.tel.length != 11) {
							uni.showToast({
								title: '请输入正确的手机号格式',
								icon: 'none',
								duration: 1000
							});
						} else {
							uniCloud.callFunction({
								name: 'edit_I_tel',
								data: {
									idnum: this.idnum,
									tel: this.tel,

								}
							})
						}

					}
					if (this.email.length != 0) {
						uniCloud.callFunction({
							name: 'edit_I_email',
							data: {
								idnum: this.idnum,
								email: this.email,
								
							}
						})
					}
					
					if(this.email.length != 0 && this.tel.length == 11){
						uni.showToast({
							title: '修改成功',
							icon: 'none',
							duration: 1000
						});
					}


				}

			},
			old: function() {
				uniCloud.callFunction({
					name: 'old_I_info',
					data: {
						idnum: this.idnum
					}
				}).then((res) => {
					const {
						result
					} = res;
					this.old_tel = result.data[0].tel;
					this.old_email = result.data[0].email;
					// this.test = result.data[0].tasktitle;

				})
			}
		}
	}
</script>

<style>
	.hint {
		margin-left: 30rpx;
	}

	.main {
		margin-top: 80rpx;
	}

	.task {
		height: 80rpx;

		border-radius: 20rpx;
		background-color: #F2F2F2;
		margin: 20rpx;
	}

	#task_content {
		height: 950rpx;
	}

	#submit {
		margin: 40px;

	}

	.text {
		padding: 15rpx;
	}
</style>
