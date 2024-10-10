<template>
    <div class="events">
        <div class="event" v-for="event in events" v-bind:key="event.id">
            <router-link :to="{ name: 'eventDetails', params: { id: event.id } }">
                <div id="content">
                    <img id="image" v-bind:src="event.eventImage" alt="event image" width="300" height="200">
                    <h3><strong>{{ event.eventTitle }}</strong></h3>
                    <p><strong>Location:</strong>  {{ event.eventLocation }}</p>
                    <p><strong>Address:</strong> {{ event.eventAddress }}</p>
                    <div class="event-time">
                        <p><strong>Date:</strong> {{ formattedDate(event.eventDate) }} &nbsp;|&nbsp;</p>
                        <p><strong>Time:</strong> {{ formattedTime(event.eventTime) }} &nbsp;|&nbsp;</p>
                        <p><strong>Duration:</strong> {{ event.eventDuration }} mins</p>
                    </div>
                </div>
            </router-link>
            <div>
               
                     <div v-if="userLoggedIn">
                        <button class="button is-info" @click="togglePetSelection()">Register your pet!</button>
                    <div v-if="showPetSelection">
                        <h3>Select your pet:</h3>
                            <ul class="pet-selection">
                                <li v-for="pet in event.petCandidates" v-bind:key="pet.id" @click="selectPet($event, pet)" class="pet-item">
                                    <img v-bind:src="pet.image" alt="pet image" width="100" height="100"/>
                                   <p class="pet-name"> {{ pet.name }}</p>
                                   <span v-if="pet.isSelected" class="checkmark">✔</span>
                                
                                </li>
                            </ul>
                             
                             <div v-if="selectPet">
                                <span role="button" class="button is-success" @click="confirmSelection(event.id, selectedPet.id )">Confirm</span>
                             </div>
                             <div v-else>
                                <p>Please log in to register your pet.</p>
                                <button @click="redirectToLogin">Log In</button>
                            </div>
                    </div>
                    </div>
                        
            </div>
                    <div v-if="!userLoggedIn">
                        <button class="button is-info" @click="togglePetSelection">Login to register the event!</button>
                    </div>


             </div>
    </div>
   
</template>

<script>
import eventService from '../services/eventService';
import petService from '../services/petService';
export default{
    computed: {

        userLoggedIn() {
                return !(this.$store.state.token == undefined || this.$store.state.token == "") ? true : false ;
        }

         
    },
    data(){
        return{
            events: [],
            pets: [],
            showPetSelection: false,
            selectedPet: null,
            isSelected: false,
            isLoggedIn: false
        }
    },
    created(){
        return eventService.getEvents().then(
            (response) => {
                this.events = response.data;
            }
        ),
        //get all pets
        this.loadPets();
        //check if user is logged in
        // this.isLoggedInStatus();

    },
    methods:{
        //get all pets
        loadPets(){
        petService.getPetByUser().then(
            (response) => {
                this.pets = response.data;
            }
        ).catch(
            (error) => {
                console.log(error);
            }
        );
        },

        formattedDate(eventDate) {
            const parts = eventDate.split('-');
            const year = parseInt(parts[0], 10);
            const month = parseInt(parts[1], 10) - 1; // months are zero-based in JavaScript
            const day = parseInt(parts[2], 10);

            const date = new Date(year, month, day);
            return `${month + 1}-${day}-${year}`; // add 1 to month to convert it back to 1-based
    },
        formattedTime(eventTime) {
            const parts = eventTime.split(':');
            let hours = parseInt(parts[0], 10);
            const minutes = parseInt(parts[1], 10);

            const ampm = hours >= 12 ? 'PM' : 'AM';
            hours = hours % 12;
            hours = hours ? hours : 12; // the hour '0' should be '12'
      
            return `${hours}:${minutes < 10 ? '0' + minutes : minutes} ${ampm}`;
        },
        
        //to show pet selection
        togglePetSelection(){
            this.showPetSelection = !this.showPetSelection;



        },
        //to select pet
        selectPet(event, pet){
            this.selectedPet = pet;
            pet.isSelected = !pet.isSelected;

            console.log(event.target)
        },
        //to confirm selection
        confirmSelection(eventId, petId){
        
            petService.registerEvent(eventId, petId).then(
                (response) => {
                    this.$router.push({name: 'eventDetails', params: {id: eventId}});
                }
            )
           
        },
        //redirect to login
        redirectToLogin(){
            this.$router.push({name: 'login'});
        },
        //check if user is logged in
        checkLoginStatus(){
           this.isLoggedIn = !!localStorage.getItem('token');
        }


    },
}

</script>
<style scoped>
    .events{
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
    }
    #content{
        color: black;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        border: 1px solid black;
        margin: 10px;
        padding: 10px;
        width: auto;
        flex-grow: 1;
    }
    .image{
        width: 300px;
        height: 200px;
    }
    .playdates{
        display: flex;
        flex-direction: row; 
    }
    .event h3{
        margin: 0;
        font-family: cursive;
    }
    .event p{
        margin: 0;
    }
    .pet-selection{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        list-style-type: none;
        padding: 0;
    }
    .pet-item {
    position: relative;
    cursor: pointer;
    }
    .checkmark {
    position: absolute;
    top: 5px;
    color: green; 
    font-size: 20px; 
    right: 10px;
}
   

</style>