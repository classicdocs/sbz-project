const ActionType = {
  ADD_MALT_TO_MASH_TUN: "ADD_MALT_TO_MASH_TUN", // phase 1
  STOP_ADDING_MALT_TO_MASH_TUN: "STOP_ADDING_MALT_TO_MASH_TUN", // phase 2
  ADD_WATER_TO_MASH_TUN: "ADD_WATER_TO_MASH_TUN", // phase 2
  STOP_ADDING_WATER_TO_MASH_TUN: "STOP_ADDING_WATER_TO_MASH_TUN", // phase 2
  RELEASE_MIXTURE_TO_PRE_LAUNTER_TUN: "RELEASE_MIXTURE_TO_PRE_LAUNTER_TUN", //phase 2
  STOP_RELEASING_MIXTURE_TO_PRE_LAUNTER_TUN: "STOP_RELEASING_MIXTURE_TO_PRE_LAUNTER_TUN", // phase 3
  RELEASE_MIXTURE_TO_LAUNTER_TUN: "RELEASE_MIXTURE_TO_LAUNTER_TUN", // phase 3
  STOP_RELEASING_MIXTURE_TO_LAUNTER_TUN: "STOP_RELEASING_MIXTURE_TO_LAUNTER_TUN", // phase 4
  ADD_WATER_TO_LAUNTER_TUN: "ADD_WATER_TO_LAUNTER_TUN", // phase 4
  STOP_ADDING_WATER_TO_LAUNTER_TUN: "STOP_ADDING_WATER_TO_LAUNTER_TUN", // phase 4
  RELEASE_BACK_MIXTURE_TO_PRE_LAUNTER_TUN: "RELEASE_BACK_MIXTURE_TO_PRE_LAUNTER_TUN", //phase 4
  RELEASE_MIXTURE_TO_PRE_LAUNTER_TUN_SECOND_TIME: "RELEASE_MIXTURE_TO_PRE_LAUNTER_TUN_SECOND_TIME", // phase 4
  STOP_RELEASING_BACK_MIXTURE_TO_PRE_LAUNTER_TUN: "STOP_RELEASING_BACK_MIXTURE_TO_PRE_LAUNTER_TUN", //phase 4
  STOP_RELEASING_MIXTURE_TO_PRE_LAUNTER_TUN_SECOND_TIME: "STOP_RELEASING_MIXTURE_TO_PRE_LAUNTER_TUN_SECOND_TIME", //phase 3
  RELEASE_MIXTURE_TO_LAUNTER_TUN_SECOND_TIME: "RELEASE_MIXTURE_TO_LAUNTER_TUN_SECOND_TIME", //phase 3
  STOP_RELEASING_BACK_MIXTURE_TO_LAUNTER_TUN_SECOND_TIME: "STOP_RELEASING_BACK_MIXTURE_TO_LAUNTER_TUN_SECOND_TIME", // phase 4
  RELEASE_WORT_TO_BREW_KETTLE: "RELEASE_WORT_TO_BREW_KETTLE", // phase 4
  ADD_HOPS_TO_BREW_KETTLE: "ADD_HOPS_TO_BREW_KETTLE", // phase 4
  STOP_RELEASING_WORT_TO_BREW_KETTLE: "STOP_RELEASING_WORT_TO_BREW_KETTLE", //phase 4
  STOP_ADDING_HOPS_TO_BREW_KETTLE: "STOP_ADDING_HOPS_TO_BREW_KETTLE", // phase 5
  RELEASE_WORT_TO_WHIRLPOOL: "RELEASE_WORT_TO_WHIRLPOOL", // phase 5
  STOP_RELEASING_WORT_TO_WHIRLPOOL: "STOP_RELEASING_WORT_TO_WHIRLPOOL", // phase 5
  RELEASE_WORT_TO_COOLING_MEDIUM: "RELEASE_WORT_TO_COOLING_MEDIUM", // phase 7
  STOP_RELEASING_WORT_TO_COOLING_MEDIUM: "STOP_RELEASING_WORT_TO_COOLING_MEDIUM", // phase 7
  ADD_YEAST: "ADD_YEAST", // phase 8
  STOP_ADDING_YEAST: "STOP_ADDING_YEAST", // phase 8
  RELEASE_BEER_TO_CONDITIONING_TANK: "RELEASE_BEER_TO_CONDITIONING_TANK", // phase 8
  STOP_RELEASING_BEER_TO_CONDITIONING_TANK: "STOP_RELEASING_BEER_TO_CONDITIONING_TANK", // phase 8
  RELEASE_BEER_TO_FILTERING_MEDIUM: "RELEASE_BEER_TO_FILTERING_MEDIUM", // phase 9
  STOP_RELEASING_BEER_TO_FILTERING_MEDIUM: "STOP_RELEASING_BEER_TO_FILTERING_MEDIUM", // phase 9
}

const INTERVAL = 600;
const VALUE = 5;

function handleMessage(actionType) {
  switch(actionType) {
    case ActionType.ADD_MALT_TO_MASH_TUN: {
      addMaltToMashTun();
      break;
    }
    case ActionType.STOP_ADDING_MALT_TO_MASH_TUN: {
      stopAddingMaltToMashTun();
      break;
    }
    case ActionType.ADD_WATER_TO_MASH_TUN: {
      addWaterToMashTun();
      break;
    }
    case ActionType.STOP_ADDING_WATER_TO_MASH_TUN: {
      stopAddingWaterToMashTun();
      break;
    }
    case ActionType.RELEASE_MIXTURE_TO_PRE_LAUNTER_TUN: {
      releaseMixtureToPreLaunterTun();
      break;
    }
    case ActionType.STOP_RELEASING_MIXTURE_TO_PRE_LAUNTER_TUN: {
      stopReleasingMixtureToPreLaunterTun();
      break;
    }
    case ActionType.RELEASE_MIXTURE_TO_LAUNTER_TUN: {
      releaseMixtureToLaunterTun();
      break;
    }
    case ActionType.STOP_RELEASING_MIXTURE_TO_LAUNTER_TUN: {
      stopReleasingMixtureToLaunterTun();
      break;
    }
    case ActionType.ADD_WATER_TO_LAUNTER_TUN: {
      addWaterToLaunterTun();
      break;
    }
    case ActionType.STOP_ADDING_WATER_TO_LAUNTER_TUN: {
      stopAddingWaterToLaunterTun();
      break;
    }
    case ActionType.RELEASE_BACK_MIXTURE_TO_PRE_LAUNTER_TUN: {
      releaseBackMixtureToPreLaunterTun();
      break;
    }
    case ActionType.RELEASE_MIXTURE_TO_PRE_LAUNTER_TUN_SECOND_TIME: {
      releaseMixtureToPreLaunterTunSecondTime();
      break;
    }
    case ActionType.STOP_RELEASING_BACK_MIXTURE_TO_LAUNTER_TUN_SECOND_TIME: {
      stopReleasingMixtureToLaunterTunSecondTime();
    }
    case ActionType.STOP_RELEASING_BACK_MIXTURE_TO_PRE_LAUNTER_TUN: {
      stopReleasingBackMixtureToPreLaunterTun();
      break;
    }
    case ActionType.STOP_RELEASING_MIXTURE_TO_PRE_LAUNTER_TUN_SECOND_TIME: {
      stopReleasingMixtureToPreLaunterTunSecondTime();
      break;
    }
    case ActionType.RELEASE_MIXTURE_TO_LAUNTER_TUN_SECOND_TIME: {
      releaseMixtureToLaunterTunSecondTime();
      break;
    }
    case ActionType.RELEASE_WORT_TO_BREW_KETTLE: {
      releaseWortToBrewKettle();
      break;
    }
    case ActionType.STOP_RELEASING_WORT_TO_BREW_KETTLE: {
      stopReleasingWortToBrewKettle();
      break;
    }
    case ActionType.ADD_HOPS_TO_BREW_KETTLE: {
      addHopsToBrewKettle();
      break;
    }
    case ActionType.STOP_ADDING_HOPS_TO_BREW_KETTLE: {
      stopAddingHopsToBrewKettle();
      break;
    }
    case ActionType.RELEASE_WORT_TO_WHIRLPOOL: {
      releaseWortToWhirlpool();
      break;
    }
    case ActionType.STOP_RELEASING_WORT_TO_WHIRLPOOL: {
      stopReleasingWortToWhirlpool();
      break;
    }
    case ActionType.RELEASE_WORT_TO_COOLING_MEDIUM: {
      releaseWortToCoolingMedium();
      break;
    }
    case ActionType.STOP_RELEASING_WORT_TO_COOLING_MEDIUM: {
      stopReleasingWortToCoolingMedium();
      break;
    }
    case ActionType.ADD_YEAST: {
      addYeast();
      break;
    }
    case ActionType.STOP_ADDING_YEAST: {
      stopAddingYeast();
      break;
    }
    case ActionType.RELEASE_BEER_TO_CONDITIONING_TANK: {
      releaseBeerToConditioningTank();
      break;
    }
    case ActionType.STOP_RELEASING_BEER_TO_CONDITIONING_TANK: {
      stopReleasingBeerToConditioningTank();
      break;
    }
    case ActionType.RELEASE_BEER_TO_FILTERING_MEDIUM: {
      releaseBeerToFilteringMedium();
      break;
    }
    case ActionType.STOP_RELEASING_BEER_TO_FILTERING_MEDIUM: {
      stopReleasingBeerToFilteringMedium();
      break;
    }
  }
}

let intervalMashTunMalt = null;

function addMaltToMashTun() {
  intervalMashTunMalt = setInterval(() => {
    measurerMashTunMalt.value += 5;
    measurerMashTun.value += 5;
  }, INTERVAL)
}

function stopAddingMaltToMashTun() {
  if (intervalMashTunMalt != null) {
    clearInterval(intervalMashTunMalt);
    intervalMashTunMalt = null;
  }
}

let intervalMashTunWater = null;

function addWaterToMashTun() {
  intervalMashTunWater = setInterval(() => {
    measurerMashTunWater.value += VALUE;
    measurerMashTun.value += VALUE;
  }, INTERVAL)
}

function stopAddingWaterToMashTun() {
  if (intervalMashTunWater != null) {
    clearInterval(intervalMashTunWater);
    intervalMashTunWater = null;
  }
}

let intervalMashTun = null;

function releaseMixtureToPreLaunterTun() {
  measurerMashTunMalt.value = 0;
  measurerMashTunWater.value = 0;
  intervalMashTun = setInterval(() => {
    measurerMashTun.value -= VALUE;
    measurerPreLaunterTun.value += VALUE;
  }, INTERVAL)
}

function stopReleasingMixtureToPreLaunterTun() {
  if (intervalMashTun != null) {
    clearInterval(intervalMashTun);
    intervalMashTun = null;
  }
}

let intervalPreLaunterTun = null;

function releaseMixtureToLaunterTun() {
  intervalPreLaunterTun = setInterval(() => {
    measurerPreLaunterTun.value -= VALUE;
    measurerLaunterTun.value += VALUE;
  }, INTERVAL)
}

function stopReleasingMixtureToLaunterTun() {
  if (intervalPreLaunterTun != null) {
    clearInterval(intervalPreLaunterTun);
    intervalPreLaunterTun = null;
  }
}

let intervalLaunterTunWater = null;

function addWaterToLaunterTun() {
  intervalLaunterTunWater = setInterval(() => {
    measurerLaunterTunWater.value += VALUE;
    measurerLaunterTun.value += VALUE;
  }, INTERVAL)
}

function stopAddingWaterToLaunterTun() {
  if (intervalLaunterTunWater != null) {
    clearInterval(intervalLaunterTunWater);
    intervalLaunterTunWater = null;
  }
}

let intervalBackMixtureToPreLaunterTun = null;

function releaseBackMixtureToPreLaunterTun() {
  measurerLaunterTunWater.value = 0;
  intervalBackMixtureToPreLaunterTun = setInterval(() => {
    measurerLaunterTun.value -= VALUE;
    measurerPreLaunterTun.value += VALUE;
  }, INTERVAL)
}

function stopReleasingBackMixtureToPreLaunterTun() {
  if (intervalBackMixtureToPreLaunterTun != null) {
    clearInterval(intervalBackMixtureToPreLaunterTun);
    intervalBackMixtureToPreLaunterTun = null;
  }
}

let intervalMixtureToPreLaunterTunSecondTime = null;

function releaseMixtureToPreLaunterTunSecondTime() {
  intervalMixtureToPreLaunterTunSecondTime = setInterval(() => {
    measurerMashTun.value -= VALUE;
    measurerPreLaunterTun.value += VALUE;
  }, INTERVAL)
}

function stopReleasingMixtureToPreLaunterTunSecondTime() {
  if (intervalMixtureToPreLaunterTunSecondTime != null) {
    clearInterval(intervalMixtureToPreLaunterTunSecondTime);
    intervalMixtureToPreLaunterTunSecondTime = null;
  }
}

let intervalMixtureToLaunterTunSecondTime = null;

function releaseMixtureToLaunterTunSecondTime() {
  intervalMixtureToLaunterTunSecondTime = setInterval(() => {
    measurerPreLaunterTun.value -= VALUE;
    measurerLaunterTun.value += VALUE;
  }, INTERVAL)
}

function stopReleasingMixtureToLaunterTunSecondTime() {
  if (intervalMixtureToLaunterTunSecondTime != null) {
    clearInterval(intervalMixtureToLaunterTunSecondTime);
    intervalMixtureToLaunterTunSecondTime = null;
  }
}

let intervalRelaseWortToBrewKettle = null;

function releaseWortToBrewKettle() {
  intervalRelaseWortToBrewKettle = setInterval(() => {
    measurerLaunterTun.value -= VALUE;
    measurerBrewKettle.value += VALUE;
  }, INTERVAL)
}

function stopReleasingWortToBrewKettle() {
  if (intervalRelaseWortToBrewKettle != null) {
    clearInterval(intervalRelaseWortToBrewKettle);
    intervalRelaseWortToBrewKettle = null;
  }
}

let intervalAddHopsToBrewKettle = null;

function addHopsToBrewKettle() {
  intervalAddHopsToBrewKettle = setInterval(() => {
    measurerBrewKettleHops.value += VALUE;
    measurerBrewKettle.value += VALUE;
  }, INTERVAL)
}

function stopAddingHopsToBrewKettle() {
  if (intervalAddHopsToBrewKettle != null) {
    clearInterval(intervalAddHopsToBrewKettle);
    intervalAddHopsToBrewKettle = null;
  }
}

let intervalReleaseWortToWhirlpool = null;

function releaseWortToWhirlpool() {
  measurerBrewKettleHops.value = 0;
  intervalReleaseWortToWhirlpool = setInterval(() => {
    measurerBrewKettle.value -= VALUE;
    measurerWhirlpool.value += VALUE;
  }, INTERVAL)
}

function stopReleasingWortToWhirlpool() {
  if (intervalReleaseWortToWhirlpool != null) {
    clearInterval(intervalReleaseWortToWhirlpool);
    intervalReleaseWortToWhirlpool = null;
  }
}

let intervalReleaseWortToCoolingMedium = null;

function releaseWortToCoolingMedium() {
  intervalReleaseWortToCoolingMedium = setInterval(() => {
    measurerWhirlpool.value -= VALUE;
  }, INTERVAL)
}

function stopReleasingWortToCoolingMedium() {
  if (intervalReleaseWortToCoolingMedium != null) {
    clearInterval(intervalReleaseWortToCoolingMedium);
    intervalReleaseWortToCoolingMedium = null;
  }
}

let intervalAddingYeast = null;

function addYeast() {
  intervalAddingYeast = setInterval(() => {
    measurerYeast.value += VALUE;
    measurerFermentationTank.value += VALUE;
  }, INTERVAL)
}

function stopAddingYeast() {
  if (intervalAddingYeast != null) {
    clearInterval(intervalAddingYeast);
    intervalAddingYeast = null;
  }
}

let intervalReleaseBeerToConditioningTank = null;

function releaseBeerToConditioningTank() {
  measurerYeast.value = 0;
  intervalReleaseBeerToConditioningTank = setInterval(() => {
    measurerFermentationTank.value -= VALUE;
    measurerConditioningTank.value += VALUE;
  }, INTERVAL)
}

function stopReleasingBeerToConditioningTank() {
  if (intervalReleaseBeerToConditioningTank != null) {
    clearInterval(intervalReleaseBeerToConditioningTank);
    intervalReleaseBeerToConditioningTank = null;
  }
}

let intervalReleaseBeerToFilteringMedium = null;

function releaseBeerToFilteringMedium() {
  intervalReleaseBeerToFilteringMedium = setInterval(() => {
    measurerConditioningTank.value -= VALUE;
  }, INTERVAL)
}

function stopReleasingBeerToFilteringMedium() {
  if (intervalReleaseBeerToFilteringMedium != null) {
    clearInterval(intervalReleaseBeerToFilteringMedium);
    intervalReleaseBeerToFilteringMedium = null;
  }
}

module.exports = {
  handleMessage: handleMessage
}