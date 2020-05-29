var axios = require('axios');

axios.default.defaults.baseURL = "http://localhost:8080/api";

function post(url, data={}) {
  return axios.default.post(url, data);
}

module.exports = {
  post: post
}