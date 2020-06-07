<template>
  <v-container>
    <v-row>
      <v-col>
        <v-select
          :items="templateList"
          v-model="selectedTemplate"
          label="Templates"
          style="width: 500px"
        ></v-select>
      </v-col>
    </v-row>
        <v-form v-model="formValid"  onSubmit="return false;">
           <v-row>
            <v-col>
              <v-text-field
                v-for="item in selectedValues" :key="item.name"
                :name="item.name"
                :label="item.name"
                v-model="item.value"
                required
                type="number"
                style="width: 500px"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row >
            <v-col>
              <v-btn color="success" v-if="selectedTemplate"
                @click="formSubmit"
              >Submit</v-btn>
            </v-col>
          </v-row>
        </v-form>  
   <v-snackbar v-model="snackbar.model" :timeout="snackbar.timeout" :color="snackbar.color" top>
    {{snackbar.message}}
    <v-btn   @click.native="snackbar.model = false">Close</v-btn>
  </v-snackbar>
  </v-container>
</template>


<script>

import axios from 'axios'

  export default {
    name: 'Home',

    data: () => ({
        data: [],
        templateList: [],
        selectedTemplate: "",
        formValid: false,
        selectedValues: [],
        templatesConsts: {
          PHASE_2_STOP_ADDING_WATER_TO_MASH_TUN: "phase2-stop_adding_water_to_mash_tun",
          PHASE_2_STOP_ADDING_MALT_TO_MASH_TUN: "phase2-stop_adding_malt_to_mash_tun",
          PHASE_2_START_MASHING:"phase2-start_mashing",
          PHASE_3_STOP_RELEASING_MIXTURE: "phase3-stop_releasing_mixture",
          PHASE_4_STOP_RELEASING_MIXTURE_TO_LAUNTER_TUN: "phase4-stop_releasing_mixture_to_launter_tun",
          PHASE_4_STOP_ADDING_WATER_TO_LAUNTER_TUN: "phase4-stop_adding_water_to_launter_tun",
          PHASE_4_STOP_RELEASING_MIXTURE_TO_PRE_LAUNTER_TUN: "phase4-stop_releasing_mixture_to_pre_launter_tun",
          PHASE_5_STOP_RELEASING_HOPS_TO_BREW_KETTLE: "phase5-stop_releasing_hops_to_brew_kettle",
          PHASE_5_START_BOILING: "phase5-start_boiling",
          PHASE_6_START_SWIRLING: "phase6-start_swirling",
          PHASE_8_STOP_ADDING_YEAST: "phase8-stop_adding_yeast",
          PHASE_8_BEGIN_FERMENTATION: "phase8-begin_fermentation",
          PHASE_9_BEGIN_BEERS_CONTITIONING: "phase9-begin_beers_conditioning"
        },
        snackbar: {
          model: false,
          color: "success",
          timeout: 5000,
          message: ""
        }
    }),
    mounted () {
      axios.get('http://localhost:8080/api/template/all')
      .then(response => {
        this.data = response.data
        response.data.forEach(template => {
          this.templateList.push({
            text: template.ruleName,
            value: template
          })
          

        });
      })
    },
    methods: {
      formSubmit() {
        console.log('form submited');

        let ruleValues = "";
        this.selectedValues.forEach(val => {
          ruleValues += val.name + "=" + val.value + ";";
        });
        ruleValues = ruleValues.substr(0,ruleValues.length - 1); 
        
        let data = {...this.selectedTemplate, ruleValues};
        axios.put("http://localhost:8080/api/template", data)
        .then(() => {
          this.snackbar.message = "Rule successfuly changed!";
          this.snackbar.color = "success";
        })
        .catch(() => {
          this.snackbar.message = "Error! Please try again";
          this.snackbar.color = "error";
        }).finally(() => {
          this.snackbar.model = true;
        })
      }
    },
    watch: {
      selectedTemplate(newVal) {
        let values = [];
        let templateValues = newVal.ruleValues.split(';');
        templateValues.forEach(val => {
          values.push({
            name: val.split("=")[0],
            value: val.split("=")[1],
          })
        });

        this.selectedValues = values;
      }
    }
   
  }
</script>
