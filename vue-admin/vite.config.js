import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import { resolve } from 'path'
import eslintPlugin from 'vite-plugin-eslint'

export default defineConfig({
  plugins: [
    vue(),
    // ElementPlus的自动导入
    AutoImport({ resolvers: [ElementPlusResolver()] }),
    Components({ resolvers: [ElementPlusResolver()] }),
    // eslint的配置
    eslintPlugin({ include: ['src/**/*.js', 'src/**/*.vue', 'src/*.js', 'src/*.vue'] })
  ],
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src'),
      views: resolve(__dirname, 'src/views'),
      utils: resolve(__dirname, 'src/utils'),
      components: resolve(__dirname, 'src/components'),
    }
  }
})
