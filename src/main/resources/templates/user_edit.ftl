<html>
<head>
    <title>欢迎进入user用户列表</title>
    <script type="text/javascript" src="/jquery-3.2.1.js"></script>
</head>
<body>
    <form id="editFrm">
        <input type="hidden" name="id" value="${user.id?c}"/>
        名称:<input type="text" name="name" value="${user.name!""}"/><br/>
        出生日期:<input type="date" name="brith" value='<#if user.brith??>${user.brith?string("yyyy-MM-dd")}</#if>'/><br/>
        所在部门:<select name="did">
            <option value="">请选择所在的部门</option>
            <#list depts as dept>
                <option <#if user.did??><#if user.did==dept.id>selected</#if></#if> value="${dept.id}">${dept.name}</option>
            </#list>
        </select><br/>
        邮箱:<input type="email" name="email" value="${user.email!""}"/><br/>
        <!--不能写<button></button> 不能写 type="submit"  采用ajax的写法-->
        <input type="button" onclick="updateObject()" value="编辑用户"/>
    </form>


    <script type="text/javascript">
        function updateObject(){
            $.post('/user/edit',$("#editFrm").serialize(),function(data){
                if(data){
                    alert("编辑成功")
                    window.location.href="/user/list";
                }else{
                    alert("编辑失败")
                }
            },"json");
        }
    </script>
</body>
</html>