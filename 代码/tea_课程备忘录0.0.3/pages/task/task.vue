<template>
	<view>

		<view>
			<uni-list>
				<view v-for="(item,index) in tasklist" @click="todetail(item._id,rate_list[index])" @mouseover="confirm_task_id(item._id)"
					@touchstart="touchStart" @touchend="touchEnd" :class="rate_list[index]? '0' : '1'">
					<uni-list-item :title="'@'+item.course_name+'-'+item.task_title" :note="item.task_ddl+'-'+item.task_content" :rightText="rate_list[index]+'%'">
					</uni-list-item>
					<!-- <button @click="confirm()">完成</button> -->
				</view>
				
			</uni-list>
		</view>
		<view v-if="tasklist.length <= 0 " class="tip" style="width: 30%; margin: 120rpx auto;">
			暂无任务！
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [{
						index: 1,
						data: 1
					},
					{
						index: 2,
						data: 2,
					},
					{
						index: 3,
						data: 3,
					},
					{
						index: 4,
						data: 4,
					},
					{
						index: 5,
						data: 5,
					}

				],
				idnum: '',
				tasklist: [],
				test: "",
				startX: 0,
				_id: "",
				rate_list:[],
				remind_list:[]
			}
		},
		onLoad() {
			this.idnum = uni.getStorageSync("globalUser");
			// console.log(this.idnum);

			this.gettasklist();

		},
		onShow() {
			this.gettasklist();
		},
		onPullDownRefresh() {
			//监听下拉刷新动作的执行方法，每次手动下拉刷新都会执行一次
			console.log('refresh');
			this.gettasklist();

			setTimeout(function() {
				uni.stopPullDownRefresh(); //停止下拉刷新动画
			}, 1000);
		},


		methods: {
			/**
			 * 触摸开始
			 * @param {Object} e
			 */
			touchStart: function(e) {
				if (e.touches.length == 1) {
					//设置触摸起始点水平方向位置
					this.startX = e.touches[0].clientX;
				}
			},
			/**
			 * 触摸结束
			 * @param {Object} e
			 */
			touchEnd: function(e) {
				if (e.changedTouches.length == 1) {
					//手指移动结束后水平位置
					var endX = e.changedTouches[0].clientX;
					let diff = endX - this.startX;
					if (Math.abs(diff) > 20) {
						if (diff > 0) {
							console.log("右滑...");




						} else {
							console.log("左滑...");
							uni.showModal({
								title: '提示',
								content: '确认完成任务了吗？',
								cancelText: "取消", // 取消按钮的文字  
								confirmText: "确认", // 确认按钮的文字  
								showCancel: true, // 是否显示取消按钮，默认为 true
								confirmColor: '#f55850',
								cancelColor: '#39B54A',
								success: (res) => {
									if (res.confirm) {
										console.log('comfirm') //点击确定之后执行的代码
										uniCloud.callFunction({
											name: 'finish_task', // 云函数名字
											// 传输数据
											data: {

												task_id: this._id,

											},
											// 成功
											success(res) {
												console.log("successllalalalalala");
												uni.showToast({
													title: '任务完成！',
													icon: 'none',
													duration: 1000
												});

											},
											//失败

										})
									} else {
										console.log('cancel') //点击取消之后执行的代码
									}
								}
							})
						}
					}
				}

			},
			confirm_task_id: function(id) {
				// console.log(data)
				// console.log(id);
				// this.$emit("click",event);
				this._id = id;
				console.log("hahattt")
				console.log(this._id)
			},
			todetail: function(id,rate) {
				// console.log(data)
				// console.log(id);
				// this.$emit("click",event);

				uni.navigateTo({
					url: '../task_detail/task_detail?taskid=' + id +'&rate=' + rate
				})

			},

			gettasklist: function() {
				// console.log(data)
				uniCloud.callFunction({
					name: 'taskshow',
					data: {
						idnum: this.idnum,
						// iffinish: '未完成',
						id:"教师",
						ing:1
					}
				}).then((res) => {
					const {
						result
					} = res;
					this.tasklist = result.data;
					
					for(let j=0;j< this.tasklist.length;j++){
						uniCloud.callFunction({
							name:"get_rate_list",/* 获取完成率和是否提醒的列表 */
							data:{
								task_id:this.tasklist[j]._id,
								
							}
						}).then((res) => {
							const {
								result
							} = res;
							console.log(result.data)
							this.rate_list[j]=100*result.data
							this.remind_list[j]=result.if_remind
						})
					}
					
					// this.test = result.data[0].tasktitle;

				})

			},
			confirm: function() {
				console.log("1")
			}
		},
		onNavigationBarButtonTap: function(e) {
			console.log(e.text); //提交
			console.log(e.fontSize); //16px
			if (e.text == "+") {
				uni.navigateTo({
					url: '../edittask/edittask'
				})
			}
			if (e.text == "历史") {
				uni.navigateTo({
					url: '../task_his/task_his'
				})
			}

		}
	}
</script>

<style>

</style>
