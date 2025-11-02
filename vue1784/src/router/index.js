import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(), // hash模式：createWebHashHistory，history模式：createWebHistory
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path:'/orders',
            name:'orders',
            component: () => import('../view/orders.vue')
        },
        {
            path:'/HelloWorld',
            name:'HelloWorld',
            component: () => import('../components/HelloWorld.vue')
        },
        {
            path:'/data',
            name:'data',
            component: () => import('../view/data.vue')
        },
        {
            path:'/mine',
            name:'mine',
            component: () => import('../view/mine.vue')
        },
        {
            path:'/today',
            name:'today',
            component: () => import('../view/today.vue')
        },
        {
            path:'/month',
            name:'month',
            component: () => import('../view/month.vue')
        },
        {
            path:'/last-seven-days',
            name:'last-seven-days',
            component: () => import('../view/last-seven-days.vue')
        },
        {
            path:'/last-six-months',
            name:'last-six-months',
            component: () => import('../view/last-six-months.vue')
        },
        {
            path:'/login',
            name:'login',
            component: () => import('../view/login.vue')
        },
        {
            path:'/register',
            name:'register',
            component: () => import('../view/register.vue')
        }


    ]
})

export default router