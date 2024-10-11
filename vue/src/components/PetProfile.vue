<template>
  <div class="allPets">
    <h1 id="header">ALL PETS</h1>
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
      <h3><router-link :to="{name: 'petDetails', params:{id:pet.id}}">
        <strong>{{pet.name}}</strong></router-link></h3>
        <p>{{pet.personality}}</p>
      <router-link :to="{name: 'petDetails', params:{id:pet.id}}">
        <figure class="image is-400x350">
        <img v-bind:src="pet.image" alt="pet image"/>
      </figure>
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

<style scoped>
.pets {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  box-shadow: 2px 2px 2px #ccc;
  flex-wrap: wrap;
}
.allPets {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  box-shadow: 2px 2px 2px #ccc;
  flex-wrap: wrap;
}
#header {
  font-size: 3em;
  color: green;
  font-family: cursive;
  font-weight: bold;
}
strong {
  font-size: 2em;
  font-family: cursive;
  font-weight: bold;
}
p {
  font-size: 1.25em;
  font-family: cursive;
  font-weight: bold;
  color: hotpink;
}

</style>