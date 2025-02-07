import { defineStore } from 'pinia';

export const useUserStore = defineStore('usuario', {
  state: () => ({
    usuario: null, // Armazena informações do usuário
  }),
  actions: {
    login(usuarioData) {
      this.usuario = usuarioData; // Atualiza as informações do usuário ao fazer login
    },
    logout() {
      this.usuario = null; // Limpa as informações do usuário ao fazer logout
    },
  },
});
