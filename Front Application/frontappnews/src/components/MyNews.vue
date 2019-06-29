<template>
<div>
<div   style="display: table-cell;" class="backgroundcolor">

 <h1 v-if="message!=''">{{message}}</h1>
   <div class="leftnewsbox"  v-for="News in TitleAR" :key="News.ID"> 
              <img src="../assets/img/addbt.png" class="addbt"  />
            <img class="Arrow" src="../assets/img/arrow.png" @click="OpenNewTAB(News.Link)" >
              <br>
                <img v-bind:src="News.Image"
     
                class="NewsImage">
          
              <p class="News-Title" @click="OpenNewTAB(News.Link)">{{ News.Title }}</p>
              <hr>
              <p class="newsdesc">{{ News.Description || "No Description" }} 
               
                 </p>
         

          </div>
       
</div> 
</div>
</template>
<script>

import axios from 'axios'
import Menu from "../Menu";
export default {
 name:"MyNews",
 components:{
  Menu
 },
  data() {
    return {
        TitleAR:[],
        message:''
      
    };
  },
  created(){       
     axios.get("http://localhost:8080/News/GetCategoriesedArticles/"+sessionStorage.getItem("user_ID")).then((response)=>{
              var articles=response.data;
              if(articles.message==null){
              articles.forEach(element => {    
               this.TitleAR.push(element);
                });
     }else{
       this.message=articles.message;

     }
        
      });  
             },
             methods:{
                OpenNewTAB(link){
                  var win = window.open('link','_blank')
                   win.focus();
                }
          
             }
 

};
</script>
<style>
 @import '../assets/css/homepage.css';
</style>