<template>
    <div class="flex flex-col w-full items-center">
        <div class="h-30 items-end flex flex-row w-ful justify-center">
            <h1 class="text-lg font-bold text-midnight mb-6">Provas anteriores</h1>
        </div>
        <div class=" h-full w-80/100 flex items-center flex-col rounded-md">

            <div  v-if="testResults != null" v-for="(testResult, index) in testResults" :key="testResult.id"  class=" flex flex-col w-full items-center pt-10">

                <TestResultComponent :testResult="testResult" @click="handleTestOpen(testResult.id)"/>

            </div>

            <TestResultPaginationComponent :currentPage="currentPage" :maxPage="totalPages" @pageChange="updatedCurrentPage" class="mt-15"/>

        </div>

        <TestResultModal v-if="this.testResultStore.active"/>

    </div>
</template>

<script>

import { getUserTestResultHistory } from "@/services/userService";
import { useCompletedTestResult } from '@/stores/modals.js'

import TestResultComponent from "@/components/test/test_result/TestResultComponent.vue";
import TestResultPaginationComponent from "@/components/test/test_result/TestResultPaginationComponent.vue";

import TestResultModal from "@/components/modal/TestResultModal.vue";

export default {

    data(){
        return{
            totalPages: null,
            testResults: null, 
            currentPage: 1,
            testResultStore: useCompletedTestResult()
        }
    },
    components:{
        TestResultComponent,
        TestResultPaginationComponent,
        TestResultModal
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

        },
        
        async handleTestOpen(testId){
            await this.testResultStore.open(testId);
        }

    }


}

</script>