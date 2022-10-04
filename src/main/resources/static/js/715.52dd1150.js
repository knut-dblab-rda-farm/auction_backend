"use strict";(self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[]).push([[715,896],{7929:function(e,t,s){s.d(t,{Z:function(){return b}});var a=s(3396),i=s(7139);const o=e=>((0,a.dD)("data-v-0063bdd0"),e=e(),(0,a.Cn)(),e),n={class:"main_nav_t_div"},r={class:"main_t_nav"},l={class:"main_t_nav_list"},c=o((()=>(0,a._)("li",{class:"nav__btn"},[(0,a._)("a",{class:"nav__link",href:"alert"},[(0,a._)("i",{class:"fas fa-bell fa-2x","aria-hidden":"true"})])],-1))),u={class:"main_m_li_list"},d={class:"nav_m_link",href:"workout.html"},_={class:"nav__btn"},h=o((()=>(0,a._)("i",{class:"fas fa-chevron-left fa-2x","aria-hidden":"true"},null,-1))),m=[h];function v(e,t,s,o,h,v){return(0,a.wg)(),(0,a.iD)("div",n,[(0,a._)("nav",r,[(0,a._)("ul",l,[c,(0,a._)("li",u,[(0,a._)("p",d,(0,i.zw)(s.headerProps),1)]),(0,a._)("li",_,[(0,a._)("a",{class:"nav__link",onClick:t[0]||(t[0]=t=>e.$router.go(-1))},m)])])])])}var p={props:{headerProps:String}},g=s(89);const f=(0,g.Z)(p,[["render",v],["__scopeId","data-v-0063bdd0"]]);var b=f},2543:function(e,t,s){s.d(t,{Z:function(){return d}});var a=s(3396),i=s(7139);function o(e,t,s,o,n,r){return(0,a.wg)(),(0,a.iD)("div",null,[(0,a._)("i",{onClick:t[0]||(t[0]=e=>r.likeClick()),class:"fa fa-heart like-icon",style:(0,i.j5)([1===n.like.state?{color:"#FFC1AA"}:{color:"lightgrey"}])},null,4)])}var n=s(6265),r=s.n(n),l={data(){return{like:{users:[],count:0,state:0,auction_id:0,consumer_id:0},user:JSON.parse(localStorage.getItem("user"))}},async mounted(){this.like.auction_id=parseInt(this.$route.params.id),this.like.consumer_id=this.user.consumer_id,await r().get(`/api/checkWish/${this.like.auction_id}/${this.like.consumer_id}`,{headers:{TOKEN:this.user.token}}).then((e=>{console.log("1111"+e),console.log("1111"+e.headers),console.log(e.data),this.like.state=e.data})).catch((e=>{console.log(e)}))},methods:{likeClick(){let e={auction_id:this.like.auction_id,consumer_id:this.user.consumer_id};0===this.like.state?r().post("/api/registWish",e,{headers:{TOKEN:this.user.token}}).then((e=>{console.log("1111"+e),console.log("1111"+e.headers),alert("좋아요를 클릭했습니다!")})).catch((e=>{console.log(e)})):1===this.like.state&&r()["delete"](`/api/deleteWish/${this.like.auction_id}/${this.user.consumer_id}`,{headers:{TOKEN:this.user.token}}).then((e=>{console.log("1111"+e),console.log("1111"+e.headers),alert("좋아요를 취소하였습니다!")})).catch((e=>{console.log(e)}))}}},c=s(89);const u=(0,c.Z)(l,[["render",o],["__scopeId","data-v-68f55ab3"]]);var d=u},177:function(e,t,s){s.d(t,{Z:function(){return f}});var a=s(3396),i=s(9374),o=s(1138),n=s(7041),r=s(1477),l=s(2370),c=s(3712),u=s(4870),d=s(1107),_=s(131);const h=(0,d.a)({name:"VProgressCircular",props:{bgColor:String,color:String,indeterminate:[Boolean,String],modelValue:{type:[Number,String],default:0},rotate:{type:[Number,String],default:0},width:{type:[Number,String],default:4},...(0,i.Z)(),...(0,o.Q)({tag:"div"}),...(0,n.x$)()},setup(e,t){let{slots:s}=t;const o=20,d=2*Math.PI*o,h=(0,u.iH)(),{themeClasses:m}=(0,n.ER)(e),{sizeClasses:v,sizeStyles:p}=(0,i.t)(e),{textColorClasses:g,textColorStyles:f}=(0,l.rY)((0,u.Vh)(e,"color")),{textColorClasses:b,textColorStyles:k}=(0,l.rY)((0,u.Vh)(e,"bgColor")),{intersectionRef:w,isIntersecting:y}=(0,r.S)(),{resizeRef:C,contentRect:D}=(0,c.y)(),S=(0,a.Fl)((()=>Math.max(0,Math.min(100,parseFloat(e.modelValue))))),N=(0,a.Fl)((()=>Number(e.width))),$=(0,a.Fl)((()=>p.value?Number(e.size):D.value?D.value.width:Math.max(N.value,32))),I=(0,a.Fl)((()=>o/(1-N.value/$.value)*2)),P=(0,a.Fl)((()=>N.value/$.value*I.value)),O=(0,a.Fl)((()=>(0,_.kb)((100-S.value)/100*d)));return(0,a.m0)((()=>{w.value=h.value,C.value=h.value})),()=>(0,a.Wm)(e.tag,{ref:h,class:["v-progress-circular",{"v-progress-circular--indeterminate":!!e.indeterminate,"v-progress-circular--visible":y.value,"v-progress-circular--disable-shrink":"disable-shrink"===e.indeterminate},m.value,v.value,g.value],style:[p.value,f.value],role:"progressbar","aria-valuemin":"0","aria-valuemax":"100","aria-valuenow":e.indeterminate?void 0:S.value},{default:()=>[(0,a.Wm)("svg",{style:{transform:`rotate(calc(-90deg + ${Number(e.rotate)}deg))`},xmlns:"http://www.w3.org/2000/svg",viewBox:`0 0 ${I.value} ${I.value}`},[(0,a.Wm)("circle",{class:["v-progress-circular__underlay",b.value],style:k.value,fill:"transparent",cx:"50%",cy:"50%",r:o,"stroke-width":P.value,"stroke-dasharray":d,"stroke-dashoffset":0},null),(0,a.Wm)("circle",{class:"v-progress-circular__overlay",fill:"transparent",cx:"50%",cy:"50%",r:o,"stroke-width":P.value,"stroke-dasharray":d,"stroke-dashoffset":O.value},null)]),s.default&&(0,a.Wm)("div",{class:"v-progress-circular__content"},[s.default({value:S.value})])]})}});function m(e,t){return(0,a.wg)(),(0,a.j4)(h,{class:"spinner",indeterminate:"",color:"orange"})}var v=s(89);const p={},g=(0,v.Z)(p,[["render",m],["__scopeId","data-v-2c2c7d18"]]);var f=g},8774:function(e,t,s){s.r(t),s.d(t,{default:function(){return X}});var a=s(3396);const i={class:"auction-contain"},o={class:"inner"},n={class:"buttonContain"};function r(e,t,s,r,l,c){const u=(0,a.up)("Header"),d=(0,a.up)("Slide"),_=(0,a.up)("AuctionList"),h=(0,a.up)("bottom-nav");return(0,a.wg)(),(0,a.iD)("div",i,[(0,a._)("div",o,[(0,a.Wm)(u,{headerProps:l.headerProps},null,8,["headerProps"]),(0,a.Wm)(d,{imgData:l.imgData},null,8,["imgData"]),(0,a._)("div",n,[(0,a._)("button",{onClick:t[0]||(t[0]=e=>c.reload()),class:"reload-button"},"새로고침")]),(0,a.Wm)(_)]),(0,a.Wm)(h,{class:"bottom"})])}var l=s(7929),c=s(880),u=s(7139);const d=e=>((0,a.dD)("data-v-8c4145e6"),e=e(),(0,a.Cn)(),e),_={class:"auctionList-contain"},h={class:"white_div"},m={class:"goods_pay_section"},v={class:"goods_groups"},p={ref:"items",class:"goods_group_list"},g=["onClick"],f={class:"goods_item"},b={class:"goods_thumb"},k=["src"],w={class:"goods_info"},y={class:"goods"},C={class:"name"},D={class:"info"},S=d((()=>(0,a._)("span",{class:"blind"},"상품금액",-1))),N={key:0,class:"state _statusName value_color_green _click(nmp.front.order.timeline.home.list.openDeliveryPopup(/o/orderStatus/deliveryTracking/2022020394386781/ORDER_DELIVERY/api)) _stopDefault"},$={key:1,class:"state _statusName value_color_green _click(nmp.front.order.timeline.home.list.openDeliveryPopup(/o/orderStatus/deliveryTracking/2022020394386781/ORDER_DELIVERY/api)) _stopDefault"},I={class:"guide"},P={class:"seller_item"},O={class:"inner"},T={class:"seller"},E={class:"tel"},x={class:"date"},M=d((()=>(0,a._)("br",null,null,-1))),z=d((()=>(0,a._)("br",null,null,-1))),H={style:{display:"none"}};function L(e,t,s,i,o,n){const r=(0,a.up)("Spinner"),l=(0,a.up)("InfiniteLoading");return(0,a.wg)(),(0,a.iD)("div",_,[!0===o.spinnerState?((0,a.wg)(),(0,a.j4)(r,{key:0})):(0,a.kq)("",!0),(0,a._)("fieldset",null,[(0,a._)("div",h,[(0,a._)("div",m,[(0,a._)("div",v,[(0,a._)("ul",p,[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.$store.state.auctionList,(e=>((0,a.wg)(),(0,a.iD)("li",{onClick:t=>n.navigateAuction(e),key:e.auction_Id,id:"_rowLi20220203162708CHK2022020394386781",class:"goods_pay_item _interlockNo20220211200904406814"},[(0,a._)("div",f,[(0,a._)("div",b,[(0,a._)("img",{src:`/product_images/${e.productDTO.product_img_name}.png`,alt:"",width:"90",height:"90"},null,8,k)]),(0,a._)("div",w,[(0,a._)("div",y,[(0,a._)("p",C,(0,u.zw)(e.auction_name),1),(0,a._)("ul",D,[(0,a._)("li",null,[S,(0,a.Uk)((0,u.zw)(e.bid_price.toLocaleString())+"원",1)])])]),1===e.bid_status?((0,a.wg)(),(0,a.iD)("p",N,(0,u.zw)(n.updateDeadlineDate(e.deadline_date))+" 경매 종료",1)):(0,a.kq)("",!0),0===e.bid_status?((0,a.wg)(),(0,a.iD)("p",$," 최종 낙찰가 "+(0,u.zw)(e.a_max_price.toLocaleString())+"원",1)):(0,a.kq)("",!0),(0,a._)("p",I,(0,u.zw)(e.productDTO.p_explanation),1)])]),(0,a._)("div",P,[(0,a._)("div",O,[(0,a._)("span",T,(0,u.zw)(e.f_farm_name),1),(0,a._)("span",E,"Tel : 0"+(0,u.zw)(e.f_phonenum.toString().replace(/[^0-9]/g,"").replace(/(^02|^0505|^1[0-9]{1}|^0[0-9]{4})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--","-")),1),(0,a._)("span",x,(0,u.zw)(e.productDTO.p_reg_date),1),M,z])])],8,g)))),128))],512)])])])]),(0,a._)("p",H,(0,u.zw)(o.now),1),(0,a.Wm)(l,{comments:e.auction,onInfinite:t[0]||(t[0]=e=>n.moreProduct())},null,8,["comments"])])}var W=s(177),R=s(2543),Z=s(3266),F=s.n(Z),K=s(4029),A=s.n(K),V=s(4806),Y=s.n(V),J={components:{Like:R.Z,Spinner:W.Z},data(){return{spinnerState:!1,limit:0,now:0}},mounted(){window.addEventListener("scroll",Y().throttle((()=>{this.infiniteScroll()}),500),!0),this.updateNow(),setInterval(this.updateNow.bind(this),1e3)},created(){this.connect()},methods:{infiniteScroll(){if(console.log(this.$route.path),"/auction"===this.$route.path){const{innerHeight:e}=window;Math.round(this.$refs.items.scrollTop+window.innerHeight)>=this.$refs.items.scrollHeight&&(console.log("스크롤 실행"),this.moreProduct())}console.log(innerHeight)},updateNow(){this.now=Math.round(Date.now()/1e3)},updateDeadlineDate(e){let t=new Date;t.setFullYear(Number(e.substr(0,4))),t.setMonth(Number(e.substr(5,2))-1),t.setDate(Number(e.substr(8,2))),t.setHours(Number(e.substr(11,2))),t.setMinutes(Number(e.substr(14,2))),t.setSeconds(Number(e.substr(17,2)));let s=Math.round(t.getTime()/1e3)-this.now;if(s<0)return"";let a=Math.floor(s/86400),i=Math.floor(s%86400),o=Math.floor(i/3600),n=Math.floor(i%3600/60),r=Math.floor(i%3600%60),l="";return 0!=a&&(l+=a+"일 "),0!=o&&(l+=o+"시간 "),0!=n&&(l+=n+"분 "),0!=r&&(l+=r+"초 후"),l},navigateAuction(e){this.$router.push({name:"auction_detail",params:{id:e.auction_Id,auction:JSON.stringify(e)}})},navigategoback(){console.log("!!"),this.$router.go(-1)},moreProduct(){this.spinnerState=!0,this.send()},send(){this.stompClient&&this.stompClient.connected&&this.stompClient.send("/receive_limit/"+this.$store.state.config.headers.TOKEN,this.$store.state.limit,{})},connect(){const e="/socket";let t=new(A())(e);this.stompClient=F().over(t),console.log(`소켓 연결을 시도합니다. 서버 주소: ${e}`);let s={TOKEN:this.$store.state.config.headers.TOKEN};this.stompClient.connect(s,(e=>{this.connected=!0,console.log("소켓 연결 성공",e),this.stompClient.subscribe("/send_auction_data/"+this.$store.state.config.headers.TOKEN,(e=>{this.spinnerState=!1;const t=JSON.parse(e.body);if(console.log("response_data.length: "+t.length),t.length){for(let e=0;e<t.length;e++)this.$store.commit("PUSH_AUCTION",t[e]);this.$store.commit("UP_LIMIT",4)}})),this.moreProduct(),this.stompClient.subscribe("/send_bidding",(e=>{const t=JSON.parse(e.body);console.log(t),void 0!=t&&this.$store.commit("UPDATE_BID_PRICE",t)}))}),(e=>{console.log("소켓 연결 실패",e),this.connected=!1}))}}},U=s(89);const j=(0,U.Z)(J,[["render",L],["__scopeId","data-v-8c4145e6"]]);var q=j,B=s(3454),Q={components:{bottomNav:B["default"],Header:l.Z,AuctionList:q,Slide:c["default"]},data(){return{headerProps:"경매 알림",imgData:void 0}},methods:{reload(){location.reload()}}};const G=(0,U.Z)(Q,[["render",r],["__scopeId","data-v-25c470c5"]]);var X=G},1477:function(e,t,s){s.d(t,{S:function(){return n}});var a=s(4870),i=s(3396),o=s(2385);function n(e){const t=(0,a.iH)(),s=(0,a.iH)(!1);if(o.cu){const a=new IntersectionObserver((t=>{null==e||e(t,a),s.value=!!t.find((e=>e.isIntersecting))}));(0,i.Jd)((()=>{a.disconnect()})),(0,i.YP)(t,((e,t)=>{t&&(a.unobserve(t),s.value=!1),e&&a.observe(e)}),{flush:"post"})}return{intersectionRef:t,isIntersecting:s}}}}]);
//# sourceMappingURL=715.52dd1150.js.map