<template>
    <h1>Add pet</h1>
    <pet-form :pet="petObject"/>
</template>

<script>
import PetForm from '../components/PetForm.vue';
import petService from '../services/petService';

export default {
    components: {
        PetForm
    },
    data() {
    return {
      // petObject:{
      //   id: 0,
      //   name: '',
      //   breed: '',
      //   dateOfBirth: '',
      //   gender: '',
      //   petSize: '',
      //   vaccination: false,
      //   isSpayNeuter:false,
      //   energyLevel: "",
      //   personality: '',
      //   image: ''
      // }
      petObject: {}
    };
  },
  methods:{
    getPet(){
      petService.getPetById(this.$route.params.id).then(
        (response) => {
          this.petObject = response.data;
        }
      ).catch(
        (error) => {
          console.log(error);
        }
      );
    }
  },
  created(){
    this.getPet();
  }


}
</script>

<style>

</style>