<script lang="ts" setup>
import Aside from "./components/aside/index.vue";
import Header from "./components/header/index.vue";
import Main from "./components/main/index.vue";
</script>

<template>
  <el-container style="height: 100%">
    <Aside />
    <el-container direction="vertical">
      <Header />
      <Main />
    </el-container>
  </el-container>
</template>
