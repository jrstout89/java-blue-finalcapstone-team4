<template>
    <div class="events">
        <div class="event" v-for="event in events" v-bind:key="event.id">
            <div>
                <h3>{{ event.eventTitle }}</h3>
                <p>Location:  {{ event.eventLocation }}</p>
                <p>Date: {{ event.eventDate }}</p>
                <p>Time: {{ event.eventTime }}</p>
                <p>Duration: {{ event.eventDuration}} mins</p>
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
}

</script>
<style>
    .events{
        display: flex;
        flex-direction: row; 
    }
    .event{
        border: 1px solid black;
        margin: 10px;
        padding: 10px;
        width: 300px;
        flex-grow: 1;
        margin-left: 20px;
    }
    .event h3{
        margin: 0;
    }
    .event p{
        margin: 0;
    }

</style>