<template>
  <div class="block">
    <el-form :inline="true" :model="userquery" class="demo-form-inline">
      <el-form-item label="项目名">
        <el-input v-model="userquery.name" placeholder="请输入" style="width: 100px"></el-input>
      </el-form-item>

      <el-form-item label="项目发起人">
        <el-input v-model="userquery.creatusername" placeholder="请输入" style="width: 100px"></el-input>
      </el-form-item>

      <el-form-item label="项目完成状态">
        <el-select v-model="userquery.statis" placeholder="请选择">
          <el-option label="已完成" value="pass" ></el-option>
           <el-option label="未完成" value="wait" ></el-option>
        </el-select>
      </el-form-item>


      <el-form-item label="员工姓名">
        <el-input v-model="userquery.membername" placeholder="请输入" style="width: 100px"></el-input>
      </el-form-item>

      <el-form-item label="申请时间">
        <el-date-picker v-model="userquery.timeStart" type="datetime" placeholder="选择开始时间"></el-date-picker>
      </el-form-item>
      <el-date-picker v-model="userquery.timeEnd" type="datetime" placeholder="选择结束时间"></el-date-picker>
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

      <el-table-column prop="name" label="项目名" width="120"></el-table-column>

      <el-table-column prop="creatusername" label="项目发起人" width="120"></el-table-column>
      <el-table-column prop="membername" label="员工名称" width="220"></el-table-column>

      <el-table-column prop="gmtCreate" label="发布时间" width="220"></el-table-column>
      <el-table-column prop="statis" label="完成状态" width="220"></el-table-column>

      <el-table-column label="操作" width="170">
        <template slot-scope="scope">
          <el-button     v-if="scope.row.statis=='wait'" type="success" size="mini" icon="el-icon-check" @click="pass(scope.row.id)">直接完成</el-button>
          <el-button type="info" size="mini" icon="el-icon-remove" @click="del(scope.row.id)">删除成员记录</el-button>
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
import projectApi from "@/api/project";
export default {
  data() {
    return {
      total: 0,
      page: 1,
      size: 5,
      userquery: {
        name: "",
        creatusername: "",
        timeStart: "",
        timeEnd: "",
      },
      list: [],
    };
  },
  created() {
    this.getInfo();
  },
  methods: {
    del(id) {
      projectApi.delMemberById(id).then((response) => {
        this.$message.success(response.message);
      });
      setTimeout(function () {
        window.location.href = "/project/allprojectdetail"; //1秒后刷新页面
      }, "1000");
    },

    pass(id) {
      projectApi.pass(id).then((response) => {
        this.$message.success(response.message);
      });
      setTimeout(function () {
        window.location.href = "/project/allprojectdetail"; //1秒后刷新页面
      }, "1000");
    },
    getInfo(page = 1) {
      this.page = page; //表示传入参数赋值，不传则赋值为1
      projectApi
        .getAllProjectDetail(this.page, this.size, this.userquery)
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