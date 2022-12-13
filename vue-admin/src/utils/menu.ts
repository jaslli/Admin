import {getMenusByUsername} from "/@/api/menu"
import router from '/@/router'

const Layout = () => import("/@/layout/index.vue");

// 获取用户的权限菜单
export function getMenu(username) {
    const menus = getMenusByUsername(username);
    const userList = filterRouter(menus)
    userList.forEach(route => {
        router.addRoute(route)
    })
    // store.commit('user/SET_MENULIST', userList)
}

// 遍历routes，将routes中的组件导入
function filterRouter(routes) {
    return routes.filter(route => {
        route.meta = {}
        route.meta.title = route.title
        route.component = loadView(route.component.toLowerCase())
        // TODO
        // if (route.icon != null) {
        //   route.icon = 'iconfont' + route.icon
        // }
        if (route.children && route.children.length) {
            route.children = filterRouter(route.children)
        }
        return true
    })
}

// 将页面的组件加载到router中，注意不能使用import静态导入
const loadView = (view) => {
    if (view === '') {
        return
    }
    if (view === 'layout') {
        return Layout
    }
    return () => import(`/@/views/${view}`)
}