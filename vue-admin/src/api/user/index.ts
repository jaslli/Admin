import { http } from "/@/utils/http"
import { Result } from "/#/resultType";
import {ILoginForm, User} from "./types"

enum API {
    LOGIN = "/login",
    LOGOUT = "/logout",
    GETINFO = "/user/getById/"
}

/**
 *  登录
 */
export const login = (data: ILoginForm) => {
    return new Promise<Result<{ token: string }>>(async (resolve, reject) => {
        try {
            const res = await http.post<{}, Result<{ token: string }>>(API.LOGIN, {
                data
            });
            resolve(res);
        } catch (error) {
            reject(error);
        }
    });
};

/**
 *  注销
 */
export const logout = () => {
    return new Promise<Result<any>>(async (resolve, reject) => {
        try {
            const res = await http.post<{}, Result<{ token: string }>>(API.LOGOUT);
            resolve(res);
        } catch (error) {
            reject(error);
        }
    });
};

/**
 *  获取用户信息
 */
export const getInfo = (username: String) => {
    return new Promise<Result<User>>(async (resolve, reject) => {
        try {
            const res = await http.get<{}, Result<User>>(API.GETINFO + username);
            resolve(res);
        } catch (error) {
            reject(error);
        }
    });
};