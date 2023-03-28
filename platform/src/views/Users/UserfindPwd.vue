<template>
    <div id="container" class="html body">
        <div class="ghost">
            <div class="body">
                <div class="face">
                    <div class="eye left"></div>
                    <div class="eye right"></div>
                    <div class="smile"></div>
                    <div class="rosy left"></div>
                    <div class="rosy right"></div>
                </div>
                <div class="arm left"></div>
                <div class="arm right"></div>
                <div class="bottom">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
            <div class="shadow"></div>
        </div>
        <div id="regcontainer">
            <el-form ref="regForm" class="regForm" label-width="100px" label-position="left"
                     :model="regForm" :rules="rules" >
                <h3 class="regtitle">找回密码</h3>
                <el-form-item label="用户名：" prop="userName">
                    <el-input type="text"  v-model="regForm.userName" @blur="checkuserName"
                              class="input" placeholder="请输入用户名"></el-input>
                </el-form-item>

                <el-form-item label="邮箱：" prop="email">
                    <el-input class="input" type="text" v-model="regForm.email"
                              placeholder="请输入邮箱" id="email" name="email"></el-input>
                    <el-button @click="getEmailCode" id="getEmailCode" :class="{disabled: !this.canClick}">{{content}}</el-button>
                </el-form-item>
                <el-form-item label="输入验证码：" >
                    <el-input type="text"  v-model="regForm.passage"
                              class="input" id="captcha" name="captcha" style="display: inline" required>
                    </el-input>
                </el-form-item>

                <el-form-item  label="密码：" prop="password">
                    <el-input type="password" v-model="regForm.password"
                              class="input" placeholder="请输入密码"></el-input>
                </el-form-item>

                <el-form-item  label="再次输入：" prop="checkPass">
                    <el-input type="password" v-model="regForm.checkPass"
                              class="input" placeholder="请再次输入密码"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" class="regbutton" style="width: 250px;display:block;margin:0 auto" @click="submitreg">确定</el-button>
                </el-form-item>

            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "UserfindPwd",
        data(){
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.regForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            //  var validuserName=(rule,value,callback)=>{
            //
            //     if (value === ''){
            //         callback(new Error('请输入用户名'));
            //     }else if (){
            //
            //     }
            // };

            return{
                content:'发送验证码',
                totalTime: 90,
                canClick: true,
                regForm:{
                    checkPass: '',
                    userName:'',
                    password:'',
                    email:'',
                    passage:''
                    // sex: [{
                    //     value: 1,
                    //     label: '男'
                    // }, {
                    //     value: 0,
                    //     label: '女'
                    // }]
                },
                checked:true,
                rules:{
                    userName: [{required:true,message:'请输入用户名！',trigger:'blur'}],
                    password: [{required:true,message:'请输入密码！',trigger:'blur'}],
                    email:[{required:true,message:'请输入邮箱！',trigger:'blur'},
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }],
                    checkPass: [{ validator: validatePass2, trigger: 'blur' }]

                },
            }
        },
        methods:{
            getEmailCode(){
                if(this.regForm.email==''){
                    alert("请先输入您的邮箱");
                }else{
                    var param={
                        email:this.regForm.email
                    }
                    var params=this.$qs.stringify(param);
                    this.$axios
                    .post("user/getEmailCode",params)
                    .then(rst=>{
                        if(rst) {
                            alert("邮箱验证码发送成功！");
                            sessionStorage.setItem("passage", rst.data);
                            if (!this.canClick) return  //改动的是这两行代码
                            this.canClick = false
                            this.content = this.totalTime + 's后重新发送'
                            let clock = window.setInterval(() => {
                                this.totalTime--
                                this.content = this.totalTime + 's后重新发送'
                                if (this.totalTime < 0) {
                                    window.clearInterval(clock)
                                    this.$axios
                                        .post("user/deleteCode")
                                        .then(rst=>{

                                        })
                                        .catch(ex=>{
                                            console.log(ex);
                                        });
                                    this.content = '重新发送验证码'
                                    this.totalTime = 10
                                    this.canClick = true  //这里重新开启
                                }
                            },1000)

                        }
                    }).catch(ex=>{
                        console.log(ex);
                    });
                }
            },
            submitreg() {
                var passage=sessionStorage.getItem("passage");
                    if(passage==this.regForm.passage){
                        this.$refs.regForm.validate((valid)=>{
                            if(valid){
                                var param=this.$qs.stringify(this.regForm);
                                this.$axios
                                    .post("user/updatepassword",param)
                                    .then(successResponse=>{
                                        if(successResponse){
                                            console.log(successResponse);
                                            alert("修改成功！")
                                            this.$router.push({path:'/login'});
                                        }else{
                                            console.log(successResponse);
                                            alert("修改失败,请检查您的验证码和用户名是否正确");
                                        }
                                    })
                                    .catch(ex=>{
                                        alert("修改失败,请检查您的验证码和用户名是否正确");
                                        console.log(ex);
                                    })
                            }else{
                                this.$message.error('请正确输入所有字段！');
                                return false;
                            }
                        });
                    }else{
                        alert("验证码错误，修改失败！");
                    }

            }
        }
    }
</script>

<style scoped>
    @import 'style.css';
    *{
        margin: 0px;
        padding: 0px;
    }
    .disabled{
        border-color: #ddd;
        color:#57a3f3;
        cursor: not-allowed;
    }
    #container{
        width: 100%;
        height: 100%;
        background-image:url("../../assets/login2.jpg") ;
        display: flex;
        background-size:cover;  /* 让背景图基于容器大小伸缩 */
        background-attachment:fixed;
        justify-content: center;
        align-items: center;
        position: fixed;
    }
    #regcontainer{
        width: 400px;
        border: 3px solid black;
        border-radius: 15px;
        padding: 15px 35px 15px 35px;
    }
    .regtitle{
        magin:0px auto 40px auto;
        text-align: center;
        font-size: xx-large;
    }
    .regForm{
        margin: 0px;
    }
    button{
        text-align: center;
        width: 100px;
        display:block;
        margin:0 auto;
        background: rgba(255,255,255,0.4);
        border: black;
        color: #777489;
        font-family: fangsong;
    }

    .regbutton:hover{
        background: #777489;
        font-family: fangsong;
    }
    .regtitle{
        magin:0px auto 40px auto;
        text-align: center;
        font-size: xx-large;
        font-family: fangsong;
        color: white;
        height: 55px;
    }
    .input>>>.el-input__inner{
        background: rgba(255, 255, 255, 0.4);
        border-radius: 5px;
        color: black;
    }

    .input>>>input{
        --el-input-placeholder-color: white;
        --el-input-focus-border: #777489;

    }
</style>