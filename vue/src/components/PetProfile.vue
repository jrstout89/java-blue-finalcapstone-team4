<template>
  <div class="allPets">
    <h1>All Pets</h1>
    
    <div class="pets" v-for="pet in pets" v-bind:key="pet.id">
        <router-link :to="{name: 'petDetails', params:{id:pet.id}}">
            <img v-bind:src="pet.image" alt="pet image" width="300" height="200">
            <h3><strong>{{pet.name}}</strong></h3>
            <p>{{pet.personality}}</p>
        </router-link>
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
        petService.getPets().then(
            (response) => {
                this.pets = response.data;
            }
        ).catch(
            (error) => {
                console.log(error);
            }
        );
    },
    computed:{
        
    }
}
</script>

<style>

</style>