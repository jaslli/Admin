<script setup lang="ts">
let msg: String = "hello world"
ref(msg)
</script>

<template>
  <h1>{{ msg }}</h1>
</template>

<style scoped>
</style>
