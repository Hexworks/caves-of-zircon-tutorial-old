module.exports = [
  {
    changeOrigin: true,
    context: [
      '/api',
      '/users'
    ],
    target: 'http://localhost:30666',
    secure: false,
  }
];
