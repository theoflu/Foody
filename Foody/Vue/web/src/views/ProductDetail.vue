<template>
  <div class="container shopping-cart">
    <div class="content">
      <h3>{{ productDetail.name }}</h3>

      <div class="row">
        <div class="col-md-12 col-lg-12">
          <div class="row">
            <div class="col-md-5">
              <image-carousel v-bind:images=productDetail.images></image-carousel>
            </div>
            <div class="col-md-7">
              <product-features v-bind:productFeatures="productDetail.features"></product-features>
            </div>
          </div>
          <hr>
        </div>
      </div>
      <div class="row">
        <product-description v-bind:description="productDetail.description"></product-description>
      </div>
      <div class="row">
        <product-comments v-bind:productId="productDetail.id"></product-comments>
      </div>
    </div>
  </div>
</template>

<script>

import ImageCarousel from "@/components/product-detail/image-carousel";
import {getById} from "@/common/product.service";
import ProductFeatures from "@/components/product-detail/product-features.vue";
import ProductComments from "@/components/product-detail/product-comments.vue";
import ProductDescription from "@/components/product-detail/product-description.vue";

export default {
  name: 'ProductDetailComponent',
  components: {ProductFeatures, ProductDescription, ProductComments, ImageCarousel},
  data() {
    return {productDetail: {}}
  },
  mounted() {
    getById(this.$route.params.id).then(response => {
      this.productDetail = response.data;
    });

  }
}
</script>


<style scoped lang='scss'>

</style>