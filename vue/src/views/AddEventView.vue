<template>
  <h1 id="header">Add New Event</h1>
  <event-form :Event="eventObject" v-if="showEventForm"/>
</template>

<script>
import EventForm from '../components/EventForm.vue';
import eventService from '../services/eventService';

export default {
    components: {
        EventForm
    },
    data() {
        return {
        eventObject:{
            id: 0,
            eventTitle: '',
            eventLocation: '',
            eventAddress: '',
            maximumPets: 0,
            eventDate: "",
            eventTime: "",
            eventDuration: "",
            eventDescription: "",
            eventImage: ""
        },
        showEventForm: false
        };
    },
    methods:{
        getEvent(){
            eventService.getEventById(this.$route.params.id).then(
                (response) => {
                this.eventObject = response.data;
                }
            ).catch(
                (error) => {
                    console.log(error);
                }
            ).finally(
                () => {
                    this.showEventForm = true;
                }
            );
        }
    },
    created(){
        this.getEvent();
    }
    
}
</script>

<style scoped>
#header{
    text-align: center;
    margin-top: 20px;
    font-size: 60px;
    color: hsl(323, 65%, 37%);
    font-family: cursive;
    font-style: oblique;
}
</style>