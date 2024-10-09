<template>
    <form v-on:submit.prevent="submitForm" class="cardForm">
        {{ newEvent }}
        <div class="field">
            <label class="label">Event title</label>
            <div class="control">
                <input class="input" type="text" placeholder="Text input" v-model="newEvent.eventTitle">
            </div>
        </div>

        <div class="field">
            <label class="label">Location</label>
            <div class="control">
                <input class="input is-success" type="text" placeholder="Text input" v-model="newEvent.eventLocation">
                <span class="icon is-small is-left">
                    <i class="fas fa-user"></i>
                </span>
                <span class="icon is-small is-right">
                    <i class="fas fa-check"></i>
                </span>
            </div>
        </div>

        <div class="field">
            <label class="label">Address</label>
            <div class="control">
                <input class="input is-success" type="text" placeholder="Text input" v-model="newEvent.eventAddress">
                <span class="icon is-small is-left">
                    <i class="fas fa-user"></i>
                </span>
                <span class="icon is-small is-right">
                    <i class="fas fa-check"></i>
                </span>
            </div>
        </div>

        <div class="field">
            <label class="label">Maximum pets</label>
            <div class="control">
                <input class="input is-success" type="number" placeholder="Number input" v-model="newEvent.maximumPets">
                <span class="icon is-small is-left">
                    <i class="fas fa-user"></i>
                </span>
                <span class="icon is-small is-right">
                    <i class="fas fa-check"></i>
                </span>
            </div>
        </div>

        <!-- event host should auto populate.  -->

        <div class="field">
            <label class="label">Event Date</label>
            <div class="control">
                <input class="input is-danger" type="date" placeholder="Email input" v-model="newEvent.eventDate">
                <span class="icon is-small is-left">
                 <i class="fas fa-envelope"></i>
                </span>
                <span class="icon is-small is-right">
                 <i class="fas fa-exclamation-triangle"></i>
                </span>
            </div>
        </div>

        <div class="field">
            <label class="label">Event Time</label>
            <div class="control">
                <input class="input is-danger" type="time" placeholder="Email input" v-model="newEvent.eventTime">
                <span class="icon is-small is-left">
                 <i class="fas fa-envelope"></i>
                </span>
                <span class="icon is-small is-right">
                    <i class="fas fa-exclamation-triangle"></i>
                </span>
            </div>
        </div>

        <div class="field">
            <label class="label">Duration</label>
            <div class="control">
                <input class="input is-danger" type="number" placeholder="Email input" v-model="newEvent.eventDuration">
                <span class="icon is-small is-left">
                 <i class="fas fa-envelope"></i>
                </span>
                <span class="icon is-small is-right">
                    <i class="fas fa-exclamation-triangle"></i>
                </span>
            </div>
        </div>

        <div class="field">
            <label class="label">Description</label>
            <div class="control">
                <textarea class="textarea" placeholder="Textarea" v-model="newEvent.eventDescription"></textarea>
            </div>
        </div>
        <div class="file is-primary">
        <label class="file-label">
            <input class="file-input" type="file" name="resume" />
            <span class="file-cta">
            <span class="file-icon">
                <i class="fas fa-upload"></i>
            </span>
            <span class="file-label"> Add a picture! </span>
            </span>
        </label>
        </div>

        <div class="field is-grouped">
        <div class="control">
            <button class="button is-link">Submit</button>
        </div>
        <div class="control">
            <button class="button is-link is-light" v-on:click="cancelForm">Cancel</button>
        </div>
        </div>
        

    </form>
</template>

<script>
import eventService from '../services/eventService';

export default {
    props: {
        Event: {
            type: Object,
            required: true
        }
    },
    data(){
        return{
            newEvent:{
                id: this.Event.id,
                eventTitle: this.Event.eventTitle,
                eventLocation: this.Event.eventLocation,
                eventAddress: this.Event.eventAddress,
                maximumPets: this.Event.maximumPets,
                eventDate: this.Event.eventDate,
                eventTime: this.Event.eventTime,
                eventDuration: this.Event.eventDuration,
                eventDescription: this.Event.eventDescription,
                eventImage: this.Event.eventImage
            },
            
        };
    },
    methods:{
        //submit form
      submitForm(){
            // console.log('THE ID IS... ' + this.newEvent.id);
            if(this.newEvent.id === 0 || this.newEvent.id === undefined){
                eventService.addEvent(this.newEvent).then(
                    (response) => {
                        if(response.status === 201){
                            //redirect to events page
                        this.$router.push( {name: 'user'});
                        }
                    }
                ).catch(
                    (error) => {
                        console.log(error);
                    }
                );
            }else{
                eventService.updateEvent(this.newEvent.id, this.newEvent).then(
                    (response) => {
                        if(response.status === 200){
                            //redirect to pets page
                        this.$router.push( {name: 'user'});
                        }
                    }
                ).catch(
                    (error) => {
                        console.log(error);
                    }
                );
            }
        },
//cancel form
        cancelForm(){
            this.$router.push( {name: 'user'});
        },

    },
    created(){
        this.newEvent = this.Event;
    }
};
</script>

<style>

</style>