<template>
    <div class="flex flex-col w-full items-center mt-15">

        <div class="h-16 flex justify-between items-center w-full px-6 bg-white">
            <h1 class="text-lg font-bold text-midnight">Provas anteriores</h1>

            <div class="flex items-center gap-x-4">
                <SelectMenu v-model="selectedArea" title="Área do conhecimento:" :options="options"/>
                <IconButtonComponent content="filter" title="Filtro"/>
            </div>
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
import { getKnowledgeArea } from "@/services/knowledgeAreaService"
import { getUserTestResultHistory } from "@/services/userService";

import { useCompletedTestResult } from '@/stores/modals.js'
import { useTestResultHistoryFilter } from '@/stores/searchFilter.js';

import TestResultComponent from "@/components/test/test_result/TestResultComponent.vue";
import TestResultPaginationComponent from "@/components/test/test_result/TestResultPaginationComponent.vue";
import SelectMenu from "@/components/common/SelectMenu.vue";
import IconButtonComponent from "@/components/common/IconButtonComponent.vue";

import TestResultModal from "@/components/modal/TestResultModal.vue";



export default {

    data(){
        return{

            totalPages: null,
            testResults: null, 
            currentPage: 1,
            testResultStore: useCompletedTestResult(),
            testResultHistoryFilter: useTestResultHistoryFilter(),
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
        SelectMenu,
        IconButtonComponent
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

            const response = await getUserTestResultHistory(
                { 
                    page: page, 
                    size: 4, 
                    sort: null, 
                    knowledgeArea: getKnowledgeArea(this.testResultHistoryFilter.knowledgeArea.id), 
                    minDate: this.testResultHistoryFilter.minDate, 
                    maxDate: this.testResultHistoryFilter.maxDate
                }
            );

            this.totalPages = response.data.totalPages;
            this.testResults = response.data.content;
        },

        async updatedCurrentPage(newPage){

            this.currentPage = newPage - 1
            this.handleTestHistory(this.currentPage);

        },


    },
    watch: {

        async selectedArea(newSelectedArea, oldSelectedArea){   
            this.currentPage = 0;
            await this.testResultHistoryFilter.setKnowledgeArea(newSelectedArea)
            await this.handleTestHistory(0);

        }
        
    }


}

</script>