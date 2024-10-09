<template>
    <div>
        <h3 class="comments-bubble">Comments</h3>
        <ul class="comments-list">
            <li class="comments" v-for="comment in comments" :key="comment.id">
                <div class="comment-content">
                    <strong>&nbsp;&nbsp;{{ comment.username }} replied: </strong>
                    <div class="comment-text"> {{ comment.commentContent }} </div>
                </div>
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

<style scoped>
.comments-list {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.comments {
    list-style: none;
    padding: 0;
    margin: 0;
    text-align: left;
}

.comments-bubble {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
}

li {
    background-color: #f1f1f1;
    border: 1px solid #ddd;
    border-radius: 12px;
    padding: 10px 15px;
    margin: 10px 0;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    align-self: center;
    display: flex;
    flex-direction: column;
    width: 95%;
}

strong {
    margin-right: 5px;
    width: auto;
    flex-shrink: 0;
    white-space: nowrap;
}

.comment-text {
    flex-grow: 1;
    word-wrap: break-word;
    padding-left: 10px;
    padding-right: 10px;
    line-height: 1.5;
    white-space: pre-wrap;
    margin-left: 5px;
}
</style>