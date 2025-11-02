<template>
  <div class="message">
    <van-nav-bar title="消息中心" left-arrow @click-left="$router.push('/home')">
      <template #right>
          <van-icon name="service-o" />
          <span style="font-size: 12px;">客服</span>
      </template>
    </van-nav-bar>
    <div class="system">
      <svg-icon iconClass="notice"></svg-icon>
      系统通知
    </div>
    <div class="empty">
      <img src="@/assets/img/message/empty_message.png" alt="">
      暂无聊天消息
    </div>
  </div>
</template>
<script setup>
 
</script>
<style lang="less" scoped>
.message {
  background-color: #f7f9fb;
  height: 100vh;
}
:deep(.van-nav-bar) {
  .van-icon {
    font-size: 18px;
  }
  .van-nav-bar__right  {
    color: var(--primary-color);
    display: flex;
    height: 100%;
    flex-direction: column;
    justify-content: center;
  }
}
.system {
  margin-top: 10px;
  height: 60px;
  padding: 0 20px;
  background-color: #fff;
  display: flex;
  align-items: center;
  font-size: 16px;
  border-top: 1px solid #e9e9e9;
  border-bottom: 1px solid #e9e9e9;
  .svg-icon {
    font-size: 40px;
    margin-right: 10px;
  }
}
.empty {
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #999;
  img {
    width: 100%;
  }
}
</style>