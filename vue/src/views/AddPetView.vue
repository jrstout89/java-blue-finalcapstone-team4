<template>
    <h1 class="label">Add your fur friend</h1>
    <pet-form :pet="petObject" v-if="showPetForm"/>
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
      petObject:{
        id: 0,
        name: '',
        breed: '',
        dateOfBirth: '',
        gender: '',
        petSize: '',
        vaccination: false,
        spayNeuter:false,
        energyLevel: "",
        personality: '',
        image: ''
      },
      showPetForm: false
    };
  },
  methods:{
    getPet(){
      petService.getPetById(this.$route.params.id).then(
        (response) => {
          this.petObject = response.data;
          this.showPetForm = true;
        }
      ).catch(
        (error) => {
          console.log(error);
        }
      ).finally(
        () => {
        this.showPetForm = true;
        }
      );
    }
  },
  created(){
    this.getPet();
  }


}
</script>

<style scoped>
.label{
    text-align: center;
    margin-top: 20px;
    font-size: 60px;
    color: hsl(323, 65%, 37%);
    font-family: cursive;
    font-style: oblique;
}
</style>