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
 * @param {String} token
 * @returns true Or false
 */
export function setToken(token: string) {
    return Cookies.set(TOKEN_KEY, token)
}

/**
 * 将cookie中的Token移除
 * @returns true Or false
 */
export function removeToken() {
    return Cookies.remove(TOKEN_KEY)
}