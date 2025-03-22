<template>
    <div class="w-full lg:w-[550px]">
        <div class="w-full">
            <router-link to="/home" class="p-0">
                <button @click=""
                    class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                    <span class="ml-2 text-xl">Voltar</span>
                </button>
            </router-link>
        </div>

        <div class="pt-6 w-full" v-if="bloqueiaAbertura">
            <div class="w-full rounded-md bg-gray-900 p-4 flex-col text-gray-100">
                <h1 class="text-xl font-medium">Você já tem uma solicitação aberta!</h1>
                <div class="w-full py-2 flex justify-center">
                    <div class="w-[90%] h-[1px] bg-white/40"></div>
                </div>
                <p class="font-light">Usuários só podem ter uma solicitação pendente por vez. Você pode criar uma nova
                    solicitação quando sua solicitação pendente for concluída, excluída ou atingir 20 likes.

                </p>
                <div class="w-full py-2 flex justify-center">
                    <div class="w-[90%] h-[1px] bg-white/40"></div>
                </div>
                <span class="text-sm"> Se você não tem
                    solicitações
                    pendentes, entre
                    em contato com o
                    <router-link to="/menu/suporte">
                        <span class="text-sky-500 font-medium cursor-pointer">suporte</span>.
                    </router-link>
                </span>
            </div>
        </div>

        <div v-else>

            <div class="w-full flex items-center mt-8">
                <div class="flex flex-col">
                    <h1 class="text-3xl lg:text-5xl font-normal text-left text-gray-800">Escreva sua <span
                            class="font-semibold">solicitação.</span></h1>
                    <p class="text-left text-md lg:text-xl font-normal text-gray-500 pr-[30px] mt-2">Escreva aqui sua
                        reclamação ou
                        sugestão
                        para o município.
                    </p>
                </div>
            </div>

            <div class="w-full mt-6 flex flex-col">
                <div class="w-full rounded-full bg-gray-900 flex items-center px-[3px] py-[3px]">
                    <input type="text" class="w-full px-[19px] bg-transparent focus:outline-none text-white"
                        placeholder="Bairro atingido" v-model="bairro">
                    <div class="w-[45px] h-[40px] bg-white rounded-full flex items-center justify-center">
                        <img src="../assets/check.png" class="filtro2 size-[30px]">
                    </div>
                </div>

                <span v-if="erroBairro" class="text-500 mt-[3px] text-start">Digite um bairro válido</span>

            </div>

            <form>
                <div class="w-full flex flex-col text-left mt-6 relative">
                    <span class="text-md lg:text-xl font-medium px-2">Título</span>
                    <input type="text" class="border-2 rounded-md bg-zinc-50 px-[15px] lg:py-4 py-2"
                        :class="erroTitulo ? 'border-red-500' : 'border-gray-800'" v-model="titulo">
                    <span v-if="erroTitulo" class="mt-[3px] text-red-500 text-start">Digite um título válido</span>
                </div>

                <div class="w-full  flex flex-col text-left mt-6 relative">
                    <span class="text-md lg:text-xl font-medium px-2 ">Descrição</span>
                    <textarea type="text" class="border-2 border-gray-800 rounded-md bg-zinc-50 px-[15px] py-4"
                        :class="erroDescricao ? 'border-red-500' : 'border-gray-800'" v-model="descricao"></textarea>
                    <span v-if="erroDescricao" class="mt-[3px] text-red-500 text-start">Digite uma descrição
                        válido</span>
                </div>
                <div class="flex items-center mt-4 ml-2"><input type="checkbox" v-model="anonimoCheckBox"
                        class="h-[18px] w-[18px]"><span class="ml-2 font-medium text-md">Ocultar
                        nome</span>
                </div>
                <div class="w-full mt-2 flex justify-end">
                    <button @click.prevent="registraSolicitacao()"
                        class="rounded-full text-white bg-gray-900 px-4 py-[4px]">Enviar</button>
                </div>
            </form>

        </div>
    </div>
</template>
<script>
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';

export default {
    name: "New",
    data() {
        return {
            solicitacao: {},
            usuario: {},
            titulo: '',
            bairro: '',
            descricao: '',
            anonimoCheckBox: false,
            esconderNome: 0,
            solicitacoesAbertas: [],
            bloqueiaAbertura: false,
            erroBairro: false,
            erroTitulo: false,
            erroDescricao: false,
        }
    },
    mounted() {
        useUserStore().reconectaSessao()
        if (useUserStore().usuario == null) {
            this.$router.push("/login")
        } else {
            this.usuario = useUserStore().usuario
            this.verificaSolicitacaoAberta();
        }
    },
    methods: {
        async registraSolicitacao() {
            if (this.sucesso) {
                return
            }

            this.verificaErroTitulo()
            this.verificaErroBairro()
            this.verificaErroDescricao()

            if (this.verificaErroTitulo() || this.verificaErroBairro() || this.verificaErroDescricao()) return;

            try {
                const response = await axios.post("http://localhost:5000/solicitacao/new", {
                    titulo: this.titulo,
                    bairro: this.bairro,
                    descricao: this.descricao,
                    idUsuario: this.usuario.id,
                    anonimo: this.esconderNome
                }, {
                    headers: {
                        "Content-Type": "application/json",
                    },
                });
                if (response.status == 200) {
                    this.$router.push("/home")
                }
                console.log("user id:" + this.usuario.id)
            } catch (e) {
                console.error("Erro ao registar a solicitação: " + e);
            }
        },
        async verificaSolicitacaoAberta() {
            try {
                const response = await axios.get(`http://localhost:5000/usuario/solicitacaoaberta/${this.usuario.id}`);
                this.solicitacoesAbertas = response.data
                this.bloqueiaAbertura = this.solicitacoesAbertas.some(s => s.numLikes < 20);
            } catch (e) {
                console.error("Ocorreu um erro ao verificar se o usuário possui solicitação aberta: " + e)
            }
        },
        verificaErroBairro() {
            if (this.bairro.length < 3 || /^\d+$/.test(this.bairro)) {
                this.erroBairro = true
                return true
            } else {
                this.erroBairro = false
            }
        },
        verificaErroDescricao() {
            if (this.descricao.length < 25 || /^\d+$/.test(this.descricao)) {
                this.erroDescricao = true
                return true
            } else {
                this.erroDescricao = false
            }
        },
        verificaErroTitulo() {
            if (this.titulo.length < 5 || /^\d+$/.test(this.titulo)) {
                this.erroTitulo = true
                return true
            } else {
                this.erroTitulo = false
            }
        }
    },
    watch: {
        anonimoCheckBox() {
            this.anonimoCheckBox ? this.esconderNome = 1 : this.esconderNome = 0
        }
    }
}
</script>