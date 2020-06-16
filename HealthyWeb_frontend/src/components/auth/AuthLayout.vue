<template>
  <div class="auth-layout row align-content--center">
    <div class="flex xs12 lg6 pa-3 flex-center"></div>

    <div class="flex xs12 lg6 pa-3">
      <div class="d-flex justify--center">
        <div class="wrapper">
          <router-view />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.wrapper {
  width: 100%;
  padding-bottom: 50px;
  padding-top: 50px;
  height: 100%;
  position: relative;
  overflow-x: hidden;
  overflow-y: auto;
}

@media (min-width: 992px) {
  .wrapper {
    padding-top: 100px;
  }
}
</style>