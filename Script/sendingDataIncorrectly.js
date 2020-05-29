var requests = require('./http');

const INTERVAL = 500;
let intervals = [];

function start() {
  startSendingData(measurerMashTunMalt);
  startSendingData(measurerMashTunWater);
  startSendingData(measurerMashTun);
  startSendingData(measurerPreLaunterTun);
  startSendingData(measurerLaunterTun);
  startSendingData(measurerLaunterTunWater);
  startSendingData(measurerBrewKettleHops);
  startSendingData(measurerBrewKettle);
  startSendingData(measurerWhirlpool);
  startSendingData(measurerFermentationTank);
  startSendingData(measurerYeast);
  startSendingData(measurerConditioningTank);
}

function stop() {
  intervals.forEach(interval => {
    if (interval) {
      clearInterval(interval);
      interval = null;
    }
  })
}

function startSendingData(measurer, interval = INTERVAL) {
  let intervalSendingData = setInterval(() => {
    // console.log(measurer);
    requests.post('/action/measurer', measurer);
  }, interval); 

  intervals.push(intervalSendingData);
}

module.exports = {
  start: start,
  stop: stop
}