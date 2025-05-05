<template>
    <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0 py-8 lg:py-20" v-if="menuBox">
        <div
            class="flex items-start flex-col fixed w-[300px] rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 px-8 py-6">
            <div class="w-full flex justify-between">
                <h1 class="text-xl font-semibold">Menu</h1>
                <button @click="menuBox = false, aviso = ''"><img src="../assets/close.svg"
                        class="size-[20px]"></button>
            </div>
            <ul class="w-full mt-4">
                <RouterLink :to="item.link" class="p-0" v-for="item in itemsMenu" @click="menuBox = false">
                    <li
                        class="p-2 border-b-[1px] border-gray-400 text-left font-medium menu-item hover:bg-gray-200 transition-all duration-200 cursor-pointer flex items-center group">

                        <img :src="item.svg" class="size-[20px]"><span
                            class="ml-2 group-hover:translate-x-2 transition-all duration-200 text-gray-800">{{
                                item.title }}</span>

                    </li>
                </RouterLink>
            </ul>
        </div>
    </div>

    <div class="w-full lg:w-[550px] relative z-[150] py-8 lg:py-20">
        <div class="lg:hidden w-full relative flex justify-between">
            <router-link to="/home" class="p-0 z-[150]">
                <button @click=""
                    class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer ml-auto">
                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                    <span class="ml-2 text-xl">Voltar</span>
                </button>
            </router-link>

            <button @click="menuBox = true"
                class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
                <span class="text-xl">Menu</span>
                <img src="../assets/menu.svg" class="ml-2 filtro size-[20px]">
            </button>
        </div>

        <div class="lg:flex flex-col fixed hidden">
            <router-link to="/home" class="p-0 z-[150] ">
                <button @click=""
                    class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer ml-auto">
                    <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                    <span class="ml-2 text-xl">Voltar</span>
                </button>
            </router-link>
            <ul class="menu bg-white text-left rounded-box w-40 start mt-4">
                <li class="menu-title text-gray-800">Menu</li>
                <router-link to="/menu/conta">
                    <li><a class="font-medium text-gray-800">Conta</a></li>
                </router-link>
                <router-link to="/menu/configuracoes">
                    <li><a class="font-medium text-gray-800">Configurações</a></li>
                </router-link>
                <router-link to="/menu/suporte">
                    <li><a class="font-medium text-gray-800">Suporte</a></li>
                </router-link>
            </ul>
        </div>

        <div class="mt-12 lg:mt-0">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
import { useUserStore } from '@/stores/userStore';

export default {
    name: "Menu",
    data() {
        return {
            menuBox: false,
            itemsMenu: []
        }
    },
    methods: {
        carregaMenu() {
            this.itemsMenu.push(
                { id: 1, title: "Conta", link: `conta`, svg: require("@/assets/user.svg") },
                { id: 2, title: "Configurações", link: `configuracoes`, svg: require("@/assets/settings.svg") },
                { id: 3, title: "Suporte", link: `suporte`, svg: require("@/assets/support.svg") }
            )
        },
    },
    mounted() {
        useUserStore().reconectaSessao()
        if (useUserStore().usuario == null) {
            this.$router.push("/login")
        } else {
            this.carregaMenu()
        }
    }
}
</script>