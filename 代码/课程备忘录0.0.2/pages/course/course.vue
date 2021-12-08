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
                  ['', '', '', '', '电装实习', '电装实习', '', '', '', '大学体育', '大学体育', ''],
                  ['', '', '数据结构与算法分析', '数据结构与算法分析', '', '', '', '', '信号与系统', '信号与系统', '', '']
                ],
				courseList:[
					
				],
				stu_idnum:"31901238",
				
				course_name:"",
				course_day:"",
				start_time:"",
				end_time:"",
				course_add:"",
				theCourse:[]
			}
		},
		
		onLoad(){
			this.loadCourse()
		},
		
		methods: {
			loadCourse() {
				uniCloud.callFunction({
					name:"loadCourse",
					data:{
						stu_idnum:this.stu_idnum
					}
				}).then((res)=>{
					const {result} = res
					this.courseList = result.data
					this.updateTimeTables()
				})
				

			},
			updateTimeTables() {

				console.log(this.courseList.length);
				//for循环根据每一门课的_id到course中查找具体的课程详细,并改变timetables
				for (var i = 0; i < this.courseList.length; i++){
					this.searchCourse(this.courseList[i].course_id).then(res => {
						console.log(this.courseList[i].course_id)//1
						console.log(this.start_time)
						console.log(this.end_time)
						for (var j = this.start_time; j <= this.end_time; j++) {
							// console.log(j)
							console.log(this.timetables[this.course_day][j])
							this.timetables[this.course_day][j] = this.course_name
						}
					})

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
						this.course_day = 1
					} else if (result.data[0].course_day == "周二") {
						this.course_day = 2
					} else if (result.data[0].course_day == "周三") {
						this.course_day = 3
					} else if (result.data[0].course_day == "周四") { 
						this.course_day = 4
					} else if (result.data[0].course_day == "周五") {
						this.course_day = 5
					}
					//console.log(this.course_name)
					this.start_time = result.data[0].start_time
					
					console.log(this.start_time)
					this.end_time = result.data[0].end_time
					this.course_add = result.data[0].course_add
				})
				return ""
			}
		},
		components:{
			lpxtimetable
		},
		onNavigationBarButtonTap:function(e){
		    console.log(e.text);//提交
			console.log(e.fontSize);//16px
			uni.navigateTo({
				url:'../editcourse/editcourse'
			})
		}
	}
</script>

<style>

</style>