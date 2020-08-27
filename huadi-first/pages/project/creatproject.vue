<template>
  <div style="margin-left:30%">


    <el-form ref="form1" :model="form1" label-width="80px">

 <el-form-item label="项目名称">
        <el-input v-model="form1.name" style="width: 300px"></el-input>
      </el-form-item>

           <el-form-item label="项目详情">
        <el-input type="textarea" v-model="form1.info" style="width: 400px"></el-input>
      </el-form-item>

      <el-form-item label="筛选成员">
        <el-select v-model="form1.departmentid" placeholder="请选择" @change="selectUser">
          <el-option :label="de.name" :value="de.id" v-for="de in list" :key="de.id"></el-option>
        </el-select>
      </el-form-item>

    <el-transfer
      v-model="form1.yesData"
      :props="{key: 'userid',label: 'name'}"
      :titles="['未选择', '已选择']"
      @change="handleChange"
      :data="userlist"
    ></el-transfer>

          <el-form-item style="margin-left:150px">
        <el-button type="primary" @click="onSubmit">发布项目</el-button>
      </el-form-item>
    </el-form>
    
  </div>
</template>
<script>
import userApi from "@/api/userinfo";
import projectApi from "@/api/project";
export default {
  data() {
    return {
        list:[],//部门列表
      userlist: [{
          userid:"",
          name:""
      }],//选中的员工列表
      form1: {
        name: "",
        info: "",
        yesData: []
      }
      
      // 注意:key 的字符类型要一致!!!
    };
  },
  created() {
    this.getDepartment();
  },
  methods: {
      onSubmit(){
    projectApi.creatProject(this.form1).then(response=>{
        this.$message.success(response.message);
        if(response.success){
            setTimeout(function () {
              window.location.href = "/project/allproject"; //1秒后刷新页面
            }, "1000");}
    })
      },
      //上面是发布项目的方法
    selectUser() {
      userApi.findUser(this.form1.departmentid).then((response) => {
        this.userlist = response.data.list;

      });
    },
    getDepartment() {
      userApi.getDepartment().then((response) => {
        this.list = response.data.list;
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
  },
};
</script>