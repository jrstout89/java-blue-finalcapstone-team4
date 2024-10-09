<template>
  <div class="allPets">
    <h1>All Pets</h1>
        <router-link to="/pets/add-pet">
             <button class="button is-info">Add</button>
        </router-link>
        <div class="buttons">

       </div>
   </div>
    <div class="pets" v-for="pet in pets" v-bind:key="pet.id">
      <router-link :to="{name: 'petDetails', params:{id:pet.id}}">
        <img v-bind:src="pet.image" alt="pet image" width="300" height="200">
        <h3><strong>{{pet.name}}</strong></h3>
        <p>{{pet.personality}}</p>
      </router-link>
  
    </div>
  
</template>

<script>
import petService from '../services/petService'
export default {
  data() {
    return {
      pets: [],
    }
  },
  created() {
    this.loadPets();
  },
  methods: {
    loadPets() {
      petService.getAllPetsByCustomerId(this.$route.params.customerId).then(
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
}
</script>

<style>
.pets {
  display: flex;
  flex-direction: row;
  margin-top: 20px;

}

</style>