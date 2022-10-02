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
