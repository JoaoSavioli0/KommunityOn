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

        <div class="w-full flex items-center mt-8">
            <div class="flex flex-col">
                <h1 class="text-3xl lg:text-5xl  font-normal text-left text-gray-800">Minhas <span
                        class="font-semibold">solicitações.</span></h1>
                <p class="text-left text-lg lg:text-xl font-medium text-gray-500 pr-[30px] mt-2">Veja e acompanhe o
                    andamento de
                    suas
                    solicitações abertas.
                </p>
            </div>
        </div>

        <div class="w-full mt-6 relative">
            <div class="py-[6px] rounded-md border-[1.5px] border-gray-800 w-full flex justify-start bg-zinc-50">
                <input type="text" v-model="pesquisa"
                    class="px-[12px] focus:outline-none w-full bg-transparent text-gray-700" placeholder="Pesquisar">
                <div class="border-l-[1.5px] border-zinc-400 px-[10px] flex justify-center">
                    <button @click="filtrosOpen = !filtrosOpen" ref="filtrosButton">
                        <img src="../assets/equalizer.png" class="w-[25px] h-auto filtro-cinza">
                    </button>
                </div>
            </div>

            <div v-if="filtrosOpen" ref="filtrosBox"
                class="absolute w-fit rounded-md bg-zinc-50 shadow-md px-4 py-2 end-0 z-[350] text-left">
                <span class="font-bold text-zinc-800 text-sm">Tags</span>
                <ul>
                    <li v-for="tag in tags" :key="tag.id" class="flex items-center mt-[5px]">
                        <input type="checkbox" :key="tag.id" :value="tag.id" v-model="filtros">
                        <span class="ml-2 text-xs font-medium">{{ tag.nome }}</span>
                    </li>
                </ul>
            </div>

        </div>

        <form>
            <div class="w-full flex justify-between mt-6">

                <label class="w-[30%]">
                    <input type="radio" name="option" checked value="destaque" class="hidden peer" />
                    <div @click="filtro = 1"
                        class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white lg:text-md text-sm">
                        Todos</div>
                </label>

                <label class="w-[30%]">
                    <input type="radio" name="option" value="proximo" class="hidden peer" />
                    <div @click="filtro = 2"
                        class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white lg:text-md text-sm">
                        Abertos</div>
                </label>

                <label class="w-[30%]">
                    <input type="radio" name="option" value="recente" class="hidden peer" />
                    <div @click="filtro = 3"
                        class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white lg:text-md text-sm">
                        Concluídos</div>
                </label>
            </div>
        </form>

        <div v-if="carregando" class="w-full py-24 flex justify-center items-center">
            <span class="loading loading-ring loading-lg"></span>
        </div>

        <!-- Cajo não tenha solicitações -->
        <div v-if="solicitacoesPesquisadas.length == 0 && !carregando"
            class="w-full text-center py-6 text-xl font-medium text-zinc-800">
            <span>Não foram encontradas solicitações para a sua pesquisa!</span>
        </div>

        <!-- Solicitações -->
        <div v-if="solicitacoesPesquisadas.length != 0 && !carregando" class="solicitacoes mt-8">
            <div v-if="solicitacoes.length > 0" class="flex-col flex gap-y-4">
                <div class="w-full rounded-[20px] bg-gray-900 flex px-6 py-4 text-left justify-between items-center relative"
                    v-for="solicitacao in solicitacoesPesquisadas" :key="solicitacao.id">
                    <RouterLink :to="`/solicitacao/${solicitacao.id}`" class="p-0 relative w-full">
                        <div class="absolute end-[-10px] top-[0px]">
                            <img src="../assets/clock.svg" class="size-[30px] max-[600px]:size-[25px] filtro-atencao"
                                v-if="solicitacao.dataConclusao == null">
                            <img src="../assets/success.svg" class="size-[30px] max-[600px]:size-[25px] filtro-sucesso"
                                v-else>
                        </div>
                        <div class="flex flex-col">
                            <h1 class="text-white font-semibold text-2xl">{{ solicitacao.titulo }}</h1>
                            <div class="flex mt-2">
                                <div class=" flex items-center text-white mt-[3px]">
                                    <img src="../assets/location.png"
                                        class="filtro max-[600px]:size-[14px] size-[19px]">
                                    <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.bairro }}, {{
                                        solicitacao.cidade }}</span>

                                    <div class="ml-8 max-[600px]:ml-4 flex items-center">
                                        <img src="../assets/comments.png"
                                            class="max-[600px]:size-[14px] size-[19px] filtro">
                                        <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.numComentarios }}</span>
                                    </div>

                                    <div class="ml-8 max-[600px]:ml-4 flex items-center">
                                        <img src="../assets/heart.png"
                                            class="max-[600px]:size-[20px] size-[25px] filtro">
                                        <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.numLikes }}</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </RouterLink>

                </div>
            </div>
            <div v-else class="w-full">
                <h1 class="text-2xl">Você ainda não tem nenhuma solicitação cadastrada!</h1>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { useUserStore } from '@/stores/userStore';
import { useRouter } from "vue-router"
import { RouterLink } from 'vue-router';
import { watch } from "vue";

export default {
    name: "SolicitacoesUsuario",
    props: ['id'],
    data() {
        return {
            solicitacoes: [],
            solicitacoesPesquisadas: [],
            filtros: [],
            filtro: 1,
            filtrosOpen: false,
            pesquisa: '',
            usuario: null,
            carregando: true
        }
    },
    mounted() {
        useUserStore().reconectaSessao()
        if (useUserStore().usuario == null) {
            this.$router.push("/login")
        } else {
            this.usuario = useUserStore().usuario
            this.carregaSolicitacoes()
            this.carregaTags()
            document.addEventListener("click", this.fechaJanelas)
        }
    },
    methods: {
        fechaJanelas(event) {
            if (this.$refs.filtrosBox && !this.$refs.filtrosBox.contains(event.target) && !this.$refs.filtrosButton.contains(event.target)) {
                this.filtrosOpen = false
            }
        },
        async carregaSolicitacoes() {
            try {
                const response = await axios.post(`http://localhost:8080/solicitacao/solicitacoes/usuario/${this.id}`, this.filtros, {
                    headers: {
                        "Content-Type": "application/json",
                    },
                })
                this.solicitacoes = response.data;
                this.solicitacoesPesquisadas = [...this.solicitacoes]
            } catch (error) {
                console.error("Erro ao buscar solicitações: ", error);
            } finally {
                this.carregando = false;
            }
        },
        mudaFiltro(filtro) {
            switch (filtro) {
                case 1: this.solicitacoesPesquisadas = this.solicitacoes; break;
                case 2: this.ordenaAbertos(); break;
                case 3: this.ordenaConcluido(); break;
            }
        },
        ordenaAbertos() {
            this.solicitacoesPesquisadas = this.solicitacoes
            if (this.pesquisa) { this.pesquisaSolicitacao() }
            this.solicitacoesPesquisadas = this.solicitacoesPesquisadas.filter(solicitacao => solicitacao.dataConclusao == null)
        },
        ordenaConcluido() {
            this.solicitacoesPesquisadas = this.solicitacoes
            if (this.pesquisa) { this.pesquisaSolicitacao() }
            this.solicitacoesPesquisadas = this.solicitacoesPesquisadas.filter(solicitacao => solicitacao.dataConclusao != null)
        },
        pesquisaSolicitacao() {
            if (this.pesquisa) {
                const removerAcentos = (str) => str.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();

                const pesquisaNormalizada = removerAcentos(this.pesquisa);

                this.solicitacoesPesquisadas = this.solicitacoesPesquisadas.filter((s) => {
                    const tituloNormalizado = removerAcentos(s.titulo);
                    const descricaoNormalizada = removerAcentos(s.descricao);
                    const bairroNormalizado = removerAcentos(s.bairro);

                    return (
                        tituloNormalizado.includes(pesquisaNormalizada) ||
                        descricaoNormalizada.includes(pesquisaNormalizada) ||
                        bairroNormalizado.includes(pesquisaNormalizada) ||
                        pesquisaNormalizada.includes(tituloNormalizado) ||
                        pesquisaNormalizada.includes(descricaoNormalizada) ||
                        pesquisaNormalizada.includes(bairroNormalizado)
                    );
                });
            } else {
                this.mudaFiltro(this.filtro)
            }
        },
        async carregaTags() {
            try {
                const response = await axios.post("http://localhost:8080/tag/all")
                this.tags = response.data
            } catch (error) {
                console.error("Erro ao carregar filtros: " + error)
            }
        },
    },
    watch: {
        pesquisa() {
            this.pesquisaSolicitacao()
        },
        filtros() {
            console.log(this.filtros)
            this.carregaSolicitacoes()
        },
        filtro() {
            this.mudaFiltro(this.filtro)
        }
    }
}
</script>