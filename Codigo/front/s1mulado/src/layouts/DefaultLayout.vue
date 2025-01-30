<template>

    <header>
        <NavbarComponent class="fixed w-full"> </NavbarComponent>
    </header>
    <main class="flex w-full flex-grow justify-center align-middle bg-white body ">
        <RouterView />
    </main>

</template>

<script>

    import NavbarComponent from '@/components/common/NavbarComponent.vue';

    export default {
        name: 'DefaultLayout',
        components: {
            NavbarComponent,
        },
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