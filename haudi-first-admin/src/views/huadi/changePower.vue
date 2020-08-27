<template>
<div style="margin-left:15%">
<el-collapse v-model="activeName" accordion>
  <el-collapse-item :title="de.name" v-for="de in list" :name="de.id" :key="de.id">
    

  <el-checkbox-group v-model="de.list" >
    <el-checkbox v-for="po in powerlist" :label="po.id" :key="po.id">{{po.name}}</el-checkbox>
  </el-checkbox-group>


  </el-collapse-item> 
</el-collapse>

<div style="margin-left:30%">
<el-button type="primary" @click="submit()">保存修改</el-button>
</div>
 
  </div>
</template>
<script>
import departmentApi from "@/api/department";
  export default {
    data() {
      return {
          activeName:1,
           list:[],//部门列表,
powerlist: [{
          id:"",
          name:""
      }],

        checkAll: false,
        checkedPower: [],//部门现有权力
        isIndeterminate: true
      };
    },
    created() {
 this.getDepartment();
 this.getAllPower()
  },
    methods: {
        submit(){
            departmentApi.changePower(this.list).then(response=>{
                    if(response.success){
          this.$message({
            type: "success",
            message: response.message
          });
          this.$router.push({ path: "/"});
          }
            })
        },
                getDepartment() {
      departmentApi.getDepartment2().then((response) => {
        this.list = response.data.list;
      });
    },
          getAllPower() {
      departmentApi.getAllPower().then((response) => {
        this.powerlist = response.data.listall;
      });
    }
    }
  };
</script>