import {ILoginForm, login as doLogin, logout as doLogout} from "/@/api/user";
import {removeToken, setToken} from "/@/utils/auth";

export const userStore = defineStore('user',
{
    state: () => ({}),
    getters: {},
    actions: {
        // 登录
        async login(loginForm: ILoginForm) {
            const result = await doLogin(loginForm);
            const token = result?.data;
            if (token) {
                // 设置token到cookie
                setToken(token)
            }
            return result;
        },
        // 注销
        async logout() {
            // 注销
            await doLogout();
            // 清除所有用户信息
            // 清除token
            removeToken();
        }
    }
  },
);
