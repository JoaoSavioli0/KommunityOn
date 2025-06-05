<template>
  <!-- Menu -->
  <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="menuBox">
    <div
      class="flex items-start flex-col fixed w-[300px] rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 px-8 py-6">
      <div class="w-full flex justify-between">
        <h1 class="text-xl font-semibold">Menu</h1>
        <button @click="menuBox = false, aviso = ''"><img src="../assets/close.svg" class="size-[20px]"></button>
      </div>
      <ul class="w-full mt-4">
        <RouterLink :to="item.link" class="p-0" v-for="item in itemsMenu">
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

  <!-- Aviso confirma interação -->
  <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="confirmaInteracaoBox">
    <div
      class="flex items-start flex-col fixed w-[90%] lg:w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
      <h1 class="text-xl lg:text-2xl font-semibold text-start">Confirmar interação?</h1>
      <p class="my-2 text-start">Essa ação não poderá ser revertida.</p>
      <div class="w-full flex justify-start mt-4">
        <button class="py-2 w-[150px] text-center rounded-full bg-gray-900 text-white font-medium"
          @click.prevent="confirmaInteracaoBox = false, curteSolicitacao(idSolicitacaoInteragir)">Confirmar</button>
        <button
          class="ml-4 py-2 w-[150px] text-center rounded-full bg-transparent border-2 border-gray-800 text-gray-800 font-medium"
          @click.prevent="confirmaInteracaoBox = false">Cancelar</button>
      </div>
    </div>
  </div>

  <!-- Aviso interacao bloqueada -->
  <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="interacaoBloq">
    <div
      class="flex items-start flex-col fixed w-[90%] lg:w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
      <p class="my-2 text-center font-semibold text-lg">{{ avisoTxt }}</p>
      <div class="w-full flex justify-center mt-4">
        <button class="py-2 w-[150px] text-center rounded-full bg-gray-900 text-white font-medium"
          @click.prevent="interacaoBloq = false, avisoTxt = ''">Ok</button>
      </div>
    </div>
  </div>

  <!-- Menu -->
  <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="menuBox">
    <div
      class="flex items-start flex-col fixed w-[300px] rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 px-8 py-6">
      <div class="w-full flex justify-between">
        <h1 class="text-xl font-semibold">Menu</h1>
        <button @click="menuBox = false, aviso = ''"><img src="../assets/close.svg" class="size-[20px]"></button>
      </div>
      <ul class="w-full mt-4">
        <RouterLink :to="item.link" class="p-0" v-for="item in itemsMenu">
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

  <!-- Aviso confirma interação -->
  <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="confirmaInteracaoBox">
    <div
      class="flex items-start flex-col fixed w-[90%] lg:w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
      <h1 class="text-xl lg:text-2xl font-semibold text-start">Confirmar interação?</h1>
      <p class="my-2 text-start">Essa ação não poderá ser revertida.</p>
      <div class="w-full flex justify-start mt-4">
        <button class="py-2 w-[150px] text-center rounded-full bg-gray-900 text-white font-medium"
          @click.prevent="confirmaInteracaoBox = false, curteSolicitacao(idSolicitacaoInteragir)">Confirmar</button>
        <button
          class="ml-4 py-2 w-[150px] text-center rounded-full bg-transparent border-2 border-gray-800 text-gray-800 font-medium"
          @click.prevent="confirmaInteracaoBox = false">Cancelar</button>
      </div>
    </div>
  </div>

  <!-- Aviso interacao bloqueada -->
  <div class="fixed h-screen w-screen bg-black/50 z-[250] top-0" v-if="interacaoBloq">
    <div
      class="flex items-start flex-col fixed w-[90%] lg:w-[400px] py-10 rounded-lg bg-gray-100 shadow-lg top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 p-8">
      <p class="my-2 text-center font-semibold text-lg">{{ avisoTxt }}</p>
      <div class="w-full flex justify-center mt-4">
        <button class="py-2 w-[150px] text-center rounded-full bg-gray-900 text-white font-medium"
          @click.prevent="interacaoBloq = false, avisoTxt = ''">Ok</button>
      </div>
    </div>
  </div>

  <div class="flex lg:w-[900px] justify-center lg:py-20 py-8 transition-all duration-500">

    <div class="w-[300px] relative px-6 ">
      <div class="fixed h-screen w-[350px] transition-all duration-500 z-10" :class="{
        'opacity-0 -translate-x-full pointer-events-none': !mostraMenuLateral,
        'opacity-100 translate-x-0': mostraMenuLateral
      }">
        <div class="w-[250px] fixed transition-all duration-500">
          <div class="w-full flex">
            <RouterLink :to="`/usuario/${usuario.id}`" class="p-0 rounded-full">
              <div
                class="size-[60px] rounded-full shadow-md overflow-hidden relative flex-shrink-0 group cursor-pointer"
                :class="imagemUsuario ? 'bg-transparent' : 'bg-gray-900'">
                <img v-if="imagemUsuario" :src="imagemUsuario" class="h-full w-full object-cover absolute">
                <img v-else src="../assets/user_body.png" class="filtro absolute bottom-[-15px]">
              </div>
            </RouterLink>
            <div class="flex flex-col ml-2 items-start justify-center">
              <span class="font-semibold text-lg leading-[0.9]">{{ primeiroNome }}</span>
              <span class="text-sm font-medium">{{ enderecoUsuario.bairro }}, {{ enderecoUsuario.cidade }}</span>
            </div>
          </div>
          <div
            class="mt-6 w-full flex flex-col *:px-2 *:flex *:w-full *:h-[40px] *:rounded-md *:transition-all *:duration-100 *:text-gray-900 *:items-center">
            <router-link class="hover:bg-gray-200" to="/new">
              <img src="../assets/acoes-1.svg" class="w-[25px] h-auto">
              <span class="font-medium text-lg ml-2">Nova solicitação</span>
            </router-link>

            <router-link class="hover:bg-gray-200" :to="`/usuario/${usuario.id}`">
              <img src="../assets/acoes-2.svg" class="w-[25px] h-auto">
              <span class="font-medium text-lg ml-2">Sua conta</span>
            </router-link>

            <router-link class="hover:bg-gray-200" to="menu/conta">
              <img src="../assets/settings.svg" class="w-[25px] h-auto">
              <span class="font-medium text-lg ml-2">Configurações</span>
            </router-link>

            <router-link class="hover:bg-gray-200" to="/ecoshop">
              <img src="../assets/sustainable.svg" class="w-[41px] h-auto -mx-2">
              <span class="font-medium text-lg ml-2">EcoShop</span>
            </router-link>

            <router-link class="hover:bg-gray-200" to="menu/suporte">
              <img src="../assets/support.svg" class="w-[25px] h-auto">
              <span class="font-medium text-lg ml-2">Suporte</span>
            </router-link>

            <a @click="logout()" class="hover:bg-gray-200 cursor-pointer">
              <img src="../assets/exit.svg" class="w-[25px] h-auto">
              <span class="font-medium text-lg ml-2">Sair</span>
            </a>
          </div>
        </div>
      </div>
    </div>

    <div class="transition-all duration-500 flex flex-col items-center w-full lg:w-[600px]"
      :class="mostraMenuLateral ? 'translate-x-[0px]' : '-translate-x-[150px]'">

      <!-- Div principal -->
      <div class="w-full flex justify-between">
        <button @click="logout()"
          class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
          <img src="../assets/exit.svg" class="filtro size-[20px]">
          <span class="ml-2 text-xl">Sair</span>
        </button>
        <button @click="menuBox = true"
          class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
          <span class="text-xl">Menu</span>
          <img src="../assets/menu.svg" class="ml-2 filtro size-[20px]">
        </button>
      </div>

      <div class="w-full flex items-center justify-between mt-8">
        <div class="flex flex-col">
          <h1 class="text-3xl lg:text-5xl font-medium text-left text-gray-800">Olá, <span class="font-semibold">{{
            primeiroNome
              }}</span></h1>
          <p class="text-left text-md lg:text-xl font-normal text-gray-600 pr-[30px]">Veja aqui os principais
            chamados
            na
            sua região!
          </p>
        </div>
        <RouterLink :to="`/usuario/${usuario.id}`" class="p-0 rounded-full">
          <div
            class="size-[90px] lg:size-[120px] rounded-full shadow-md overflow-hidden relative flex-shrink-0 group cursor-pointer"
            :class="imagemUsuario ? 'bg-transparent' : 'bg-gray-900'">
            <img v-if="imagemUsuario" :src="imagemUsuario" class="h-full w-full object-cover absolute">
            <img v-else src="../assets/user_body.png" class="filtro absolute bottom-[-15px]">
            <div
              class="absolute w-full h-full bg-black/70 opacity-0 group-hover:opacity-100 transition-all duration-200 flex justify-center items-center pointer-events-none">
              <img src="../assets/user.svg" class="size-[70px] filtro opacity-80">
            </div>
          </div>
        </RouterLink>
      </div>

      <div class="mt-8 relative w-full">
        <RouterLink to="/new" class="p-0 relative w-full">
          <button
            class="w-full rounded-[15px] bg-gray-900 text-white h-[60px] overflow-hidden text-xl font-medium novachamada-button flex justify-center items-center relative">
            <span class="novachamada absolute transition-all duration-200">Nova solicitação</span>
            <span class="novachamada2 absolute transition-all duration-200 text-4xl">+</span>
          </button>
        </RouterLink>
      </div>

      <div class="w-full relative mt-4">
        <RouterLink :to="`/usuario/${usuario.id}`" class="p-0 relative w-full">
          <button
            class="w-full rounded-[15px] border-gray-800 border-2 text-gray-800 h-[50px] overflow-hidden text-xl font-medium flex justify-center items-center relative z-[50] bg-zinc-50 hover:bg-gray-200 transition-all">
            <span class="absolute">Seu perfil</span>
          </button>
        </RouterLink>
      </div>

      <div class="w-full mt-4 rounded-[15px] flex bg-lime-600 py-4 pr-4 text-left">
        <div class="flex shrink-0 pl-2 pr-4">
          <img src="../assets/sustainable.svg" class="w-[100px] h-auto filtro-branco">
        </div>
        <div class="grow-1 flex flex-col justify-center text-zinc-50">
          <h1 class="font-semibold text-2xl">Mês da Enegia Renovável</h1>
          <p>No Mês da Enegia Renovável, envie sugestões sustentáveis ou interaja com elas para ganhar pontos.
            Pontos
            podem ser
            trocados na nossa <router-link to="/ecoshop" class="p-0"><span
                class="font-semibold underline cursor-pointer">loja
                sustentável</span></router-link> .</p>
        </div>
      </div>

      <div class="w-full flex justify-between mt-8 items-center">
        <span class="font-semibold text-xl text-gray-800">Solicitações</span>
        <span class="font-medium text-gray-800 text-lg font-medium cursor-pointer">{{ quantSolicitacoes }}</span>
      </div>

      <div class="w-full mt-6 relative">
        <div class="py-[6px] rounded-md border-[1.5px] border-gray-800 bg-zinc-50 w-full flex justify-start">
          <input type="text" class="px-[12px] focus:outline-none w-full bg-transparent text-gray-700"
            placeholder="Pesquisar" v-model="pesquisa">
          <div class="border-l-[1.5px] border-zinc-400 px-[10px] flex justify-center">
            <button class="p-0" @click="filtrosOpen = !filtrosOpen" ref="filtrosButton">
              <img src="../assets/equalizer.png" class="w-[25px] h-auto filtro-cinza">
            </button>
          </div>
        </div>

        <div v-if="filtrosOpen" ref="filtrosBox"
          class="absolute w-fit rounded-md bg-zinc-50 shadow-md px-4 py-2 end-0 z-[350] text-left">
          <span class="font-bold text-zinc-800 text-sm">Tags</span>
          <ul>
            <li v-for="tag in tags" :key="tag.id" class="flex items-center mt-[5px]">
              <input type="checkbox" :key="tag.id" :value="tag.id" v-model="filtros">
              <span class="ml-2 text-xs font-medium">{{ tag.nome }}</span>
            </li>
          </ul>
        </div>

      </div>

      <form class="w-full">
        <div class="w-full flex justify-between mt-6">

          <label class="w-[22%]">
            <input type="radio" name="option" v-model="exibindo" checked value="destaque" class="hidden peer" />
            <div @click="filtro = 1"
              class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white lg:text-lg text-xs">
              Destaques</div>
          </label>

          <label class="w-[22%]">
            <input type="radio" name="option" v-model="exibindo" value="proximo" class="hidden peer" />
            <div @click="filtro = 2"
              class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white lg:text-lg text-xs">
              Próximos</div>
          </label>

          <label class="w-[22%]">
            <input type="radio" name="option" v-model="exibindo" value="recente" class="hidden peer" />
            <div @click="filtro = 3"
              class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white lg:text-lg text-xs">
              Recentes</div>
          </label>

          <label class="w-[22%]">
            <input type="radio" name="option" v-model="exibindo" value="concluidos" class="hidden peer" />
            <div @click="filtro = 4"
              class="cursor-pointer py-[4px] lg:py-[2px] rounded-full bg-gray-200 border-[1px] border-gray-400 text-gray-400 peer-checked:bg-gray-800 peer-checked:text-white lg:text-lg text-xs">
              Concluídos</div>
          </label>
        </div>
      </form>

      <div v-if="carregando" class="w-full py-24 flex justify-center items-center">
        <span class="loading loading-ring loading-lg"></span>
      </div>

      <!-- Cajo não tenha solicitações -->
      <div v-if="solicitacoesPesquisadas.length == 0 && !carregando"
        class="w-full text-center py-6 text-xl font-medium text-zinc-800">
        <span>Não foram encontradas solicitações para a sua pesquisa!</span>
      </div>

      <!-- Solicitações -->
      <div v-if="solicitacoesPesquisadas.length != 0 && !carregando"
        class="solicitacoes mt-8 flex flex-col gap-y-4 w-full">
        <div class="w-[97%] rounded-[20px] bg-gray-900 flex px-6 py-4 text-left justify-between items-center relative"
          v-for="solicitacao in solicitacoesPesquisadas" :key="solicitacao.id">
          <RouterLink :to="`/solicitacao/${solicitacao.id}`" class="p-0 relative w-full">
            <div class="flex flex-col w-[80%] max-[600px]:w-[95%]">
              <h1 class="text-white font-semibold text-2xl w-[80%] max-[600px]:text-lg">{{ solicitacao.titulo }}
              </h1>
              <p class="text-gray-300 line-clamp-4 w-[80%] max-[600px]:text-xs">{{ solicitacao.descricao }}</p>
              <div class="flex mt-2">
                <div class=" flex items-center text-white mt-[3px]">
                  <img src="../assets/location.png" class="filtro size-[19px] max-[600px]:size-[13px]">
                  <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.bairro }}</span>

                  <div class="ml-8 flex items-center max-[600px]:ml-4">
                    <img src="../assets/comments.png" class="size-[19px] max-[600px]:size-[13px] filtro">
                    <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.numComentarios }}</span>
                  </div>

                  <div class="ml-8 flex items-center max-[600px]:ml-4">
                    <img src="../assets/clock.svg" class="size-[22px] max-[600px]:size-[16px] filtro-atencao"
                      v-if="solicitacao.dataConclusao == null">
                    <img src="../assets/success.svg" class="size-[22px] max-[600px]:size-[16px] filtro-sucesso" v-else>
                  </div>
                </div>

              </div>
            </div>
          </RouterLink>
          <div
            class="border-l-[1px] border-gray-600 pl-6 flex justify-center items-center absolute end-[-23px] lg:end-[-35px]">
            <span class="text-white font-semibold mr-4">{{ solicitacao.numLikes }}</span>
            <div
              class="size-[50px] lg:size-[70px] rounded-full bg-[#F0F4F9] flex items-center justify-center cursor-pointer">
              <div class="heart-container" title="Like" @click.prevent="botaoCurtirEvent(solicitacao.id)">
                <input type="checkbox" class="checkbox" :id="solicitacao.id"
                  :checked="curtidos.includes(solicitacao.id)">
                <div class="svg-container">
                  <svg viewBox="0 0 24 24" class="svg-outline" xmlns="http://www.w3.org/2000/svg">
                    <path
                      d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z">
                    </path>
                  </svg>
                  <svg viewBox="0 0 24 24" class="svg-filled" xmlns="http://www.w3.org/2000/svg">
                    <path
                      d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z">
                    </path>
                  </svg>
                  <svg class="svg-celebrate" width="100" height="100" xmlns="http://www.w3.org/2000/svg">
                    <polygon points="10,10 20,20"></polygon>
                    <polygon points="10,50 20,50"></polygon>
                    <polygon points="20,80 30,70"></polygon>
                    <polygon points="90,10 80,20"></polygon>
                    <polygon points="90,50 80,50"></polygon>
                    <polygon points="80,80 70,70"></polygon>
                  </svg>
                </div>
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
  name: 'HomeView',
  mounted() {
    useUserStore().reconectaSessao()
    if (useUserStore().usuario == null) {
      this.$router.push("/login")
    } else {
      this.usuario = useUserStore().usuario
      this.carregaSolicitacoes()
      this.carregaInteracoes()
      this.carregaMenu()
      this.carregaImagem()
      this.carregaTags()
      this.carregaEndereco()
      document.addEventListener("click", this.fechaJanelas)

      window.addEventListener("scroll", () => {
        if (window.scrollY > 400) {
          this.mostraMenuLateral = true;
        } else {
          this.mostraMenuLateral = false
        }
      });
    }
  },
  data() {
    return {
      exibindo: "",
      solicitacoes: [],
      solicitacoesPesquisadas: [],
      usuario: {},
      carregando: true,
      aviso: '',
      avisoTxt: '',
      avisoBoxTimeOutExec: false,
      curtidos: [],
      like: false,
      confirmaInteracaoBox: false,
      idSolicitacaoInteragir: 0,
      menuBox: false,
      itemsMenu: [],
      pesquisa: '',
      imagemUsuario: null,
      tags: [],
      filtrosOpen: false,
      filtros: [],
      filtro: 1,
      quantSolicitacoes: 0,
      interacaoBloq: false,
      enderecoUsuario: {},
      mostraMenuLateral: false
    }
  },
  methods: {
    fechaJanelas(event) {
      if (this.$refs.filtrosBox && !this.$refs.filtrosBox.contains(event.target) && !this.$refs.filtrosButton.contains(event.target)) {
        this.filtrosOpen = false
      }
    },
    async carregaImagem() {
      this.imagemUsuario = null
      try {
        const response = await axios.get(`http://localhost:5000/usuario/foto-perfil/${this.usuario.id}`)

        if (response.data != "sem foto") {
          this.imagemUsuario = `data:image/png;base64,${response.data}`;
        }
      } catch (error) {
        console.error("Ocorreu um erro ao carregar a foto de usuário: " + error)
      }

    },

    async carregaEndereco() {
      try {
        const endereco = await axios.get(`http://localhost:5000/endereco/${this.usuario.id}`)
        this.enderecoUsuario = endereco.data
      } catch (error) {
        console.log("Erro ao carregar endereço do usuário!")
      }
    },

    async carregaSolicitacoes() {
      try {
        const response = await axios.post(`http://localhost:5000/solicitacao/solicitacoes`, this.filtros, {
          headers: {
            "Content-Type": "application/json",
          },
        })

        if (!this.quantSolicitacoes) {
          this.quantSolicitacoes = response.data.length
        }

        this.solicitacoes = response.data
        this.solicitacoesPesquisadas = [...this.solicitacoes]
        console.log(this.solicitacoesPesquisadas)
        this.filtro = 1
      } catch (error) {
        console.error("Erro ao buscar solicitações: ", error);
      } finally {
        this.carregando = false;
      }
    },
    async carregaInteracoes() {
      try {
        const response = await axios.get(`http://localhost:5000/usuario/interacoes/${this.usuario.id}`);
        this.curtidos = response.data
      } catch (error) {
        console.error("Erro ao buscar interações do usuário: ", error);
      }
    },
    async curteSolicitacao(idSolicitacao) {
      try {
        const response = await axios.get(`http://localhost:5000/usuario/curtir/${this.usuario.id}/${idSolicitacao}`);
        if (response.data.includes("Sucesso")) {
          this.curtidos.push(idSolicitacao)
          this.carregaSolicitacoes();
        } else {
          this.avisoTxt = response.data
          this.interacaoBloq = true
        }
      } catch (error) {
        console.error("Erro ao curtir solicitação: " + error)
      }
    },
    carregaMenu() {
      this.itemsMenu.push(
        { id: 1, title: "Conta", link: `menu/conta`, svg: require("@/assets/user.svg") },
        { id: 2, title: "Configurações", link: `menu/configuracoes`, svg: require("@/assets/settings.svg") },
        { id: 3, title: "Suporte", link: `menu/suporte`, svg: require("@/assets/support.svg") }
      )
    },
    async carregaTags() {
      try {
        const response = await axios.post("http://localhost:5000/tag/all")
        this.tags = response.data
      } catch (error) {
        console.error("Erro ao carregar filtros: " + error)
      }
    },
    mudaFiltro(filtro) {
      switch (filtro) {
        case 1: this.ordenaLike(); break;
        case 2:
        case 3: this.ordenaData(); break;
        case 4: this.ordenaConcluido(); break;
      }
    },

    ordenaData() {
      this.solicitacoesPesquisadas = [...this.solicitacoes]
      if (this.pesquisa) { this.pesquisaSolicitacao() }
      this.solicitacoesPesquisadas.sort((a, b) => {
        // ordena por data
        const dataA = new Date(a.dataAbertura);
        const dataB = new Date(b.dataAbertura);

        if (dataA > dataB) return -1;
        if (dataA < dataB) return 1;

        // se as datas forem iguais ordena pelos likes
        return b.numLikes - a.numLikes;
      });
    },

    ordenaConcluido() {
      this.solicitacoesPesquisadas = [...this.solicitacoes]
      if (this.pesquisa) { this.pesquisaSolicitacao() }
      this.solicitacoesPesquisadas = this.solicitacoesPesquisadas.filter(solicitacao => solicitacao.dataConclusao != null)
    },

    ordenaLike() {
      this.solicitacoesPesquisadas = [...this.solicitacoes]
      if (this.pesquisa) { this.pesquisaSolicitacao() }

      this.solicitacoesPesquisadas.sort((a, b) => {
        // ordena por data
        const likesA = a.numLikes
        const likesB = b.numLikes
        const dataA = new Date(a.dataAbertura);
        const dataB = new Date(b.dataAbertura);

        if (likesA > likesB) return -1;
        if (likesA < likesB) return 1;

        // se as datas forem iguais ordena pelos likes
        return dataB - dataA;
      });
    },

    logout() {
      const userStore = useUserStore();
      userStore.logout();
      this.$router.push("/login")
    },
    botaoCurtirEvent(id) {
      if (!this.curtidos.includes(id)) {
        this.idSolicitacaoInteragir = id;
        this.confirmaInteracaoBox = true;
      }
    },
    pesquisaSolicitacao() {
      if (this.pesquisa) {
        const removerAcentos = (str) => str.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();

        const pesquisaNormalizada = removerAcentos(this.pesquisa);

        this.solicitacoesPesquisadas = this.solicitacoesPesquisadas.filter((s) => {
          const tituloNormalizado = removerAcentos(s.titulo);
          const descricaoNormalizada = removerAcentos(s.descricao);
          const bairroNormalizado = removerAcentos(s.bairro);

          return (
            tituloNormalizado.includes(pesquisaNormalizada) ||
            descricaoNormalizada.includes(pesquisaNormalizada) ||
            bairroNormalizado.includes(pesquisaNormalizada) ||
            pesquisaNormalizada.includes(tituloNormalizado) ||
            pesquisaNormalizada.includes(descricaoNormalizada) ||
            pesquisaNormalizada.includes(bairroNormalizado)
          );
        });
      } else {
        this.mudaFiltro(this.filtro)
      }
    }
  },
  computed: {
    primeiroNome() {
      if (!this.usuario || !this.usuario.nome) {
        return "";
      }
      return this.usuario.nome.split(' ')[0];
    }
  },
  watch: {
    pesquisa() {
      this.pesquisaSolicitacao()
    },
    filtros() {
      this.carregaSolicitacoes()
    },
    filtro() {
      this.mudaFiltro(this.filtro)
    }
  }
}
</script>

<style>
.slide-enter-active,
.slide-leave-active {
  transition: all 0.5s ease;
}

.slide-enter-from {
  opacity: 0;
  transform: translateX(-50px);
}

.slide-enter-to {
  opacity: 1;
  transform: translateX(0);
}

.slide-leave-from {
  opacity: 1;
  transform: translateX(0);
}

.slide-leave-to {
  opacity: 0;
  transform: translateX(-50px);
}

.filtro {
  filter: brightness(0) saturate(100%) invert(100%) sepia(3%) saturate(11%) hue-rotate(346deg) brightness(104%) contrast(103%);
}

.filtro2 {
  filter: brightness(0) saturate(100%) invert(16%) sepia(34%) saturate(15%) hue-rotate(320deg) brightness(95%) contrast(93%);
}

.filtro-gray-800 {
  filter: brightness(0) saturate(100%) invert(11%) sepia(24%) saturate(948%) hue-rotate(174deg) brightness(95%) contrast(88%);
}

.filtro-cinza {
  filter: brightness(0) saturate(100%)
}

.filtro-verde {
  filter: brightness(0) saturate(100%) invert(71%) sepia(41%) saturate(7052%) hue-rotate(49deg) brightness(93%) contrast(90%);
}

.filtro-branco {
  filter: brightness(0) saturate(100%) invert(92%) sepia(0%) saturate(7107%) hue-rotate(191deg) brightness(107%) contrast(96%);
}

.novachamada2 {
  transform: translateY(60px);
}

.novachamada-button:hover .novachamada {
  transform: translateY(-60px);
}

.novachamada-button:hover .novachamada2 {
  transform: translateY(0px);
}

@media screen and (max-width:640px) {
  .heart-container {
    width: 33px !important;
    height: 33px !important;
  }
}

.heart-container {
  --heart-color: #1F2937;
  position: relative;
  width: 40px;
  height: 40px;
  transition: .3s;
}

.heart-container .checkbox {
  position: absolute;
  width: 100%;
  height: 100%;
  opacity: 0;
  z-index: 20;
  cursor: pointer;
}

.heart-container .svg-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.heart-container .svg-outline,
.heart-container .svg-filled {
  fill: var(--heart-color);
  position: absolute;
}

.heart-container .svg-filled {
  animation: keyframes-svg-filled 1s;
  display: none;
}

.heart-container .svg-celebrate {
  position: absolute;
  animation: keyframes-svg-celebrate .5s;
  animation-fill-mode: forwards;
  display: none;
  stroke: var(--heart-color);
  fill: var(--heart-color);
  stroke-width: 2px;
}

.heart-container .checkbox:checked~.svg-container .svg-filled {
  display: block
}

.heart-container .checkbox:checked~.svg-container .svg-celebrate {
  display: block
}

@keyframes keyframes-svg-filled {
  0% {
    transform: scale(0);
  }

  25% {
    transform: scale(1.2);
  }

  50% {
    transform: scale(1);
    filter: brightness(1.5);
  }
}

@keyframes keyframes-svg-celebrate {
  0% {
    transform: scale(0);
  }

  50% {
    opacity: 1;
    filter: brightness(1.5);
  }

  100% {
    transform: scale(1.4);
    opacity: 0;
    display: none;
  }
}

.menu-item:nth-last-child(1) {
  border: none;
  border-radius: 0px 0px 5px 5px;
}

.menu-item:nth-child(1) {
  border-radius: 5px 5px 0px 0px;
}
</style>