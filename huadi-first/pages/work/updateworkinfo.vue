<template>
  <div>
    <el-form ref="form1" :model="form1" label-width="80px">
      审核状态：
      <i class="el-icon-check" v-if="form1.statis=='pass'">通过</i>
      <i class="el-icon-close" v-if="form1.statis=='defeat'">失败</i>
      <i class="el-icon-s-promotion" v-if="form1.statis=='wait'">等待</i>
     <el-divider></el-divider>
 <el-row>
                <el-col :span="6">

      <el-form-item label="姓名">
        <el-input v-model="form1.name" style="width: 100px"></el-input>
      </el-form-item>

       </el-col>
                <el-col :span="6">

      <el-form-item label="性别">
        <el-select v-model="form1.sex" placeholder="请选择性别" style="width: 150px">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>

       </el-col>
                <el-col :span="6">

      <el-form-item label="年龄">
        <el-input-number
        style="width: 120px"
          :min="0"
          v-model="form1.age"
          controls-position="right"
          placeholder="年龄必须为整数"
        />
      </el-form-item>

      
       </el-col>
                <el-col :span="6">

      <el-form-item label="工作部门">
        <el-select v-model="form1.departmentid" placeholder="请选择部门" style="width: 150px">
          <el-option :label="de.name" :value="de.id" v-for="de in list" :key="de.id"></el-option>
        </el-select>
      </el-form-item>

       </el-col>
            </el-row>

      <el-form-item label="教育情况">
        <el-input type="textarea" v-model="form1.education"></el-input>
      </el-form-item>
      <el-form-item label="工作情况">
        <el-input type="textarea" v-model="form1.workinfo"></el-input>
      </el-form-item>

      <el-form-item label="获奖信息">
        <el-input type="textarea" v-model="form1.awardsinfo"></el-input>
      </el-form-item>

      <el-form-item label="惩罚记录">
        <el-input type="textarea" v-model="form1.punishmentinfo"></el-input>
      </el-form-item>

       <el-row>
                <el-col :span="6">

      <el-form-item label="手机号">
        <el-input v-model="form1.phone" style="width: 250px"></el-input>
      </el-form-item>

        </el-col>
                <el-col :span="6">

      <el-form-item label="居住地">
        <el-input v-model="form1.address" style="width: 250px"></el-input>
      </el-form-item>

        </el-col>
                <el-col :span="6">

      <el-form-item label="工资">
            <el-input-number
        style="width: 250px"
          :min="0"
          :max="99999"
          v-model="form1.salary"
          placeholder="工资范围在1-99999"
        />
        <!-- <el-input v-model="form1.salary" style="width: 250px"></el-input> -->
      </el-form-item>

         </el-col>
            </el-row>

      <el-form-item style="text-align: center">
        <el-button type="primary" @click="onSubmit" >提交申请</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import userApi from "@/api/userinfo";
export default {
  data() {
    return {
      list: [],
      form1: {
        name: "",
        age: "",
        sex: "",
        education: "",
        departmentid: "",
        workinfo: "",
        awardsinfo: "",
        punishmentinfo: "",
        phone: "",
        address: "",
        salary: "",
        statis: "",
      },
    };
  },
  created() {
    this.getUserInfo();
    this.getDepartment();
  },
  methods: {
    onSubmit() {
      this.$confirm("此操作将暂时失去所有权限, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          userApi.updateWorkInfo(this.form1).then((response) => {
            this.$message.success(response.message);
            if(response.success){
            setTimeout(function () {
              window.location.href = "/work/workinfo";//1秒后刷新页面
            }, "1000");
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消申请",
          });
        });
    },
    getUserInfo() {
      userApi.getWorkInfo().then((response) => {
        if(response.data.user){
        this.form1 = response.data.user;
        }
      });
    },
    getDepartment() {
      userApi.getDepartment().then((response) => {
        this.list = response.data.list;
      });
    },
  },
};
</script>