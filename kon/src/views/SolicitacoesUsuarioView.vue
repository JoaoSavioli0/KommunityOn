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

    <div class="w-full flex items-center mt-8">
        <div class="flex flex-col">
            <h1 class="text-5xl font-normal text-left text-gray-800">Minhas <span
                    class="font-semibold">solicitações.</span></h1>
            <p class="text-left text-xl font-medium text-gray-500 pr-[30px] mt-2">Veja e acompanhe o andamento de suas
                solicitações abertas.
            </p>
        </div>
    </div>

    <div class="w-full mt-6">
        <div class="py-[6px] rounded-md border-[1.5px] border-gray-800 w-full flex justify-start">
            <input type="text" class="px-[12px] focus:outline-none w-full bg-transparent text-gray-700"
                placeholder="Pesquisar">
            <div class="border-l-[1.5px] border-zinc-400 px-[10px] flex justify-center">
                <img src="../assets/equalizer.png" class="w-[25px] h-auto filtro-cinza">
            </div>
        </div>
    </div>

    <form>
        <div class="w-full flex justify-between mt-6">

            <label class="w-[30%]">
                <input type="radio" name="option" v-model="exibindo" checked value="destaque" class="hidden peer" />
                <div @click="mudaFiltro('por_like')"
                    class="cursor-pointer py-[2px] rounded-full bg-gray-200 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white">
                    Todos</div>
            </label>

            <label class="w-[30%]">
                <input type="radio" name="option" v-model="exibindo" value="proximo" class="hidden peer" />
                <div @click="mudaFiltro('por_data')"
                    class="cursor-pointer py-[2px] rounded-full bg-gray-200 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white">
                    Abertos</div>
            </label>

            <label class="w-[30%]">
                <input type="radio" name="option" v-model="exibindo" value="recente" class="hidden peer" />
                <div @click="mudaFiltro('por_data')"
                    class="cursor-pointer py-[2px] rounded-full bg-gray-200 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white">
                    Concluídos</div>
            </label>
        </div>
    </form>

    <div v-if="carregando" class="w-full py-24 flex justify-center items-center">
        <span class="loading loading-ring loading-lg"></span>
    </div>

    <div v-else class="solicitacoes mt-8">
        <div v-if="solicitacoes.length > 0" class="flex-col flex gap-y-4">
            <div class="w-full rounded-[20px] bg-gray-800 flex px-6 py-4 text-left justify-between items-center relative"
                v-for="solicitacao in solicitacoes">
                <div class="flex flex-col">
                    <h1 class="text-white font-semibold text-2xl">{{ solicitacao.titulo }}</h1>
                    <div class="flex mt-2">
                        <div class=" flex items-center text-white mt-[3px]">
                            <img src="../assets/location.png" class="filtro size-[19px]">
                            <span class="ml-2">{{ solicitacao.bairro }}, {{ solicitacao.cidade }}</span>

                            <div class="ml-8 flex items-center">
                                <img src="../assets/comments.png" class="size-[19px] filtro">
                                <span class="ml-2">{{ solicitacao.numComentarios }}</span>
                            </div>

                            <div class="ml-8 flex items-center">
                                <img src="../assets/heart.png" class="size-[25px] filtro">
                                <span class="ml-2">{{ solicitacao.numLikes }}</span>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="py-6 border-l-[1px] border-gray-500 flex items-center justify-end pl-4">
                    <span class="text-white font-semibold text-center w-full">
                        {{ solicitacao.data_conclusao ? 'Concluído' : 'Aberto' }}
                    </span>
                </div>
            </div>
        </div>
        <div v-else class="w-full">
            <h1 class="text-2xl">Você ainda não tem nenhuma solicitação cadastrada!</h1>
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
    setup() {
        const userStore = useUserStore();
        const router = useRouter();

        watch(
            () => userStore.usuario,
            (novoUsuario) => {
                if (novoUsuario.id >= 1) {
                    console.log("Usuário carregado:", novoUsuario);
                } else {
                    console.error("Usuário não encontrado na store.");
                    router.push("/login");
                }
            },
            { immediate: true } // Executa logo no início
        );
    },
    data() {
        return {
            solicitacoes: [],
            usuario: null,
            carregando: true
        }
    },
    mounted() {
        const userStore = useUserStore();
        if (userStore.usuario != null) {
            this.usuario = userStore.usuario
            this.carregaSolicitacoes()
            console.log(userStore.usuario)
        } else {
            console.log("Usuário não encontrado no store.");
            this.$router.push("/login")
        }
    },
    methods: {
        async carregaSolicitacoes() {
            try {
                const response = await axios.get(`http://localhost:8080/solicitacao/solicitacoes/usuario/${this.id}`);
                this.solicitacoes = response.data;
                console.log(this.solicitacoes);
            } catch (error) {
                console.error("Erro ao buscar solicitações: ", error);
            } finally {
                this.carregando = false;
            }
        },
        mudaFiltro(filtro) {
            this.filtro = filtro;
            this.carregaSolicitacoes();
        }
    }
}
</script>