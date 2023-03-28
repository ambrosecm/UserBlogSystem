<template>
    <el-container id="container">
        <el-header id="header">
            <el-affix :offset="10">
                <el-row :gutter="20">
                    <el-col :span="4"><div class="grid-content logo">
                        <h1>猿来如此</h1>
                    </div>
                    </el-col>
                    <el-col :span="14" class="hidden-md-and-down">
                        <div class="grid-content">
                            <a href="/videomain">
                                <el-button type="text">视频</el-button>
                            </a>
                            <a href="/mainblog">
                                <el-button type="text">博客</el-button>
                            </a>
                        </div></el-col>
                    <el-col :span="6" class="hidden-md-and-down">
                        <div class="grid-content">
                            <div id="right" v-if="user">
                                <a href="/personal" style="text-decoration : none ">
                                    <h5>欢迎你：{{user.userName}}</h5>
                                </a>
                                <el-button  id="tuichu" type="text" size="mini"  @click="logout">退出</el-button>
                            </div>
                            <div v-else>
                                <a href="/login">
                                    <el-button type="text" size="mini">登录</el-button>
                                </a>
                                <a href="/reg">
                                    <el-button type="text" size="mini">注册</el-button>
                                </a>
                            </div></div></el-col>
                </el-row>
            </el-affix>
        </el-header>
        <el-affix :offset="60">
            <hr/>
        </el-affix>
        <el-main>
            <router-view></router-view>
        </el-main>
    </el-container>


</template>

<script>
    export default {
        name: "Main2",
        data() {

            return {
                user: {}
            }
        },
        created() {
            this.user = JSON.parse(sessionStorage.getItem("user"))
            console.log(this.user)
        },
        methods: {
            logout() {
                sessionStorage.removeItem("user");
                location.href = "/index";
            }
        },

    }
</script>
<style scoped>
    h1{
        font-size: 40px;
        font-weight: normal;
        margin: 5px 10px;
        color: black;
    }
    #header{
        display: flex;
        flex-direction: column;
        background-color: rgba(0,0,0,0);
    }
    #container{
        width: 100%;
        height: 100%;
        background-color: rgba(157, 233, 255, 0.46);
    }
    hr{
        margin: 20px;
        border: 5px solid #1c3245;
        border-top:none;
        color: black;
    }
</style>

<style scoped>
    /deep/.el-button--text {
        border-color: transparent;
        color: #1d1e1f;
        background: 0 0;
        padding-left: 25px;
        padding-right: 25px;
        font-size: 28px;
        margin: 10px;
    }
    /deep/el-button：hover{
        color: #191D18;
        margin: 15px;
        font-size: 35px;
        border-radius: 4px;
        background-image: linear-gradient(rgba(0,0,0,0), rgba(0,0,0,0.3));
        border-bottom-width: 5px;
        border-color: #34495e;
    }
    #right{
        display: flex;
        margin: 20px;
        justify-content: flex-end;
    }
    #tuichu{
        font-size: 20px;
        margin: -10px;
    }
    h5{
        font-size: 20px;
        color: rgba(0, 0, 0, 0.93);
    }

</style>