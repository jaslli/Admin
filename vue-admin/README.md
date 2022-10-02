# 使用Eslint

可以参考一下来进行[https://www.jianshu.com/p/4b94540dd998]

# 引入Element-plus，并配置按需引入

安装依赖

```bash
# 选择一个你喜欢的包管理器

# NPM
$ npm install element-plus --save

# Yarn
$ yarn add element-plus

# pnpm
$ pnpm install element-plus
```

安装自动导入的依赖

```bash
yarn add -D unplugin-vue-components unplugin-auto-import
```

在配置文件中配置

```js
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

export default defineConfig({
  plugins: [
    ......
    // ElementPlus的自动导入
    AutoImport({
      resolvers: [ElementPlusResolver()]
    }),
    Components({
      resolvers: [ElementPlusResolver()]
    }),
    .....
  ]
})

```

如果还有需要，可以配置一下全局的样式`App.vue`

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

# 使用vue-router4

安装依赖

```bash
yarn add vue-router@4
```

最初的配置

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

