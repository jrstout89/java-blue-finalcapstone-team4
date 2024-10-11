<template>
    <div class="personal-events">
        <h1><span>YOUR EVENTS:</span></h1>
        <div class="events">
            <div v-for="event in events" :key="event.id" class="event">
            <nav id="button">
                <button class="button is-success" @click="editEvent(event.id)">Edit</button>
                &nbsp; &nbsp;
                <button class="button is-warning" @click="deleteEvent(event.id)">Delete</button>
            </nav>
                <br>
                <h2 id="header"> <router-link v-bind:to="{ name: 'eventDetails', params: { id: event.id } }">
                {{ event.eventTitle }} </router-link> </h2>
                <p><strong>Location: &nbsp;&nbsp; </strong> {{ event.eventLocation }}</p>
                <p><strong>Address: &nbsp;&nbsp; </strong> {{ event.eventAddress }}</p>
                <p> <strong> Maximum Pets: &nbsp;&nbsp; </strong> {{ event.maximumPets }} </p>
                <p> <strong> Event Host: &nbsp;&nbsp; </strong> {{ event.eventHost }} </p>
                <p> <strong> Event Date: &nbsp;&nbsp; </strong> {{ event.eventDate }} </p>
                <p>  <strong> Event Time: &nbsp;&nbsp; </strong> {{ event.eventTime }} </p>
                <p> <strong> Event Duration: &nbsp;&nbsp; </strong> {{ event.eventDuration }} </p>
                <figure class="image is-square">
                <img :src="event.eventImage" alt="Event Image" class="img">
                </figure>
                <br/>
            <nav id="button">
                <button class="button is-success" @click="editEvent(event.id)">Edit</button>
                &nbsp; &nbsp;
                <button class="button is-warning" @click="deleteEvent(event.id)">Delete</button>
            </nav>
            <br id="line">
            </div>
        </div>  
    </div>
</template>

<script>
import eventService from '../services/eventService';

export default {
    data(){
        return{
            events: []
        }
    },
    created(){
        eventService.getUserEvents().then(
            (response) => {
                console.log(response);
                this.events = response.data;
            }
        );
    },
    methods: {
        deleteEvent(eventId) {
            if (confirm('Are you sure you want to delete this event?')) {
                console.log(`Attempting to delete event with ID: ${eventId}`);
                eventService.deleteEvent(eventId).then(
                (response) => {
                    if (response.status === 200 || response.status === 204) {
                        console.log('Event deleted successfully. Redirecting...');
                        //stay at same page
                        this.events = this.events.filter(event => event.id !== eventId);
                        // reload the page from cache, put true to reload from server
                        this.$router.push({ name: 'users' });
                    }
                }
                
                ).catch(
                (error) => {
                    console.log(error);
                }
                );
            }
        },
        //edit Event
        editEvent(eventId){
            this.$router.push({name: 'updateEvent', params: {id: eventId}});
        }
    },
    computed: {
        
    }

}
</script>

<style scoped>
.events {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 20px;
    border: 1px solid black;
    padding: 20px;
    border-radius: 10px;
    background-color: white;
    box-shadow: 5px 5px 5px #888888;
    width: 50%;
    height: 50%;
    margin-left: 25%;
    border-radius: 10%;
    color: black;
}
#header {
    font-size: 1.5em;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    font-style: oblique;
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
}

#button{
    display: flex;
    justify-content: center;
    align-items: center;
}
p {
    font-size: 1em;
    display: flex;
    justify-content: center;
    align-items: center;
    font-style: italic;
}
h1 {
    font-size: 2.0em;
    font-family: cursive;
    font-style: oblique;
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    color: hotpink;
    border-radius: 10px;
    height: 50px;
    text-shadow: 50px 50px 50px black;
}

span {
    background-color: white;
}

strong {
    font-size: 1.2em;
    color: rgb(135, 200, 39);
}
</style>