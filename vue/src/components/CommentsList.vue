<template>
    <div>
        <h3>Comments</h3>
        <ul>
            <li v-for="comment in comments" :key="comment.id">
                <strong>{{ comment.username }} replied:</strong> {{ comment.commentContent }}
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'CommentsList',
    props: {
        comments: {
            type: Array,
            required: true
        }
    }
}
</script>

<style>
ul {
    list-style: none;
    padding: 0;
    margin: 0;
    text-align: center;

}

li {
    background-color: #f1f1f1;
    border: 1px solid #ddd;
    border-radius: 12px;
    padding: 10px 15px;
    margin: 10px 0;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    text-align: left;
    max-width: 90%;
    
}
</style>