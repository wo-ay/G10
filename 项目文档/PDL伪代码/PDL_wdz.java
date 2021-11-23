PDL wdzs_part

Procedure 个人任务添加 is
begin 
    输入任务标题，任务内容，任务所在课程（选填），截至日期(输入时即已规范)
    loop until 输入信息完整正确
        if 输入信息不完整 then
            提示错误信息
        endif
    endif loop    
    
    if(按下完成按钮){
        insert into task(task_id（自动生成）,task_tital,task_content,task_course,task_ddl) values(?,任务标题,任务内容,任务所在课程,截至日期)
    }
    
end 个人任务添加



Procedure 个人任务编辑 is
begin 
    选中任务(获取任务号)，进入任务界面
    
    if(选中任务标题){
        update task set task_tital = ? where task_id=?;
    }
    if(选中任务内容){
        update task set task_tital = ? where task_id=?;
    }
    if(选中任务所在课程){
        update task set task_course = ? where task_id=?;
    }  
    if(选中任务截止日期){
        update task set task_ddl = ? where task_id=?;
    }          

end 个人任务编辑

Procedure 个人任务删除 is
begin 

    选中任务(获取任务号)
    delete from task where task_id = ?    

end 个人任务删除

Procedure 个人任务查看 is
begin 

    选中任务(获取任务号)
    select task_tital,task_content,task_course,task_ddl from task where task_id = ?    

end 个人任务查看

Procedure 教师任务发布 is
begin 
    输入任务标题，任务内容，任务所在课程，截至日期(输入时即已规范)
    loop until 输入信息完整
        if 输入信息不完整 then
            提示错误信息
        endif
    endif loop    
    
    if(按下完成按钮){
        insert into task(task_id（自动生成）,task_tital,task_content,task_course,task_ddl) values(?,任务标题,任务内容,任务所在课程,截至日期)
    }
    
end 教师任务发布

Procedure 教师任务发布 is
begin 
    输入任务标题，任务内容，任务所在课程，截至日期(输入时即已规范)
    loop until 输入信息完整
        if 输入信息不完整 then
            提示错误信息
        endif
    endif loop    
    
    if(按下完成按钮){
        insert into task(task_id（自动生成）,task_tital,task_content,task_course,task_ddl) values(?,任务标题,任务内容,任务所在课程,截至日期)
    }
    
end 教师任务发布

Procedure 教师任务编辑 is（可有可无）
begin 
    选中任务(获取任务号)，进入任务界面
    
    if(选中任务标题){
        update task set task_tital = ? where task_id=?;
    }
    if(选中任务内容){
        update task set task_tital = ? where task_id=?;
    }
    if(选中任务所在课程){
        update task set task_course = ? where task_id=?;
    }  
    if(选中任务截止日期){
        update task set task_ddl = ? where task_id=?;
    }          

end 教师任务编辑

Procedure 教师任务删除 is
begin 

    选中任务(获取任务号)
    delete from task where task_id = ?    

end 教师任务删除

Procedure 教师任务查看 is
begin 

    选中任务(获取任务号)
    select task_tital,task_content,task_course,task_ddl from task where task_id = ?    

end 教师任务查看