<template>
  <h1>Add event</h1>
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
        //   id: 0,
        //   name: '',
        //   date: '',
        //   time: '',
        //   duration: '',
        //   location: '',
        //   address: '',
        //   maximumPets: 0,
        //   description: '',
        //   image: ''
        },
        showEventForm: false
        // eventObject: {}
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

<style>

</style>