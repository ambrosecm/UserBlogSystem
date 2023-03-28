import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import qs from 'qs'
import VideoPlayer from 'vue-video-player/src'
import 'vue-video-player/src/custom-theme.css'
import 'video.js/dist/video-js.css'
import 'element-plus/theme-chalk/display.css';
import * as echarts from 'echarts'

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    }
    const requireAuth = to.meta.requireAuth
    // 判断该路由是否需要登录权限
    if (requireAuth) {
        if (window.sessionStorage.getItem('user')) {
            next()
        } else {
            next('/login')
        }
    } else {
        next()  // 确保一定要有next()被调用
    }
})


let app = createApp(App)
axios.defaults.baseURL="http://localhost:8888/"
app.config.globalProperties.$axios=axios
app.config.globalProperties.$qs=qs
app.config.globalProperties.$echarts = echarts
app.use(ElementPlus)
app.use(VideoPlayer)

app.use(router).mount('#app')
