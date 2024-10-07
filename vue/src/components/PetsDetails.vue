<template>
    <div class="petsDetails">
        <button class="button is-success" @click="editPet()">Edit</button>
        <button class="button is-warning" @click="deletePet()">Delete</button>
    
        <div class="pets">
            <img v-bind:src="pets.image" alt="pet image" width="300" height="200">
            <h3><strong>{{pets.name}}</strong></h3>
            <p>{{pets.personality}}</p>
             <p><strong>Breed: </strong>{{pets.breed}}</p>
            <p><strong>Date of Birth: </strong>{{pets.dateOfBirth}}</p>
            <p><strong>Gender: </strong>{{pets.gender}}</p>
            <p><strong>Size: </strong>{{pets.petSize}}</p>
            <p><strong>Vaccination: </strong>{{pets.vaccination}}</p>
            <p><strong>Spay or Neuter: </strong>{{pets.isSpayNeuter}}</p>
            <p><strong>Energy Level: </strong>{{pets.energyLevel}}</p>
        </div>
</div>
</template>

<script>
import petService from '../services/petService'
export default {
    data(){
        return{
            pets:{}
        }
    },
    created(){
        petService.getPetById(this.$route.params.id).then(
            (response) => {
                this.pets = response.data;
            }
        ).catch(
            (error) => {
                console.log(error);
            }
        );
    },
    methods:{
    //delete pet
    deletePet() {
      if (confirm('Are you sure you want to delete this pet?')) {
        console.log(`Attempting to delete pet with ID: ${this.pets.id}`);
        petService.deletePet(this.pets.id).then(
          (response) => {
            if (response.status === 204) {
                console.log('Pet deleted successfully. Redirecting...');
                //redirect to pets page
              this.$router.push({ name: 'pets', params: { customerId: this.$store.state.user.id } });
            }
          }
        ).catch(
          (error) => {
            console.log(error);
          }
        );
      }
    },
    //edit pet
    editPet(){
        this.$router.push({name: 'updatePet', params: {id: this.pets.id}});
    }

 
    }

}
</script>

<style>

</style>