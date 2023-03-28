<template>
    <div class="container">
        <el-card>
        <el-form ref="editForm" class="editForm" label-width="120px" label-position="left" :rules="rules" >

            <el-form-item label="用户名：">
                {{user.userName}}
            </el-form-item>
            <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8888/user/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :data="{'userName':user.userName}"
            :before-upload="beforeAvatarUpload"
            style="display: inline-block;width: 300px"
            >

                <el-form-item label="点击上传头像：">
                    <el-avatar v-if="userAvatarUrl" size="100" :src="userAvatarUrl"></el-avatar>
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-form-item>
            </el-upload>
                <el-form-item  label="手机号：" prop="phone">
                <el-input type="text" v-model="user.phone"
                          class="input" placeholder="请输入手机号"></el-input>
            </el-form-item>
            <el-form-item label="邮箱：" prop="email">
                <el-input class="input" type="text" v-model="user.email"
                          placeholder="请输入邮箱"></el-input>
            </el-form-item>

            <el-form-item label="性别：">
                <el-radio-group v-model="user.sex">
                    <el-radio :label="0">女</el-radio>
                    <el-radio :label="1">男</el-radio>
                </el-radio-group>
            </el-form-item>


            <el-form-item label="地址：">
                <el-input type="text" v-model="user.address"
                          class="input"></el-input>
            </el-form-item>

            <el-form-item label="出生日期：">
                <el-date-picker
                    v-model="user.ubirth"
                    type="date"
                    placeholder="选择日期"
                    format="YYYY 年 MM 月 DD 日"
                    value-format="YYYY/MM/DD">
                    <span>{{user.ubirth}}</span>
            </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" label="保存" @click="editsave">保存</el-button>
            </el-form-item>


        </el-form>
        </el-card>
    </div>

</template>

<script>
    export default {
        name: "Userinfo",
        data(){
            return{
                user:{},
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                imageUrl:'',
               userAvatarUrl:"",
                rules:{
                    phone:[{required:true,message:'请输入手机号！',trigger:'blur'}],
            }

            }
        },
        methods:{
            errormsg() {
                this.$message.error('错了哦，这是一条错误消息');
            },
            successmsg() {
                this.$message({
                    message: '修改成功！',
                    type: 'success'
                });
            },
            handleAvatarSuccess(res, file) {
                this.imageUrl = URL.createObjectURL(file.raw);
                console.log(URL.createObjectURL(file.raw))
                console.log(this.imageUrl)
                console.log(res)
                this.user.upic=imageUrl;

            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            editsave(){
                console.log( this.user.email);
                console.log( this.user.phone);
                console.log( this.user.ubirth);
                console.log( this.user.sex);
                var param=this.$qs.stringify(this.user);
                this.$axios
                    .post("user/doupdate",param)
                    .then(successResponse=>{
                        if(successResponse){
                            sessionStorage.setItem("user", JSON.stringify(this.user));
                            this.successmsg();
                            this.$router.push({path:'/userinfo'});
                        }else{
                            console.log(successResponse);
                            alert("修改失败！");
                        }
                    })
                    .catch(ex=>{
                        alert("修改失败！");
                        this.errormsg();
                        console.log(ex);
                    })

            }
        },
        created() {
            this.user=JSON.parse(sessionStorage.getItem("user"));
            console.log(this.user);

        },
        mounted() {
            var param={'name':this.user.userName};
            param=this.$qs.stringify(param);
            console.log(param);
            this.$axios
                .post("user/dofindName",param)
                .then(rst=>{
                    console.log(rst);
                    this.user=rst.data;
                    this.userAvatarUrl=require("../../assets/"+rst.data.upic)
                })
                .catch(ex=>{
                    console.log(ex);
                })
        }
    }
</script>

<style scoped>

</style>