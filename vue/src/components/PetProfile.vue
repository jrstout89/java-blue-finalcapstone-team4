<template>
  <div class="allPets">
    <h1>All Pets</h1>
    <div class="pets" v-for="pet in pets" v-bind:key="pet.id">
      <router-link :to="{name: 'petDetails', params:{id:pet.id}}">
        <img v-bind:src="pet.image" alt="pet image" width="300" height="200">
        <h3><strong>{{pet.name}}</strong></h3>
        <p>{{pet.personality}}</p>
      </router-link>
      <button @click="editPet(pet)">Edit</button>
      <button @click="deletePet(pet.id)">Delete</button>
    </div>
    
    <!-- Edit Pet Modal -->
    <!-- <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <h2>Edit Pet</h2>
        <input v-model="editedPet.name" placeholder="Pet Name">
        <input v-model="editedPet.personality" placeholder="Pet Personality">
        <input v-model="editedPet.image" placeholder="Pet Image URL">
        <button @click="updatePet">Save</button>
        <button @click="cancelEdit">Cancel</button>
      </div>
    </div> -->
  </div>
    

</template>

<script>
import petService from '../services/petService'
export default {
  data() {
    return {
      pets: [],
      showEditModal: false,
      editedPet: {}
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
    // editPet(pet) {
    //   this.editedPet = { ...pet };
    //   this.showEditModal = true;
    // },
    // updatePet() {
    //   petService.updatePet(this.editedPet.id, this.editedPet).then(
    //     () => {
    //       this.showEditModal = false;
    //       this.loadPets();
    //     }
    //   ).catch(
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },
    // cancelEdit() {
    //   this.showEditModal = false;
    // },
    deletePet(id) {
      if (confirm('Are you sure you want to delete this pet?')) {
        petService.deletePet(id).then(
          () => {
            this.loadPets();
          }
        ).catch(
          (error) => {
            console.log(error);
          }
        );
      }
    }
  }
}
</script>

<style>

</style>