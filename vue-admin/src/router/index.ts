import { createRouter, createWebHashHistory } from 'vue-router';
import NProgress from '/@/plugins/nprogress';
import { getToken } from "/@/utils/auth";

// 默认路由
const routes = [
  {
    path: '/',
    component: () => import('/@/components/HelloWorld.vue')
  },
  {
    path: '/login',
    component: () => import('/@/views/login/index.vue')
  },
  {
    path: '/layout',
    component: () => import('/@/layout/index.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach(async (_to, _from, next) => {
  NProgress.start();
  // 如果去往登录页，直接跳转
  if (_to.path === "/login") {
    next();
  }
  // 如果没有token,跳转至登录页
  if (!getToken()) {
    next({ path: '/login' })
  } else {
    next();
  }
});

router.afterEach((_to) => {
  NProgress.done();
});

export default router