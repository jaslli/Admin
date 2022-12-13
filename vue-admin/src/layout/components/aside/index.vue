<script lang="ts" setup>
import Logo from "./logo.vue"
</script>

<template>
  <el-aside width="230px" style="background-color: red">
    <Logo />
    <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        :collapse="false"
        @open="handleOpen"
        @close="handleClose"
    >
      <el-sub-menu index="1">
        <template #title>
          <span>Navigator One</span>
        </template>
        <el-menu-item-group>
          <template #title><span>Group One</span></template>
          <el-menu-item index="1-1">item one</el-menu-item>
          <el-menu-item index="1-2">item two</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="Group Two">
          <el-menu-item index="1-3">item three</el-menu-item>
        </el-menu-item-group>
        <el-sub-menu index="1-4">
          <template #title><span>item four</span></template>
          <el-menu-item index="1-4-1">item one</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-menu-item index="2">
        <template #title>Navigator Two</template>
      </el-menu-item>
      <el-menu-item index="3" disabled>
        <template #title>Navigator Three</template>
      </el-menu-item>
      <el-menu-item index="4">
        <template #title>Navigator Four</template>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<style scoped>

</style>
