import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router/index'
import { localGet } from './index'
import config from '~/config'


// 这边由于后端没有区分测试和正式，姑且都写成一个接口。
axios.defaults.baseURL = ''
// 携带 cookie，对目前的项目没有什么作用，因为我们是 token 鉴权
axios.defaults.withCredentials = true
// 请求头，headers 信息
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers['token'] = localGet('token') || ''
// 默认 post 请求，使用 application/json 形式
axios.defaults.headers.post['Content-Type'] = 'application/json'

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    // 根据条件修改请求地址 a就是后端java项目a.war  的文件名
    config.url = 'a' + config.url;
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

export default axios