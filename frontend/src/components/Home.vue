<template>
  <v-container>
    <v-row>
      <v-col>
        <v-select
          :items="templateList"
          v-model="selectedTemplate"
          label="Templates"
        ></v-select>
      </v-col>
    </v-row>
    <v-container>
        <v-form v-model="formValid"  @submit="formSubmit" onSubmit="return false;">
           <v-row v-if="selectedTemplate.templateName === 'phase1-stop_adding_water_to_mash_tun'">
            <v-col>
              <v-text-field
                name="water"
                label="Water"
                v-model="selectedTemplate.water"
                required
                type=""
                style="width: 100px"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>  
    </v-container>
   
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
        axios.put("http://localhost:8080/api/template", this.selectedTemplate)
        .then(() => {
          console.log('snackbar')
        })
        .catch(error => {
          console.log(error);
        })
      }
    }
  }
</script>
