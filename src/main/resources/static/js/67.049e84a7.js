"use strict";(self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[]).push([[67,896],{5381:function(e,t,a){a.d(t,{Z:function(){return S}});var s=a(3396);const l=e=>((0,s.dD)("data-v-491d14ee"),e=e(),(0,s.Cn)(),e),n={class:"main_nav_t_div"},o={class:"main_t_nav"},r={class:"main_t_nav_list"},i={class:"nav__btn"},u=l((()=>(0,s._)("i",{class:"fas fa-bell fa-2x","aria-hidden":"true"},null,-1))),c={class:"main_m_li_list"},_=["src"],d={class:"nav__btn"},m=l((()=>(0,s._)("i",{class:"fas fa-plus fa-2x","aria-hidden":"true"},null,-1)));function f(e,t,a,l,f,g){const p=(0,s.up)("router-link");return(0,s.wg)(),(0,s.iD)("div",n,[(0,s._)("nav",o,[(0,s._)("ul",r,[(0,s._)("li",i,[(0,s.Wm)(p,{class:"nav__link",to:"/alert"},{default:(0,s.w5)((()=>[u])),_:1})]),(0,s._)("li",c,[(0,s._)("img",{class:"logo",src:f.logo,alt:"로고",width:"45"},null,8,_)]),(0,s._)("li",d,[(0,s.Wm)(p,{to:"/auction_reg"},{default:(0,s.w5)((()=>[m])),_:1})])])])])}var g=a.p+"img/logo.2026bf89.png",p={props:{headerProps:String},data(){return{logo:g}},mounted(){console.log(g)}},v=a(89);const h=(0,v.Z)(p,[["render",f],["__scopeId","data-v-491d14ee"]]);var S=h},2272:function(e,t,a){a.r(t),a.d(t,{default:function(){return g}});var s=a(3396);const l=e=>((0,s.dD)("data-v-4e1c30ae"),e=e(),(0,s.Cn)(),e),n={class:"main-contain"},o=l((()=>(0,s._)("fieldset",null,[(0,s._)("div",{class:"sh_group"},[(0,s._)("p",{class:"main_text"},[(0,s._)("br"),(0,s.Uk)("'파치' 의 사전적 의미는 깨어지거나 흠이 나서 못 쓰게 된 물건을 말하는데 농산물이 떨어져서 깨지거나 흠이 나 있지만 일반 농산물보다 맛있고, 신선함을 표현하기 위해 애벌레가 사과를 베어먹었다는 것을 표현하였다. ")])])],-1)));function r(e,t,a,l,r,i){const u=(0,s.up)("Header"),c=(0,s.up)("Slide"),_=(0,s.up)("bottom-nav");return(0,s.wg)(),(0,s.iD)(s.HY,null,[(0,s._)("div",n,[(0,s.Wm)(u,{headerProps:r.headerProps},null,8,["headerProps"]),(0,s.Wm)(c),o,(0,s._)("div",null,[(0,s._)("button",{class:"login-form__btn",type:"submit",onClick:t[0]||(t[0]=e=>i.logout())},"로그아웃")])]),(0,s.Wm)(_)],64)}var i=a(5381),u=a(880),c=a(3454),_=a(9388),d={components:{bottomNav:c["default"],Header:i.Z,Slide:u["default"]},data(){return{headerProps:"Main",name:null,f_data:[],user:JSON.parse(localStorage.getItem("user")),userState:""}},mounted(){console.log(this.userState),"farm"===localStorage.getItem("checkUser")?this.userState=this.user.f_profile_img:this.userState=this.user.c_profile_img},methods:{logout(){console.log("logout"),this.$store.commit("LOGOUT"),this.$router.push("/login")},testCheck(){console.log(name),localStorage.setItem(key,_.values),localStorage.setItem("totalinfo",JSON.stringify(f_data))}}},m=a(89);const f=(0,m.Z)(d,[["render",r],["__scopeId","data-v-4e1c30ae"]]);var g=f}}]);
//# sourceMappingURL=67.049e84a7.js.map