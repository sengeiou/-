import request from '@/utils/request'
export default {
    getBarSalary(){
    return request({
      url: `/usermain/project/admin/getPassPorjectByYear`,
      method: 'get'
    })
  }

}