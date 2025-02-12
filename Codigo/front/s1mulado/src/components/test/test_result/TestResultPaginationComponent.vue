<script>
import {
  Button,
} from "@/components/ui/button";

import {
  Pagination,
  PaginationEllipsis,
  PaginationFirst,
  PaginationLast,
  PaginationList,
  PaginationListItem,
  PaginationNext,
  PaginationPrev,
} from "@/components/ui/pagination";

export default {
  components: {
    Pagination,
    PaginationEllipsis,
    PaginationFirst,
    PaginationLast,
    PaginationList,
    PaginationListItem,
    PaginationNext,
    PaginationPrev,
    Button
  },
  data() {
    return {
      currentPage: 1, // Estado inicial da página
      minPage: 1, // Página mínima permitida
    };
  },
  methods: {

    handlePageChange(newPage) {


      if (newPage < this.minPage) {
        this.currentPage = this.minPage;
      } else if (newPage > this.maxPage) {
        this.currentPage = this.maxPage;
      } else {
        this.currentPage = newPage;
      }

    },
    
  }, props: {
    maxPage: Number
  }
};
</script>

<template>

  <Pagination 
    v-slot="{ page, items }" 
    :page="currentPage" 
    @update:page="handlePageChange" 
    :total="maxPage*4" 
    :items-per-page="4"
    :sibling-count="1" 
    show-edges
  >
    <PaginationList class="flex items-center gap-1" @click="$emit('pageChange', currentPage)">

      <PaginationFirst />
      <PaginationPrev />

      <PaginationNext />
      <PaginationLast />
      
    </PaginationList>

  </Pagination>

</template>