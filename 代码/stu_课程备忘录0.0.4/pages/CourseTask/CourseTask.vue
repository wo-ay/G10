<template>
	<view>
		<uni-list>
			<uni-list>
				<view v-for="item in course_task_list" @click="loadCourseTaskDetail(item._id)" v-if="item.if_remind=='1'" class="remind" style="padding: 10rpx;">
					<uni-list-item :title="'@'+item.course_name+'：'+item.task_title" :note="'截止时间：' + item.task_ddl"
						show-arrow="true" ></uni-list-item>
				</view>
				<view v-for="item in course_task_list" @click="loadCourseTaskDetail(item._id)" v-if="item.if_remind=='0'" class="remind">
					<uni-list-item :title="'@'+item.course_name+'：'+item.task_title" :note="'截止时间：' + item.task_ddl"
						show-arrow="true" ></uni-list-item>
				</view>
			</uni-list>
		</uni-list>
		<view v-if="course_task_list.length <= 0 " class="tip" style="width: 33%; margin: 120rpx auto;">
			暂无课程任务！
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				course_id: "",
				course_task_list: [],
				task_id_list: []
			}
		},

		onLoad(option) {
			this.course_id = option._id
			// this.course_id = '61b6079bb804f80001909fcc'
			this.getCourseTaskList();
		},

		// onShow() {
		// 	// this.course_id = option._id
		// 	this.getCourseTaskList();
		// },

		methods: {
			getCourseTaskList() {
				console.log(this.course_id)
				console.log(uni.getStorageSync("globalUser"))
				uniCloud.callFunction({
					name: "loadCourseTaskList",
					data: {
						course_id: this.course_id,
						stu_id: uni.getStorageSync("globalUser")
					}
				}).then((res) => {

					const {//查到任务列表
						result
					} = res;
					// let len=result.length
					// console.log(len)
					this.task_id_list=result.data
					let len=this.task_id_list.length
					console.log(len)
					for(let i = 0 ; i < len ; i++){
						uniCloud.callFunction({
							name:"searchTask",
							data:{
								task_id: this.task_id_list[i].task_id
								
							}
						}).then((res) => {
							const {//查到任务列表
								result
							} = res;
							this.course_task_list.push(result.data[0])
							console.log(result.data[0])
						})
						
						// console.log("testttttttt")
					}
					
					// this.course_task_list = result.data
					// console.log("结果是啥???")
					// // console.log(this.course_task_list[0])
					// console.log(this.course_task_list.length)

				})
			},

			loadCourseTaskDetail(_id) {
				// this.$emit("click",event)
				uni.navigateTo({
					url: '../CourseTaskDetail/CourseTaskDetail?_id=' + _id
				})
			}
		}
	}
</script>

<style>
	.remind{
		background-color: #7CCD7C;
	}
</style>
