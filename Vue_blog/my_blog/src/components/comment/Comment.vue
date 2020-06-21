<template>
  <div class="ui bottom attached segment m-bg">
    <comment-area></comment-area>
    <reply-form></reply-form>
  </div>
</template>

<script>
import CommentArea from "./CommentArea";
import ReplyForm from "./ReplyForm";

export default {
    name: 'Comment',
    components: {
        CommentArea,
        ReplyForm
    }
};
</script>

<style>
</style>