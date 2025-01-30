<script setup>
import { cn } from '@/lib/utils';
import { Circle } from 'lucide-vue-next';
import {
  RadioGroupIndicator,
  RadioGroupItem,
  useForwardProps,
} from 'radix-vue';
import { computed } from 'vue';

const props = defineProps({
  id: { type: String, required: false },
  value: { type: String, required: false },
  disabled: { type: Boolean, required: false },
  required: { type: Boolean, required: false },
  name: { type: String, required: false },
  asChild: { type: Boolean, required: false },
  as: { type: null, required: false },
  class: { type: null, required: false },
});

const delegatedProps = computed(() => {
  const { class: _, ...delegated } = props;

  return delegated;
});

const forwardedProps = useForwardProps(delegatedProps);
</script>

<template>
  <RadioGroupItem
    v-bind="forwardedProps"
    :class="cn(
        'h-4 w-4 rounded-full border border-neutral-400 bg-white', // Torna sempre visível
        'focus:outline-none focus:ring-2 focus:ring-neutral-950 focus:ring-offset-2',
        'disabled:cursor-not-allowed disabled:opacity-50',
        props.class,
      )">
      
    <RadioGroupIndicator class="flex items-center justify-center">
      <Circle class="h-2 w-2 fill-current text-current  " />
    </RadioGroupIndicator>
  </RadioGroupItem>

</template>
