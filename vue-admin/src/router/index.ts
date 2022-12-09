import { createRouter, createWebHashHistory } from 'vue-router';
import NProgress from '../plugins/nprogress/nprogress';
import Home from '/@/components/HelloWorld.vue'
import Login from '/@/views/login/index.vue'

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
  setTimeout(() => {

  }, 1000)
  next();
});

router.afterEach((_to) => {
  NProgress.done();
});

export default router