import axios from 'axios';


const http = axios.create({
    baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
    getPets() {
        return http.get('/pets', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },
    getPetByUser() {
        return http.get('/pets/user', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
            },
    getPetById(id) {
        return http.get(`/pet/${id}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },
    addPet(pet) {
        return http.post('/add-pet', pet, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },
    updatePet(petId, pet) {
       console.log(pet.id);
        return http.put(`/update-pet/${petId}`, pet, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },
    deletePet(id) {
        return http.delete(`/pets/${id}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },
    getAllPetsByCustomerId(customerId) {
        console.log("Fetching pets for customer ID:", customerId);
        return http.get(`/by-customer/${customerId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        });
    },
    registerEvent(event) {
        return http.post('/register-playdate', event, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },
    getPetsByPlaydateId(playdateId) {
        return http.get('/${playdateId}/pet', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
            },

    
}