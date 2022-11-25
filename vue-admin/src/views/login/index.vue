<template>
  <div class="login-container" v-loading="loading">
    <div class="login-wrapper">
      <div class="login-container-left">
        <img src="@/assets/acg.png">
      </div>
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="rules"
        label-width="100px"
        class="login-container-right"
      >
        <h1>登 录</h1>
        <div class="login-inner">
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder="用户名" />
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="loginForm.password" placeholder="密码" type="password" autocomplete="off" />
          </el-form-item>
        </div>
        <div class="submit">
          <input
            type="submit"
            value="Login"
          >
        </div>
      </el-form>
    </div>
  </div>

</template>

<script>
export default {
  data () {
    // 用户名的校验规则
    const validateUsername = (rule, value, callback) => {
      if (this.loginForm.username === '') {
        callback(new Error('请输入用户名'))
      } else {
        callback()
      }
    }
    // 密码的校验规则
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (this.loginForm.password.length < 6) {
        callback(new Error('密码的长度最少六位'))
      } else {
        callback()
      }
    }
    return {
      // 登录列表
      loginForm: {
        username: '',
        password: ''
      },
      // 校验规则
      rules: {
        username: { validator: validateUsername, trigger: 'blur' },
        password: { validator: validatePassword, trigger: 'blur' }
      },
      // loading加载效果
      loading: false
    }
  },
}
</script>

<style scoped>
.login-container {
  height: 100%;
  display: flex;
  align-items: center;
  overflow: hidden;
  background-color: #8EC5FC;
  background-image: linear-gradient(62deg, #8EC5FC 0%, #E0C3FC 100%);
}
.login-container .login-wrapper {
  height: 550px;
  width: 900px;
  min-width: 900px;
  display: flex;
  background-color: rgba(255,255,255,60%);
  box-shadow: 10px 10px 30px rgba(255,255,255,.3);
  margin: 0 auto;
  border-radius: 1.5rem;
}
.login-container .login-wrapper .login-container-left {
  width: 35%;
  height: 100%;
  background-color:skyblue;
  border-radius: 1.5rem 0 0 1.5rem;
  opacity: 75%;
}
.login-container .login-wrapper .login-container-left img {
  width: 100%;
  height: 100%;
  border-radius: 1.5rem 0 0 1.5rem;
}
.login-container .login-wrapper .login-container-right {
  display: flex;
  width: 585px;
  height: 550px;
  flex-direction: column;
  margin-top: 50px;
}
.login-container .login-wrapper .login-container-right h1 {
  color: rgb(144,129,241);
  margin-bottom: 70px;
  text-align: center;
}
.login-container .login-wrapper .login-container-right .login-inner {
  width: 500px;
  height: 160px;
}
.login-container .login-wrapper .login-container-right .login-inner .el-input__inner {
  border: 0;
  border-radius:0;
  border-bottom: 1px solid rgb(144,129,241);
  background: #E3E7FE;
  font-size: 18px;
  height: 40px;
}
.login-container .login-wrapper .login-container-right .submit input{
  width: 228px;
  height: 50px;
  color: #f6f6f6;
  font-size: 20px;
  border: none;
  border-radius: 12px;
  margin-top: 30px;
  justify-items: center;
  background-image: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);
}
.login-container .login-wrapper .login-container-right .login-inner .submit:hover {
  box-shadow: 0 0 2rem -0.5rem rgb(0 0 0 / 15%);
}

.el-input__wrapper {
  width: 200px !important;
  padding: 0 !important;
}
</style>
