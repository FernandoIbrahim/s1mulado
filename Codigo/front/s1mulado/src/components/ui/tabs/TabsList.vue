<script setup>
import { cn } from '@/lib/utils';
import { TabsList } from 'radix-vue';
import { computed } from 'vue';

const props = defineProps({
  loop: { type: Boolean, required: false },
  asChild: { type: Boolean, required: false },
  as: { type: null, required: false },
  class: { type: null, required: false },
});

const delegatedProps = computed(() => {
  const { class: _, ...delegated } = props;

  return delegated;
});
</script>

<template>
  <TabsList
    v-bind="delegatedProps"
    :class="
      cn(
        'inline-flex items-center justify-center rounded-md bg-neutral-100 p-1 text-gray-200',
        props.class,
      )
    "
  >
    <slot />
  </TabsList>
</template>
