var start = require('./src/start');
var socket = require('./src/socket');
require('./src/global');

const hostname = '127.0.0.1';
const port = 3001;


global.MODE = {
  INCORRECTLY: "INCORRECTLY",
  REGULAR: "REGULAR"
}
global.mode = MODE.REGULAR;

const express = require('express')
const app = express()
 
app.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
  socket.connect(start.start);
})
