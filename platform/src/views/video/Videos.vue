
<template>
	<el-dialog
	  title="添加"
	  v-model="dialogAddVideo">
			<el-form :model="form" >
			<el-form-item label="课程标题" >
			<el-input v-model="form.vtitle" autocomplete="off"></el-input>
			</el-form-item>	
			<el-form-item label="课程标签" >
			<el-select v-model="form.vtypeId" placeholder="请选择标签">
				<el-option v-for="item in typeList" 
				:key="item.typeName" 
				:label="item.typeName" 
				:value="item.typeId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="课程描述" >
			<el-input v-model="form.vdescription" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="讲师介绍" >
			<el-input v-model="form.vteacher" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="学习时长">
			<el-input v-model="form.vlong" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="课程大纲" >
			<el-input v-model="form.voutline" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="课程价格" >
			<el-input v-model="form.vprice" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogAddVideo = false">取 消</el-button>
	      <el-button type="primary" @click="addVideo()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<el-dialog
	  title="编辑"
	  v-model="dialogEditVideo">
			<el-form :model="form" >
			<el-form-item label="课程标题" >
			<el-input v-model="form.vtitle" autocomplete="off"></el-input>
			</el-form-item>	
			<el-form-item label="课程标签" >
			<el-select v-model="form.vtypeId" placeholder="请选择标签">
				<el-option v-for="item in typeList" 
				:key="item.typeName" 
				:label="item.typeName" 
				:value="item.typeId"
				>
			</el-option>
			</el-select>
			</el-form-item>
			<el-form-item label="课程描述" >
			<el-input v-model="form.vdescription" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="讲师介绍" >
			<el-input v-model="form.vteacher" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="学习时长">
			<el-input v-model="form.vlong" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="课程大纲" >
			<el-input v-model="form.voutline" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="课程价格" >
			<el-input v-model="form.vprice" autocomplete="off"></el-input>
			</el-form-item>
			</el-form>
	  <template #footer>
	    <span class="dialog-footer">
	      <el-button @click="dialogEditVideo = false">取 消</el-button>
	      <el-button type="primary" @click="EditVideo()">确 定</el-button>
	    </span>
	  </template>
	</el-dialog>
	<div id="container">
		<el-header>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>视频课程管理</el-breadcrumb-item>
			</el-breadcrumb>
		</el-header>
		<div id="search_content">
		 <el-form ref="search_form"
		        label-width="120px">
		        <el-form-item label="课程标题">
		          <el-input v-model="videos.vtitle">
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
		<div id="addVideo">
			<el-button type="primary" class="el-icon-search"
			@click="dialogAddVideo=true">
			  添加课程
			</el-button>
		</div>
		<div id="show_table">
			<el-table :data="videos">
				<el-table-column label="视频序号" prop="vid">
				</el-table-column>
				<el-table-column label="课程标题" prop="vtitle">
				</el-table-column>
				<el-table-column label="课程描述" prop="vdescription">
				</el-table-column>
				<el-table-column label="上传时间" prop="vtime">
				</el-table-column>
				<el-table-column label="讲师介绍" prop="vteacher">
				</el-table-column>
				<el-table-column label="学习时长" prop="voutline">
				</el-table-column>
				<el-table-column label="课程大纲" prop="voutline">
				</el-table-column>
				<el-table-column label="播放数量" prop="vplayNum">
				</el-table-column>
				<el-table-column label="课程价格" prop="vprice">
				</el-table-column>
				<el-table-column
				      fixed="right"
				      label="操作">
				      <template #default="scope">
				        <el-button @click="handleBlog(scope.row)" type="primary" 
						icon="el-icon-edit" >编辑</el-button>
				        <el-button type="danger" 
						icon="el-icon-delete" 
						@click="deleteVideo(scope.row.vid)">删除</el-button>
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
	import moment from "moment";
	export default{
		name:"Videos",
		data(){
			return{
				videos:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				dialogAddVideo:false,
				dialogEditVideo:false,
				form:{
					vid:'',
					vtitle:'',
					vtypeId:'',
					vdescription:'',
					vsrc:'',
					vtime:'',
					vteacher:'',
					vlong:'',
					vprice:'',
					voutline:''
				},
				typeList:[]
			}
		},
		methods:{
			handleBlog(val){
				this.dialogEditVideo=true;
				this.form=val;
			},
			EditVideo(){
				var postData = this.$qs.stringify({
					vid:this.form.vid,
					vtitle:this.form.vtitle,
					vtypeId:this.form.vtypeId,
					vdescription:this.form.vdescription,
					vteacher:this.form.vteacher,
					vlong:this.form.vlong,
					voutline:this.form.voutline,
					vprice:this.form.vprice,
					vsrc:this.form.vsrc
				});
				this.$axios
				.post("video/updateVideo",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogEditVideo=false;
			},
			addVideo(){
				let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
				console.log(str);
				var postData = this.$qs.stringify({
					vtitle:this.form.vtitle,
					vtypeId:this.form.vtypeId,
					vdescription:this.form.vdescription,
					vsrc:this.form.vsrc,
					vtime:str,
					vteacher:this.form.vteacher,
					vlong:this.form.vlong,
					voutline:this.form.voutline,
					vprice:this.form.vprice
				});
				this.$axios
				.post("video/addVideo",postData)
				.then(rst=>{
					console.log(rst);
					this.init();
				})
				.catch(er=>{
					console.log(er);
				});
				this.dialogAddVideo=false;
			},
			init(){
				this.videos.pageNum=this.pageNum;
				this.videos.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.videos);
				this.$axios
				.post("video/findAllVideos",param)
				.then(rst=>{
					console.log(rst);
					this.videos=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
				})
			},
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
			deleteVideo(row){
				 this.$confirm('删除操作, 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
							var postData=this.$qs.stringify({
								vid:row
							});
							this.$axios
							.post("video/deleteVideo",postData)
							.then(rst=>{
								this.init();
							})
							.catch(er=>{
								console.log(er);
							});
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
			},
			findtypeName(){
				this.$axios
				.post("video/findAllTypes")
				.then(rst=>{
					this.typeList=rst.data;
					console.log(rst.data);
				})
				.catch(er=>{
					console.log(er);
				})
			},
		},
		created() {
			this.findtypeName();
			this.videos.pageNum=this.pageNum;
			this.videos.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.videos);
			this.$axios
			.post("video/findAllVideos",param)
			.then(rst=>{
				console.log(rst);
				this.videos=rst.data.list;
				this.pageTotal=rst.data.total;
			}).catch(ex=>{
				console.log(ex);
			});
		}
	}
</script>

<style>
</style>
