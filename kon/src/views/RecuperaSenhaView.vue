<template>
    <section class="w-full h-full bg-[#F0F4F9]">

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

        <div class="w-full h-full flex justify-center items-center flex-col">
            <transition-group name="move" tag="div" class="flex w-full lg:w-[450px] justify-center items-center"
                v-if="!codigoEnviado && !codigoValido">
                <div key="divEnvio"
                    class="w-full rounded-[30px] bg-white p-8 flex flex-col justify-between shadow-sm transition-all duration-300">

                    <div class="w-full">
                        <div class="w-full flex">
                            <router-link :to="usuarioLogado ? '/menu/conta' : '/login'" class="p-0 z-[150]">
                                <button @click=""
                                    class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer ml-auto">
                                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                                    <span class="ml-2 text-xl">Voltar</span>
                                </button>
                            </router-link>
                        </div>
                        <div class="w-full my-4 pl-[1px] text-left">
                            <h1 class="text-2xl font-semibold"><span class="font-normal">Recuperação de</span> Senha
                            </h1>
                        </div>
                    </div>

                    <div class="w-full my-4 rounded-lg bg-gray-900 text-zinc-50 p-4 text-start">
                        <h2 class="font-medium">Esqueceu sua senha?</h2>
                        <p class="font-normal text-xs mt-2" v-if="usuarioLogado">Solicite um código de verificação para
                            autenticarmos sua identidade e redefina sua senha.</p>
                        <p class="font-normal text-xs mt-2" v-else>Digite seu CPF ou E-mail e te enviaremos um código de
                            recuperação.
                        </p>
                    </div>

                    <div class="w-full flex flex-col mt-6">

                        <!-- Cpf ou email -->
                        <div class="w-full relative" v-if="!usuarioLogado">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Cpf ou Email</span>
                            <input type="text" v-model="cpfOuEmail" maxlength="45"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <span v-if="avisoEnvio" class="mt-[2px] text-red-500 text-sm text-start">{{ avisoEnvio }}</span>

                        <button
                            class="w-full rounded-lg bg-gray-900 text-zinc-50 h-[50px] font-medium flex justify-center items-center"
                            :class="usuarioLogado ? 'mt-6' : 'mt-12'" @click="enviaCodigo(1)">
                            <span class="loading loading-ring loading-md" v-if="carregandoEnvioEmail"></span>
                            <span v-else>Enviar código no E-mail</span>
                        </button>

                        <button
                            class="w-full rounded-lg bg-gray-900 text-zinc-50 h-[50px] mt-2 font-medium flex justify-center items-center"
                            @click="enviaCodigo(2)">
                            <span class="loading loading-ring loading-md" v-if="carregandoEnvioTelefone"></span>
                            <span v-else>Enviar código no celular</span>
                        </button>
                    </div>
                </div>
            </transition-group>

            <transition-group name="move" tag="div" class="flex w-full lg:w-[450px] justify-center items-center"
                v-if="codigoEnviado && !codigoValido">
                <div key="divCodigo"
                    class="w-full rounded-[30px] bg-white p-8 flex flex-col justify-between shadow-sm transition-all duration-300">

                    <div class="w-full">
                        <div class="w-full flex">
                            <router-link :to="usuarioLogado ? '/menu/conta' : '/login'" class="p-0 z-[150]">
                                <button @click=""
                                    class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer ml-auto">
                                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                                    <span class="ml-2 text-xl">Voltar</span>
                                </button>
                            </router-link>
                        </div>
                        <div class="w-full my-4 pl-[1px] text-left">
                            <h1 class="text-2xl font-semibold"><span class="font-normal">Recuperação de</span> Senha
                            </h1>
                            <h2 class="mt-2 text-md">Digite o código de recuperação que te enviamos.</h2>
                        </div>
                    </div>

                    <div class="w-full flex flex-col mt-6">

                        <!-- Código -->
                        <div class="w-full relative">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Código de
                                recuperação</span>
                            <input type="text" v-model="codigo" maxlength="6"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <span v-if="avisoCodigo" class="mt-[2px] text-red-500 text-sm text-start">{{ avisoCodigo
                        }}</span>

                        <button class="w-full rounded-lg bg-gray-900 text-zinc-50 h-[50px] mt-12 font-medium"
                            @click="validaCodigo()">
                            <span class="loading loading-ring loading-md" v-if="carregandoVerificacao"></span>
                            <span v-else>Validar</span></button>

                    </div>
                </div>
            </transition-group>

            <transition-group name="move" tag="div" class="flex flex-col w-full lg:w-[450px] items-center"
                v-if="codigoValido && !codigoEnviado">
                <div key="divLogin"
                    class="w-full rounded-[30px] bg-white p-8 flex flex-col justify-between shadow-sm transition-all duration-300">
                    <div class="w-full">
                        <div class="w-full flex">
                            <router-link :to="usuarioLogado ? '/menu/conta' : '/login'" class="p-0 z-[150]">
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


                        <!-- Nova senha -->
                        <div class="w-full relative">
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
                                class="bg-gray-900 font-regular rounded-[10px] h-[50px] outline-none w-full text-white">
                                <span class="loading loading-ring loading-md" v-if="carregandoAlteracao"></span>
                                <span v-else>Alterar</span>
                            </button>
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
            novaSenha: '',
            novaSenhaConfirma: '',
            aviso: '',
            senhaAviso: [],
            senhaIgual: true,
            senhaVisivel: false,
            senhaConfirmVisivel: false,
            senhaAtualVisivel: false,
            usuario: null,
            dialogOpen: false,
            cpfOuEmail: '',
            codigo: '',
            codigoEnviado: false,
            codigoValido: false,
            usuarioLogado: false,
            usuario: {},
            avisoCodigo: "",
            avisoEnvio: "",
            carregandoEnvioEmail: false,
            carregandoEnvioTelefone: false,
            carregandoVerificacao: false,
            carregandoAlteracao: false,
        }
    },
    mounted() {
        useUserStore().reconectaSessao()
        if (useUserStore().usuario != null) {
            this.usuarioLogado = true
            this.usuario = useUserStore().usuario
            this.cpfOuEmail = this.usuario.cpf
        }
    },
    methods: {
        exit() {
            this.dialogOpen = false
            this.$router.push("/menu/conta")
        },
        resetaForm() {
            this.senhaAtual = ""
            this.novaSenha = ""
            this.novaSenhaConfirma = ""
        },
        async enviaCodigo(tipo) {
            tipo == 1 ? this.carregandoEnvioEmail = true : this.carregandoEnvioTelefone = true
            try {
                const response = await axios.post(`http://localhost:8080/recuperacao/envia_codigo`, {
                    cpfOuEmail: this.cpfOuEmail
                })

                let data = response.data

                if (data) {
                    alert("Esse código seria enviado para " + (tipo == 1 ? data["email"] : data["telefone"]) + "\n" + data["codigo"] + "\nAnote")
                    this.codigoEnviado = true;
                }

            } catch (error) {
                this.avisoEnvio = "Usuário não encontrado"
            }
            this.carregandoEnvioEmail = this.carregandoEnvioTelefone = false
        },
        async validaCodigo() {
            this.carregandoVerificacao = true;
            try {
                const response = await axios.post(`http://localhost:8080/recuperacao/verifica_codigo`, {
                    cpfOuEmail: this.cpfOuEmail,
                    codigo: this.codigo
                })

                let data = response.data

                if (data != 0) {
                    this.idUsuario = data
                    this.codigoValido = true
                    this.codigoEnviado = false
                } else {
                    this.avisoCodigo = "Código incorreto."
                }

            } catch (error) {
                this.aviso = "Erro: " + error
            }
            this.carregandoVerificacao = false
        },
        async alteraSenha() {
            if (this.usuarioLogado) {
                if (this.idUsuario != this.usuario.id) {
                    console.log("Erro inesperado")
                    return
                }
            }
            this.carregandoAlteracao = true
            try {
                console.log(this.novaSenha)
                console.log(this.idUsuario)
                const response = await axios.post(`http://localhost:8080/usuario/senha/alteracao?novaSenha=${this.novaSenha}&id=${this.idUsuario}`)
                this.$refs.dialog.showModal()
            } catch (error) {
                this.aviso = "Erro: " + error
            }
            this.carregandoAlteracao = false
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
        },
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