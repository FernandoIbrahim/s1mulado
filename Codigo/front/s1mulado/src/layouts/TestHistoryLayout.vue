<template>
    <div class="flex flex-col w-full items-center">
        <div class="h-30 items-end flex flex-row w-ful justify-center">
            <h1 class="text-lg font-bold text-gray-500 mb-6">Provas anteriores</h1>
        </div>
        <div class=" h-full bg-gray-100 w-80/100 flex items-center flex-col rounded-md">

            <div  v-if="testResults != null" v-for="(testResult, index) in testResults" :key="testResult.id"  class=" flex flex-col w-full items-center pt-10">
                    <TestResultComponent :testResult="testResult"/>
            </div>

            <TestResultPaginationComponent :currentPage="currentPage" :maxPage="totalPages" @pageChange="updatedCurrentPage" class="mt-4"/>

        </div>
    </div>
</template>

<script>

import {  getUserTestResultHistory } from "@/services/userService";

import TestResultComponent from "@/components/test/test_result/TestResultComponent.vue";
import TestResultPaginationComponent from "@/components/test/test_result/TestResultPaginationComponent.vue";

export default {

    data(){
        return{
            totalPages: null,
            testResults: null, 
            currentPage: 1
        }
    },
    components:{
        TestResultComponent,
        TestResultPaginationComponent
    },

    async created(){

        try{

            const response = await getUserTestResultHistory({ page: 0, size: 4, sort: null });
            this.testResults = response.data.content;
            this.totalPages = response.data.totalPages;
            console.log(this.totalPages);
            
        }catch(error){

            console.log(error);

        }

    },
    methods: {

        async updatedCurrentPage(newPage){

            console.log(newPage);
            this.currentPage = newPage - 1
            const response = await getUserTestResultHistory({ page: this.currentPage, size: 4, sort: null });
            this.testResults = response.data.content;

        }   

    }


}

</script>