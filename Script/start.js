var requests = require('./http');
var sendingData = require('./sendingData');
var sendingDataIncorrectly = require('./sendingDataIncorrectly');

function start() {

  console.log("start");
  requests.post('/action/start').then(() => {
    console.log('finished');
    sendingData.stop();
    sendingDataIncorrectly.stop();
  });

  setTimeout(() => {
    if (mode === MODE.REGULAR) {
      sendingData.start();
    } else if (mode === MODE.INCORRECTLY) {
      sendingDataIncorrectly.start();
    }
  }, 1000);
}

module.exports = {
  start: start
}