<template>
    <div class="relative flex justify-center flex-col items-center pb-20">
        <div class="w-full">
            <router-link to="/home" class="p-0">
                <button @click=""
                    class="bg-gray-800 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                    <span class="ml-2 text-xl">Voltar</span>
                </button>
            </router-link>
        </div>

        <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="confirmaInteracaoBox">
            <div
                class="flex items-start flex-col fixed w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
                <h1 class="text-2xl font-semibold">Confirmar interação?</h1>
                <p class="my-2">Essa ação não poderá ser revertida.</p>
                <div class="w-full flex justify-start mt-4">
                    <button class="py-2 w-[150px] text-center rounded-full bg-gray-800 text-white font-medium"
                        @click.prevent="confirmaInteracaoBox = false, curteSolicitacao()">Confirmar</button>
                    <button
                        class="ml-4 py-2 w-[150px] text-center rounded-full bg-transparent border-2 border-gray-800 text-gray-800 font-medium"
                        @click.prevent="confirmaInteracaoBox = false">Cancelar</button>
                </div>
            </div>
        </div>

        <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="avisoBox">
            <div
                class="flex items-start flex-col fixed w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
                <h1 class="text-2xl font-semibold">{{ aviso }}</h1>
                <div class="w-full flex justify-center mt-4">
                    <button class="py-2 w-[150px] text-center rounded-full bg-gray-800 text-white font-medium"
                        @click.prevent="avisoBox = false">Ok</button>
                </div>
            </div>
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

        <div class="mt-6 font-lg text-justify font-medium w-full">
            {{ solicitacao.descricao }}
        </div>

        <div class="w-full flex mt-4">
            <button class="flex cursor-pointer" @click="confirmaInteracaoBox = true">
                <div class="bg-gray-800 rounded-l-md pl-2 pr-4 h-[32px] w-fit flex items-center z-[40] relative">
                    <img src="../assets/heart.png" class="filtro size-[20px]">
                    <span class="ml-2 text-gray-100 font-medium">Curtir</span>
                    <div class="absolute bg-white rotate-45 z-[50] size-[13px] end-[-7px] top-[9px] "></div>
                </div>

                <div
                    class="relative bg-white rounded-r-md px-2 h-[32px] flex items-center border-t-2 border-r-2 border-b-2 border-gray-800 font-medium z-[50]">
                    {{
                        solicitacao.numLikes }}
                </div>
            </button>

            <button class="bg-gray-800 rounded-md text-white px-2 h-[32px] w-fit flex items-center justify-center ml-4">
                <img src="../assets/share.svg" class="filtro size-[20px]">
                <span class="ml-2 text-gray-100 font-medium">Compartilhar</span>
            </button>

            <button class="bg-gray-800 rounded-md text-white px-2 h-[32px] w-fit flex items-center justify-center ml-4">
                <img src="../assets/trash.svg" class="filtro size-[20px]">
                <span class="ml-2 text-gray-100 font-medium">Excluir</span>
            </button>
        </div>

        <div class="w-full text-left flex justify-between mt-16 flex items-center">
            <h1 class="text-2xl font-semibold">Comentários</h1>
            <h1 class="text-2xl font-semibold font-medium">{{ solicitacao.numComentarios }}</h1>
        </div>

        <div class="w-full mt-4">
            <textarea v-model="textoComentario"
                class="min-h-[50px] max-h-[300px] rounded-md bg-gray-200 p-2 w-full focus:outline-none"
                placeholder="Escreva um comentário"></textarea>
            <div class="w-full flex justify-start">
                <button class="rounded-md bg-gray-800 text-gray-100 px-4 py-[4px] font-medium w-fit"
                    @click="enviaComentario()">Enviar</button>
            </div>
        </div>

        <div class="w-full mt-12">
            <div class="w-full flex items-start mb-[15px] py-4 px-2 border-b-[1px] border-gray-300 rounded-lg"
                v-for="comentario in comentarios">
                <!-- Ícone à esquerda -->
                <div class="flex-shrink-0">
                    <div class="rounded-full size-[40px] bg-gray-800"></div>
                </div>
                <!-- Conteúdo à direita -->
                <div class="ml-4 flex flex-col text-left flex-grow">
                    <h1 class="font-semibold text-lg">{{ comentario.usuario.nome }}</h1>
                    <div class="w-full overflow-hidden">
                        <p class="text-gray-500 line-clamp-4 break-words break-all">
                            {{ comentario.texto }}
                        </p>
                    </div>
                </div>
            </div>
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
            comentarios: [],
            endereco: {},
            textoComentario: "",
            aviso: '',
            avisoBox: false,
            confirmaInteracaoBox: false,
            avisoBoxTimeOutExec: false,
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

                this.carregaComentarios()
            } catch (error) {
                console.error("Ocorreu um erro ao carregar a solicitação: " + error)
            }
        },

        async enviaComentario() {
            try {
                const response = await axios.post("http://localhost:8080/comentario/cadastro", {
                    idUsuario: this.usuario.id,
                    idSolicitacao: this.id,
                    texto: this.textoComentario
                }, {
                    headers: {
                        "Content-Type": "application/json",
                    },
                })

                this.comentarios.unshift(response.data);
                this.textoComentario = ""
                this.solicitacao.numComentarios++;
            } catch (error) {
                console.error("Erro ao enviar comentário: " + error)
            }
        },

        async carregaComentarios() {
            try {
                const comentarios = await axios.get(`http://localhost:8080/comentario/solicitacao/${this.id}`)
                this.comentarios = comentarios.data
            } catch (error) {
                console.error("Erro ao carregar comentários: " + error)
            }
        },

        async curteSolicitacao() {
            try {
                const response = await axios.get(`http://localhost:8080/usuario/curtir/${this.usuario.id}/${this.id}`);
                if (response.data.includes("Sucesso")) {
                    this.solicitacao.numLikes++;
                } else {
                    this.aviso = response.data
                    this.avisoBox = true
                }
            } catch (error) {
                console.error("Erro ao curtir solicitação: " + error)
            }
        },
    }
}
</script>