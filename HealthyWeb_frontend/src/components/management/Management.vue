<template>
  <div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="date" label="Date" width="180"></el-table-column>
      <el-table-column prop="name" label="Name" width="180"></el-table-column>
      <el-table-column prop="address" label="Address"></el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  name: "app-management",
  data() {
    return {
      tableData: [
        {
          date: "2020-16-06",
          name: "Lương Vỹ",
          address: "Hà Nội"
        },
        {
          date: "2020-16-06",
          name: "Lê Võ Minh Đạt",
          address: "Hà Nội"
        },
        {
          date: "2020-16-06",
          name: "Nguyễn Hải Nam",
          address: "Hà Nội"
        },
        {
          date: "2020-16-06",
          name: "Vũ Nguyên Hoàng",
          address: "Tp Hồ Chí Minh"
        },
      ]
    };
  },
  mounted() {}
};
</script>
<style lang="scss" scoped>
</style>