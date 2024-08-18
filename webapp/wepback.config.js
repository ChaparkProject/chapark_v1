const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: ["@babel/polyfill", "./src/index.js"], // 확장자를 명시적으로 지정
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'index_bundle.js',
  },
  resolve: {
    alias: {
      '@API': path.resolve(__dirname, 'src/API'),
    },
    extensions: ['.js', '.jsx', '.json'],
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/, // .js와 .jsx 파일을 대상으로 설정
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env', '@babel/preset-react'],
            plugins: ['@babel/plugin-proposal-class-properties'],
          },
        },
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './index.html',
    }),
  ],
  devServer: {
    static: {
      directory: path.join(__dirname, 'public'), // 최신 버전의 Webpack에서는 contentBase 대신 static 사용
    },
    compress: true,
    port: 3000,
  },
  mode: 'development',
};
