<template>
    <div class="w-full lg:w-[550px] py-8 lg:py-20">
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
                <div class="w-full rounded-lg bg-gray-900 flex items-center h-[50px]">
                    <input type="text" class="w-full px-4 bg-transparent focus:outline-none text-white"
                        placeholder="Bairro atingido" v-model="bairro">

                </div>

                <span v-if="erroBairro" class="text-500 mt-[3px] text-start">Digite um bairro válido</span>

            </div>

            <form>
                <div class="w-full flex flex-col text-left mt-6 px-4 py-2 relative bg-gray-900 rounded-lg">
                    <span class="text-gray-400">Título</span>
                    <input type="text" class="bg-transparent outline-none py-2 text-zinc-50"
                        :class="erroTitulo ? 'border-2 border-red-500' : ''" v-model="titulo">
                    <span v-if="erroTitulo" class="mt-[3px] text-red-500 text-start">Digite um título válido</span>
                </div>

                <div class="w-full flex flex-col text-left mt-6 px-4 py-2 relative bg-gray-900 rounded-lg">
                    <span class="text-gray-400">Descrição</span>
                    <textarea type="text" class="bg-transparent outline-none py-2 text-zinc-50"
                        :class="erroDescricao ? 'border-2 border-red-500' : ''" v-model="descricao"></textarea>
                    <span v-if="erroDescricao" class="mt-[3px] text-red-500 text-start">Digite uma descrição
                        válida</span>
                </div>


                <div class="w-full mt-6 flex justify-between items-center">
                    <button @click.prevent="anonimoCheckBox = !anonimoCheckBox"
                        class="py-1 px-2 w-[140px] bg-gray-900 rounded-full py-1 relative">
                        <span class="text-md text-zinc-50">{{ anonimoCheckBox ?
                            'Exibir nome' :
                            'Ocultar nome' }}</span>
                        <span class="font-sm italic absolute bottom-[-25px] start-[5px] whitespace-nowrap">
                            {{ anonimoCheckBox ? 'Seu nome não será exibido' : 'Seu nome será exibido' }}</span>
                    </button>
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