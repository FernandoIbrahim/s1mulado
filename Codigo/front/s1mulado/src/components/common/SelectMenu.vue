<template>
  <Listbox as="div" v-model="selected" class="w-40">
    <ListboxLabel class="block text-xs font-medium text-midnight pl-1">{{ title }}</ListboxLabel>
    <div class="relative mt-1">
      <ListboxButton class="grid w-full cursor-default grid-cols-1 rounded-md bg-white py-1 pr-1 pl-3 text-left text-midnight outline-1 -outline-offset-1 outline-gray-300 focus:outline-2 focus:-outline-offset-2 focus:outline-zinc-900 text-sm">
        <span class="col-start-1 row-start-1 flex items-center gap-3 pr-6">
          <span class="block truncate">{{ selected?.name }}</span>
        </span>
      </ListboxButton>

      <transition leave-active-class="transition ease-in duration-100" leave-from-class="opacity-100" leave-to-class="opacity-0" enter-active-class="transition ease-out duration-300" enter-from-class="opacity-0 -translate-y-2" enter-to-class="opacity-100 translate-y-0">
        <ListboxOptions class="absolute z-10 mt-1 max-h-56 w-full overflow-auto rounded-md bg-white py-1 text-base ring-1 shadow-lg ring-black/5 focus:outline-hidden text-sm">
          <ListboxOption as="template" v-for="option in options" :key="option.id" :value="option" v-slot="{ active, selected }">
            <li :class="[active ? 'bg-midnight text-white outline-hidden' : 'text-midnight', 'relative cursor-default py-2 pr-5 pl-0 select-none rounded-sm']">
              <div class="flex items-center">
                <span :class="[selected ? 'font-semibold' : 'font-normal', 'ml-3 block truncate']">{{ option.name }}</span>
              </div>

              <span v-if="selected" :class="[active ? 'text-white' : 'text-indigo-600', 'absolute inset-y-0 right-0 flex items-center pr-4']">
              </span>
            </li>
          </ListboxOption>
        </ListboxOptions>
      </transition>
    </div>
  </Listbox>
</template>

<script setup>
import { ref } from 'vue'
import { Listbox, ListboxButton, ListboxLabel, ListboxOption, ListboxOptions } from '@headlessui/vue'

const props = defineProps({
  title: {
    type: String,
    required: true,
  },
  options: {
    type: Array,
    required: true,
  },
})


const selected = ref(props.options[1]) // This will automatically update through v-model

</script>
