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

    <div class="w-full mt-12">
        <div class="flex w-fit rounded-lg bg-white shadow-md py-2">
            <div class="flex flex-col items-center px-4 border-r-[1px] border-gray-300">
                <span class="">Conta</span>
            </div>
            <div class="flex flex-col items-center px-4 border-r-[1px] border-gray-300">
                <span class="">Suporte</span>
            </div>
            <div class="flex flex-col items-center px-4 ">
                <span class="">Configurações</span>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Menu"
}
</script>