<template>
  <div class="app-container">
    <div style="margin:0 0 5px 20px">
      会议明细列表
    </div>

    <div class="app-container">
      <div class="filter-container" />

      <el-table
        :key="tableKey"
        :data="list"
        border
        fit
        highlight-current-row
        style="width: 100%;"
      >

        <el-table-column prop="startTime" label="开始时间" align="center" width="180" />
        <el-table-column prop="endTime" label="结束时间" align="center" width="180" />
        <el-table-column prop="title" label="标题" align="center" width="180" />
        <el-table-column prop="authorBlock" label="作者块" align="center" width="900" />
      </el-table>

      <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />

    </div>

  </div>
</template>

<script>
import Pagination from '@/components/Pagination/index.vue'
import { getDetailPage } from '@/api/session/detail'

export default {
  name: 'SessionDetail',
  components: { Pagination },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listQuery: {
        pageNo: 1,
        pageSize: 10,
        actionId: undefined
      },
      tempRoute: {}
    }
  },
  created() {
    const actionId = this.$route.params && this.$route.params.id
    this.listQuery.actionId = actionId
    this.getList(actionId)

    this.tempRoute = Object.assign({}, this.$route)
  },
  methods: {
    getList(actionId) {
      getDetailPage(this.listQuery).then(res => {
        this.list = res.data.list
        this.total = res.data.total

        this.setPageTitle(actionId)
        this.setTagsViewTitle(actionId)
      })
    },
    setTagsViewTitle(actionId) {
      const title = '会议明细'
      const route = Object.assign({}, this.tempRoute, { title: `${title}-${actionId}` })
      this.$store.dispatch('tagsView/updateVisitedView', route)
    },
    setPageTitle(actionId) {
      const title = '会议明细'
      document.title = `${title} - ${actionId}`// 浏览器标签的标题
    }
  }
}
</script>

