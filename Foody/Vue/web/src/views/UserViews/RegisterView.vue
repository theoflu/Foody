<script>
import axios from "axios";

export default {
  name: "RegisterView",

  data(){
    return{
      result:{},
      user:{userName:"",
        email:"",
        password: "",
        cellphone:"",
        address:""
      }
    }
  },
  created(){},
  mounted() {console.log("mounted() called ......");
  },methods:{RegisterData() {

      if(this.user.password!==this.$refs.againpsw.value || this.user.userName==="")
      {
        alert("Passwords Unmatch!")

      }
      else{
      (axios).post("http://localhost:8080/user/create",this.user)
          .then(({data})=>{console.log(data);
            console.log(this.user);
            try {
              if(this.user===null)
              {
                alert("HATA!")

              }
              else {

                this.$router.push({ name: 'login' });
                return alert("Registration is completed!");
              }
            }
            catch(err){
              alert("Error pls try again"+err);
            }


          })
    }
  }}


}

</script>
<template>
  <div class="container">
    <section class="vh-100" >
      <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-xl-9">

            <h1 class="text-dark mb-4" >Kayıt OL</h1>

            <div class="card" style="border-radius: 15px;">
              <div class="card-body" >
                <form @submit.prevent="RegisterData">
                <div class="row align-items-center pt-4 pb-3">

                  <div class="col-md-3 ps-5">

                    <h6 class="mb-0">Full name</h6>

                  </div>
                  <div class="col-md-9 pe-5">

                    <input type="text" v-model='user.userName' class="form-control form-control-lg" />

                  </div>
                </div>
                <hr class="mx-n3">
                <div class="row align-items-center py-3">
                  <div class="col-md-3 ps-5">

                    <h6 class="mb-0">Email address</h6>

                  </div>
                  <div class="col-md-9 pe-5">

                    <input v-model='user.email' type="email" class="form-control form-control-lg" placeholder="example@example.com" />

                  </div>
                </div>
                <hr class="mx-n3">
                <div class="row align-items-center py-3">
                  <div class="col-md-3 ps-5">

                    <h6 class="mb-0">Phone</h6>

                  </div>
                  <div class="col-md-9 pe-5">

                    <input v-model='user.cellphone' type="tel" class="form-control form-control-lg" placeholder="05__ ___ __ __" />

                  </div>
                </div>
                <hr class="mx-n3">
                <div class="row align-items-center py-3">
                  <div class="col-md-3 ps-5">

                    <h6 class="mb-0">Adress</h6>

                  </div>
                  <div class="col-md-9 pe-5">

                    <textarea class="form-control" v-model='user.address' rows="3" placeholder="Message sent to the employer"></textarea>

                  </div>
                </div>
                <hr class="mx-n3">
                <div class="row align-items-center py-3">
                  <div class="col-md-3 ps-5">

                    <h6 class="mb-0">Password</h6>

                  </div>
                  <div class="col-md-9 pe-5">

                    <input v-model='user.password' type="password" class="form-control form-control-lg" placeholder="" />

                  </div>
                </div>
                <hr class="mx-n3">
                <div class="row align-items-center py-3">
                  <div class="col-md-3 ps-5">

                    <h6 class="mb-0">Password(again)</h6>

                  </div>
                  <div class="col-md-9 pe-5">

                    <input ref="againpsw" type="password" class="form-control form-control-lg" placeholder="" />

                  </div>
                </div>
                <hr class="mx-n3">
                <div class="px-5 py-4">
                  <button  type="submit" class="btn btn-primary btn-lg">Kayıt Ol</button>
                </div>
                </form>
              </div>
            </div>

          </div>
        </div>
      </div>
    </section>
    </div>

</template>


<style scoped lang="scss">

</style>