<template>
	<view>
		<uni-list>
			<uni-list>
				<view v-for="(item,index) in course_task_list" @click="getTeacherCourseTaskDetail(item._id,rate_list[index])">
					<uni-list-item :title = "'@'+item.course_name+'：'+item.task_title" :note="'截止时间：' + item.task_ddl" :rightText="rate_list[index]+'%'" ></uni-list-item>
				</view>
			</uni-list>
		</uni-list>
		<view v-if="course_task_list.length <= 0 " class="tip" style="width: 30%; margin: 120rpx auto;">
			暂无课程任务！
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				course_id:"",
				teacher_id:"",
				course_task_list:[],
				rate_list:[]
			}
		},
		
		onLoad(option) {
			this.teacher_id = uni.getStorageSync("globalUser")
			this.course_id = option._id
			this.getCourseTaskList();
		},
		onShow(){
			this.getCourseTaskList();
		},
		onPullDownRefresh() {
			//监听下拉刷新动作的执行方法，每次手动下拉刷新都会执行一次
			console.log('refresh');
			this.getCourseTaskList();
		
			setTimeout(function() {
				uni.stopPullDownRefresh(); //停止下拉刷新动画
			}, 1000);
		},
		onNavigationBarButtonTap: function(e) {
			console.log(e.text); //提交
			console.log(e.fontSize); //16px
			if (e.text == "+") {
				uni.navigateTo({
					url: '../editcoursetask/editcoursetask?courseid='+this.course_id
				})
			}
			
		
		},
		methods: {
			getCourseTaskList(){
				console.log(this.course_id)
				console.log(this.teacher_id)
				uniCloud.callFunction({
					name:"getTeacherCourseTaskList",
					data:{
						course_id:this.course_id,
						teacher_id:this.teacher_id
					}
				}).then((res)=>{
					const{result} = res
					this.course_task_list = result.data
					for(let j=0;j< this.course_task_list.length;j++){
						uniCloud.callFunction({
							name:"get_rate_list",/* 获取完成率和是否提醒的列表 */
							data:{
								task_id:this.course_task_list[j]._id,
								
							}
						}).then((res) => {
							const {
								result
							} = res;
							console.log(result.data)
							this.rate_list[j]=100*result.data
							// this.remind_list[j]=result.if_remind
						})
					}
				})
			},
			
			getTeacherCourseTaskDetail(_id,rate) {
				// this.$emit("click",event)
				uni.navigateTo({
					url:"../task_detail/task_detail?taskid=" + _id +'&rate=' + rate +'&course_id=' + this.course_id
				})
			}
			
		}
	}
</script>

<style>
	/* .tip{
		width: 30%; 
		margin: 120rpx auto;
	} */
</style>
