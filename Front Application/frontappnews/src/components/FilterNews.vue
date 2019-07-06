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
import axios from "axios"
export default{
    data(){
      return{
          file:'',
          categoriesArray:[],
          url:'',
         content:'',
         FileCateg:''
      }
    },
           created(){
 axios.get("http://localhost:8080/News/getcategfile/"+sessionStorage.getItem("user_ID")).then(
               (response)=>{
                  var categories=response.data;
                  categories=categories.substr(1,categories.length-2);
                  this.categoriesArray=categories.split(".");
                  
                console.log(this.categoriesArray);

               }
           );
         },
    methods:{
         UploadFile(){
          
             this.file= this.$refs.uploadfield.files[0] ;
            var fileReader= new FileReader();
  
  fileReader.addEventListener("load",function(){
              var ch;
            ch= fileReader.result;
         console.log(ch);
       axios.get("http://localhost:8080/News/fileupload/"+sessionStorage.getItem("user_ID")+"/"+ch).then(
           (response)=>{
            
           }
       );
      
        })
        fileReader.readAsText(this.file);

         },
         Download(){
     
        var i;   
      
 for ( i = 0; i < this.categoriesArray.length-1; i++) {
    
    this.content=this.content+this.categoriesArray[i]+".\r\n";
    

}
this.content=this.content+this.categoriesArray[this.categoriesArray.length-1];
    
    var Blobfile=new Blob([this.content],{type:'text/plain;charset:utf-8'});
    this.content="";
    var link =document.createElement('a');
    link.href = window.URL.createObjectURL(Blobfile);
    link.download ='file.txt';
    link.click();
    
          

         },
  
         
    }
};

</script>
<style>
</style>