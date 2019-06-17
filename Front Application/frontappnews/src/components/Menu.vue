<template>
  <div class="menu">
      <img src="../assets/img/logoNews.png" class="logo" />
           <br><br>
           <label class="menulinks">All News</label>
       <label class="menulinks">Categories</label>
       <label class="menulinks">Favorite Articles</label>
      <router-link to="/mynews"> <label class="menulinks">My News</label></router-link>
       
       <router-link to="/"><label class="logout">Log Out</label></router-link>
       </div>
</template>

<script>
export default{
    name:"Menu"
}
</script>
<style>
</style>