<template>
    
  <nav class=" flex flex-row items-center h-16 justify-between z-10 gap-1 text-[#DADADA] bg-white">

      <div class="left-10 flex relative">

          <IconButtonComponent content = "home" mainUrl = "/home" title="Home" />

      </div>

      <div class=" mr-20 relative">
          <ul class="flex flex-row gap-[4vw] items-center">
              <li>
                  <IconButtonComponent content = "education" mainUrl = "/tests" title="Provas"/>
              </li>

              <li>
                  <IconButtonComponent content = "profile" mainUrl = "/profile" title="Perfil"/>
              </li>
          </ul>
      </div>
  </nav>

</template>


<script>

import IconButtonComponent from '@/components/common/IconButtonComponent.vue'

export default {
    name: 'NavbarComponent',
    components: {
      IconButtonComponent
    }
}
</script>