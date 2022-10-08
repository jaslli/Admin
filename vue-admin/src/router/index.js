import {
  createRouter,
  createWebHashHistory
} from 'vue-router'

import Home from '@/views/Home/index.vue'
import NotFound from '@/views/404/index.vue'
import Login from 'views/login/index.vue'
import Layout from '@/layout/index.vue'
import Test from 'components/Test.vue'

const routes = [
  {
    path: '/',
    component: Home
  },
  // 登录页
  { path: '/login', name: 'Login', component: Login },
  { path: '/layout', name: 'Layout', component: Layout },
  { path: '/test', name: 'Test', component: Test },
  // 查不到的去404
  { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
