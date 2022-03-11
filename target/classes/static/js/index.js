// noinspection JSUnusedGlobalSymbols
/*删除确认*/
function del(number) {
    const msg = "您是否要删除此定时任务！";
    if (confirm(msg) === true) {
        firm(number);
    } else {
        return false;
    }
}

function Switch(Switch, number) {
    let msg;
    if (Switch === "1")
        msg = "您是否要关闭此定时任务！";
    else msg = "您是否要打开此定时任务！";
    if (confirm(msg) === true) {
        updateSwitch(Switch, number)
    } else location.reload();
}

/*AJAX实现身份验证*/
let xmlhttp = new XMLHttpRequest();

function firm(number) {
    const msg = prompt("请输入身份码");
    if (msg != null && msg !== "") {
        xmlhttp.open("GET", "/删除?number=" + number + "&Code=" + msg, true);
        xmlhttp.onreadystatechange = Result;
        xmlhttp.send(null);
    } else
        return false;
}

function updateSwitch(Switch, number) {
    const msg = prompt("请输入身份码");
    if (msg != null && msg !== "") {
        xmlhttp.open("GET", "/开关?number=" + number + "&Code=" + msg + "&Switch=" + Switch, true);
        xmlhttp.onreadystatechange = Result;
        xmlhttp.send(null);
    } else location.reload();
}

/*身份验证反馈*/
function Result() {
    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
        if (xmlhttp.response.slice(0, 10) === "身份码正确,删除成功") {
            alert("身份码正确,删除成功");
            location.reload();
        } else if (xmlhttp.response.slice(0, 11) === "身份码正确,已经将任务") {
            alert(xmlhttp.response);
            location.reload();
        } else if (xmlhttp.response.slice(1, 11) === "身份码错误,无法操作") {
            alert(xmlhttp.response);
            updateSwitch(xmlhttp.response.slice(0, 1), xmlhttp.response.slice(11, xmlhttp.response.length));
        } else {
            alert("身份码错误,请重新输入");
            firm(xmlhttp.response.slice(11, xmlhttp.response.length));
        }
}

/*点击‘+’处理*/
function appear() {
    document.getElementById("add").style.display = "none"
    document.getElementById("put").style.display = "block";
    document.getElementById("cover").style.display = "block";
    document.getElementById("cover").style.height = document.getElementById("center").clientHeight + "px";
    window.scrollTo(0, document.body.scrollHeight)
}

/*点击‘输入板关闭’处理*/
function disappear() {
    document.getElementById("put").style.display = "none"
    document.getElementById("add").style.display = "block";
    document.getElementById("cover").style.display = "none";
    window.scrollTo(0, document.body.scrollHeight)
}