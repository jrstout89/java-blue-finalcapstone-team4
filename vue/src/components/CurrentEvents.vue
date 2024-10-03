<template>
    <div class="events">
        <div class="event" v-for="event in events" v-bind:key="event.id">
            <router-link :to="{ name: 'eventDetails', params: { id: event.id } }">
                <div>
                    <img v-bind:src="event.eventImage" alt="event image" width="300" height="200">
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
        }
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