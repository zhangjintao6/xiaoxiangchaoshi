import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

import 'normalize.css'
import './assets/css/index.css'

import "virtual:svg-icons-register"
import SvgIcon from '@/components/SvgIcon/svg-icon.vue'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.component("svg-icon",SvgIcon)

app.mount('#app')
