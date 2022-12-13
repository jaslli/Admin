<script lang="ts" setup>
// logo地址
const logo = "https://img.yww52.com/icon.ico";
// 系统名称
const title = "后台管理系统";
</script>

<template>
  <div class="logo-wraper">
    <img :src="logo" alt="logo">
    <p>{{ title }}</p>
  </div>
</template>

<style scoped>
.logo-wraper {
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: center;
  background-color: blueviolet;
}
.logo-wraper img{
  width: 32px;
  height: 32px;
  margin: auto 3px auto 0px;
}
.logo-wraper p{
  font-size: 18px;
  font-weight: bold;
}
</style>
