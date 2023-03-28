<template>
    <el-container id="container">

        <el-main style="background-color: rgba(0,0,0,0)" >
            <el-row type="flex" justify="center" :gutter="20">
                <el-col :span="16">
                    <div>
                        <el-card v-for="item in blogData" :key="item">
                            <div style="margin: 30px 0" >
                                <div style="margin-bottom: 10px; font-size: 16px; cursor: pointer;" @click="goblog(item.bid)">
                                    <strong>{{item.btitle}}</strong>
                                    <div style="float: right">
                                        标签类型：<div style="float: right" v-for="t in item.blogtags">{{t.tname}}&nbsp;</div>
                                    </div>

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
                                            <a @click="goblog(item.bid)" target="_blank">
                                                <el-button style="float: right" type="warning" size="mini" icon="el-icon-view" round>阅读文章</el-button>
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
                <el-col :span="6">
                    <el-input v-model="search.searchParam"  placeholder="请输入内容" style="width: 430px">
                        <template #append>
                            <el-button  icon="el-icon-search" @click="searchbyParam(this.search.searchParam)">搜索</el-button>
                        </template>
                    </el-input>

                    <div style="margin: 20px 0">
                        <el-card class="biaoqian">
                            <div style="border-bottom: 1px solid gray; padding-bottom: 10px; font-size: 20px">标签分类</div>
                            <div style="margin-top: 10px">
                                <a @click="searchbyTag(item.tid)" v-for="item in tags" style="cursor: pointer;">
                                    <el-tag style="margin: 5px">{{item.tname}}</el-tag>
                                </a>
                            </div>
                        </el-card>
                    </div>
                </el-col>
            </el-row>

        </el-main>


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
                param:'',
                paramtype:0,
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
            searchbyParam(param){
                this.paramtype=1;
                this.search.searchParam=param;
                this.search.pageNum=this.pageNum;
                this.search.pageSize=this.pageSize;
                var params=this.$qs.stringify(this.search);
                this.$axios
                    .post("blog/findBlogByParam",params)
                    .then(rst=>{
                        console.log(rst);
                        this.blogData=rst.data.list;
                        this.pageTotal=rst.data.total;
                    }).catch(ex=>{
                    console.log(ex);
                });
                this.search.searchParam=''
            },
            searchbyTag(param){
                this.paramtype=2;
                this.search.tid=param;
                this.search.pageNum=this.pageNum;
                this.search.pageSize=this.pageSize;
                var params=this.$qs.stringify(this.search);
                this.$axios
                    .post("blog/findBlogByTag",params)
                    .then(rst=>{
                        console.log(rst);
                        this.blogData=rst.data.list;
                        this.pageTotal=rst.data.total;
                    }).catch(ex=>{
                    console.log(ex);
                });
                this.search.searchParam=''
            },

            init(){
                if(this.paramtype==1){
                    this.search.searchParam=this.param;
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
                    this.search.searchParam=''
                }else if(this.paramtype==2){
                    this.search.tid=this.param;
                    this.search.pageNum=this.pageNum;
                    this.search.pageSize=this.pageSize;
                    var param=this.$qs.stringify(this.search);
                    this.$axios
                        .post("blog/findBlogByTag",param)
                        .then(rst=>{
                            console.log(rst);
                            this.blogData=rst.data.list;
                            this.pageTotal=rst.data.total;
                        }).catch(ex=>{
                        console.log(ex);
                    });
                    this.search.searchParam=''
                }

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
            this.param=this.$route.query.param;
            this.paramtype=this.$route.query.paramtype;
            this.pageNum=1;
            this.init();
        }

    }
</script>

<style scoped>

    #container {

    }
    .biaoqian .el-card{
        width: 300px;
    }
</style>