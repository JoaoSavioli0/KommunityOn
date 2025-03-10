<template>

    <div class="w-full relative mt-12 z-[150]">
        <div class="fixed">
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
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "Menu",
    mounted(){
        const userStore = useUserStore()
    userStore.reconectaSessao()
    if(userStore.usuario==null){
      this.$router.push("/login")
    }
    }
}
</script>