import axios from 'axios';


const http = axios.create({
    baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
    getForum() {
        return http.get('/forum', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    }
}