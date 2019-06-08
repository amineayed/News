<template>
  <div id="app">
   <Menu />
       <br>
       <div class="pagecontent">
           <br>    <br>    <br>    <br>
    <Article />

       
       </div>

  </div>
</template>

<script>
import Vue from 'vue'
import Article from './components/Article.vue'
import Menu  from './components/Menu.vue'
export default {
  name: 'app',
 components:{
   Article,
   Menu
 }
}

</script>

<style>
  @import './assets/css/homepage.css';
</style>
