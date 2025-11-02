import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/login"
    },
    {
      path: "/home",
      component: () => import("@/views/home/home.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: "/favor",
      component: () => import("@/views/favor/favor.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: "/order",
      component: () => import("@/views/order/order.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: "/message",
      component: () => import("@/views/message/message.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: "/city",
      component: () => import("@/views/city/city.vue")
    },
    {
      path: "/search",
      component: () => import("@/views/search/search.vue"),
    },
    {
      path: '/detail/:id',
      component: () => import("@/views/detail/detail.vue"),

    },
    {
      path: '/sort',
      component: () => import("@/views/sort/sort.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: '/yanxuan',
      component: () => import("@/views/yanxuan/yanxuan.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: '/typeSearch',
      component: () => import("@/views/sort/cpns/search-sort.vue"),
    },
    {
      path: '/yanxuanSearch',
      component: () => import("@/views/yanxuan/search-yanxuan.vue"),
    },

    {
      path: '/cart',
      component: () => import("@/views/sort/cpns/cart.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: '/zuye',
      component: () => import("@/views/my/zuye.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: "/shop",
      component: () => import("@/views/shop/shangchen.vue"),
      meta: {
        showTabbar: true
      }
    },
    {
      path: '/ShopDetail',
      component: () => import("@/views/shop/ShopDetail/shopdetail.vue"),

    },
    {
      path: '/addressEdit',
      name: 'addressEdit',
      component: () => import("@/views/sort/cpns/addressEdit.vue"),
    },
    {
      path: '/addressAdd',
      component: () => import("@/views/sort/cpns/addressAdd.vue"),
    },
    {
      path: '/search-product',
      component: () => import("@/views/sort/cpns/search-product.vue"),
    },
    {
      path: '/login',
      component: () => import("@/views/login.vue"),
    },
    {
      path: '/register',
      component: () => import("@/views/register.vue"),
    },
  ]
})

export default router
