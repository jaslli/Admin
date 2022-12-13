import { http } from "/@/utils/http"
import { Result } from "/#/resultType";
import { ILoginForm } from "./types"

enum API {
    LOGIN = "/login",
    LOGOUT = "/logout"
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
    return new Promise<Result<{ token: string }>>(async (resolve, reject) => {
        try {
            const res = await http.post<{}, Result<{ token: string }>>(API.LOGOUT);
            resolve(res);
        } catch (error) {
            reject(error);
        }
    });
};