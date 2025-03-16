<template>
    <div class="w-full pl-[180px]">
        <div class="flex flex-col items-start w-full">
            <h1 class="font-bold text-gray-800 text-2xl mt-2">Suporte</h1>
            <div class="w-full flex flex-col items-start mt-6">

                <div class="w-full text-left w-full relative">
                    <h2 class="text-md font-medium text-gray-700 bg-gray-100 pr-[15px] z-[50] relative w-fit">
                        Atendimento</h2>
                    <div class="absolute h-[1px] w-full bg-gray-400 top-[50%] z-[40]"></div>
                </div>

                <div class="w-full max-w-full flex flex-col gap-y-4 mt-4 ">
                    <h1 class="text-lg font-medium text-gray-800 text-start">Escolha seu problema:</h1>
                    <div class="rounded-md bg-gray-900 text-gray-100 text-center font-medium w-full py-[5px] relative cursor-pointer"
                        v-for="opcao in opcoes" :key="opcao.id">
                        {{ opcao.text }}
                        <img src="../assets/arrow.png"
                            class="w-[15px] filtro h-auto end-[25px] absolute top-1/2 -translate-y-1/2">
                    </div>
                </div>

                <div class="w-full text-left w-full relative mt-12">
                    <h2 class="text-md font-medium text-gray-700 bg-gray-100 pr-[15px] z-[50] relative w-fit">
                        Perguntas Frequentes</h2>
                    <div class="absolute h-[1px] w-full bg-gray-400 top-[50%] z-[40]"></div>
                </div>

                <div class="collapse bg-gray-900 border-base-300 border py-[5px] rounded-md text-gray-100 mt-4 text-start">
                    <input type="checkbox" />
                    <div class="collapse-title font-medium h-min">Por que não consigo abrir uma solicitação?</div>
                    <div class="collapse-content text-sm text-start">
                        <br>Você não poderá abrir uma solicitação caso: 
                        <ul class="list-disc ml-4">
                            <li>Você já possua uma solicitação em aberto<br><a href="" class="text-sky-500">Verifique suas solicitações aqui</a></li>
                            <li>Você não tenha confirmado seu endereço. <br><a href="" class="text-sky-500">Confirme seu endereço aqui</a></li>
                            <li>As ações da sua conta tenham sido limitadas por ferir as nossas diretrizes.</li>
                        </ul>
                    </div>
                </div>

                <div class="collapse bg-gray-900 border-base-300 border py-[5px] rounded-md text-gray-100 mt-4 text-start">
                    <input type="checkbox" />
                    <div class="collapse-title font-medium h-min">Quanto tempo para resolverem minha solicitação?</div>
                    <div class="collapse-content text-sm text-start">
                        <br>No KommunityOn, as solicitações são selecionadas a partir da interação popular, as solicitações com maior destaque terão prioridade. Você pode ver a situação das suas solicitações <a :href="`/solicitacoes/${usuarioId}`" class="text-sky-500">aqui</a>
                    </div>
                </div>

                <div class="collapse bg-gray-900 border-base-300 border py-[5px] rounded-md text-gray-100 mt-4 text-start">
                    <input type="checkbox" />
                    <div class="collapse-title font-medium h-min">Por que não consigo curtir uma solicitação?</div>
                    <div class="collapse-content text-sm text-start">
                        <br>No KommunityOn, as interações positivas em uma solicitação são o indicativo mais importante de que aquele pedido precisa de atenção, por isso, visando evitar a desvalorização da importância desse indicativo, limitamos a quantidade de curtidas que um usuário pode enviar. <br><br>Ao se cadastrar, o usuário pode interagir com até duas solicitações, e <b>terá que esperar 7 dias</b> para poder curtir outra solicitação novamente. 
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
            ],
            usuario: null,
            usuarioId: 0
        }
    },
    mounted() {
        this.usuario = useUserStore().usuario
        this.usuarioId = this.usuario.id
    },
    methods: {

    }
}
</script>