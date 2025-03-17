<template>
    <div class="flex flex-col w-full items-center mt-15">

        <div class="h-16 flex justify-between items-center w-full px-6 bg-white">
            <h1 class="text-lg font-bold text-midnight">Provas anteriores</h1>

            <div class="flex items-center gap-x-4">
                <SelectMenu v-model="selectedArea" title="Área do conhecimento:" :options="options"/>
                <TestResultFilterPopouver/>
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
import TestResultFilterPopouver from '@/components/test/test_result/TestResultFilterPopouver.vue';

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
            selectedArea: { id: 2, name: 'Natureza' },
            minDate: null,
            maxDate: null,

        }
    },
    components:{
        TestResultComponent,
        TestResultPaginationComponent,
        TestResultModal,
        SelectMenu,
        TestResultFilterPopouver
    },

    async created(){

        try{

            await this.handleTestHistory(0);

        }catch(error){

            console.log(error);

        }

    },
    methods: {

        formatDateToCustomISO(dateString) {

          if(isNaN(dateString)){
            console.log("NULL")
            return null
          }

          const date = new Date(dateString);

          if (isNaN(date)) {
            throw new Error('Data inválida.');
          }
      
          const year = date.getFullYear();
          const month = String(date.getMonth() + 1).padStart(2, '0'); // Janeiro = 1
          const day = String(date.getDate()).padStart(2, '0');
          const hours = String(date.getHours()).padStart(2, '0');
          const minutes = String(date.getMinutes()).padStart(2, '0');
          const seconds = String(date.getSeconds()).padStart(2, '0');
      
          console.log(`${year}-${month}-${day}T${hours}:${minutes}:${seconds}`)
          return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;

        },

        async handleTestOpen(testId){

            await this.testResultStore.open(testId);
            
        },

        async handleTestHistory(page){

            const minDate = this.testResultHistoryFilter.minDate 
              ? this.formatDateToCustomISO(this.testResultHistoryFilter.minDate)
              : null;

            const maxDate = this.testResultHistoryFilter.maxDate 
              ? this.formatDateToCustomISO(this.testResultHistoryFilter.maxDate)
              : null;

            const response = await getUserTestResultHistory({
                page: page,
                size: 4,
                sort: null,
                knowledgeArea: getKnowledgeArea(this.testResultHistoryFilter.knowledgeArea?.id),
                minDate: minDate,
                maxDate: maxDate
            });

            this.totalPages = response.data.totalPages;
            this.testResults = response.data.content;
            console.log(this.testResults);
            
        },

        async updatedCurrentPage(newPage){

            this.currentPage = newPage - 1
            this.handleTestHistory(this.currentPage);

        },


    },
    watch: {

        async selectedArea(newSelectedArea, oldSelectedArea){   
            this.currentPage = 0;
            await this.testResultHistoryFilter.setKnowledgeArea(newSelectedArea);
            await this.handleTestHistory(0);
        },

        'testResultHistoryFilter.minDate': {
          async handler(newMinDate) {
            console.log('MIN DATE ATUALIZADA:', newMinDate);
            await this.handleTestHistory(0);
          },
          deep: true
        },

        'testResultHistoryFilter.maxDate': {
            async handler(newMaxDate) {
            console.log('MAX DATE ATUALIZADA:', newMaxDate);
            await this.handleTestHistory(0);
          },
          deep: true
        }

        
        
    }


}

</script>