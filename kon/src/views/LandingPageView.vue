<template>
    <div class="w-full flex flex-col overflow-x-hidden">
        <div class="w-full max-h-screen bg-zinc-100 flex items-center overflow-hidden relative">
            <div class="w-full h-[100px] absolute bottom-0 start-0 gradiente-baixo z-30"></div>
            <div class="w-full h-[100px] absolute top-0 start-0 gradiente-cima z-30"></div>
            <div class="w-[45%] h-full text-start px-40 z-40">
                <div class="titulo w-full">
                    <h1 class="font-medium text-[80px] leading-none">Kommunity<span class="font-bold">On</span></h1>
                    <span class="text-gray-600 text-2xl font-regular w-full text-start mt-2 ml-[5px]">Porque a cidade é
                        de
                        todo
                        mundo.</span>
                </div>
                <div class="botoes flex gap-x-6 mt-6">
                    <router-link to="/home">
                        <button
                            class="rounded-md w-[200px] h-[60px] bg-gray-900 text-zinc-50 font-semibold text-lg">Comece
                            já</button>
                    </router-link>
                    <button
                        class="border border-gray-900 bg-zinc-100 rounded-md h-[60px] w-[200px] text-gray-900 text-lg font-medium">Saiba
                        mais</button>
                </div>
            </div>


            <div class="w-[55%] h-full relative pl-40 z-20">

                <div class="overflow-hidden h-full w-full">
                    <div class="flex flex-col justify-start">
                        <div class="flex flex-col gap-y-6 pb-6 animate-scroll justify-start">
                            <div class="w-[80%] rounded-[20px] bg-gray-900 flex px-6 py-4 text-left justify-between items-center relative solicitacao "
                                v-for="solicitacao in solicitacoesExibicao" :key="solicitacao.id">
                                <div class="flex flex-col w-full max-[600px]:w-[95%]">
                                    <h1 class="text-white font-semibold text-2xl w-[80%] max-[600px]:text-lg">
                                        {{ solicitacao.titulo }}</h1>
                                    <p class="text-gray-300 line-clamp-4 w-[80%] max-[600px]:text-xs">
                                        {{ solicitacao.descricao }}</p>
                                    <div class="flex mt-2">
                                        <div class=" flex items-center text-white mt-[3px]">
                                            <img src="../assets/location.png"
                                                class="filtro size-[19px] max-[600px]:size-[13px]">
                                            <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.local }}</span>

                                            <div class="ml-8 flex items-center max-[600px]:ml-4">
                                                <img src="../assets/comments.png"
                                                    class="size-[19px] max-[600px]:size-[13px] filtro">
                                                <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.comentarios
                                                    }}</span>
                                            </div>
                                        </div>

                                    </div>
                                </div>


                                <div
                                    class="border-l-[1px] border-gray-600 pl-6 flex justify-center items-center absolute end-[-23px] lg:end-[-35px]">
                                    <span class="text-white font-semibold mr-4">{{ solicitacao.numLikes }}</span>
                                    <div
                                        class="size-[50px] lg:size-[70px] rounded-full bg-zinc-50 flex items-center justify-center cursor-pointer">
                                        <div class="heart-container" title="Like">
                                            <input type="checkbox" class="checkbox" disabled>
                                            <div class="svg-container">
                                                <svg viewBox="0 0 24 24" class="svg-outline"
                                                    xmlns="http://www.w3.org/2000/svg">
                                                    <path
                                                        d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z">
                                                    </path>
                                                </svg>
                                                <svg viewBox="0 0 24 24" class="svg-filled"
                                                    xmlns="http://www.w3.org/2000/svg">
                                                    <path
                                                        d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z">
                                                    </path>
                                                </svg>
                                                <svg class="svg-celebrate" width="100" height="100"
                                                    xmlns="http://www.w3.org/2000/svg">
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
                        <div class="flex flex-col gap-y-6 pb-6 animate-scroll">
                            <div class="w-[80%] rounded-[20px] bg-gray-900 flex px-6 py-4 text-left justify-between items-center relative solicitacao"
                                v-for="solicitacao in solicitacoesExibicao" :key="solicitacao.id">
                                <div class="flex flex-col w-full max-[600px]:w-[95%]">
                                    <h1 class="text-white font-semibold text-2xl w-[80%] max-[600px]:text-lg">
                                        {{ solicitacao.titulo }}</h1>
                                    <p class="text-gray-300 line-clamp-4 w-[80%] max-[600px]:text-xs">
                                        {{ solicitacao.descricao }}</p>
                                    <div class="flex mt-2">
                                        <div class=" flex items-center text-white mt-[3px]">
                                            <img src="../assets/location.png"
                                                class="filtro size-[19px] max-[600px]:size-[13px]">
                                            <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.local }}</span>

                                            <div class="ml-8 flex items-center max-[600px]:ml-4">
                                                <img src="../assets/comments.png"
                                                    class="size-[19px] max-[600px]:size-[13px] filtro">
                                                <span class="ml-2 max-[600px]:text-xs">{{ solicitacao.comentarios
                                                    }}</span>
                                            </div>
                                        </div>

                                    </div>
                                </div>


                                <div
                                    class="border-l-[1px] border-gray-600 pl-6 flex justify-center items-center absolute end-[-23px] lg:end-[-35px]">
                                    <span class="text-white font-semibold mr-4">{{ solicitacao.numLikes }}</span>
                                    <div
                                        class="size-[50px] lg:size-[70px] rounded-full bg-zinc-50 flex items-center justify-center cursor-pointer">
                                        <div class="heart-container" title="Like">
                                            <input type="checkbox" class="checkbox" disabled>
                                            <div class="svg-container">
                                                <svg viewBox="0 0 24 24" class="svg-outline"
                                                    xmlns="http://www.w3.org/2000/svg">
                                                    <path
                                                        d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z">
                                                    </path>
                                                </svg>
                                                <svg viewBox="0 0 24 24" class="svg-filled"
                                                    xmlns="http://www.w3.org/2000/svg">
                                                    <path
                                                        d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z">
                                                    </path>
                                                </svg>
                                                <svg class="svg-celebrate" width="100" height="100"
                                                    xmlns="http://www.w3.org/2000/svg">
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

            </div>
        </div>
        <div class="w-full h-screen bg-white flex flex-col items-center justify-center">
            <div class="w-[700px]">
                <h1 class="text-[55px] font-black text-gray-900 leading-[1.1]">Seja mais que uma voz, seja ouvido!</h1>
            </div>
            <div class="w-[750px] mt-4">
                <span class="text-lg text-gray-700 font-medium">Basta um cadastro rápido para começar. Sem burocracia.
                    Sem
                    papel. Sem
                    precisar sair
                    de casa. O KommunityON foi pensado para funcionar em qualquer dispositivo, com uma interface simples
                    e direta.
                </span>
            </div>
            <div class="mt-24 grid grid-cols-3 gap-[110px] w-max justify-center">
                <div class="flex flex-col items-center h-[250px] w-[300px]">
                    <img src="../assets/acoes-1.svg" class="w-[100px] h-auto">
                    <span class="text-lg font-medium mt-4">Registre reclamações sobre infraestrutura, iluminação, coleta
                        de
                        lixo e mais.</span>
                </div>
                <div class="flex flex-col items-center h-[250px] w-[300px]">
                    <img src="../assets/acoes-2.svg" class="w-[100px] h-auto">
                    <span class="text-lg font-medium mt-4">Engaje sua comunidade para dar visibilidade aos problemas do
                        seu bairro</span>
                </div>
                <div class="flex flex-col items-center h-[250px] w-[300px]">
                    <img src="../assets/acoes-3.svg" class="w-[100px] h-auto">
                    <span class="text-lg font-medium mt-4">Interaja com pedidos de outros cidadãos curtindo e
                        comentando.</span>
                </div>
            </div>
        </div>
        <div class="w-full min-h-screen bg-gray-900 flex flex-col items-center justify-center">
            <div class="h-[500px] w-[600px] flex justify-center items-center relative gap-x-8 pointer-events-none">
                <div class="absolute z-20 text-white font-black text-[660px]">KON
                </div>
                <img src="../assets/screenshot-1.jpg" class="w-[320px] h-auto rounded-lg shadow-lg z-30">
                <img src="../assets/screenshot-2.jpg" class="w-[350px] h-auto rounded-lg shadow-lg z-30">
                <img src="../assets/screenshot-3.jpg" class="w-[320px] h-auto rounded-lg shadow-lg z-30">
            </div>
            <div class="w-full mt-32 px-40"><button
                    class="w-full bg-white shadow-md h-[65px] cursor-pointer rounded-lg text-3xl font-semibold ">Acesse
                    já</button>
            </div>
        </div>
        <div class="w-full py-24 px-80 bg-zinc-100">
            <div class="text-start w-full grid grid-cols-4">
                <div>
                    <h1 class="text-[70px] font-black text-gray-900 self-center">KON</h1>
                </div>
                <div class="px-8">
                    <h2 class="font-bold text-lg px-[4px]">Sobre nós</h2>
                    <ul
                        class="*:font-medium mt-8 gap-y-[1px] *:px-[4px] *:rounded-md *:transition-all *:duration-100 *:cursor-pointer">
                        <li class="hover:bg-gray-200">Home</li>
                        <li class="hover:bg-gray-200">Informações</li>
                        <li class="hover:bg-gray-200">Contatos</li>
                    </ul>
                </div>
                <div class="px-8">
                    <h2 class="font-bold text-lg px-[4px]">KommunityOn</h2>
                    <ul class="mt-8 gap-y-[1px]">
                        <router-link to="/login" class="p-0">
                            <li
                                class="font-medium px-[4px] rounded-md transition-all duration-100 cursor-pointer hover:bg-gray-200">
                                Login</li>
                        </router-link>
                        <router-link to="/cadastro" class="p-0">
                            <li
                                class="font-medium px-[4px] rounded-md transition-all duration-100 cursor-pointer hover:bg-gray-200">
                                Cadastro</li>
                        </router-link>
                    </ul>
                </div>
                <div class="px-8">
                    <h2 class="font-bold text-lg px-[4px]">Fale conosco</h2>
                    <ul
                        class="*:font-medium mt-8 gap-y-[1px] *:px-[4px] *:rounded-md *:transition-all *:duration-100 *:cursor-pointer">
                        <li class="hover:bg-gray-200">Solicitar orçamento</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "LandingPage",
    data() {
        return {
            solicitacoesExibicao: [
                { id: 1, titulo: 'Buraco perigoso na Rua das Laranjeiras', local: 'Vila Esperança', descricao: 'Tem um buraco enorme bem na frente da escola municipal, na Rua das Laranjeiras. Já causou dois acidentes com moto. Precisa de manutenção urgente!', numLikes: 19, comentarios: 7 },

                { id: 2, titulo: 'Mais lixeiras na praça principal, por favor', local: 'Centro', descricao: 'A praça onde rola a feira de domingo tá sempre cheia de lixo porque não tem lixeira suficiente. Isso dá uma imagem feia pra quem passa por lá.', numLikes: 31, comentarios: 26 },

                { id: 3, titulo: 'Iluminação pública falhando', local: 'Jardim das Palmeiras', descricao: 'Metade dos postes da Rua 7 de Setembro não estão funcionando. A rua fica super escura à noite, trazendo sensação de insegurança.', numLikes: 7, comentarios: 3 },

                { id: 4, titulo: 'Falta de acessibilidade no posto de saúde', local: 'Conjunto Vitória', descricao: 'O posto de saúde da região não tem rampa de acesso. Meu avô usa cadeira de rodas e a gente precisa pedir ajuda toda vez que ele vai lá.', numLikes: 11, comentarios: 14 },

                { id: 5, titulo: 'Árvore quase caindo na calçada', local: 'Alto do Cruzeiro', descricao: 'Tem uma árvore imensa com as raízes expostas e inclinada. Com a chuva, parece que vai cair a qualquer momento. Fica na esquina da Rua São Bento com a João Pedro.', numLikes: 4, comentarios: 1 },

                { id: 6, titulo: 'Solicitação de lombada em frente à escola', local: 'Vila Esperança', descricao: 'Tem um buraco enorme bem na frente da escola municipal, na Rua das Laranjeiras. Já causou dois acidentes com moto. Precisa de manutenção urgente!', numLikes: 14, comentarios: 6 },
            ]
        }
    }
}
</script>

<style scoped>
.animate-scroll {
    will-change: transform;
    animation: scrolling 20s linear infinite;
}

@keyframes scrolling {
    0% {
        transform: translateY(50%);
    }

    100% {
        transform: translateY(-50%);
    }
}

.gradiente-baixo {
    background: #ffffff;
    background: linear-gradient(0deg, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 0.56) 15%, rgba(255, 255, 255, 0) 100%);
}

.gradiente-cima {
    background: #ffffff;
    background: linear-gradient(180deg, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 0.56) 15%, rgba(255, 255, 255, 0) 100%);
}
</style>