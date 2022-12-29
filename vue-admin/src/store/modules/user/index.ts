import { getInfo as doGetInfo, login as doLogin, logout as doLogout } from "/@/api/user";
import { ILoginForm } from "/@/api/user/types"
import {removeToken, setToken} from "/@/utils/auth";

export const userStore = defineStore('user',
{
    state: () => ({}),
    getters: {},
    actions: {
        /**
         * 登录
         *
         * @param loginForm
         */
        async login(loginForm: ILoginForm) {
            const result = await doLogin(loginForm);
            const token = result?.data;
            if (token) {
                // 设置token到cookie
                setToken(token)
            }
        },
        /**
         * 注销
         */
        async logout() {
            // 注销
            await doLogout();
            // 清除所有用户信息
            // 清除token
            removeToken();
        },
        /**
         * 根据用户名获取用户信息
         *
         * @param username
         */
        async getInfo(username: string) {
            const result = await doGetInfo(username);
            console.log(result)
        }
    }
  },
);
