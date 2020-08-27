<template>
  <div class="block">
    <el-form :inline="true" :model="userquery" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="userquery.name" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item label="部门">
        <el-select v-model="userquery.departmentid" placeholder="请选择">
          <el-option :label="de.name" :value="de.id" v-for="de in departmentlist" :key="de.id"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="时间范围">
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
      <el-table-column fixed label="序号" width="120xp" align="center">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="150xp"></el-table-column>

      <el-table-column prop="department" label="部门" width="200xp"></el-table-column>

      <el-table-column prop="sex" label="性别" width="150xp"></el-table-column>

      <el-table-column prop="salary" label="工资" width="200xp"></el-table-column>

      <el-table-column label="操作" width="250xp">
        <template slot-scope="scope">
          <el-button type="primary" @click="setInfo(scope)">查看详细信息</el-button>
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="edit(scope.row.userid)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>

<el-dialog title="详细信息" :visible.sync="drawer" width="30%" :before-close="handleClose">
            <span>
              <p>年龄：{{info.age}}</p>
              <p>教育情况：{{info.education}}</p>
              <p>工作情况：{{info.workinfo}}</p>
              <p>获奖记录：{{info.awardsinfo}}</p>
              <p>处罚记录：{{info.punishmentinfo}}</p>
              <p>手机号码：{{info.phone}}</p>
              <p>居住地：{{info.address}}</p>
            </span>
            <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="drawer = false">确 定</el-button>
            </span>
          </el-dialog>


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
import userApi from "@/api/userinfo";
export default {
  data() {
    return {
      info: [],
      drawer: false,
      total: 0,
      page: 1,
      size: 5,
      userquery: {
        name: "",
        sex: "",
        departmentid: "",
        applyDateBegin: "",
        applyDateEnd: "",
      },
      list: [],
      departmentlist: [],
    };
  },
  created() {
    this.getInfo();
    this.getDepartment();
  },
  methods: {
    edit(id) {// 直接调用$router.push 实现携带参数的跳转
      return  this.$router.push({
          path: `/work/changeworkinfo/${id}|2`,
        })},

    setInfo(scope) {
      this.info.age = scope.row.age;
      this.info.education = scope.row.education;
      this.info.workinfo = scope.row.workinfo;
      this.info.awardsinfo = scope.row.awardsinfo;
      this.info.punishmentinfo = scope.row.punishmentinfo;
      this.info.phone = scope.row.phone;
      this.info.address = scope.row.address;
      this.drawer = true;
    },
    getInfo(page = 1) {
      this.page = page; //表示传入参数赋值，不传则赋值为1
      userApi
        .getGreatDealInfoPlus(this.page, this.size, this.userquery)
        .then((response) => {
          this.list = response.data.list;
          this.total = response.data.total;
        });
    },

    clear() {
      this.userquery = {};
      this.getInfo();
    },

    getDepartment() {
      userApi.getDepartment().then((response) => {
        this.departmentlist = response.data.list;
      });
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
  },
};
</script>