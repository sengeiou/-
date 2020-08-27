<template>
  <div class="block">
    <el-form :inline="true" :model="userquery" class="demo-form-inline">
      <el-form-item label="昵称">
        <el-input v-model="userquery.nickname" placeholder="请输入昵称" style="width:120px"></el-input>
      </el-form-item>

      <el-select v-model="userquery.statis" placeholder="请选择锁定状态" style="width:120px">
        <el-option label="锁定" value="1"></el-option>
        <el-option label="未锁定" value="0"></el-option>
      </el-select>

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
      <el-table-column fixed label="序号" width="100" align="center">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>

     <el-table-column prop="avatar" label="头像" width="220">
         <template slot-scope="scope">
             <img :src="scope.row.avatar" with="100px" height="100px">
        </template>
        
     </el-table-column>
      
      <el-table-column prop="nickname" label="昵称" width="220"></el-table-column>

      <el-table-column prop="gmtCreate" label="注册时间" width="220"></el-table-column>

      <el-table-column label="操作" width="220">
        <template slot-scope="scope">

 <!-- v-if="scope.row.deleted==true" -->
          <el-button
         
            type="success"
            size="mini"
            icon="el-icon-check"
            @click="pass(scope.row.id)"
          >解锁</el-button>

 <!-- v-if="scope.row.deleted==false" -->
          <el-button
         
            type="info"
            size="mini"
            icon="el-icon-remove"
            @click="defeat(scope.row.id)"
          >锁定</el-button>
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
import userApi from "@/api/user";
export default {
  data() {
    return {
      info:[],
      drawer: false,
      total: 0,
      page: 1,
      size: 5,
      userquery: {
        nickname: "",
        statis: "",
        applyDateBegin: "",
        applyDateEnd: "",
      },
      list: []
    };
  },
  created() {
    this.getInfo();
  },
  methods: {

    pass(id) {
 this.$confirm("此操作将解封该账号, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
            //
          userApi.unlock(id).then((response) => {
            this.$message.success(response.message);
            this.getInfo()
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
    defeat(id) {
 this.$confirm("此操作将锁定该账号（无法登录）, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
            //
          userApi.lock(id).then((response) => {
            this.$message.success(response.message);
             this.getInfo()
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
    getInfo(page = 1) {
      this.page = page; //表示传入参数赋值，不传则赋值为1
      userApi
        .getAllUser(this.page, this.size, this.userquery)
        .then((response) => {
            console.log(response.data.list)
          this.list = response.data.list;
          this.total = response.data.total;
        });
    },

    clear() {
      this.userquery = {};
      this.getInfo();
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