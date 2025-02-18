<template>
    <div class="w-full  h-40 items-center flex">
        <div class="w-50 h-30 ">

            <div v-if="user != null">

                <p class="text-gray-800">{{ user.username }}</p>
                <p class="text-gray-800 text-xs">{{ user.email }}</p>
                
            </div>
            <TestResultChart />

        </div>
    </div>
</template>

<script>
    import TestResultChart from '@/components/charts/TestResultChart.vue'
    import { getUserData }  from '@/services/userService'
    
    export default {
        data(){

            return {
                user: null
            }

        }, 
        methods: {

            async handleUserData(){
                try{
                    
                    const response =  await getUserData();
                    this.user =  response.data

                    console.log(this.user);
                    
                }catch(error){
                    console.log(error)
                }
            }

        },
        components: {
            TestResultChart
        },
        created(){

            this.handleUserData();

        }
    }

</script>
