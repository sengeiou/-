<template>
  <div style="margin-left:30%">
    <el-form ref="form1" :model="form1" label-width="80px">

         <el-form-item label="所属部门">
        <el-select v-model="form1.departmentid" placeholder="请选择"   @change="selectUser">
          <el-option :label="de.name" :value="de.id" v-for="de in list" :key="de.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="收款人">
        <el-select v-model="form1.userid" placeholder="请选择">
          <el-option :label="u.name" :value="u.userid" v-for="u in userlist" :key="u.userid"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="金额">
        <el-input-number
          :min="0"
          v-model="form1.salary"
          controls-position="right"
          placeholder="请输入金额"
        />
      </el-form-item>


      <el-form-item>
        <el-button type="primary" @click="onSubmit">发放</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import moneyApi from "@/api/money";
import userApi from "@/api/userinfo";
export default {
  data() {
    return {
        list: [],
        userlist:[],
      form1: {
        userid: "",
        departmentid: "",
        salary: "",
      },
    };
  },
  created() {
this.getDepartment();

  },
  methods: {
      selectUser(){
            userApi.findUser(this.form1.departmentid).then(response=>{
                this.userlist=response.data.list
            })
      },
    onSubmit() {
      this.$confirm("是否确认提交?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          moneyApi.paySalary(this.form1).then((response) => {
            this.$message.success(response.message);
            setTimeout(function () {
              window.location.href = "/money/allapply"; //1秒后刷新页面
            }, "1000");
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
      getDepartment() {
      userApi.getDepartment().then((response) => {
        this.list = response.data.list;
      });
    }
    //下面声明方法结束
    
  },
};
</script>