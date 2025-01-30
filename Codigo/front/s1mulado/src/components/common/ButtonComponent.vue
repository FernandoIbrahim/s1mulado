<template>
    
    <div>
          <button type="submit" class="flex w-full justify-center rounded-md bg-gray-500 px-3 py-1.5 text-sm/6 font-semibold text-white shadow-xs hover:bg-gray-900 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-600">  <slot></slot> </button>
    </div>

</template>

<script>

    export default {
        name: 'ButtonComponent',
    }

</script>