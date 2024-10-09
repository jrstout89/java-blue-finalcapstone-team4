import axios from 'axios';


const http = axios.create({
    baseURL: import.meta.env.VITE_REMOTE_API
    });

export default {
    getEvents() {
        return axios.get('/events');
    },
    getEventById(id) {
        return axios.get(`/events/${id}`);
    },
    addEvent(event) {
        return axios.post('/events', event);
    },
    updateEvent(eventId, event) {
        return axios.put(`/update-event`, event);
    },
    deleteEvent(id) {
        return axios.delete(`/events/${id}`);
    },
    getUser() {
        return http.get(`/user`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },
    getUserEvents(customerId) {
        return http.get(`/personal-events/`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

}
