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
import ButtonComponent from "@/components/common/ButtonComponent.vue";

export default{
    name: "TestResultFilterPopouver",
    components: {
        IconButtonComponent,
        Popover,
        PopoverContent,
        PopoverTrigger, 
        DataPicker,
        ButtonComponent
    },
    data(){
      return {
        testResultHistoryFilter: useTestResultHistoryFilter()
      }
    },
    computed: {
      startDate: {
        get() {
          if(!this.testResultHistoryFilter.minDate) return null
          return this.testResultHistoryFilter.minDate
        },
        set(value) {
          this.testResultHistoryFilter.setMinDate(value)
        }
      },
      endDate: {
        get() {
          if(!this.testResultHistoryFilter.maxDate) 
            return null
          return this.testResultHistoryFilter.maxDate
        },
        set(value) {
          this.testResultHistoryFilter.setMaxDate(value)
        }
      }
    },
    methods: {

      cleanFiltersHanddler(){
        this.testResultHistoryFilter.setMaxDate(null);
        this.testResultHistoryFilter.setMinDate(null);
        this.$forceUpdate();
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
      <div class="my-5">
        <ButtonComponent padding-x="5" padding-y="1" :onclick="cleanFiltersHanddler">Limpar Filtros</ButtonComponent>
      </div>
    </PopoverContent>
  </Popover>
</template>