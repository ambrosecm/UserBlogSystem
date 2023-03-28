<template>
    <!--    <el-card style="margin: 0px;padding:0px" class="box">-->
    <!--&lt;!&ndash;        <el-row style="height:0px;border-bottom: 1px solid gray; padding: 10px 0; font-size: 20px">我的博客</el-row>&ndash;&gt;-->
    <!--        <div class="cardi" style="margin: 0px 0px;padding:0px 0px;height: 300px;" v-for="(item) in blogData" :key="item">-->
    <!--            <div style="margin-bottom: 0px; font-size: 16px; height: 100px">-->
    <!--                <strong>{{item.btitle}}</strong>-->
    <!--            </div>-->
    <!--            <el-row :gutter="20">-->
    <!--                <el-col :span="8">-->
    <!--&lt;!&ndash;                    <a :href="'http://localhost:8888/page/front/detail.html?id=' + item.id" target="_blank">&ndash;&gt;-->
    <!--                        <img src="https://i.loli.net/2021/08/27/i45TYQZxGIBzaD8.jpg" style="width: 100%; height: 200px; border-radius: 5px" class="image">-->
    <!--&lt;!&ndash;                    </a>&ndash;&gt;-->
    <!--                </el-col>-->
    <!--                <el-col :span="16">-->
    <!--&lt;!&ndash;                    <div style="color: #6b6b6b; height: 50px; margin-bottom: 10px">{{item.description}}</div>&ndash;&gt;-->
    <!--                    <div style="height: 50px">-->
    <!--                       <el-col  v-for="t in item.tg">-->
    <!--                           <el-tag>{{t.tname}}</el-tag>-->
    <!--                       </el-col>-->

    <!--                    </div>-->
    <!--                    <div style="height: 50px">-->
    <!--                                            <span style="color: #6b6b6b">-->
    <!--                                                作者：{{user.userName}} &nbsp;&nbsp;&nbsp;-->
    <!--                                                阅读数：{{item.bviewNum}}&nbsp;&nbsp;&nbsp;-->
    <!--                                                点赞数：{{item.bpraise}}&nbsp;&nbsp;&nbsp;-->
    <!--                                                时间：{{item.btime}}&nbsp;&nbsp;&nbsp;-->
    <!--                                            </span>-->
    <!--&lt;!&ndash;                        <a :href="'/page/front/detail.html?id=' + item.id" target="_blank">&ndash;&gt;-->
    <!--                            <el-button style="float: right" type="warning" size="mini" icon="el-icon-view" round>阅读文章</el-button>-->
    <!--&lt;!&ndash;                        </a>&ndash;&gt;-->
    <!--                    </div>-->
    <!--                </el-col>-->
    <!--            </el-row>-->
    <!--        </div>-->
    <!--        <div style="background-color: white">-->
    <!--&lt;!&ndash;            <el-pagination&ndash;&gt;-->
    <!--&lt;!&ndash;                    @size-change="handleSizeChange"&ndash;&gt;-->
    <!--&lt;!&ndash;                    @current-change="handleCurrentChange"&ndash;&gt;-->
    <!--&lt;!&ndash;                    :current-page="pageNum"&ndash;&gt;-->
    <!--&lt;!&ndash;                    :page-size="pageSize"&ndash;&gt;-->
    <!--&lt;!&ndash;                    layout="total, sizes, prev, pager, next, jumper"&ndash;&gt;-->
    <!--&lt;!&ndash;                    :total="total">&ndash;&gt;-->
    <!--&lt;!&ndash;            </el-pagination>&ndash;&gt;-->
    <!--            <el-pagination-->
    <!--                    background-->
    <!--                    layout="prev,pager,next"-->
    <!--                    :total="pageTotle"-->
    <!--                    :page-size="pageSize"-->
    <!--                    @current-change="pageData">-->
    <!--            </el-pagination>-->
    <!--        </div>-->
    <!--    </el-card>-->
    <div class="container">
                <div>
                    <el-card style="margin-top: 20px; margin-bottom: 50px"  v-for="item in blogData" :key="item">
                        <div style="text-align: center">
                            <div style="color: #888; font-size: 20px;" @click="goblog(item.bid)">
                                <strong>{{item.btitle}}</strong>
                            </div>
                            <div style="text-align: center; margin-top: 10px">
                                <div style="color: #888; font-size: 10px;">
                                                作者：{{user.userName}} &nbsp;&nbsp;&nbsp;
                                                阅读数：{{item.bviewNum}}&nbsp;&nbsp;&nbsp;
                                                点赞数：{{item.bpraise}}&nbsp;&nbsp;&nbsp;
                                                时间：{{item.btime}}&nbsp;&nbsp;&nbsp;
                                    <a  target="_blank">
                                        <el-button style="float: right" type="warning" size="mini" icon="el-icon-view" round @click="gotoedit(item.bid)">编辑</el-button>
                                        <el-button style="float: right" type="danger" size="mini" icon="el-icon-view" round @click="delblog(item.bid)">删除</el-button>

                                    </a>
                                </div>>
                            </div>

                        </div>
                        <!-- 分页 -->

                    </el-card>
                </div>
                <div id="page_content" >
                    <el-pagination
                            background
                            layout="prev, pager, next"
                            :total="pageTotal"
                            :page-size="pageSize"
                            @current-change="pageData">
                    </el-pagination>
                </div>
    </div>
</template>

<script>
    export default {
        name: "Myblog",
        data() {
            return {
                src: 'blog.html',
                // search:{
                //     searchParam:''
                // },
                blogData: [],
                pageNum: 1,
                pageSize: 2,
                pageTotal: 0,
                tags: [],
                tag: ''
            }
        },
        methods:{
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
            gotoedit(bid){
                this.$router.push({
                    path:'/editblog',
                    query:{
                        bid:bid
                    }
                });
            },
            delblog(bid){
                if(confirm('确定要删除吗')==true){
                    this.handleDelete(bid);
                }
            },
            handleDelete(bid){
                var param={"bid":bid};
                param=this.$qs.stringify(param);
                this.$axios
                    .post("blog/deleteBlog",param)
                    .then(rst=>{
                        if(rst.data){
                            this.success();
                            this.init();
                        }
                    }).catch(ex=>{
                    console.log(ex);
                });
            },
            success() {
                this.$message({
                    message: '删除成功！',
                    type: 'success'
                });
            },
            // gotoSelect(param,b){
            //     this.$router.push({
            //         path:'/selectblog',
            //         query:{
            //             param:param,
            //             paramtype:b
            //         }
            //     });
            // },
            init(){
                var param={"pageNum":this.pageNum,
                    "pageSize":this.pageSize,
                    "uid":this.user.uid};
                param=this.$qs.stringify(param);
                this.$axios
                    .post("user/findBlog",param)
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
            this.user=JSON.parse(sessionStorage.getItem("user"));
            console.log(this.user);
            this.init();
        }
    }
</script>

<style scoped>
    .box{
        height:650px;
        /*overflow:auto;*/
        padding: 0px;
        margin: 0px;
    }
    /*.cardi{*/
    /*    color: #87CEEB;*/
    /*}*/
    .container{
        height: 100%;
    }
    #courseList .el-row{
        padding-bottom: 20px;
        padding-top: 20px;
        height: 50px;
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
    /*.el-card:hover{*/
    /*    transform: scale(1);*/
    /*}*/

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
    .el-card{
        width: 90%;
        height: 300px;
    }
</style>