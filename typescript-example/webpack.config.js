const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: "./src/ts/index.ts",
    output: {
        filename: "bundle.js",
        path: __dirname + '/build',
    },
    devtool: "source-map",
    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.json', '.kt'],
        modules: ['node_modules', 'kotlin_build']
    },
    module: {
        rules: [
            { test: /\.ts$/, use: 'ts-loader' }
        ]
    },
    devServer: {
        inline: true,
        port: 8080
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new HtmlWebpackPlugin({ title: "Inochi Webpack", template: "./src/index.html" })
    ]
};