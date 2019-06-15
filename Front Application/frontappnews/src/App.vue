<template >
  <div id="app" >
      <router-view>
    </router-view>
    <HomePage />

  </div>
</template>

<style>
 @import './assets/css/login.css';
</style>
<script>
 import Login from './components/Login.vue'
 import HomePage from './components/Login'
export default{
  components : {
    Login,
    HomePage
  }
}
</script>

