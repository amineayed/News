<template >
  <div id="app" >
     

 <component :is="Component"></component>
  </div>
</template>

<style>
 @import './assets/css/login.css';
</style>
<script>


 import SignUpLogin from './views/SignUpLogin'

export default{
  components : {
    SignUpLogin,

  },
  data(){
    return{
      Component:"SignUpLogin"
    }
  }
}
</script>

