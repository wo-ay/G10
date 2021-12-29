<template>
	<view>

		<view class="main">


			<view class="task" id="task_title">
				<textarea v-model="task_title" placeholder="请输入任务" class="text"></textarea>
			</view>
			<long-date type="day" getDayNum="10" :openStatus="true" startTime="2021-12-03" endTime="2022-02-01"
				chooesMaxDay="8" @select="Time">
			</long-date>
			<view class="task" id="task_content">
				<textarea v-model="task_content" placeholder="请输入任务内容" class="text"></textarea>
			</view>
		</view>

		<view id="submit">
			<button @click="submit_task()">提交</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				task_course: '',
				task_title: '',
				task_ddl: '',
				task_content: '',
				iffinish: '',
				idnum: '',
				task_ddl: "",
				task_day: '',
				start_time: '',
				end_time: '',
				course_exist: '',
				course_day: '',

				timelist: [],
				datalist: [],
				task_id: '',
				course_id: '',
				stu_list: [1, 2, 3],
				course_id: '',
				course_name: ''
			}
		},
		onLoad(option) {
			this.idnum = uni.getStorageSync("globalUser");
			// console.log(this.idnum);
			this.course_id = option.courseid
			this.getcoursename()

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
		methods: {
			getcoursename: function() {
				uniCloud.callFunction({
						name: "get_course_name",
						data: {
							course_id: this.course_id

						}
					}).then((res) => {
							const {
								result
							} = res
							this.course_name = result.data
						})
					},

					Time(val) {
						console.log(val);
						this.task_ddl = val;
					},
					submit_task: function() {

						/* 首先判断输入的课程名是否存在于course_tea表中 */


						uniCloud.callFunction({
							name: "add_course_task",
							data: {
								teacher_idnum: this.idnum,
								task_course: this.course_id,
								task_title: this.task_title,
								task_ddl: this.task_ddl,
								task_content: this.task_content,
								course_name: this.course_name

							}

						}).then((res) => {


							// console.log(result.task_id);
							uni.showToast({
								title: '添加课程任务成功',
								icon: 'none',
								duration: 1000
							});
							uniCloud.callFunction({ //获取task_id
								name: "getTaskId",
								data: {
									teacher_idnum: this.idnum,
									// task_course: this.task_course,
									// start_time: this.start_time,
									// end_time: this.end_time,
									task_title: this.task_title,
									task_ddl: this.task_ddl,
									task_content: this.task_content,
								}
							}).then((res) => {
								const {
									result
								} = res
								this.task_id = result.data
								uniCloud.callFunction({
									name: "get_task_stu",
									data: {

										course_id: this.course_id,

									}
								}).then((res) => {
									const {
										result
									} = res
									this.stu_list = result.stu_list
									console.log(this.stu_list.length)
									for (let t = 0; t < this.stu_list.length; t++) {
										uniCloud.callFunction({
											name: "add_task_stu",
											data: {
												course_id:this.course_id,
												stu_idnum: this.stu_list[t].stu_idnum,
												task_id: this.task_id,
												task_title: this.task_title,
											}
										})
									}
									uni.navigateTo({
										url: "../TeacherCourseTaskList/TeacherCourseTaskList"
									})



								})
							})


						})


					}
			}
		}
</script>

<style>
	.main {
		margin-top: 20rpx;
	}

	#task_title {
		margin-top: 50rpx;
	}

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

	#starttime {
		float: left;
	}

	#endtime {
		float: right;
	}

	.task {
		height: 80rpx;
		background-color: #F2F2F2;
		border-radius: 20rpx;

		margin: 20rpx;
	}

	.text {
		padding-top: 15rpx;
		margin-top: 20rpx;
		margin-right: 30rpx;
		margin-left: 20rpx;
	}

	#task_content {
		height: 950rpx;
	}

	#submit {
		margin: 40px;

	}
</style>
