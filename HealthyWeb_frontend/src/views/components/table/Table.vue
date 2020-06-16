<template>
  <div class="table-view">
    <table class="table-container">
      <tr>
        <th>User ID</th>
        <th>User name</th>
        <th>Mail address</th>
        <th>Phone number</th>
        <th>Registration date</th>
        <th>Status of use</th>
        <th>Detail</th>
      </tr>
      <tr>
        <td>Alfreds Futterkiste</td>
        <td>Maria Anders</td>
        <td>Germany</td>
        <td>Germany</td>
        <td>Germany</td>
        <td>Germany</td>
        <td class="link-button">詳細</td>
      </tr>
      <tr>
        <td>Alfreds Futterkiste</td>
        <td>Maria Anders</td>
        <td>Germany</td>
        <td>Germany</td>
        <td>Germany</td>
        <td>Germany</td>
        <td class="link-button">詳細</td>
      </tr>
      <tr>
        <td>Alfreds Futterkiste</td>
        <td>Maria Anders</td>
        <td>Germany</td>
        <td>Germany</td>
        <td>Germany</td>
        <td>Germany</td>
        <td class="link-button">詳細</td>
      </tr>
      <tr>
        <td>Alfreds Futterkiste</td>
        <td>Maria Anders</td>
        <td>Germany</td>
        <td>Germany</td>
        <td>Germany</td>
        <td>Germany</td>
        <td class="link-button">詳細</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "table-view"
};
</script>

<style lang="scss">
.table-container {
  width: 100%;
  border-spacing: 0;
  th {
    padding-left: 18px;
    padding-top: 18px;
    padding-bottom: 18px;
    font-size: 16px;
    text-align: left;
    background-color: #f3f3f3;
  }

  td {
    height: 60px;
    vertical-align: center;
    text-align: left;
    padding-left: 18px;
    border-bottom: 1px solid #ececec;
  }
  tr {
    .link-button {
      color: #1272c4;
      font-weight: bold;
      cursor: pointer;
    }
  }
}
</style>