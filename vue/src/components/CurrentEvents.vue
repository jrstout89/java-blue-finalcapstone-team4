<template>
    <div class="header">
        <h1>All Events</h1>
    </div>
    <div class="events">
        
        <div id="side-bar">
            <ul>
                <li>Location</li>
                <li>Date</li>
                <li>
                     Pet
                     <button @click="toggleDropdown" class="down-arrow" aria-expanded="isActive">
                        <i class="fas" :class="isActive ? 'fa-angle-up' : 'fa-angle-down'"></i>
                    </button>
                     <ul v-show="isActive">
                        <li>Breed</li>
                        <li>Energy Level</li>
                        <li>Size</li>
                     </ul>
                 </li>
             </ul>
        </div>

        <div class="event" v-for="event in events" v-bind:key="event.id">
            <div>
                <h3>Location: {{ event.eventLocation }}</h3>
                <p>Date: {{ event.eventDate }}</p>
                <p>Time: {{ event.eventTime }}</p>
                <p>Duration: {{ event.eventDuration }}</p>
                <p>maximum pets:{{ event.maximumPets }}</p>
            </div>
        </div>
    </div>
   
</template>

<script>
import eventService from '../services/eventService';
export default{
    data(){
        return{
            events: [],
            isActive: false,
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
        toggleDropdown() {
            this.isActive = !this.isActive;
        },
        selectItem(item) {
            this.activeItem = item;
        }
    }
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
    #side-bar{
        width: 200px;
        border: 1px solid #ccc;
        padding: 10px;
        background-color: #f0f0f0;
        margin-bottom: 10px;
    }
    #side-bar ul{
        list-style-type: none;
        padding: 0;
    }
    .header{
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
        font-family: cursive;
        border-bottom: black 1px solid;
    }
</style>