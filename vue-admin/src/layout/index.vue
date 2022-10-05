<template>
  <el-container>
    <!-- 侧边栏 -->
    <el-aside width="200px">
      <Aside />
    </el-aside>
    <!-- 右边部分 -->
    <el-container>
      <!-- Header部分 -->
      <el-header>
        <Header />
      </el-header>
      <!-- 主体内容 -->
      <el-main>
        <Main />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import Aside from 'components/Aside.vue'
import Header from 'components/Header.vue'
import Main from 'components/Main.vue'
</script>

<style scoped>
.el-container {
  height: 100%;
}
.el-main {
  background-color: #FFFFFF;
  color: #333;
  padding: 0;
}
.el-header {
  background-color: #FFFFFF;
  padding: 0;
  border-bottom: 1px solid #97E1F1;
}

.el-aside {
  border-right: 1px solid #97E1F1;
}
</style>
