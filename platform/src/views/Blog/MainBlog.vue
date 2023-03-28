<template>
    <el-container id="container">
        <div  style="background-color: rgba(0,0,0,0)" >
            <div id="recommend" style="background-color: rgba(0,0,0,0)">
                <el-carousel>
                    <el-carousel-item v-for="item in 5" :key="item">
                        <h3 class="small">{{ item }}</h3>
                    </el-carousel-item>
                </el-carousel>
            </div>
            <el-row type="flex" justify="center" :gutter="20" >
                <el-col :span="4" class="hidden-md-and-down" >
                </el-col>
                <el-col :span="16">
                    <div>
                        <el-card v-for="item in blogData" :key="item">
                            <div style="margin: 30px 0" >
                                <div style="margin-bottom: 10px; font-size: 16px; cursor: pointer;" @click="goblog(item.bid)">
                                    <strong>{{item.btitle}}</strong>
                                </div>
                                <el-row :gutter="20">
                                    <el-col :span="20">
                                        <div style="height: 50px">
                                            <span style="color: #6b6b6b">
                                                作者：{{item.user.userName}} &nbsp;&nbsp;
                                                阅读数：{{item.bviewNum}}&nbsp;&nbsp;
                                                点赞数：{{item.bpraise}}&nbsp;&nbsp;
                                                时间：{{item.btime}}&nbsp;&nbsp;
                                            </span>
                                            <a  target="_blank">
                                                <el-button style="float: right" type="warning" size="mini" icon="el-icon-view" @click="goblog(item.bid)" round>阅读文章</el-button>
                                            </a>
                                        </div>
                                    </el-col>
                                </el-row>

                            </div>
                            <!-- 分页 -->
                        </el-card>

                        <div id="page_content">
                            <el-pagination
                                    background
                                    layout="total,sizes,prev, pager, next,jumper"
                                    :total="pageTotal"
                                    :page-sizes="[2,4,6,8]"
                                    :page-size="pageSize"
                                    @size-change="handleSizeChange"
                                    @current-change="pageData"
                                    :current-change="pageNum"
                            >
                            </el-pagination>
                        </div>
                    </div>
                </el-col>
                <el-col :span="4" class="hidden-md-and-down">
                    <div id="search">
                        <el-input v-model="search.searchParam" placeholder="请输入内容" style="width: 300px">
                            <template #append>
                                <el-button  icon="el-icon-search" @click="gotoSelect(this.search.searchParam,1)">搜索</el-button>
                            </template>
                        </el-input>

                    </div>
                    <div style="margin: 20px 0">
                        <el-card>
                            <div style="border-bottom: 1px solid gray; padding-bottom: 10px; font-size: 20px">标签分类</div>
                            <div style="margin-top: 10px">
                                <a @click="gotoSelect(item.tid,2)" v-for="item in tags" style="cursor: pointer;">
                                    <el-tag style="margin: 5px">{{item.tname}}</el-tag>
                                </a>
                            </div>
                        </el-card>
                    </div>
                </el-col>
            </el-row>

        </div>
    </el-container>

</template>

<script>
    export default {
        name: "MainBlog",
        data() {
            return {
                src: 'blog.html',
                search:{
                    searchParam:''
                },
                blogData: [],
                pageNum: 1,
                pageSize: 6,
                pageTotal: 0,
                tags: [],
                tag: ''
            }
        },
        methods:{
            handleSizeChange(val){
                console.log(`每页${val}条`);
                this.pageSize=val;
                this.init();
            },
            gotologin(){
                this.$router.push({
                    path:'/login',
                });
            },
            goblog(bid){
                var param={bid:bid};
                var params=this.$qs.stringify(param);
                this.$axios
                    .post("blog/viewBlog",params)
                    .then(rst=>{
                        console.log(rst);
                    }).catch(ex=>{
                    console.log(ex);
                });
                this.$router.push({
                    path:'/blog',
                    query:{
                        bid:bid
                    }
                });
            },
            gotoSelect(param,b){
                this.$router.push({
                    path:'/selectblog',
                    query:{
                        param:param,
                        paramtype:b
                    }
                });
            },
            init(){
                this.search.pageNum=this.pageNum;
                this.search.pageSize=this.pageSize;
                var param=this.$qs.stringify(this.search);
                this.$axios
                    .post("blog/findBlogByParam",param)
                    .then(rst=>{
                        console.log(rst);
                        this.blogData=rst.data.list;
                        this.pageTotal=rst.data.total;
                    }).catch(ex=>{
                    console.log(ex);
                });
                this.$axios
                    .post("blog/findAllTag")
                    .then(rst=>{
                        console.log(rst);
                        this.tags=rst.data;
                    }).catch(ex=>{
                    console.log(ex);
                });

            },

            pageData(curr){
                this.pageNum=curr;
                this.init();
            }

        },
        created() {
            this.pageNum=1;
            this.init();
        }

    }
</script>

<style scoped>
    #container{

        width: 100%;
        height: 100%;
        /*display: flex;*/
        top: 100px;
        bottom: 100px;

        overflow-y: scroll;
    }

    /*轮播图开始/////////////////////////////////////////////////////////////////////////*/
    .el-carousel{
        width: 100%;
        height: 100%;
        padding: 0;
        margin: 0;
    }
    #recommend{
        width: 100%;
        height: 300px;
        padding: 0;
        margin: 0;
    }
    .el-carousel__item h3 {
        color: #475669;
        text-align: center;
        font-size: 120px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
    /deep/.el-carousel__item{
        width: 1200px;
        margin: 0 328px 0;
    }
    /*轮播图结束/////////////////////////////////////////////////////////////////////////*/



    /*卡片样式开始/////////////////////////////////////////////////////////////////////////*/
    #courseList .el-row{
        padding-bottom: 20px;
        padding-top: 20px;
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
    }
    .el-card:hover{
        transform: scale(1.02);
    }

    .image {
        height: 250px;
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
    /*卡片样式结束/////////////////////////////////////////////////////////////////////////*/
   #test{
        background-image:url("../../assets/tu2.jpg") ;
        height: 100%;
        width: 100%;

    }

</style>
<style scoped>

</style>