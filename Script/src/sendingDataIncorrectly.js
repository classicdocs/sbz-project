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

  let intervalSendingData = null;

  if (measurer.name === "MEASURER_LAUNTER_TUN") {
    let fixed = false;
    setTimeout(() => {
      fixed = true;
      console.log("MEASURER_LAUNTER_TUN FIXED");
    }, 30000)

    intervalSendingData = setInterval(() => {
      if (fixed) {
        requests.post('/action/measurer', measurer);
      }
    }, interval); 


  } else {
    intervalSendingData = setInterval(() => {
      requests.post('/action/measurer', measurer);
    }, interval); 
  
  }
  intervals.push(intervalSendingData);
}

module.exports = {
  start: start,
  stop: stop
}