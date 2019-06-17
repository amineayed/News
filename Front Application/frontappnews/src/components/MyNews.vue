<template>
<div>
<div   style="display: table-cell;" class="backgroundcolor">

  
   <div class="leftnewsbox"  v-for="News in TitleAR" > 
              <img src="../assets/img/addbt.png" class="addbt"  />
             <a :href="News.Link"  ><img class="Arrow" src="../assets/img/arrow.png" ></a>
              <br>
                <img v-bind:src="News.Image"
     
                class="NewsImage">
          
              <p class="News-Title">{{ News.Title }}</p>
              <hr>
              <p class="newsdesc">{{ News.Description || "No Description" }} 
               
                 </p>
         

          </div>
       
</div> 
</div>
</template>
<script>

import axios from 'axios'
import Menu from "./Menu";
export default {
 name:"MyNews",
 components:{
  Menu
 },
  data() {
    return {
        TitleAR:[],
      
    };
  },
  created(){       
     axios.get("http://localhost:8080/News/articles/all").then((response)=>{
              var articles=response.data;
              articles.forEach(element => {    
               this.TitleAR.push(element);
                });
        
      });  
             },
             methods:{
            
          
             }
 

};
</script>
<style>
 @import '../assets/css/homepage.css';
</style>