<template>
    <div id="entire-page">
    <div id="content">
        <h1 class="event-title">{{ event.eventTitle }}</h1>
        <p>{{ event.eventLocation }}</p>
        <p>{{ event.eventAddress }}</p>
        <div class="event-time">
            <p>{{ formattedDate }}</p>
            <p>&nbsp;|&nbsp;{{ formattedTime }} </p>
            <p>&nbsp;|&nbsp;{{ event.eventDuration }} mins</p>
        </div>
        <p>{{ event.eventDescription }}</p>
        <figure class="image is-400x350">
        <img :src="event.eventImage" alt="Event Image"> 
    </figure>
    </div>
    <!-- show all the pets of event -->
    <div id="pet_content">
        <h1 class="pet-title">Pets attending this event:</h1>
        <div class="pet-card" v-for="pet in pets" v-bind:key="pet.id">
            <figure class="image is-400x350">
            <img :src="pet.image" alt="pet image">
            </figure>
            <h3><strong>{{ pet.name }}</strong></h3>
            <p>{{ pet.personality }}</p>
        </div>

    </div>
    </div>
</template>

<script>
import eventService from '../services/eventService';
import petService from '../services/petService';

export default {
    data() {
        return {
            event: {},
            pets: []
        };
    },
    props: {
        
    },
    created() {
        // return eventService.getEventById(this.$route.params.id).then(
        //     (response) => {
        //         this.event = response.data;
        //     }
        // );
        this.loadEventAndPets();
    },
    methods: {
        // getEventById(id) {
        //     return eventService.getEventById(id);
        // },
        // loadPets() {
        //     petService.getPetsByPlaydateId(this.event.id).then(
        //         (response) => {
        //             this.pets = response.data;
        //         }
        //     ).catch(
        //         (error) => {
        //             console.log(error);
        //         }
        //     );
            
        // }
        loadEventAndPets() {
            const eventId = this.$route.params.id;
            eventService.getEventById(eventId)
                .then((response) => {
                    this.event = response.data;
                    console.log("Event ID for pets:", this.event.id);
                    return petService.getPetsByPlaydateId(this.event.id);
                })
                .then((response) => {
                    this.pets = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
            }
    },
    computed: {
        formattedDate() {
            const parts = this.event.eventDate.split('-');
            const year = parseInt(parts[0], 10);
            const month = parseInt(parts[1], 10) - 1; // months are zero-based in JavaScript
            const day = parseInt(parts[2], 10);

            const date = new Date(year, month, day);
            return `${month + 1}-${day}-${year}`; // add 1 to month to convert it back to 1-based
        },
        formattedTime() {
            const parts = this.event.eventTime.split(':');
            let hours = parseInt(parts[0], 10);
            const minutes = parseInt(parts[1], 10);

            const ampm = hours >= 12 ? 'PM' : 'AM';
            hours = hours % 12;
            hours = hours ? hours : 12; // the hour '0' should be '12'
            
            return `${hours}:${minutes < 10 ? '0' + minutes : minutes} ${ampm}`;
        }
    }
};
</script>

<style scoped>
#content {
    grid-area: content;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px;
    border: 1px solid black;
    padding: 20px;
    border-radius: 10px;
    background-color: white;
    box-shadow: 5px 5px 5px #888888;
    width: 50%;
    color: black;
}
#pet_content {
    grid-area: pet_content;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px;
    border: 1px solid black;
    padding: 20px;
    border-radius: 10px;
    background-color: white;
    box-shadow: 5px 5px 5px #888888;
    width: 50%;
    color: black;
}
.event-title {
    font-weight: bold;
    font-size: 1.5em; 
}
.pet-title{
    font-weight: bold;
    font-size: 1.5em;
}
.event-time{
    display: flex;
    flex-direction: row;
}
.pet-card {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px;
    border: 1px solid black;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 5px 5px 5px #888888;
    width: 50%;
    color: black;
}
#entire-page {
    display: flex;
    flex-direction: row;
    justify-content: center;

}
</style>