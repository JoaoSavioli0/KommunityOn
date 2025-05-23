import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import New from '@/views/NewView.vue'
import LoginView from '@/views/LoginView.vue';
import SolicitacoesUsuarioView from '@/views/SolicitacoesUsuarioView.vue';
import CadastroView from '@/views/CadastroView.vue';
import MenuView from '@/views/MenuView.vue';
import SolicitacaoView from '@/views/SolicitacaoView.vue';
import ContaMenu from '@/components/ContaMenu.vue';
import SettingsMenu from '@/components/SettingsMenu.vue';
import SuporteMenu from '@/components/SuporteMenu.vue';
import { useUserStore } from '@/stores/userStore';
import NewSenhaView from '@/views/NewSenhaView.vue';
import RecuperaSenhaView from '@/views/RecuperaSenhaView.vue';
import UserView from '@/views/UserView.vue';
import EcoShopView from '@/views/EcoShopView.vue';
import RecompensasUsuarioView from '@/views/RecompensasUsuarioView.vue';
import LandingPageView from '@/views/LandingPageView.vue';

const routes = [
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/new',
    name: 'new',
    component: New
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/solicitacoes/:id',
    name: "solicitacoesUsuario",
    component: SolicitacoesUsuarioView,
    props: true
  },
  {
    path: '/cadastro',
    name: 'cadastro',
    component: CadastroView
  },
  {
    path: '/menu',
    name: 'menu',
    component: MenuView,
    children: [
      { path: 'conta', component: ContaMenu },
      { path: 'configuracoes', component: SettingsMenu },
      { path: 'suporte', component: SuporteMenu }
    ]
  },
  {
    path: '/solicitacao/:id',
    name: 'solicitacao',
    component: SolicitacaoView,
    props: true
  },
  {
    path: '/alterar_senha',
    name: 'alterar_senha',
    component: NewSenhaView,
  },
  {
    path: '/recupera_senha',
    name: 'recupera_senha',
    component: RecuperaSenhaView,
  },
  {
    path: '/usuario/:id',
    name: 'usuario',
    component: UserView,
    props: true
  },
  {
    path: '/ecoshop',
    name: 'ecoshop',
    component: EcoShopView
  },
  {
    path: '/recompensas',
    name: 'recompensas',
    component: RecompensasUsuarioView
  },
  {
    path: '/kommunityon',
    name: 'landingpage',
    component: LandingPageView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      // Se houver uma posição salva, usa essa posição
      return savedPosition;
    } else {
      // Rola para o topo (0, 0) na navegação normal
      return { left: 0, top: 0 };
    }
  }
});

router.beforeEach(async (to, from, next) => {
  const userStore = useUserStore()
  await userStore.reconectaSessao(); // Verifica se o usuário está autenticado

  if ((to.meta.requiresAuth && !userStore.usuario) || (!userStore.usuario && to.path === '/')) {
    next("/kommunityon");
  } else if((userStore.usuario && to.path === '/')) {
    next("/kommunityon");
  } else{
    next()
  }
});

export default router
