"use strict";(self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[]).push([[241,896],{7929:function(a,e,t){t.d(e,{Z:function(){return k}});var s=t(3396),r=t(7139);const n=a=>((0,s.dD)("data-v-0063bdd0"),a=a(),(0,s.Cn)(),a),i={class:"main_nav_t_div"},o={class:"main_t_nav"},l={class:"main_t_nav_list"},d=n((()=>(0,s._)("li",{class:"nav__btn"},[(0,s._)("a",{class:"nav__link",href:"alert"},[(0,s._)("i",{class:"fas fa-bell fa-2x","aria-hidden":"true"})])],-1))),m={class:"main_m_li_list"},c={class:"nav_m_link",href:"workout.html"},f={class:"nav__btn"},p=n((()=>(0,s._)("i",{class:"fas fa-chevron-left fa-2x","aria-hidden":"true"},null,-1))),_=[p];function h(a,e,t,n,p,h){return(0,s.wg)(),(0,s.iD)("div",i,[(0,s._)("nav",o,[(0,s._)("ul",l,[d,(0,s._)("li",m,[(0,s._)("p",c,(0,r.zw)(t.headerProps),1)]),(0,s._)("li",f,[(0,s._)("a",{class:"nav__link",onClick:e[0]||(e[0]=e=>a.$router.go(-1))},_)])])])])}var u={props:{headerProps:String}},g=t(89);const v=(0,g.Z)(u,[["render",h],["__scopeId","data-v-0063bdd0"]]);var k=v},4300:function(a,e,t){t.r(e),t.d(e,{default:function(){return z}});var s=t(3396),r=t(7139);const n=a=>((0,s.dD)("data-v-be9f12d8"),a=a(),(0,s.Cn)(),a),i={class:"farm-contain"},o={class:"farm-information"},l=["src"],d={class:"farm-name-tel"},m={class:"farm-userName"},c=n((()=>(0,s._)("h3",{class:"userName"},"이름",-1))),f={class:"farm-tel"},p=n((()=>(0,s._)("h3",{class:"tel"},"연락처",-1))),_={class:"farm-description"},h=n((()=>(0,s._)("h3",{class:"description"},"농가설명",-1))),u={class:"buttons"},g={class:"farm-best"},v={key:0,class:"best-detail"},k={class:"best-datas"},b={class:"farm-address"},D=n((()=>(0,s._)("div",{class:"address-detail"},null,-1))),w=n((()=>(0,s._)("div",{id:"map"},null,-1)));function I(a,e,t,n,I,S){const x=(0,s.up)("Header"),y=(0,s.up)("Slide");return(0,s.wg)(),(0,s.iD)("div",i,[(0,s.Wm)(x,{headerProps:I.test.farmName},null,8,["headerProps"]),((0,s.wg)(),(0,s.j4)(y,{imgData:I.imgData,key:I.reload},null,8,["imgData"])),(0,s._)("div",o,[(0,s._)("img",{class:"farm-image",src:null==I.farmIntroData.f_profile_img||""==I.farmIntroData.f_profile_img?"/member_profile_images/base_image.png":`/member_profile_images/${I.farmIntroData.f_profile_img}.png`,alt:"농가 사진"},null,8,l)]),(0,s._)("div",d,[(0,s._)("div",m,[c,(0,s._)("p",null,(0,r.zw)(I.farmIntroData.f_name),1)]),(0,s._)("div",f,[p,(0,s._)("p",null,(0,r.zw)(I.farmIntroData.f_phonenum),1)])]),(0,s._)("div",_,[h,(0,s._)("p",null,(0,r.zw)(I.farmIntroData.f_explanation),1)]),(0,s._)("div",u,[(0,s._)("div",g,[(0,s._)("button",{class:"best",onClick:e[0]||(e[0]=a=>S.bestToggle())},"주요 농작물"),!0===I.bestState?((0,s.wg)(),(0,s.iD)("div",v,[(0,s._)("ul",k,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(I.bestFarm.length,((a,e)=>((0,s.wg)(),(0,s.iD)("li",{key:e},(0,r.zw)(e+1)+". "+(0,r.zw)(I.bestFarm[e]),1)))),128))])])):(0,s.kq)("",!0)]),(0,s._)("div",b,[(0,s._)("button",{onClick:e[1]||(e[1]=a=>S.addressToggle()),class:"address"},"농가 주소"),D])]),w])}var S=t(6265),x=t.n(S),y=t(7929),C=t(880),M={components:{Header:y.Z,Slide:C["default"]},data(){return{reload:0,headerName:"",test:{farmName:"따과의 즐거운 농장",name:"김따과",tel:"010-1234-1234",description:"이 세상에서 농사짓는게 제일 행복한 김따과 입니다 ^~^",address:{x:0,y:0},bestFarm:["사과","오렌지","망고","수박"]},bestState:!1,addressState:!1,f_location:"충북 충주시 대소원면 대학로 50",f_farm_name:"교통농가",farmIntroData:{},user:JSON.parse(localStorage.getItem("user")),imgData:[],bestFarm:[]}},watch:{farmIntroData(){}},created(){console.log(this.$route.params.id),x().get(`/api/farmMember/${this.$route.params.id}`,{headers:{TOKEN:this.user.token}}).then((a=>{console.log(a.data),this.farmIntroData=a.data;let e=a.data.f_major_crop.split(", ");for(let t=0;t<e.length;t++)this.bestFarm.push(e[t]),console.log(this.bestFarm);if(void 0==this.farmIntroData.f_img)this.imgData.push("base_farm_image");else{console.log(a.data);let e=this.farmIntroData.f_img[this.farmIntroData.f_img.length-1];console.log(e);for(let a=0;a<e;a++)this.imgData.push(this.farmIntroData.f_img.replace("(0)",`(${a})`))}this.reload=1,console.log(this.imgData),void 0==this.farmIntroData.f_explanation&&(this.farmIntroData.f_explanation=this.test.description)})).catch((a=>{console.log(a)}))},beforeMount(){},mounted(){if(window.kakao&&window.kakao.maps)this.initMap();else{const a=document.createElement("script");a.style.width="300px",a.style.height="300px",a.onload=()=>kakao.maps.load(this.initMap),a.src="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e912469aedfe46c334cf869f731be1fa&libraries=services",document.head.appendChild(a)}},methods:{initMap(){const a=document.getElementById("map");a.style.width="300px",a.style.height="300px";const e={center:new kakao.maps.LatLng(33.450701,126.570667),level:5};var t=new kakao.maps.Map(a,e),s=new kakao.maps.services.Geocoder;console.log(this.farmIntroData),s.addressSearch(this.farmIntroData.f_location,((a,e)=>{if(console.log(a),console.log(e),e===kakao.maps.services.Status.OK){var s=new kakao.maps.LatLng(a[0].y,a[0].x),r=new kakao.maps.Marker({map:t,position:s}),n=new kakao.maps.InfoWindow({content:`<div style="width:150px;text-align:center;padding:6px 0;">${this.farmIntroData.f_farm_name}</div>`});n.open(t,r),t.setCenter(s)}}))},addScript(){const a=document.createElement("script");a.onload=()=>kakao.maps.load(this.initMap()),a.src="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e912469aedfe46c334cf869f731be1fa",document.head.appendChild(a)},bestToggle(){this.bestState=!this.bestState},addressToggle(){this.addressState=!this.addressState}}},N=t(89);const $=(0,N.Z)(M,[["render",I],["__scopeId","data-v-be9f12d8"]]);var z=$}}]);
//# sourceMappingURL=241.bb3774a2.js.map