<template>
  <el-container>

    <el-main style="background-color: rgba(0,0,0,0)" >
      <el-row type="flex" justify="center" :gutter="20">
          <el-col :span="2">

          </el-col>
        <el-col :span="16">
<!--            轮播图开始///////////////////////////////////////////////////////////////////////////////////////////-->
          <div>
            <el-row :gutter="10">
              <el-col :span="24">
                <el-carousel height="305px">
                  <el-carousel-item >
                    <a href="/videoPlay">
                      <img style="width: 100%; height: 100%; border-radius: 4px;" src='https://i.loli.net/2021/08/27/i45TYQZxGIBzaD8.jpg'>
                    </a>
                  </el-carousel-item>

                  <el-carousel-item >
                    <a href="/videoPlay">
                      <img style="width: 100%; height: 100%; border-radius: 4px;" src='https://i.loli.net/2021/08/27/i45TYQZxGIBzaD8.jpg'>
                    </a>
                  </el-carousel-item>
                </el-carousel>
              </el-col>
            </el-row>
          </div>
<!--            轮播图结束///////////////////////////////////////////////////////////////////////////////////////////-->

<!--          视频列表开始////////////////////////////////////////////////////////////////////////////////////////////-->
          <div style="margin: 20px 0">
            <el-card>
              <div style="border-bottom: 1px solid gray; padding: 10px 0; font-size: 20px">技术视频</div>
              <div style="margin: 10px 0">
                <el-row :gutter="10">
                  <el-col :span="8" v-for="(item,index) in videos" :key="item" style="margin: 5px 0;">
                    <el-card :body-style="{ padding: '5px' }">
<!--                      <a href="/videoPlay" target="_blank">-->
                        <img src="https://i.loli.net/2021/08/27/i45TYQZxGIBzaD8.jpg" v-on:click="toVideoPlay(item.vid),updateVPlayNum(item.vid)" style="width: 100%; height: 170px; border-radius: 5px" class="image">
<!--                      </a>-->
                      <div style="padding: 14px; height: 180px">
                        <div style="font-size: 16px; height: 50px"><strong>{{item.vtitle}}</strong></div>
                        <div style="color: #6b6b6b; height: 50px; margin-bottom: 5px">{{item.vdescription}}</div>
                        <div>
                          <div >
                            <span class="el-icon-view">&nbsp;{{item.vplayNum}}</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <span class="el-icon-thumb">&nbsp;{{item.vpraise}}</span>
                          </div>
                          <span style="color: #6b6b6b">{{item.vtime}}</span>
<!--                          <a href="/videoPlay" target="_blank">-->
                            <el-button style="float: right" size="mini" icon="el-icon-video-play" v-on:click="toVideoPlay(item.vid),updateVPlayNum(item.vid)">观看视频 </el-button>
<!--                          </a>-->
                        </div>
                      </div>
                    </el-card>
                  </el-col>
                </el-row>
              </div>
              <!-- 分页 -->
              <div style="background-color: white">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[6, 9, 12, 15]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
              </div>
            </el-card>
          </div>
<!--          视频列表结束////////////////////////////////////////////////////////////////////////////////////////////-->
        </el-col>
        <el-col :span="6">
          <div>
            <el-card style="background-color: #4e4e4e; color: #e3e3e3">
              <div style="border-bottom: 1px solid orangered; padding-bottom: 10px; font-size: 20px">
                <span style="margin-right: 20px">小组简介</span>
                <el-button v-if="isAdmin" size="mini" @click="enterEnd">进入后台</el-button>
              </div>
              <div>
                <div style="color: #f0f8ff; margin: 10px 0; font-size: 14px">
                  <i class="el-icon-s-custom"></i>
                  <span>职业：中国石油大学（华东）准大三学生</span>
                </div>
                <div style="color: #f0f8ff; margin: 10px 0; font-size: 14px">
                  <i class="el-icon-map-location"></i>
                  <span>地址：中国石油大学华东</span>
                </div>
                <div style="color: #f0f8ff; margin: 10px 0; font-size: 14px">
                  <i class="el-icon-medal"></i>
                  <span>微信公众号：暂无</span>
                </div>
                <div style="color: #f0f8ff; margin: 10px 0; font-size: 14px">
                  <i class="el-icon-trophy"></i>
                  <span>B站：暂无</span>
                </div>
                <div style="color: #f0f8ff; margin: 10px 0; font-size: 14px">
                  <i class="el-icon-message"></i>
                  <span>e-mail：yyds@163.com</span>
                </div>
                <div style="margin-top: 20px;">
                                    <span>
                                        <a href="javascript:void(0)" @mouseleave="hideImg('wxImg')"  @mouseover="showImg('wxImg')">
                                            <img style="width:20%; border-radius: 50%" src="/images/wx.jpg" alt="">
                                        </a>
                                        <div id="wxImg" style="display:none;height:50px;back-ground:#f00;position:absolute; margin-left: -35px">
                                            <img style="width:50%; border-radius: 5px" src="/images/Java开发宝典.jpg" alt="">
                                        </div>
                                    </span>
                  <span>
                                        <a href="javascript:void(0)" @mouseleave="hideImg('qqImg')"  @mouseover="showImg('qqImg')">
                                            <img style="width:20%; border-radius: 50%" src="/images/wx.jpg" alt="">
                                        </a>
                                        <div id="qqImg" style="display:none;height:50px;back-ground:#f00;position:absolute; margin-left: 35px">
                                            <img style="width:50%; border-radius: 5px" src="/images/武哥聊编程.jpg" alt="">
                                        </div>
                                    </span>

                </div>

              </div>

            </el-card>
          </div>
          <div style="margin: 20px 0">
            <el-card>
              <div style="border-bottom: 1px solid gray; padding-bottom: 10px; font-size: 20px">标签分类</div>
              <div style="margin-top: 10px">
                <a href="#" v-for="item in tags">
                  <el-tag style="margin: 5px">{{item}}</el-tag>
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
  name: "VideoMain",
  data() {
    return {
      currentDate: new Date(),
      video:{
        vtitle:'',
        typeNmae:''
      },
      videos:[

      ],
      tags:[

      ],
    };
  },
  created() {
    let param = this.$qs.stringify(this.video);
    this.$axios
        .post("video/findVideo",param)
        .then(rst=>{
          // console.log(rst);
          this.videos = rst.data;
          let arr = new Array();
          for(let i=0; i<this.videos.length; i++){
            arr[i] = this.videos[i].type.typeName;
          }
          this.tags = Array.from(new Set(arr))
        }).catch(ex=>{
      console.log(ex);
    });
  },
  methods:{
    toVideoPlay(vid){
      // console.log(item.vsrc);
      // let param = this.$qs.stringify(item);
      this.$router.push({
        path:'/videoPlay',
        query:{
          vid:vid
        }
      });
    },
    updateVPlayNum(vid){
      let a =
      this.$axios
          .post("video/updateVplayNum?vid="+vid)
          .then(rst=>{
          }).catch(ex=>{
        console.log(ex);
      });
    }
  }
}
</script>

<style scoped>
/*整体布局开始/////////////////////////////////////////////////////////////////////////*/
.el-container{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.el-header{
  width: 100%;
  height: 80px;
}

.el-main{
  width: 100%;
  flex: 1;
  display: flex;
  flex-direction: column;
}

.el-footer{
  width: 100%;
  height: 30px;
}
/*整体布局结束/////////////////////////////////////////////////////////////////////////*/


/*轮播图开始/////////////////////////////////////////////////////////////////////////*/
.el-carousel{
  width: 100%;
  height: 100%;
  padding: 0;
  margin: 0;
}
#recommend{
  width: 100%;
  height: 300px;
  padding: 0;
  margin: 0;
}
.el-carousel__item h3 {
  color: #475669;
  text-align: center;
  font-size: 120px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
/*轮播图结束/////////////////////////////////////////////////////////////////////////*/



/*卡片样式开始/////////////////////////////////////////////////////////////////////////*/
#courseList .el-row{
  padding-bottom: 20px;
  padding-top: 20px;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

/*.el-card{*/
/*  transform: scale(1);*/
/*}*/
/*.el-card:hover{*/
/*  transform: scale(1.15);*/
/*}*/

.image {
  height: 250px;
  width: 100%;
  display: block;
  /*transform: scale(1);*/
  /*transition: transform 1s ease 0s;*/
}
.image:hover {
  /*transform: scale(1.25);*/
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
/*卡片样式结束/////////////////////////////////////////////////////////////////////////*/


</style>