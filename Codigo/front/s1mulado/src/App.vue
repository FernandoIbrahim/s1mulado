<template>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

  <div class=" flex flex-col h-screen bg-neutral-900">
      <component :is="layoutComponent">
        <router-view/>
      </component>
  </div>

</template>

<script>

import DefaultLayout from '@/layouts/DefaultLayout.vue'

export default {
  name: 'App',
  components: {
    DefaultLayout,
  },
  computed: {
    layoutComponent() {
      return this.$route.meta.layout === 'default' ? DefaultLayout : 'div';
    }
  }
};

</script>

