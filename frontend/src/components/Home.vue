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
           <v-row v-if="selectedTemplate.templateName === templatesConsts.PHASE_1_STOP_ADDING_WATER_TO_MASH_TUN">
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
          PHASE_1_STOP_ADDING_WATER_TO_MASH_TUN: "phase1-stop_adding_water_to_mash_tun"
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
