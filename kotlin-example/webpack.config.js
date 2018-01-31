const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const KotlinWebpackPlugin = require('@jetbrains/kotlin-webpack-plugin');

module.exports = {
    entry: "kotlinApp",
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
            {
                test: /\.js$/,
                include: path.resolve(__dirname, '../kotlin_build'),
                exclude: [/kotlin\.js$/],
                use: ['source-map-loader'],
                enforce: 'pre',
            },
        ]
    },
    devServer: {
        inline: true,
        port: 8080
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new KotlinWebpackPlugin({ src: __dirname + '/src/kotlin' }),
        new HtmlWebpackPlugin({ title: "Inochi Webpack", template: "./src/index.html" })
    ]
};