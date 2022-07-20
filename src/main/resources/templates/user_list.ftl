<html>
<head>
    <title>欢迎进入user用户列表</title>
    <script type="text/javascript" src="/jquery-3.2.1.js"></script>
</head>
<body>
    <a href="/user/toAdd">添加用户</a>
    <a href="javascript:deleteByIds()">批量删除</a>
    <form action="../user/list" method="post">
        名称:<input type="text" name="name" value="${user.name!""}"/>
        出生日期:<input type="date" name="brith"  value="<#if user.brith??>${user.brith?string("yyyy-MM-dd")}</#if>"/>
        <button>搜索</button>
    </form>
    <table>
       <thead>
       <tr>
           <th><input type="checkbox" id="chb"/></th>
           <th>序号</th>
           <th>名称</th>
           <th>部门</th>
           <th>年龄</th>
           <th>出生日期</th>
           <th>邮箱</th>
           <th>工作证件照</th>
           <th>操作</th>
       </tr>
       </thead>

       <tbody>
          <#list pageInfo.records as user>
           <tr>
               <td><input type="checkbox" class="chbx" value="${user.id}"/></td>
               <td>${user.id!""}</td>
               <td>${user.name!""}</td>
               <td>${user.dname!""}</td>
               <td>${user.age!""}</td>
               <td>
                   <#if user.brith??>
                       ${user.brith?string("yyyy-MM-dd")}
                       <#else>
                   </#if>
               </td>
               <td>${user.email!""}</td>
               <td><img src="${user.zp!""}" width="100px" height="100px"></td>
               <td>
                   <a href="/user/detail?id=${user.id?c}">详情</a>
                   <a href="/user/toEdit?id=${user.id?c}">修改</a>
                   <a href="javascript:deleteById(${user.id?c})">删除</a>
               </td>
           </tr>
          </#list>

       <tr>
           <td colspan="10" center>
               <a href="/user/list?current=1">首页</a>
               <a href="/user/list?current=${pageInfo.current-1}">上一页</a>
               <a href="/user/list?current=${(pageInfo.current+1>=pages)?string(pages,pageInfo.current+1)}">下一页</a>
               <a href="/user/list?current=${pages}">尾页</a>
           </td>

       </tr>

    </table>

    <script>
        $("#chb").click(function (){
            $(".chbx").prop("checked",this.checked);
        })

        function deleteByIds(){
            var ids = $(".chbx:checked").map(function(){
                return this.value;
            }).get().join(",");

            if(ids!=""){
                var flag = window.confirm("你确定要删除选中的数据吗?");
                if(flag){
                    deleteById(ids);
                }else{
                    alert("取消删除");
                }
            }else{
                window.alert("请选择要删除的数据");
            }
        }

        function deleteById(ids){
            $.post('/user/delete',{ids:ids},function(data){
                if(data){
                    alert("删除成功")
                    window.location.reload();
                }else{
                    alert("删除失败");
                }
            },"json");
        }
    </script>

</body>
</html>