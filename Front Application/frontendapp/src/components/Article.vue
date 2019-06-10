<template>
<div   style="display: table-cell;">
 
   <div class="leftnewsbox"  v-for="News in TitleAR" > 
              <img src="../assets/img/addbt.png" class="addbt" />
               <img class="Arrow" src="../assets/img/arrow.png">
              <br>
                <img src="../assets/img/france24.png"
     
                class="NewsImage">
         
              <p class="News-Title">{{ News.Title }}</p>
              <p class="newsdesc">{{ News.Description }} 
               
                 </p>
         
          </div>
</div>  
</template>
<script>
import Vue from 'vue'
import axios from 'axios'
var vm=this;
export default {
 name:"Article",
  data() {
    return {
        TitleAR:[]
    };
  },
    created(){
 
        
     axios.get("http://localhost:8080/News/articles/all").then((response)=>{
              var articles=response.data;
              articles.forEach(element => {
             
               this.TitleAR.push(element);
               // console.log(element.Title);
             
            
             });
        
      });
            
          /*articles.forEach(element => {
             
               this.TitleAR.push(element.Title);
               // console.log(element.Title);
             
            
             });*/
    
             },
 

};
</script>
<style>
</style>