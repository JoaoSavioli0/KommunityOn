<template>
    <div class="w-full h-screen flex justify-center items-center fixed top-0 z-[200]">
        <div class="w-full h-full bg-black/70 absolute z-10" @click="fechaBox"></div>

        <div class="bg-zinc-50 rounded-xl py-6 px-8 z-20 w-[600px] min-h-[300px] flex flex-col"
            v-if="!carregandoAtivacao && !concluiuAtivacao">
            <div class="w-full pb-4 border-b-[1px] border-zinc-400 text-start flex items-center">
                <h1 class="font-semibold text-gray-800 text-2xl line-clamp-2 grow">{{ recompensas[0].nome }}</h1>
                <button class="size-fit shrink-0 cursor-pointer" @click="fechaBox"><img src="../assets/close.svg"
                        class="size-[22px]"></button>
            </div>

            <div class="w-full py-4">
                <p class="text-md font-gray-600 text-start">{{ recompensas[0].descricao }}</p>
            </div>

            <div class="w-full py-2 mt-2 border-b-[1px] border-zinc-300 text-left">
                <h1 class="text-2xl font-medium">Você possui: <span class="font-semibold">{{
                    recompensas.length
                        }}</span></h1>
            </div>

            <div class="w-full grid grid-col-1 gap-4 mt-8">
                <div class="w-full rounded-lg border-[1px] border-zinc-400 shadow-md px-4 py-2 flex justify-between items-center"
                    v-for="recompensa in recompensas">
                    <span>{{ recompensa.nome }}</span>
                    <button class="w-[60px] py-2 rounded-md bg-gray-800 text-zinc-50 font-semibold text-xs"
                        @click="() => { if (!recompensa.ativado) ativaItem(recompensa.idUnico) }">
                        {{ recompensa.ativado ? 'Ativado' : 'Ativar' }}
                    </button>
                </div>
            </div>
        </div>

        <div class="bg-zinc-50 rounded-xl py-6 px-8 z-20 w-[600px] min-h-[250px] flex items-center justify-center"
            v-else-if="carregandoAtivacao && !concluiuAtivacao">
            <span class="loading loading-ring loading-xl"></span>
        </div>

        <div class="bg-zinc-50 rounded-xl py-6 px-8 z-20 w-[600px]" v-else-if="!carregandoAtivacao && concluiuAtivacao">
            <h2 class="font-medium text-left text-2xl">{{ aviso }}</h2>
            <div class="w-full flex justify-end mt-4">
                <button class="px-4 py-2 rounded-md bg-gray-800 text-zinc-50" @click="concluiAtivacao">
                    Voltar
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "RecompensaResgatada",
    props: ["recompensas"],
    mounted() {

    },
    data() {
        return {
            aviso: '',
            carregandoAtivacao: false,
            concluiuAtivacao: false,
        }
    },
    methods: {
        fechaBox() {
            this.$emit("fechar-box")
        },
        concluiAtivacao() {
            this.$emit("conclui-ativacao")
        },
        async ativaItem(idUnica) {
            try {
                this.carregandoAtivacao = true
                const response = await axios.post(`http://localhost:5000/recompensa/ativar/${idUnica}`)
                this.aviso = response.data
            } catch (error) {
                console.error("Erro desconhecido ao ativar recompensa: ", error)
            } finally {
                this.carregandoAtivacao = false
                this.concluiuAtivacao = true
            }
        }
    }
}
</script>