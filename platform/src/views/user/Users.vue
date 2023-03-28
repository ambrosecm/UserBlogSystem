<template>

      <el-dialog
        title="编辑"
        v-model="dialogFormUpdate">
		<el-form :model="form" :rules="rules">
		<el-input v-model="form.uid" type="hidden" />
		<el-form-item label="姓名" >
		<el-input v-model="form.userName" autocomplete="off"></el-input>
		</el-form-item>
		<el-form-item label="密码"  >
		<el-input v-model="form.password" autocomplete="off"></el-input>
		</el-form-item>
		<el-form-item label="邮箱" >
		<el-input v-model="form.email" autocomplete="off"></el-input>
		</el-form-item>
		<el-form-item label="电话" >
		<el-input v-model="form.phone" autocomplete="off"></el-input>
		</el-form-item>
		<el-form-item label="生日" >
		<el-date-picker type="datetime" placeholder="选择日期" v-model="form.ubirth" style="width: 100%;"></el-date-picker>
		</el-form-item>
		<el-form-item label="性别" >
		<el-select v-model="form.sex" placeholder="请选择性别">
		        <el-option label="男" value="1"></el-option>
		        <el-option label="女" value="0"></el-option>
		   </el-select>
		</el-form-item>
		<el-form-item label="地址" >
		<el-input v-model="form.address" autocomplete="off"></el-input>
		</el-form-item>
		</el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogFormUpdate = false">取 消</el-button>
            <el-button type="primary" @click="updateUser()">确 定</el-button>
          </span>
        </template>
      </el-dialog>

<el-dialog title="添加用户" v-model="dialogFormVisible">
  <el-form :model="form" :rules="rules">
    <el-form-item label="姓名" >
	<el-input v-model="form.userName" autocomplete="off"></el-input>
    </el-form-item>
	<el-form-item label="密码" >
	<el-input v-model="form.password" autocomplete="off"></el-input>
	</el-form-item>
	<el-form-item label="邮箱" >
	<el-input v-model="form.email" autocomplete="off"></el-input>
	</el-form-item>
	<el-form-item label="电话" >
	<el-input v-model="form.phone" autocomplete="off"></el-input>
	</el-form-item>
	<el-form-item label="生日" >
	<el-date-picker type="date"  placeholder="选择日期" v-model="form.ubirth" style="width: 100%;"></el-date-picker>
	</el-form-item>
	<el-form-item label="性别" >
	<el-select v-model="form.sex" placeholder="请选择性别">
	        <el-option label="男" value="1"></el-option>
	        <el-option label="女" value="0"></el-option>
	   </el-select>
	</el-form-item>
	<el-form-item label="地址" >
	<el-input v-model="form.address" autocomplete="off"></el-input>
	</el-form-item>
  </el-form>
  <template #footer>
    <span class="dialog-footer">
	
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="addUser()">确 定</el-button>
    </span>
  </template>
</el-dialog>

		<el-container>
			<el-header>
				<el-breadcrumb separator-class="el-icon-arrow-right">
					<el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item>用户管理</el-breadcrumb-item>
				</el-breadcrumb>
			</el-header>
			<el-main><div id="search_content">
				<el-form ref="search_form"
						 label-width="120px">
					<el-form-item label="用户姓名">
						<el-input v-model="users.userName">
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
				<div id="addUser">
					<el-button type="primary" @click="dialogFormVisible = true">添加用户</el-button>
				</div>
				<div id="show_users">
					<el-table
							:data="users">
						<el-table-column label="姓名"
										 prop="userName"></el-table-column>
						<el-table-column label="邮箱"
										 prop="email"></el-table-column>
						<el-table-column label="性别" prop="sex">
							<template #default="scope">
								{{ scope.row.sex === 1 ? '男' : '女' }}
							</template>
						</el-table-column>

						<el-table-column label="生日"
										 prop="ubirth"></el-table-column>
						<el-table-column label="电话"
										 prop="phone"></el-table-column>
						<el-table-column label="住址"
										 prop="address"></el-table-column>
						<el-table-column
								fixed="right"
								label="操作"
						>
							<template #default="scope">
								<el-button @click="handleClick(scope.row)" type="primary"
										   icon="el-icon-edit" size="small">编辑</el-button>
								<el-button type="danger"
										   icon="el-icon-delete" size="small"
										   @click="deleteUser(scope.row.uid)">删除</el-button>
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
				</el-main>
		</el-container>
</template>

<script>
	import moment from "moment"
	export default{
		name:'Users',
		data(){
			return{
				users:[],
				pageNum:1,
				pageTotal:0,
				pageSize:4,
				dialogFormVisible:false,
				dialogFormUpdate:false,
				form:{
					uid:'',
					ubirth:'',
					userName:'',
					password:'',
					email:'',
					phone:'',
					sex:'',
					udate:'',
					address:'',
					
				},
				formLabelWidth: '120px',
				rules:{
				                    userName: [
				                        { required: true, message: '请输入姓名', trigger: 'blur' },
				                        { min: 2, max: 7, message: '长度在 2 到 7 个字符', trigger: 'blur' }
				                    ],
				                    
				        }
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
			 handleClick(row) {
			        console.log(row);
					this.dialogFormUpdate=true;
					this.form=row;
					},
			deleteUser(row){
				this.$confirm('删除操作, 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
							console.log(row);
							var postData=this.$qs.stringify({
								uid:row
							});
							this.$axios
							.post("user/deleteUser",postData)
							.then(rst=>{
								this.users.pageNum=this.pageNum;
								this.users.pageSize=this.pageSize;
								var param=this.$qs.stringify(this.users);
								this.$axios
								.post("user/findAllUsers",param)
								.then(rst=>{
									console.log(rst);
									this.users=rst.data.list;
									this.pageTotal=rst.data.total;
								}).catch(ex1=>{
								console.log(ex1);
								});	
							})
							.catch(ex=>{
								console.log(ex);
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
			updateUser(){
				this.users.pageNum=this.pageNum;
				this.users.pageSize=this.pageSize;
				let delayTime = new Date(this.form.ubirth).toJSON().split('T')[0];
								console.log(delayTime);
								var postData = this.$qs.stringify({
									uid:this.form.uid,
									ubirth:delayTime,
									userName:this.form.userName,
									password:this.form.password,
									email:this.form.email,
									phone:this.form.phone,
									sex:this.form.sex,
									address:this.form.address
								});
								console.log(postData);
								this.$axios
								.post("user/updateUser",postData)
								.then(rst=>{
									this.users.pageNum=this.pageNum;
									this.users.pageSize=this.pageSize;
									var param=this.$qs.stringify(this.users);
									this.$axios
									.post("user/findAllUsers",param)
									.then(rst=>{
										console.log(rst);
										this.users=rst.data.list;
										this.pageTotal=rst.data.total;
									}).catch(ex1=>{
									console.log(ex1);
									});	
								}).catch(ex=>{
									console.log(ex);
								})
								this.dialogFormUpdate = false;
			},
			addUser(){
							let str = moment(new Date()).format("YYYY-MM-DD hh:mm:ss") 
							let delayTime = new Date(this.form.ubirth).toJSON().split('T')[0];
							console.log(delayTime);
							var postData = this.$qs.stringify({
								uid:this.form.uid,
								ubirth:delayTime,
								userName:this.form.userName,
								password:this.form.password,
								email:this.form.email,
								phone:this.form.phone,
								sex:this.form.sex,
								udate:str,
								address:this.form.address
							});
							this.$axios
							.post("user/addUser",postData)
							.then(rst=>{
								this.users.pageNum=this.pageNum;
								this.users.pageSize=this.pageSize;
								var param=this.$qs.stringify(this.users);
								this.$axios
								.post("user/findAllUsers",param)
								.then(rst=>{
									console.log(rst);
									this.users=rst.data.list;
									this.pageTotal=rst.data.total;
								}).catch(ex1=>{
								console.log(ex1);
								});	
							}).catch(ex=>{
								console.log(ex);
							})
							this.dialogFormVisible = false
						},
						handleEdit(index,row){
							this.dialogFormUpdate=true;
							this.form=Object.assign({},row);
						},
			init(){
				this.users.pageNum=this.pageNum;
				this.users.pageSize=this.pageSize;
				var param=this.$qs.stringify(this.users);
				this.$axios
				.post("user/findAllUsers",param)
				.then(rst=>{
					console.log(rst);
					this.users=rst.data.list;
					this.pageTotal=rst.data.total;
				}).catch(ex=>{
					console.log(ex);
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
			this.users.pageNum=this.pageNum;
			this.users.pageSize=this.pageSize;
			var param=this.$qs.stringify(this.users);
			this.$axios
			.post("user/findAllUsers",param)
			.then(rst=>{
				console.log(rst);
				this.users=rst.data.list;
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
