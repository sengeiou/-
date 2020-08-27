<template>
<div>

  <!-- 课程信息表单 -->
    <el-form label-width="120px" ref="department" :model="department">
      <el-form-item label="部门名称">
        <el-input v-model="department.name" placeholder="请输入" style="width:200px" />
      </el-form-item>
      
       <el-form-item label="部门描述">
        <el-input type="textarea" v-model="department.detail" style="width:800px"></el-input>
      </el-form-item>

                  <el-form-item label="选择成员">
        <el-select v-model="department.departmentid" placeholder="请选择" @change="selectUser">
          <el-option :label="de.name" :value="de.id" v-for="de in list" :key="de.id"></el-option>
        </el-select>
      </el-form-item>

<div style="margin-left:300px">
    <el-transfer
      v-model="department.yesData"
      :props="{key: 'userid',label: 'name'}"
      :titles="['未选择', '已选择']"
      @change="handleChange"
      :data="userlist"
    ></el-transfer>


      <el-form-item label="选择权限">
            </el-form-item>
    <el-transfer
      v-model="department.power"
      :props="{key: 'id',label: 'name'}"
      :titles="['未选择', '已选择']"
      @change="handleChange"
      :data="powerlist"
    ></el-transfer>

     </div>         
 
    </el-form>
<div style="text-align:center">
<el-button style="margin-top: 12px;" @click="submit">保存</el-button>
</div>
</div>
</template>
<script>
import departmentApi from "@/api/department";
  export default {
    data() {
      return {
         
          list:[],//部门列表
      userlist: [{
          userid:"",
          name:""
      }],
        powerlist: [{
          id:"",
          name:""
      }],
          department:{
               departmentid:'',
              name:'',
              detail:"",
              yesData: [],
              power:[]
          }
      };
    },
  created() {
 this.getDepartment();
 this.getAllPower()
  },
    methods: {
      submit() {
        departmentApi.adddepartment(this.department).then(response => {
          if(response.success){
          this.$message({
            type: "success",
            message: "添加成功!"
          });
          this.$router.push({ path: "/people"});
          }
        });     
      },
            
    selectUser() {
      departmentApi.findUser(this.department.departmentid).then((response) => {
        this.userlist = response.data.list;

      });
    },
        getDepartment() {
      departmentApi.getDepartment().then((response) => {
        this.list = response.data.list;
      });
    },
          getAllPower() {
      departmentApi.getAllPower().then((response) => {
        this.powerlist = response.data.listall;
      });
    },

    handleChange(value, direction, movedKeys) {
      // console.log(value, direction, movedKeys);
      console.log(this.form1.yesData);
      //可以通过direction回调right/left 来进行操作，right：把数字移到右边，left把数据移到左边
      if (direction === "right") {
      }
      if (direction === "left") {
      }
    },
    }
  }
</script>