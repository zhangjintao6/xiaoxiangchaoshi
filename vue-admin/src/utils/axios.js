import axios from 'axios'



// 后端接口地址。
axios.defaults.baseURL = 'http://localhost:88/'
// 携带 cookie，对目前的项目没有什么作用，因为我们是 token 鉴权
axios.defaults.withCredentials = true
// 请求头，headers 信息
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers['token'] = localStorage.getItem('token') || ''
// 默认 post 请求，使用 application/json 形式
axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.interceptors.response.use((res=> res.data)) //axios直接返回数据


export default axios