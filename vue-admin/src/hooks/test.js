import { reactive, onMounted, onBeforeMount } from "vue"
export default function() {
  // 鼠标坐标的数据
  let point = reactive({
    x: 0,
    y: 0
  })
  // 鼠标保存
  function savePoint(event) {
    point.x = event.pageX
    point.y = event.pageY
    console.log(event.pageX, event.pageY)
  }
  // 鼠标点击的生命周期钩子
  // 当挂载之后获取鼠标坐标
  onMounted(() => {
    window.addEventListener('click',savePoint)
  })
  // 当卸载之后移除这个click事件
  onBeforeMount(() => {
    window.removeEventListener('click',savePoint)
  })
  return point
}