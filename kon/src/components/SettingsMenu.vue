<template>
    <div class="w-full pl-[140px]">
        <div class="flex flex-col items-start ">
            <h1 class="font-bold text-gray-800 text-2xl mt-2">Configurações</h1>
            <div class="w-full flex flex-col items-start mt-6">

                <div class="w-full text-left w-full relative">
                    <h2 class="text-md font-medium text-gray-700 bg-gray-100 pr-[15px] z-[50] relative w-fit">
                        Informações
                        pessoais</h2>
                    <div class="absolute h-[1px] w-full bg-gray-400 top-[50%] z-[40]"></div>
                </div>

                <div class="w-full max-w-full flex flex-wrap gap-2 mt-4">
                    <div class="flex flex-col items-start w-full">
                        <span class="text-sm">Nome</span>
                        <div
                            class="w-full rounded-md bg-gray-300 text-gray-500 mt-[5px] px-2 py-[4px] min-h-[30px] text-left">
                            {{
                                usuario.nome }}
                        </div>
                    </div>

                    <div class="flex flex-col items-start w-full mt-2">
                        <span class="text-sm">Email</span>
                        <div
                            class="w-full rounded-md bg-gray-300 text-gray-500 mt-[5px] px-2 py-[4px] min-h-[30px] text-left">
                            {{
                                usuario.email }}
                        </div>
                        <a
                            class="text-blue-500 text-xs text-decoration-none font-medium mt-[4px] cursor-pointer">Editar</a>
                    </div>

                    <div class="flex w-full justify-between mt-2">
                        <div class="flex flex-col items-start w-[48%]">
                            <span class="text-sm">Telefone</span>
                            <div
                                class="w-full rounded-md bg-gray-300 text-gray-500 mt-[5px] px-2 py-[4px] text-left relative min-h-[30px]">
                                {{
                                    usuario.telefone }}
                            </div>
                            <a
                                class="text-blue-500 text-xs text-decoration-none font-medium mt-[4px] cursor-pointer">Editar</a>
                        </div>
                        <div class="flex flex-col items-start w-[48%]">
                            <span class="text-sm">CPF</span>
                            <div
                                class="w-full rounded-md bg-gray-300 text-gray-500 mt-[5px] px-2 py-[4px] text-left min-h-[30px]">
                                {{
                                    usuario.cpf }}
                            </div>
                        </div>
                    </div>

                    <div class="flex w-full justify-between mt-2">
                        <div class="flex flex-col items-start w-[48%]">
                            <span class="text-sm">Cidade</span>
                            <div
                                class="w-full rounded-md bg-gray-300 text-gray-500 mt-[5px] px-2 py-[4px] text-left relative min-h-[30px]">
                                {{ endereco.cidade }}
                            </div>
                            <a class="text-blue-500 text-xs text-decoration-none font-medium mt-[4px] cursor-pointer">Editar
                                endereço</a>
                        </div>
                        <div class="flex flex-col items-start w-[48%]">
                            <span class="text-sm">Bairro</span>
                            <div
                                class="w-full rounded-md bg-gray-300 text-gray-500 mt-[5px] px-2 py-[4px] text-left min-h-[30px]">
                                {{ endereco.bairro }}
                            </div>
                        </div>
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