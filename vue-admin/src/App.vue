<template>
  <router-view />
</template>

<script lang="ts" setup>
import { getMenu } from "/@/router/menu"
import { checkToken } from "/@/utils/auth";
if (checkToken()) {
  getMenu("yww");
}
</script>

<style>
html,body {
  margin: 0;
}
#app {
  height: 100vh;
}
</style>