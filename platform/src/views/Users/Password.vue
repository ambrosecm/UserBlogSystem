<template>
    <el-card class="box">
    <el-form status-icon :model="pwdForm" :rules="rules" ref="pwdForm" label-width="120px" class="demo-ruleForm">
        <el-form-item label="请输入旧密码：" prop="oldpwd">
            <el-input type="password" v-model="pwdForm.oldpwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入新密码：" prop="newpwd">
            <el-input type="password" v-model="pwdForm.newpwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码：" prop="checkPass">
            <el-input type="password" v-model="pwdForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm">修改</el-button>
        </el-form-item>
    </el-form>
    </el-card>
</template>

<script>
    export default {
        name: "Personal",
        data(){
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.pwdForm.checkPass !== '') {
                        this.$refs.pwdForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.pwdForm.newpwd) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return{
                user:{},
                pwdForm:{
                    oldpwd:"",
                    newpwd:"",
                    checkPass:"",
                },
                checked:true,
                rules:{
                    newpwd:[
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass:[
                        { validator: validatePass2, trigger: 'blur' }
                    ]
                }

            }

        },
        methods:{
            nullerror() {
                this.$message({
                    message: '请输入所有字段！',
                    type: 'warning'
                });
            },
            error() {
                this.$message({
                    message: '原密码输入错误！',
                    type: 'error'
                });
            },
            success() {
                this.$message({
                    message: '修改成功！',
                    type: 'success'
                });
            },
            submitForm() {
                this.$refs.pwdForm.validate((valid)=>{
                    if(valid){
                        var param={
                            'npwd':this.pwdForm.newpwd,
                            'opwd':this.pwdForm.oldpwd,
                            'name':this.user.userName};
                        param=this.$qs.stringify(param);
                        this.$axios
                            .post("user/doupdatepwd",param)
                            .then(rst=>{
                                if(rst.data==1){
                                    this.success();
                                }
                                else{
                                    this.error();
                                }
                            })
                            .catch(ex=>{
                                console.log(ex);
                            })
                            //this.success();
                        //alert(111);
                    } else {
                        this.nullerror();
                        return false;
                    }
                });
            },


        },
        created() {
            this.user=JSON.parse(sessionStorage.getItem("user"))
            console.log(this.user)
        }
    }
</script>

<style scoped>
    .el-card {
        height:100%;
    }

</style>