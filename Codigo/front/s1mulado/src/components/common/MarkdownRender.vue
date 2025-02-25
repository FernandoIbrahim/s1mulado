<script>
import { marked } from "marked";

export default {
  props: {
    content: {
      type: String,
      required: true
    }
  },
  computed: {
    renderedMarkdown() {
      return marked(this.content);
    }
  }
};
</script>

<template>
  <div v-html="renderedMarkdown"></div>
</template>