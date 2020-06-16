<template>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="totalElement"
        :page-size="numberElementOnePage"
        @current-change="handleCurrentChange"
        >
    </el-pagination>
</template>

<script>
export default {
  name: "pagination",
  props: {
    totalElement: Number,
    totalPages: Number,
    numberElementOnePage: Number,
  },

  methods: {
    handleCurrentChange(val) {
      this.$emit('changeCurrentPage', val)
    }
  }
};
</script>