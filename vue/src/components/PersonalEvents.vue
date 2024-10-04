<template>
    <div class="personal-events">
        <h2>Your events</h2>
        <div class="events">
            <div v-for="event in events" :key="event.id" class="event">
                <h3>{{ event.eventTitle }}</h3>
                <p><strong>Location: </strong> {{ event.eventLocation }}</p>
                <p><strong>Address: </strong> {{ event.eventAddress }}</p>
                <p> Maximum Pets: {{ event.maximumPets }}</p>
                <p> Event Host: {{ event.eventHost }}</p>
                <p> Event Date: {{ event.eventDate }}</p>
                <p> Event Time: {{ event.eventTime }}</p>
                <p> Event Duration: {{ event.eventDuration }}</p>
                <p> Event Description: {{ event.eventDescription }}</p>
                <img :src="event.eventImage" alt="Event Image">
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
        return eventService.getEvents().then(
            (response) => {
                this.events = response.data;
            }
        );
    },

}
</script>

<style>

</style>