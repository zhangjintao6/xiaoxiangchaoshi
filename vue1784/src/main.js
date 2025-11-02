import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import  axios  from 'axios'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import locale from 'element-plus/es/locale/lang/zh-cn'

createApp(App).use(ElementPlus).mount('#app');
// 设置基础访问路径
axios.defaults.baseURL='http://127.0.0.1:88/'

const app = createApp(App)
app.use(router)
app.use(ElementPlus, {locale })
app.mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.config.globalProperties.$axios = axios