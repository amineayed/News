<template>

<div    class="backgroundcolor">

   <div class="leftnewsbox"  v-for="News in TitleAR" > 
              <img src="../assets/img/addbt.png" class="addbt"  />
            <img class="Arrow" src="../assets/img/arrow.png" @click="OpenNewTAB(News.Link)" >
              <br>
                <img v-bind:src="News.Image"
     
                class="NewsImage">
          
<p class="News-Title" @click="OpenNewTAB(News.Link)">{{ News.Title }}</p>
              <hr class="articleseperator">
              <p class="newsdesc">{{ News.Description || "No Description" }} 
               
                 </p>
           <label class="newsdesc"> Source : {{ News.Source }}</label>
          
          </div>
     
</div> 

</template>
<script>

import axios from 'axios'

export default {
 name:"Article",
 components:{
  
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
            
                 OpenNewTAB(link){
                  var win = window.open(link,'_blank')
                   win.focus();
                }
          
             }
 

};
</script>
<style>
 @import '../assets/css/homepage.css';
</style>