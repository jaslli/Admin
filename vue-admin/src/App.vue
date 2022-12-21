<template>
  <router-view />
</template>

<script lang="ts" setup>
import { getMenu } from "/@/utils/menu"
getMenu("yww");
</script>

<style>
html,body {
  margin: 0;
}
#app {
  height: 100vh;
}
</style>