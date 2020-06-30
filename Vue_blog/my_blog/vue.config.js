module.exports = {
  // outputDir: 'admin',
  configureWebpack: {
    resolve: {
      alias: {
        'assets': '@/assets',
        'utils': '@/utils',
        'components': '@/components',
        'views': '@/views',
        'network': '@/network',
        'store': '@/store',
        'common': '@/common'
      }
    },
    externals: {
      /*         vue: 'Vue',
              'vue-router': 'VueRouter', */
      'semantic': 'Semantic',
      'jquery': '$',
    },
    module: {
      rules: [{
        test: /\.(eot|svg|ttf|woff2)(\?\S*)?$/,
        loader: 'file-loader'
      }]
    }
  },
  devServer: {
    proxy: {
      '/': {
        target: 'http://xxx', //源地址
        changeOrigin: true, //改变源
        pathRewrite: {
          '^/api': 'http://xxx' //路径重写
        } 
      }
    }
  }
}