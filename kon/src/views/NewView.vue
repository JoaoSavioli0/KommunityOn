<template>
    <div>
        <div class="w-full">
            <router-link to="/home" class="p-0">
                <button @click=""
                    class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                    <span class="ml-2 text-xl">Voltar</span>
                </button>
            </router-link>
        </div>

        <div class="pt-6 pb-2 w-full" v-if="temSolicitacaoAberta">
            <div class="w-full rounded-md bg-gray-900 p-4 flex-col text-gray-100">
                <h1 class="text-xl font-medium">Você já tem uma solicitação aberta!</h1>
                <div class="w-full py-2 flex justify-center">
                    <div class="w-[90%] h-[1px] bg-white/40"></div>
                </div>
                <p class="font-light">Usuários só podem ter uma solicitação pendente por vez. Se você não tem
                    solicitações
                    pendentes, entre
                    em contato com o <span class="text-sky-500 font-medium cursor-pointer">suporte</span>.</p>
            </div>
        </div>

        <div class="pb-8" v-else>

            <div class="w-full flex items-center mt-8">
                <div class="flex flex-col">
                    <h1 class="text-5xl font-normal text-left text-gray-800">Escreva sua <span
                            class="font-semibold">solicitação.</span></h1>
                    <p class="text-left text-xl font-medium text-gray-500 pr-[30px] mt-2">Escreva aqui sua reclamação ou
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
                <div class="flex items-center ml-4 mt-2"><input type="checkbox" v-model="esconderNome"
                        class="h-[18px] w-[18px]"><span class="ml-2 font-normal text-lg">Ocultar
                        nome</span>
                </div>
            </div>

            <form>
                <div class="w-full flex flex-col text-left mt-8 relative">
                    <span class="ml-2 text-xl font-normal absolute bg-[#F0F4F9] px-2 top-[-13px]">Título</span>
                    <input type="text" class="border-2 border-gray-800 rounded-md bg-[#F0F4F9] px-[15px] py-4"
                        v-model="titulo">
                </div>
                <div class="w-full  flex flex-col text-left mt-6 relative">
                    <span class="ml-2 text-xl font-normal absolute bg-[#F0F4F9] px-2 top-[-13px]">Descrição</span>
                    <textarea type="text" class="border-2 border-gray-800 rounded-md bg-[#F0F4F9] px-[15px] py-4"
                        v-model="descricao"></textarea>
                </div>
                <div class="w-full mt-4 flex justify-end">
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
            esconderNome: false,
            temSolicitacaoAberta: false,
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
            try {
                console.log("esconderNome: " + this.esconderNome)
                const response = await axios.post("http://localhost:8080/solicitacao/new", {
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
                console.log(response)
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
                const response = await axios.get(`http://localhost:8080/usuario/solicitacaoaberta/${this.usuario.id}`);
                console.log(response.data);
                this.temSolicitacaoAberta = response.data;
            } catch (e) {
                console.error("Ocorreu um erro ao verificar se o usuário possui solicitação aberta: " + e)
            }
        }
    }
}
</script>