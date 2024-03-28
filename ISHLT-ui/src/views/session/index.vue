<template>
  <div class="app-container">
    <div style="margin:0 0 5px 20px">
      会议列表
    </div>

    <div class="app-container">
      <div class="filter-container">
        <el-checkbox-group v-model="checkboxVal">
          <el-checkbox label="描述">描述</el-checkbox>
          <el-checkbox label="地点">地点</el-checkbox>
          <el-checkbox label="日期">日期</el-checkbox>
        </el-checkbox-group>
      </div>

      <el-table
        :key="tableKey"
        v-loading="listLoading"
        :data="list"
        border
        fit
        highlight-current-row
        style="width: 100%;"
        @sort-change="sortChange"
      >

        <el-table-column prop="id" label="主键id" sortable="custom" align="center" width="100" />
        <el-table-column prop="description" label="描述" align="center" width="900">
          <template slot-scope="scope">
            <div v-html="scope.row.description" />
          </template>
        </el-table-column>
        <el-table-column prop="place" label="地点" align="center" width="180" />
        <el-table-column prop="sessionDate" label="日期" align="center" width="180" />
        <el-table-column label="明细" align="center" width="95">
          <template slot-scope="scope">
            <router-link :to="'/session/detail/'+scope.row.id" class="link-type">
              <span>查看</span>
            </router-link>
          </template>
        </el-table-column>

      </el-table>

      <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />

    </div>

  </div>
</template>

<script>

import { getSessionPage } from '@/api/session/session'
import Pagination from '@/components/Pagination/index.vue'

export default {
  name: 'Session',
  components: { Pagination },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        pageNo: 1,
        pageSize: 10,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      },
      key: 1, // table key
      formTheadOptions: ['描述', '地点', '日期'],
      checkboxVal: ['描述', '地点'], // checkboxVal
      formThead: ['描述', '地点'] // 默认表头 Default header
    }
  },
  watch: {
    checkboxVal(valArr) {
      this.formThead = this.formTheadOptions.filter(i => valArr.indexOf(i) >= 0)
      this.key = this.key + 1// 为了保证table 每次都会重渲 In order to ensure the table will be re-rendered each time
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getSessionPage(this.listQuery).then(res => {
        this.list = res.data.list // 将从接口获取的数据赋值给tableData Assign data obtained from the API to tableData
        this.total = res.data.total

        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    }, sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    }
  }
}
</script>

