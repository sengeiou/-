import request from '@/utils/request'
export default {
    getAllUser(page,size,userquery){
    return request({
      url: `/usermain/admin/user/getAllUser/${page}/${size}`,
      method: 'post',
      data:userquery
    })
  },
  lock(id){
    return request({
      url: `/usermain/admin/user/lock/${id}`,
      method: 'get'
    })
  },
  unlock(id){
    return request({
      url: `/usermain/admin/user/unlock/${id}`,
      method: 'get'
    })
  }

}