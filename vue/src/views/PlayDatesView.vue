<template>
    <div class="content-container">
        <div class="left-column">
             <!-- Filter will go here -->
             <!-- <p id="filter">Filter</p> -->
             <location id="location"></location>
        </div>
        <h1 id="header">Current Events</h1>
        <current-events id="current-events"></current-events>
    </div>
    <!-- </div> -->

</template>

<script>
import CurrentEvents from '../components/CurrentEvents.vue'
import location from '../components/Location.vue';

export default {
    components:{
        CurrentEvents,
        location
    },

}
</script>

<style scoped>
.content-container {
    display: flex;
    flex-direction: column;
    background-color: rgb(7, 250, 7);
}

.left-column {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-areas: 
    "location"
    "current-events";
}
#header {
    text-align: center;
    font-size: 3em;
    font-family: cursive;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-weight: bold;
    text-decoration: underline;
    margin-bottom: 20px;
}

#location {
    width: 40%;
    float: left;
    height: 100vh;
    padding: 1em;
    box-sizing: border-box;
    display: flex;
    margin: 30px;
    align-items: flex-end;
    grid-area: "location";
}
#current-events {
    display: grid;
    grid-area: "current-events";
    display: flex;
    justify-items: center;
    
}
</style>