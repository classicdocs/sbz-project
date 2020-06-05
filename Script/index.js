const http = require('http');
var start = require('./start');
var socket = require('./socket');
require('./global');
var requests = require('./http');

const hostname = '127.0.0.1';
const port = 3000;


global.MODE = {
  INCORRECTLY: "INCORRECTLY",
  REGULAR: "REGULAR"
}
global.mode = MODE.REGULAR;

// const server = http.createServer((req, res) => {
//   res.statusCode = 200;
//   res.setHeader('Content-Type', 'text/plain');
//   res.end('Hello World');
// });

// server.listen(port, hostname, () => {
//   console.log(`Server running at http://${hostname}:${port}/`);
//   socket.connect(start.start);
// });


const express = require('express')
const app = express()
 
// app.post('/regular', function (req, res) {
//   const msg = "Switching mode to: " + MODE.REGULAR;
//   console.log(msg);
//   mode = MODE.REGULAR;
//   return res.send(msg);
// })

// app.post('/incorrectly', function (req, res) {
//   const msg = "Switching mode to: " + MODE.INCORRECTLY;
//   console.log(msg);
//   mode = MODE.INCORRECTLY;
//   return res.send(msg);
// })
 
app.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
  socket.connect(start.start);
})
