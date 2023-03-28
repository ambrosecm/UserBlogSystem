<template>
    <div id ="container">
        <el-card>
        <div id="show_table">
            <el-table :data="ccomments">
                <el-table-column v-if="show" width="100%" label="评论号" prop="ccid">
                </el-table-column>
                <el-table-column width="200%" label="博客" prop="blog.btitle">
                </el-table-column>
                <el-table-column width="200%" label="评论者" prop="user.userName">
                </el-table-column>
                <el-table-column width="200%" label="评论内容" prop="cctext">
                </el-table-column>
                <el-table-column width="200%" label="评论时间" prop="cctime">
                </el-table-column>
                <el-table-column width="200%" label="评论获赞" prop="ccpraise">
                </el-table-column>

                <el-table-column>
                    <template #default="scope">
                        <el-button type="primary"  @click="deleteq(scope.row)" class="el-icon-delete">
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div id="page_content">
                <el-pagination
                        background
                        layout="prev, pager, next"
                        :total="pageTotal"
                        :page-size="pageSize"
                        @current-change="pageData">
                </el-pagination>
            </div>
        </div>
            </el-card>
    </div>
</template>

<script>
    export default {
        name: "Myccomment",
        data(){
            return{
                show:false,
                list:{
                    ccid:'',
                    btitle:'',
                    userName:'',
                    cctext:'',
                    cctime:'',
                    ccpraise:''
                },
                user:{},
                ccomments:[],
                pageNum:1,
                pageTotal:0,
                pageSize:4,
                dccid:''
            }
        },
        methods:{
            init(){
                this.list.pageNum=this.pageNum;
                this.list.pageSize=this.pageSize;
                this.list.uid=this.user.uid;
                var param=this.$qs.stringify(this.list);
                console.log(param);
                this.$axios
                    .post("user/findCc",param)
                    .then(rst=>{
                        //console.log(rst);
                        this.ccomments=rst.data.list;
                        this.pageTotal=rst.data.total;
                        //console.log(this.ccomments);

                    }).catch(ex=>{
                    console.log(ex);
                });
            },
            pageData(curr){
                this.pageNum=curr;
                this.init();
            },
            success() {
                this.$message({
                    message: '删除成功！',
                    type: 'success'
                });
            },
            handleDelete(row){
                this.dccid=row.ccid;
                var param={'ccid':this.dccid};
                param = this.$qs.stringify(param);
                //console.log(param);
                this.$axios
                    .post("user/deleteCc",param)
                    .then(rst=>{
                        if(rst.data){
                            this.success();
                        }
                          }).catch(ex=>{
                    console.log(ex);
                });
                this.init();
            },
            deleteq(row){
                if(confirm('确定要删除吗')==true){
                    this.handleDelete(row);
                }

            }
        },
        created(){
            this.user=JSON.parse(sessionStorage.getItem("user"))
            console.log(this.user)
            this.init();
        }

    }
</script>

<style scoped>
.el-card{
    height: 100%;
}
</style>