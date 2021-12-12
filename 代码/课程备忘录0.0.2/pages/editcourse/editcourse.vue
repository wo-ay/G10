<template>
	<view>

		<view class="main">
			<view class="task" id="task_course">
				<textarea  placeholder="请输入课程名" v-model="course_name" class="text"></textarea>
			</view>
			
			<view class="task">
				<select-lay :zindex="1211" :value="course_day" name="name" placeholder="请选择课程所在日" :options="datalist"
					@selectitem="selectdayitem" id="select">
				</select-lay>
			</view>

			<view class="start_endtime" id="starttime">
				<select-lay :zindex="1211" :value="start_time" name="name" placeholder="开始节数" :options="timelist"
					@selectitem="selectstarttimeitem" id="select">
				</select-lay>
			</view>
			<view class="start_endtime" id="endtime">
				<select-lay :zindex="1211" :value="end_time" name="name" placeholder="结束节数" :options="timelist"
					@selectitem="selectendtimeitem" id="select">
				</select-lay>
			</view>






			<view class="task" id="task_title">
				<textarea value="" placeholder="请输入课程地点" v-model="course_add" class="text"></textarea>
			</view>

			<!-- <view>{{course_name}}</view>
			<view>{{course_day}}</view>
			<view>{{start_time}}</view>
			<view>{{end_time}}</view>
			<view>{{course_add}}</view> -->
			<!-- <view class="task" id="task_content">
				<textarea value="" placeholder="请输入任务内容"></textarea>
			</view> -->
		</view>

		<!-- <view class="title">实时获取输入值：{{course_name}},{{course_day}},{{start_time}},{{end_time}},{{course_add}}</view> -->
		<view id="submit">
			<button @click="add_course()" class="btn">提交</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				datalist: [],
				timelist: [],
				//模拟初始数据
				tval: "value2",
				// arrays: ['---请选择---','教师','学生','管理员'],
				index: 0,
				course_name: '',
				course_day: '',
				start_time: '',
				end_time: '',
				course_add: '',
				idnum:'',
				coursenum:'',
			}
		},
		methods: {
			selectdayitem(index, item) {
				// console.log(item)
				if (index >= 0) {
					// this.tval = item.value;
					this.course_day = item.value;
				} else {
					this.tval = ""
				}
			},
			selectstarttimeitem(index, item) {
				// console.log(item)
				if (index >= 0) {
					// this.tval = item.value;
					this.start_time = item.value;
				} else {
					this.tval = ""
				}
			},
			selectendtimeitem(index, item) {
				// console.log(item)
				if (index >= 0) {
					// this.tval = item.value;
					this.end_time = item.value;
				} else {
					this.tval = ""
				}
			},
			add_course: function(){
				
				uniCloud.callFunction({
					name:"course_exist",
					data: {
						course_name: this.course_name,
						course_day: this.course_day,
						start_time: this.start_time,
						end_time: this.end_time,
						course_add: this.course_add,
						idnum:this.idnum,
										
					},
				}).then((res) => {
					const {
						result
					} = res
					// console.log(result.coursenum )
					// this.info=result.data
					this.coursenum = result.coursenum
					
					console.log(this.coursenum)
					
				})
				
				if(this.coursenum==0){
					uniCloud.callFunction({
						name:"add_course",
						data: {
							course_name: this.course_name,
							course_day: this.course_day,
							start_time: this.start_time,
							end_time: this.end_time,
							course_add: this.course_add,
							idnum:this.idnum,
											
						},
						// 成功
						success(res) {
							console.log("添加课程success");
							uni.showToast({
								title: '添加课程成功',
								icon: 'none',
								duration: 1000
							});
							
						},
						
					})
				}
				
				uniCloud.callFunction({
					name:"add_course_stu",
					data: {
						course_name: this.course_name,
						course_day: this.course_day,
						start_time: this.start_time,
						end_time: this.end_time,
						course_add: this.course_add,
						idnum:this.idnum,
										
					},
					// 成功
					success(res) {
						console.log("添加课程success");
						uni.showToast({
							title: '添加课程成功',
							icon: 'none',
							duration: 1000
						});
						
					},
					
				})
				// uniCloud.callFunction({
				// 	name:"add_course",
				// 	data: {
				// 		course_name: this.course_name,
				// 		course_day: this.course_day,
				// 		start_time: this.start_time,
				// 		end_time: this.end_time,
				// 		course_add: this.course_add,
				// 		idnum:this.idnum,
										
				// 	},
				// 	// 成功
				// 	success(res) {
				// 		console.log("添加课程success");
				// 		uni.showToast({
				// 			title: '添加课程成功',
				// 			icon: 'none',
				// 			duration: 1000
				// 		});
						
				// 	},
				// 	//失败
				// 	fail(e) {
				// 		console.log(e);
				// 	}
				// })
				
				
				uni.switchTab({
					url: '../course/course'
				});
			}
		},
		// onBackPress() {
		// 	uni.navigateTo({
		// 		url: "../course/course",
		// 	});
		// 	return true
		// },
		onLoad() {
			this.idnum=uni.getStorageSync("globalUser");
			console.log(this.idnum)
		},
		onReady() {
			this.datalist = [{
					label: "周一",
					value: "周一"
				},
				{
					label: "周二",
					value: "周二"
				},
				{
					label: "周三",
					value: "周三"
				},
				{
					label: "周四",
					value: "周四"
				},
				{
					label: "周五",
					value: "周五"
				},
				{
					label: "周六",
					value: "周六"
				},
				{
					label: "周日",
					value: "周日"
				}
			];
			this.timelist = [{
					label: "1",
					value: "1"
				},
				{
					label: "2",
					value: "2"
				},
				{
					label: "3",
					value: "3"
				},
				{
					label: "4",
					value: "4"
				},
				{
					label: "5",
					value: "5"
				},
				{
					label: "6",
					value: "6"
				},
				{
					label: "7",
					value: "7"
				},
				{
					label: "8",
					value: "8"
				},
				{
					label: "9",
					value: "9"
				},
				{
					label: "10",
					value: "10"
				},
				{
					label: "11",
					value: "11"
				},
				{
					label: "12",
					value: "12"
				}
			];
		},
	}
</script>

<style>
	.main {
		margin-top: 80rpx;
	}

	.task,
	.start_endtime {
		height: 70rpx;
		/* border: #006400; */
		background-color: #F2F2F2;
		border-radius: 20rpx;
		/* border-style: groove; */
		margin: 20rpx;
		padding: 10rpx;
	}

	.start_endtime {
		width: 300rpx;
	}
	.text{
		margin-top: 10rpx;
		margin-left: 15rpx;
	}
	#starttime {
		float: left;
	}

	#endtime {
		float: right;
	}

	#task_content {
		height: 950rpx;
	}

	#submit {
		margin: 40px;

	}
	.btn{
		color: #FFFFFF;
		background-color: #7CCD7C;
	}
	#task_title {
		margin-top: 200rpx;
	}
</style>
