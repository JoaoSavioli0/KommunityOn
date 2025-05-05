<template>
    <RecompensaBox :recompensa="recompensaBox" :usuarioId="usuario.id" v-if="recompensaBox != 0"
        @fechar-box="recompensaBox = 0" @conclui-resgate="recarregaDados()" />
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
        <div class="w-full mt-8 text-start flex items-center">
            <img src="../assets/sustainable.svg" class="w-[80px] h-auto filtro-verde -ml-4">
            <h1 class="text-3xl lg:text-5xl font-semibold text-lime-600 -ml-2">Eco<span
                    class="text-gray-800 font-light">Shop</span>
            </h1>
        </div>
        <div class="w-full mt-2">
            <p class="text-left text-md lg:text-xl font-normal text-gray-600">
                Gaste seus
                <img src="../assets/ticket.svg" class="size-[18px] inline-block align-middle ml-1 -mr-1" />
                <span class="font-medium"> pontos</span> na <span class="font-medium">Ecoshop</span> e resgate
                recompensas.
            </p>
        </div>

        <div class="w-full grid grid-cols-2 gap-4 mt-4">
            <div
                class="rounded-xl bg-lime-600 text-zinc-50 flex flex-col justify-around items-center pb-4 px-2 overflow-hidden">
                <img src="../assets/sustainable.svg" class="size-[60px] shrink-0 filtro-branco ">
                <span class="text-sm font-medium">Ganhe pontos criando e interagindo com solicitações
                    sustentáveis</span>
            </div>
            <div
                class=" rounded-xl border border-lime-600 text-lime-600 flex flex-col justify-around items-center pt-4 pb-4 px-2 overflow-hidden">
                <img src="../assets/trophy.svg" class="size-[35px] shrink-0 filtro-verde py-[3px]">
                <span class="text-sm font-medium">Solicitações sustentáveis que forem solucionadas receberão prêmios
                    exclusivos</span>
            </div>
            <div class="col-span-2 rounded-xl bg-lime-600 text-zinc-50 flex items-center px-6 py-4">
                <img src="../assets/happy.svg" class="size-[50px] shrink-0 filtro-branco">
                <span class="text-sm font-medium ml-4 text-left">Receba conquistas exclusivas para exibir no seu pefil e
                    troque seus
                    pontos por recompensas na EcoShop</span>
            </div>
        </div>

        <div class="w-full mt-6 flex gap-x-4">
            <div class="flex rounded-xl bg-lime-600 h-[50px] px-4 items-center w-fit">
                <img src="../assets/ticket.svg" class="w-[30px] h-auto filtro-branco">
                <span class="ml-2 font-semibold text-zinc-50 text-xl">{{ pontos }}</span>
            </div>

            <router-link to="/recompensas" class="p-0">
                <button
                    class="px-[15px] h-[50px] rounded-xl border border-lime-600 text-lime-600 bg-zinc-50 font-semibold">
                    Minhas recompensas
                </button>
            </router-link>
        </div>


        <div class="w-full mt-12 py-2 border-b-[1px] border-zinc-400 flex justify-between">
            <h2 class="text-2xl text-gray-800 font-medium text-start">Recompensas disponíveis</h2>
            <span class="text-2xl pr-2">{{ recompensas.length }}</span>
        </div>

        <div class="w-full grid grid-cols-2 gap-4 mt-6">
            <button
                class="border-[1px] px-4 py-2 border-zinc-300 rounded-md shadow-lg hover:shadow-xl hover:-translate-y-[2px] transition-all duration-200 bg-gray-100 flex overflow-hidden min-w-0 cursor-pointer"
                v-for="recompensa in recompensas" @click="recompensaBox = recompensa" :key="recompensa.id">
                <span class="font-semibold line-clamp-2 w-[75%] text-start">{{ recompensa.nome }}</span>
                <div class="pl-4 border-l-[1px] border-zinc-300 grow text-end flex items-center">
                    <span class="font-bold">{{ recompensa.preco }}</span>
                    <img src="../assets/ticket.svg" class="size-[18px] ml-[4px] shrink-0">
                </div>
            </button>

        </div>
    </div>
</template>

<script>
import axios from 'axios'
import { useUserStore } from '@/stores/userStore'
import RecompensaBox from '@/components/RecompensaBox.vue'

export default {
    name: "EcoShop",
    data() {
        return {
            pontos: 0,
            usuario: {},
            recompensas: [],
            recompensaBox: 0
        }
    },
    components: {
        RecompensaBox
    },
    mounted() {
        useUserStore().reconectaSessao()
        if (useUserStore().usuario == null) {
            this.$router.push("/login")
        } else {
            this.usuario = useUserStore().usuario
            this.carregaDados()
        }
    },
    methods: {
        async carregaDados() {
            this.carregaPontos()
            this.carregaRecompensas()
        },

        recarregaDados() {
            this.recompensaBox = 0
            this.carregaDados()
        },

        async carregaPontos() {
            try {
                const response = await axios.get(`http://localhost:5000/ecopoint/${this.usuario.id}`)
                this.pontos = response.data
            } catch (error) {
                console.error("Erro ao carregar pontos", error.response.data)
            }
        },
        async carregaRecompensas() {
            try {
                const response = await axios.get(`http://localhost:5000/recompensa/recompensas`)
                this.recompensas = response.data
            } catch (error) {
                console.error("Erro ao carregar recompensas: ", error)
            }
        }
    }
}
</script>

<style scoped>
.filtro-verde {
    filter: brightness(0) saturate(100%) invert(71%) sepia(41%) saturate(7052%) hue-rotate(49deg) brightness(93%) contrast(90%);
}

.filtro-branco {
    filter: brightness(0) saturate(100%) invert(92%) sepia(0%) saturate(7107%) hue-rotate(191deg) brightness(107%) contrast(96%);
}
</style>