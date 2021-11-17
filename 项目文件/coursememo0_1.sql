/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/11/16 16:44:04                          */
/*==============================================================*/


drop table if exists alarm;

drop table if exists course;

drop table if exists manager;

drop table if exists personal_task;

drop table if exists student;

drop table if exists task;

drop table if exists teacher;

/*==============================================================*/
/* Table: alarm                                                 */
/*==============================================================*/
create table alarm
(
   alarm_id             varchar(15) not null,
   course_id            varchar(15),
   alarm_time           datetime,
   alarm_remark         text,
   primary key (alarm_id)
);

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   course_id            varchar(15) not null,
   student_id           varchar(15),
   alarm_id             varchar(15),
   teacher_id           varchar(15),
   course_name          varchar(15),
   course_add           varchar(15),
   course_time          varchar(15),
   primary key (course_id)
);

/*==============================================================*/
/* Table: manager                                               */
/*==============================================================*/
create table manager
(
   manager_id           varchar(15) not null,
   manager_name         varchar(15),
   manager_pwd          varchar(15),
   primary key (manager_id)
);

/*==============================================================*/
/* Table: personal_task                                         */
/*==============================================================*/
create table personal_task
(
   ptask_id             varchar(15) not null,
   student_id           varchar(15),
   ptask_name           varchar(15),
   ptask                text,
   ptask_ddl            date,
   ptask_starttime      date,
   primary key (ptask_id)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   student_id           varchar(15) not null,
   manager_id           varchar(15),
   student_name         varchar(15),
   student_pwd          varchar(15),
   student_tel          varchar(15),
   student_email        varchar(15),
   student_gender       varchar(15),
   primary key (student_id)
);

/*==============================================================*/
/* Table: task                                                  */
/*==============================================================*/
create table task
(
   task_id              varchar(15) not null,
   course_id            varchar(15),
   task_name            varchar(15),
   task_content         text,
   task_ddl             date,
   task_starttime       date,
   primary key (task_id)
);

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   teacher_id           varchar(15) not null,
   manager_id           varchar(15),
   teacher_name         varchar(15),
   teacher_pwd          varchar(15),
   teacher_tel          varchar(15),
   teacher_email        varchar(15),
   teacher_gender       varchar(15),
   primary key (teacher_id)
);

alter table alarm add constraint FK_remind foreign key (course_id)
      references course (course_id) on delete restrict on update restrict;

alter table course add constraint FK_learn foreign key (student_id)
      references student (student_id) on delete restrict on update restrict;

alter table course add constraint FK_remind2 foreign key (alarm_id)
      references alarm (alarm_id) on delete restrict on update restrict;

alter table course add constraint FK_teach foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table personal_task add constraint FK_possess foreign key (student_id)
      references student (student_id) on delete restrict on update restrict;

alter table student add constraint FK_manage_students foreign key (manager_id)
      references manager (manager_id) on delete restrict on update restrict;

alter table task add constraint FK_distribute_or_receive_task foreign key (course_id)
      references course (course_id) on delete restrict on update restrict;

alter table teacher add constraint FK_Relationship_3 foreign key (manager_id)
      references manager (manager_id) on delete restrict on update restrict;

