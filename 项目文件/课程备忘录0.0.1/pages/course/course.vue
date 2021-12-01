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
                  ['大学英语', '大学英语', '大学英语', '', '', '', '毛概', '毛概', '', '', '', '选修'],
                  ['', '', '信号与系统', '信号与系统', '模拟电子技术基础', '模拟电子技术基础', '', '', '', '', '', ''],
                  ['大学体育', '大学体育', '形势与政策', '形势与政策', '', '', '', '', '', '', '', ''],
                  ['', '', '', '', '电装实习', '电装实习', '', '', '', '大学体育', '大学体育', ''],
                  ['', '', '数据结构与算法分析', '数据结构与算法分析', '', '', '', '', '信号与系统', '信号与系统', '', '']
                ]
			}
		},
		methods: {
			
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