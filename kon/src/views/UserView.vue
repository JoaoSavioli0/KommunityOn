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

        <div class="w-full flex items-center mt-12 text-left ">
            <RouterLink to="/menu/conta" class="p-0 rounded-full">
                <div class="size-[90px] lg:size-[100px] rounded-full shadow-md overflow-hidden relative flex-shrink-0 group cursor-pointer"
                    :class="imagemUsuario ? 'bg-transparent' : 'bg-gray-900'">
                    <img v-if="imagemUsuario" :src="imagemUsuario" class="h-full w-full object-cover absolute">
                    <img v-else src="../assets/user_body.png" class="filtro absolute bottom-[-15px]">
                    <div
                        class="absolute w-full h-full bg-black/70 opacity-0 group-hover:opacity-100 transition-all duration-200 flex justify-center items-center pointer-events-none">
                        <img src="../assets/pencil.svg" class="size-[70px] filtro opacity-80">
                    </div>
                </div>
            </RouterLink>
            <div class="flex ml-2 flex-col">
                <h1 class="text-3xl font-semibold line-clamp-1">{{ usuario.nome }}</h1>
                <div class="flex items-center text-xl">
                    <img src="../assets/location.png" class="size-[16px]">
                    <span class="ml-[3px]">{{ endereco.bairro }}, <span class="font-medium">{{ endereco.cidade
                    }}</span></span>
                </div>
            </div>
        </div>

        <!-- Biografia -->
        <div class="w-full mt-4" v-if="usuario.biografia || usuarioLogado.id == id">
            <div
                class="py-2 w-full flex justify-between text-start text-xl font-semibold border-b-[1px] border-zinc-400">
                <span>Biografia</span>
                <div class="h-[30px] flex items-center justify-center">
                    <img src="../assets/pencil.svg" class="w-[30px] h-auto cursor-pointer"
                        v-if="usuarioLogado.id == id && !editandoBiografia" @click="editandoBiografia = true">

                    <img src="../assets/check.png" class="w-[20px] h-auto cursor-pointer mr-4"
                        v-if="usuarioLogado.id == id && editandoBiografia"
                        @click="() => { salvaBiografia(), editandoBiografia = false }">

                    <img src="../assets/close.svg" class="w-[20px] h-auto cursor-pointer"
                        v-if="usuarioLogado.id == id && editandoBiografia"
                        @click="editandoBiografia = false, biografiaEditada = usuario.biografia">
                </div>
            </div>
            <div class="w-full">
                <textarea class="p-2 text-start w-full bg-transparent resize-none rounded-b-2xl outline-none"
                    :disabled="!editandoBiografia" maxlength="300" :class="{ 'bg-zinc-50': editandoBiografia }"
                    v-model="biografiaEditada"></textarea>
            </div>
        </div>


        <!-- Conquistas  -->
        <div class="w-full mt-4" v-if="conquistas.length > 0">
            <div
                class="py-2 w-full flex justify-between text-start text-xl font-semibold border-b-[1px] border-zinc-400">
                <span>Conquistas</span>
                <span>{{ conquistas.length }}</span>
            </div>
            <div class="w-full flex gap-x-4 py-2 px-2 ">
                <div class="tooltip w-[40px]" v-for="conquista in conquistas"
                    :data-tip="`${conquista.titulo}: ${conquista.condicao}`">
                    <button class="btn bg-transparent p-0 hover:bg-transparent shadow-none border-none"><img
                            :src="require(`../assets/${conquista.icon}`)" class="w-[30px] h-auto"></button>
                </div>
            </div>
        </div>


        <!-- Solicitações -->
        <div class="w-full mt-6 relative">
            <div
                class="w-full flex justify-between text-start text-xl font-semibold py-2 border-b-[1px] border-zinc-400">
                <span>Solicitações</span>
                <span>{{ solicitacoes.length }}</span>
            </div>
            <div class="py-[6px] rounded-md border-[1.5px] border-gray-800 w-full flex justify-start bg-zinc-50 mt-4">
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

        <form class="w-full">
            <div class="w-full flex justify-between mt-6">

                <label class="w-[30%]">
                    <input type="radio" name="option" checked value="destaque" class="hidden peer" />
                    <div @click="filtro = 1"
                        class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-900 peer-checked:text-white lg:text-md text-sm">
                        Todos</div>
                </label>

                <label class="w-[30%]">
                    <input type="radio" name="option" value="proximo" class="hidden peer" />
                    <div @click="filtro = 2"
                        class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-900 peer-checked:text-white lg:text-md text-sm">
                        Abertos</div>
                </label>

                <label class="w-[30%]">
                    <input type="radio" name="option" value="recente" class="hidden peer" />
                    <div @click="filtro = 3"
                        class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-900 peer-checked:text-white lg:text-md text-sm">
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
        <div v-if="solicitacoesPesquisadas.length != 0 && !carregando" class="solicitacoes mt-8 w-full">
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
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';

export default {
    name: 'User',
    props: ['id'],
    data() {
        return {
            usuarioLogado: {},
            usuario: {},
            endereco: {},
            conquistas: [],
            imagemUsuario: null,
            solicitacoes: [],
            solicitacoesPesquisadas: [],
            filtros: [],
            filtro: 1,
            filtrosOpen: false,
            pesquisa: '',
            carregando: true,
            editandoBiografia: false,
            biografiaEditada: ''
        }
    },
    mounted() {
        useUserStore().reconectaSessao()
        if (useUserStore().usuario == null) {
            this.$router.push("/login")
        } else {
            this.usuarioLogado = useUserStore().usuario
            this.carregaUsuario()
            this.carregaSolicitacoes()
            this.carregaTags()
            document.addEventListener("click", this.fechaJanelas)
        }
    },
    methods: {
        carregaImagem() {
            const profileUsuario = this.usuario.fotoPerfil; // Base64 retornado do backend
            if (!profileUsuario) return;

            // Adiciona o prefixo correto para exibir no <img>
            this.imagemUsuario = `data:image/png;base64,${profileUsuario}`;
        },

        async carregaUsuario() {
            try {
                const response = await axios.get(`http://localhost:5000/usuario/${this.id}`)
                this.usuario = response.data
            } catch (error) {
                console.error("Erro ao carregar usuário " + this.id + ": ", error)
            }

            this.biografiaEditada = this.usuario.biografia ? this.usuario.biografia : "Sem biografia"

            this.carregaImagem()
            this.carregaConquistas()

            const endereco = await axios.get(`http://localhost:5000/endereco/${this.id}`)
            this.endereco = endereco.data
        },

        async carregaConquistas() {
            try {
                const response = await axios.get(`http://localhost:5000/conquista/usuario/${this.id}`)
                this.conquistas = response.data
                console.log(this.conquistas)
            } catch (error) {
                console.error("Erro ao carregar conquistas: ", error.response.data)
            }
        },

        fechaJanelas(event) {
            if (this.$refs.filtrosBox && !this.$refs.filtrosBox.contains(event.target) && !this.$refs.filtrosButton.contains(event.target)) {
                this.filtrosOpen = false
            }
        },

        async carregaSolicitacoes() {
            try {
                const response = await axios.post(`http://localhost:5000/solicitacao/solicitacoes/usuario/${this.id}`, this.filtros, {
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
                const response = await axios.post("http://localhost:5000/tag/all")
                this.tags = response.data
            } catch (error) {
                console.error("Erro ao carregar filtros: " + error)
            }
        },

        async salvaBiografia() {
            try {
                await axios.post("http://localhost:5000/usuario/biografia/nova", {
                    novaBiografia: this.biografiaEditada,
                    id: this.usuario.id
                })
            } catch (error) {
                console.error("Erro ao salvar biografia: ", error)
            }
        }
    },
    watch: {
        pesquisa() {
            this.pesquisaSolicitacao()
        },
        filtros() {
            this.carregaSolicitacoes()
        },
        filtro() {
            this.mudaFiltro(this.filtro)
        }
    }
}
</script>