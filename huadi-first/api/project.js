import request from '@/utils/request'
export default {
  delMemberById(id){//删除单个员工的记录
    return request({
      url: `/usermain/project-menberl-info/delMemberById/${id}`,
      method: 'get'
    })
  }
,
  getAllProjectDetail(current,size,quer){
    return request({
      url: `/usermain/project-menberl-info/getAllProjectDetail/${current}/${size}`,
      method: 'post',
      data: quer
    })
  }
  ,
  delById(id){//根据ID删除全部相关信息
    return request({
      url: `/usermain/project-menberl-info/delById/${id}`,
      method: 'get'
    })
  },
  checkProjectById(id){
    return request({
      url: `/usermain/project-menberl-info/checkProjectById/${id}`,
      method: 'get'
    })
  },
  creatProject(form1){
    return request({
      url: '/usermain/project/creatProject',
      method: 'post',
      data: form1
    })
  },
  //下面方法为查看全部项目记录
  getAllProject(current,size,quer){
    return request({
      url: `/usermain/project/getAllProject/${current}/${size}`,
      method: 'post',
      data: quer
    })
  },
  //下面方法为查询自己参与项目且未完成的记录
  getMyWaitProject(current,size,quer){
    return request({
      url: `/usermain/project-menberl-info/getMyWaitProject/${current}/${size}`,
      method: 'post',
      data: quer
    })
  },
  pass(id){
    return request({
      url: `/usermain/project-menberl-info/pass/${id}`,
      method: 'get'
    })
  },

  getMyPassProject(current,size,quer){
    return request({
      url: `/usermain/project-menberl-info/getMyPassProject/${current}/${size}`,
      method: 'post',
      data: quer
    })
  }

}