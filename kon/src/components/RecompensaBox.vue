<template>
    <div class="w-full h-screen flex justify-center items-center fixed top-0 z-[200]">
        <div class="w-full h-full bg-black/70 absolute z-10" @click="fechaBox"></div>

        <div class="bg-zinc-50 rounded-xl py-6 px-8 z-20 w-[600px] min-h-[300px] flex flex-col"
            v-if="!carregandoResgate && !concluiuResgate">
            <div class="w-full pb-4 border-b-[1px] border-zinc-400 text-start flex items-center">
                <h1 class="font-semibold text-gray-800 text-2xl line-clamp-2 grow">{{ recompensa.nome }}</h1>
                <button class="size-fit shrink-0 cursor-pointer" @click="fechaBox"><img src="../assets/close.svg"
                        class="size-[22px]"></button>
            </div>

            <div class="w-full py-4">
                <p class="text-md font-gray-600 text-start">{{ recompensa.descricao }}</p>
            </div>
            <div class="w-full mt-4 text-start">
                <ul class="*:font-semibold">
                    <li>Quantidade: <span class="font-medium">{{ recompensa.estoque }}</span></li>
                    <li>Preço: <span class="font-medium">{{ recompensa.preco }} pontos</span></li>
                </ul>
            </div>

            <div class="w-full mt-4 flex justify-end">
                <button class="px-4 py-2 rounded-md bg-gray-800 text-zinc-50" @click="resgataRecompensa">
                    Resgatar
                </button>
            </div>
        </div>

        <div class="bg-zinc-50 rounded-xl py-6 px-8 z-20 w-[600px] min-h-[250px] flex items-center justify-center"
            v-if="carregandoResgate && !concluiuResgate">
            <span class="loading loading-ring loading-xl"></span>
        </div>

        <div class="bg-zinc-50 rounded-xl py-6 px-8 z-20 w-[600px]" v-if="!carregandoResgate && concluiuResgate">
            <h2 class="font-medium text-left text-2xl">{{ aviso }}</h2>
            <div class="w-full flex justify-end mt-4">
                <button class="px-4 py-2 rounded-md bg-gray-800 text-zinc-50" @click="concluiResgate">
                    Fechar
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "RecompensaBox",
    props: ["recompensa", "usuarioId"],
    mounted() {

    },
    data() {
        return {
            aviso: '',
            carregandoResgate: false,
            concluiuResgate: false,
        }
    },
    methods: {
        fechaBox() {
            this.$emit("fechar-box")
        },
        concluiResgate() {
            this.$emit("conclui-resgate")
        },
        async resgataRecompensa() {
            try {
                this.carregandoResgate = true
                const response = await axios.post(`http://localhost:5000/recompensa/resgate`, {
                    usuarioId: this.usuarioId,
                    recompensaId: this.recompensa.id
                })
                this.aviso = response.data
            } catch (error) {
                console.error("Erro desconhecido ao resgatar recompensa: ", error)
            } finally {
                this.carregandoResgate = false
                this.concluiuResgate = true
            }
        }
    }
}
</script>