<html>
<head>
    <title>欢迎进入user用户列表</title>
    <script type="text/javascript" src="/jquery-3.2.1.js"></script>
</head>
<body>

    <form id="addFrm" enctype="multipart/form-data" method="post" >
        名称:<input type="text" name="name" id="name_po"/><br/>
        出生日期:<input type="date" name="brith"/><br/>
        所在部门:<select name="did">
                <option value="">请选择所在的部门</option>
                    <#list depts as dept>
                        <option value="${dept.id}">${dept.name}</option>
                    </#list>
                </select><br/>
        邮箱:<input type="email" name="email"/><br/>
        员工照片:<input type="file" name="file" id="file"><br/>
        员工任意张个人照：<input type="file" name="files" id="files" multiple="multiple"><br/>
        <!--不能写<button></button> 不能写 type="submit"  采用ajax的写法-->
        <input type="button" onclick="saveObject()" value="添加用户"/>
    </form>

    <script type="text/javascript">

        //多文件上传
        function saveObject() {
            var ser = $("#addFrm").serialize();
            //封装文件：这里要注意重点来了
            var formData = new FormData();
            //发横转员工证件照
            formData.append("file", $("#file")[0].files[0]);
            //封装员工的任意张个人照
            var files = $("#files")[0].files;
            for(var i=0;i<files.length;i++){
                formData.append("files",files[i]);
            }
            // alert(formData.get("files"))
            // 把表单参数追加到文件对象里
            var splitArr = ser.split("&");
            for ( x in splitArr){
                //等号切分
                var s = splitArr[x].split("=");
                if(s[0]=="name"){
                    formData.append("name",$("#name_po").val());
                } else{
                    formData.append(s[0],s[1]);
                }
            }

            // ajax请求
            $.ajax({
                type: "POST",
                url: "/user/add",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    alert("文件接收成功")
                    window.location.href="/user/list";
                },
                error: function (e) {
                    alert("文件接收失败");
                }
            });

        }

        //单文件上传
        // function saveObject() {
        //     var ser = $("#addFrm").serialize();
        //     //封装文件
        //     var formData = new FormData();
        //     formData.append("file", $("#file")[0].files[0]);
        //     //把表单参数追加到文件对象里
        //     var splitArr = ser.split("&");
        //     for ( x in splitArr){
        //         //等号切分
        //         var s = splitArr[x].split("=");
        //         if(s[0]=="name"){
        //             formData.append("name",$("#name_po").val());
        //         }else{
        //             formData.append(s[0],s[1]);
        //         }
        //     }
        //     // ajax请求
        //     $.ajax({
        //         type: "POST",
        //         url: "/user/add",
        //         data: formData,
        //         processData: false,
        //         contentType: false,
        //         success: function (data) {
        //             alert("文件接收成功")
        //         },
        //         error: function (e) {
        //             alert("文件接收失败");
        //         }
        //     });
        // }

        //普通添加
        // function saveObject(){
        //     $.post('/user/add',$("#addFrm").serialize(),function(data){
        //         if(data){
        //             alert("添加成功")
        //             window.location.href="/user/list";
        //         }else{
        //             alert("添加失败")
        //         }
        //     },"json");
        // }




    </script>
</body>
</html>