import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from "vite"
import vue from "@vitejs/plugin-vue"
import eslint from "vite-plugin-eslint"

export default defineConfig({
  plugins: [
    vue(),
    eslint()
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    }
  }
})
