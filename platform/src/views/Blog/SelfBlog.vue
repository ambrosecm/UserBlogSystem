<template >

    <div id="wrapper" v-cloak style="height: 700px">
<!--        <div style="padding-bottom: 10px">-->
<!--            <el-breadcrumb separator-class="el-icon-arrow-right">-->
<!--                <el-breadcrumb-item @click.native="parent.changeIndex('index')">首页</el-breadcrumb-item>-->
<!--                <el-breadcrumb-item>博客编辑</el-breadcrumb-item>-->
<!--            </el-breadcrumb>-->
<!--        </div>-->

        <div style="height: 1px; margin: 10px 0; background-color: white"></div>

        <el-form :model="entity">
            <el-form-item label="博客标题" label-width="100px">
                <el-input v-model="entity.btitle" autocomplete="off" style="width: 40%"></el-input>
            </el-form-item>

            <el-form-item label="选择标签" label-width="100px">
                    <el-checkbox v-for="tag in tags"
                                 :label="tag.tid"
                                 v-model="checkedTag"
                                 :key="tag.tid" border>
                        {{tag.tname}}
                    </el-checkbox>
            </el-form-item>
            <el-form-item label="博客内容" label-width="100px">
                    <el-row>
                        <el-col span="8">
                            <Editor id="tinymce" v-model="tinymceHtml" :init="editorInit" ></Editor>
                        </el-col>
                        <el-col span="8" >&nbsp;&nbsp;&nbsp</el-col>

                        <el-col span="8" >
                                <el-card style="width:500px;flex-direction: column;white-space: normal;  word-break: break-all;  word-wrap: break-word;">
                                    <dl v-html="tinymceHtml" ></dl>
                                </el-card>
                        </el-col>
                    </el-row>
            </el-form-item>
        </el-form>



        <div style="padding: 10px">
            <el-button type="primary" @click="save">保 存</el-button>
<!--            <el-button @click="history.back()">取 消</el-button>-->
        </div>
    </div>

</template>

<script>

    import tinymce from 'tinymce/tinymce'
    import Editor from '@tinymce/tinymce-vue'
    import 'tinymce/themes/silver/theme'
    import 'tinymce/icons/default/icons'
    import 'tinymce/themes/silver'
    import 'tinymce/plugins/image'
    import 'tinymce/plugins/link'
    import 'tinymce/plugins/code'
    import 'tinymce/plugins/table'
    import 'tinymce/plugins/lists'
    import 'tinymce/plugins/contextmenu'
    import 'tinymce/plugins/wordcount'
    import 'tinymce/plugins/colorpicker'
    import 'tinymce/plugins/textcolor'

    export default {
        name: "SelfBlog",
        components:{Editor},
        data(){
            return {
                //富文本编辑器选项
                tinymceHtml:'',
                editorInit:{
                    language_url: '/tinymce/langs/zh_CN.js',
                    language: 'zh_CN',
                    skin_url: '/tinymce/skins/ui/oxide',
                    height: 400,
                    width:600,
                    plugins: 'link lists image code table colorpicker textcolor wordcount contextmenu',
                    toolbar:
                        'bold italic underline strikethrough | fontsizeselect | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist | outdent indent blockquote | undo redo | link unlink image code | removeformat',
                    branding: false
                },
                dialogFormVisible: false,
                entity: {
                    bwriter:1,    //传入用户id
                    btitle:'',
                    btext:''
                },
                isCollapse: false,
                tags:[],
                checkedTag:[]

            }
        },
        mounted() {
            tinymce.init({});
        },

        methods:{

            save(){
                this.entity.btext=this.tinymceHtml;
                this.entity.tags=this.checkedTag;
                var params=this.$qs.stringify(this.entity);
                this.$axios
                    .post("blog/uploadBlog",params)
                    .then(rst=>{
                        console.log(rst);
                        if(rst!=0){
                            alert("发表成功");
                            this.$router.push({
                                path:'/myblog', //个人中心页面
                                // query:{
                                //     param:param,
                                //     paramtype:b
                                // }
                            });
                        }else{
                            alert("发表失败");
                        }
                    }).catch(ex=>{
                    console.log(ex);
                });
            }

        },
        created() {

            this.$axios
                .post("blog/findAllTag")
                .then(rst=>{
                    this.tags=rst.data;
                    console.log(rst);
                    console.log(this.tags)

                })
                .catch(ex=>{console.log(ex)});

            //取出用户数据
            this.entity.bwriter=JSON.parse(sessionStorage.getItem("user")).uid;
            console.log(this.entity.bwriter);
        }
    }
</script>

<style >


</style>