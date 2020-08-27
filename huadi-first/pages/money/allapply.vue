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
      <el-table-column fixed label="序号" width="120" align="center">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>

      <el-table-column prop="name" label="收款人姓名" width="160"></el-table-column>
      <el-table-column prop="money" label="金额" width="140"></el-table-column>

      <el-table-column prop="dealname" label="发放人姓名" width="160"></el-table-column>
      <el-table-column prop="department" label="收款人所属部门" width="220"></el-table-column>

      <el-table-column prop="time" label="发放时间" width="220"></el-table-column>
      
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            type="info"
            size="mini"
            icon="el-icon-remove"
            @click="deleteById(scope.row.tableid)"
          >删除记录</el-button>
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
      deleteById(id){
          moneyApi.deleteById(id).then(response=>{
 this.$message.success(response.message);
            setTimeout(function () {
              window.location.href = "/money/allapply"; //1秒后刷新页面
            }, "1000");
          })
      },
    getInfo(page = 1) {
      this.page = page; //表示传入参数赋值，不传则赋值为1
      moneyApi
        .getAllMoneyPay(this.page, this.size, this.userquery)
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