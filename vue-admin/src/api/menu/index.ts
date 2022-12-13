import { http } from "/@/utils/http"
import { Result } from "/#/resultType";

enum API {
    GETMENUSBYUSERNAME = "/menu/getMenus/"
}

/**
 *  根据用户名获取菜单数据
 */
export const getMenusByUsername = (username: string) => {
    return new Promise<Result<Menu[]>>(async (resolve, reject) => {
        try {
            const res = await http.get<{}, Result<Menu[]>>(API.GETMENUSBYUSERNAME + username);
            console.log(res)
            resolve(res);
        } catch (error) {
            reject(error);
        }
    });
};

