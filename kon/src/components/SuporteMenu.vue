<template>
    <div class="w-full pl-[140px]">
        <div class="flex flex-col items-start ">
            <h1 class="font-bold text-gray-800 text-2xl mt-2">Suporte</h1>
            <div class="w-full flex flex-col items-start mt-6">

                <div class="w-full text-left w-full relative">
                    <h2 class="text-md font-medium text-gray-700 bg-gray-100 pr-[15px] z-[50] relative w-fit">
                        Atendimento</h2>
                    <div class="absolute h-[1px] w-full bg-gray-400 top-[50%] z-[40]"></div>
                </div>

                <div class="w-full max-w-full flex flex-col gap-y-4 mt-4 ">
                    <h1 class="text-lg font-medium text-gray-800 text-start">Escolha seu problema:</h1>
                    <div class="rounded-md bg-gray-800 text-gray-100 text-center w-full py-[5px] relative"
                        v-for="opcao in opcoes" :key="opcao.id">
                        {{ opcao.text }}
                        <img src="../assets/arrow.png"
                            class="w-[15px] filtro h-auto end-[25px] absolute top-1/2 -translate-y-1/2">
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
import { useRouter } from "vue-router"

export default {

    name: 'SuporteMenu',
    data() {
        return {
            opcoes: [
                { id: 1, text: "Problemas com solicitações" },
                { id: 2, text: "Problemas com a conta" },
                { id: 3, text: "Outros" }
            ]
        }
    },
    mounted() {

    },
    methods: {

    }
}
</script>