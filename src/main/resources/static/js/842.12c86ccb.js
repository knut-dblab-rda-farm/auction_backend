"use strict";(self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[]).push([[842,673],{9123:function(t,e,s){s.d(e,{Z:function(){return v}});var o=s(3396),n=s(7139);const i=t=>((0,o.dD)("data-v-29d81906"),t=t(),(0,o.Cn)(),t),a={class:"main_nav_t_div"},l={class:"main_t_nav"},r={class:"main_t_nav_list"},c=i((()=>(0,o._)("li",{class:"nav__btn"},[(0,o._)("a",{class:"nav__link",href:"alert"},[(0,o._)("i",{class:"fas fa-bell fa-2x","aria-hidden":"true"})])],-1))),d={class:"main_m_li_list"},u={class:"nav_m_link",href:"workout.html"},h={class:"nav__btn"},_=i((()=>(0,o._)("i",{class:"fas fa-chevron-left fa-2x","aria-hidden":"true"},null,-1))),m=[_];function p(t,e,s,i,_,p){return(0,o.wg)(),(0,o.iD)("div",a,[(0,o._)("nav",l,[(0,o._)("ul",r,[c,(0,o._)("li",d,[(0,o._)("p",u,(0,n.zw)(s.headerProps),1)]),(0,o._)("li",h,[(0,o._)("a",{class:"nav__link",onClick:e[0]||(e[0]=e=>t.$router.go(-1))},m)])])])])}var g={props:{headerProps:String}},f=s(89);const b=(0,f.Z)(g,[["render",p],["__scopeId","data-v-29d81906"]]);var v=b},4568:function(t,e,s){s.d(e,{Z:function(){return d}});var o=s(3396);function n(t,e,s,n,i,a){return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("i",{onClick:e[0]||(e[0]=t=>a.likeClick()),class:"fa fa-heart like-icon"})])}s(6699);var i=s(6265),a=s.n(i),l={data(){return{like:{users:[],count:0,state:!1}}},async mounted(){await a().get("/api/checkWish",{params:{auction_id:this.$route.params.id,consumer_id:this.$store.state.login.userInfo.consumer_id}},{headers:{"Content-Type":"multipart/form-data"}}).then((t=>{alert(t)})).catch((t=>{console.log(t)}))},methods:{likeWho(){a().get("/api/likePostWho",{params:{post_id:this.$route.params.post}}).then((t=>{for(let e=0;e<t.data.length;e++)this.like.users.push(t.data[e].nickname),t.data[e].consumer_id===this.$store.state.login.user.consumer_id&&(document.querySelector(".likeAndShareBtn").style.backgroundColor="lightgrey",document.querySelector(".likeAndShareBtn").style.color="grey");this.like.users=this.like.users.filter((t=>void 0!==t)),this.like.count=this.like.users.length})).catch((t=>{console.log(t)}))},likeClick(){let t=JSON.parse(localStorage.getItem("userInformation"));this.like.users.includes(this.$store.state.login.user.consumer_id)?a()["delete"]("/api/likesMinus",{params:{post_id:this.$route.params.post,customer_id:this.$store.state.login.user.customer_id}}).then((t=>{console.log(t)})):a().post("/api/likesPlus",{post_id:this.$route.params.post,consumer_id:t.nickname}).then((t=>{console.log(t)}))}}},r=s(89);const c=(0,r.Z)(l,[["render",n],["__scopeId","data-v-857fb2a2"]]);var d=c},4858:function(t,e,s){s.r(e),s.d(e,{default:function(){return q}});var o=s(3396);const n={class:"auction-contain"};function i(t,e,s,i,a,l){const r=(0,o.up)("Header"),c=(0,o.up)("Slide"),d=(0,o.up)("AuctionList");return(0,o.wg)(),(0,o.iD)("div",n,[(0,o.Wm)(r,{headerProps:a.headerProps},null,8,["headerProps"]),(0,o.Wm)(c),(0,o.Wm)(d)])}var a=s(3266),l=s.n(a),r=s(4029),c=s.n(r),d=s(9123),u=s(7866),h=s(7139);const _=t=>((0,o.dD)("data-v-2bd77f26"),t=t(),(0,o.Cn)(),t),m={class:"white_div"},p={class:"goods_pay_section"},g={class:"goods_group"},f={class:"goods_group_list"},b=["onClick"],v={class:"goods_item"},k={class:"goods_thumb"},w=["src"],$={class:"goods_info"},N={class:"goods"},D={class:"name"},C={class:"info"},y=_((()=>(0,o._)("span",{class:"blind"},"상품금액",-1))),I={class:"date"},O=_((()=>(0,o._)("span",{class:"blind"},"등록일",-1))),P={class:"state _statusName value_color_green _click(nmp.front.order.timeline.home.list.openDeliveryPopup(/o/orderStatus/deliveryTracking/2022020394386781/ORDER_DELIVERY/api)) _stopDefault"},S={class:"guide"},M={class:"seller_item"},T={class:"inner"},E={class:"seller"},L={class:"tel"},z=_((()=>(0,o._)("br",null,null,-1))),K=_((()=>(0,o._)("br",null,null,-1))),U={style:{display:"none"}};function A(t,e,s,n,i,a){const l=(0,o.up)("InfiniteLoading");return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("fieldset",null,[(0,o._)("div",m,[(0,o._)("div",p,[(0,o._)("div",g,[(0,o._)("ul",f,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(this.$store.state.auctionList,(t=>((0,o.wg)(),(0,o.iD)("li",{onClick:e=>a.navigateProduct(t),key:t.auction_Id,id:"_rowLi20220203162708CHK2022020394386781",class:"goods_pay_item _interlockNo20220211200904406814"},[(0,o._)("div",v,[(0,o._)("div",k,[(0,o._)("img",{src:`/product_images/${t.productDTO.product_img_name}.png`,alt:"",width:"90",height:"90"},null,8,w)]),(0,o._)("div",$,[(0,o._)("div",N,[(0,o._)("p",D,(0,h.zw)(t.auction_name),1),(0,o._)("ul",C,[(0,o._)("li",null,[y,(0,o.Uk)((0,h.zw)(t.bid_price)+"원",1)]),(0,o._)("li",I,[O,(0,o.Uk)(" "+(0,h.zw)(t.productDTO.p_reg_date),1)])])]),(0,o._)("div",P,(0,h.zw)(t.bid_status)+" ("+(0,h.zw)(a.updateDeadlineDate(t.deadline_date))+" 경매 종료)",1),(0,o._)("p",S,(0,h.zw)(t.productDTO.p_explanation),1)])]),(0,o._)("div",M,[(0,o._)("div",T,[(0,o._)("span",E,(0,h.zw)(t.f_farm_name),1),(0,o._)("span",L,(0,h.zw)(t.f_phonenum),1),z,K])])],8,b)))),128))])])])])]),(0,o._)("div",null,[(0,o._)("button",{class:"more-data",onClick:e[0]||(e[0]=t=>a.moreProduct())},"더보기")]),(0,o._)("p",U,(0,h.zw)(i.now),1),(0,o.Wm)(l,{comments:t.auction,onInfinite:e[1]||(e[1]=t=>a.moreProduct())},null,8,["comments"])])}var H=s(4568),J=(s(6265),{components:{Like:H.Z},data(){return{limit:0,infiniteId:+new Date,now:0}},mounted(){this.updateNow(),setInterval(this.updateNow.bind(this),1e3)},created(){this.connect()},methods:{updateNow(){this.now=Math.round(Date.now()/1e3)},updateDeadlineDate(t){let e=new Date;e.setFullYear(Number(t.substr(0,4))),e.setMonth(Number(t.substr(5,2))-1),e.setDate(Number(t.substr(8,2))),e.setHours(Number(t.substr(11,2))),e.setMinutes(Number(t.substr(14,2))),e.setSeconds(Number(t.substr(17,2)));let s=Math.round(e.getTime()/1e3)-this.now;if(s<0)return"";let o=Math.floor(s/86400),n=Math.floor(s%86400),i=Math.floor(n/3600),a=Math.floor(n%3600/60),l=Math.floor(n%3600%60);return`${o}일 ${i}시 ${a}분 ${l}초 후`},navigateProduct(t){console.log("navigateProduct"),console.log(t),console.log(t.bid_num),this.$router.push({name:"auction_detail",params:{id:t.auction_Id,auction:JSON.stringify(t)}})},navigategoback(){console.log("!!"),this.$router.go(-1)},moreProduct(t){console.log("state",t),this.send(),then((({data:e})=>{e.hits.length?(this.page+=1,this.list.push(...e.hits),t.loaded()):t.complete()}))},send(){console.log("limit:"+this.$store.state.limit),console.log(this.stompClient),console.log(this.stompClient.connected),this.stompClient&&this.stompClient.connected&&(console.log(this.$store.state.limit),console.log(this.$store.state.config.headers.TOKEN),this.stompClient.send("/receive_limit/"+this.$store.state.config.headers.TOKEN,this.$store.state.limit,{}),this.$store.commit("UP_LIMIT",4),console.log("st",this.$store.state),console.log(this.$store.state.auctionList))},connect(){const t="/socket";let e=new(c())(t);this.stompClient=l().over(e),console.log(`소켓 연결을 시도합니다. 서버 주소: ${t}`),console.log(this.$store.state.config.headers);let s={TOKEN:this.$store.state.config.headers.TOKEN};console.log(s),this.stompClient.connect(s,(t=>{this.connected=!0,console.log("소켓 연결 성공",t),this.stompClient.subscribe("/send_auction_data/"+this.$store.state.config.headers.TOKEN,(t=>{const e=JSON.parse(t.body);console.log(e[0]),console.log("deadline: "+e[0].deadline_date),console.log("deadline_date type 확인: "+typeof e[0].deadline_date),console.log(e.length),JSON.parse(t.body);for(let s=0;s<e.length;s++)this.$store.commit("PUSH_AUCTION",e[s]);console.log("this.auctionList"),console.log(this.$store.state.auctionList[0])})),this.stompClient.subscribe("/send_bidding",(t=>{const e=JSON.parse(t.body);console.log(e),void 0!=e&&this.$store.commit("UPDATE_BID_PRICE",e)}))}),(t=>{console.log("소켓 연결 실패",t),this.connected=!1}))}}}),Z=s(89);const W=(0,Z.Z)(J,[["render",A],["__scopeId","data-v-2bd77f26"]]);var R=W,B=s(6337),Y={components:{bottomNav:B["default"],Header:d.Z,AuctionList:R,Slide:u["default"]},data(){return{headerProps:"경매 알림",limit:0,infiniteId:+new Date,now:0}},mounted(){this.updateNow(),setInterval(this.updateNow.bind(this),1e3)},created(){this.connect()},methods:{updateNow(){this.now=Math.round(Date.now()/1e3)},updateDeadlineDate(t){let e=new Date;e.setFullYear(Number(t.substr(0,4))),e.setMonth(Number(t.substr(5,2))-1),e.setDate(Number(t.substr(8,2))),e.setHours(Number(t.substr(11,2))),e.setMinutes(Number(t.substr(14,2))),e.setSeconds(Number(t.substr(17,2)));let s=Math.round(e.getTime()/1e3)-this.now;if(s<0)return"";let o=Math.floor(s/86400),n=Math.floor(s%86400),i=Math.floor(n/3600),a=Math.floor(n%3600/60),l=Math.floor(n%3600%60);return`${o}일 ${i}시 ${a}분 ${l}초 후`},connect(){const t="/socket";let e=new(c())(t);this.stompClient=l().over(e),console.log(`소켓 연결을 시도합니다. 서버 주소: ${t}`),console.log(this.$store.state.config.headers);let s={TOKEN:this.$store.state.config.headers.TOKEN};console.log(s),this.stompClient.connect(s,(t=>{this.connected=!0,console.log("소켓 연결 성공",t),this.stompClient.subscribe("/send_auction_data/"+this.$store.state.config.headers.TOKEN,(t=>{const e=JSON.parse(t.body);console.log(e[0]),console.log("deadline: "+e[0].deadline_date),console.log("deadline_date type 확인: "+typeof e[0].deadline_date),console.log(e.length),JSON.parse(t.body);for(let s=0;s<e.length;s++)this.$store.commit("PUSH_AUCTION",e[s]);console.log("this.auctionList"),console.log(this.$store.state.auctionList[0])})),this.stompClient.subscribe("/send_bidding",(t=>{const e=JSON.parse(t.body);console.log(e),void 0!=e&&this.$store.commit("UPDATE_BID_PRICE",e)}))}),(t=>{console.log("소켓 연결 실패",t),this.connected=!1}))}}};const x=(0,Z.Z)(Y,[["render",i]]);var q=x}}]);
//# sourceMappingURL=842.12c86ccb.js.map