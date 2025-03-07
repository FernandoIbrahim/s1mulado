<template>
    <div class="flex flex-col w-full items-center">
        <div class="h-30  flex flex-row w-ful justify-between items-center w-80/100">
            
            <h1 class="text-lg font-bold text-midnight flex">Provas anteriores</h1>

            <SelectMenu v-model="selectedArea" title="Área do conhecimento:" :options="this.options"/>

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
import SelectMenu from "@/components/common/SelectMenu.vue";

import TestResultModal from "@/components/modal/TestResultModal.vue";

import { getKnowledgeArea } from "@/services/knowledgeAreaService"

export default {

    data(){
        return{

            totalPages: null,
            testResults: null, 
            currentPage: 1,
            testResultStore: useCompletedTestResult(),
            options: [
                { id: 1, name: 'Exatas' },
                { id: 2, name: 'Natureza' },
                { id: 3, name: 'Humanas' },
                { id: 4, name: 'Linguagens'}
            ],
            selectedArea: { id: 2, name: 'Natureza' }

        }
    },
    components:{
        TestResultComponent,
        TestResultPaginationComponent,
        TestResultModal,
        SelectMenu
    },

    async created(){

        try{

            await this.handleTestHistory(0);

        }catch(error){

            console.log(error);

        }

    },
    methods: {

        
        async handleTestOpen(testId){

            await this.testResultStore.open(testId);
            
        },

        async handleTestHistory(page){

            const response = await getUserTestResultHistory({ page: page, size: 4, sort: null, knowledgeArea: getKnowledgeArea(this.selectedArea.id)});
            this.testResults = response.data.content;
            this.totalPages = response.data.totalPages;
            console.log(this.totalPages);

        },

        async updatedCurrentPage(newPage){

            this.currentPage = newPage - 1
            this.handleTestHistory(this.currentPage);

        },


    },
    watch: {

        async selectedArea(oldSelectedArea, newSelectedArea){
            
            this.currentPage = 0;
            await this.handleTestHistory(0);

        }
        
    }


}

</script>