<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "product-features",
  props: ['productFeatures']
}
</script>

<template>
  <div class="row">
    {{ productFeatures }}
  </div>
  <hr/>
  <div class="row">
    info 2
  </div>
  <hr/>
  <div class="row">
    info 3
  </div>
  <hr/>
  <div class="row">
    info 4
  </div>
</template>

<style scoped lang="scss">

</style>