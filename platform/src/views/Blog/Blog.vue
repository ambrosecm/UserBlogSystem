<template>
    <div id="wrapper" v-cloak>
        <div style="height: 1px; margin: 10px 0; background-color: white"></div>

        <el-card style="margin-top: 20px; margin-bottom: 50px">
            <div style="text-align: center">
                <div style="color: #888; font-size: 20px;">{{entity.btitle}}</div>
            </div>
            <div style="text-align: center; margin-top: 10px">
                <div style="color: #888; font-size: 10px;" >
                    作者：{{this.uname}}&nbsp;&nbsp;&nbsp;
                    时间：{{entity.btime}}&nbsp;&nbsp;&nbsp;
                    点赞量：{{entity.bpraise}}&nbsp;&nbsp;&nbsp;
                    阅读量：{{entity.bviewNum}}
                </div>
            </div>
            <!--                    分割线-->
            <div style="height: 1px; margin: 10px 0; background-color: #ccc"></div>

            <div style="padding: 20px; color: #888" id="view-notice-body">
                <dl v-html="entity.btext">
                </dl>

            </div>
            <div >
                <el-row >
                    <el-button type="success" @click="praise" round>赞赏{{entity.bpraise}}</el-button>
                </el-row>
            </div>
        </el-card>


        <el-card style="margin-top: 20px; margin-bottom: 50px">
            <div style="display: flex">
                <div style="color: #888; font-size: 20px; flex: 1">评论区</div>
            </div>
            <!--                    分割线-->
            <div style="height: 1px; margin: 10px 0; background-color: #ccc"></div>

            <div style="padding: 20px; color: #888">
                <div>
                    <el-input type="textarea" :rows="3" v-model="comment.cctext"></el-input>
                    <div style="text-align: right; padding: 10px"><el-button type="primary" @click="savecomment">发表评论</el-button></div>
                </div>
            </div>

            <!--                    分割线-->
            <div style="height: 1px; margin: 10px 0; background-color: #ccc"></div>

            <div style="display: flex; padding: 20px" v-for="item in comments">
                <div style="padding: 0 10px; flex: 5">
                    <div><b style="font-size: 14px">{{ item.user.userName}}</b></div>
                    <div style="padding: 10px 0; color: #888">
                        {{ item.cctext }}
                        <el-button type="text" style="font-size: medium" size="mini" @click="praisecomment(item.ccid)" >&nbsp;&nbsp;&nbsp;赞{{ item.ccpraise }}</el-button>
                    </div>
                    <div style="color: #888; font-size: 12px">
                        <span>{{ item.cctime  }}</span>
                        <el-button type="text" style="margin-left: 20px;font-size: medium" @click="showreply(item.ccid)">回复</el-button>
                        <div style="display: none" v-bind:id="item.ccid">
                            <el-input v-model="input" placeholder="请输入回复内容" style="width: 300px"></el-input>
                            <el-button type="primary" round size="small" @click="sendchildcomment(item.ccid)">发表评论</el-button>
                            <el-button round size="small" @click="cancel(item.ccid)">取消回复</el-button>
                        </div>
                    </div>
                    <div style="padding: 0 10px; flex: 5" v-for="cdc in item.childCcomments">
                        <div><b style="font-size: 14px">{{ cdc.user.userName}}</b></div>
                        <div style="padding: 10px 0; color: #888">
                            回复{{item.user.userName}}说:{{ cdc.cctext }}
                            <el-button type="text" size="mini" @click="praisecomment(cdc.ccid)" >&nbsp;&nbsp;&nbsp;赞{{ cdc.ccpraise }}</el-button>
                        </div>
                        <div style="color: #888; font-size: 12px">
                            <span>{{ cdc.cctime  }}</span>
                            <el-button type="text" style="margin-left: 20px" @click="showreply(cdc.ccid)">回复</el-button>
                            <div style="display: none" v-bind:id="cdc.ccid">
                                <el-input v-model="input" placeholder="请输入回复内容" style="width: 300px"></el-input>
                                <el-button type="primary" round size="small" @click="sendchildcomment(cdc.ccid)">发表评论</el-button>
                                <el-button round size="small" @click="cancel(cdc.ccid)">取消回复</el-button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
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
        </el-card>
    </div>

</template>

<script>
    export default {
        name: "Blog",
        data(){
            return {
                param:[{bid:0}],//返回博客参数
                uname:'',
                comment:{  //发送评论参数
                    uid:0,//登录用户   0为初始值
                    bid:0,//博客id
                    cctext:'',
                },
                entity:[],//返回博客数据
                tags:[],
                comments:[],
                input:'',
                isCollapse: false,
                pageTotal:0,
                pageSize:6,
                pageNum:1
            }
        },
        methods:{
            handleSizeChange(val){
                console.log(`每页${val}条`);
                this.pageSize=val;
                this.init();
            },
            sendchildcomment(ccid){
                var param={
                    ccwriterId:this.comment.uid,
                    bid:this.comment.bid,
                    ccparentId:ccid,
                    cctext:this.input
                };
                var params=this.$qs.stringify(param);
                this.$axios
                    .post("blog/sendCcomment",params)
                    .then(rst=>{
                        console.log(rst);
                        if(rst!=0){alert("回复成功");}else{alert("回复失败");}
                    }).catch(ex=>{
                    console.log(ex);
                });
                this.cancel(ccid);
                this.initcomment();
                this.input=''
            },
            showreply(ccid){
                if(this.comment.uid==0){
                    alert("请登录后操作");
                }else {
                    var obj=document.getElementById(ccid+"");
                    if(obj){obj.style.display=""}
                }

            },
            cancel(ccid){
                var obj=document.getElementById(ccid+"");
                if(obj){obj.style.display="none";}
                this.input=''
            },
            gotologin(){
                this.$router.push({
                    path:'/login',
                });
            },
            praisecomment(ccid){
                if(this.comment.uid==0){
                    alert("请登录后操作");
                }else{
                    var param={ccid:ccid};
                    var params=this.$qs.stringify(param);
                    this.$axios
                        .post("blog/praiseCcomment",params)
                        .then(rst=>{
                            console.log(rst);
                            if(rst!=0){alert("点赞成功");}else{alert("点赞失败");}
                        }).catch(ex=>{
                        console.log(ex);

                    });
                    this.initcomment();
                }

            },
            praise(){
                if(this.comment.uid==0){
                    alert("请登录后操作");
                }else{
                    var params=this.$qs.stringify(this.param);
                    this.$axios
                        .post("blog/praiseBlog",params)
                        .then(rst=>{
                            console.log(rst);
                            if(rst!=0){
                                alert("谢谢你的点赞");

                            }else {
                                alert("Error，点赞失败");
                            }
                        }).catch(ex=>{
                        console.log(ex);

                    });
                    this.init();
                }


            },
            savecomment(){
                if(this.comment.uid==0){
                    alert("请登录之后发表评论");
                }else{

                    var params=this.$qs.stringify(this.comment);
                    this.$axios
                        .post("blog/sendCcomment",params)
                        .then(rst=>{
                            console.log(rst);
                            if(rst!=0){
                                alert("评论成功");
                            }else{
                                alert("失败");
                            }
                        }).catch(ex=>{
                        console.log(ex);
                    });
                    this.initcomment();
                    this.comment.cctext='';
                }


            },
            init(){
                var params=this.$qs.stringify(this.param);
                this.$axios
                    .post("blog/findById",params)
                    .then(rst=>{
                        console.log(rst);
                        this.entity=rst.data;
                        this.uname=this.entity.user.userName;
                        this.bid=this.entity.bid;
                        this.comment.bid=this.entity.bid;
                    }).catch(ex=>{
                    console.log(ex);
                });
                this.$axios
                    .post("blog/findTagById",params)
                    .then(rst=>{
                        console.log(rst);
                        this.tags=rst.data;
                    }).catch(ex=>{
                    console.log(ex);
                });
                this.param.pageNum=this.pageNum;
                this.param.pageSize=this.pageSize;
                params=this.$qs.stringify(this.param);
                this.$axios
                    .post("blog/findCtById",params)
                    .then(rst=>{
                        console.log(rst);
                        this.comments=rst.data.list;
                        this.pageTotal=rst.data.total;
                    }).catch(ex=>{
                    console.log(ex);
                });
            },
            pageData(curr){
                this.pageNum=curr;
                this.initcomment();
            },
            initcomment(){
                this.param.pageNum=this.pageNum;
                this.param.pageSize=this.pageSize;
                var params=this.$qs.stringify(this.param);
                this.$axios
                    .post("blog/findCtById",params)
                    .then(rst=>{
                        console.log(rst);
                        this.comments=rst.data.list;
                        this.pageTotal=rst.data.total;
                    }).catch(ex=>{
                    console.log(ex);
                });
            }
        },
        created() {
            this.pageNum=1;
            this.param.bid=parseInt(this.$route.query.bid);
            this.comment.bid=parseInt(this.$route.query.bid);
            // this.comment.uid=this.$route.query.uid;   //传入用户id
            if(sessionStorage.getItem("user")){
                this.comment.uid=JSON.parse(sessionStorage.getItem("user")).uid;
            }
            console.log(this.comment.uid);
            this.init();
        },
        mounted() {

        }

    }

</script>

<style >

</style>