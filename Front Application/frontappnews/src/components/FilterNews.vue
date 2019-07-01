<template>
<div  class="fileform">
    <br><br>
    <h1 class="ConfigTitle">News  Configuration </h1>
    <hr class="seperatorlogin">
    <p class="text">If you don't have the structure please Download the file then modify the content then
        upload it again</p>
     
          <img src="../assets/Download.png"  @click="Download()" class="downloadicone"/><br>
    <input type="file" value="GET FILE" class="bt" ref="uploadfield" @change="UploadFile()" />
    <br>

</div>
</template>
<script>
</script>
<style>
</style>
<<<<<<< HEAD
=======
<script>
import axios from 'axios'
export default{
    data(){
      return{
          file:'',
          categoriesArray:[],
          url:'',
         content:''
      }
    },
           created(){
 axios.get("http://localhost:8080/News/GetCateg/"+sessionStorage.getItem("user_ID")).then(
               (response)=>{
                  var categories=response.data;
                  categories.forEach(element =>{
                     this.categoriesArray.push(element.title);
                  })
               }
           );
         },
    methods:{
         UploadFile(){
             this.file= this.$refs.uploadfield.files[0] ;
             console.log(this.file);
         },
         Download(){
     
        var i;   
      
 for ( i = 0; i < this.categoriesArray.length; i++) {
    this.content=this.content+this.categoriesArray[i]+":ON ";
    this.content+='\n';

}

    
    var Blobfile=new Blob([this.content],{type:'text/plain;charset:utf-8'});
    var link =document.createElement('a');
    link.href = window.URL.createObjectURL(Blobfile);
    link.download ='file.txt';
    link.click();
    
          

         },
  
         
    }
};

</script>
>>>>>>> 2c0a3435bbb05f07621c0fe4d4474d0cef9c88d6
