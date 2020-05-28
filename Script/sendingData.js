var requests = require('./http');

const INTERVAL = 300;

function start() {
  startMeasurerMashTunMalt();
  startMeasurerMashTunWater();
  startMeasurerMashTun();
  startMeasurerPreLaunterTun();
  startMeasurerLaunterTun();
  startMeasurerLaunterTunWater();
  startMeasurerBrewKettleHops();
  startMeasurerBrewKettle();
  startMeasurerWhirlpool();
  startMeasurerYeast();
  startMeasurerFermentationTank();
  startMeasurerConditioningTank();
}

function startMeasurerMashTunMalt() {
  setInterval(() => {
    console.log(measurerMashTunMalt);
    requests.post('/action/measurer', measurerMashTunMalt);
  }, INTERVAL); 
}

function startMeasurerMashTunWater() {
  setInterval(() => {
    console.log(measurerMashTunWater);
    requests.post('/action/measurer', measurerMashTunWater);
  }, INTERVAL); 
}

function startMeasurerMashTun() {
  setInterval(() => {
    console.log(measurerMashTun);
    requests.post('/action/measurer', measurerMashTun);
  }, INTERVAL); 
}

function startMeasurerPreLaunterTun() {
  setInterval(() => {
    console.log(measurerPreLaunterTun);
    requests.post('/action/measurer', measurerPreLaunterTun);
  }, INTERVAL); 
}

function startMeasurerLaunterTun() {
  setInterval(() => {
    console.log(measurerLaunterTun);
    requests.post('/action/measurer', measurerLaunterTun);
  }, INTERVAL); 
}

function startMeasurerLaunterTunWater() {
  setInterval(() => {
    console.log(measurerLaunterTunWater);
    requests.post('/action/measurer', measurerLaunterTunWater);
  }, INTERVAL); 
}

function startMeasurerBrewKettleHops() {
  setInterval(() => {
    console.log(measurerBrewKettleHops);
    requests.post('/action/measurer', measurerBrewKettleHops);
  }, INTERVAL); 
}

function startMeasurerBrewKettle() {
  setInterval(() => {
    console.log(measurerBrewKettle);
    requests.post('/action/measurer', measurerBrewKettle);
  }, INTERVAL); 
}

function startMeasurerWhirlpool() {
  setInterval(() => {
    console.log(measurerWhirlpool);
    requests.post('/action/measurer', measurerWhirlpool);
  }, INTERVAL); 
}

function startMeasurerFermentationTank() {
  setInterval(() => {
    console.log(measurerFermentationTank);
    requests.post('/action/measurer', measurerFermentationTank);
  }, INTERVAL); 
}

function startMeasurerYeast() {
  setInterval(() => {
    console.log(measurerYeast);
    requests.post('/action/measurer', measurerYeast);
  }, INTERVAL); 
}

function startMeasurerConditioningTank() {
  setInterval(() => {
    console.log(measurerConditioningTank);
    requests.post('/action/measurer', measurerConditioningTank);
  }, INTERVAL); 
}

module.exports = {
  start: start 
}