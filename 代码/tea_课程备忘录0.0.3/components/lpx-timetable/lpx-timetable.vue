<template>
  <view class="timetable">
    <view class="header">  <!-- 显示周几-->
      <view class="header-item" v-for="(item,index) in week" :key="item" :style="{ color: todayWeekIndex === index ? '#4070FF' : 'unset' }">{{ item }}</view>
    </view>

    <view class="main">
      <view class="row" v-for="(item,index) in timetableType" :key="index"><!-- 显示节数及每节课时间 -->
        <view class="time-item" >
          <view class="index">{{ item.index }}</view><!-- 显示节数 -->
          <view class="time">{{ item.name }}</view><!-- 显示每节课的上课时间 -->
        </view>
      </view>
		
		
      <view class="course-container">
        <view class="week" v-for="(week, weekIndex) in courseData" :key="weekIndex">
          <view class="courseList" v-for="(course, courseIndex) in week" :key="courseIndex">
            <view @click="handleCourseClick(course, weekIndex, courseIndex)" class="course" :style="{ height: (course.length * 79) + 'px', background: course.backgroundColor}" v-if="course.length > 0">{{ course.name }}</view>
			<!-- <view  @click="testCourseClick()"class="course" :style="{ height: (course.length * 79) + 'px', background: course.backgroundColor}" v-if="course.length > 0">{{ course.name }}</view> -->
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
  export default {
    name: 'Timetable',
    props: {
      timetableType: {
        type: Array,
        default: () => {
          return [
            { index: '1', name: '08:00\n08:45' },
            { index: '2', name: '08:50\n09:35' },
            { index: '3', name: '09:50\n10:35' },
            { index: '4', name: '10:40\n11:25' },
            { index: '5', name: '11:30\n12:15' },
            { index: '6', name: '13:30\n14:15' },
            { index: '7', name: '14:20\n15:05' },
            { index: '8', name: '15:20\n16:05' },
            { index: '9', name: '16:10\n16:55' },
            { index: '10', name: '18:30\n19:15' },
            { index: '11', name: '19:20\n20:05' },
            { index: '12', name: '20:10\n20:55' }
          ]
        }
      },
      week: {
        type: Array,
        default: () => {
          return ['一', '二', '三', '四', '五', '六', '日']
        }
      },
      timetables: {
        type: Array,
        default: () => {
          return []
        }
      },
      palette: {
        type: Array,
        default: () => {
          return []
        }
      }
    },
    data () {
      return {
        allPalette: [...this.palette, '#f05261', '#48a8e4', '#ffd061', '#52db9a', '#70d3e6', '#52db9a', '#3f51b5', '#f3d147', '#4adbc3', '#673ab7', '#f3db49', '#76bfcd', '#b495e1', '#ff9800', '#8bc34a'],
		course_id:""
	  }
    },
    computed: {
      courseData () {
        // 为数据标记背景颜色的函数
        let paletteIndex = 0
        const getBackgroundColor = () => {
          const backgroundColor = this.allPalette[paletteIndex]
          paletteIndex++
          if (paletteIndex >= this.allPalette.length) {
            paletteIndex = 0
          }
          return backgroundColor
        }
        // 合并
        const listMerge = []
        this.timetables.forEach(function (list, i) {
          if (!listMerge[i]) {
            listMerge[i] = []
          }
          list.forEach(function (item, index) {
            if (!index) {
              return listMerge[i].push({ name: item, length: 1, backgroundColor: item === '' ? 'none' : getBackgroundColor() })
            }
            if (item === (listMerge[i][index - 1] || {}).name && item) {
              const sameIndex = (listMerge[i][index - 1] || {}).sameIndex
              if (sameIndex || sameIndex === 0) {
                listMerge[i][sameIndex].length++
                return listMerge[i].push({ name: item, length: 0, sameIndex: sameIndex })
              }
              listMerge[i][index - 1].length++
              return listMerge[i].push({ name: item, length: 0, sameIndex: index - 1 })
            } else {
              return listMerge[i].push({ name: item, length: 1, backgroundColor: item === '' ? 'none' : getBackgroundColor() })
            }
          })
        })
        return listMerge
      },
      todayWeekIndex () {
        let weekIndex = new Date().getDay() - 1
        if (weekIndex === -1) {
          weekIndex = 6
        }
        return weekIndex
      }
    },
    methods: {
      handleCourseClick:function(course, weekIndex, courseIndex) {
		  
		  //weekIndex礼拜几，course课程，courseIndex第几节课开始
		  
        const data = {
          index: courseIndex + 1,
          length: course.length,
          week: this.week[weekIndex],
          weekIndex: weekIndex,
          name: course.name
        }
        console.log(`星期${data.week}; 第${data.index}节课; 课程名:${data.name}; 课节:${data.length}`)
        console.log(data)
		//根据周几第几节课和地点，查该课程的_id
		let addr=data.name.split('@')
	
		let week = "周" + data.week
		let index = data.index
		let add = addr[1]
		
		uniCloud.callFunction({
			name:"searchCourseByTimeAddr",
			data:{
				course_day:week,
				start_time:index+'',
				//start_time:"3",
				course_add:add
			}
		}).then((res)=>{
			const{result} =  res
			console.log(result)
			this.course_id = result.data[0]._id
			console.log(this.course_id)
			uni.navigateTo({
				// url:'/pages/TeacherCourseTaskList/TeacherCourseTaskList?_id=' + this.course_id
				url:'/pages/TeacherCourseTaskList/TeacherCourseTaskList?_id=61b6079bb804f80001909fcc'
			})
			
		})
        this.$emit('courseClick', data)//子组件向父组件传值,,第一个值为父组件定义函数，第二个是需要传递的参数
      },
	  
		  
	 //  toDetail() {
		// this.$emit("click",event);
		// console.log("点击了！")
		// console.log(this.course_id)
		// uni.navigateTo({
		// 	url:'../course_task/course_task?_id='+this.course_id
		// })
	 //  },
	  
	  testCourseClick:function(){
	    console.log("test")
	  },
	  toTask:function(){
		  
	  }
    }
  }
</script>

<style scoped lang="scss">
.timetable{
  background: white;
  border: 1px solid #E4E7ED;
  border-radius: 8rpx;
  .header{
    padding-left: 88rpx;
    height: 56rpx;
    display: flex;
    align-items: center;
    background: #F5F7FA;
    .header-item{
      flex: 1;
      text-align: center;
    }
  }
  .main{
    position: relative;
    .row{
      height: 85px;
      position: relative;
      &:after{
        content: '';
        height: 0;
        width: 100%;
        position: absolute;
        bottom: 0;
        left: 0;
        border-bottom: 1rpx dashed #E4E7ED;
      }
      .time-item{
        height: 100%;
        width: 88rpx;
        text-align: center;
        background: #F5F7FA;
        .index{
          color: #909399;
          padding-bottom: 8rpx;
          padding-top: 16rpx;
        }
      }
    }
    .course-container{
      position: absolute;
      top: 0;
      left: 90rpx;
      width: calc(100% - 280rpx);
	  // width: 88rpx;
      height: 100%;
      display: flex;
      .week{
        flex: 1;
        width: 0;
        flex-grow: 1;
        display: flex;
        flex-direction: column;
        .courseList{
          word-break: break-all;
          color: white;
          overflow: hidden;
          .course{
			width: 70rpx;
            padding: 8rpx;
			left:40rpx;
            // border-radius: 16rpx;
			border-radius: 16rpx;
            text-align: center;
          }
        }
      }
    }
  }
}
</style>