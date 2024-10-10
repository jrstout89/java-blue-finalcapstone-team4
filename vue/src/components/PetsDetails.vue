<template>
    <div class="petsDetails">
      <div class="pet-content">
 
        <div class="pets">
            <img v-bind:src="pets.image" alt="pet image" width="300" height="200">
            <h3><strong>{{pets.name}}</strong></h3>
             <p><strong>Breed: </strong>{{pets.breed}}</p>
            <p><strong>Date of Birth: </strong>{{pets.dateOfBirth}}</p>
            <p><strong>Gender: </strong>{{pets.gender}}</p>
            <p><strong>Size: </strong>{{pets.petSize}}</p>
            <p><strong>Vaccination: </strong>{{pets.vaccination}}</p>
            <p><strong>Spay or Neuter: </strong>{{pets.isSpayNeuter}}</p>
            <p><strong>Energy Level: </strong>{{pets.energyLevel}}</p>
            <p><strong>Personality: </strong>
             {{pets.personality}}</p>
            <div class="buttons">
        <button class="button is-success" @click="editPet()" >Edit</button>
        <button class="button is-warning" @click="deletePet()">Delete</button>
        <button class="button is-info" @click="this.$router.push({ name: 'pets', params: { customerId: this.$store.state.user.id } })">Back</button>
      </div> 
        </div>
      </div>
      
      
  <div class="event-content">

     <div v-if="events.length">
      <h1 class="event-title">Registered Event Details</h1>
    
      <div class="event" v-for="event in events" v-bind:key="event.id"> 
        <img :src="event.eventImage" alt="event image" width="300" height="200">
        <h3><strong>{{ event.eventTitle }}</strong></h3>
        <p><strong>Location:</strong>  {{ event.eventLocation }}</p>
        <p><strong>Address:</strong> {{ event.eventAddress }}</p>
        <div class="event-time">
          <!-- <p>{{ formattedDate }}</p>
            <p>&nbsp;|&nbsp;{{ formattedTime }} </p> -->
            <p><strong>Duration:</strong> {{ event.eventDuration }} mins</p>
        </div>
        <button class="button is-warning" @click="removePetFromEvent(event.id, pets.id)">Remove pet from this event</button>
      </div>
    </div>
    <div v-else>
        <h1 class="event-title">No Registered Events</h1>
        <p>
          <router-link to="/playdates"><a>Check all available events </a></router-link>
        </p>
    </div>
  </div>
</div>


</template>

<script>
import petService from '../services/petService'
import eventService from '../services/eventService'
export default {
    data(){
        return{
            pets:{},
            events:[]
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
        eventService.getEventByPetId(this.$route.params.id).then(
            (response) => {
                this.events = response.data;
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
    },
    //remove pet from event
    removePetFromEvent(eventId, petId){
      if (!eventId || !petId) {
        console.error('Event ID or Pet ID is not defined.');
        return;
    }
      if (confirm('Are you sure you want to remove this pet from the event?')) {
        eventService.removePet(eventId, petId).then(
            (response) => {
              console.log('Response:', response);
                if(response.status === 200){
                    console.log('Pet removed from event successfully. Redirecting...');
                    //redirect to pets page
                    // this.$router.push({ name: 'petDetails', params: { id: petId} });
                    location.reload();
                }else{
                  console.error('Failed to remove pet:', response);
                }
            }
        ).catch(
            (error) => {
              console.error('Error occurred while removing pet:', error.response ? error.response.data : error.message);
            }
        );
         }

 
    }
  }
    // computed:{
    //   formattedDate() {
    //         const parts = this.events.eventDate.split('-');
    //         const year = parseInt(parts[0], 10);
    //         const month = parseInt(parts[1], 10) - 1; // months are zero-based in JavaScript
    //         const day = parseInt(parts[2], 10);

    //         const date = new Date(year, month, day);
    //         return `${month + 1}-${day}-${year}`; // add 1 to month to convert it back to 1-based
    //     },
    //     formattedTime() {
    //         const parts = this.events.eventTime.split(':');
    //         let hours = parseInt(parts[0], 10);
    //         const minutes = parseInt(parts[1], 10);

    //         const ampm = hours >= 12 ? 'PM' : 'AM';
    //         hours = hours % 12;
    //         hours = hours ? hours : 12; // the hour '0' should be '12'
            
    //         return `${hours}:${minutes < 10 ? '0' + minutes : minutes} ${ampm}`;
    //     }
    // }

}
</script>

<style>
.petsDetails{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    margin-top: 20px;
    width: auto;
}
.event-content{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    margin-top: 20px;
  
}

.pets{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin-top: 20px;
    background-color: #f5f5f5;
    padding: 20px;
    border-radius: 5px;
    box-shadow: #ccc 2px 2px 2px;
    width: auto;
}
.event{
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin-top: 20px;
    background-color: #f5f5f5;
    padding: 20px;
    border-radius: 5px;
    box-shadow: #ccc 2px 2px 2px;
    width: auto;
}
.event-title {
    font-weight: bold;
    font-size: 1.5em; 
}

</style>