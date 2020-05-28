
var Stomp = require('@stomp/stompjs');
var SockJS = require('sockjs-client');
var actions = require('./actions');

var stompClient = null;

function connect(start) {

    var socket = new SockJS('http://localhost:8080/websocket');
    stompClient = Stomp.Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic', function (message) {
          handleMessage(JSON.parse(message.body));
        });
        start();
    });
}


function handleMessage(message) {
  console.log(message);
  actions.handleMessage(message.message);
}

module.exports = {
  connect: connect
}

