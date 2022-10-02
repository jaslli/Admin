import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'

const app = createApp(App)
// 引入vue-router
app.use(router)

app.mount('#app')
