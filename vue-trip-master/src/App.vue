<template>
  <div id="app">
    <router-view></router-view>
    <Tabbar v-show="route.meta.showTabbar"></Tabbar>
    <Loading></Loading>
  </div>

</template>

<script setup>

import Tabbar from  "@/components/Tabbar/tab-bar.vue";
import Loading from "./components/Loading/loading.vue";
import { useRoute } from "vue-router";
const route = useRoute()
</script>


<style scoped>

</style>
