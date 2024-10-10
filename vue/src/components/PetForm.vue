<template>
    <form v-on:submit.prevent="submitForm" class="cardForm">
      <div>
      <div class="button is-info" v-on:click="upload">Upload your fur friend picture!</div><br>
      <img v-bind:src="newPet.image" alt="pet image" width="300" height="200" v-if="pet.image">
      
  </div>

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
     Is Spayed or Neuter <input type="checkbox" v-model="this.newPet.spayNeuter">
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
  name: 'CloudinaryComp',
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
                spayNeuter: this.pet.spayNeuter,
                energyLevel: this.pet.energyLevel,
                personality: this.pet.personality,
                image: this.pet.image
            },
          myWidget: {}
          
            
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
        //cloudinary
        upload() {
        this.myWidget.open();
      },
},
created(){
    this.newPet = this.pet;
  },
  mounted() {
    this.myWidget = window.cloudinary.createUploadWidget(
      {
        cloudName: 'dzksumgzr',
        uploadPreset: 'pdswxfwn'
      },
      (error, result) => { 
        if (!error && result && result.event === "success") {
          console.log('Done! Here is the image info: ', result.info);
          console.log("Image URL: " + result.info.url);
          this.newPet.image = result.info.url;
        }
      }

    );
  }
 
}
</script>

<style>
.cardForm{
    margin: 0 auto;
    width: 50%;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
    margin-top: 20px;
    box-shadow: #ccc 2px 2px 2  ;
}
</style>