<template>
    <div class="petsDetails">
    
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
        <div class="buttons">
        <router-link to="/pets/add-pet">
             <button class="button is-info">Add</button>
        </router-link>
         <button class="button is-success">Edit</button>
         <button class="button is-warning">Delete</button>
        </div>
    </div>
</template>

<script>
import petService from '../services/petService'
export default {
    data(){
        return{
            pets: []
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

}
</script>

<style>

</style>