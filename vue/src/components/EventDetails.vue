<template>
  <div>
    <h1 class="event-title">{{ event.eventTitle }}</h1>
    <p>{{ event.eventLocation }}</p>
    <p>{{ event.eventAddress }}</p>
    <div class="event-time">
        <p>{{ formattedDate }}</p>
        <p>&nbsp;|&nbsp;{{ formattedTime }} </p>
        <p>&nbsp;|&nbsp;{{ event.eventDuration }} mins</p>
    </div>
    <p>{{ event.eventDescription }}</p>
    <img :src="event.eventImage" alt="Event Image">
    
  </div>
</template>

<script>
import eventService from '../services/eventService';
export default {
    data() {
        return {
            event: {},
        };
    },
    created() {
        return eventService.getEventById(this.$route.params.id).then(
            (response) => {
                this.event = response.data;
            }
        );
    },
    methods: {
        getEventById(id) {
            return eventService.getEventById(id);
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

<style>
.event-title {
    font-weight: bold;
    font-size: 1.5em; 
}
.event-time{
    display: flex;
    flex-direction: row;
}
</style>