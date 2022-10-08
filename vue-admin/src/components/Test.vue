<script setup>

</script>

<template>
  <div class="pagination">
    <span class="pagination-item">&lt;</span>
    <span class="pagination-item">1</span>
    <span class="pagination-item">···</span>
    <span class="pagination-item">3</span>
    <span class="pagination-item">4</span>
    <span class="pagination-item">5</span>
    <span class="pagination-item">6</span>
    <span class="pagination-item">7</span>
    <span class="pagination-item">···</span>
    <span class="pagination-item">9</span>
    <span class="pagination-item">&gt;</span>
    <span style="margin-left: 30px">共 60 条</span>
  </div>
</template>

<style scoped>
.pagination {
}
.pagination-item {
  display: inline-block;
  background-color: slateblue;
  margin: 0 5px;
  width: 2.5em;
  height: 2.5em;
  line-height: 2.5em;
  border-radius: 10px;
}
</style>
