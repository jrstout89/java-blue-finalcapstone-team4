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
                <button class="button is-success" @click="editEvent()">Edit</button>
                <button class="button is-warning" @click="deleteEvent()">Delete</button>
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
        deleteEvent() {
            if (confirm('Are you sure you want to delete this event?')) {
                console.log(`Attempting to delete event with ID: ${this.events.id}`);
                eventService.deleteEvent(this.events.id).then(
                (response) => {
                    if (response.status === 204) {
                        console.log('Event deleted successfully. Redirecting...');
                        //redirect to events page
                        this.$router.push({ name: 'events', params: { customerId: this.$store.state.user.id } });
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
        editEvent(){
            this.$router.push({name: 'updateEvent', params: {id: this.events.id}});
        }

    },

}
</script>

<style>

</style>