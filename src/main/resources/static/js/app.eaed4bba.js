(function(){"use strict";var e={9874:function(e,n,t){var o=t(9242),a=t(3396),r=t(7718),i=t(9271);function c(e,n,t,o,c,l){const u=(0,a.up)("router-view");return(0,a.wg)(),(0,a.j4)(r.q,{class:"app"},{default:(0,a.w5)((()=>[(0,a.Wm)(i.O,{class:"main"},{default:(0,a.w5)((()=>[(0,a.Wm)(u)])),_:1})])),_:1})}var l=t(6337),u={name:"App",components:{bottomNav:l["default"]},data:()=>({})},s=t(89);const m=(0,s.Z)(u,[["render",c]]);var d=m,f=t(678);const p=[{path:"/",redirect:"/login"},{path:"/test",name:"test",component:()=>t.e(901).then(t.bind(t,7901))},{path:"/test1",name:"test1",component:()=>t.e(423).then(t.bind(t,6423))},{path:"/login",name:"login",component:()=>Promise.all([t.e(439),t.e(617)]).then(t.bind(t,6843))},{path:"/login",name:"login",component:()=>Promise.all([t.e(439),t.e(617)]).then(t.bind(t,6843))},{path:"/clause",name:"clause",component:()=>t.e(748).then(t.bind(t,6748))},{path:"/signup",name:"signup",component:()=>Promise.all([t.e(439),t.e(325)]).then(t.bind(t,8325))},{path:"/signupFarm",name:"signupFarm",component:()=>Promise.all([t.e(439),t.e(325)]).then(t.bind(t,8325))},{path:"/check_user",name:"check_user",component:()=>t.e(686).then(t.bind(t,1686))},{path:"/check_farm",name:"check_farm",component:()=>t.e(696).then(t.bind(t,2696))},{path:"/farm_user_info",name:"farm_user_info",component:()=>t.e(760).then(t.bind(t,1760))},{path:"/farm_biz_info",name:"farm_biz_info",component:()=>t.e(952).then(t.bind(t,9952))},{path:"/main",name:"main",component:()=>Promise.all([t.e(388),t.e(957)]).then(t.bind(t,1957))},{path:"/auction",name:"auction",component:()=>Promise.all([t.e(388),t.e(936),t.e(866),t.e(842)]).then(t.bind(t,4858))},{path:"/auction_reg",name:"auction_reg",component:()=>t.e(108).then(t.bind(t,6108))},{path:"/search",name:"search",component:()=>Promise.all([t.e(388),t.e(936),t.e(378)]).then(t.bind(t,7378))},{path:"/trand",name:"trand",component:()=>t.e(776).then(t.bind(t,5776))},{path:"/farm_mypage",name:"farm_mypage",component:()=>t.e(253).then(t.bind(t,4253))},{path:"/alert",name:"alert",component:()=>t.e(12).then(t.bind(t,9012))},{path:"/webSocketTest",name:"webSocketTest",component:()=>Promise.all([t.e(388),t.e(936),t.e(811)]).then(t.bind(t,3811))},{path:"/farm_calculate",name:"farm_calculate",component:()=>t.e(834).then(t.bind(t,2834))},{path:"/farm_calculate_clear",name:"farm_calculate_clear",component:()=>t.e(560).then(t.bind(t,3560))},{path:"/auction_detail/:id",name:"auction_detail",component:()=>Promise.all([t.e(388),t.e(936),t.e(866),t.e(941)]).then(t.bind(t,4331))},{path:"/auction_detail/farm_intro",name:"farm_intro",component:()=>t.e(759).then(t.bind(t,8759))},{path:"/farm_mypage_auction",name:"farm_mypage_auction",component:()=>t.e(647).then(t.bind(t,5647))},{path:"/farm_mypage_auction/writeReview",name:"writeReview",component:()=>t.e(805).then(t.bind(t,4805))},{path:"/farm_mypage_get_review",name:"farm_mypage_get_review",component:()=>t.e(82).then(t.bind(t,8617))},{path:"/farm_mypage_post_review",name:"farm_mypage_post_review",component:()=>t.e(81).then(t.bind(t,5081))},{path:"/farm_mypage_keep",name:"farm_mypage_keep",component:()=>t.e(381).then(t.bind(t,9381))},{path:"/farm_profile",name:"farm_profile",component:()=>Promise.all([t.e(439),t.e(270)]).then(t.bind(t,4270))},{path:"/ServiceCenter",name:"ServiceCenter",component:()=>t.e(688).then(t.bind(t,1688))},{path:"/farm_loc",name:"farm_loc",component:()=>t.e(778).then(t.bind(t,3778))},{path:"/review",name:"review",component:()=>t.e(296).then(t.bind(t,1296))},{path:"/bottomNav",name:"bottomNav",component:()=>Promise.resolve().then(t.bind(t,6337))},{path:"/add_profile",name:"add_profile",component:()=>t.e(192).then(t.bind(t,2192))},{path:"/kakaoMap",name:"kakaoMap",component:()=>t.e(398).then(t.bind(t,2398))},{path:"/slide",name:"slide",component:()=>Promise.all([t.e(866),t.e(673)]).then(t.bind(t,7866))},{path:"/consumer_mypage",name:"consumer_mypage",component:()=>t.e(752).then(t.bind(t,5752))},{path:"/consumer_profile",name:"consumer_profile",component:()=>Promise.all([t.e(439),t.e(980)]).then(t.bind(t,9980))},{path:"/consumer_loc",name:"consumer_loc",component:()=>t.e(938).then(t.bind(t,4938))}],h=(0,f.p7)({history:(0,f.PO)("/"),routes:p});h.beforeEach(((e,n,t)=>{t()}));var b=h,g=t(65);const _=new g.ZP.Store({state:{userInfo:JSON.parse(localStorage.getItem("user")),isLogin:!1,isLoginError:!1},mutations:{loginSuccess(e,n){e.isLogin=!0,e.isLoginError=!1,e.userInfo=n},loginError(e){e.isLogin=!1,e.isLoginError=!0},logout(e){e.isLogin=!1,e.isLoginError=!1,e.userInfo=null},TOKEN_SAVE:(e,n)=>{console.log("TOKEN_SAVE: "+n),e.userInfo=n,console.log(e.userInfo)}},actions:{}}),v=new g.ZP.Store({state:{count:1122,id:"스토어 모듈화 성공적"}}),y=new g.ZP.Store({state:{user:{kindOfUser:"45",name:null,email:null,passwd:null,phonenum:null}}});var w=t(6265),E=t.n(w),L=(0,g.MT)({modules:{signup:y,login:_,test:v},state:{existEmail:!0,kindOfFarm:null,user:{name:null,email:null,passwd:null,phonenum:null},config:{headers:{TOKEN:null}},auctionList:[],limit:2,alertList:[],keywordList:[],popularKeywordList:[]},mutations:{KIND_OF_FARM:(e,n)=>{console.log(n),e.kindOfFarm=n},FARM_INFO:(e,n)=>{console.log(n),e.user=n},EXIST_EMAIL:(e,n)=>{e.existEmail=n,console.log(e.existEmai)},TOKEN_SAVE:(e,n)=>{console.log("TOKEN_SAVE: "+n),e.config.headers.TOKEN=n,console.log(e.config)},INIT_AUCTION_LIST:(e,n)=>{console.log(n),e.auctionList=n,console.log(e.auctionList)},PUSH_AUCTION:(e,n)=>{console.log(n),e.auctionList.push(n)},RESET_AUCTION_LIST:e=>{e.auctionList=[]},UP_LIMIT:(e,n)=>{console.log(n),e.limit+=n},RESET_LIMIT:e=>{e.limit=0},UPDATE_BID_PRICE:(e,n)=>{console.log("UPDATE_BID_PRICE"),console.log(n);for(let t=0;t<e.auctionList.length;t++)if(n.auction_Id==e.auctionList[t].auction_Id){console.log(e.auctionList[t].auction_Id),console.log(n.auction_Id),e.auctionList[t].bid_price=n.bid_price;break}},INIT_ALERT_LIST:(e,n)=>{console.log(n),e.alertList=n},PUSH_ALERT_LIST:(e,n)=>{console.log(n),e.alertList.push(n)},CHECKED_ALERT:(e,n)=>{console.log("alertList_index: "+n),e.alertList[n].checked=1},PUSH_KEYWORD_LIST:(e,n)=>{console.log(n);let t=!0;for(let o=0;o<e.keywordList.length;o++)e.keywordList[o]==n&&(t=!1);t&&e.keywordList.push(n)},INIT_POPULAR_KEYWORD_LIST:(e,n)=>{console.log(n),e.popularKeywordList=n}},actions:{existEmail:({commit:e},n)=>{console.log(n),E().get("/api/existEmail",{params:{email:n}}).then((n=>{console.log(n),e("EXIST_EMAIL",1==n.data),1==n.data?alert("이미 존재하는 아이디입니다!"):alert("사용 가능한 아이디입니다!")})).catch((e=>{console.log(e)}))}}}),O=(t(9773),t(3398)),I=(0,O.Rd)();async function T(){const e=await t.e(461).then(t.t.bind(t,3657,23));e.load({google:{families:["Roboto:100,300,400,500,700,900&display=swap"]}})}var P=t(6400),S=t(7339);T(),(0,o.ri)(d).use(b).use(L).use(I).use(P.ZP).use(S.ZP).mount("#app")},6337:function(e,n,t){t.r(n),t.d(n,{default:function(){return b}});var o=t(3396),a=t(7139),r=t(4678),i=t(3289);const c=e=>((0,o.dD)("data-v-523df10e"),e=e(),(0,o.Cn)(),e),l={class:"bottom-contain"},u={class:"icon-text"},s=c((()=>(0,o._)("span",{class:"icon-text"},"My Page",-1))),m=(0,o.Uk)("mdi-account");function d(e,n,t,c,d,f){return(0,o.wg)(),(0,o.iD)("div",l,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(d.button.url.length,((e,n)=>((0,o.wg)(),(0,o.j4)(r.T,{key:n,to:d.button.url[n],class:"button"},{default:(0,o.w5)((()=>[(0,o._)("span",u,(0,a.zw)(d.button.text[n]),1),(0,o.Wm)(i.t,{class:"icon"},{default:(0,o.w5)((()=>[(0,o.Uk)((0,a.zw)(d.button.icon[n]),1)])),_:2},1024)])),_:2},1032,["to"])))),128)),(0,o.Wm)(r.T,{onClick:n[0]||(n[0]=e=>f.navigatedivision()),class:"button"},{default:(0,o.w5)((()=>[s,(0,o.Wm)(i.t,{class:"icon"},{default:(0,o.w5)((()=>[m])),_:1})])),_:1})])}var f={data(){return{user:JSON.parse(localStorage.getItem("user"))||"",consumer_id:this.consumer_id,farm_id:this.farm_id,button:{url:["/main","/search","/auction"],icon:["mdi-home","mdi-magnify","mdi-gavel"],text:["Home","Search","Auction"]}}},methods:{navigatedivision(){console.log(this.user),console.log(this.user.consumer_id),null==this.user.consumer_id?this.$router.push({name:"farm_mypage"}):this.$router.push({name:"consumer_mypage"})},getPost(){JSON.parse(localStorage.getItem("user"))}}},p=t(89);const h=(0,p.Z)(f,[["render",d],["__scopeId","data-v-523df10e"]]);var b=h}},n={};function t(o){var a=n[o];if(void 0!==a)return a.exports;var r=n[o]={id:o,loaded:!1,exports:{}};return e[o].call(r.exports,r,r.exports,t),r.loaded=!0,r.exports}t.m=e,function(){var e=[];t.O=function(n,o,a,r){if(!o){var i=1/0;for(s=0;s<e.length;s++){o=e[s][0],a=e[s][1],r=e[s][2];for(var c=!0,l=0;l<o.length;l++)(!1&r||i>=r)&&Object.keys(t.O).every((function(e){return t.O[e](o[l])}))?o.splice(l--,1):(c=!1,r<i&&(i=r));if(c){e.splice(s--,1);var u=a();void 0!==u&&(n=u)}}return n}r=r||0;for(var s=e.length;s>0&&e[s-1][2]>r;s--)e[s]=e[s-1];e[s]=[o,a,r]}}(),function(){t.n=function(e){var n=e&&e.__esModule?function(){return e["default"]}:function(){return e};return t.d(n,{a:n}),n}}(),function(){var e,n=Object.getPrototypeOf?function(e){return Object.getPrototypeOf(e)}:function(e){return e.__proto__};t.t=function(o,a){if(1&a&&(o=this(o)),8&a)return o;if("object"===typeof o&&o){if(4&a&&o.__esModule)return o;if(16&a&&"function"===typeof o.then)return o}var r=Object.create(null);t.r(r);var i={};e=e||[null,n({}),n([]),n(n)];for(var c=2&a&&o;"object"==typeof c&&!~e.indexOf(c);c=n(c))Object.getOwnPropertyNames(c).forEach((function(e){i[e]=function(){return o[e]}}));return i["default"]=function(){return o},t.d(r,i),r}}(),function(){t.d=function(e,n){for(var o in n)t.o(n,o)&&!t.o(e,o)&&Object.defineProperty(e,o,{enumerable:!0,get:n[o]})}}(),function(){t.f={},t.e=function(e){return Promise.all(Object.keys(t.f).reduce((function(n,o){return t.f[o](e,n),n}),[]))}}(),function(){t.u=function(e){return"js/"+(461===e?"webfontloader":e)+"."+{12:"efb3e21f",81:"a40d27e4",82:"98997367",108:"3ac7d203",192:"e10809cc",253:"d73c3301",270:"3bbebb08",296:"0c2ffaa1",325:"8481dd5f",378:"a470f414",381:"63a9feb2",388:"a6b2592e",398:"50890e95",423:"a59220f6",439:"ef8a5a65",461:"adfdfe01",560:"33da1aef",617:"78edf6ad",647:"052032da",673:"4d314bde",686:"11406faa",688:"5af156ad",696:"28b009f7",748:"d0208bd5",752:"cd86c9bc",759:"ba0d2b69",760:"5f7d7468",776:"daaad04b",778:"b6bd2870",805:"0949c5f2",811:"96453346",834:"546ccf48",842:"12c86ccb",866:"7358f9e3",901:"547c156d",936:"6e53aabb",938:"53100722",941:"3ab9414d",952:"ba0af711",957:"edb2034c",980:"979c9305"}[e]+".js"}}(),function(){t.miniCssF=function(e){return"css/"+e+"."+{12:"913cca83",108:"c21c8feb",253:"0babc647",296:"3c0cbf4a",325:"e1cec9c0",378:"c2cc0373",439:"d0732a87",617:"a49917cc",647:"703e4368",673:"d44ac952",686:"8641b99d",696:"8bc897ca",748:"2e076f4e",752:"0babc647",759:"d1fbe04a",805:"efd12914",834:"e212c392",842:"958a704d",941:"f94e5f6e",957:"32d72c59"}[e]+".css"}}(),function(){t.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){t.o=function(e,n){return Object.prototype.hasOwnProperty.call(e,n)}}(),function(){var e={},n="realvue:";t.l=function(o,a,r,i){if(e[o])e[o].push(a);else{var c,l;if(void 0!==r)for(var u=document.getElementsByTagName("script"),s=0;s<u.length;s++){var m=u[s];if(m.getAttribute("src")==o||m.getAttribute("data-webpack")==n+r){c=m;break}}c||(l=!0,c=document.createElement("script"),c.charset="utf-8",c.timeout=120,t.nc&&c.setAttribute("nonce",t.nc),c.setAttribute("data-webpack",n+r),c.src=o),e[o]=[a];var d=function(n,t){c.onerror=c.onload=null,clearTimeout(f);var a=e[o];if(delete e[o],c.parentNode&&c.parentNode.removeChild(c),a&&a.forEach((function(e){return e(t)})),n)return n(t)},f=setTimeout(d.bind(null,void 0,{type:"timeout",target:c}),12e4);c.onerror=d.bind(null,c.onerror),c.onload=d.bind(null,c.onload),l&&document.head.appendChild(c)}}}(),function(){t.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){t.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e}}(),function(){t.p="/"}(),function(){var e=function(e,n,t,o){var a=document.createElement("link");a.rel="stylesheet",a.type="text/css";var r=function(r){if(a.onerror=a.onload=null,"load"===r.type)t();else{var i=r&&("load"===r.type?"missing":r.type),c=r&&r.target&&r.target.href||n,l=new Error("Loading CSS chunk "+e+" failed.\n("+c+")");l.code="CSS_CHUNK_LOAD_FAILED",l.type=i,l.request=c,a.parentNode.removeChild(a),o(l)}};return a.onerror=a.onload=r,a.href=n,document.head.appendChild(a),a},n=function(e,n){for(var t=document.getElementsByTagName("link"),o=0;o<t.length;o++){var a=t[o],r=a.getAttribute("data-href")||a.getAttribute("href");if("stylesheet"===a.rel&&(r===e||r===n))return a}var i=document.getElementsByTagName("style");for(o=0;o<i.length;o++){a=i[o],r=a.getAttribute("data-href");if(r===e||r===n)return a}},o=function(o){return new Promise((function(a,r){var i=t.miniCssF(o),c=t.p+i;if(n(i,c))return a();e(o,c,a,r)}))},a={143:0};t.f.miniCss=function(e,n){var t={12:1,108:1,253:1,296:1,325:1,378:1,439:1,617:1,647:1,673:1,686:1,696:1,748:1,752:1,759:1,805:1,834:1,842:1,941:1,957:1};a[e]?n.push(a[e]):0!==a[e]&&t[e]&&n.push(a[e]=o(e).then((function(){a[e]=0}),(function(n){throw delete a[e],n})))}}(),function(){var e={143:0};t.f.j=function(n,o){var a=t.o(e,n)?e[n]:void 0;if(0!==a)if(a)o.push(a[2]);else if(673!=n){var r=new Promise((function(t,o){a=e[n]=[t,o]}));o.push(a[2]=r);var i=t.p+t.u(n),c=new Error,l=function(o){if(t.o(e,n)&&(a=e[n],0!==a&&(e[n]=void 0),a)){var r=o&&("load"===o.type?"missing":o.type),i=o&&o.target&&o.target.src;c.message="Loading chunk "+n+" failed.\n("+r+": "+i+")",c.name="ChunkLoadError",c.type=r,c.request=i,a[1](c)}};t.l(i,l,"chunk-"+n,n)}else e[n]=0},t.O.j=function(n){return 0===e[n]};var n=function(n,o){var a,r,i=o[0],c=o[1],l=o[2],u=0;if(i.some((function(n){return 0!==e[n]}))){for(a in c)t.o(c,a)&&(t.m[a]=c[a]);if(l)var s=l(t)}for(n&&n(o);u<i.length;u++)r=i[u],t.o(e,r)&&e[r]&&e[r][0](),e[r]=0;return t.O(s)},o=self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[];o.forEach(n.bind(null,0)),o.push=n.bind(null,o.push.bind(o))}();var o=t.O(void 0,[998],(function(){return t(9874)}));o=t.O(o)})();
//# sourceMappingURL=app.eaed4bba.js.map