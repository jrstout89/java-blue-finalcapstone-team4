<template>
    <form v-on:submit.prevent="submitForm" class="cardForm">
  <div class="field">
  <label class="label">Name</label>
  <div class="control">
    <input class="input" type="text" placeholder="Text input" v-model="this.newPet.name">
  </div>
</div>

<div class="field">
  <label class="label">Breed</label>
  <div class="control">
    <input class="input is-success" type="text" placeholder="Text input" v-model="this.newPet.breed">
    <span class="icon is-small is-left">
      <i class="fas fa-user"></i>
    </span>
    <span class="icon is-small is-right">
      <i class="fas fa-check"></i>
    </span>
  </div>
</div>

<div class="field">
  <label class="label">Date of Birth</label>
  <div class="control">
    <input class="input is-danger" type="date" placeholder="Email input" v-model="this.newPet.dateOfBirth">
    <span class="icon is-small is-left">
      <i class="fas fa-envelope"></i>
    </span>
    <span class="icon is-small is-right">
      <i class="fas fa-exclamation-triangle"></i>
    </span>
  </div>
</div>

<div class="field gender">
  <label class="label">Gender</label>
  <div class="control">
    <div class="select">
      <select v-model="this.newPet.gender">
        <option>male</option>
        <option>female</option>
      </select>
    </div>
  </div>
</div>
<div class="field size">
  <label class="label">Size</label>
  <div class="control">
    <div class="select">
      <select v-model="this.newPet.petSize">
        <option>small</option>
        <option>medium</option>
        <option>large</option>
        <option>extra large</option>
      </select>
    </div>
  </div>
</div>

<div class="field vaccination">
  <div class="control">
    <label class="checkbox">
     Is Vaccinated <input type="checkbox" v-model="this.newPet.vaccination">
    </label>
  </div>
</div>
<div class="field spay_neuter">
  <div class="control">
    <label class="checkbox">
     Is Spayed or Neuter <input type="checkbox" v-model="this.newPet.isSpayNeuter">
    </label>
  </div>
</div>
<div class="field size">
  <label class="label">Energy Level</label>
  <div class="control">
    <div class="select">
      <select v-model="this.newPet.energyLevel">
        <option>low</option>
        <option>medium</option>
        <option>high</option>
      </select>
    </div>
  </div>
</div>

<div class="field">
  <label class="label">Personality</label>
  <div class="control">
    <textarea class="textarea" placeholder="Textarea" v-model="this.newPet.personality"></textarea>
  </div>
</div>

<!-- upload image -->

<div class="card">
  <div class="top">
    <p>Drag &drop image</p>
  </div>
  <div class="drag-area" @dragover.prevent="onDragOver" @dragleave="ondragleave" @drop.prevent="on-drop">
    <span v-if="!isDragging">
      Drag & Drop image here or 
      <span class="img-select" role="button" @click="selectFiles">
        choose file
      </span>
    </span>
    <div v-else class="img-select">Drop images here</div>
    <input name="file" type="file" class="file" ref="fileInput" multiple @change="onFileSelect"/>
  </div>
  <div class="container">
    <div class="image" v-for="(image, index) in images" :key="index">
      <span class="delete">&times;</span>
      <img :src="image.url"/>
    </div>
  </div>
  <button type="button">Upload</button>
</div>


<div class="field is-grouped">
  <div class="control">
    <button class="button is-link">Submit</button>
  </div>
  <div class="control">
    <button class="button is-link is-light" v-on:click="cancelForm">Cancel</button>
  </div>
</div>
</form>
</template>

<script>
import petService from '../services/petService';
export default {
    props: {
        pet: {
            type: Object,
            required: true
        }
    },
    data(){
        return{
            newPet:{
                id: this.pet.id,
                name: this.pet.name,
                breed: this.pet.breed,
                dateOfBirth: this.pet.dateOfBirth,
                gender: this.pet.gender,
                petSize: this.pet.petSize,
                vaccination: this.pet.vaccination,
                isSpayNeuter: this.pet.isSpayNeuter,
                energyLevel: this.pet.energyLevel,
                personality: this.pet.personality,
                image: this.pet.image
            },
          images: [],
          isDragging: false,
          
            
        }
    },
    methods:{
//submit form
      submitForm(){
            if(this.newPet.id === 0){
                petService.addPet(this.newPet).then(
                    (response) => {
                        if(response.status === 201){
                            //redirect to pets page
                        this.$router.push( {name: 'pets', params: { customerId: this.$store.state.user.id  } });
                        }
                    }
                ).catch(
                    (error) => {
                        console.log(error);
                    }
                );
            }else{
                petService.updatePet(this.newPet.id, this.newPet).then(
                 
                    (response) => {
                      
                        if(response.status === 200){
                            //redirect to pets page
                        this.$router.push( {name: 'pets', params: { customerId: this.$store.state.user.id  } });
                        }
                    }
                ).catch(
                    (error) => {
                        console.log(error);
                    }
                );
            }
        },
//cancel form
        cancelForm(){
            this.$router.push( {name: 'pets', params: { customerId: this.$store.state.user.id  } });
        },
        selectFiles(){
          this.$refs.fileInput.click();
        },
        onFileSelect(e){
         const files = e.target.files;
         if(files.length ===0) return;
         for(let i = 0; i < files.length; i++){
           if(files[i].tyoe.split('/')[0] !== 'image'){
             return alert(`${files[i].name} is not an image`);
           }
           if(!this.images.some((e) => e.name === files[i].name)){
             this.images.push({name: files[i].name, url:URL.createObjectURL(files[i])});
           }
         }
        },
        deleteImage(index){
          this.images.splice(index,1);
        },
        onDragOver(event){
          event.preventDefault();
          this.isDragging = true;
          event.dataTransfer.dropEffect = 'copy';
        },
        ondragleave(event){
          event.preventDefault();
          this.isDragging = false;
        },
        onDrop(event){
          event.preventDefault();
          this.isDragging = false;
          const files = event.dataTransfer.files;
        }


        
    
//upload image
// uploading(e) {
//   const file = e.target.files[0];
//   const reader = new FileReader();
//   reader.readAsDataURL(file);
//   reader.onload = e => {
//     this.previewImage = e.reader.result;
//     this.newPet.image = e.reader.result;
//     console.log(this.newPet.image);
//   };
// },
},
created(){
    this.newPet = this.pet;
  }
 
}
</script>

<style>
.card{
  width:100%;
  padding: 10px;
  box-shadow: 0 0  5px #ffdfdf;
  border-radius: 5px;
  overflow: hidden;
}
.card .top{
text-align: center;
}
.card p{
  font-weight: bold;
  color:aquamarine
}
.card button{
  outline:0;
  border:0;
  color: #fff;
  border-radius: 4px;
  font-weight: 400;
  padding:8px 13px;
  width:100%;
  background: aquamarine;
}
.card .drag-area{
height: 150px;
border-radius: 5px;
border: 2px dashed aquamarine;
background: #f4f3f9;
display: flex;
justify-content: center;
align-items: center;
user-select: center;
-webkit-user-select: none;
margin-top: 10px;
}
.card .drag-area .visible{
  font-size: 18px;
}
.card .img-select{
 color:#5256ad;
 margin-left: 5px;
 cursor: pointer;
 transition:0.4s;
}
.card .img-select:hover{
  opacity: 0.6;
}
.card .container{
 width: 100%;
 height:auto;
 display: flex;
 justify-content: flex-start;
 align-items: flex-start;
 flex-wrap: wrap;
 max-height: 200px;
 position: relative;
 margin-bottom: 8px;
 }
 .card .container .image{
  width: 75px;
  margin-right: 5px;
  height:75px;
  position:relative;
  margin-bottom: 8px;
 }
 .card .container .image img{
  width:100%;
  height:100%;
  border-radius:5px;
 }
 .card .container .image span{
  position:absolute;
  top:-2px;
  right: 9px;
  font-size: 20px;
  cursor:pointer;
 }
 .card input,
 .card .drag-area .on-drop,
 .card .drag-area.dragover .visible{
  display: none;
 }
 .delete{
  z-index: 999;
  color:chocolate
 }
</style>