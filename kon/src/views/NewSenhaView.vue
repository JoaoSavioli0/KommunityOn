<template>
    <section class="w-full h-full bg-[#F0F4F9] py-8 lg:py-20">

        <dialog id="my_modal_1" ref="dialog" class="modal">
            <div class="modal-box">
                <h3 class="text-lg font-medium">Senha alterada <span class="font-semibold">com sucesso!</span></h3>
                <div class="modal-action w-full flex justify-center">
                    <form method="dialog">
                        <button class="btn bg-gray-900 text-zinc-50 py-[3px] w-[100px] rounded-full"
                            @click="exit()">Ok</button>
                    </form>
                </div>
            </div>
        </dialog>

        <div class="w-full w-[550px] h-full flex justify-center items-center flex-col">
            <transition-group name="move" tag="div" class="flex flex-col w-full items-center">
                <div key="divLogin"
                    class="w-full lg:w-[450px] rounded-[30px] bg-white p-8 flex flex-col justify-between shadow-sm transition-all duration-300">
                    <div class="w-full">
                        <div class="w-full flex">
                            <router-link to="/menu/conta" class="p-0 z-[150]">
                                <button @click=""
                                    class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer ml-auto">
                                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                                    <span class="ml-2 text-xl">Voltar</span>
                                </button>
                            </router-link>
                        </div>
                        <div class="w-full my-4 pl-[1px] text-left">
                            <h1 class="text-2xl font-semibold"><span class="font-normal">Alteração de</span> Senha</h1>
                        </div>
                    </div>
                    <div class="w-full flex flex-col mt-6">


                        <!-- Senha antiga -->
                        <div class="w-full relative">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Senha atual</span>
                            <input :type="[senhaAtualVisivel ? 'text' : 'password']" v-model="senhaAtual"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                            <div class="absolute end-[15px] top-[12px] flex items-center" v-if="senhaAtual.length > 0">

                                <img src="../assets/closed-eye.svg"
                                    class="size-[20px] mr-2 filtro-gray-800 cursor-pointer"
                                    @click="senhaAtualVisivel = true" v-if="!senhaAtualVisivel">

                                <img src="../assets/eye.svg" class="size-[20px] mr-2 filtro-gray-800 cursor-pointer"
                                    @click="senhaAtualVisivel = false" v-if="senhaAtualVisivel">

                            </div>
                        </div>


                        <!-- Nova senha -->
                        <div class="w-full relative mt-12">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Nova senha</span>
                            <input :type="senhaVisivel ? 'text' : 'password'" v-model="novaSenha" maxlength="25"
                                :class="{ 'border-red-500': erroSenha }"
                                class="border-[2px] transition-all rounded-[10px] border-gray-800 py-2 px-4 outline-none w-full">
                            <div class="absolute end-[15px] top-[12px] flex items-center" v-if="novaSenha.length > 0">

                                <img src="../assets/closed-eye.svg"
                                    class="size-[20px] mr-2 filtro-gray-800 cursor-pointer" @click="senhaVisivel = true"
                                    v-if="!senhaVisivel">

                                <img src="../assets/eye.svg" class="size-[20px] mr-2 filtro-gray-800 cursor-pointer"
                                    @click="senhaVisivel = false" v-if="senhaVisivel">

                                <span class="border-l-[1px] border-gray-400 pl-2 font-normal text-sm">{{
                                    25 - novaSenha.length
                                }}</span>

                            </div>
                        </div>


                        <!-- Avisos de erro -->
                        <div class="w-full">
                            <ul class="w-full text-xs text-gray-700 text-left"
                                :class="([senhaAviso.length > 0 ? 'p-2' : 'p-0'])">
                                <li v-for="aviso in senhaAviso">{{ aviso }}</li>
                            </ul>
                        </div>


                        <!-- Confirma nova senha -->
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Confirme a nova
                                senha</span>
                            <input :type="senhaConfirmVisivel ? 'text' : 'password'" v-model="novaSenhaConfirma"
                                maxlength="25" :class="[
                                    !novaSenha ? 'border-gray-300' : '',
                                    erroSenhaConfirm && novaSenha ? 'border-red-500' : '',
                                    novaSenha && !erroSenhaConfirm ? 'border-gray-800' : '']"
                                :disabled="novaSenha.length == 0"
                                class="border-[2px] transition-all duration-200 rounded-[10px] py-2 px-4 outline-none w-full">

                            <div class="absolute end-[15px] top-[12px] flex items-center"
                                v-if="novaSenhaConfirma.length > 0">
                                <img src="../assets/closed-eye.svg" class="size-[20px] filtro-gray-800 cursor-pointer"
                                    @click="senhaConfirmVisivel = true" v-if="!senhaConfirmVisivel">

                                <img src="../assets/eye.svg" class="size-[20px] filtro-gray-800 cursor-pointer"
                                    @click="senhaConfirmVisivel = false" v-if="senhaConfirmVisivel">
                            </div>

                            <span v-if="!senhaIgual && novaSenha"
                                class="text-red-500 p-2 text-left w-full text-xs block">As
                                senhas
                                não
                                conferem</span>
                        </div>

                        <div class="w-full relative mt-8">
                            <button @click.prevent="alteraSenha()"
                                class="bg-gray-900 font-regular rounded-[10px] py-4 outline-none w-full text-white">Alterar</button>
                            <p class="text-sm text-gray-800 mt-2">Esqueceu sua senha?
                                <router-link to="/recupera_senha">
                                    <span class="text-blue-600 font-medium cursor-pointer">
                                        Clique aqui
                                    </span>
                                </router-link>
                            </p>
                        </div>
                    </div>
                </div>

                <div key="divAviso"
                    class="mt-4 w-[90%] lg:w-[400px] py-4 rounded-[30px] bg-gray-900 p-8 flex justify-center items-center transition-all duration-300"
                    v-if="aviso">
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
    name: "NewSenha",
    data() {
        return {
            senhaAtual: '',
            novaSenha: '',
            novaSenhaConfirma: '',
            aviso: '',
            senhaAviso: [],
            senhaIgual: true,
            senhaVisivel: false,
            senhaConfirmVisivel: false,
            senhaAtualVisivel: false,
            usuario: {},
            dialogOpen: false
        }
    },
    mounted() {
        useUserStore().reconectaSessao()
        if (useUserStore().usuario == null) {
            this.$router.push("/login")
        } else {
            this.usuario = useUserStore().usuario
        }
    },
    methods: {
        exit() {
            this.dialogOpen = false
            this.$router.push("/menu/conta")
        },
        verificaSenha() {
            this.senhaAviso = []
            if (this.novaSenha.length > 0) {
                if (this.novaSenha.length < 8) this.senhaAviso.push("- Pelo menos 8 dígitos")
                if (this.novaSenha.toLowerCase === this.novaSenha) this.senhaAviso.push("- Pelo menos um caractere maiúsculo")
                if (!(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(this.novaSenha))) this.senhaAviso.push("- Pelo menos 1 caractere especial")
                if (!(/[abcdefghijklmnopqrstuvwxyz]/.test(this.novaSenha.toLowerCase))) this.senhaAviso.push("- Pelo menos 1 caractere")
                if (!(/[1234567890]/.test(this.novaSenha))) this.senhaAviso.push("- Pelo menos 1 número")
            }
        },
        resetaForm() {
            this.senhaAtual = ""
            this.novaSenha = ""
            this.novaSenhaConfirma = ""
        },
        async alteraSenha() {
            if (this.senhaAtual != this.usuario.senha) {
                this.aviso = "Senha incorreta"
                this.resetaForm()
                return
            } else {
                try {
                    const response = await axios.post(`http://localhost:5000/usuario/senha/alteracao?novaSenha=${this.novaSenha}&id=${this.usuario.id}`)
                    this.resetaForm()
                    this.$refs.dialog.showModal()
                } catch (error) {
                    this.aviso = "Erro: " + error
                }
            }

        }
    },
    watch: {
        novaSenha() {
            this.verificaSenha();
            if (this.novaSenha.length > 0) {
                this.senhaIgual = this.novaSenha === this.novaSenhaConfirma;
            }
            if (this.novaSenha.length == 0) {
                this.novaSenhaConfirma = ''
            }
        },
        novaSenhaConfirma() {
            this.senhaIgual = this.novaSenha === this.novaSenhaConfirma;
        },
        aviso() {
            setTimeout(() => {
                this.aviso = ""
            }, 4000)
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