<script>
export default {
  name: "product-description",
  props: ['productDescription']
}
</script>

<template>
  <div class="col-md-12 col-lg-12">
    <div class="row">
      {{ productDescription }}
    </div>
    <hr>
  </div>
</template>

<style scoped lang="scss">

</style>