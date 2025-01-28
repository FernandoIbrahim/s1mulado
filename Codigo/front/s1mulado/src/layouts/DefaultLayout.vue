<template>

    <main class="flex w-full flex-grow justify-center align-middle bg-white body ">
        <TestComponent></TestComponent>
    </main>

</template>

<script>

    import TestComponent from '@/components/test/TestComponent.vue'

    export default {
        name: 'DefaultLayout',

        components: {
            TestComponent
        }

    }

</script>

<style>

.body {

    box-sizing: border-box;
    padding: 10vh 10vw;

}

@media  screen and (min-width: 600px){

    .body {
      padding: 10vh 10vw
    }

}

@media  screen and (min-width: 840px){

    .body {
      padding: 10vh 20vw;
    }

}



</style>