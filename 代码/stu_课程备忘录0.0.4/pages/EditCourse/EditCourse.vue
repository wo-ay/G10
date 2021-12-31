<template>
	<view>

		<view class="main">
			<view class="task" id="task_course">
				<textarea placeholder="请输入课程名" v-model="course_name" class="text"></textarea>
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
				timetables: [
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
				],
				courseList: [

				],
				//模拟初始数据
				tval: "value2",
				// arrays: ['---请选择---','教师','学生','管理员'],
				index: 0,
				course_name: '',
				course_day: '',
				start_time: '',
				end_time: '',
				course_add: '',
				idnum: '',
				coursenum: '',
				id: '教师',

				day: 0,
				tea_idnum: '',
				conflict: 0,
				old_course_name: '',
				old_course_day: '',
				old_start_time: '',
				old_end_time: '',
				theCourse: [],
			}
		},
		onLoad() {
			//this.stu_idnum = "31901238"
			this.tea_idnum = uni.getStorageSync("globalUser");
			console.log(this.tea_idnum + 'test')
			this.loadCourse()
		},

		onShow() {
			// this.tea_idnum = uni.getStorageSync("globalUser");
			this.loadCourse()
		},
		methods: {
			loadCourse() {
				uniCloud.callFunction({
					name: "loadStuCourse",
					data: {
						stu_idnum: this.tea_idnum
					}
				}).then((res) => {
					const {
						result
					} = res
					this.courseList = result.data
					this.updateTimeTables()
				})
			},

			updateTimeTables() {
				//for循环根据每一门课的_id到course中查找具体的课程详细,并改变timetables
				for (var i = 0; i < this.courseList.length; i++) {
					//this.searchCourse(this.courseList[i].course_id)
					this.searchCourse(this.courseList[i].course_id)
					// for (var j = this.start_time; j <= this.end_time; j++) {
					// 	// console.log(j)
					// 	//console.log(this.timetables[this.course_day][j])
					// 	this.timetables[this.course_day][j] = this.course_name
					// }

				}

			},

			searchCourse(course_id) {
				uniCloud.callFunction({
					name: 'searchCourse',
					data: {
						course_id: course_id
					}
				}).then((res) => {
					const {
						result
					} = res
					this.theCourse = result.data
					//该课程的详细信息
					this.old_course_name = result.data[0].course_name

					if (result.data[0].course_day == "周一") {
						this.old_course_day = 0
					} else if (result.data[0].course_day == "周二") {
						this.old_course_day = 1
					} else if (result.data[0].course_day == "周三") {
						this.old_course_day = 2
					} else if (result.data[0].course_day == "周四") {
						this.old_course_day = 3
					} else if (result.data[0].course_day == "周五") {
						this.old_course_day = 4
					}
					console.log(this.old_course_name)

					console.log(this.old_course_day)
					this.old_start_time = result.data[0].start_time
					this.old_end_time = result.data[0].end_time
					this.old_course_add = result.data[0].course_add
					console.log(this.old_start_time + "  " + this.old_end_time)
					for (var j = this.old_start_time; j <= this.old_end_time; j++) {
						//this.timetables[this.course_day][j - 1]= this.course_name
						this.$set(this.timetables[this.old_course_day], j - 1, this.old_course_name + "@" + this
							.old_course_add)
					}
					console.log("周2第1节课" + this.timetables[1][1])
					//console.log("周三第三节课" + this.timetables[2][2])
					// console.log(this.timetables)

					// this.timetables[0][1] = "软件工程"	
					//this.$set(this.timetables[0],1,"软件工程")
					console.log(this.timetables[0][1])
				})
			},
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
			add_course: function() {
				if (this.course_name == '') {
					uni.showToast({
						title: '请输入课程名',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.course_day == '') {
					uni.showToast({
						title: '请选择课程所在日',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.start_time == '') {
					uni.showToast({
						title: '请选择开始节数',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.end_time == '') {
					uni.showToast({
						title: '请选择结束节数',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (parseInt(this.end_time) < parseInt(this.start_time)) {
					uni.showToast({
						title: '结束节数应较大',
						icon: 'none',
						duration: 1000
					});
					return false;
				}
				if (this.course_add == '') {
					uni.showToast({
						title: '请输入课程地点',
						icon: 'none',
						duration: 1000
					});
					return false;
				}

				this.conflict = 0
				if (this.course_day == '周一') {
					this.day = 1;
				}
				if (this.course_day == '周二') {
					this.day = 2;
				}
				if (this.course_day == '周三') {
					this.day = 3;
				}
				if (this.course_day == '周四') {
					this.day = 4;
				}
				if (this.course_day == '周五') {
					this.day = 5;
				}
				if (this.course_day == '周六') {
					this.day = 6;
				}
				if (this.course_day == '周日') {
					this.day = 7;
				}

				console.log("课程日---" + this.day)
				console.log("开始---" + parseInt(this.start_time))
				console.log("结束---" + parseInt(this.end_time))
				// console.log("test-timetable"+this.timetables[1][1])
				for (let i = parseInt(this.start_time); i < parseInt(this.end_time); i++) {
					if (this.timetables[this.day-1][i - 1] != '') {

						this.conflict = 1;
						console.log("课程冲突")

					}
					// if(i==this.end_time-'0'-1){
					// 	if(this.conflict == 1){

					// 	}
					// }
				}

				if (this.conflict == 1) {
					uni.showToast({
						title: '课程时间冲突',
						icon: 'error'
					})
				} else {


					uniCloud.callFunction({
						name: "course_exist",
						data: {
							course_name: this.course_name,
							course_day: this.course_day,
							start_time: this.start_time,
							end_time: this.end_time,
							course_add: this.course_add,
							// idnum: this.idnum,
							// id: ''
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




					if (this.coursenum == 0) {
						uniCloud.callFunction({
							name: "add_course",
							data: {
								course_name: this.course_name,
								course_day: this.course_day,
								start_time: this.start_time,
								end_time: this.end_time,
								course_add: this.course_add,
								

							},
							// 成功
							success(res) {
								console.log("添加课程success");
								uni.showToast({
									title: "添加课程成功",
									icon: "success",
									duration: 2000
								});
								setTimeout(function() {
									uni.navigateBack()
								}, 2000);

							},

						})
					}


					let j = 0;
					for (let i = 0; i < 98000; i++) {
						if (i == 97900) {



							// if (this.id == '教师') {
							uniCloud.callFunction({
								name: "add_course_stu",
								data: {
									course_name: this.course_name,
									course_day: this.course_day,
									start_time: this.start_time,
									end_time: this.end_time,
									course_add: this.course_add,
									idnum: this.tea_idnum,

								},
								// 成功
								success(res) {
									console.log("添加课程success");
									


								},

							})
							// }

						}
					}
				}
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


				// uni.switchTab({
				// 	url: '../course/course'
				// });
			}
		},
		// onBackPress() {
		// 	uni.navigateTo({
		// 		url: "../course/course",
		// 	});
		// 	return true
		// },
		// onLoad(option) {
		// 	this.idnum = uni.getStorageSync("globalUser");
		// 	console.log(this.idnum)
		// 	// this.id = option.id
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

	.text {
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

	.btn {
		color: #FFFFFF;
		background-color: #7CCD7C;
	}

	#task_title {
		margin-top: 200rpx;
	}
</style>
