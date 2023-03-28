<template>
	<div id="container">
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>视频留言</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<div id="search_content">
		 <el-form ref="search_form"
		        label-width="120px">
		        <el-form-item label="留言内容">
		          <el-input v-model="vcomments.vctext">
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
			<el-table :data="vcomments">
			<el-table-column label="序号" prop="vcid">
			</el-table-column>
				<el-table-column label="作者名称" prop="user.userName">
				</el-table-column>
				<el-table-column label="视频标题" prop="video.vtitle">
				</el-table-column>
				<el-table-column label="用户名" prop="user.userName">
				</el-table-column>
				<el-table-column label="评论时间" prop="vctime">
				</el-table-column>
				<el-table-column label="评论获赞" prop="vcpraise">
				</el-table-column>
				<el-table-column label="评论内容" prop="vctext">
				</el-table-column>
				<el-table-column
				      fixed="right"
				      label="操作"
				      width="100">
				      <template #default="scope">
				        
				        <el-button type="danger" 
						icon="el-icon-delete" 
						@click="deleteBlog(scope.row.vcid)">删除</el-button>
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
	export default{
		name:'Vcomments',
		data(){
			return{
				vcomments:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
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
				this.vcomments.pageSize=this.pageSize;
				this.vcomments.pageNum=this.pageNum;
				var param = this.$qs.stringify(this.vcomments);
				this.$axios
				.post("/vcomment/findAllVcomments",param)
				.then(rst=>{
					console.log(rst);
					this.vcomments=rst.data.list;
					this.pageTotal=rst.data.total;
				})
				.catch(ex=>{
					console.log(ex);
				})
			},
			
			deleteBlog(row){
				 this.$confirm('删除操作, 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
							var postData=this.$qs.stringify({
								vcid:row
							});
							this.$axios
							.post("vcomment/deleteVcomment",postData)
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
			this.vcomments.pageSize=this.pageSize;
			this.vcomments.pageNum=this.pageNum;
			var param = this.$qs.stringify(this.vcomments);
			this.$axios
			.post("/vcomment/findAllVcomments",param)
			.then(rst=>{
				console.log(rst);
				this.vcomments=rst.data.list;
				this.pageTotal=rst.data.total;
			})
			.catch(ex=>{
				console.log(ex);
			})
		}
	}
</script>

<style>
</style>
