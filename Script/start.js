var requests = require('./http');
var sendingData = require('./sendingData');

function start() {

  console.log("start");
  requests.post('/action/start').then(() => {
    console.log('finished');
    sendingData.stop();
  });

  setTimeout(() => {
    sendingData.start();
  }, 1000);

}

module.exports = {
  start: start
}