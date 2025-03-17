<script setup>
import { ref, watch } from 'vue'
import { cn } from '@/lib/utils'
import { Button } from '@/components/ui/button'
import { Calendar } from '@/components/ui/calendar'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover'
import { CalendarIcon } from 'lucide-vue-next'
import { DateFormatter, getLocalTimeZone, CalendarDate, parseDate } from '@internationalized/date'


const df = new DateFormatter('en-US', { dateStyle: 'long' })

const props = defineProps({
  dataPickerName: {
    type: String,
    required: true,
  },
  modelValue: {
    type: [Date, null],
    required: true
  }

})


const value = ref(props.modelValue ? parseDate(props.modelValue.toISOString().split('T')[0]) : null);

const emit = defineEmits(['update:modelValue'])
  
watch(value, (newVal) => {
  emit('update:modelValue', newVal ? newVal.toDate(getLocalTimeZone()) : null)
})

</script>

<template>
  <Popover>
    <PopoverTrigger as-child>
      <Button
        variant="outline"
        :class="cn(
          'w-[280px] justify-start text-left font-normal',
          !value && 'text-muted-foreground',
        )"
      >
        <CalendarIcon class="mr-2 h-4 w-4" />
        {{ props.modelValue ? df.format(value.toDate(getLocalTimeZone())) : dataPickerName }}
      </Button>
    </PopoverTrigger>
    <PopoverContent class="w-auto p-0">
      <Calendar v-model="value" initial-focus />
    </PopoverContent>
  </Popover>
</template>