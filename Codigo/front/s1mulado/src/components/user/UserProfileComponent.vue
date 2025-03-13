<template>
    <div class="w-full  h-40 items-center flex  flex-col mt-10">

        <div class="flex w-full justify-between items-center">
            <h1 class="text-lg font-semibold midnight">Dados Pessoais</h1>
            <SelectMenu v-model="selectedKnowledgeArea" title="Área do conhecimento:" :options="this.options"/>
        </div>


        <div v-if="selectedKnowledgeArea == null">
            <h1 class="text-lg  midnight mt-40">Selecione a área do conhecimento</h1>
        </div>

        <div v-if="selectedKnowledgeArea != null">
            <div class="mt-40">
                <h1 class="text-md  midnight w-full flex justify-center">Últimas Provas</h1>
                <TestResultChart class="mt-5" :knowledgeArea="selectedKnowledgeArea"/>
            </div>
        </div>


    </div>
</template>

<script>
    import TestResultChart from '@/components/charts/TestResultChart.vue'
    import { getUserData }  from '@/services/userService'

    import SelectMenu from '@/components/common/SelectMenu.vue'
    
    export default {
        data(){

            return {
                user: null,
                selectedKnowledgeArea: null,

                options: [
                    { id: 1, name: 'Exatas' },
                    { id: 2, name: 'Natureza' },
                    { id: 3, name: 'Humanas' },
                    { id: 4, name: 'Linguagens'}
                ],

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
            TestResultChart,
            SelectMenu
        },  
        created(){

            this.handleUserData();

        },
    }

</script>
