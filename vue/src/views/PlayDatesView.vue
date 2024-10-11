<template>
    <div class="content-container">
        <div class="left-column">
            <h1 id="header">Current Events</h1>
             <location id="location"></location>
        </div>
        <current-events id="current-events"></current-events>
    </div>
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
}

#header {
    text-align: center;
    font-size: 3em;
    font-family: cursive;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-weight: bold;
    margin-bottom: 20px;
    font-style: oblique;
    color: rgb(21, 165, 21);
}

#location {
    padding: 1em;
    display: flex;
    align-items: center;
    justify-content: center;
}
#current-events {
    display: grid;
    grid-area: "current-events";
    display: flex;
    justify-items: center;
    
}
</style>