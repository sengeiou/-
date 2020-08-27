import request from '@/utils/request'
export default {
//处理审批
  dealApply(userid,result) {
    return request({
      url: `/usermain/m-apply-spend/dealApply/${userid}/${result}`,
      method: 'get'
    })
  },
  //发起申请
    moneyapply(member) {
    return request({
      url: '/usermain/m-apply-spend/moneyapply',
      method: 'post',
      data: member
    })
  },
  //获取我的申请记录
  getMyMoneyPay(current,size,queryvo) {
    return request({
      url: `/usermain/pay-salary/getMyMoneyPay/${current}/${size}`,
      method: 'post',
      data:queryvo
    })
  },
  //删除工资发放记录
  deleteById(id){
    return request({
      url: `/usermain/pay-salary/deleteById/${id}`,
      method: 'get'
    })
  },
  getAllMoneyPay(current,size,queryvo) {
    return request({
      url: `/usermain/pay-salary/getAllMoneyPay/${current}/${size}`,
      method: 'post',
      data:queryvo
    })
  },
  //获取全部用户工资发放记录

//获取全部待审批
  getWaitApply(current,size,queryvo){
    return request({
      url: `/usermain/m-apply-spend/getWaitApply/${current}/${size}`,
      method: 'post',
      data:queryvo
    })
  },
  //手动发工资，前端未写页面，此接口未调用
  paySalary(member) {
    return request({
      url: '/usermain/pay-salary/paySalary',
      method: 'post',
      data: member
    })
  },
  
}