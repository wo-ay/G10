<template>
	<view>

		<view>
			<uni-list>
				<view v-for="item in tasklist" @click="todetail(item._id)" @mouseover="confirm_task_id(item._id)"
					@touchstart="touchStart" @touchend="touchEnd">
					<uni-list-item :title="'@'+item.task_course+'-'+item.task_title" :note="item.task_content" :rightText="item.iffinish">
					</uni-list-item>
					<!-- <button @click="confirm()">完成</button> -->
				</view>
				<view>{{_id}}</view>
			</uni-list>
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
			}
		},
		onLoad() {
			this.idnum = uni.getStorageSync("globalUser");
			// console.log(this.idnum);

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
			
			confirm_task_id: function(id) {
				// console.log(data)
				// console.log(id);
				// this.$emit("click",event);
				this._id = id;
				console.log("hahattt")
				console.log(this._id)
			},
			todetail: function(id) {
				// console.log(data)
				// console.log(id);
				// this.$emit("click",event);

				uni.navigateTo({
					url: '../task_detail/task_detail?taskid=' + id
				})

			},

			gettasklist: function() {
				// console.log(data)
				uniCloud.callFunction({
					name: 'taskshow',
					data: {
						idnum: this.idnum,
						ing:0
						// iffinish:"已完成"
					}
				}).then((res) => {
					const {
						result
					} = res;
					this.tasklist = result.data;
					// this.test = result.data[0].tasktitle;

				})

			},
			confirm: function() {
				console.log("1")
			}
		},
		
	}
</script>

<style>

</style>
