<template>
    <div class="w-full lg:pl-[190px] h-max pb-20 z-[100]">
        <div class="flex flex-col items-start ">
            <h1 class="font-bold text-gray-800 text-2xl mt-2">Conta</h1>
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
                        <div class="w-full rounded-md bg-gray-300 text-gray-500 mt-[5px] px-2 py-[4px] min-h-[30px] text-left border-2 border-gray-300"
                            v-if="!editandoValues.editandoNome">
                            {{
                                usuarioAtualizado.nome }}
                        </div>
                        <input
                            class="w-full rounded-md bg-gray-100 text-gray-800 mt-[5px] px-2 py-[4px] min-h-[30px] text-left border-2 border-gray-500 outline-none"
                            v-model="nomeAtualizado" v-if="editandoValues.editandoNome">
                        </input>
                        <a class="text-blue-500 text-xs text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                            @click="editarValor('editandoNome')" v-if="!editandoValues.editandoNome">Editar</a>
                        <div class="flex" v-else>
                            <a class="text-blue-500 text-xs inline text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                @click="salvarAlteracoes('editandoNome')">Salvar</a>
                            <a class="ml-4 text-blue-500 text-xs inline text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                @click="editandoValues.editandoNome = false">Cancelar</a>
                        </div>
                    </div>


                    <div class="flex flex-col items-start w-full mt-2">
                        <span class="text-sm">E-mail</span>
                        <div class="w-full rounded-md bg-gray-300 text-gray-500 mt-[5px] px-2 py-[4px] min-h-[30px] text-left border-2 border-gray-300"
                            v-if="!editandoValues.editandoEmail">
                            {{
                                usuarioAtualizado.email }}
                        </div>
                        <input
                            class="w-full rounded-md bg-gray-100 text-gray-800 mt-[5px] px-2 py-[4px] min-h-[30px] text-left border-2 border-gray-500 outline-none"
                            v-model="emailAtualizado" v-if="editandoValues.editandoEmail">
                        </input>
                        <a class="text-blue-500 text-xs text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                            @click="editarValor('editandoEmail')" v-if="!editandoValues.editandoEmail">Editar</a>
                        <div class="flex" v-else>
                            <a class="text-blue-500 text-xs inline text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                @click="salvarAlteracoes('editandoEmail')">Salvar</a>
                            <a class="ml-4 text-blue-500 text-xs inline text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                @click="editandoValues.editandoEmail = false">Cancelar</a>
                        </div>
                    </div>


                    <div class="flex w-full justify-between mt-2">
                        <div class="flex flex-col items-start w-[48%]">
                            <span class="text-sm">Telefone</span>
                            <div class="w-full rounded-md bg-gray-300 text-gray-500 border-2 border-gray-300 mt-[5px] px-2 py-[4px] text-left relative min-h-[30px]"
                                v-if="!editandoValues.editandoTelefone">
                                {{
                                    usuarioAtualizado.telefone }}
                            </div>
                            <input
                                class="w-full rounded-md bg-gray-100 text-gray-800 border-2 border-gray-500 outline-none mt-[5px] px-2 py-[4px] text-left relative min-h-[30px]"
                                v-model="telefoneAtualizado" v-if="editandoValues.editandoTelefone">
                            </input>

                            <a class="text-blue-500 text-xs text-decoration-none font-medium mt-[4px] cursor-pointer"
                                v-if="!editandoValues.editandoTelefone"
                                @click="editarValor('editandoTelefone')">Editar</a>
                            <div class="flex" v-else>
                                <a class="text-blue-500 text-xs inline text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                    @click="salvarAlteracoes('editandoTelefone')">Salvar</a>
                                <a class="ml-4 text-blue-500 text-xs inline text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                    @click="editandoValues.editandoTelefone = false">Cancelar</a>
                            </div>
                        </div>


                        <div class="flex flex-col items-start w-[48%]">
                            <span class="text-sm">CPF</span>
                            <div
                                class="w-full rounded-md bg-gray-300 text-gray-500 border-2 border-gray-300 mt-[5px] px-2 py-[4px] text-left min-h-[30px]">
                                {{
                                    usuarioAtualizado.cpf }}
                            </div>
                        </div>
                    </div>

                    <div class="flex w-full justify-between mt-2">
                        <div class="flex flex-col items-start w-[48%]">
                            <span class="text-sm">Cidade</span>
                            <div class="w-full rounded-md bg-gray-300 text-gray-500 border-2 border-gray-300 mt-[5px] px-2 py-[4px] text-left relative min-h-[30px]"
                                v-if="!editandoValues.editandoEndereco">
                                {{ endereco.cidade }}
                            </div>
                            <input
                                class="w-full rounded-md bg-gray-100 text-gray-800 border-2 border-gray-500 mt-[5px] px-2 py-[4px] text-left relative min-h-[30px]"
                                v-if="editandoValues.editandoEndereco" v-model="cidadeAtualizado">
                            </input>

                            <a class="text-blue-500 text-xs text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                v-if="!editandoValues.editandoEndereco" @click="editarValor('editandoEndereco')">Editar
                                endereço</a>
                            <div class="flex" v-else>
                                <a class="text-blue-500 text-xs inline text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                    @click="salvarAlteracoes('editandoEndereco')">Salvar</a>
                                <a class="ml-4 text-blue-500 text-xs inline text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                    @click="editandoValues.editandoEndereco = false">Cancelar</a>
                            </div>
                        </div>
                        <div class="flex flex-col items-start w-[48%]">
                            <span class="text-sm">Bairro</span>
                            <div class="w-full rounded-md bg-gray-300 text-gray-500 border-2 border-gray-300 mt-[5px] px-2 py-[4px] text-left min-h-[30px]"
                                v-if="!editandoValues.editandoEndereco">
                                {{ endereco.bairro }}
                            </div>
                            <input
                                class="w-full rounded-md bg-gray-100 text-gray-800 border-2 border-gray-500 mt-[5px] px-2 py-[4px] text-left min-h-[30px]"
                                v-if="editandoValues.editandoEndereco" v-model="bairroAtualizado">

                            </input>
                        </div>

                    </div>
                    <div class="flex flex-col items-center mt-4">
                        <span>Foto de perfil</span>
                        <div
                            class="size-[70px] rounded-full bg-gray-900 mt-2 overflow-hidden flex justify-center items-center">
                            <img v-if="imagemPreview && alterandoFoto" :src="imagemPreview" class="w-[70px] h-auto">
                            <img v-else :src="imagemUsuario" class="w-[70px] h-auto">
                        </div>
                        <div class="flex flex-col items-start">
                            <a class="text-blue-500 text-xs text-decoration-none font-medium mt-[4px] cursor-pointer hover:underline"
                                @click.prevent="alterandoFoto = true" v-if="!alterandoFoto">Alterar
                                foto</a>
                            <div v-else class="flex flex-col">
                                <label for="foto-upload"
                                    class="w-[100px] py-[2px] rounded-md bg-gray-300 text-gray-800 font-medium text-sm cursor-pointer flex mt-2 flex justify-center">
                                    <img src="../assets/upload.svg" class="w-[20px] h-auto"> <span
                                        class="ml-[4px]">Carregar</span></label>
                                <input type="file" id="foto-upload" class="hidden" @change="fotoUpload">

                                <button
                                    class="w-[100px] py-[2px] rounded-md bg-gray-300 text-gray-800 font-medium text-sm cursor-pointer flex mt-2 justify-center disabled:cursor-default disabled:text-gray-500"
                                    :disabled="!imagemPreview" @click="salvarFoto()">Salvar</button>

                                <button
                                    class="w-[100px] py-[2px] rounded-md bg-transparent border-[2px] border-gray-300 text-gray-800 font-medium text-sm cursor-pointer flex mt-2 justify-center"
                                    @click="alterandoFoto = false, imagemPreview = null">Cancelar</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="w-full text-left w-full relative mt-8">
                    <h2 class="text-md font-medium text-gray-700 bg-gray-100 pr-[15px] z-[50] relative w-fit">
                        Segurança e privacidade</h2>
                    <div class="absolute h-[1px] w-full bg-gray-400 top-[50%] z-[40]"></div>
                </div>

                <div class="w-full max-w-full flex flex-wrap gap-2 mt-4">
                    <RouterLink to="/alterar_senha" class="p-0 w-full">
                        <button class="w-full rounded-md bg-gray-300 text-red-600 py-[3px] font-medium">
                            Alterar senha
                        </button>
                    </RouterLink>
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

    name: 'ContaMenu',
    data() {
        return {
            usuario: {},
            endereco: {},
            usuarioAtualizado: {},
            alterandoFoto: false,
            imagemUpada: null,
            imagemPreview: null,
            imagemUsuario: null,
            editandoValues: {
                editandoNome: false,
                editandoEmail: false,
                editandoTelefone: false,
                editandoEndereco: false
            },

            nomeAtualizado: null,
            emailAtualizado: null,
            cidadeAtualizado: null,
            bairroAtualizado: null,
            telefoneAtualizado: null,
            profileUsuario: null
        }
    },
    mounted() {
        const userStore = useUserStore()
        this.usuario = userStore.usuario
        this.carregaDados()
        this.carregaImagem()
    },
    methods: {
        async carregaImagem() {
            this.profileUsuario = null
            try {
                this.profileUsuario = await axios.get(`http://localhost:8080/usuario/foto-perfil/${this.usuario.id}`)
            } catch (error) {
                console.log("Ocorreu um erro ao carregar a foto de usuário: " + error)
            }
            if (this.profileUsuario != null) {
                if (!this.profileUsuario.data) return;
                // Adiciona o prefixo correto para exibir no <img>
                this.imagemUsuario = `data:image/png;base64,${this.profileUsuario.data}`;
            }
        },

        async carregaDados() {
            //Endereco
            try {
                const response = await axios.get(`http://localhost:8080/endereco/${this.usuario.id}`)
                this.endereco = response.data

                this.cidadeAtualizado = this.endereco.cidade
                this.bairroAtualizado = this.endereco.bairro
            } catch (error) {
                console.log("Erro ao buscar endereço de usuário" + error)
            }

            //Usuario
            try {
                const response = await axios.get(`http://localhost:8080/usuario/${this.usuario.id}`)
                this.usuarioAtualizado = response.data
                console.log("Telefone recebido bd: " + this.usuarioAtualizado.telefone)

                this.emailAtualizado = this.usuarioAtualizado.email
                this.telefoneAtualizado = this.usuarioAtualizado.telefone
                this.nomeAtualizado = this.usuarioAtualizado.nome
            } catch (error) {
                console.log("Erro ao buscar endereço de usuário" + error)
            }
        },

        fotoUpload(event) {
            let tipo = event.target.files[0].type
            let tamanho = event.target.files[0].size

            if (tipo !== "image/png" && tipo !== "image/jpg" && tipo !== "image/jpeg") {
                console.log("A imagem precisa ser em formato JPG, JPEG ou PNG")
                return
            } else if (tamanho > 3100000) {
                console.log("A imagem deve ter menos de 3mb!")
                return
            }
            this.imagemPreview = URL.createObjectURL(event.target.files[0])
            this.imagemUpada = event.target.files[0]
        },

        async salvarFoto() {
            if (this.imagemUpada) {
                const formData = new FormData()
                formData.append("file", this.imagemUpada)
                try {
                    const response = await axios.post(`http://localhost:8080/usuario/foto-perfil/${this.usuario.id}/nova`,
                        formData,
                        {
                            headers: { "Content-Type": "multipart/form-data" }
                        }
                    )

                    this.carregaImagem()

                    alert(response.data)
                } catch (error) {
                    console.error("Erro ao salvar imagem: " + error)
                }
                this.imagemPreview = null
                this.alterandoFoto = false
                this.imagemUpada = null
            }
        },

        async salvarAlteracoes(value) {
            console.log("Salvando alterações...")
            this.editandoValues[value] = false

            await this.salvarAlteracoesUsuario()
            await this.salvarAlteracoesEndereco()
            this.carregaDados()
        },

        async salvarAlteracoesUsuario() {
            let usuarioAtualizado = { ...this.usuario }
            usuarioAtualizado.email = this.emailAtualizado
            usuarioAtualizado.nome = this.nomeAtualizado
            usuarioAtualizado.telefone = this.telefoneAtualizado

            try {
                await axios.post(`http://localhost:8080/usuario/atualiza/${this.usuario.id}`, {
                    id: this.usuario.id,
                    nome: usuarioAtualizado.nome,
                    email: usuarioAtualizado.email,
                    telefone: usuarioAtualizado.telefone,

                }, {
                    headers: {
                        "Content-Type": "application/json",
                    },
                })

                let usuarioStoreAtualizado = this.usuario
                usuarioStoreAtualizado.nome = usuarioAtualizado.nome
                usuarioStoreAtualizado.email = usuarioAtualizado.email
                usuarioStoreAtualizado.telefone = usuarioAtualizado.telefone

                useUserStore().login(usuarioStoreAtualizado)

                console.log("Atualizado com sucesso!")
                return true
            } catch (e) {
                console.error("Erro ao atualizar informações: " + e)
                return false
            }
        },

        async salvarAlteracoesEndereco() {
            try {
                await axios.post(`http://localhost:8080/endereco/cadastro`, {
                    bairro: this.bairroAtualizado,
                    cidade: this.cidadeAtualizado,
                    uf: this.endereco.uf,
                    id: this.endereco.id,
                    usuario: {
                        id: this.usuario.id
                    }
                })
            } catch (e) {
                console.error("Erro ao atualizar endereço: " + e)
            }
        },

        editarValor(value) {
            for (let editando in this.editandoValues) {
                if (editando == value) {
                    this.editandoValues[editando] = true
                    continue
                }
                this.editandoValues[editando] = false
            }
        }
    }
}
</script>