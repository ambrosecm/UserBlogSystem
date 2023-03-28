<template>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <div id="logincontainer">
        <el-form ref="loginForm" :model="loginForm" :rules="rules">
            <h3 class="logintitle">登录</h3>
            <el-form-item prop="userName" label="账号：">
                <el-input class="input1" type="text" auto-comolete="flase" v-model="loginForm.userName"
                          placeholder="请输入用户名或手机号"></el-input>
            </el-form-item>
            <el-form-item prop="password" label="密码：">
                <el-input class="input1" type="password" auto-comolete="flase" v-model="loginForm.password"
                          placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item prop="checkId" label="验证码：" >
                <div style="display: inline-block">
                    <el-input class="input1" type="text" auto-comolete="flase" v-model="loginForm.checkId"
                              placeholder="请输入验证码" style="width: 120px;display: inline-block" ></el-input>
                    <div class="login-code" @click="refreshCode" style="display: inline-block;cursor: pointer;" >
                        <Identify :identifyCode="identifyCode"></Identify>
                    </div>
                </div>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" class="loginbutton" style="width: 250px;display:block;margin:0 auto" @click="submitlogin">登录</el-button>
            </el-form-item>
            <el-form-item>
                <router-link style="color: black" to="/reg" >没有账号？去注册</router-link>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <router-link style="color: black" to="/userfindpwd" >忘记密码</router-link>
            </el-form-item>
        </el-form>
        </div>
    </div>
</template>

<script>
    import Identify from './identify'
    export default {
        name: "Login",
        components: {
            Identify
        },
        watch:{

        },
        data(){
            return{
                identifyCodes: '1234567890abcdefjhijklinopqrsduvwxyz',
                identifyCode: '',
                loginForm:{
                    userName:'',
                    password:'',
                    checkId:''
                },
                checked:true,
                rules:{
                    userName: [{required:true,message:'请输入用户名！',trigger:'blur'}],
                    password: [{required:true,message:'请输入密码！',trigger:'blur'}],
                    checkId:[{required:true,message:'请输入验证码！',trigger:'blur'}]
                },

            }
        },
        methods:{

            refreshCode () {
                this.identifyCode = ''
                this.makeCode(this.identifyCodes, 4)
            },
            makeCode (o, l) {
                for (let i = 0; i < l; i++) {
                    this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
                }
            },
            randomNum (min, max) {
                return Math.floor(Math.random() * (max - min) + min)
            },
            //登录事件
            submitlogin(){
                if(this.loginForm.checkId.toLowerCase() !== this.identifyCode.toLowerCase()){
                    alert("请填写正确验证码")
                }else{
                    this.$refs.loginForm.validate((valid)=>{
                        if(valid){
                            var param=this.$qs.stringify(this.loginForm);
                            this.$axios
                                .post("user/login",param)
                                .then(successResponse=>{
                                    if(successResponse.data){
                                        console.log(successResponse);

                                        sessionStorage.setItem("user", JSON.stringify(successResponse.data));
                                        // let str=sessionStorage.getItem("info");
                                        // console.log(str);
                                        // etTimeout(() => {
                                        //     location.href= "index"}, 1000)
                                        alert("登录成功！")
                                        this.$router.push("/index");

                                    }else{
                                        console.log(successResponse);
                                        alert("账号或密码错误，登录失败！");
                                    }
                                })
                                .catch(ex=>{
                                    console.log(ex);
                                })
                        }else{
                            this.$message.error('请输入所有字段');
                            return false;
                        }
                    });
                }
            }
        },
        mounted() {
            this.identifyCode = ''
            this.makeCode(this.identifyCodes, 4)
        }
    }
</script>

<style scoped>
    @import 'style.css';
    *{
        margin: 0px;
        padding: 0px;
    }
    #logincontainer{
        width: 400px;
        border: 3px solid black;
        border-radius: 15px;
        padding: 15px 35px 15px 35px;

    }
    .loginbutton{
        width: 150px;
        display:block;
        margin:0 auto;
        background: rgba(255,255,255,0.4);
        border: white;
        color: #777489;
        font-family: fangsong;
    }
    .loginbutton:hover{
        background: #777489;
    }
    .logintitle{
        magin:0px auto 50px auto;
        text-align: center;
        font-size: xx-large;
        font-family: fangsong;
        color: black;
        height: 55px;
    }
    .input1>>>.el-input__inner{
        background: rgba(255, 255, 255, 0.4);
        border-radius: 5px;
        color: black;
    }

    .input1>>>input{
        --el-input-placeholder-color: white;
        --el-input-focus-border: #777489;

    }
    #ghost{
        margin: 0px 0px 0px 0px;
    }

</style>
<style scoped>
    #container{
        width: 100%;
        height: 100%;
        background-color: #43b4ee;
        display: flex;
        background-size:cover;  /* 让背景图基于容器大小伸缩 */
        background-attachment:fixed;
        justify-content: center;
        align-items: center;
        position: fixed;
    }
</style>
