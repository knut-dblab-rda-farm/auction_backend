"use strict";(self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[]).push([[522],{9123:function(a,e,s){s.d(e,{Z:function(){return g}});var t=s(3396),n=s(7139);const l=a=>((0,t.dD)("data-v-29d81906"),a=a(),(0,t.Cn)(),a),d={class:"main_nav_t_div"},i={class:"main_t_nav"},r={class:"main_t_nav_list"},c=l((()=>(0,t._)("li",{class:"nav__btn"},[(0,t._)("a",{class:"nav__link",href:"alert"},[(0,t._)("i",{class:"fas fa-bell fa-2x","aria-hidden":"true"})])],-1))),o={class:"main_m_li_list"},m={class:"nav_m_link",href:"workout.html"},_={class:"nav__btn"},p=l((()=>(0,t._)("i",{class:"fas fa-chevron-left fa-2x","aria-hidden":"true"},null,-1))),u=[p];function v(a,e,s,l,p,v){return(0,t.wg)(),(0,t.iD)("div",d,[(0,t._)("nav",i,[(0,t._)("ul",r,[c,(0,t._)("li",o,[(0,t._)("p",m,(0,n.zw)(s.headerProps),1)]),(0,t._)("li",_,[(0,t._)("a",{class:"nav__link",onClick:e[0]||(e[0]=e=>a.$router.go(-1))},u)])])])])}var f={props:{headerProps:String}},k=s(89);const h=(0,k.Z)(f,[["render",v],["__scopeId","data-v-29d81906"]]);var g=h},8522:function(a,e,s){s.r(e),s.d(e,{default:function(){return q}});var t=s(3396),n=s(7139);const l=a=>((0,t.dD)("data-v-0c6d35a4"),a=a(),(0,t.Cn)(),a),d={class:"farm-contain"},i=l((()=>(0,t._)("div",{class:"farm-information"},[(0,t._)("img",{class:"farm-image",src:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqVGCXTRUebkmD2Yi3Zd4sRGWCryxfM4wdI8jZuPBqqA&s",alt:"농가 사진"})],-1))),r={class:"farm-name-tel"},c={class:"farm-userName"},o=l((()=>(0,t._)("h3",{class:"userName"},"이름",-1))),m={class:"farm-tel"},_=l((()=>(0,t._)("h3",{class:"tel"},"연락처",-1))),p={class:"farm-description"},u=l((()=>(0,t._)("h3",{class:"description"},"농가설명",-1))),v={class:"buttons"},f={class:"farm-best"},k={key:0,class:"best-detail"},h={class:"farm-address"},g={class:"address-detail"},w={key:0,id:"map",class:"map",style:{width:"300px",height:"300px"}};function b(a,e,s,l,b,y){const C=(0,t.up)("Header");return(0,t.wg)(),(0,t.iD)("div",d,[(0,t.Wm)(C,{headerProps:b.test.farmName},null,8,["headerProps"]),i,(0,t._)("div",r,[(0,t._)("div",c,[o,(0,t._)("p",null,(0,n.zw)(b.test.name),1)]),(0,t._)("div",m,[_,(0,t._)("p",null,(0,n.zw)(b.test.tel),1)])]),(0,t._)("div",p,[u,(0,t._)("p",null,(0,n.zw)(b.test.description),1)]),(0,t._)("div",v,[(0,t._)("div",f,[(0,t._)("button",{class:"best",onClick:e[0]||(e[0]=a=>y.bestToggle())},"주요 농작물"),!0===b.bestState?((0,t.wg)(),(0,t.iD)("div",k,[(0,t._)("ul",null,[((0,t.wg)(!0),(0,t.iD)(t.HY,null,(0,t.Ko)(b.test.bestFarm.length,((a,e)=>((0,t.wg)(),(0,t.iD)("li",{key:e},(0,n.zw)(e+1)+". "+(0,n.zw)(b.test.bestFarm[e]),1)))),128))])])):(0,t.kq)("",!0)]),(0,t._)("div",h,[(0,t._)("button",{onClick:e[1]||(e[1]=a=>y.addressToggle()),class:"address"},"농가 주소"),(0,t._)("div",g,[!0===b.addressState?((0,t.wg)(),(0,t.iD)("div",w,"지도 생길 부분")):(0,t.kq)("",!0)])])])])}var y=s(9123),C={components:{Header:y.Z},data(){return{headerName:"",map:null,test:{farmName:"따과의 즐거운 농장",name:"김따과",tel:"010-1234-1234",description:"이 세상에서 농사짓는게 제일 행복한 김따과 입니다 ^~^",address:{x:0,y:0},bestFarm:["사과","오렌지","망고","수박"]},bestState:!1,addressState:!1}},mounted(){if(window.kakao&&window.kakao.maps){const a=document.getElementById(".map"),e={center:new kakao.maps.LatLng(33.450701,126.570667),level:5};this.map=new kakao.maps.Map(a,e),console.log(a)}},methods:{addScript(){const a=document.createElement("script");a.onload=()=>kakao.maps.load(this.initMap),a.src="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e912469aedfe46c334cf869f731be1fa",document.head.appendChild(a)},bestToggle(){this.bestState=!this.bestState},addressToggle(){this.addressState=!this.addressState},initMap(){const a=document.getElementById(".map"),e={center:new kakao.maps.LatLng(33.450701,126.570667),level:5};this.map=new kakao.maps.Map(a,e),console.log(a)}}},S=s(89);const D=(0,S.Z)(C,[["render",b],["__scopeId","data-v-0c6d35a4"]]);var q=D}}]);
//# sourceMappingURL=522.14eae007.js.map