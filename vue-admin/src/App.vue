<template>
  <el-config-provider :size="size" :z-index="zIndex">
    <router-view />
  </el-config-provider>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { ElConfigProvider } from 'element-plus'

export default defineComponent({
  components: { ElConfigProvider },
  setup () {
    return {
      zIndex: 3000,
      size: 'small',
    }
  },
})
</script>

<style>
html,body {
  margin: 0;
}

#app {
  height: 100vh;
}

::selection {
  color: #fff;
  background-color: rgb(144, 129, 241);
}

</style>