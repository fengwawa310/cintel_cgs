/**
 * Created by Administrator on 2018/1/2.
 */
function login(){
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    // 用户名和密码都不为空
    if(username&&password){
        document.getElementById('username').value = username;
        document.getElementById('password').value = password;
        location.href = "http://localhost:8080/mana_web/login/login.do"; //实现跳转
        //$("#loginForm").submit();
    }
}
$('#loginForm').bootstrapValidator({
    message: '这个值没有被验证',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        Username: {
            message: '用户名还没有验证',
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 16,
                    message: '用户名长度在1到16位之间'
                }
            }
        },
        Password: {
            message: '密码还没有验证',
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                stringLength: {
                    min: 6,
                    max: 16,
                    message: '密码长度在6到16之间'
                },
                different: {
                    field: 'Username',
                    message: '密码不能和用户名相同'
                }
            }
        }
     
       
    }
});