<template>
	<view>

		<view class="main">
			<view class="task" id="task_course">
				<textarea value="" placeholder="请输入课程名" v-model="course_name"></textarea>
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
				<textarea value="" placeholder="请输入课程地点" v-model="course_add"></textarea>
			</view>

			<!-- <view class="task" id="task_content">
				<textarea value="" placeholder="请输入任务内容"></textarea>
			</view> -->
		</view>

		<!-- <view class="title">实时获取输入值：{{course_name}},{{course_day}},{{start_time}},{{end_time}},{{course_add}}</view> -->
		<view id="submit">
			<button>提交</button>
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
		},
		// onBackPress() {
		// 	uni.navigateTo({
		// 		url: "../course/course",
		// 	});
		// 	return true
		// },

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
		height: 80rpx;
		border: #006400;
		border-radius: 20rpx;
		border-style: groove;
		margin: 20rpx;
	}

	.start_endtime {
		width: 300rpx;
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

	#task_title {
		margin-top: 200rpx;
	}
</style>
