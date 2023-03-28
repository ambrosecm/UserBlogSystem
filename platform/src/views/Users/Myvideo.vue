<template>
    <div id="courseList1">
        <el-row :gutter="20">
            <el-col :span="6" :offset="1" :data="list" v-for="item in list">
                <el-card :body-style="{ padding: '0px' }" >
                    <div @click="gotoplay(item.vid)">
                        <img src="https://i.loli.net/2021/08/27/i45TYQZxGIBzaD8.jpg" class="image" >
                    </div>
                    <div style="padding: 0px;">
                        <div class="bottom clearfix" >
                            <span>{{ item.vtitle }}</span>
                        </div>
                        <div class="bottom clearfix">
                            <time class="time">{{ item.vdescription }}</time>
<!--                            <el-button  class="button el-icon-video-play" type="primary">取消收藏</el-button>-->
                            <el-button  class="button el-icon-video-play" type="primary" @click="gotoplay(item.vid)">播放</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
<!--        <v-else>-->
<!--            您还没有购买视频哦！-->
<!--        </v-else>-->

        <div id="page_content">
            <el-pagination
                    background
                    layout="prev,pager,next"
                    :total="pageTotle"
                    :page-size="pageSize"
                    @current-change="pageData">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Myvideo",
        data(){
            return{
                user:{},
                list:[],
                pageNum:1,
                pageTotle:0,
                pageSize:6,
                currentData:new Date()

            }
        },
        created() {
            this.user=JSON.parse(sessionStorage.getItem("user"))
            console.log(this.user)
        },
        mounted() {
            // var param={'uid':this.user.uid,
            //             'pageNum':this.pageNum,
            //             'pageSize':this.pageSize};
            // param=this.$qs.stringify(param);
            // console.log(param);
            // this.$axios
            //     .post("user/findByUid",param)
            //     .then(rst=>{
            //         //console.log(rst);
            //         this.list=rst.data;
            //         this.pageTotle=this.list.total;
            //         console.log(this.list)
            //     })
            //     .catch(ex=>{
            //         console.log(ex);
            //     })
            this.init();
        },
        methods:{
            init(){
                var param={'uid':this.user.uid,
                    'pageNum':this.pageNum,
                    'pageSize':this.pageSize};
                param=this.$qs.stringify(param);
                console.log(param);
                this.$axios
                    .post("user/findByidbuy",param)
                    .then(rst=>{
                        console.log(rst);
                        this.list=rst.data.list;
                        this.pageTotle=rst.data.total;
                        //console.log(this.list)
                    })
                    .catch(ex=>{
                        console.log(ex);
                    })
            },
            pageData(curr){
                this.pageNum=curr;
                this.init();
            },
            gotoplay(vid){
                this.$router.push({
                    path:'videoPlay',
                    query:{
                        vid:vid
                    }
                });
            }
        }
    }
</script>

<style scoped>

    #spanclass{
        height: 20px;
    }

    #courseList .el-row{
        padding-bottom: 20px;
        padding-top: 20px;
        padding-left: 100px;
        /*width: 100%;*/
        overflow-y:scroll;
        overflow-x:hidden;
        height: calc(100%);
    }

    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .button {
        padding: 0;
        float: right;
    }

    .el-card{
        transform: scale(1);
        height: 100%;
    }
    .el-card:hover{
        transform: scale(1.15);
    }

    .image {
        height: 200px;
        width: 100%;
        display: block;
        /*transform: scale(1);*/
        /*transition: transform 1s ease 0s;*/
    }
    .image:hover {
        /*transform: scale(1.25);*/
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
</style>