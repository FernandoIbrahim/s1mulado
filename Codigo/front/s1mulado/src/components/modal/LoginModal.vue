<template>
  <div>
    <div class="fixed inset-0 flex items-center justify-center bg-gray-500 hover:bg-sky-100/10 z-10 backdrop-blur-xs">
      <div class="bg-white p-6 rounded-lg shadow-lg w-[400px] ">
        <div class = "w-full h-fit flex justify-end p-2 ">
          <div class="w-9 h-9 flex">
            <ButtonComponent @click="handleExitModal">X</ButtonComponent> 
          </div>
        </div>

        <Tabs default-value="Login" class="pt-4">
          <TabsList class="flex space-x-2 border-b">
            <TabsTrigger value="Login" class="p-2">Login</TabsTrigger>
            <TabsTrigger value="Registrar" class="p-2">Registrar</TabsTrigger>
          </TabsList>
          <TabsContent value="Login" class="p-4">
            <ShadIput type="text" placeholder="username" v-model="username" class=" mt-4" />
            <ShadIput type="text" placeholder="senha" v-model="password" class=" mt-8"/>
            <ButtonComponent class=" mt-8" @click="handleLogin">Entrar</ButtonComponent> 
          </TabsContent>
          <TabsContent value="Registrar" class="p-4">
            <ShadIput type="text" placeholder="username" v-model="username" class=" mt-4" />
            <ShadIput type="text" placeholder="e-mail" v-model="email" class=" mt-8"/>
            <ShadIput type="text" placeholder="celular" v-model="phoneNumber" class=" mt-8"/>
            <ShadIput type="text" placeholder="senha" v-model="password" class=" mt-8"/>
            <ButtonComponent class=" mt-8" @click="handleRegister">Criar conta</ButtonComponent> 
          </TabsContent>
        </Tabs>
      </div>
    </div>
  </div>
</template>


<script>
import Cookies from 'js-cookie';

import { useLoginModal } from '@/stores/modals.js'

import { useToast } from 'vue-toastification'

import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import { Input as ShadIput} from '@/components/ui/input'
import  ButtonComponent  from '@/components/common/ButtonComponent.vue'
import { login, register } from '@/services/authService.js'
import CreateTestComponent from '@/components/test/CreateTestComponent.vue';


export default{
  name: 'LoginModal',
  data(){
    return {

      username: '',
      password: '',
      phoneNumber: '',
      email: '',
      loginModalStore: useLoginModal(),


      toast: useToast()

    }
  },
  components: {
    Tabs, TabsContent, TabsList, TabsTrigger, ShadIput, ButtonComponent
  },
  methods: {

    async handleLogin() {
      try {

        console.log(this.username);
        console.log(this.password);
        if (!(this.username) || !(this.password)) {
          console.log("Preencha todos os campos")
          return;
        }

        const response = await login({username:this.username, password:this.password});
      
        console.log('Login realizado:', response.data);

        Cookies.set('jwt', response.data.token, {
          expires: 1, 
          secure: true, 
          sameSite: 'None' 
        });

        this.toast.success('Bem vindo!')
        
        this.$emit("login-closed");
        this.loginModalStore.close()
        

      } catch (error) {
        console.log(error);
        this.toast.error('Erro ao realizar login');
      }


    },


    async handleRegister() {

      try {

        console.log(this.username);
        console.log(this.password);
        console.log(this.email);
        console.log(this.phoneNumber);

        if (!(this.username) || !(this.password) || !(this.phoneNumber) || (!this.email)) {
          console.log("Preencha todos os campos")
          return;
        }

        const response = await register({username:this.username, password:this.password, email: this.email, phoneNumber:this.phoneNumber});
      
        console.log('Registro realizado:', response.data);

        this.loginModalStore.close()
        router.go(0)

      } catch (error) {

        console.log(error);
        
      }


    },
    
    async handleExitModal(){

      this.loginModalStore.close();

    }


  }

}
</script>