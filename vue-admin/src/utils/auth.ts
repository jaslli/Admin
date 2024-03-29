import Cookies from "js-cookie";

/**
 * Token的名称
 */
const TOKEN_KEY = 'Admin-Token'

/**
 * 从cookie中获取cookie
 * @returns token
 */
export function getToken() {
    return Cookies.get(TOKEN_KEY)
}

/**
 * 将Token存放到cookie中
 * @param {String} data
 * @returns true Or false
 */
export function setToken(data: { token: string }) {
    return Cookies.set(TOKEN_KEY, data)
}

/**
 * 将cookie中的Token移除
 * @returns true Or false
 */
export function removeToken() {
    return Cookies.remove(TOKEN_KEY)
}

/**
 * 检查cookie中是否存在Token
 * @returns true Or false
 */
export function checkToken() {
    let token: any = getToken();
    return typeof token !== "undefined";
}