export function localGet (key) {
    const value = window.localStorage.getItem(key)
    try {
        return JSON.parse(window.localStorage.getItem(key))
    } catch (error) {
        return value
    }
}

export function localSet (key, value) {
    window.localStorage.setItem(key, JSON.stringify(value))
}

export function localRemove (key) {
    window.localStorage.removeItem(key)
}

// 判断内容是否含有表情字符，现有数据库不支持。
export function hasEmoji (str = '') {
    const reg = /[^\u0020-\u007E\u00A0-\u00BE\u2E80-\uA4CF\uF900-\uFAFF\uFE30-\uFE4F\uFF00-\uFFEF\u0080-\u009F\u2000-\u201f\u2026\u2022\u20ac\r\n]/g;
    return str.match(reg) && str.match(reg).length
}

// 类型图片上传
export const uploadImgType = 'http://localhost:88/product_types/upload'
//标签图片上传
export const uploadImgLabel = 'http://localhost:88/label/upload'
//商品主图片上传
export const uploadImgProduct = 'http://localhost:88/products/upload'
// 商品详情图片上传
export const uploadImgsServer = 'http://localhost:88/img_detail/upload'

export const pathMap = {
    login: '登录',
    hello: '欢迎管理员',
    userMain: '用户管理主页',
    productMain: '商品管理主页',
    addProduct: '商品添加页',
    labelMain:'标签主页',
    addLabel:'标签添加页',
    typeMain:'类型主页',
    addType:'类型添加页',
}
