<template>
  <div class="in-wrap">
    <!-- 公共头 -->
    <app-header/>
    <!-- /公共头 -->

    <!-- 内容区域 -->
    <nuxt/>
    <!-- /内容区域 -->

    <!-- 公共底 -->
    <app-footer />
    <!-- /公共底 -->
  </div>
</template>
<script>
import AppHeader from '~/components/AppHeader.vue'
import AppFooter from '~/components/AppFooter.vue'
// import cookie from 'js-cookie'

export default {
  components: {
    AppHeader, AppFooter
  }
}
</script>
