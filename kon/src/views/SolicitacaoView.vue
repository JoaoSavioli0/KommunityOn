<template>
    <div class="relative flex justify-center flex-col items-center w-full lg:w-[550px] py-8 lg:py-20">
        <div class="w-full">
            <router-link to="/home" class="p-0">
                <button @click=""
                    class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                    <span class="ml-2 text-xl">Voltar</span>
                </button>
            </router-link>
        </div>

        <!-- Aviso confirma interacao -->
        <div class="fixed h-screen w-full bg-black/50 z-[250] top-0" v-if="confirmaInteracaoBox">
            <div
                class="flex items-start flex-col fixed w-[90%] lg:w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
                <h1 class="text-xl lg:text-2xl font-semibold text-start lg:text-center">Confirmar interação?</h1>
                <p class="my-2 text-start lg:text-center">Essa ação não poderá ser revertida.</p>
                <div class="w-full flex justify-start mt-4">
                    <button class="py-2 w-[150px] text-center rounded-full bg-gray-900 text-white font-medium"
                        @click.prevent="confirmaInteracaoBox = false, curteSolicitacao()">Confirmar</button>
                    <button
                        class="ml-4 py-2 w-[150px] text-center rounded-full bg-transparent border-2 border-gray-800 text-gray-800 font-medium"
                        @click.prevent="confirmaInteracaoBox = false">Cancelar</button>
                </div>
            </div>
        </div>

        <!-- bloqueia interacao aviso -->
        <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="avisoCurtirBox">
            <div
                class="flex items-start flex-col fixed w-[90%] lg:w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
                <h1 class="text-xl lg:text-2xl font-semibold">{{ aviso }}</h1>
                <div class="w-full flex justify-center mt-4">
                    <button class="py-2 w-[150px] text-center rounded-full bg-gray-900 text-white font-medium"
                        @click.prevent="avisoCurtirBox = false">Ok</button>
                </div>
            </div>
        </div>

        <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="confirmaExcluirBox">
            <div
                class="flex items-start flex-col fixed w-[90%] lg:w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
                <h1 class="text-xl lg:text-2xl font-semibold text-start lg:text-center">Deseja excluir essa solicitação?
                </h1>
                <p class="my-2 text-start lg:text-center">Essa ação não poderá ser revertida.</p>
                <div class="w-full flex justify-start mt-4">
                    <button class="py-2 w-[150px] text-center rounded-full bg-gray-900 text-white font-medium"
                        @click.prevent="confirmaExcluirBox = false, excluiSolicitacao()">Confirmar</button>
                    <button
                        class="ml-4 py-2 w-[150px] text-center rounded-full bg-transparent border-2 border-gray-800 text-gray-800 font-medium"
                        @click.prevent="confirmaExcluirBox = false">Cancelar</button>
                </div>
            </div>
        </div>

        <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="confirmaConcluirBox">
            <div
                class="flex items-start flex-col fixed w-[90%] lg:w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
                <h1 class="text-xl lg:text-2xl font-semibold text-start lg:text-center">Deseja concluir essa
                    solicitação?</h1>
                <p class="my-2 text-start lg:text-center">Essa ação não poderá ser revertida.</p>
                <div class="w-full flex justify-start mt-4">
                    <button class="py-2 w-[150px] text-center rounded-full bg-gray-900 text-white font-medium"
                        @click.prevent="confirmaConcluirBox = false, concluiSolicitacao()">Confirmar</button>
                    <button
                        class="ml-4 py-2 w-[150px] text-center rounded-full bg-transparent border-2 border-gray-800 text-gray-800 font-medium"
                        @click.prevent="confirmaConcluirBox = false">Cancelar</button>
                </div>
            </div>
        </div>

        <div class="w-full flex items-center mt-12 text-left ">
            <div class="rounded-full size-[40px] bg-gray-900 overflow-hidden">
                <img v-if="imagemUsuario && !solicitacao.anonimo" :src="imagemUsuario"
                    class="h-full w-full object-cover ">
                <img v-else src="../assets/user_body.png" class="filtro bottom-[-5px] relative">
            </div>
            <div class="flex ml-2 flex-col">
                <h1 class="text-lg font-semibold">{{ solicitacao.anonimo ? "Usuário" : usuarioSolicitacao.nome }}</h1>
                <div class="flex text-sm">
                    <img src="../assets/location.png" class="size-[16px]">
                    <span class="ml-[3px]">{{ endereco.bairro }}, {{ endereco.cidade }}</span>
                </div>
            </div>
        </div>

        <div class="pt-4 w-full">
            <div class="w-full flex gap-x-2">
                <div class="rounded-full text-gray-100 bg-gray-900 px-2 py-[2px] text-sm" v-for="tag in tags"
                    :key="tag.id_tag">
                    {{ tag.nome }}
                </div>
            </div>
        </div>

        <div class="w-full mt-4 flex items-center" v-if="solicitacao.dataConclusao == null">
            <img src="../assets/clock.svg" class="size-[23px] filtro-atencao">
            <span class="ml-2 filtro-atencao font-semibold">Não resolvida</span>
        </div>
        <div class="w-full mt-6 flex items-center" v-else>
            <img src="../assets/success.svg" class="size-[23px] filtro-sucesso">
            <span class="ml-2 filtro-sucesso font-semibold">Resolvida</span>
        </div>

        <div class="flex w-full justify-start">
            <div class="w-max mt-2 flex items-center pr-2 rounded-lg bg-lime-500 text-[#F0F4F9]"
                v-if="tags.find(t => t.id == 9)">
                <img src="../assets/sustainable.svg" class="w-[30px] h-auto filtro-branco">
                <span class="ml-[2px] text-lg font-medium">Sustentável</span>
            </div>
        </div>

        <div class="w-full mt-2 text-left">
            <h1 class="font-semibold text-3xl lg:text-4xl">{{ solicitacao.titulo }}</h1>
            <h2 class="text-gray-600 text-xl lg:text-2xl font-medium">Em {{ solicitacao.bairro }}</h2>
        </div>

        <div class="mt-4 font-lg text-justify font-medium w-full">
            {{ solicitacao.descricao }}
        </div>

        <div class="w-full flex mt-6 flex-wrap gap-x-4 gap-y-2">
            <button class="flex cursor-pointer" @click="confirmaInteracaoBox = true">
                <div class="bg-gray-900 rounded-l-md pl-2 pr-4 h-[32px] w-fit flex items-center z-[40] relative">
                    <img src="../assets/heart.png" class="filtro size-[20px]">
                    <span class="ml-2 text-gray-100 font-medium">{{ solicitacaoCurtida ? 'curtido' : 'curtir' }}</span>
                    <div class="absolute bg-white rotate-45 z-[50] size-[13px] end-[-7px] top-[9px] "></div>
                </div>

                <div
                    class="relative bg-white rounded-r-md px-2 h-[32px] flex items-center border-t-2 border-r-2 border-b-2 border-gray-800 font-medium z-[50]">
                    {{
                        solicitacao.numLikes }}
                </div>
            </button>

            <button class="bg-gray-900 rounded-md text-white px-2 h-[32px] w-fit flex items-center justify-center">
                <img src="../assets/share.svg" class="filtro size-[20px]">
                <span class="ml-2 text-gray-100 font-medium">Compartilhar</span>
            </button>

            <button class="bg-gray-900 rounded-md text-white px-2 h-[32px] w-fit flex items-center justify-center"
                v-if="solicitacaoPropria" @click="confirmaExcluirBox = true">
                <img src="../assets/trash.svg" class="filtro size-[20px]">
                <span class="ml-2 text-gray-100 font-medium">Excluir</span>
            </button>

            <div class="relative">
                <button
                    class="bg-gray-900 rounded-md text-white px-2 h-[32px] w-fit flex items-center justify-center relative z-[50]"
                    v-if="usuarioAdmin" @click="opcoesAdmin = !opcoesAdmin">
                    <img src="../assets/admin.svg" class="filtro size-[20px]">
                    <span class="ml-2 text-gray-100 font-medium">Admin</span>
                </button>
                <ul class="absolute w-full mt-[-4px] z-[40]" v-if="opcoesAdmin">
                    <li class="px-2 pb-[1px] pt-[7px] bg-gray-600 text-gray-200 admin-items w-full flex justify-center flex-col items-center cursor-pointer hover:bg-gray-500 transition-all duration-200"
                        @click="confirmaExcluirBox = true">
                        Excluir
                        <div class="w-[90%] bg-gray-500 h-[0.5px] mt-[2px]"></div>
                    </li>
                    <li class="px-2 py-[3px] bg-gray-600 text-gray-200 admin-items cursor-pointer hover:bg-gray-500 transition-all duration-200"
                        @click="confirmaConcluirBox = true">Concluir</li>
                </ul>
            </div>
        </div>


        <!-- Comentários -->
        <div class="w-full text-left flex justify-between mt-12 flex items-center">
            <h1 class="text-2xl font-semibold">Comentários</h1>
            <h1 class="text-2xl font-semibold font-medium">{{ comentarios.length }}</h1>
        </div>

        <div class="w-full mt-6">
            <textarea v-model="textoComentario"
                class="min-h-[50px] max-h-[300px] rounded-md bg-gray-200 p-2 w-full focus:outline-none"
                placeholder="Escreva um comentário"></textarea>
            <div class="w-full flex justify-start">
                <button class="rounded-md bg-gray-900 text-gray-100 px-4 py-[4px] font-medium w-fit mt-2"
                    @click="enviaComentario()">Enviar</button>
            </div>
        </div>

        <div class="w-full mt-12">
            <div class="w-full flex items-start mb-[15px] py-4 px-2 border-b-[1px] border-gray-300 rounded-lg"
                v-for="comentario in comentarios">
                <!-- Ícone -->
                <div class="flex-shrink-0">
                    <div class="rounded-full size-[40px] bg-gray-900 overflow-hidden">
                        <img v-if="carregaImagemComentario(comentario.usuario.fotoPerfil)"
                            :src="carregaImagemComentario(comentario.usuario.fotoPerfil)"
                            class="h-full w-full object-cover">
                        <img v-else src="../assets/user_body.png" class="filtro bottom-[-5px] relative">
                    </div>
                </div>
                <!-- Comentario -->
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
            avisoCurtirBox: false,
            confirmaInteracaoBox: false,
            confirmaExcluirBox: false,
            confirmaConcluirBox: false,
            avisoCurtirBoxTimeOutExec: false,
            solicitacoesUsuario: [],
            solicitacaoPropria: false,
            usuarioAdmin: false,
            opcoesAdmin: false,
            imagemUsuario: null,
            tags: [],
            sustentavel: false,
            curtidos: [],
        }
    },
    mounted() {
        useUserStore().reconectaSessao()
        if (useUserStore().usuario == null) {
            this.$router.push("/login")
        } else {
            this.usuario = useUserStore().usuario
            this.carregaSolicitacao()
            this.carregaInteracoes()
            this.usuarioAdmin = this.usuario.tipo === "ADM"
        }
    },
    methods: {
        carregaImagem() {
            const profileUsuario = this.usuarioSolicitacao.fotoPerfil; // Base64 retornado do backend
            if (!profileUsuario) return;

            // Adiciona o prefixo correto para exibir no <img>
            this.imagemUsuario = `data:image/png;base64,${profileUsuario}`;
        },
        carregaImagemComentario(fotoPerfil) {
            if (!fotoPerfil) return false;
            return `data:image/png;base64,${fotoPerfil}`
        },
        async carregaSolicitacao() {
            try {
                const solicitacao = await axios.get(`http://localhost:5000/solicitacao/${this.id}`)
                this.solicitacao = solicitacao.data
                this.usuarioSolicitacao = this.solicitacao.usuario
                this.carregaTags()
                this.solicitacaoPropria = this.usuarioSolicitacao.id == this.usuario.id

                this.carregaImagem()
                this.carregaComentarios()

                const endereco = await axios.get(`http://localhost:5000/endereco/${this.usuarioSolicitacao.id}`)
                this.endereco = endereco.data

            } catch (error) {
                console.error("Ocorreu um erro ao carregar a solicitação: " + error)
            }
        },

        async carregaInteracoes() {
            try {
                const response = await axios.get(`http://localhost:5000/usuario/interacoes/${this.usuario.id}`);
                this.curtidos = response.data
            } catch (error) {
                console.error("Erro ao buscar interações do usuário: ", error);
            }
        },

        async carregaTags() {
            try {
                this.tags = (await axios.get(`http://localhost:5000/solicitacao/tags/${this.id}`)).data
            } catch (error) {
                console.error("Erro ao carregar tags: " + error)
            }
        },

        async enviaComentario() {
            try {
                if (!this.textoComentario) return
                const response = await axios.post("http://localhost:5000/comentario/cadastro", {
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
                const comentarios = await axios.get(`http://localhost:5000/comentario/solicitacao/${this.id}`)
                this.comentarios = comentarios.data
            } catch (error) {
                console.error("Erro ao carregar comentários: " + error)
            }
        },

        async curteSolicitacao() {
            try {
                const response = await axios.get(`http://localhost:5000/usuario/curtir/${this.usuario.id}/${this.id}`);
                if (response.data.includes("Sucesso")) {
                    this.solicitacao.numLikes++;
                } else {
                    this.aviso = response.data
                    this.avisoCurtirBox = true
                }
                this.carregaInteracoes()
            } catch (error) {
                console.error("Erro ao curtir solicitação: " + error)
            }
        },

        async excluiSolicitacao() {
            try {
                await axios.delete(`http://localhost:5000/solicitacao/exclui/${this.id}`)
                this.$router.push("/home")
            } catch (error) {
                console.log("Erro ao deletar a solicitação: " + error)
            }
        },

        async concluiSolicitacao() {
            try {
                await axios.patch(`http://localhost:5000/solicitacao/conclui/${this.id}`)
                    .then(() => console.log("Solicitação concluída com sucesso!"))
            } catch (error) {
                console.log("Erro ao concluir solicitação: " + error)
            }
        }
    },
    computed: {
        solicitacaoCurtida() {
            return this.curtidos.includes(Number(this.id));
        }

    }
}
</script>

<style>
.admin-items:nth-last-child(1) {
    border-radius: 0px 0px 5px 5px;
}

.filtro-atencao {
    filter: brightness(0) saturate(100%) invert(73%) sepia(19%) saturate(4051%) hue-rotate(344deg) brightness(99%) contrast(87%);
}

.filtro-sucesso {
    filter: brightness(0) saturate(100%) invert(79%) sepia(93%) saturate(562%) hue-rotate(55deg) brightness(86%) contrast(88%);
}

.filtro-branco {
    filter: brightness(0) saturate(100%) invert(97%) sepia(5%) saturate(393%) hue-rotate(182deg) brightness(100%) contrast(96%);
}
</style>