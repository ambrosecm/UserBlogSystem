<template>
	<el-container id="container">
		<el-header id="header">
			<el-row class="row-bg" justify="space-between">
				<el-col :span="6">
					<div class="grid-content bg-purple">
						<h1 id="houtai">后台管理</h1>
					</div>
				</el-col>
				<el-col :span="1" class="hidden-md-and-down">
					<div class="grid-content bg-purple">
						<a href="/personal">
							<el-button type="info" circle>
								退出
							</el-button></a>
					</div>
				</el-col>
			</el-row>
		</el-header>
		<el-container>
			<el-aside width="200px" id="aside">
				<el-menu
					:router="true"
					:unique-opened="true">
					<el-menu-item class="el-icon-s-management" index="/usersta">用户信息统计</el-menu-item>
				<el-menu-item class="el-icon-s-custom" index="/users">用户管理</el-menu-item>
				<el-menu-item class="el-icon-s-flag" index="/tags">标签管理</el-menu-item>
				<el-menu-item class="el-icon-s-platform" index="/blogs">博客管理</el-menu-item>
				<el-sub-menu index="2">
					<template #title>
						<i class="el-icon-s-comment"></i>
						留言管理
					</template>
					<el-menu-item index="/ccomments" class="el-icon-s-comment">博客留言</el-menu-item>
					<el-menu-item index="/vcomments" class="el-icon-s-comment">视频留言</el-menu-item>
				</el-sub-menu>
				<el-menu-item index="/videos" class="el-icon-s-shop">课程资源管理</el-menu-item>
			</el-menu>
			</el-aside>
			<el-main id="main">
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	export default{
		name:'Background'
	}
</script>

<style scoped="scoped">
	#houtai{
		margin: 12px;
		color: #e3e3e3;
		font-family: 黑体;
		font-size: 30px;
	}
	#container{
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
	}
	#header{
		background-color: #071b2e;
	}
	#main{
		width: 100%;
		flex: 1;
		display: flex;
	}
	#aside{
		background-color: #071b2e;
	}
</style>
