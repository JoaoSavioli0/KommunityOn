<template>
    <section class="w-full h-full bg-[#F0F4F9]">
        <div class="w-full h-full flex justify-center items-center flex-col">
            <transition-group name="move" tag="div" class="flex flex-col items-center">
                <div key="divLogin"
                    class="w-[400px] rounded-[30px] bg-white p-8 flex flex-col justify-between shadow-sm transition-all duration-300">
                    <div class="w-full">
                        <div class="w-full flex">
                            <h1 class="text-zinc-800 font-medium"><span class="font-semibold">K</span>ommunity<span
                                    class="font-semibold">ON</span></h1>
                        </div>
                        <div class="w-full my-4 pl-[1px] text-left">
                            <h1 class="text-2xl font-semibold"><span class="font-normal">Faça seu</span> Cadastro</h1>
                        </div>
                    </div>
                    <div class="w-full flex flex-col mt-6">
                        <div class="w-full relative">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Nome</span>
                            <input type="text" v-model="nome"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">E-mail</span>
                            <input type="text" v-model="email"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">CPF</span>
                            <input type="text" v-model="cpf"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Telefone</span>
                            <input type="text" v-model="telefone"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Senha</span>
                            <input type="password" v-model="senha"
                                class="border-[2px] transition-all rounded-[10px] border-gray-800 py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Confirme a
                                senha</span>
                            <input type="password" v-model="senhaConfirm"
                                class="border-[2px] transition-all rounded-[10px] border-gray-800 py-2 px-4 outline-none w-full">
                        </div>

                        <div class="w-full relative mt-6 flex py-4">
                            <div class="h-[1px] bg-gray-300 w-full relative flex justify-center"><span
                                    class="absolute top-[-12px] bg-white px-2 font-medium">Endereço</span></div>
                        </div>

                        <div class="w-full relative mt-6 flex justify-between">
                            <div class="w-[125px]">
                                <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Estado</span>
                                <select type="text" v-model="uf"
                                    class="border-[2px] transition-all rounded-[10px] border-gray-800 h-[43px] px-4 outline-none w-full">
                                    <option value="SP">SP</option>
                                </select>
                            </div>
                            <div class="grow pl-2">
                                <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Cidade</span>
                                <input type="text" v-model="cidade"
                                    class="border-[2px] transition-all rounded-[10px] border-gray-800 h-[43px] px-4 outline-none w-full">
                            </div>
                        </div>

                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Bairro</span>
                            <input type="text" v-model="bairro"
                                class="border-[2px] transition-all rounded-[10px] border-gray-800 py-2 px-4 outline-none w-full">
                        </div>

                        <div class="w-full relative mt-8">
                            <button @click.prevent="fazCadastro()"
                                class="bg-gray-800 font-regular rounded-[10px] py-4 outline-none w-full text-white">Cadastrar</button>
                        </div>
                        <div class="w-full relative mt-6 flex">
                            <div class="h-[1px] bg-gray-300 w-full relative flex justify-center"><span
                                    class="absolute top-[-12px] bg-white px-2 font-medium">Ou</span></div>
                        </div>
                        <RouterLink to="/login" class="p-0">
                            <div class="w-full relative mt-8">
                                <button
                                    class="border-2 text-gray-800 border-gray-800 font-regular rounded-[10px] py-6 outline-none w-full transition-all duration-300 overflow-hidden relative cadastrese-button flex justify-center items-center">
                                    <span class="cadastrese absolute transition-all duration-300">Fazer login</span>
                                    <span class="cadastrese2 absolute transition-all duration-300">Fazer login</span>
                                </button>
                            </div>
                        </RouterLink>
                    </div>
                </div>

                <div key="divAviso"
                    class="mt-4 w-[400px] py-4 rounded-[30px] bg-gray-800 p-8 flex justify-center items-center transition-all duration-300"
                    v-if="aviso != ''">
                    <p class="text-white">{{ aviso }}</p>
                </div>
            </transition-group>
        </div>
    </section>
</template>

<script>
import axios from 'axios';
import { useUserStore } from '@/stores/userStore';

export default {
    name: "CadastroView",
    data() {
        return {
            nome: '',
            cpf: '',
            email: '',
            telefone: '',
            senha: '',
            senhaConfirm: '',
            uf: '',
            cidade: '',
            bairro: '',
            aviso: '',
        }
    },
    methods: {
        async fazCadastro() {
            if (this.usuario == '' || this.senha == '') {
                this.aviso = 'Prencha todos os campos para realizar o cadastro!'
                return;
            } else {
                try {
                    const novoUsuario = await axios.post("http://localhost:8080/usuario/cadastro", {
                        nome: this.nome,
                        email: this.email,
                        cpf: this.cpf,
                        senha: this.senha,
                        telefone: this.telefone
                    }, {
                        headers: {
                            "Content-Type": "application/json",
                        },
                    })

                    console.log(novoUsuario.data.id)

                    const usuarioEndereco = await axios.post("http://localhost:8080/endereco/cadastro", {
                        uf: this.uf,
                        bairro: this.bairro,
                        cidade: this.cidade,
                        usuario: {
                            id: novoUsuario.data.id
                        }
                    }, {
                        headers: {
                            "Content-Type": "application/json",
                        },
                    })

                    if ((novoUsuario && novoUsuario.status == 200) && (usuarioEndereco && usuarioEndereco.status == 200)) {
                        this.aviso = "Cadastro realizado com sucesso!\nAcesse sua conta realizando o login"
                        this.resetaCampos()
                    }

                } catch (err) {
                    console.log(err)
                    if (err.response && err.response.status === 404) {
                        this.aviso = "Senha incorreta."
                    }
                }
            }
            setTimeout(() => { this.aviso = '' }, 4000);
        },
        resetaCampos() {
            this.nome = '',
                this.email = '',
                this.cpf = '',
                this.senha = '',
                this.senhaConfirm = '',
                this.telefone = '',
                this.uf = '',
                this.cidade = '',
                this.bairro = ''
        }
    }
}
</script>

<style>
/* Entrada */
.move-enter-active,
.move-leave-active {
    transition: all 0.3s ease;
}

.move-enter-from {
    transform: translateY(20px);
    opacity: 0;
}

.move-enter-to {
    transform: translateY(0);
    opacity: 1;
}

/* Saída */
.move-leave-from {
    transform: translateY(0);
    opacity: 1;
}

.move-leave-to {
    transform: translateY(20px);
    opacity: 0;
}

.cadastrese2 {
    transform: translateY(60px);
}

.cadastrese-button:hover .cadastrese {
    transform: translateY(-60px);
}

.cadastrese-button:hover .cadastrese2 {
    transform: translateY(0px);
}

input:-webkit-autofill {
    appearance: none;
    /* Remove o estilo padrão */
    background-color: transparent !important;
    /* Define o fundo transparente */
    color: inherit !important;
    /* Herda a cor do texto */
    -webkit-text-fill-color: inherit;
    /* Herda a cor do texto preenchido */
    box-shadow: 0 0 0px 1000px transparent inset !important;
    /* Remove o fundo automático */
    transition: background-color 5000s ease-in-out 0s;
    /* Evita flash de cor padrão */
}

input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus {
    background-color: transparent !important;
    -webkit-text-fill-color: inherit !important;
    box-shadow: 0 0 0px 1000px transparent inset !important;
}
</style>