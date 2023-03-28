<template>
  <el-container>

    <el-main style="background-color: rgba(0,0,0,0)" >
      <el-row>
        <el-col :span="2">
        </el-col>
        <el-col :span="16">
          <el-card>
            <div>
              <div slot="header" class="clearfix">
                <span style="font-size: 25px">{{video.vtitle}}&nbsp;&nbsp;&nbsp;{{video.vdescription}}</span>
              </div>
              <div >
                <span>{{video.vplayNum}}播放&nbsp;&nbsp;&nbsp;{{video.vtime}}</span>
              </div>
            </div>

            <div id="videoPlay">
              <video-player class="video-player vjs-custom-skin"
                            ref="videoPlayer"
                            :playsinline="true"
                            :options="playerOptions">
              </video-player>
            </div>
          </el-card>
        </el-col>

        <el-col :span="6">
          <div>

            <el-card style="height: 150px">
              <span>{{video.voutline}}</span>
            </el-card>
          </div>
        </el-col>
      </el-row>

      <el-row>
        <ul>
          <li v-bind:style="{ color: praiseColor}" v-on:click="changePraColor"><i class="el-icon-thumb"></i></li>{{video.vpraise}}
          <li v-bind:style="{ color: collectColor}" v-on:click="changeColColor"><i class="el-icon-star-on"></i></li>{{collectNum}}
          <li v-on:click="openNote();findNote()"><i class="el-icon-edit" style="color: red;"></i></li>
          <el-switch
              style="display: block"
              v-model="switchValue"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="关闭弹幕"
              inactive-text="打开弹幕">
          </el-switch>
        </ul>

        <el-drawer
            title="我是标题"
            :visible.sync="drawer"
            :with-header="false"
            :model-value="drawer">
          <div style="text-align: center">
            <span style="font-size: 30px; font-family: 楷体;">快速笔记</span>
          </div>

          <div>
            <el-input
                type="textarea"
                :rows="30"
                placeholder="随时随地记录下你的心得吧！"
                v-model="note">
            </el-input>
          </div>
          <div style="margin-left: 276px; margin-top: 16px">
            <el-button @click="clearNote" type="primary"  class="el-icon-document-delete" style="margin-left: 10px">
              清空
            </el-button>
            <el-button @click="saveNote" type="primary" class="el-icon-document-add">
              保存
            </el-button>
          </div>

        </el-drawer>
      </el-row>

      <div>
        <div style="display: flex">
          <div style="color: #888; font-size: 20px; flex: 1">评论区</div>
        </div>
        <!--                    分割线-->
        <div style="height: 1px; margin: 10px 0; background-color: #ccc"></div>

        <div style="padding: 20px; color: #888">
          <div>
            <el-input type="textarea" :rows="3" v-model="comment.vctext"></el-input>
            <div style="text-align: right; padding: 10px"><el-button type="primary" @click="savecomment">发表评论</el-button></div>
          </div>
        </div>

        <!--                    分割线-->
        <div style="height: 1px; margin: 10px 0; background-color: #ccc"></div>

        <div style="display: flex; padding: 20px" v-for="item in comments">
          <div style="padding: 0 10px; flex: 5">
            <div><b style="font-size: 14px">{{ item.user.userName}}</b></div>
            <div style="padding: 10px 0; color: #888">
              {{ item.vctext }}
              <el-button type="text" style="font-size: medium" size="mini" @click="praisecomment(item.vcid)" >&nbsp;&nbsp;&nbsp;赞{{ item.vcpraise }}</el-button>
            </div>
            <div style="color: #888; font-size: 12px">
              <span>{{ item.vctime  }}</span>
              <el-button type="text" style="margin-left: 20px;font-size: medium" @click="showreply(item.vcid)">回复</el-button>
              <div style="display: none" v-bind:id="item.vcid">
                <el-input v-model="input" placeholder="请输入回复内容" style="width: 300px"></el-input>
                <el-button type="primary" round size="small" @click="sendchildcomment(item.vcid)">发表评论</el-button>
                <el-button round size="small" @click="cancel(item.vcid)">取消回复</el-button>
              </div>
            </div>
            <div style="padding: 0 10px; flex: 5" v-for="cdc in item.childVcomments">
              <div><b style="font-size: 14px">{{ cdc.user.userName}}</b></div>
              <div style="padding: 10px 0; color: #888">
                回复{{item.user.userName}}说:{{ cdc.vctext }}
                <el-button type="text" size="mini" @click="praisecomment(cdc.vcid)" >&nbsp;&nbsp;&nbsp;赞{{ cdc.vcpraise }}</el-button>
              </div>
              <div style="color: #888; font-size: 12px">
                <span>{{ cdc.vctime  }}</span>
                <el-button type="text" style="margin-left: 20px" @click="showreply(cdc.vcid)">回复</el-button>
                <div style="display: none" v-bind:id="cdc.vcid">
                  <el-input v-model="input" placeholder="请输入回复内容" style="width: 300px"></el-input>
                  <el-button type="primary" round size="small" @click="sendchildcomment(cdc.vcid)">发表评论</el-button>
                  <el-button round size="small" @click="cancel(cdc.vcid)">取消回复</el-button>
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
      </div>

    </el-main>
  </el-container>




</template>

<script>

export default {
  data() {
    return {
      param:[{vid:0}],//返回博客参数
      hasNote:false,
      note:'',
      comment:{  //发送评论参数
        uid:0,//登录用户   0为初始值
        vid:0,//博客id
        vctext:'',
      },
      comments:[],
      input:'',
      pageTotal:0,
      pageSize:6,
      pageNum:1,
      colDate:{

      },
      user:{

      },
      isPraised:-1,
      praiseColor:'#99a9bf',
      isCollected: -1,
      collectColor:'#99a9bf',
      switchValue:'true',
      video:{

      },
      collectNum:'',
      drawer: false,
      src:'',

      playerOptions: {
        //播放速度
        playbackRates: [0.5, 1.0, 1.5, 2.0],
        //如果true,浏览器准备好时开始回放。
        autoplay: false,
        // 默认情况下将会消除任何音频。
        muted: false,
        // 导致视频一结束就重新开始。
        loop: false,
        // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        preload: 'auto',
        language: 'zh-CN',
        // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        aspectRatio: '16:9',
        // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        fluid: true,
        sources: [{
          //类型
          type: "video/mp4",
          //url地址

          src: 'https://www.runoob.com/try/demo_source/movie.mp4',
        },

        ],


        //你的封面地址
        poster: require('../../assets/02.jpg'),
        //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        notSupportedMessage: '此视频暂无法播放，请稍后再试',
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          //全屏按钮
          fullscreenToggle: true
        }
      },


    }

  },
  methods:{
    handleSizeChange(val){
      console.log(`每页${val}条`);
      this.pageSize=val;
      this.initcomment();
    },
    sendchildcomment(vcid){
      var param={
        vcwriterId:this.comment.uid,
        vid:this.comment.vid,
        vcparentId:vcid,
        vctext:this.input
      };
      var params=this.$qs.stringify(param);
      this.$axios
              .post("video/sendVcomment",params)
              .then(rst=>{
                console.log(rst);
                if(rst!=0){alert("回复成功");}else{alert("回复失败");}
              }).catch(ex=>{
        console.log(ex);
      });
      this.cancel(vcid);
      this.initcomment();
      this.input=''
    },
    showreply(vcid){
      if(this.comment.uid==0){
        alert("请登录后操作");
      }else {
        var obj=document.getElementById(vcid+"");
        if(obj){obj.style.display=""}
      }

    },
    cancel(vcid){
      var obj=document.getElementById(vcid+"");
      if(obj){obj.style.display="none";}
      this.input=''
    },
    praisecomment(vcid){
      if(this.comment.uid==0){
        alert("请登录后操作");
      }else{
        var param={vcid:vcid};
        var params=this.$qs.stringify(param);
        this.$axios
                .post("video/praiseVcomment",params)
                .then(rst=>{
                  console.log(rst);
                  if(rst!=0){alert("点赞成功");}else{alert("点赞失败");}
                }).catch(ex=>{
          console.log(ex);

        });
        this.initcomment();
      }

    },
    savecomment(){
      if(this.comment.uid==0){
        alert("请登录之后发表评论");
      }else{

        var params=this.$qs.stringify(this.comment);
        this.$axios
                .post("video/sendVcomment",params)
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
        this.comment.vctext='';
      }


    },
    findNote(){
      console.log('jinlailaelakejfkaj')
      this.$axios.post("/video/findNote?vid="+this.video.vid + "&uid=" + this.user.uid)
          .then(rst=>{
            if(rst.data!=''){
              this.hasNote = true;
              this.note = rst.data.note;
            }else{
              this.hasNote=false;
            }
          })
          .catch(ex=>{
            console.log(ex);
          });
    },
    changeColColor(){
      this.isCollected = this.isCollected*(-1);
      if(this.isCollected>0){
        this.collectColor = '#089bff'
        this.$axios.post("/video/saveCol?vid="+this.video.vid + "&uid=" + this.user.uid)
            .then(rst=>{
              this.init();
            })
            .catch(ex=>{
              console.log(ex);
            });
      }else{
        this.collectColor='#99a9bf'
        this.$axios.post("/video/delCol?vid="+this.video.vid + "&uid=" + this.user.uid)
            .then(rst=>{
              this.init();
            })
            .catch(ex=>{
              console.log(ex);
            });
      }
    },
    changePraColor(){
        this.isPraised = this.isPraised*(-1);
        if(this.isPraised>0){
          this.praiseColor = '#089bff'
          this.$axios.post("/video/addPraise?vid="+this.video.vid)
              .then(rst=>{
                this.init();
              })
              .catch(ex=>{
                console.log(ex);
              });
        }else{
          this.praiseColor='#99a9bf'
          this.$axios.post("/video/subPraise?vid="+this.video.vid)
              .then(rst=>{
                this.init();
              })
              .catch(ex=>{
                console.log(ex);
              });
        }


    },
    openNote(){
      console.log("执行opennote");
      this.drawer = true
    },
    saveNote(){
      this.drawer=false;
      if(this.hasNote==true){
        this.$axios.post("/video/updateNote?vid="+this.video.vid + "&uid=" + this.user.uid + "&note=" + this.note)
            .catch(ex=>{
              console.log(ex);
            });
      }else{
        this.$axios.post("/video/addNote?vid="+this.video.vid + "&uid=" + this.user.uid + "&note=" + this.note)
            .then(rst=>{
              this.hasNote = true;
            })
            .catch(ex=>{
              console.log(ex);
            });

      }
    },
    clearNote(){
      this.note=''
      this.$axios.post("/video/updateNote?vid="+this.video.vid + "&uid=" + this.user.uid + "&note=" + this.note)
          .catch(ex=>{
            console.log(ex);
          });
    },
    init(){
      this.user = JSON.parse(sessionStorage.getItem('user'));
      console.log(this.user);
      let vid = this.$route.query.vid;
      this.$axios.post("/video/findVideoById?vid="+vid)
          .then(rst=>{
            this.video = rst.data;
            let str = this.video.vsrc;
            this.playerOptions.sources[0].src = this.video.vsrc;
          })
          .catch(ex=>{
            console.log(ex);
          });

      this.$axios.post("/video/findColVideoNum?vid="+vid)
          .then(rst=>{
            this.collectNum = rst.data;
          })
          .catch(ex=>{
            console.log(ex);
          });

      this.$axios.post("/video/findCol?uid="+this.user.uid +"&vid="+ vid)
          .then(rst=>{
            this.colDate = rst.data;
            console.log(this.colDate=='')
            console.log(typeof this.colDate)
            if(this.colDate!=''){
              this.isCollected = 1
              this.collectColor = '#089bff'

            }
          })
          .catch(ex=>{
            console.log(ex);
          });
      this.param.pageNum=this.pageNum;
      this.param.pageSize=this.pageSize;
      var params=this.$qs.stringify(this.param);
      this.$axios
              .post("video/findCtById",params)
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
              .post("video/findCtById",params)
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
    this.param.vid=parseInt(this.$route.query.vid);
    this.comment.vid=parseInt(this.$route.query.vid);
    // this.comment.uid=this.$route.query.uid;   //传入用户id
    if(sessionStorage.getItem("user")){
      this.comment.uid=JSON.parse(sessionStorage.getItem("user")).uid;
    }
    this.init();

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
  background-color: rgba(0,0,0,0);
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

.el-footer {
  width: 100%;
  height: 30px;
}
ul {
  font-size: 50px;
  margin: 0 320px;
}

/*整体布局结束/////////////////////////////////////////////////////////////////////////*/







.demo{
  display: inline-block;
  width: 100%;
  height: 100%;
  text-align: center;
  line-height: 100px;
  border: 1px solid transparent;
  border-radius: 4px;
  overflow: hidden;
  background: #fff;
  position: relative;
  box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
  margin-right: 4px;
}

ul{
  font-size: 50px;
}
ul li{
  display: inline-block;
  padding-right: 70px;
}

ul li:hover{
  color: #089bff !important;
}
  .el-row{
    background-color: rgba(0,0,0,0);
  }


</style>
