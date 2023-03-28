<template>
    <el-container id="container1">
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
                                <a href="/userinfo" style="text-decoration : none ">
                                    <h5>欢迎你：{{user.userName}}</h5>
                                </a>

                                <el-popconfirm
                                        confirmButtonText="确定"
                                        cancelButtonText="取消"
                                        icon="el-icon-info"
                                        iconColor="red"
                                        title="您确定要退出登录吗？"
                                        @confirm="logout"

                                >
                                    <template #reference>
                                        <el-button id="tuichu" type="text" size="mini">退出</el-button>
                                    </template>
                                </el-popconfirm>
<!--                                <el-button  id="tuichu" type="text" size="mini"  @click="logout">退出</el-button>-->
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
        <el-affix :offset="10">
            <hr/>
        </el-affix>
        <svg class="editorial" xmlns="http://www.w3.org/2000/svg" viewBox="0 24 150 28" preserveAspectRatio="none">
            <defs>
                <path
                        id="gentle-wave"
                        d="m -160,44.4 c 30,0 58,
        -18 87.7,-18 30.3,0 58.3,
        18 87.3,18 30,0 58,-18 88,
        -18 30,0 58,18 88,18 l 0,
        34.5 -351,0 z" />
            </defs>
            <g class="parallax">
                <use xlink:href="#gentle-wave" x="50" y="0" fill="#4579e2"/>
                <use xlink:href="#gentle-wave" x="50" y="3" fill="#3461c1"/>
                <use xlink:href="#gentle-wave" x="50" y="6" fill="#2196F3"/>
            </g>
        </svg>
        <div class="content">
            <div id="mingyan">
            <h2>为每个想学习知识的人提供一个少走弯路的平台</h2>
            <h3>生活原本沉闷，但跑起来就有风</h3>
            </div>
            <a href="/mainblog">
            <el-button size="large" type="success">博客</el-button>
            </a>
            <el-button style="background-color: rgba(0,0,0,0)" type="text">   </el-button>
            <a href="/videomain">
            <el-button type="success">视频</el-button>
            </a>
        </div>
    </el-container>


</template>

<script>
    export default {
        name: "Index",
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
        color: white;
        font-family: fangsong;
    }
    hr{
        margin: 20px;
        border: 3px solid #d8eeff;
        border-top:none;
    }
</style>

<style lang="css" scoped>
    /deep/.el-button--text {
        border-color: transparent;
        color: #ffffff;
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
    /deep/.btn {
        display: inline-block;
        line-height: 35px;
        margin: 8px;
        padding: 0 15px;
        font-size: 15px;
        position: relative;
        opacity: .999;
        border-radius: 3px;}

    .btn-fill-vert-o {
        background-color: transparent;
        border: 1px solid #d0d0d0;
        color: #B8B8B8;
        overflow: hidden;
    }


</style>
<style scoped>
    .parallax > use {
        animation: move-forever 12s linear infinite;
        transform-origin: 50% 50%;
    }
    .parallax > use:nth-child(1) {
        animation-delay: -2s;
    }
    .parallax > use:nth-child(2) {
        animation-delay: -2s;
        animation-duration: 5s;
    }
    .parallax > use:nth-child(3) {
        animation-delay: -4s;
        animation-duration: 3s;
    }

    @keyframes move-forever {
        0% {
            transform: translate(-90px, 0%);
        }
        100% {
            transform: translate(85px, 0%);
        }
    }
    .editorial {
        display: block;
        width: 100%;
        height: 10em;
        margin: 0;
        margin-top: 220px;
    }

    #container1{
        background-image: linear-gradient(#2398f0, #aae2e5);
        margin: 0;
    }

    .content {
        font-family: 'Lato',sans-serif;
        text-align: center;
        background-image: linear-gradient(#2196f3, #1973f3);
        text-align: center;
        min-height: 75vh;
        margin: 0;
        padding: 1em;
        color: #eee;
        font-size: 2em;
        font-weight: 300;
    }

    .content {
        font-size: 1em;
    }

    .content h1 {
        margin-top: -1.3em;
        font-weight: 100;
        font-size: 5em;
        user-select: none;
        cursor: default;
    }

    @media (max-width: 50em) {
        .content h1 {
            font-size: 12vmax;
        }

        .editorial {
            height: 15vw;
        }
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
        color: rgba(255, 255, 255, 0.93);
    }
    #mingyan{
        margin-top: -140px;
        padding: 10px;
    }
    h2{
        font-family: 黑体;
        padding: 10px;
        font-size: 40px;
    }
    h3{
        font-family: 黑体;
        padding: 10px;
        font-size: 30px;
    }
</style>

