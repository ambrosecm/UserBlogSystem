import { createRouter, createWebHistory } from 'vue-router'
import UserReg from "../views/Users/UserReg";
import Login from "../views/Users/Login";
import Index from "../views/Index";
import Personal from "../views/Users/Personal"
import Userinfo from "../views/Users/Userinfo";
import Password from "../views/Users/Password";
import Myfavovideo from "../views/Users/Myfavovideo"
import Myvideo from "../views/Users/Myvideo";
import Myccomment from "../views/Users/Myccomment";
import Myblog from "../views/Users/Myblog"
import BlogsBackground from '../views/Blog/Blogs.vue'

import MainBlog from "../views/Blog/MainBlog";
import Blog from "../views/Blog/Blog";
import SelfBlog from "../views/Blog/SelfBlog";
import SelectBlog from "../views/Blog/SelectBlog";
import EditBlog from "../views/Blog/EditBlog";

import VideoMain from "../views/video/VideoMain";
import VideoPlay from "../views/video/VideoPlay";

import Main2 from "../views/main/Main2";

import Main from "../views/main/Main2";
import UserfindPwd from "../views/Users/UserfindPwd";
import Users from '../views/user/Users.vue'
import Tags from '../views/tag/Tags.vue'

import Blogs from '../views/Blog/Blogs.vue'

import Ccomments from '../views/ccomment/Ccomments.vue'
import Vcomments from '../views/vcomment/Vcomment.vue'
import Videos from '../views/video/Videos.vue'

import UserSta from "../views/background/UserSta";
import BlogSta from "../views/background/BlogSta";
import Background from "../views/background/Background";

const routes = [


  {
    path: '/index',
    name: 'Index',
    props:true,
    component: Index,
    meta:{
      title:'首页',
      requireAuth: ''
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta:{
      title:'login',
      requireAuth: ''
    }
  },
  {
    path: '/main2',
    name: 'Main2',
    component: Main2,
    children:[
      {
        path:'/blog',
        name:'Blog',
        component: Blog
      }, {
        path:'/mainblog',
        name:'MainBlog',
        component: MainBlog
      },
      {
        path:'/selectblog',
        name:'SelectBlog',
        component: SelectBlog
      },
      {

        path:'/videoMain',
        name:'VideoMain',
        component: VideoMain
      },
      {
        path:'/videoPlay',
        name:'VideoPlay',
        component: VideoPlay
      },
    ]},
  {
    path: '/personal',
    name: 'Personal',
    component: Personal,
    meta:{
      title:'personal',
      requireAuth: true
    },

    children:[
      {
        path:'/userinfo',
        name:'Userinfo',
        component:Userinfo
      },
      {
        path:'/password',
        name:'Password',
        component:Password
      },
      {
        path:'/myfavovideo',
        name:'Myfavovideo',
        component: Myfavovideo
      },
      {
        path:'/myvideo',
        name:'Myvideo',
        component: Myvideo
      },
      {
        path:'/myccomment',
        name:'Myccomment',
        component: Myccomment
      },
      {
        path:'/myblog',
        name:'Myblog',
        component: Myblog
      },
      {
        path:'/editblog',
        name:'EditBlog',
        component:EditBlog
      },
      {
        path:'/selfblog',
        name:'SelfBlog',
        component:SelfBlog
      },
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {

    path: '/reg',
    name: 'UserReg',
    component: UserReg
  },
  {

    path: '/userfindpwd',
    name: 'UserfindPwd',
    component: UserfindPwd
  },
  {

    path:'/videoMain',
    name:'VideoMain',
    component: VideoMain
  },
  {
    path:'/videoPlay',
    name:'VideoPlay',
    component: VideoPlay
  },
  {
    path:'/mainblog',
    name:'MainBlog',
    component: MainBlog
  },
  {
    path:'/blog',
    name:'Blog',
    component: Blog
  },
  {
    path:'/selectblog',
    name:'SelectBlog',
    component:SelectBlog
  },

  {
    path:'/background',
    name:'Background',
    component:Background,
    children:[
      {
        path:'/users',
        name:'Users',
        component:Users
      },
      {
        path:'/tags',
        name:'Tags',
        component:Tags
      },
      {
        path:'/blogs',
        name:'Blogs',
        component:Blogs
      },
      {
        path:'/ccomments',
        name:'Ccomments',
        component:Ccomments
      },
      {
        path:'/vcomments',
        name:'Vcomments',
        component:Vcomments
      },
      {
        path:'/videos',
        name:'Videos',
        component:Videos
      },
      {
        path:'/usersta',
        name:'UserSta',
        component:UserSta
      },
      {
        path:'/blogsta',
        name:'BlogSta',
        component:BlogSta
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
