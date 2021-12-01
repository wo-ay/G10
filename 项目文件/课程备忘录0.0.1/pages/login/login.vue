<template>
	<view class="zai-box">
		<image src="../../static/logo.jpg" mode='aspectFit' class="zai-logo"></image>
		
		<view class="zai-form">
			<select-lay :zindex="1211" :value="tval" name="name" placeholder="请选择身份" :options="datalist"
			             @selectitem="selectitem" id="select" >
			</select-lay>
			<view class="title">实时获取输入值：{{inputrole}},{{inputid}},{{inputpwd}}</view>
			<!-- <view class="role" v-if="inputrole==t">身份：老师</view>
			<view class="role" v-if="inputrole==s">身份：学生</view>
			<view class="role" v-if="inputrole==m">身份：管理员</view> -->
			<!-- <input class="zai-input" @input="onKeyInputidnum" placeholder-class placeholder="请输入教工号/学号"  v-model="inputteacherid"/> -->
			<input class="zai-input"  placeholder-class placeholder="请输入教工号/学号"  v-model="inputid"/>
			<input class="zai-input" placeholder-class password placeholder="请输入密码" v-model="inputpwd"/>
			
			<view class="zai-label-forget">忘记密码？</view>
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
				arrays: ['---请选择---','教师','学生','管理员'],
				index:0,
				inputid: '',
				inputpwd:'',
				inputrole:'',
				statu:'',
				data:'',
				msg:'',
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
			                    // console.log(item)
			                    if (index >= 0) {
			                        this.tval = item.value;
									this.inputrole = item.value;
			                    } else {
			                        this.tval = ""
			                    }
			},
			// onKeyInputidnum: function(event) {
			//     //    this.inputteacherid = event.target.value
			// 	   // console.log("test-------",this.inputValue)
			// },
			login(){
				
				
				
			  //   uni.request({
			  //       url: 'http://192.168.16.110:8080/login', //仅为示例，并非真实接口地址。
					// method:'POST',
			  //       data: {
			  //           identity:this.inputrole,
					// 	id:this.inputid,
					// 	pwd:this.inputpwd
			  //       },
			  //       header: {
			  //           'Content-Type': 'application/json' //自定义请求头信息
			  //       },
			  //       success: (res) => {
					// 	this.statu=res.statu;
			  //           console.log(this.statu);
			  //           // this.text = 'request success';
			  //       }
			  //   });
				uniCloud.callFunction({
					name:"login",
					data:{
						// name:"wdz",
						// age:"18",
						name:this.inputid,
						age:this.inputpwd,
					},
					success(res) {
						console.log("成功返回结果为",res)
					},
					fail(res) {
						console.log("失败返回结果为",res)
					},
				
				})
				
				if(this.inputid == ''){
					uni.showToast({
						title: '请输入教工号/学号',
						icon: 'none',
						duration:1000
					});
					return false;
				}
				if(this.inputpwd == ''){
					uni.showToast({
						title: '请输入密码',
						icon: 'none',
						duration:1000
					});
					return false;
				}

			},

		},
		components:{
			selectlay
		}
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
		margin-top: 100upx;
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
		margin-top: 130rpx;
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}
	.zai-label-forget{
		margin-top: -5rpx;
		padding: 60upx 0;
		text-align: center;
		font-size: 30upx;
		color: #a7b6d0;
	}
	.zai-btn{
		top: 160rpx;
		background: #228B22;
		color: #fff;
		border: 0;
		border-radius: 100upx;
		font-size: 36upx;
	}
	.zai-btn:after{
		border: 0;
	}
	/*按钮点击效果*/
	.zai-btn.button-hover{
		transform: translate(1upx, 1upx);
	}
</style>
