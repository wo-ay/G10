<template>
	<view class="zai-box">
		<image src="../../static/logo.jpg" mode='aspectFit' class="zai-logo"></image>

		<view class="zai-form">
			<select-lay :zindex="1211" :value="tval" name="name" placeholder="请选择身份" :options="datalist"
			             @selectitem="selectitem" id="select">
			</select-lay>
			<input class="zai-input" placeholder-class placeholder="请输入教工/学生姓名" />
			<input class="zai-input" placeholder-class placeholder="请输入教工号/学号" />
			<input class="zai-input" placeholder-class placeholder="请输入验证手机号" />
			<input class="zai-input" placeholder-class placeholder="请输入验证邮箱" />
			<input class="zai-input" placeholder-class password placeholder="请输入密码"/>
			<input class="zai-input" placeholder-class password placeholder="请再输入一次密码"/>
			<button class="zai-btn">立即注册</button>
			<navigator url="../zaizai-login/index" open-type='navigateBack' hover-class="none" class="zai-label">已有账号，点此去登录.</navigator>
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
				arrays: ['---请选择---','教师','学生','管理员'],
				index:0
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
		        }
		    ];
		},
		methods: {
			selectitem(index, item) {
			                    console.log(item)
			                    if (index >= 0) {
			                        this.tval = item.value;
			                    } else {
			                        this.tval = ""
			                    }
			},
			
			
			
		
		},
	}
</script>

<style>
	.zai-box{
		padding: 0 100upx;
		position: relative;
	}
	.zai-logo{
		top: 20rpx;
		width: 100%;
		height: 310upx;
	}
	.zai-title{
		position: absolute;
		top: 0;
		line-height: 360upx;
		font-size: 68upx;
		color: #fff;
		text-align: center;
		width: 100%;
		margin-left: -100upx;
	}
	.zai-form{
		margin-top: 60upx;
	}
	.zai-input{
		background: #e2f5fc;
		margin-top: 30upx;
		border-radius: 100upx;
		padding: 20upx 40upx;
		font-size: 36upx;
	}
	.input-placeholder, .zai-input{
		color: #94afce;
	}
	.zai-label{
		margin-top: 10rpx;
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}
	.zai-btn{
		top: 10rpx;
		background: #228B22;
		color: #fff;
		border: 0;
		border-radius: 100upx;
		font-size: 36upx;
		margin-top: 60upx;
	}
	.zai-btn:after{
		border: 0;
	}
	/*按钮点击效果*/
	.zai-btn.button-hover{
		transform: translate(1upx, 1upx);
	}
</style>
