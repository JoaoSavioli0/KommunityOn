import { defineStore } from 'pinia';
import axios from 'axios';

export const useUserStore = defineStore('usuario', {
  state: () => ({
    usuario: null, // Armazena informações do usuário
    token: localStorage.getItem("token") || null
  }),
  actions: {
    async login(usuarioData) {
      try {
        const response = await axios.post("http://localhost:8080/usuario/login", {
          cpfOuEmail: usuarioData.cpfOuEmail,
          senha: usuarioData.senha
        },
          {
            headers: {
              "Content-Type": "application/json",
            },
          })

        this.usuario = response.data.usuario
        this.token = response.data.token

        localStorage.setItem("token", this.token)

        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

      } catch (error) {
        console.error("Erro ao efeturar login: " + error)
      }
    },
    logout() {
      this.usuario = null
      this.token = null
      localStorage.removeItem("token")
      delete axios.defaults.headers.common["Authorization"]
    },
    async reconectaSessao() {
      if (!this.token) return

      try {
        const response = await axios.get("http://localhost:8080/usuario/me", {
          headers: { Authorization: `Bearer ${this.token}` },
        })

        this.usuario = response.data
      } catch (error) {
        console.error("Sua sessão está expirada, deslogando...")
        this.logout()
      }
    }
  },
});
