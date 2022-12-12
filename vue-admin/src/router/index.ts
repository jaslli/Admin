import { createRouter, createWebHashHistory } from 'vue-router';
import NProgress from '/@/plugins/nprogress';
import Home from '/@/components/HelloWorld.vue'
import Login from '/@/views/login/index.vue'
import { getToken } from "/@/utils/auth";

const routes = [
  {
    path: '/',
    component: Home
  },
  {
    path: '/login',
    component: Login
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