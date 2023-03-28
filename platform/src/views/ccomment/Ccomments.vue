<template>
	<div id ="container">
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>留言管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<div id="serach">
			<el-form ref="search_form"
			       label-width="120px">
			       <el-form-item label="留言内容">
			         <el-input v-model="ccomments.cctext">
			           <i class="el-icon-aim" style="font-size: 30px;"></i>
						 <template #append>
							 <el-button type="primary" class="el-icon-search"
										v-on:click="search()">
								 &nbsp;&nbsp;搜索&nbsp;&nbsp;
							 </el-button>
						 </template>
			         </el-input>
			       </el-form-item>
			     </el-form>
		</div>
		<div id="show_table">
			<el-table :data="ccomments">
				<el-table-column label="序号" prop="ccid">
				</el-table-column>
				<el-table-column label="博客标题" prop="blog.btitle">
				</el-table-column>
				<el-table-column label="评论时间" prop="cctime">
				</el-table-column>
				<el-table-column label="评论获赞" prop="ccpraise">
				</el-table-column>
				<el-table-column label="评论内容" prop="cctext">
				</el-table-column>
				<el-table-column label="用户名" prop="user.userName">
				</el-table-column>
				<el-table-column
				      fixed="right"
				      label="操作"
				      width="100">
				      <template #default="scope">
				      
				        <el-button type="danger" 
						icon="el-icon-delete" 
						@click="deleteCcoment(scope.row.ccid)">删除</el-button>
				      </template>
				    </el-table-column>
				
			</el-table>
			<div id="page_content">
					<el-pagination
										 background
										layout="total,sizes,prev, pager, next,jumper"
										:total="pageTotal"
										:page-sizes="[2,4,6,8]"
										:page-size="pageSize"
										@size-change="handleSizeChange"
										 @current-change="handleCurrentChange"
										 :current-change="pageNum"
										 >
										</el-pagination>
				</div>
		</div>
	</div>
</template>

<script>
	export default {
		name:'ccomments',
		data(){
			return{
				ccomments:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4
				}
		},
		methods:{
			handleCurrentChange(val)
						{
							console.log(`当前页:${val}`);
							this.pageNum=val;
							this.init();
						},
						handleSizeChange(val){
							console.log(`每页${val}条`);
							this.pageSize=val;
							this.init();
						},
			init(){
				this.ccomments.pageNum=this.pageNum;
				this.ccomments.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.ccomments);
				this.$axios
				.post("Ccomment/findAllCcomments",param)
				.then(rst=>{
					console.log(rst);
					this.ccomments=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			},
			deleteCcoment(row){
				 this.$confirm('删除操作, 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
							var param=this.$qs.stringify({
							ccid:row
							});
							this.$axios
							.post("Ccomment/deleteCcomment",param)
							.then(rst=>{
								this.init();
							})
							.catch(er=>{
								console.log(er);
							})
				          this.$message({
				            type: 'success',
				            message: '删除成功!'
				          });
				        }).catch(() => {
				          this.$message({
				            type: 'info',
				            message: '已取消删除'
				          });
				        });
			},
			search(){
				this.pageNum=1;
				this.init();
			},
			pageData(curr){
				this.pageNum=curr;
				this.init();
			}
		},
		created(){
			this.ccomments.pageNum=this.pageNum;
			this.ccomments.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.ccomments);
			this.$axios
			.post("Ccomment/findAllCcomments",param)
			.then(rst=>{
				console.log(rst);
				this.ccomments=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
</style>
