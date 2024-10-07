<template>
    <h1>Add pet</h1>
    <pet-form :pet="petObject"/>
</template>

<script>
import PetForm from '../components/PetForm.vue';

export default {
    components: {
        PetForm
    },
    data() {
    return {
      petObject:{
        customerId: 1,
        name: '',
        breed: '',
        dateOfBirth: '',
        gender: '',
        petSize: '',
        vaccination: false,
        isSpayNeuter:false,
        energyLevel: "",
        personality: '',
        image: ''
      }
    };
  },


}
</script>

<style>

</style>