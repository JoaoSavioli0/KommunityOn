<template>
    <section class="w-full h-screen bg-[#F0F4F9]">
        <div class="w-full h-full flex justify-center items-center flex-col">
            <transition-group name="move" tag="div" class="flex flex-col items-center w-full">
                <div key="divLogin"
                    class="w-full lg:w-[450px] rounded-[30px] bg-white p-8 flex flex-col justify-between shadow-sm transition-all duration-300">
                    <div class="w-full">
                        <div class="w-full flex">
                            <h1 class="text-zinc-800 font-medium"><span class="font-semibold">K</span>ommunity<span
                                    class="font-semibold">ON</span></h1>
                        </div>
                        <div class="w-full my-4 pl-[1px] text-left">
                            <h1 class="text-2xl font-semibold"><span class="font-normal">Faça</span> Login</h1>
                        </div>
                    </div>
                    <div class="w-full flex flex-col mt-6">
                        <div class="w-full relative">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">CPF ou E-mail</span>
                            <input type="text" v-model="cpfOuEmail"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Senha</span>
                            <input type="password" v-model="senha"
                                class="border-[2px] transition-all rounded-[10px] border-gray-800 py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-8">
                            <button @click.prevent="fazLogin()"
                                class="bg-gray-900 font-regular rounded-[10px] py-4 outline-none w-full text-white">Confirmar</button>
                            <p class="text-sm text-gray-800 mt-2">Esqueceu sua senha?
                                <router-link to="/recupera_senha">
                                    <span class="text-blue-600 font-medium cursor-pointer">
                                        Clique aqui
                                    </span>
                                </router-link>
                            </p>
                        </div>
                        <div class="w-full relative mt-6 flex">
                            <div class="h-[1px] bg-gray-300 w-full relative flex justify-center"><span
                                    class="absolute top-[-12px] bg-white px-2 font-medium">Ou</span></div>
                        </div>
                        <div class="w-full pt-8">
                            <router-link to="/cadastro" class="p-0">
                                <div class="w-full relative">
                                    <button
                                        class="border-2 text-gray-800 border-gray-800 font-regular rounded-[10px] py-6 outline-none w-full transition-all duration-300 overflow-hidden relative cadastrese-button flex justify-center items-center">
                                        <span class="cadastrese absolute transition-all duration-300">Cadastre-se</span>
                                        <span
                                            class="cadastrese2 absolute transition-all duration-300">Cadastre-se</span>
                                    </button>
                                </div>
                            </router-link>
                        </div>
                    </div>
                </div>

                <div key="divAviso"
                    class="mt-4 w-[90%] lg:w-[400px] py-4 rounded-[30px] bg-gray-900 p-8 flex justify-center items-center transition-all duration-300"
                    v-if="aviso != ''">
                    <p class="text-white">{{ aviso }}</p>
                </div>
            </transition-group>
        </div>
    </section>
</template>

<script>
import axios from 'axios';
import { useUserStore } from '@/stores/userStore';

export default {
    name: "Login",
    data() {
        return {
            loginObj: {
                cpfOuEmail: "",
                senha: ""
            },
            cpfOuEmail: '',
            senha: '',
            aviso: '',
            usuario: null
        }
    },
    mounted() {
        const userStore = useUserStore()
        userStore.reconectaSessao()
        if (userStore.usuario != null) {
            this.$router.push("/home")
        }
    },
    methods: {
        async fazLogin() {
            if (this.usuario == '' || this.senha == '') {
                this.aviso = 'Prencha os campos para realizar o login!'
                return;
            } else {
                try {
                    this.loginObj.cpfOuEmail = this.cpfOuEmail
                    this.loginObj.senha = this.senha
                    await useUserStore().login(this.loginObj)
                    if (useUserStore().usuario != null) {
                        this.$router.push("/home")
                    } else {
                        this.aviso = "Usuário ou senha incorreta."
                    }
                } catch (err) {
                    console.log(err)
                }
            }
            setTimeout(() => { this.aviso = '' }, 4000);
        }
    }
}
</script>

<style>
/* Entrada */
.move-enter-active,
.move-leave-active {
    transition: all 0.3s ease;
}

.move-enter-from {
    transform: translateY(20px);
    opacity: 0;
}

.move-enter-to {
    transform: translateY(0);
    opacity: 1;
}

/* Saída */
.move-leave-from {
    transform: translateY(0);
    opacity: 1;
}

.move-leave-to {
    transform: translateY(20px);
    opacity: 0;
}

.cadastrese2 {
    transform: translateY(60px);
}

.cadastrese-button:hover .cadastrese {
    transform: translateY(-60px);
}

.cadastrese-button:hover .cadastrese2 {
    transform: translateY(0px);
}

input:-webkit-autofill {
    appearance: none;
    /* Remove o estilo padrão */
    background-color: transparent !important;
    /* Define o fundo transparente */
    color: inherit !important;
    /* Herda a cor do texto */
    -webkit-text-fill-color: inherit;
    /* Herda a cor do texto preenchido */
    box-shadow: 0 0 0px 1000px transparent inset !important;
    /* Remove o fundo automático */
    transition: background-color 5000s ease-in-out 0s;
    /* Evita flash de cor padrão */
}

input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus {
    background-color: transparent !important;
    -webkit-text-fill-color: inherit !important;
    box-shadow: 0 0 0px 1000px transparent inset !important;
}
</style>