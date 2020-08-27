<template>
  <div>
    <el-form ref="form1" :model="form1" label-width="80px">

 <el-row>
                <el-col :span="6">

    <el-form-item label="姓名"> 
        <el-input v-model="form1.name"  style="width:120px"></el-input>
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
        <el-select v-model="form1.departmentid" placeholder="请选择部门">
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
        <el-input v-model="form1.salary" style="width: 250px"></el-input>
      </el-form-item>

      
         </el-col>
            </el-row>

      <el-form-item style="text-align: center">
        <el-button type="primary" @click="onSubmit">保存修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import userApi from "@/api/userinfo";
export default {
  data() {
    return {
      power:'',
      userId:"",
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
      },
    };
  },
  created() {
    var str=this.$route.params.id;
    var arr=str.split("|")
      this.userId=arr[0];
      this.power=arr[1];
    this.getUserInfo();
    this.getDepartment();
  },
  methods: {
    onSubmit() {
      this.$confirm("确定要保存当前修改的内容吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          if(this.power!=1&&this.power!=2){
            alert("非法操作！")
             window.location.href = "/";
          }
          //根据上一个页面传过来的参数决定调用指定权限的方法
          if(this.power==1){
          userApi.updateNormalWorkInfoByUserId(this.form1,this.userId).then((response) => {
            this.$message.success(response.message);
            if(response.success){
               setTimeout(function () {
        window.location.href = '/work/managenormalwork' //1秒后刷新页面
      }, "1000");

            }
          })
          }

          if(this.power==2){
            userApi.updateGreatWorkInfoByUserId(this.form1,this.userId).then((response) => {
            this.$message.success(response.message);

              if(response.success){
  setTimeout(function () {
        window.location.href = '/work/managegreatwork' //1秒后刷新页面
      }, "1000");
              
            }
          });
          }

        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
    getUserInfo() {
       if(this.power!=1&&this.power!=2){
            alert("非法操作！")
             window.location.href = "/";
          }
      //根据上一个页面传过来的参数决定调用指定权限的方法
      if(this.power==1){
      userApi.getNormalWorkInfoByUserId(this.userId).then((response) => {
        this.form1 = response.data.user;
      })
      }
       if(this.power==2){
           userApi.getGreatWorkInfoByUserId(this.userId).then((response) => {
        this.form1 = response.data.user;
      })
       }

    },
    getDepartment() {
      userApi.getDepartment().then((response) => {
        this.list = response.data.list;
      });
    },
  },
};
</script>