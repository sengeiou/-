<template>
  <div class="block">
    <el-form :inline="true" :model="userquery" class="demo-form-inline">

          <el-form-item label="审批状态">
        <el-select v-model="userquery.statis" placeholder="请选择" style="width: 100px">
          <el-option label="通过" value="pass" ></el-option>
           <el-option label="待审批" value="wait" ></el-option>
            <el-option label="失败" value="defeat"></el-option>
        </el-select>
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

        <el-table-column prop="statis" label="审核状态" width="120"></el-table-column>

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
        statis: "",
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
    getInfo(page = 1) {
      this.page = page; //表示传入参数赋值，不传则赋值为1
      moneyApi
        .getMyMoneyPay(this.page, this.size, this.userquery)
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