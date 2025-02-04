<template>
    <div class="flex flex-col w-full items-center">
        <div class="h-30 items-end flex flex-row w-ful justify-center">
            <h1 class="text-lg font-bold text-gray-500 mb-6">Provas anteriores</h1>
        </div>
        <div class=" h-full bg-gray-100 w-80/100 flex items-center flex-col rounded-md">

            <div  v-if="testResults != null" v-for="(testResult, index) in testResults" :key="testResult.id"  class=" flex flex-col w-full items-center pt-10">
                    <TestResultComponent :testResult="testResult"/>
            </div>

        </div>
    </div>
</template>

<script>

import {  getUserTestResultHistory } from "@/services/userService";

import TestResultComponent from "@/components/test/TestResultComponent.vue";

export default {

    data(){
        return{
            testResults: null
        }
    },
    components:{
        TestResultComponent
    },
    async created(){

        try{

            const response = await getUserTestResultHistory();
            this.testResults = response.data;

            console.log(this.testResults);
            
        }catch(error){

            console.log(error);

        }

    }

}

</script>