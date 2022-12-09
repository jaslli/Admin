<script lang="ts" setup>
// 表单数据
const loginForm = reactive({
  username: '',
  password: ''
})
const login = function () {
  console.log(loginForm.username)
  console.log(loginForm.password)
}
</script>

<template>
  <div class="main">
    <div class="wrapper">
      <div class="title">登录</div>
        <div class="login-username">
          <input placeholder="请输入用户名" v-model="loginForm.username">
        </div>
        <div class="login-password">
          <input placeholder="请输入密码" type="password" v-model="loginForm.password">
        </div>
        <div class="login-button">
          <button @click="login">登录</button>
        </div>
      </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  justify-content: center;
  height: 100%;
  background-image: url("https://img.yww52.com/default_cover.jpg");
  background-size:100% 100%;
  background-attachment:fixed;
  background-repeat:no-repeat;
}
.wrapper {
  display: flex;
  flex-direction: column;
  align-self: center;
  width: 350px;
  height: 360px;
  padding: 16px 24px;
  background-color: #8aa7ec;
  border-radius: 25px;
}
.title {
  margin-top: 15px;
  font-size: 25px;
  text-align: center;
}
.login-username {
  margin-top: 60px;
}
.login-password {
  margin-top: 25px;
}
.wrapper input {
  width: calc(100% - 10px);
  height: 45px;
  border: 0;
  border-radius: 10px;
  padding: 0 0 0 10px;
}
.login-button {
  margin-top: 60px;
}
.login-button button {
  width: 100%;
  height: 40px;
  border: 0;
  border-radius: 10px;
  background-color: #3B82F6;
}
</style>
