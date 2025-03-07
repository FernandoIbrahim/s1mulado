<template>
    
    <div>
          <button type="submit" class="flex w-full justify-center rounded-md bg-midnight text-md font-semibold text-white shadow-xs hover:midnight focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-600" :class="['px-' + paddingX, 'py-' + paddingY]">  <slot></slot> </button>

    </div>

</template>

<script>

    export default {
        name: 'ButtonComponent',
        props: {
            paddingX: {
                type: String,
                required: true
            },
            paddingY: {
                type: String,
                required: true
            }
        }

    }

</script>