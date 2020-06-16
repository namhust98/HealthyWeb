<template>
  <div class="search">
    <div class="search-container">
      <input type="text" :placeholder="placeholderText" v-model="searchQuery" name="searchQuery" v-on:keyup.enter="search()"/>
      <button v-on:click="search()">
        <i class="el-icon-search"></i>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "search",
  props: {
    nameSearch: "",
    placeholderText: ""
  },
  data() {
  return {
    searchQuery: null,
  }
},
  methods: {
    search(){
      if(this.nameSearch == "filter-management") {
        this.$emit("searchQuery", this.searchQuery);
        return;
      }
      if(this.nameSearch == "unpaid-guest") {
        this.$emit("searchQuery", this.searchQuery);
        return;
      }
      if(this.nameSearch == "search-user-send-mail") {
        this.$emit("emailNameSearch", this.searchQuery);
        return;
      }
    }, 
    resetInputValue: function(value) {
      this.searchQuery = "";
    } 
  },
};
</script>

<style lang="scss">
.search {
  width: 80%;
  .search-container {
    position: relative;
    display: flex;

    input {
      width: 100%;
      border: 1px solid #1272c4;
      border-right: none;
      padding: 5px 5px 5px 15px;
      // height: 25px;
      border-radius: 5px 0 0 5px;
      outline: none;
    }
    button {
      width: 55px;
      height: 37px;
      border: 1px solid #1272c4;
      background: #1272c4;
      text-align: center;
      color: #fff;
      border-radius: 0 5px 5px 0;
      cursor: pointer;
      font-size: 20px;

      //   &:hover {
      //     background: #ccc;
      //   }

      .el-icon-search {
        color: #fff;
      }
    }
  }
}
</style>