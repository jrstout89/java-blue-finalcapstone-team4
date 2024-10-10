<template>
  <div class="allPets">
    <h1>All Pets</h1>
    <div class="buttons">
        <router-link to="/pets/add-pet">
             <button class="button is-info">Add</button>
        </router-link>
        
          <router-link to="/user">
            <button class="button is-info">Previous Page</button>
          </router-link>
       </div>
   </div>
    <div class="pets" v-for="pet in pets" v-bind:key="pet.id">
      <router-link :to="{name: 'petDetails', params:{id:pet.id}}">
        <img v-bind:src="pet.image" alt="pet image"/>
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
  margin-left: 20px;
  margin-right: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  box-shadow: 2px 2px 2px #ccc;
  flex-wrap: wrap;
  width: 300px;
  height: auto;
}
.allPets {
  display: flex;
  flex-direction: column;
  align-items: center;
}
h1{
  font-size: 1.5em;
  color: #333;
  font-family: cursive;
}


</style>