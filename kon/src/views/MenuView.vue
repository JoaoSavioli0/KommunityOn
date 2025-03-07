<template>

    <div class="w-full relative mt-12 z-[150]">
        <router-link to="/home" class="p-0 fixed start-[500px] z-[150] top-[100px]">
            <button @click=""
                class="bg-gray-800 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
                <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                <span class="ml-2 text-xl">Voltar</span>
            </button>
        </router-link>
        <ul class="menu bg-white text-left rounded-box w-40 fixed start-[440px]">
            <li class="menu-title">Menu</li>
            <router-link to="/menu/conta">
                <li><a class="font-medium text-gray-800">Conta</a></li>
            </router-link>
            <router-link to="/menu/configuracoes">
                <li><a class="font-medium text-gray-800">Configurações</a></li>
            </router-link>
            <router-link to="/menu/suporte">
                <li><a class="font-medium text-gray-800">Configurações</a></li>
            </router-link>
        </ul>

        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "Menu"
}
</script>