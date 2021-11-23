PDL

Procedure 注册 is
begin
    loop until 退出该模块
        输入账号密码等个人信息
        //搜索该账号是否已经存在
        select user_name from user where user_id = ?
        if 账号已存在 then
            提示该账号已注册
        else
            if 第一次密码和第二次密码不同 then
                提示两次密码不同
            else
                insert verify(verify_id, user_identity,user_id,user_name,password,user_card,phone,email,verify_status) values(?,?,?,?,?,?,?,?,?)
            endif
        endif
    endloop
end 注册



Procedure 登录 is
begin 
    loop until 退出该模块
        输入账号密码
        //查询账号密码是否正确
        select * from user where user_id = ? and user_password = ?
        if 查询结果不为空 then
            登录成功
            if 用户身份是教师 then
                进入教师首页
            else if 用户身份是学生 then
                进入学生首页
            else if 用户身份是管理员 then
                进入管理员首页
            endif
        else
            //不正确则提示错误信息
            select user_name from user where user_id = ?
            if 查询结果为空 then 
                账号不存在
            else
                密码错误
            endif
        endif
    endloop
end 登录


Procedure 导入课表 is
begin
    用户输入学校教务系统账号密码
    程序将登录所需的请求参数封装参数对象，并发送请求
    if 登录成功 then 
        解析页面获取课表
        loop until 所有课程均处理完毕
            //将学生的选课情况插入elective表中
            insert into elective(course_id,course_name,stu_id,teacher_id) values(?,?,?,?)
            //为该课程设置闹钟，note信息包括课程地点，课程名称
            调用Procedure添加闹钟
            //查找该课程是否在course表中
            //如果两门课程的上课地点，上课时间都相同，则被认为是同一节课
            select course_id from course where course_addr = ? course_time = ?
            if 查询结果为空 then
                insert into course(course_id,course_name,course_time,course_addr,teacher_id,stu_num) values(?,?,?,?,?,?);
            endif
        endloop
    else 
        提示错误信息
    endif
end 导入课表


Procedure 添加闹钟 is
begin 
    输入闹钟的备注，时间等信息
    loop until 时间信息合理
        if 时间信息为空或不合理 then
            提示错误信息
        endif
    endif loop
    insert into alarm(alarm_id,user_id,alarm_note,alarm_time) values(?,?,?,?)
    为该闹钟设置Timer定时器，定时器将在指定的时间执行指定的任务
end 添加闹钟

Procedure 编辑闹钟 is
begin
    输入闹钟的备注，时间等信息
    loop until 时间信息合理
        if 时间信息为空或不合理 then
            提示错误信息
        endif
    endif loop
    update alarm set alarm_note = ?,alarm_time = ? where alarm_id = ?;
    删除原闹钟定时器，为该闹钟设置Timer定时器，定时器将在指定的时间执行指定的任务
end 编辑闹钟

procedure 删除闹钟 is
begin 
    delete from alarm where alarm_id = ?
end 删除闹钟

procedure 课程任务 is
begin
    //仅显示已开始且未结束的任务
    select * from course_task_process where stu_id = ? and nowTime > start_time and nowTime < end_time
end 课程任务

procedure 完成课程任务 is
begin
    update course_task_process set isfinished = true where stu_id = ? and course_task_id = ?
    //共有多少班内学生完成该课程任务
    select count(*) from course_task_process where course_task_id = ?
    在教师端显示完成人数
end 完成课程任务
