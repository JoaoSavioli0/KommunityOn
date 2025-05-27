<template>
    <section class="w-full min-h-screen bg-[#F0F4F9] py-20">
        <div class="w-full h-full flex justify-center items-center flex-col">
            <transition-group name="move" tag="div" class="flex flex-col items-center">
                <div key="divLogin"
                    class="w-[450px] rounded-[30px] bg-white p-8 flex flex-col justify-between shadow-sm transition-all duration-300">
                    <div class="w-full">
                        <a @click="voltar" class="w-full p-0 z-[150] flex items-center justify-between">
                            <button
                                class="bg-gray-900 flex items-center justify-between rounded-md px-[8px] py-[3px] w-fit text-white font-medium cursor-pointer">
                                <img src="../assets/arrow.png" class="filtro rotate-180 size-[15px]">
                                <span class="ml-2 text-xl">Voltar</span>
                            </button>
                            <h1 class="text-zinc-800 font-medium"><span class="font-semibold">K</span>ommunity<span
                                    class="font-semibold">ON</span></h1>
                        </a>
                    </div>
                    <div class="w-full">
                        <div class="w-full mt-4 pl-[1px] text-left">
                            <h1 class="text-2xl font-semibold"><span class="font-normal">Faça seu</span> Cadastro</h1>
                        </div>
                    </div>
                    <div class="w-full flex flex-col mt-6">
                        <div class="w-full relative">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Nome</span>
                            <input type="text" v-model="nome" maxlength="60" :class="{ 'border-red-500': erroNome }"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">E-mail</span>
                            <input type="text" v-model="email" maxlength="60" :class="{ 'border-red-500': erroEmail }"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">CPF</span>
                            <input type="text" v-model="cpf" :class="{ 'border-red-500': erroCpf }"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Telefone</span>
                            <input type="text" v-model="telefone" :class="{ 'border-red-500': erroTelefone }"
                                class="border-[2px] border-gray-800 rounded-[10px] py-2 px-4 outline-none w-full">
                        </div>
                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Senha</span>
                            <input :type="senhaVisivel ? 'text' : 'password'" v-model="senha" maxlength="25"
                                :class="{ 'border-red-500': erroSenha }"
                                class="border-[2px] transition-all rounded-[10px] border-gray-800 py-2 px-4 outline-none w-full">
                            <div class="absolute end-[15px] top-[12px] flex items-center" v-if="senha.length > 0">

                                <img src="../assets/closed-eye.svg"
                                    class="size-[20px] filtro-gray-800 mr-2 cursor-pointer" @click="senhaVisivel = true"
                                    v-if="!senhaVisivel">

                                <img src="../assets/eye.svg" class="size-[20px] filtro-gray-800 mr-2 cursor-pointer"
                                    @click="senhaVisivel = false" v-if="senhaVisivel">

                                <span class="border-l-[1px] border-gray-400 pl-2 font-normal text-sm">{{
                                    25 - senha.length
                                    }}</span>
                            </div>
                        </div>
                        <div class="w-full">
                            <ul class="w-full text-xs text-gray-700 text-left"
                                :class="([senhaAviso.length > 0 ? 'p-2' : 'p-0'])">
                                <li v-for="aviso in senhaAviso">{{ aviso }}</li>
                            </ul>
                        </div>

                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Confirme a
                                senha</span>
                            <input :type="senhaConfirmVisivel ? 'text' : 'password'" v-model="senhaConfirm" :class="[!senha ? 'border-gray-300' : '',
                            erroSenhaConfirm && senha ? 'border-red-500' : '',
                            senha && !erroSenhaConfirm ? 'border-gray-800' : '']" :disabled="senha.length == 0"
                                class="border-[2px] transition-all duration-200 rounded-[10px] py-2 px-4 outline-none w-full">

                            <div class="absolute end-[15px] top-[12px] flex items-center"
                                v-if="senhaConfirm.length > 0">
                                <img src="../assets/closed-eye.svg" class="size-[20px] filtro-gray-800 cursor-pointer"
                                    @click="senhaConfirmVisivel = true" v-if="!senhaConfirmVisivel">

                                <img src="../assets/eye.svg" class="size-[20px] filtro-gray-800 cursor-pointer"
                                    @click="senhaConfirmVisivel = false" v-if="senhaConfirmVisivel">
                            </div>

                            <span v-if="!senhaIgual && senha" class="text-red-500 p-2 text-left w-full text-xs block">As
                                senhas
                                não
                                conferem</span>
                        </div>

                        <div class="w-full relative mt-6 flex py-4">
                            <div class="h-[1px] bg-gray-300 w-full relative flex justify-center"><span
                                    class="absolute top-[-12px] bg-white px-2 font-medium">Endereço</span></div>
                        </div>

                        <div class="w-full relative mt-6 flex justify-between">
                            <div class="w-[125px]">
                                <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Estado</span>
                                <select type="text" v-model="uf" @change="cidades()"
                                    :class="{ 'border-red-500': erroUf }"
                                    class="border-[2px] transition-all rounded-[10px] border-gray-800 h-[43px] px-4 outline-none w-full">
                                    <option v-for="estado in estados.estados" :key="estado.sigla" :value="estado.sigla">
                                        {{ estado.sigla }}
                                    </option>
                                </select>
                            </div>
                            <div class="grow pl-2">
                                <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Cidade</span>
                                <select type="text" v-model="cidade" @change="cidades()"
                                    :class="{ 'border-red-500': erroCidade }"
                                    class="border-[2px] transition-all rounded-[10px] border-gray-800 h-[43px] px-4 outline-none w-full">
                                    <option v-for="cidade in cidades_uf" :key="cidade" :value="cidade">
                                        {{ cidade }}
                                    </option>
                                </select>
                            </div>
                        </div>

                        <div class="w-full relative mt-6">
                            <span class="absolute bg-white ml-[17px] top-[-11px] font-normal px-2">Bairro</span>
                            <input type="text" v-model="bairro" maxlength="40" :class="{ 'border-red-500': erroBairro }"
                                class="border-[2px] transition-all rounded-[10px] border-gray-800 py-2 px-4 outline-none w-full">
                        </div>

                        <div class="w-full relative mt-8">
                            <button @click.prevent="fazCadastro()"
                                class="bg-gray-900 font-regular rounded-[10px] py-4 outline-none w-full text-white">Cadastrar</button>
                        </div>
                        <div class="w-full relative mt-6 flex">
                            <div class="h-[1px] bg-gray-300 w-full relative flex justify-center"><span
                                    class="absolute top-[-12px] bg-white px-2 font-medium">Ou</span></div>
                        </div>
                        <div class="w-full pt-8">
                            <RouterLink to="/login" class="p-0">
                                <div class="w-full relative">
                                    <button
                                        class="border-2 text-gray-800 border-gray-800 font-regular rounded-[10px] py-6 outline-none w-full transition-all duration-300 overflow-hidden relative cadastrese-button flex justify-center items-center">
                                        <span class="cadastrese absolute transition-all duration-300">Fazer login</span>
                                        <span class="cadastrese2 absolute transition-all duration-300">Fazer
                                            login</span>
                                    </button>
                                </div>
                            </RouterLink>
                        </div>
                    </div>
                </div>

                <div key="divAviso"
                    class="mt-4 w-[400px] py-4 rounded-[30px] bg-gray-900 p-8 flex justify-center items-center transition-all duration-300"
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
import json from '@/data.json'

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
            senhaAviso: [],
            senhaIgual: true,
            senhaVisivel: false,
            senhaConfirmVisivel: false,
            cidades_uf: [],
            estados: json,
            erroNome: false,
            erroEmail: false,
            erroCpf: false,
            erroTelefone: false,
            erroSenha: false,
            erroSenhaConfirm: false,
            erroUf: false,
            erroCidade: false,
            erroBairro: false,
        }
    },
    watch: {
        senha() {
            this.verificaSenha();
            if (this.senhaConfirm.length > 0) {
                this.senhaIgual = this.senha === this.senhaConfirm;
            }
            if (this.senha.length == 0) {
                this.senhaConfirm = ''
            }
        },
        senhaConfirm() {
            this.senhaIgual = this.senha === this.senhaConfirm;
        },
        nome() {
            this.nome = this.nome.replace(/[^A-Za-zÀ-ÖØ-öø-ÿ\s]/g, '')
        },
        email() {
            this.email = this.email.replace(' ', '')
        },
        cpf() {
            let numeros = this.cpf.replace(/\D/g, '');

            if (numeros.length > 11) {
                numeros = numeros.slice(0, 11);
            }

            this.cpf = numeros.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2})?/, (match, p1, p2, p3, p4) => {
                return `${p1}.${p2}.${p3}${p4 ? '-' + p4 : ''}`
            })
        },
        telefone() {
            let numeros = this.telefone.replace(/\D/g, '');

            if (numeros.length > 11) {
                numeros = numeros.slice(0, 11);
            }

            this.telefone = numeros.replace(/(\d{2})(\d{4,5})(\d{4})/, (match, p1, p2, p3) => {
                return `(${p1}) ${p2}-${p3}`
            })
        }
    },
    methods: {
        voltar() {
            this.$router.back()

            setTimeout(() => {
                if (this.$route.name === 'cadastro') {
                    this.$router.push('/')
                }
            }, 500)
        },
        async fazCadastro() {
            // if (!this.nome || !this.senha || !this.telefone || this.senhaConfirm !== this.senha || !this.email || !this.cpf || !this.uf || !this.cidade || !this.bairro) {
            //     this.aviso = 'Prencha todos os campos para realizar o cadastro!'
            //     return;
            // } else {
            const cpfNumeros = this.cpf.replace(/\D/g, '')
            const telefoneNumeros = this.telefone.replace(/\D/g, '')

            this.erroNome = this.nome.length < 10
            this.erroEmail = !this.validarEmail()
            this.erroCpf = !this.validarCpf(cpfNumeros)
            this.erroTelefone = telefoneNumeros.length < 10
            this.erroSenha = this.senhaAviso.length > 0 || !this.senha
            this.erroSenhaConfirm = this.senha !== this.senhaConfirm
            this.erroUf = !this.uf
            this.erroCidade = !this.cidade
            this.erroBairro = this.bairro.length < 2

            if (this.erroNome || this.erroEmail || this.erroCpf || this.erroTelefone || this.erroSenha || this.erroSenhaConfirm || this.erroUf || this.erroCidade || this.erroBairro) {
                this.aviso = 'Corrija os campos incorretos.'
                return
            }

            try {
                const novoUsuario = await axios.post("http://localhost:5000/usuario/cadastro", {
                    nome: this.nome,
                    email: this.email,
                    cpf: cpfNumeros,
                    senha: this.senha,
                    telefone: telefoneNumeros
                }, {
                    headers: {
                        "Content-Type": "application/json",
                    },
                })

                const usuarioEndereco = await axios.post("http://localhost:5000/endereco/cadastro", {
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
            //}
            setTimeout(() => { this.aviso = '' }, 4000);
        },
        cidades() {
            this.estados.estados.forEach(element => {
                if (element.sigla === this.uf) {
                    this.cidades_uf = element.cidades;
                }
            });
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
        },
        verificaSenha() {
            this.senhaAviso = []
            if (this.senha.length > 0) {
                if (this.senha.length < 8) this.senhaAviso.push("- Pelo menos 8 dígitos")
                if (this.senha.toLowerCase === this.senha) this.senhaAviso.push("- Pelo menos um caractere maiúsculo")
                if (!(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(this.senha))) this.senhaAviso.push("- Pelo menos 1 caractere especial")
                if (!(/[abcdefghijklmnopqrstuvwxyz]/.test(this.senha.toLowerCase))) this.senhaAviso.push("- Pelo menos 1 caractere")
                if (!(/[1234567890]/.test(this.senha))) this.senhaAviso.push("- Pelo menos 1 número")
            }
        },
        validarCpf(cpf) {
            let Soma = 0;
            let Resto = 0;
            const strCPF = String(cpf).replace(/[^\d]/g, '');

            if (strCPF.length !== 11) return false;

            if ([
                '00000000000',
                '11111111111',
                '22222222222',
                '33333333333',
                '44444444444',
                '55555555555',
                '66666666666',
                '77777777777',
                '88888888888',
                '99999999999',
            ].includes(strCPF)) return false;

            for (let i = 1; i <= 9; i++)
                Soma += parseInt(strCPF.substring(i - 1, i)) * (11 - i);

            Resto = (Soma * 10) % 11;

            if (Resto === 10 || Resto === 11) Resto = 0;
            if (Resto !== parseInt(strCPF.substring(9, 10))) return false;

            Soma = 0;

            for (let i = 1; i <= 10; i++)
                Soma += parseInt(strCPF.substring(i - 1, i)) * (12 - i);

            Resto = (Soma * 10) % 11;

            if (Resto === 10 || Resto === 11) Resto = 0;
            if (Resto !== parseInt(strCPF.substring(10, 11))) return false;

            return true;
        },
        validarEmail() {
            let emailvalidacao = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailvalidacao.test(this.email)) {
                return false;
            }
            return true;
        },
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