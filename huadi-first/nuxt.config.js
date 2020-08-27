module.exports = {
    /*
  ** Headers of the page
  */
 head: {
  title: '安监总局信息管理平台',
  meta: [
    { charset: 'utf-8' },
    { name: 'viewport', content: 'width=device-width, initial-scale=1' },
    { name: 'keywords', content: '人脸登录，微信登录，手机号注册，邮箱注册' },
    { name: 'description', content: '该项目为404首发测试项目' }
  ],
  link: [
    { rel: 'icon', type: 'image/x-icon', href: 'https://huadi-test.oss-cn-beijing.aliyuncs.com/bitbug_favicon.ico' }
  ]
},
    /*
    ** Customize the progress bar color
    */
    loading: { color: '#3B8070' },
    /*
    ** Build configuration
    */
    build: {
    },
  
    plugins: [
      { src: '~/plugins/nuxt-swiper-plugin.js', ssr: false },
      { src: '~/plugins/element-ui-plugin.js', ssr: false },
      { src: '~/plugins/vue-qriously-plugin.js', ssr: false }
    ],
    css: [
      'swiper/swiper-bundle.css'
    ]

  }
  
  