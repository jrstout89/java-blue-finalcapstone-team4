<template>
    <div class="events">
        <div class="event" v-for="event in events" v-bind:key="event.id">
            <div>
                <img v-bind:src="event.eventImage" alt="event image" width="300" height="200">
                <h3><strong>{{ event.eventTitle }}</strong></h3>
                <p><strong>Location:</strong>  {{ event.eventLocation }}</p>
                <p><strong>Address:</strong> {{ event.eventAddress }}</p>
                <p><strong>Date:</strong> {{ event.eventDate }}</p>
                <p><strong>Time:</strong> {{ event.eventTime }}</p>
                <p><strong>Duration:</strong> {{ event.eventDuration}} mins</p>
            </div>
        </div>
    </div>
   
</template>

<script>
import eventService from '../services/eventService';
export default{
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
    methods:{
        
    }
}

</script>
<style>
    .playdates{
        display: flex;
        flex-direction: row; 
    }
    .event{
        border: 1px solid black;
        margin: 10px;
        padding: 10px;
        width: auto;
        flex-grow: 1;
        margin-left: 20px;
    }
    .event h3{
        margin: 0;
        font-family: cursive;
    }
    .event p{
        margin: 0;
    }

</style>