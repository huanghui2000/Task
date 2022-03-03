package com.example.taskio.Task;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 对于TaskDatabase的各种操作
 */

public interface TextParsing {
    //查询出整个Task
    @Select("select * from task")
    List<Task> list();

    //查询身份码
    @Select("select Code from task where Number = #{number}")
    String getCode(int number);

    //删除指定Task
    @Delete("delete from task where Number = #{number}")
    void delete(int number);

    //更新Switch
    @Update("UPDATE task SET Switch = #{disSwitch}  WHERE Number = #{number}")
    void updateSwitch(@Param("disSwitch") String disSwitch, @Param("number") int number);

    //插入新Task
    @Insert("insert into task values( #{time},  #{object}, #{ID}, #{ Code}, #{ Switch}, #{Things},#{ Number});")
    void addTask(@Param("object") String object, @Param("ID") String ID, @Param("Code") String Code, @Param("Switch") String Switch, @Param("Things") String Things, @Param("time") String time, @Param("Number") String Number);
}
