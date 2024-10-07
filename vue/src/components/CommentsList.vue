<template>
    <div>
        <h3>Comments</h3>
        <ul>
            <li v-for="comment in comments" :key="comment.id">
                {{ comment.commentContent }}
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

</style>