<template>
  <div class="block">
    <el-form :inline="true" :model="userquery" class="demo-form-inline">
   <el-form-item label="项目名">
        <el-input v-model="userquery.name" placeholder="请输入" style="width: 100px"></el-input>
      </el-form-item>

        <el-form-item label="项目名">
        <el-input v-model="userquery.creatusername" placeholder="请输入" style="width: 100px"></el-input>
      </el-form-item>

          <el-form-item label="完成状态">
        <el-select v-model="userquery.statis" placeholder="请选择" style="width: 100px">
          <el-option label="已完成" value="pass" ></el-option>
           <el-option label="未完成" value="wait" ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker v-model="userquery.membernumS" type="datetime" placeholder="选择开始时间"></el-date-picker>
      </el-form-item>
      <el-date-picker v-model="userquery.membernumE" type="datetime" placeholder="选择结束时间"></el-date-picker>
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

      <el-table-column prop="name" label="项目名称" width="120"></el-table-column>
      <el-table-column prop="info" label="项目详情" width="220"></el-table-column>

      <el-table-column  label="完成状态" width="120">
         <template slot-scope="scope">
          <span> {{ scope.row.statis=='pass'?'已完成':'未完成' }}</span>
        </template>
         

      </el-table-column>

      <el-table-column prop="creatusername" label="创建人" width="120"></el-table-column>

       <el-table-column prop="membernum" label="项目人数" width="120"></el-table-column>


      <el-table-column prop="gmtCreate" label="发布时间" width="220"></el-table-column>


      <el-table-column label="操作" width="170">
        <template slot-scope="scope">
          
          <el-button
            type="success"
            size="mini"
            icon="el-icon-check"
            @click="check(scope.row.id)"
          >查看成员详情</el-button>

            <el-button
            type="success"
            size="mini"
            icon="el-icon-check"
            @click="del(scope.row.id)"
          >删除</el-button>


        </template>
      </el-table-column>
    </el-table>


<el-dialog title="详细信息" :visible.sync="drawer" width="30%">

            <span v-for="(me,index) in detaillist" :key="index">
              <p>姓名：{{me.membername}}-----------完成状态：{{me.statis}}</p>
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
import projectApi from "@/api/project";
export default {
  data() {
    return {
      drawer:false,
      total: 0,
      page: 1,
      size: 5,
      userquery: {
        name:'',
        creatusername:'',
        statis: "",
        membernumS: "",
        membernumE: ""
      },
      list: [],
      detaillist:[]
    };
  },
  created() {
    this.getInfo();
  },
  methods: {

del(id){
  this.$confirm("此操作将删除每个参与员工的相关信息（等待时间较长）, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          projectApi.delById(id).then((response) => {
            this.$message.success(response.message);
            setTimeout(function () {
              window.location.href = "/project/allproject";//1秒后刷新页面
            }, "1000");
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });

}
    ,
    check(id){
      projectApi.checkProjectById(id).then(response=>{
      this.detaillist=response.data.list
      console.log(response.data.list)
      this.drawer=true;
      })
    }
    ,
    getInfo(page = 1) {
      this.page = page; //表示传入参数赋值，不传则赋值为1
      projectApi
        .getAllProject(this.page, this.size, this.userquery)
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