import { createRouter, createWebHashHistory } from 'vue-router';

import Home from '/@/components/HelloWorld.vue'

const routes = [
  {
    path: '/',
    component: Home
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router