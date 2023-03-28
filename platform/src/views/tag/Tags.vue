<template>
	<el-dialog title="添加标签" v-model="dialogFormVisible">
	  <el-form :model="form" ref="form">
	    <el-form-item label="标签名称" >
		<el-input v-model="form.tname" autocomplete="off"></el-input>
	    </el-form-item>
	  </el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogFormVisible = false">取 消</el-button>
	      <el-button type="primary" @click="addTag()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<el-dialog title="编辑标签" v-model="dialogEditTag">
	  <el-form :model="form" >
	    <el-form-item label="标签名称" >
		<el-input v-model="form.tname" autocomplete="off"></el-input>
	    </el-form-item>
	  </el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogEditTag = false">取 消</el-button>
	      <el-button type="primary" @click="updateTag()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>	
	<div id="container">
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>标签管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<div id="serach">
			<el-form ref="search_form"
			       label-width="120px">
			       <el-form-item label="标签序号">
			         <el-input v-model="tags.tid">
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
		<div id="addUser">
			<el-button type="primary" @click="dialogFormVisible = true">添加标签</el-button>
		</div>
		<div id="show_table">
		        <el-table 
		          :data="tags">
		            <el-table-column
		            label="序号" prop="tid"></el-table-column>
		            <el-table-column
		            label="标签名称" prop="tname"></el-table-column>
					<el-table-column
					      fixed="right"
					      label="操作"
					      >
					      <template #default="scope">
					        <el-button @click="handleTag(scope.row)" type="primary" 
							icon="el-icon-edit" size="small">编辑</el-button>
					        <el-button type="danger" 
							icon="el-icon-delete" size="small"
							@click="deleteTag(scope.row.tid)">删除</el-button>
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
		name:'tags',
		data(){
			return{
				tags:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				dialogFormVisible : false,
				form:{
					tname:''
				},
				dialogEditTag:false
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
			handleTag(val)
			{
				this.dialogEditTag=true;
				this.form=val;
			},
		
			init(){
				this.tags.pageNum=this.pageNum;
				this.tags.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.tags);
				this.$axios
				.post("tag/findAllTags",param)
				.then(rst=>{
					console.log(rst);
					this.tags=rst.data.list;
					this.pageTotal=rst.data.total;
					
				}).catch(ex=>{
					console.log(ex);
				});
			},
			deleteTag(row){
				
				 this.$confirm('删除操作, 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
							var postData=this.$qs.stringify({
								tid:row
							});
							this.$axios
							.post("tag/deleteTag",postData)
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
			updateTag(){
				this.tags.pageNum=this.pageNum;
				this.tags.pageSize=this.pageSize;
				var param=this.$qs.stringify({
					tid:this.form.tid,
					tname:this.form.tname
				})
				this.$axios
				.post("tag/update",param)
				.then(rst=>{
					this.init();
				})
				.catch(er=>{
					console.log(er);
				})
				this.dialogEditTag = false
			},
			search(){
				this.pageNum=1;
				this.init();
			},
			pageData(curr){
				this.pageNum=curr;
				this.init();
			},
			addTag(){

				var postData = this.$qs.stringify({
					tname:this.form.tname
				})
				this.$axios
				.post("/tag/addTag",postData)
				.then(rst=>{
					this.tags.pageNum=this.pageNum;
					this.tags.pageSize=this.pageSize;
					var param=this.$qs.stringify(this.tags);
					this.$axios
					.post("tag/findAllTags",param)
					.then(rst=>{
						console.log(rst);
						this.tags=rst.data.list;
						this.pageTotal=rst.data.total;
					}).catch(ex=>{
						console.log(ex);
					});
				}).catch(ex=>{
					console.log(ex);
				});
				this.dialogFormVisible = false
			}
		},
		created() {
			this.tags.pageNum=this.pageNum;
			this.tags.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.tags);
			this.$axios
			.post("tag/findAllTags",param)
			.then(rst=>{
				console.log(rst);
				this.tags=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
	#container{
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
	}
</style>
