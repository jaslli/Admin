interface Menu {
    id: string;
    pid: string;
    name: string;
    type: boolean;
    visible: boolean;
    path: string;
    component: string;
    icon: string;
    sort: number;
    code: string;
    children: Menu[];
    createTime: string;
    createBy: string;
    updateTime: string;
    updateBy: string;
}