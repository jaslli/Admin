<template>
<div class="header-container">
<!-- 左侧的面包屑 -->
  <el-breadcrumb class="header-left" :separator-icon="ArrowRight">
    <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
    <el-breadcrumb-item>promotion management</el-breadcrumb-item>
    <el-breadcrumb-item>promotion list</el-breadcrumb-item>
    <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
  </el-breadcrumb>
  <!-- 右侧的个人信息 -->
  <div class="header-right">
    <el-avatar
      src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      size="50"
    />
  </div>
  <!-- <el-dropdown>
    <span class="el-dropdown-link">
      <el-avatar size="large" :src="userInfo.avatar" />
      <div class="userName">{{ userInfo.username }}</div>
    </span>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item>
        <el-button class="button" type="text" @click="toAbout">个人中心</el-button>
      </el-dropdown-item>
      <el-dropdown-item>
        <el-button class="button" type="text" @click="doLogout">退出登录</el-button>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown> -->
</div>
</template>

<script setup>

</script>

<style scoped>
.header-container {
  display: flex;
  justify-content: space-between;
}
.header-left {
  margin-left: 15px;
  align-self: center;
}

.header-right {
  display: flex;
  margin-right: 15px;
  height: 100%;
}

.header-right .el-avatar {
  height: 48px;
  width: 48px;
  align-self: center;
}

</style>
