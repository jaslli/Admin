export interface ILoginForm {
    username: string;
    password: string;
}

export interface User {
    id: string;
    username: string;
    password: string;
    nickname: string;
    avatar: string;
    email: string;
    status: string;
    createTime: string;
    createBy: string;
    updateTime: string;
    updateBy: string;
}