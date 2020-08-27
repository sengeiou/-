<template>
  <div class="block">
    <el-form :inline="true" :model="userquery" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="userquery.name" placeholder="请输入姓名" style="width: 100px"></el-input>
      </el-form-item>

  <el-form-item label="申请金额范围">
        <el-input v-model="userquery.applyMoneyBegin" placeholder="起始值" style="width: 100px"></el-input>
        
      </el-form-item>

       <el-input v-model="userquery.applyMoneyEnd" placeholder="结束值" style="width: 100px"></el-input>

      <el-form-item label="申请时间">
        <el-date-picker v-model="userquery.applyDateBegin" type="datetime" placeholder="选择开始时间"></el-date-picker>
      </el-form-item>
      <el-date-picker v-model="userquery.applyDateEnd" type="datetime" placeholder="选择结束时间"></el-date-picker>
      <el-form-item>
        <!-- 这里要带括号 -->
        <el-button type="primary" @click="getInfo()">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="default" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="list" style="width: 100%">
      <el-table-column fixed label="序号" width="80" align="center">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>

      <el-table-column prop="name" label="申请人姓名" width="120"></el-table-column>
      <el-table-column prop="money" label="申请金额" width="120"></el-table-column>

      <el-table-column prop="type" label="申请类型" width="120"></el-table-column>
      <el-table-column prop="detail" label="详细说明" width="220"></el-table-column>
      <el-table-column  label="证明材料下载" width="180">
        <template slot-scope="scope">
          <el-link :href="scope.row.sourceurl">点击下载</el-link>
        </template>
      </el-table-column>

      <el-table-column prop="gmtCreate" label="申请时间" width="220"></el-table-column>

      <el-table-column label="操作" width="170">
        <template slot-scope="scope">
          <el-button
            type="success"
            size="mini"
            icon="el-icon-check"
            @click="pass(scope.row.tableid,'pass')"
          >通过</el-button>

          <el-button
            type="info"
            size="mini"
            icon="el-icon-remove"
            @click="defeat(scope.row.tableid,'defeat')"
          >拒绝</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 这里不带括号，内部封装了回调参数 -->
    <el-pagination
      @current-change="getInfo"
      :current-page.sync="page"
      :page-size="size"
      style="text-align:center;"
      layout="total, prev, pager, next, jumper"
      :total="total"
    ></el-pagination>
  </div>
</template>
<script>
import moneyApi from "@/api/money";
export default {
  data() {
    return {
      total: 0,
      page: 1,
      size: 5,
      userquery: {
        name: "",
        applyMoneyBegin:'',
        applyMoneyEnd:'',
        applyDateBegin: "",
        applyDateEnd: "",
      },
      list: [],
    };
  },
  created() {
    this.getInfo();
  },
  methods: {
    pass(id, result) {
      moneyApi.dealApply(id, result).then((response) => {
        this.$message.success(response.message);
      });
      setTimeout(function () {
        window.location.href = "/money/dealapply"; //1秒后刷新页面
      }, "1000");
    },
    defeat(id, result) {
      moneyApi.dealApply(id, result).then((response) => {
        this.$message.success(response.message);
      });
      setTimeout(function () {
        window.location.href = "/money/dealapply"; //1秒后刷新页面
      }, "1000");
    },
    getInfo(page = 1) {
      this.page = page; //表示传入参数赋值，不传则赋值为1
      moneyApi
        .getWaitApply(this.page, this.size, this.userquery)
        .then((response) => {
          this.list = response.data.list;
          this.total = response.data.total;
        });
    },

    clear() {
      this.userquery = {};
      this.getInfo();
    },
  },
};
</script>