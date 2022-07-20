<html>
<head>
    <title>欢迎进入user用户列表</title>
    <script type="text/javascript" src="/jquery-3.2.1.js"></script>
</head>
<body>
    名称:${user.name!""}<br/>
    出生日期:<#if user.brith??>${user.brith?string("yyyy-MM-dd")}</#if><br/>
    所在部门:${user.dname!""}<br/>
    邮箱:${user.email!""}<br/>
    <a href="javascript:history.go(-1)">返回</a>
</body>
</html>