import {getMenusByUsername} from "/@/api/menu"
import router from '/@/router'
import {RouteRecordRaw} from "vue-router";

const Layout = () => import("/@/layout/index.vue");

// // 获取用户的权限菜单
export async function getMenu(username: string) {
    const menus = await getMenusByUsername(username);
    const userList: RouteRecordRaw[] = filterRouter(menus.data)
    userList.forEach(route => {
        router.addRoute(route)
    })
    // store.commit('user/SET_MENULIST', userList)
}

// // 遍历routes，将routes中的组件导入
function filterRouter(routes: Menu[]): RouteRecordRaw[] {
    return routes.map(route => {
        let item: RouteRecordRaw = {
            name: route.name,
            component: loadView(route.component.toLowerCase()),
            path: route.path,
            children: [],
            meta: {
                icon: route.icon
            }
        };
        if (route.children && route.children.length) {
            item.children = filterRouter(route.children)
        }
        return item;
    })
}

// 将页面的组件加载到router中，注意不能使用import静态导入
function loadView(view: string): any {
    if (view === '') {
        return
    }
    if (view === 'layout') {
        return Layout
    }
    return defineAsyncComponent(() => import("/@/views/" + view + "/index.vue"))
}