<template>
    <!--    <div id="container">-->
    <el-container id="container" >

        <!--        确认注销账号的弹窗-->
        <el-dialog title="您确定要注销账号吗？" v-model="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="请输入密码：" :label-width="formLabelWidth">
                    <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="this.deluser">确 定</el-button>
    </span>
            </template>
        </el-dialog>

        <el-header id="header">
            <el-menu
                    :default-active="activeIndex2"
                    class="el-menu-demo"
                    mode="horizontal"
                    @select="handleSelect"
                    background-color="#2398F0"
                    text-color="#fff"
                    active-text-color="#F0EDA6">

                <el-menu-item index="1"><a href="/index">首页</a></el-menu-item>

                <el-sub-menu index="2">
                    <template #title>个人中心</template>
                    <el-menu-item index="2-1">
                        <el-popconfirm
                                confirmButtonText="确定"
                                cancelButtonText="取消"
                                icon="el-icon-info"
                                iconColor="red"
                                title="您确定要退出登录吗？"
                                @confirm="confirmEvent"

                        >
                            <template #reference>
                                <el-button style="background-color: #87CEEB;border-color: #87CEEB;width: 100%;margin: 0px">退出登录</el-button>
                            </template>
                        </el-popconfirm>
                    </el-menu-item>
                    <el-menu-item index="2-2">
                        <el-button style="background-color: #87CEEB;border-color: #87CEEB;width: 100%;margin: 0px" @click="dialogFormVisible = true">注销账号</el-button>
                    </el-menu-item>
                </el-sub-menu>

                <el-menu-item index="3" style="float: right"><router-link to="/userinfo">
                    <el-avatar v-if="userAvatarUrl" size="90" :src="userAvatarUrl"></el-avatar>
                    <el-avatar v-else size="90" :src="circleUrl"></el-avatar>
                </router-link>
                </el-menu-item>


                <el-menu-item index="3" style="float: right;margin:2px">
                    <el-form-item v-if="user.role"></el-form-item>
                    <el-form-item v-else>
                        <router-link to="/background" >
                            进入后台
                        </router-link>
                    </el-form-item>
                </el-menu-item>

            </el-menu>
        </el-header>
        <!--        </div>-->
        <el-container>
            <el-aside id="aside">
                <!--        <div id="center">-->
                <!--            <div id="left">-->
                <el-menu
                        text-color="#fff"
                        background-color="#2398F0"
                        :router="true"
                        :unique-opened="true">
                    <el-sub-menu index="1">
                        <template #title>
                            <i class="el-icon-setting"></i>
                            设置
                        </template>
                        <el-menu-item index="/userinfo">
                            <i class="el-icon-user"></i>
                            个人信息</el-menu-item>
                        <el-menu-item index="/password">
                            <i class="el-icon-key"></i>
                            修改密码</el-menu-item>

                    </el-sub-menu>
                    <el-sub-menu index="2">
                        <template #title>
                            <i class="el-icon-video-camera-solid"></i>
                            我的视频
                        </template>
                        <el-menu-item index="/myfavovideo">
                            <i class="el-icon-star-off"></i>
                            收藏视频
                        </el-menu-item>

                        <el-menu-item index="/myvideo">
                            <i class="el-icon-video-play"></i>购买视频</el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="3">
                        <template #title>
                            <i class="el-icon-notebook-1"></i>
                            我的博客
                        </template>
                        <el-menu-item index="/myblog">
                            <i class="el-icon-s-promotion"></i>
                            我发表的博客</el-menu-item>
                        <el-menu-item index="/selfBlog">
                            <i class="el-icon-edit"></i>
                            写博客</el-menu-item>
                    </el-sub-menu>


                    <el-sub-menu index="4">
                        <template #title>
                            <i class="el-icon-s-comment"></i>
                            评论管理
                        </template>
                        <el-menu-item index="/myccomment">
                            <i class="el-icon-s-comment"></i>
                            我收到的评论</el-menu-item>
                    </el-sub-menu>

                </el-menu>
            </el-aside>
            <el-main>
                <router-view style="overflow-y: scroll"></router-view>
            </el-main>
        </el-container>
    </el-container>
    <!--    </div>-->
</template>

<script>
    export default {
        name: "Personal",
        data(){
            return{
                user:{},
                dialogFormVisible: false,
                form: {
                    password: '',
                },
                formLabelWidth: '120px',
                circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
                userAvatarUrl:"",
            }
        },
        created() {
            this.user=JSON.parse(sessionStorage.getItem("user"))
            //console.log(this.user)
        },
        mounted() {
            var param={'name':this.user.userName};
            param=this.$qs.stringify(param);
            //console.log(param);
            this.$axios
                .post("user/dofindName",param)
                .then(rst=>{
                    //console.log(rst);
                    this.user=rst.data;
                    this.userAvatarUrl=require("../../assets/"+rst.data.upic)
                })
                .catch(ex=>{
                    console.log(ex);
                })
        },
        methods:{
            error() {
                this.$message({
                    message: '密码输入错误,无法注销！',
                    type: 'error'
                });
            },
            success() {
                this.$message({
                    message: '注销成功！',
                    type: 'success'
                });
            },
            confirmEvent(){
                sessionStorage.removeItem("user");
                location.href = "/index";
            },
            deluser(){
                var param={
                    'userName':this.user.userName,
                    'password':this.form.password}
                param=this.$qs.stringify(param);
                this.$axios
                    .post("user/dodeluser",param)
                    .then(rst=>{
                        if(rst.data==1){
                            this.success();
                            sessionStorage.removeItem("user");
                            this.$router.push("/index");
                        }
                        else{
                            this.error();
                        }
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            }
        }
    }
</script>

<style scoped="scoped">
    #container{
        width: 100%;
        height: 100%;
        display: flex;
        position: fixed;
        flex-direction: column;
    }
    .el-main{
        width: 100%;
        flex: 1;
        display: flex;
        flex-direction: column;
    }
    /*#header{*/
    /*    width: 100%;*/
    /*    height: 80px;*/
    /*    background-color:skyblue;*/
    /*}*/
    /*#footer{*/
    /*    width: 100%;*/
    /*    height: 30px;*/
    /*    !*background-color: darkseagreen;*!*/
    /*}*/
    /*#center{*/
    /*    width: 100%;*/
    /*    flex:1;*/
    /*    display: flex;*/
    /*}*/
    /*#left{*/
    /*    width: 200px;*/
    /*    height: 100%;*/
    /*    background-color:ghostwhite;*/
    /*}*/
    /*#right{*/
    /*    flex:1;*/
    /*    height: 100%;*/
    /*    background-color:#F5F5DC;*/
    /*}*/
    .el-header, .el-footer {
        background-color: #2398f0;
        color: var(--el-text-color-primary);
        text-align: center;
        line-height: 60px;
    }
    .el-aside {
        background-color: #2398f0;
        color: var(--el-text-color-primary);
        text-align: center;
        line-height: 200px;
        width:200px;
    }

    .el-main {
        background-color: rgba(245, 245, 220, 0.02);
        color: var(--el-text-color-primary);
        text-align: left;
        line-height: 160px;
        height: 100%;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
    .el-menu--horizontal>.el-sub-menu {
        float: right;
    }
    >>>.el-form-item__content{
        margin: 8px;
    }
</style>