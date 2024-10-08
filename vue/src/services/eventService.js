import axios from 'axios';


const http = axios.create({
    baseURL: import.meta.env.VITE_REMOTE_API
    });

export default {
    getEvents() {
        return http.get('/events');
    },
    getEventById(id) {
        return http.get(`/events/${id}`);
    },
    addEvent(event) {
        return http.post('/events', event);
    },
    updateEvent(event) {
        return http.put(`/update-event`, event);
    },
    deleteEvent(id) {
        return http.delete(`/events/${id}`);
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
