import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/login.vue')
        },
        {
            path: '/',
            name: 'hello',
            component: () => import('../components/HelloWorld.vue')
        },
        {
            path: '/userMain',
            name: 'userMain',
            component: () => import('../views/user/userMain.vue')
        },
        {
            path: '/productMain',
            name: 'productMain',
            component: () => import('../views/product/productMain.vue')
        },
        {
            path: '/addProduct',
            name: 'addProduct',
            component: () => import('../views/product/addProduct.vue')
        },
        {
            path: '/typeMain',
            name: 'typeMain',
            component: () => import('../views/type/typeMain.vue')
        },
        {
            path: '/addType',
            name: 'addType',
            component: () => import('../views/type/addType.vue')
        },
        {
            path: '/labelMain',
            name: 'labelMain',
            component: () => import('../views/label/labelMain.vue')
        },
        {
            path: '/addLabel',
            name: 'addLabel',
            component: () => import('../views/label/addLabel.vue')
        },
        {
            path: '/promotionMain',
            name: 'promotionMain',
            component: () => import('../views/promotion/promotionMain.vue')
        },
    ]
})

export default router