<template>
    <div></div>
</template>

<script>
import Auth from '@/security/Authentication';
export default {
    name: 'logout',
    created() {
        Auth.logout();
        this.$router.push({ path: '/login' });
    }
}
</script>

<style lang="scss">

</style>