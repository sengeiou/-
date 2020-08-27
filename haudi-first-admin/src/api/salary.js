import request from '@/utils/request'
export default {

    getSalaryType(quer){
        return request({
            url: `/usermain/pay-salary/admin/getSalaryType`,
            method: 'post',
            data: quer
          })
    },
    getBarSalary(){
    return request({
      url: `/usermain/pay-salary/admin/getBarSalary`,
      method: 'get'
    })
  }
,
getBarSalaryByYear(quer){
    return request({
      url: `/usermain/pay-salary/admin/getBarSalaryByYear`,
      method: 'post',
      data: quer
    })
  }
 

}