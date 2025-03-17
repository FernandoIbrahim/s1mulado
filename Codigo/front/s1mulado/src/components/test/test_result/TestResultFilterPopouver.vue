<script>

import SelectMenu from "@/components/common/SelectMenu.vue";

import { useTestResultHistoryFilter } from '@/stores/searchFilter.js';

import {
  Popover,
  PopoverContent,
  PopoverTrigger, 
} from "@/components/ui/popover"

import IconButtonComponent from "@/components/common/IconButtonComponent.vue";
import DataPicker from "@/components/ui/data-picker/DataPicker.vue";

export default{
    name: "TestResultFilterPopouver",
    components: {
        IconButtonComponent,
        Popover,
        PopoverContent,
        PopoverTrigger, 
        DataPicker
    },
    data(){
      return {
        testResultHistoryFilter: useTestResultHistoryFilter(),
        startDate: null,
        endDate: null
      }
    },
    watch: {

      startDate(newStartDate, oldStartDate){
        this.testResultHistoryFilter.setMinDate(newStartDate);
        console.log('alterando data minima');
      },

      endDate(newEndDate, oldEndDate){
        this.testResultHistoryFilter.setMaxDate(newEndDate);
        console.log('alterando data maxima');
      }

    }
}

</script>

<template>
  <Popover>
    <PopoverTrigger>
        <IconButtonComponent content="filter" title="Filtro"/>
    </PopoverTrigger>
    <PopoverContent>
      <p class="text-sm mid-night mt-2">Filtrar por:</p>
      <div class="my-5">
        <DataPicker v-model="startDate" dataPickerName="Data de início" />
      </div>
      <div class="my-5">
        <DataPicker v-model="endDate" dataPickerName="Data de fim" />
      </div>
    </PopoverContent>
  </Popover>
</template>