import {getMenusByUsername} from "/@/api/menu"
import router from '/@/router/index'
import {RouteRecordRaw} from "vue-router";

const Layout = defineAsyncComponent(() => import("/@/layout/index.vue"))

// 获取用户的权限菜单
export async function getMenu(username: string) {
    const menus = await getMenusByUsername(username);
    const userList: RouteRecordRaw[] = getRoute(menus.data)
    userList.forEach(route => {
        router.addRoute(route)
    })
    // store.commit('user/SET_MENULIST', userList)
}

// 遍历routes，将routes中的组件导入
function getRoute(routes: Menu[]): RouteRecordRaw[] {
    return routes.map(route => {
        let item: RouteRecordRaw = {
            name: route.name,
            component: loadComponent(route.component.toLowerCase()),
            path: route.path,
            children: [],
            meta: {
                icon: route.icon
            }
        };
        if (route.children && route.children.length) {
            item.children = getRoute(route.children)
        }
        return item;
    })
}

// 将页面的组件加载到router中
function loadComponent(view: string): any {
    if (view === '') {
        return
    }
    if (view === 'layout') {
        return Layout
    }
    return defineAsyncComponent(() => import("/@/views/" + view + "/index.vue"))
}