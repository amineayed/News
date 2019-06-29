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
<style>
</style>
<script>
import axios from 'axios'
export default{
    data(){
      return{
          file:'',
          categoriesArray:[],
          url:'',
    
      }
    },
    methods:{
         UploadFile(){
             this.file= this.$refs.uploadfield.files[0] ;
             console.log(this.file);
         },
         Download(){
      axios.get("http://localhost:8080/News/GetCateg/"+sessionStorage.getItem("user_ID")).then(
               (response)=>{
                  var categories=response.data;
                  categories.forEach(element =>{
                     this.categoriesArray.push(element.title);
                  })
               }
           );
        var i;   
        var content="";
 for ( i = 0; i < this.categoriesArray.length; i++) {
    content=content+this.categoriesArray[i]+": ON ";

}
console.log(content);

    var Blobfile=new Blob([content],{type:'text/plain;charset:utf-8'});
    var link =document.createElement('a');
    link.href = window.URL.createObjectURL(Blobfile);
    link.download ='file.txt';
    link.click();
    
          

         },
         
    }
};

</script>