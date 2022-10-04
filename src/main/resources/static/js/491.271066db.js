"use strict";(self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[]).push([[491,896],{7929:function(e,t,s){s.d(t,{Z:function(){return b}});var i=s(3396),o=s(7139);const a=e=>((0,i.dD)("data-v-0063bdd0"),e=e(),(0,i.Cn)(),e),n={class:"main_nav_t_div"},r={class:"main_t_nav"},l={class:"main_t_nav_list"},c=a((()=>(0,i._)("li",{class:"nav__btn"},[(0,i._)("a",{class:"nav__link",href:"alert"},[(0,i._)("i",{class:"fas fa-bell fa-2x","aria-hidden":"true"})])],-1))),u={class:"main_m_li_list"},d={class:"nav_m_link",href:"workout.html"},h={class:"nav__btn"},_=a((()=>(0,i._)("i",{class:"fas fa-chevron-left fa-2x","aria-hidden":"true"},null,-1))),m=[_];function p(e,t,s,a,_,p){return(0,i.wg)(),(0,i.iD)("div",n,[(0,i._)("nav",r,[(0,i._)("ul",l,[c,(0,i._)("li",u,[(0,i._)("p",d,(0,o.zw)(s.headerProps),1)]),(0,i._)("li",h,[(0,i._)("a",{class:"nav__link",onClick:t[0]||(t[0]=t=>e.$router.go(-1))},m)])])])])}var v={props:{headerProps:String}},g=s(89);const f=(0,g.Z)(v,[["render",p],["__scopeId","data-v-0063bdd0"]]);var b=f},2543:function(e,t,s){s.d(t,{Z:function(){return d}});var i=s(3396),o=s(7139);function a(e,t,s,a,n,r){return(0,i.wg)(),(0,i.iD)("div",null,[(0,i._)("i",{onClick:t[0]||(t[0]=e=>r.likeClick()),class:"fa fa-heart like-icon",style:(0,o.j5)([1===n.like.state?{color:"#FFC1AA"}:{color:"lightgrey"}])},null,4)])}var n=s(6265),r=s.n(n),l={data(){return{like:{users:[],count:0,state:0,auction_id:0,consumer_id:0},user:JSON.parse(localStorage.getItem("user"))}},async mounted(){this.like.auction_id=parseInt(this.$route.params.id),this.like.consumer_id=this.user.consumer_id,await r().get(`/api/checkWish/${this.like.auction_id}/${this.like.consumer_id}`,{headers:{TOKEN:this.user.token}}).then((e=>{console.log("1111"+e),console.log("1111"+e.headers),console.log(e.data),this.like.state=e.data})).catch((e=>{console.log(e)}))},methods:{likeClick(){let e={auction_id:this.like.auction_id,consumer_id:this.user.consumer_id};0===this.like.state?r().post("/api/registWish",e,{headers:{TOKEN:this.user.token}}).then((e=>{console.log("1111"+e),console.log("1111"+e.headers),alert("좋아요를 클릭했습니다!")})).catch((e=>{console.log(e)})):1===this.like.state&&r()["delete"](`/api/deleteWish/${this.like.auction_id}/${this.user.consumer_id}`,{headers:{TOKEN:this.user.token}}).then((e=>{console.log("1111"+e),console.log("1111"+e.headers),alert("좋아요를 취소하였습니다!")})).catch((e=>{console.log(e)}))}}},c=s(89);const u=(0,c.Z)(l,[["render",a],["__scopeId","data-v-68f55ab3"]]);var d=u},177:function(e,t,s){s.d(t,{Z:function(){return f}});var i=s(3396),o=s(9374),a=s(1138),n=s(7041),r=s(1477),l=s(2370),c=s(3712),u=s(4870),d=s(1107),h=s(131);const _=(0,d.a)({name:"VProgressCircular",props:{bgColor:String,color:String,indeterminate:[Boolean,String],modelValue:{type:[Number,String],default:0},rotate:{type:[Number,String],default:0},width:{type:[Number,String],default:4},...(0,o.Z)(),...(0,a.Q)({tag:"div"}),...(0,n.x$)()},setup(e,t){let{slots:s}=t;const a=20,d=2*Math.PI*a,_=(0,u.iH)(),{themeClasses:m}=(0,n.ER)(e),{sizeClasses:p,sizeStyles:v}=(0,o.t)(e),{textColorClasses:g,textColorStyles:f}=(0,l.rY)((0,u.Vh)(e,"color")),{textColorClasses:b,textColorStyles:k}=(0,l.rY)((0,u.Vh)(e,"bgColor")),{intersectionRef:w,isIntersecting:y}=(0,r.S)(),{resizeRef:C,contentRect:S}=(0,c.y)(),D=(0,i.Fl)((()=>Math.max(0,Math.min(100,parseFloat(e.modelValue))))),N=(0,i.Fl)((()=>Number(e.width))),$=(0,i.Fl)((()=>v.value?Number(e.size):S.value?S.value.width:Math.max(N.value,32))),I=(0,i.Fl)((()=>a/(1-N.value/$.value)*2)),P=(0,i.Fl)((()=>N.value/$.value*I.value)),O=(0,i.Fl)((()=>(0,h.kb)((100-D.value)/100*d)));return(0,i.m0)((()=>{w.value=_.value,C.value=_.value})),()=>(0,i.Wm)(e.tag,{ref:_,class:["v-progress-circular",{"v-progress-circular--indeterminate":!!e.indeterminate,"v-progress-circular--visible":y.value,"v-progress-circular--disable-shrink":"disable-shrink"===e.indeterminate},m.value,p.value,g.value],style:[v.value,f.value],role:"progressbar","aria-valuemin":"0","aria-valuemax":"100","aria-valuenow":e.indeterminate?void 0:D.value},{default:()=>[(0,i.Wm)("svg",{style:{transform:`rotate(calc(-90deg + ${Number(e.rotate)}deg))`},xmlns:"http://www.w3.org/2000/svg",viewBox:`0 0 ${I.value} ${I.value}`},[(0,i.Wm)("circle",{class:["v-progress-circular__underlay",b.value],style:k.value,fill:"transparent",cx:"50%",cy:"50%",r:a,"stroke-width":P.value,"stroke-dasharray":d,"stroke-dashoffset":0},null),(0,i.Wm)("circle",{class:"v-progress-circular__overlay",fill:"transparent",cx:"50%",cy:"50%",r:a,"stroke-width":P.value,"stroke-dasharray":d,"stroke-dashoffset":O.value},null)]),s.default&&(0,i.Wm)("div",{class:"v-progress-circular__content"},[s.default({value:D.value})])]})}});function m(e,t){return(0,i.wg)(),(0,i.j4)(_,{class:"spinner",indeterminate:"",color:"orange"})}var p=s(89);const v={},g=(0,p.Z)(v,[["render",m],["__scopeId","data-v-2c2c7d18"]]);var f=g},5948:function(e,t,s){s.r(t),s.d(t,{default:function(){return te}});var i=s(3396);const o={class:"auction-contain"},a={class:"inner"},n={class:"buttonContain"};function r(e,t,s,r,l,c){const u=(0,i.up)("Header"),d=(0,i.up)("Slide"),h=(0,i.up)("AuctionList"),_=(0,i.up)("bottom-nav");return(0,i.wg)(),(0,i.iD)("div",o,[(0,i._)("div",a,[(0,i.Wm)(u,{headerProps:l.headerProps},null,8,["headerProps"]),(0,i.Wm)(d,{imgData:l.imgData},null,8,["imgData"]),(0,i._)("div",n,[(0,i._)("button",{onClick:t[0]||(t[0]=e=>c.reload()),class:"reload-button"},"새로고침")]),(0,i.Wm)(h)]),(0,i.Wm)(_,{class:"bottom"})])}var l=s(7929),c=s(880),u=s(7139);const d=e=>((0,i.dD)("data-v-41801bfc"),e=e(),(0,i.Cn)(),e),h={class:"auctionList-contain"},_={class:"white_div"},m={class:"goods_pay_section"},p={class:"goods_groups"},v={ref:"items",class:"goods_group_list"},g=["onClick"],f={class:"goods_item"},b={class:"goods_thumb"},k=["src"],w={class:"goods_info"},y={class:"goods"},C={class:"name"},S={class:"info"},D=d((()=>(0,i._)("span",{class:"blind"},"상품금액",-1))),N={key:0,class:"state _statusName value_color_green _click(nmp.front.order.timeline.home.list.openDeliveryPopup(/o/orderStatus/deliveryTracking/2022020394386781/ORDER_DELIVERY/api)) _stopDefault"},$={key:1,class:"state _statusName value_color_green _click(nmp.front.order.timeline.home.list.openDeliveryPopup(/o/orderStatus/deliveryTracking/2022020394386781/ORDER_DELIVERY/api)) _stopDefault"},I={class:"guide"},P={class:"seller_item"},O={class:"inner"},T={class:"seller"},E={class:"tel"},M={class:"date"},x=d((()=>(0,i._)("br",null,null,-1))),L={key:0,class:"bid-price"},z=d((()=>(0,i._)("br",null,null,-1))),H=d((()=>(0,i._)("br",null,null,-1))),W={style:{display:"none"}};function R(e,t,s,o,a,n){const r=(0,i.up)("Spinner"),l=(0,i.up)("InfiniteLoading");return(0,i.wg)(),(0,i.iD)("div",h,[!0===a.spinnerState?((0,i.wg)(),(0,i.j4)(r,{key:0})):(0,i.kq)("",!0),(0,i._)("fieldset",null,[(0,i._)("div",_,[(0,i._)("div",m,[(0,i._)("div",p,[(0,i._)("ul",v,[((0,i.wg)(!0),(0,i.iD)(i.HY,null,(0,i.Ko)(this.$store.state.auctionList,((e,t)=>((0,i.wg)(),(0,i.iD)("li",{onClick:t=>n.navigateAuction(e),key:t,id:"_rowLi20220203162708CHK2022020394386781",class:"goods_pay_item _interlockNo20220211200904406814"},[(0,i._)("div",f,[(0,i._)("div",b,[(0,i._)("img",{src:`/product_images/${e.productDTO.product_img_name}.png`,alt:"",width:"90",height:"90"},null,8,k)]),(0,i._)("div",w,[(0,i._)("div",y,[(0,i._)("p",C,(0,u.zw)(e.auction_name),1),(0,i._)("ul",S,[(0,i._)("li",null,[D,(0,i.Uk)((0,u.zw)(e.bid_price.toLocaleString())+"원",1)])])]),1===e.bid_status?((0,i.wg)(),(0,i.iD)("p",N,(0,u.zw)(n.updateDeadlineDate(e.deadline_date))+" 경매 종료",1)):(0,i.kq)("",!0),0===e.bid_status?((0,i.wg)(),(0,i.iD)("p",$," 최종 낙찰가 "+(0,u.zw)(e.a_max_price.toLocaleString())+"원",1)):(0,i.kq)("",!0),(0,i._)("p",I,(0,u.zw)(e.productDTO.p_explanation),1)])]),(0,i._)("div",P,[(0,i._)("div",O,[(0,i._)("span",T,(0,u.zw)(e.f_farm_name),1),(0,i._)("span",E,"Tel : 0"+(0,u.zw)(e.f_phonenum.toString().replace(/[^0-9]/g,"").replace(/(^02|^0505|^1[0-9]{1}|^0[0-9]{4})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--","-")),1),(0,i._)("span",M,(0,u.zw)(e.productDTO.p_reg_date),1),x,e.isMyConsumerAuction?((0,i.wg)(),(0,i.iD)("b",L,"입찰했습니다.")):(0,i.kq)("",!0),z,H])])],8,g)))),128))],512)])])])]),(0,i._)("p",W,(0,u.zw)(a.now),1),(0,i.Wm)(l,{comments:e.auction,onInfinite:t[0]||(t[0]=e=>n.moreProduct())},null,8,["comments"])])}var Z=s(177),A=s(2543),F=s(3266),K=s.n(F),V=s(4029),Y=s.n(V),J=s(4806),U=s.n(J),q={components:{Like:A.Z,Spinner:Z.Z},data(){return{user:JSON.parse(localStorage.getItem("user")),spinnerState:!1,limit:0,now:0,bidPrice:[]}},mounted(){console.log(this.bidPrice),window.addEventListener("scroll",U().throttle((()=>{this.infiniteScroll()}),500),!0),this.updateNow(),setInterval(this.updateNow.bind(this),1e3)},created(){this.connect()},methods:{infiniteScroll(){if(console.log(this.$route.path),"/auction"===this.$route.path){const{innerHeight:e}=window;Math.round(this.$refs.items.scrollTop+window.innerHeight)>=this.$refs.items.scrollHeight&&(console.log("스크롤 실행"),this.moreProduct())}console.log(innerHeight)},updateNow(){this.now=Math.round(Date.now()/1e3)},updateDeadlineDate(e){let t=new Date;t.setFullYear(Number(e.substr(0,4))),t.setMonth(Number(e.substr(5,2))-1),t.setDate(Number(e.substr(8,2))),t.setHours(Number(e.substr(11,2))),t.setMinutes(Number(e.substr(14,2))),t.setSeconds(Number(e.substr(17,2)));let s=Math.round(t.getTime()/1e3)-this.now;if(s<0)return"";let i=Math.floor(s/86400),o=Math.floor(s%86400),a=Math.floor(o/3600),n=Math.floor(o%3600/60),r=Math.floor(o%3600%60),l="";return 0!=i&&(l+=i+"일 "),0!=a&&(l+=a+"시간 "),0!=n&&(l+=n+"분 "),0!=r&&(l+=r+"초 후"),l},navigateAuction(e){this.$router.push({name:"auction_detail",params:{id:e.auction_Id,auction:JSON.stringify(e)}})},navigategoback(){console.log("!!"),this.$router.go(-1)},moreProduct(){this.spinnerState=!0,this.send()},send(){this.stompClient&&this.stompClient.connected&&this.stompClient.send("/receive_limit/"+this.$store.state.config.headers.TOKEN,this.$store.state.limit,{})},connect(){const e="/socket";let t=new(Y())(e);this.stompClient=K().over(t),console.log(`소켓 연결을 시도합니다. 서버 주소: ${e}`);let s={TOKEN:this.$store.state.config.headers.TOKEN};this.stompClient.connect(s,(e=>{this.connected=!0,console.log("소켓 연결 성공",e),this.stompClient.subscribe("/send_auction_data/"+this.$store.state.config.headers.TOKEN,(e=>{this.spinnerState=!1;const t=JSON.parse(e.body);if(console.log("response_data.length: "+t.length),t.length){for(let e=0;e<t.length;e++)this.$store.commit("PUSH_AUCTION",t[e]),this.$store.state.auctionList[e].consumer_id===this.user.consumer_id?(this.$store.state.auctionList[e].isMyConsumerAuction=!0,this.bidPrice.push(1)):this.bidPrice.push(0);console.log(this.bidPrice),this.$store.commit("UP_LIMIT",4)}})),this.moreProduct(),this.stompClient.subscribe("/send_bidding",(e=>{const t=JSON.parse(e.body);console.log(t),void 0!=t&&this.$store.commit("UPDATE_BID_PRICE",t,this.user.consumer_id==t.consumer_id)}))}),(e=>{console.log("소켓 연결 실패",e),this.connected=!1}))}}},j=s(89);const B=(0,j.Z)(q,[["render",R],["__scopeId","data-v-41801bfc"]]);var Q=B,G=s(3454),X={components:{bottomNav:G["default"],Header:l.Z,AuctionList:Q,Slide:c["default"]},data(){return{headerProps:"경매 알림",imgData:void 0}},methods:{reload(){location.reload()}}};const ee=(0,j.Z)(X,[["render",r],["__scopeId","data-v-25c470c5"]]);var te=ee},1477:function(e,t,s){s.d(t,{S:function(){return n}});var i=s(4870),o=s(3396),a=s(2385);function n(e){const t=(0,i.iH)(),s=(0,i.iH)(!1);if(a.cu){const i=new IntersectionObserver((t=>{null==e||e(t,i),s.value=!!t.find((e=>e.isIntersecting))}));(0,o.Jd)((()=>{i.disconnect()})),(0,o.YP)(t,((e,t)=>{t&&(i.unobserve(t),s.value=!1),e&&i.observe(e)}),{flush:"post"})}return{intersectionRef:t,isIntersecting:s}}}}]);
//# sourceMappingURL=491.271066db.js.map