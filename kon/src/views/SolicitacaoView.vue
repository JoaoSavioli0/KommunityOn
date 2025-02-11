<template>
    <div class="w-full">
        <router-link to="/home" class="p-0">
            <button @click=""
                class="bg-gray-800 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
                <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                <span class="ml-2 text-xl">Voltar</span>
            </button>
        </router-link>
    </div>

    <div class="w-full mt-12 text-left">
        <h1 class="font-semibold text-4xl">{{ solicitacao.titulo }}</h1>
    </div>

    <div class="w-full flex items-center mt-4 text-left">
        <div class="rounded-full size-[40px] bg-gray-800"></div>
        <div class="flex ml-2 flex-col">
            <h1 class="text-lg font-semibold">{{ usuarioSolicitacao.nome }}</h1>
            <div class="flex text-sm">
                <img src="../assets/location.png" class="size-[16px]">
                <span class="ml-[3px]">{{ endereco.bairro }}, {{ endereco.cidade }}</span>
            </div>
        </div>
    </div>

    <div class="mt-6 font-lg text-justify font-medium">
        {{ solicitacao.descricao }}
    </div>

    <div class="flex cursor-pointer mt-4">
        <div class="bg-gray-800 rounded-l-md pl-2 pr-4 py-[3px] w-fit flex items-center z-[40] relative">
            <img src="../assets/heart.png" class="filtro size-[20px]">
            <span class="ml-2 text-gray-100 font-medium">Curtir</span>
            <div class="absolute bg-white rotate-45 z-[50] size-[13px] end-[-7px] top-[9px] "></div>
        </div>

        <div
            class="relative bg-white rounded-r-md px-2 border-t-2 border-r-2 border-b-2 border-gray-800 font-medium z-[50]">
            {{
                solicitacao.numLikes }}
        </div>
    </div>

    <div class="w-full text-left flex justify-between mt-16 flex items-center">
        <h1 class="text-2xl font-semibold">Comentários</h1>
        <h1 class="text-2xl font-semibold font-medium">{{ solicitacao.numComentarios }}</h1>
    </div>

    <div class="w-full mt-4">
        <textarea class="rounded-md bg-gray-200 p-2 w-full focus:outline-none"
            placeholder="Escreva um comentário"></textarea>
        <div class="w-full flex justify-start">
            <button class="rounded-md bg-gray-800 text-gray-100 px-4 py-[4px] font-medium w-fit">Enviar</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { useUserStore } from '@/stores/userStore';
import { RouterLink } from 'vue-router';
import { watch } from "vue";
import { useRouter } from "vue-router";

export default {
    name: "Solicitacao",
    props: ['id'],
    data() {
        return {
            usuario: {},
            solicitacao: {},
            usuarioSolicitacao: {},
            endereco: {}
        }
    },
    setup() {
        const userStore = useUserStore();
        const router = useRouter();

        watch(
            () => userStore.usuario,
            (novoUsuario) => {
                if (!novoUsuario) {
                    console.error("Usuário não encontrado na store.");
                    router.push("/login");
                }
            },
            { immediate: true } // Executa logo no início
        );
    },
    mounted() {
        const userStore = useUserStore()
        this.usuario = userStore.usuario
        this.carregaSolicitacao()
    },
    methods: {
        async carregaSolicitacao() {
            try {
                const solicitacao = await axios.get(`http://localhost:8080/solicitacao/${this.id}`)
                this.solicitacao = solicitacao.data
                this.usuarioSolicitacao = this.solicitacao.usuario

                const endereco = await axios.get(`http://localhost:8080/endereco/${this.usuarioSolicitacao.id}`)
                this.endereco = endereco.data
            } catch (error) {
                console.error("Ocorreu um erro ao carregar a solicitação: " + error)
            }
        }
    }
}
</script>