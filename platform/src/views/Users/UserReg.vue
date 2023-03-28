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
                <h3 class="regtitle">注册</h3>
                <el-form-item label="用户名：" prop="userName">
                        <el-input type="text"  v-model="regForm.userName" @blur="checkuserName"
                                  class="input" placeholder="请输入用户名"></el-input>

                </el-form-item>
                <el-form-item  label="手机号：" prop="phone">
                        <el-input type="text" v-model="regForm.phone"
                                  class="input" placeholder="请输入手机号"></el-input>
                </el-form-item>
                <el-form-item  label="密码：" prop="password">
                        <el-input type="password" v-model="regForm.password"
                                  class="input" placeholder="请输入密码"></el-input>
                </el-form-item>

                <el-form-item  label="再次输入：" prop="checkPass">
                        <el-input type="password" v-model="regForm.checkPass"
                                  class="input" placeholder="请再次输入密码"></el-input>
                </el-form-item>

                <el-form-item label="邮箱：" prop="email">
                        <el-input class="input" type="text" v-model="regForm.email"
                                  placeholder="请输入邮箱"></el-input>
                </el-form-item>

                <el-form-item label="性别：">
                    <el-radio v-model="regForm.sex" label=1>男</el-radio>
                    <el-radio v-model="regForm.sex" label=0>女</el-radio>
                </el-form-item>


<!--                <el-form-item label="性别"  prop="sex">-->
<!--                        <el-select v-model="regForm.sex.value" placeholder="请选择">-->
<!--                            <el-option-->
<!--                                    v-for="s in regForm.sex"-->
<!--                                    :key="s.value"-->
<!--                                    :label="s.label"-->
<!--                                    :value="s.value">-->
<!--                            </el-option>-->
<!--                        </el-select>-->
<!--                </el-form-item>-->


                <el-form-item>
                    <el-button type="primary" class="regbutton" style="width: 250px;display:block;margin:0 auto" @click="submitreg">注册</el-button>

                </el-form-item>
                <el-form-item>
                    <router-link to="/login" style="color: black">已有账号？去登录</router-link>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "UserReg",
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
                regForm:{
                    checkPass: '',
                    userName:'',
                    phone:'',
                    password:'',
                    email:'',
                    sex:'1',
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
                    phone:[{required:true,message:'请输入手机号！',trigger:'blur'}],
                    sex:[{required:true,message:'请选择性别！',trigger:'blur'}],
                    checkPass: [{ validator: validatePass2, trigger: 'blur' }]

                },
            }
        },
        methods:{
            checkuserName(){
                if (this.regForm.userName) {
                    var param={'name':this.regForm.userName};
                    param=this.$qs.stringify(param);
                    this.$axios
                        .post("user/findByname",param)
                        .then(Response=>{
                            if(Response.data){

                                console.log(Response);
                                alert("用户名已存在，请更换用户名！");

                            }else{
                                console.log(Response);
                                //alert("没有！");
                            }
                        })
                        .catch(ex=>{
                            console.log(ex);
                        })
                }
            },
            submitreg() {
                this.$refs.regForm.validate((valid)=>{
                    if(valid){
                        var param=this.$qs.stringify(this.regForm);
                        this.$axios
                            .post("user/reg",param)
                            .then(successResponse=>{
                                if(successResponse.status===200){
                                    console.log(successResponse);
                                    this.$router.push({path:'/login'});
                                }else{
                                    console.log(successResponse);
                                    alert("注册失败，请重新输入用户名！");
                                }
                            })
                            .catch(ex=>{
                                alert("注册失败！");
                                console.log(ex);
                            })
                    }else{
                        this.$message.error('请正确输入所有字段！');
                        return false;
                    }
                });
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