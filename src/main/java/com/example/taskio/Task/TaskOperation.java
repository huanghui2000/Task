package com.example.taskio.Task;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class TaskOperation {

    //获取Database连接
    public static SqlSession getDatabase() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    //获取Task列表
    public static List<Task> getTask() throws Exception {
        return getDatabase().getMapper(TextParsing.class).list();
    }

    //获取身份码
    public static void deleteTask(int number) throws Exception {
        SqlSession session = getDatabase();
        session.getMapper(TextParsing.class).delete(number);
        session.commit();
        session.close();
    }

    //获取身份码
    public static String getCode(int number) throws Exception {
        return getDatabase().getMapper(TextParsing.class).getCode(number);
    }

    //更新Switch
    public static void updateSwitch(String Switch, int number) throws Exception {
        String disSwitch = "0";
        if (Switch.equals("0"))
            disSwitch = "1";
        SqlSession session = getDatabase();
        session.getMapper(TextParsing.class).updateSwitch(disSwitch, number);
        session.commit();
        session.close();
    }

    //插入数据
    public static void addTask(String time, String object, String ID, String Code, String Switch, String Things, String number) throws Exception {
        SqlSession session = getDatabase();
        session.getMapper(TextParsing.class).addTask(time, object, ID, Code, Switch, Things, number);
        session.commit();
        session.close();
    }
}
