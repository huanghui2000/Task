package com.example.taskio.controller;

import com.example.taskio.Task.TaskOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@Controller
public class IndexController {

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/")
    public String toIndex(Model model) throws Exception {
        model.addAttribute("list", TaskOperation.getTask());
        return "index";
    }

    @ResponseBody
    @GetMapping("/删除")
    public String delNumber(@RequestParam("number") int Number, @RequestParam("Code") String Code) throws Exception {
        if (TaskOperation.getCode(Number).equals(Code)) {
            TaskOperation.deleteTask(Number);
            return "身份码正确,删除成功" + Number;
        } else return "身份码错误,请重新输入" + Number;
    }

    @ResponseBody
    @GetMapping("/开关")
    public String updateSwitch(@RequestParam("number") int Number, @RequestParam("Code") String Code, @RequestParam("Switch") String Switch) throws Exception {
        if (TaskOperation.getCode(Number).equals(Code)) {
            TaskOperation.updateSwitch(Switch, Number);
            return "身份码正确,已经将任务关闭" + Number;
        } else return "身份码错误,请重新输入" + Number;
    }
}