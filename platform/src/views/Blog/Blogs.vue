<template>
	<div id="container">
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>博客管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<div id="serach">
			<el-form ref="search_form"
			       label-width="120px">
			       <el-form-item label="标题">
			         <el-input v-model="blogs.btitle">
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
		        <el-table 
		          :data="blogs">
		            <el-table-column
		            label="序号" prop="bid"></el-table-column>
					<el-table-column
					label="作者" prop="user.userName"></el-table-column>
		            <el-table-column
		            label="标题" prop="btitle"></el-table-column>
					<el-table-column
					label="正文" prop="btext"></el-table-column>
					<el-table-column
					label="标签" prop="tag.tname"></el-table-column>
					<el-table-column
					label="时间" prop="btime"></el-table-column>
					<el-table-column
					label="浏览数量" prop="bviewNum"></el-table-column>
					<el-table-column
					label="点赞量" prop="bpraise"></el-table-column>
					
					<el-table-column

					      fixed="right"
					      label="操作"
					      width="100">
					      <template #default="scope">
					        <el-button @click="handleBlog(scope.row)" type="primary" 
							icon="el-icon-edit" size="small">编辑</el-button>
					        <el-button type="danger" 
							icon="el-icon-delete" size="small"
							@click="deleteBlog(scope.row.bid)">删除</el-button>
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
	</div>
</template>

<script>
	export default{
		name:'blogs2',
		data(){
			return{
				blogs:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4
			}
		},
		methods:{
			init(){
				this.blogs.pageNum=this.pageNum;
				this.blogs.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.blogs);
				this.$axios
				.post("blog/findAllBlogs",param)
				.then(rst=>{
					console.log(rst);
					this.blogs=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				});
			},
			deleteBlog(row){
				this.$confirm('删除操作, 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
							var postData=this.$qs.stringify({
								bid:row
							})
							this.$axios
							.post("blog/deleteBlog",postData)
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
		created() {
			this.blogs.pageNum=this.pageNum;
			this.blogs.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.blogs);
			this.$axios
			.post("blog/findAllBlogs",param)
			.then(rst=>{
				console.log(rst);
				this.blogs=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
</style>
