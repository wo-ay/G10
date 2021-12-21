<template>
	<view>
		<view>
			<lpxtimetable :timetables="timetables"></lpxtimetable>
		</view>
		
		
		
	</view>

</template>

<script>
	import lpxtimetable from '../../components/lpx-timetable/lpx-timetable.vue'
	export default {
		data() {
			return {
				timetables: [
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', ''],
					['', '', '', '', '', '', '', '', '', '', '', '']
				],
				courseList: [

				],
				stu_idnum: "",
				course_name: "",
				course_day: "",
				start_time: "",
				end_time: "",
				course_add: "",
				theCourse: []
			}
		},

		onLoad() {
			// this.stu_idnum = "31901238"
			this.stu_idnum = uni.getStorageSync("globalUser")
			this.loadCourse()
		},
		
		onShow(){
			this.loadCourse()
		},
		
		onPullDownRefresh() {
			//监听下拉刷新动作的执行方法，每次手动下拉刷新都会执行一次
			console.log('refresh');
			this.loadCourse();
		
			setTimeout(function() {
				uni.stopPullDownRefresh(); //停止下拉刷新动画
			}, 1000);
		},

		methods: {
			loadCourse() {
				uniCloud.callFunction({
					name: "loadCourse",
					data: {
						stu_idnum: this.stu_idnum
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
					name:'searchCourse',
					data:{
						course_id:course_id
					}
				}).then((res)=>{
					const {result} = res
					this.theCourse = result.data
					//该课程的详细信息
					this.course_name = result.data[0].course_name
				
					if (result.data[0].course_day == "周一") {
						this.course_day = 0
					} else if (result.data[0].course_day == "周二") {
						this.course_day = 1
					} else if (result.data[0].course_day == "周三") {
						this.course_day = 2
					} else if (result.data[0].course_day == "周四") { 
						this.course_day = 3
					} else if (result.data[0].course_day == "周五") {
						this.course_day = 4
					}
					//console.log(this.course_name)
					
					//console.log(this.course_day)
					this.start_time = result.data[0].start_time					
					this.end_time = result.data[0].end_time
					this.course_add = result.data[0].course_add
					//console.log(this.start_time + "  " + this.end_time)
					for (var j = this.start_time; j <= this.end_time; j++) {
						//this.timetables[this.course_day][j - 1]= this.course_name
						this.$set(this.timetables[this.course_day],j - 1,this.course_name +"@"+this.course_add)
					}
					//console.log("周三第二节课" + this.timetables[2][1])
					//console.log("周三第三节课" + this.timetables[2][2])
					// console.log(this.timetables)
					
					// this.timetables[0][1] = "软件工程"	
					//this.$set(this.timetables[0],1,"软件工程")
					//console.log(this.timetables[0][1])	
				})
			}
		},
		components: {
			lpxtimetable
		},
		onNavigationBarButtonTap: function(e) {
			console.log(e.text); //提交
			console.log(e.fontSize); //16px
			uni.navigateTo({
				url: '../EditCourse/EditCourse'
			})
		}
	}
</script>

<style>
	
</style>
