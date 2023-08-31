<script type="ts">
import axios from "axios";
import {reactive} from "vue";
import {useRouter} from "vue-router";

export default {
  name: "LoginView",
  setup(){
    const data=reactive ({
      username:"",
      password: ""
    });
    const router =useRouter();
    const submit=async ()=>{
      const response=await axios.post("http://localhost:8080/user/login",data,{withCredentials:true});

      axios.defaults.headers.common["Authorization"]=`Bearer ${response.data.token}`
      await router.push("/product")
    }
    return{
      data,
      submit
    }
  },

  /**
   data(){
   return{
   result:{},
   user:{username:"",
   password: ""
   }
   }
   },
   created(){},
   mounted() {console.log("mounted() called ......");
   },methods:{LoginData() {
   (axios).post("http://localhost:8080/user/login",this.user)
   .then(response => {


   if (response.data.token) {
   localStorage.setItem('user', JSON.stringify(response.data));
   }

   console.log(localStorage.getItem("user"));
   this.$router.push("/product")

   });
   }}
   */

}

</script>

<template>
  <div class="container">
    <div class="content">
      <div class="row">
        <div class="col-md-12 col-lg-12">
          <div class="col-md-4">
            <form @submit.prevent="submit">
              <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input v-model='data.username' type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else. valla :)</small>
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input  v-model='data.password' type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
              </div>
              <div class="form-check">
                <input  type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
              </div>

              <button type="submit" class="btn btn-primary">Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>

  </div>

</template>

<style scoped lang="scss">

</style>