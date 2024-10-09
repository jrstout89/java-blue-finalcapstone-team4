import axios from 'axios';


const http = axios.create({
    baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
    
    //Get all forums.
    getForums() {
        return http.get('/forums', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

    //Get a specific forum.
    getForumById(forumId) {
        // console.log('Getting comments for forum ID: ${forumId}');
        return http.get(`/forum/${forumId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

    // Get all comments for a specific forum.
    getCommentsForForum(forumId) {
        return http.get(`/forum/${forumId}/comments`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

    // Add a new forum.
    addForum(forum) {
        return http.post('/add-forum', forum, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

    // Update a forum.
    updateForum(forumId, forum) {
        return http.put(`/update-forum/${forumId}`, forum, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

    // Delete a forum.
    deleteForum(forumId) {
        return http.delete(`/forums/${forumId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

    // Add a new comment to a forum.
    addCommentToForum(forumId, comment) {
        return http.post(`/forum/${forumId}/add-comment`, comment, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

    // Update a comment.
    updateComment(commentId, comment) {
        return http.put(`/comments/${commentId}`, comment, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    },

    
    // Delete a comment.
    deleteComment(commentId) {
        return http.delete(`/comments/${commentId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`}});
    }
}