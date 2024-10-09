<template>
    <div class="personal-events">
        <h2>Your events</h2>
        <div class="events">
            <router-link to="/events/add-event">
                <button class="button is-info">Add Event</button>
            </router-link>
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
                <br/>
                <button class="button is-success" @click="editEvent(event.id)">Edit</button>
                <button class="button is-warning" @click="deleteEvent(event.id)">Delete</button>
            </div>
            <router-link to="/events/add-event">
                <button class="button is-info">Add Event</button>
            </router-link>
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
    methods: {
        deleteEvent(eventId) {
            if (confirm('Are you sure you want to delete this event?')) {
                console.log(`Attempting to delete event with ID: ${eventId}`);
                eventService.deleteEvent(eventId).then(
                (response) => {
                    if (response.status === 204) {
                        console.log('Event deleted successfully. Redirecting...');
                        //stay at same page
                        this.events = this.events.filter(event => event.id !== eventId);
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
        formattedDate() {
            const parts = this.events.eventDate.split('-');
            const year = parseInt(parts[0], 10);
            const month = parseInt(parts[1], 10) - 1; // months are zero-based in JavaScript
            const day = parseInt(parts[2], 10);

            const date = new Date(year, month, day);
            return `${month + 1}-${day}-${year}`; // add 1 to month to convert it back to 1-based
        },
        formattedTime() {
            const parts = this.events.eventTime.split(':');
            let hours = parseInt(parts[0], 10);
            const minutes = parseInt(parts[1], 10);

            const ampm = hours >= 12 ? 'PM' : 'AM';
            hours = hours % 12;
            hours = hours ? hours : 12; // the hour '0' should be '12'
            
            return `${hours}:${minutes < 10 ? '0' + minutes : minutes} ${ampm}`;
        }
    }

}
</script>

<style>

</style>