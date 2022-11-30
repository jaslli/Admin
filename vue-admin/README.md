# 按需引入Element-plus

安装Element-plus依赖和自动导入依赖

```bash
# Element-plus
yarn add element-plus
# 自动导入
yarn add -D unplugin-vue-components unplugin-auto-import
```

自动导入配置参考`plugins`里的`autoImport.ts`和`components.ts`



配置全局Element-plus样式

```vue
<template>
    <el-config-provider :size="size" :z-index="zIndex">
      <router-view />
    </el-config-provider>
  </template>

<script>

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
```

# 引入vue-router4

安装依赖

```bash
yarn add vue-router@4
```

配置文件

```js
import {
  createRouter,
  createWebHashHistory
} from 'vue-router'

import Home from '@/views/Home/index.vue'
import NotFound from '@/views/404/index.vue'

const routes = [
  {
    path: '/',
    component: Home
  },
  // 查不到的去404
  { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

```

在`main.js`中去使用该路由

```js
import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'

const app = createApp(App)
// 引入vue-router
app.use(router)

app.mount('#app')
```
