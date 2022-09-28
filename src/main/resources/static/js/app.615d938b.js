(function(){"use strict";var e={915:function(e,t,n){var o=n(9242),a=n(3396),r=n(7718),i=n(9271);function c(e,t,n,o,c,l){const s=(0,a.up)("AlertBox"),u=(0,a.up)("router-view");return(0,a.wg)(),(0,a.j4)(r.q,{class:"app"},{default:(0,a.w5)((()=>[(0,a.Wm)(i.O,{class:"main"},{default:(0,a.w5)((()=>[(0,a.Wm)(s,{class:"alert-box"}),(0,a.Wm)(u)])),_:1})])),_:1})}var l=n(7139);const s=e=>((0,a.dD)("data-v-d9368372"),e=e(),(0,a.Cn)(),e),u={class:"alert-box-contain"},m={class:"alert-box"},d=["src"],f={class:"alert-information"},p=s((()=>(0,a._)("p",null,null,-1))),h={class:"alert-user"},g={class:"alert-title"};function _(e,t,n,o,r,i){return(0,a.wg)(),(0,a.iD)("div",u,[(0,a._)("div",m,[(0,a._)("img",{src:`/product_images/${r.alertData.product_img_name}.png`,alt:"알림 이미지",width:"50",height:"50"},null,8,d),(0,a._)("div",f,[p,(0,a._)("p",h,(0,l.zw)(r.alertData.f_farm_name),1),(0,a._)("p",g,(0,l.zw)(r.alertData.auction_name),1)])])])}var b={data(){return{alertState:0,checkUser:null,id:null,user:JSON.parse(localStorage.getItem("user")),alertData:{}}},methods:{modalToggle(){console.log("열리는 중~~");let e=document.querySelector(".alert-box-contain");e.classList.add("event"),setTimeout((()=>{e.classList.remove("event")}),3e3)},checkAlert(){localStorage.getItem("user")?this.$sse.create("https://118.67.134.38:80/api/subscribeAlert/"+this.checkUser+"/"+this.id).on("init",(e=>{console.log("init: ",JSON.parse(e)),this.$store.commit("INIT_ALERT_LIST",JSON.parse(e))})).on("alert",(e=>{let t=JSON.parse(e);console.log("받은 데이터",JSON.parse(e)),this.alertData=t,console.log("!!!",this.alertData),this.$store.commit("PUSH_ALERT_LIST",t),this.modalToggle()})).on("error",(e=>console.error("Failed to parse or lost connection:",e))).connect().catch((e=>console.error("Failed make initial connection:",e))):JSON.parse(localStorage.getItem("user"))||this.$router.push("/login")}},mounted(){localStorage.getItem("checkUser")&&this.checkAlert()}},v=n(89);const S=(0,v.Z)(b,[["render",_],["__scopeId","data-v-d9368372"]]);var I=S,w=n(5483),E={name:"App",components:{bottomNav:w["default"],AlertBox:I},mounted(){Date.now()>JSON.parse(localStorage.getItem("expire"))&&(alert("로그아웃 되었습니다!"),console.log("현재시간",Date.now()),console.log("로그아웃 예정 시간",JSON.parse(localStorage.getItem("expire"))),this.$store.commit("LOGOUT"),this.$router.push("/login"))}};const T=(0,v.Z)(E,[["render",c]]);var L=T,y=n(678);const O=[{path:"/",redirect:localStorage.getItem("user")?"/main":"/login"},{path:"/test",name:"test",component:()=>n.e(901).then(n.bind(n,7901))},{path:"/test1",name:"test1",component:()=>n.e(423).then(n.bind(n,6423))},{path:"/login",name:"login",component:()=>Promise.all([n.e(991),n.e(222)]).then(n.bind(n,7222))},{path:"/login/searchId",name:"searchId",component:()=>Promise.all([n.e(991),n.e(975),n.e(913)]).then(n.bind(n,7913))},{path:"/login/searchPw",name:"searchPw",component:()=>Promise.all([n.e(991),n.e(975),n.e(913)]).then(n.bind(n,7913))},{path:"/clause",name:"clause",component:()=>n.e(748).then(n.bind(n,6748))},{path:"/signup",name:"signup",component:()=>Promise.all([n.e(991),n.e(975),n.e(603)]).then(n.bind(n,1603))},{path:"/signupFarm",name:"signupFarm",component:()=>Promise.all([n.e(991),n.e(975),n.e(603)]).then(n.bind(n,1603))},{path:"/check_user",name:"check_user",component:()=>n.e(686).then(n.bind(n,1686))},{path:"/check_farm",name:"check_farm",component:()=>n.e(696).then(n.bind(n,2696))},{path:"/farm_user_info",name:"farm_user_info",component:()=>Promise.all([n.e(975),n.e(798)]).then(n.bind(n,4798))},{path:"/farm_biz_info",name:"farm_biz_info",component:()=>Promise.all([n.e(975),n.e(19)]).then(n.bind(n,6019))},{path:"/main",name:"main",component:()=>Promise.all([n.e(388),n.e(314)]).then(n.bind(n,2314))},{path:"/auction",name:"auction",component:()=>Promise.all([n.e(388),n.e(177),n.e(936),n.e(195)]).then(n.bind(n,3230))},{path:"/auction_reg",name:"auction_reg",component:()=>Promise.all([n.e(177),n.e(932)]).then(n.bind(n,2368))},{path:"/auction_reg/:id",name:"auction_reg_patch",component:()=>Promise.all([n.e(177),n.e(932)]).then(n.bind(n,2368))},{path:"/search",name:"search",component:()=>Promise.all([n.e(388),n.e(936),n.e(441)]).then(n.bind(n,6441))},{path:"/trand",name:"trand",component:()=>n.e(776).then(n.bind(n,5776))},{path:"/farm_mypage",name:"farm_mypage",component:()=>n.e(529).then(n.bind(n,3461))},{path:"/alert",name:"alert",component:()=>n.e(826).then(n.bind(n,9826))},{path:"/webSocketTest",name:"webSocketTest",component:()=>Promise.all([n.e(388),n.e(936),n.e(811)]).then(n.bind(n,3811))},{path:"/farm_calculate",name:"farm_calculate",component:()=>n.e(834).then(n.bind(n,2834))},{path:"/farm_calculate_clear",name:"farm_calculate_clear",component:()=>n.e(560).then(n.bind(n,3560))},{path:"/auction_detail/:id",name:"auction_detail",component:()=>Promise.all([n.e(991),n.e(388),n.e(177),n.e(936),n.e(499)]).then(n.bind(n,2170))},{path:"/auction_detail/farm_intro/:id",name:"farm_intro",component:()=>n.e(978).then(n.bind(n,8978))},{path:"/farm_mypage_auction",name:"farm_mypage_auction",component:()=>n.e(401).then(n.bind(n,5401))},{path:"/farm_mypage_auction/writeReview/:id",name:"writeReview",component:()=>n.e(405).then(n.bind(n,3405))},{path:"/farm_mypage_get_review",name:"farm_mypage_get_review",component:()=>n.e(964).then(n.bind(n,3964))},{path:"/farm_mypage_post_review",name:"farm_mypage_post_review",component:()=>n.e(38).then(n.bind(n,8038))},{path:"/farm_mypage_keep",name:"farm_mypage_keep",component:()=>n.e(218).then(n.bind(n,8218))},{path:"/farm_profile",name:"farm_profile",component:()=>Promise.all([n.e(991),n.e(975),n.e(615)]).then(n.bind(n,3615))},{path:"/ServiceCenter",name:"ServiceCenter",component:()=>n.e(688).then(n.bind(n,1688))},{path:"/farm_loc",name:"farm_loc",component:()=>n.e(700).then(n.bind(n,5700))},{path:"/review",name:"review",component:()=>n.e(964).then(n.bind(n,3964))},{path:"/bottomNav",name:"bottomNav",component:()=>Promise.resolve().then(n.bind(n,5483))},{path:"/add_profile",name:"add_profile",component:()=>n.e(469).then(n.bind(n,9469))},{path:"/slide",name:"slide",component:()=>Promise.all([n.e(177),n.e(624)]).then(n.bind(n,1177))},{path:"/consumer_mypage",name:"consumer_mypage",component:()=>n.e(353).then(n.bind(n,6353))},{path:"/consumer_profile",name:"consumer_profile",component:()=>Promise.all([n.e(991),n.e(975),n.e(959)]).then(n.bind(n,959))},{path:"/consumer_loc",name:"consumer_loc",component:()=>n.e(754).then(n.bind(n,5754))}],A=(0,y.p7)({history:(0,y.PO)("/"),routes:O});A.beforeEach(((e,t,n)=>{n()}));var P=A,k=n(65);const N=new k.ZP.Store({state:{userInfo:null,isLogin:!1,isLoginError:!1},mutations:{loginSuccess(e,t){e.isLogin=!0,e.isLoginError=!1,e.userInfo=t},loginError(e){e.isLogin=!1,e.isLoginError=!0},logout(e){e.isLogin=!1,e.isLoginError=!1,e.userInfo=null},TOKEN_SAVE:(e,t)=>{console.log("TOKEN_SAVE: "+t),e.userInfo=t,console.log(e.userInfo)}},actions:{}}),C=new k.ZP.Store({state:{count:1122,id:"스토어 모듈화 성공적"}}),D=new k.ZP.Store({state:{user:{kindOfUser:"45",name:null,email:null,passwd:null,phonenum:null}}}),R=new k.ZP.Store({namespaced:!0,state:{reviewData:[]},mutations:{getReviewData:(e,t)=>{console.log(t),e.reviewData.push(t)}},actions:{}});var U=n(6265),x=n.n(U),j=(0,k.MT)({modules:{signup:D,login:N,test:C,writeReview:R},state:{existEmail:!0,kindOfFarm:null,user:{name:null,email:null,passwd:null,phonenum:null},config:{headers:{TOKEN:null}},review:{getReviewData:{}},auctionList:[],limit:0,searchAuctionList:[],alertList:[],keywordList:[],popularKeywordList:[]},mutations:{CHECH_USER_STATE:(e,t)=>{console.log(t),!1===t.state?e.checkUser="farm":e.checkUser="consumer",e.id=t.id,console.log(e.id),console.log(e.checkUser)},GET_REVIEW_DATA:(e,t)=>{console.log(t),e.review.getReviewData=t},KIND_OF_FARM:(e,t)=>{console.log(t),e.kindOfFarm=t},FARM_INFO:(e,t)=>{console.log(t),e.user=t},EXIST_EMAIL:(e,t)=>{e.existEmail=t,console.log(e.existEmai)},TOKEN_SAVE:(e,t)=>{console.log("TOKEN_SAVE: "+t),e.config.headers.TOKEN=t,console.log(e.config)},LOGOUT:e=>{let t=JSON.parse(localStorage.getItem("user"));console.log(t);let n=t.c_email,o=null;void 0!=n?o="consumer":(n=t.f_email,o="farm"),console.log(o+"   "+n),x().get(`/api/logout/${o}/${n}`,{}).then((e=>{console.log("res: "+e),localStorage.removeItem("user"),localStorage.removeItem("checkUser"),localStorage.removeItem("id"),localStorage.removeItem("auction")})).catch((e=>{console.log(e)}))},INIT_AUCTION_LIST:(e,t)=>{console.log(t),e.auctionList=t,console.log(e.auctionList)},PUSH_AUCTION:(e,t)=>{console.log(t),e.auctionList.push(t)},RESET_AUCTION_LIST:e=>{e.auctionList=[]},UP_LIMIT:(e,t)=>{e.limit+=t},RESET_LIMIT:e=>{e.limit=0},UPDATE_BID_PRICE:(e,t)=>{console.log("UPDATE_BID_PRICE"),console.log(t);for(let n=0;n<e.auctionList.length;n++)if(t.auction_Id==e.auctionList[n].auction_Id){console.log(e.auctionList[n].auction_Id),console.log(t.auction_Id),e.auctionList[n].bid_price=t.bid_price;break}},INIT_ALERT_LIST:(e,t)=>{console.log("!!",t),e.alertList=t},PUSH_ALERT_LIST:(e,t)=>{e.alertList.push(t)},CHECKED_ALERT:(e,t)=>{console.log("alertList_index: "+t),e.alertList[t].checked=1},PUSH_KEYWORD_LIST:(e,t)=>{console.log(t);let n=!0;for(let o=0;o<e.keywordList.length;o++)e.keywordList[o]==t&&(n=!1);n&&e.keywordList.push(t)},INIT_POPULAR_KEYWORD_LIST:(e,t)=>{console.log(t),e.popularKeywordList=t},INIT_SEARCH_AUCTION_LIST:(e,t)=>{console.log(t),e.searchAuctionList=t,console.log(e.searchAuctionList)},PUSH_SEARCH_AUCTION:(e,t)=>{console.log(t),e.searchAuctionList.push(t)},RESET_SEARCH_AUCTION_LIST:e=>{e.searchAuctionList=[]},UPDATE_SEARCH_BID_PRICE:(e,t)=>{console.log("UPDATE_BID_PRICE"),console.log(t);for(let n=0;n<e.searchAuctionList.length;n++)if(t.auction_Id==e.searchAuctionList[n].auction_Id){e.searchAuctionList[n].bid_price=t.bid_price;break}}},actions:{existEmail:({commit:e},t)=>{console.log(t),x().get("/api/existEmail",{params:{email:t}}).then((t=>{console.log(t),e("EXIST_EMAIL",1==t.data),1==t.data?alert("이미 존재하는 아이디입니다!"):alert("사용 가능한 아이디입니다!")})).catch((e=>{console.log(e)}))}}}),H=(n(9773),n(3398)),F=(0,H.Rd)();async function K(){const e=await n.e(461).then(n.t.bind(n,3657,23));e.load({google:{families:["Roboto:100,300,400,500,700,900&display=swap"]}})}var M=n(6400),$=n(7339);K(),(0,o.ri)(L).use(P).use(j).use(F).use(M.ZP).use($.ZP).mount("#app")},5483:function(e,t,n){n.r(t),n.d(t,{default:function(){return g}});var o=n(3396),a=n(7139),r=n(4678),i=n(3289);const c=e=>((0,o.dD)("data-v-3fc40c13"),e=e(),(0,o.Cn)(),e),l={class:"bottom-contain"},s={class:"icon-text"},u=c((()=>(0,o._)("span",{class:"icon-text"},"My Page",-1))),m=(0,o.Uk)("mdi-account");function d(e,t,n,c,d,f){return(0,o.wg)(),(0,o.iD)("div",l,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(d.button.url.length,((e,t)=>((0,o.wg)(),(0,o.j4)(r.T,{key:t,to:d.button.url[t],class:"button"},{default:(0,o.w5)((()=>[(0,o._)("span",s,(0,a.zw)(d.button.text[t]),1),(0,o.Wm)(i.t,{class:"icon"},{default:(0,o.w5)((()=>[(0,o.Uk)((0,a.zw)(d.button.icon[t]),1)])),_:2},1024)])),_:2},1032,["to"])))),128)),(0,o.Wm)(r.T,{onClick:t[0]||(t[0]=e=>f.navigatedivision()),class:"button"},{default:(0,o.w5)((()=>[u,(0,o.Wm)(i.t,{class:"icon"},{default:(0,o.w5)((()=>[m])),_:1})])),_:1})])}var f={data(){return{user:JSON.parse(localStorage.getItem("user"))||"",consumer_id:this.consumer_id,farm_id:this.farm_id,button:{url:["/main","/search","/auction"],icon:["mdi-home","mdi-magnify","mdi-gavel"],text:["Home","Search","Auction"]}}},methods:{navigatedivision(){console.log(this.user),console.log(this.user.consumer_id),null==this.user.consumer_id?this.$router.push({name:"farm_mypage"}):this.$router.push({name:"consumer_mypage"})},getPost(){JSON.parse(localStorage.getItem("user"))}}},p=n(89);const h=(0,p.Z)(f,[["render",d],["__scopeId","data-v-3fc40c13"]]);var g=h}},t={};function n(o){var a=t[o];if(void 0!==a)return a.exports;var r=t[o]={id:o,loaded:!1,exports:{}};return e[o].call(r.exports,r,r.exports,n),r.loaded=!0,r.exports}n.m=e,function(){var e=[];n.O=function(t,o,a,r){if(!o){var i=1/0;for(u=0;u<e.length;u++){o=e[u][0],a=e[u][1],r=e[u][2];for(var c=!0,l=0;l<o.length;l++)(!1&r||i>=r)&&Object.keys(n.O).every((function(e){return n.O[e](o[l])}))?o.splice(l--,1):(c=!1,r<i&&(i=r));if(c){e.splice(u--,1);var s=a();void 0!==s&&(t=s)}}return t}r=r||0;for(var u=e.length;u>0&&e[u-1][2]>r;u--)e[u]=e[u-1];e[u]=[o,a,r]}}(),function(){n.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return n.d(t,{a:t}),t}}(),function(){var e,t=Object.getPrototypeOf?function(e){return Object.getPrototypeOf(e)}:function(e){return e.__proto__};n.t=function(o,a){if(1&a&&(o=this(o)),8&a)return o;if("object"===typeof o&&o){if(4&a&&o.__esModule)return o;if(16&a&&"function"===typeof o.then)return o}var r=Object.create(null);n.r(r);var i={};e=e||[null,t({}),t([]),t(t)];for(var c=2&a&&o;"object"==typeof c&&!~e.indexOf(c);c=t(c))Object.getOwnPropertyNames(c).forEach((function(e){i[e]=function(){return o[e]}}));return i["default"]=function(){return o},n.d(r,i),r}}(),function(){n.d=function(e,t){for(var o in t)n.o(t,o)&&!n.o(e,o)&&Object.defineProperty(e,o,{enumerable:!0,get:t[o]})}}(),function(){n.f={},n.e=function(e){return Promise.all(Object.keys(n.f).reduce((function(t,o){return n.f[o](e,t),t}),[]))}}(),function(){n.u=function(e){return"js/"+(461===e?"webfontloader":e)+"."+{19:"1cdc57b8",38:"ee1c6606",177:"acc3d2e9",195:"1999d954",218:"e4f27bb2",222:"d19c40f3",314:"7b424622",353:"28659391",388:"a6b2592e",401:"f34611d6",405:"e356239a",423:"a59220f6",441:"6f8fb561",461:"adfdfe01",469:"7c683197",499:"9318022e",529:"56733f32",560:"33da1aef",603:"632be444",615:"7a4af3e1",624:"8f3bfa13",686:"11406faa",688:"5af156ad",696:"28b009f7",700:"e6a63f98",748:"d0208bd5",754:"06be8aad",776:"ba1f97b3",798:"4bf632f7",811:"96453346",826:"adb47f10",834:"cfdf6921",901:"547c156d",913:"7120f942",932:"8021abc5",936:"6e53aabb",959:"67229efd",964:"db694cb8",975:"6293e7c3",978:"999f0a74",991:"c89d6e06"}[e]+".js"}}(),function(){n.miniCssF=function(e){return"css/"+e+"."+{19:"be13cee3",38:"3e806b90",195:"15467bd4",218:"26050645",222:"6db53699",314:"1c4b4b1b",353:"30f5d7c2",401:"8e9997ed",405:"4e3c135f",441:"99898ca4",499:"bc8df79e",529:"0756211a",603:"20d1ddea",615:"be13cee3",624:"d488daa0",686:"8641b99d",696:"8bc897ca",748:"2e076f4e",798:"be13cee3",826:"3e806b90",834:"e212c392",913:"4cc3d88a",932:"2c352a23",959:"d0c6d91b",964:"9737f066",975:"374b0caa",978:"030ffdc0",991:"d0732a87"}[e]+".css"}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={},t="realvue:";n.l=function(o,a,r,i){if(e[o])e[o].push(a);else{var c,l;if(void 0!==r)for(var s=document.getElementsByTagName("script"),u=0;u<s.length;u++){var m=s[u];if(m.getAttribute("src")==o||m.getAttribute("data-webpack")==t+r){c=m;break}}c||(l=!0,c=document.createElement("script"),c.charset="utf-8",c.timeout=120,n.nc&&c.setAttribute("nonce",n.nc),c.setAttribute("data-webpack",t+r),c.src=o),e[o]=[a];var d=function(t,n){c.onerror=c.onload=null,clearTimeout(f);var a=e[o];if(delete e[o],c.parentNode&&c.parentNode.removeChild(c),a&&a.forEach((function(e){return e(n)})),t)return t(n)},f=setTimeout(d.bind(null,void 0,{type:"timeout",target:c}),12e4);c.onerror=d.bind(null,c.onerror),c.onload=d.bind(null,c.onload),l&&document.head.appendChild(c)}}}(),function(){n.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){n.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e}}(),function(){n.p="/"}(),function(){var e=function(e,t,n,o){var a=document.createElement("link");a.rel="stylesheet",a.type="text/css";var r=function(r){if(a.onerror=a.onload=null,"load"===r.type)n();else{var i=r&&("load"===r.type?"missing":r.type),c=r&&r.target&&r.target.href||t,l=new Error("Loading CSS chunk "+e+" failed.\n("+c+")");l.code="CSS_CHUNK_LOAD_FAILED",l.type=i,l.request=c,a.parentNode.removeChild(a),o(l)}};return a.onerror=a.onload=r,a.href=t,document.head.appendChild(a),a},t=function(e,t){for(var n=document.getElementsByTagName("link"),o=0;o<n.length;o++){var a=n[o],r=a.getAttribute("data-href")||a.getAttribute("href");if("stylesheet"===a.rel&&(r===e||r===t))return a}var i=document.getElementsByTagName("style");for(o=0;o<i.length;o++){a=i[o],r=a.getAttribute("data-href");if(r===e||r===t)return a}},o=function(o){return new Promise((function(a,r){var i=n.miniCssF(o),c=n.p+i;if(t(i,c))return a();e(o,c,a,r)}))},a={143:0};n.f.miniCss=function(e,t){var n={19:1,38:1,195:1,218:1,222:1,314:1,353:1,401:1,405:1,441:1,499:1,529:1,603:1,615:1,624:1,686:1,696:1,748:1,798:1,826:1,834:1,913:1,932:1,959:1,964:1,975:1,978:1,991:1};a[e]?t.push(a[e]):0!==a[e]&&n[e]&&t.push(a[e]=o(e).then((function(){a[e]=0}),(function(t){throw delete a[e],t})))}}(),function(){var e={143:0};n.f.j=function(t,o){var a=n.o(e,t)?e[t]:void 0;if(0!==a)if(a)o.push(a[2]);else if(/^(624|975)$/.test(t))e[t]=0;else{var r=new Promise((function(n,o){a=e[t]=[n,o]}));o.push(a[2]=r);var i=n.p+n.u(t),c=new Error,l=function(o){if(n.o(e,t)&&(a=e[t],0!==a&&(e[t]=void 0),a)){var r=o&&("load"===o.type?"missing":o.type),i=o&&o.target&&o.target.src;c.message="Loading chunk "+t+" failed.\n("+r+": "+i+")",c.name="ChunkLoadError",c.type=r,c.request=i,a[1](c)}};n.l(i,l,"chunk-"+t,t)}},n.O.j=function(t){return 0===e[t]};var t=function(t,o){var a,r,i=o[0],c=o[1],l=o[2],s=0;if(i.some((function(t){return 0!==e[t]}))){for(a in c)n.o(c,a)&&(n.m[a]=c[a]);if(l)var u=l(n)}for(t&&t(o);s<i.length;s++)r=i[s],n.o(e,r)&&e[r]&&e[r][0](),e[r]=0;return n.O(u)},o=self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[];o.forEach(t.bind(null,0)),o.push=t.bind(null,o.push.bind(o))}();var o=n.O(void 0,[998],(function(){return n(915)}));o=n.O(o)})();
//# sourceMappingURL=app.615d938b.js.map