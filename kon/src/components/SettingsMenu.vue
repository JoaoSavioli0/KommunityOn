<template>
    <div class="w-full lg:pl-[190px]">
        <div class="flex flex-col items-start ">
            <h1 class="font-bold text-gray-800 text-2xl mt-2">Configurações</h1>
            <div class="w-full bg-gray-900 text-zinc-50 mt-6 px-2 py-4 rounded-md">
                <span>Nessa página serão exibidas as opções de configuração de preferências do
                    usuário.</span>
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

    name: 'SettingsMenu',
    data() {
        return {
            usuario: {},
            endereco: {}
        }
    },
    mounted() {
        const userStore = useUserStore()
        this.usuario = userStore.usuario
        this.carregaEndereco()
    },
    methods: {
        async carregaEndereco() {
            try {
                const endereco = await axios.get(`http://localhost:8080/endereco/${this.usuario.id}`)
                this.endereco = endereco.data
            } catch (error) {
                console.log("Erro ao buscar endereço de usuário" + error)
            }

        }
    }
}
</script>