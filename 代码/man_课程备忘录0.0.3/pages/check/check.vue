<template>

	<view >
		<view class="checklist">
			<uni-list>
				<uni-list>

					<view v-for="item in checktable" @click="todetail(item.idnum)">
						<view v-if="item.ifpass==0">
							<!-- ifpass 0表示未审核，1表示通过 -1表示未通过 -->
							<uni-list-item :title="item.id+'--'+item.name" :note="item.idnum"></uni-list-item>
						</view>

					</view>

				</uni-list>

		 </uni-list>

		</view>
		
		<image src="../../static/bg1.png" class="bg"></image>


		<!-- <view class="obtain">
			<button @click="obtain()"> 获取申请列表</button>
		</view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
				checktable: []
			}
		},

		onLoad() {
			this.getchecktable()

		},
		methods: {
			todetail(idnum) {
				// console.log(data)
				// this.$emit("click", event);
				uni.navigateTo({
					url: '../check_detail/check_detail?idnum=' + idnum
				})

			},
			// obtain: function() {
			// 	const db = uniCloud.database();
			// 	db.collection("check").add({
			// 		"idnum": "111",
			// 		"name": "wddddd",

			// 	})
			// },
			handleLoad(date) {
				this.list = date
			},
			getchecktable() {
				uniCloud.callFunction({
					name: 'checkshow'
				}).then((res) => {
					const {
						result
					} = res
					this.checktable = result.data
				})
			}
		},
		onPullDownRefresh() {
			//监听下拉刷新动作的执行方法，每次手动下拉刷新都会执行一次
			console.log('refresh');
			this.getchecktable();
			
		
			setTimeout(function() {
				uni.stopPullDownRefresh(); //停止下拉刷新动画
			}, 1000);
		},
		onNavigationBarButtonTap: function(e) {
			//    console.log(e.text);//提交
			// console.log(e.fontSize);//16px

			uni.navigateTo({
				url: '../check_his/check_his'
			})
		}

	}
</script>

<style>
	
	.bg{
		width: 45%;
		margin: 550rpx auto;
	}

	.obtain {
		margin: 100rpx auto;
		width: 400rpx;
		margin-bottom: 100rpx;
	}

	.checklist {
		/* display: flex; */
		/* background: url(../../static/bg1.png) repeat fixed center; */
	}
	
</style>
