"use strict";(self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[]).push([[898,896],{5140:function(t,e,i){i.d(e,{Z:function(){return f}});var o=i(3396),s=i(7139),n=i(3289);const a=t=>((0,o.dD)("data-v-356d296a"),t=t(),(0,o.Cn)(),t),c={class:"header"},l={class:"navLi"},u=(0,o.Uk)("mdi-home"),r={class:"navLi"},d={class:"navLi"},_=a((()=>(0,o._)("i",{class:"fas fa-chevron-left fa-2x","aria-hidden":"true"},null,-1))),h=[_];function m(t,e,i,a,_,m){const p=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)("header",c,[(0,o._)("nav",null,[(0,o._)("li",l,[(0,o.Wm)(p,{to:"/"},{default:(0,o.w5)((()=>[(0,o.Wm)(n.t,null,{default:(0,o.w5)((()=>[u])),_:1})])),_:1})]),(0,o._)("li",r,(0,s.zw)(i.headerProps),1),(0,o._)("li",d,[(0,o._)("p",{class:"nav__link",onClick:e[0]||(e[0]=t=>m.goBack())},h)])])])}var p={props:{headerProps:String},methods:{goBack(){this.$router.go(-1)}}},g=i(89);const k=(0,g.Z)(p,[["render",m],["__scopeId","data-v-356d296a"]]);var f=k},2543:function(t,e,i){i.d(e,{Z:function(){return d}});var o=i(3396),s=i(7139);function n(t,e,i,n,a,c){return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("i",{onClick:e[0]||(e[0]=t=>c.likeClick()),class:"fa fa-heart like-icon",style:(0,s.j5)([1===a.like.state?{color:"#FFC1AA"}:{color:"lightgrey"}])},null,4)])}var a=i(6265),c=i.n(a),l={data(){return{like:{users:[],count:0,state:0,auction_id:0,consumer_id:0},user:JSON.parse(localStorage.getItem("user"))}},async mounted(){this.like.auction_id=parseInt(this.$route.params.id),this.like.consumer_id=this.user.consumer_id,await c().get(`/api/checkWish/${this.like.auction_id}/${this.like.consumer_id}`,{headers:{TOKEN:this.user.token}}).then((t=>{console.log("1111"+t),console.log("1111"+t.headers),console.log(t.data),this.like.state=t.data})).catch((t=>{console.log(t)}))},methods:{likeClick(){let t={auction_id:this.like.auction_id,consumer_id:this.user.consumer_id};0===this.like.state?c().post("/api/registWish",t,{headers:{TOKEN:this.user.token}}).then((t=>{console.log("1111"+t),console.log("1111"+t.headers),alert("좋아요를 클릭했습니다!")})).catch((t=>{console.log(t)})):1===this.like.state&&c()["delete"](`/api/deleteWish/${this.like.auction_id}/${this.user.consumer_id}`,{headers:{TOKEN:this.user.token}}).then((t=>{console.log("1111"+t),console.log("1111"+t.headers),alert("좋아요를 취소하였습니다!")})).catch((t=>{console.log(t)}))}}},u=i(89);const r=(0,u.Z)(l,[["render",n],["__scopeId","data-v-68f55ab3"]]);var d=r},7530:function(t,e,i){i.r(e),i.d(e,{default:function(){return _t}});var o=i(3396),s=i(7139),n=i(9242),a=i(2539);const c=t=>((0,o.dD)("data-v-731c5b7b"),t=t(),(0,o.Cn)(),t),l={class:"auction-info"},u=c((()=>(0,o._)("h2",{class:"profileh2"},"상세 정보",-1))),r={class:"profileh2"},d={class:"h3_middle"},_={class:"aside_area aside_popular"},h=c((()=>(0,o._)("h3",{class:"h_popular"},null,-1))),m={class:"tbl_home"},p={class:"detail-contain"},g={class:""},k={class:""},f=c((()=>(0,o._)("th",null,"시작가",-1))),b={key:0,class:""},w=c((()=>(0,o._)("th",null,"최대 입찰가",-1))),D={key:1,class:""},v=c((()=>(0,o._)("th",null,"현재가",-1))),T={key:2,class:""},C=c((()=>(0,o._)("th",null,"최종 낙찰가",-1))),y={class:""},A=c((()=>(0,o._)("th",null,"낙과 일자",-1))),S={class:""},O=c((()=>(0,o._)("th",null,"사이즈",-1))),x={class:""},I=c((()=>(0,o._)("th",null,"상태",-1))),N={class:"state _statusName value_color_green _click(nmp.front.order.timeline.home.list.openDeliveryPopup(/o/orderStatus/deliveryTracking/2022020394386781/ORDER_DELIVERY/api)) _stopDefault"},$={key:0},M={key:0},z=(0,o.Uk)("삭제하기"),P=(0,o.Uk)(" 수정하기 "),E={key:1},L={class:"stateBtn"},U=(0,o.Uk)("입찰하기"),W=(0,o.Uk)("입찰 완료"),q=c((()=>(0,o._)("h2",{class:"profileh2"},"생산자 정보",-1))),F={class:"goods_group_list"},H={id:"_rowLi20220213173042CHK2022021381488661",class:"goods_pay_item"},J={class:"goods_item"},R={class:"goods_thumb"},Z=["src"],B={class:"goods_info"},K={class:"guide2"},j={class:"guide2"},V={class:"guide2"},Y={class:"guide2"},G={action:"farm_intro",class:"login-form"},Q=c((()=>(0,o._)("input",{class:"login-form__btn",type:"submit",value:"농가 상세 소개"},null,-1)));function X(t,e,i,c,X,tt){const et=(0,o.up)("Header"),it=(0,o.up)("Slide"),ot=(0,o.up)("Like"),st=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)("div",null,[(0,o.Wm)(et,{headerProps:t.headerProps},null,8,["headerProps"]),(0,o.Wm)(it,{imgData:t.imgData,class:"auction-image"},null,8,["imgData"]),(0,o._)("fieldset",l,[u,(0,o._)("h2",r,(0,s.zw)(t.auction.auction_name),1),(0,o._)("h3",d,(0,s.zw)(t.auction.productDTO.p_explanation),1),(0,o._)("div",_,[h,(0,o._)("table",m,[(0,o._)("tbody",p,[(0,o._)("tr",g,[(0,o._)("th",null,(0,s.zw)(t.auction.productDTO.product),1),(0,o._)("td",null,(0,s.zw)(t.auction.productDTO.product_kg)+"kg",1)]),(0,o._)("tr",k,[f,(0,o._)("td",null,(0,s.zw)(t.auction.a_starting_price.toLocaleString())+"원",1)]),1===t.auction.bid_status?((0,o.wg)(),(0,o.iD)("tr",b,[w,(0,o._)("td",null,(0,s.zw)(t.auction.a_max_price.toLocaleString())+"원",1)])):(0,o.kq)("",!0),1===t.auction.bid_status?((0,o.wg)(),(0,o.iD)("tr",D,[v,(0,o._)("td",null,(0,s.zw)(t.auction.bid_price.toLocaleString())+"원",1)])):(0,o.kq)("",!0),0===t.auction.bid_status?((0,o.wg)(),(0,o.iD)("tr",T,[C,(0,o._)("td",null,(0,s.zw)(t.auction.a_max_price.toLocaleString())+"원",1)])):(0,o.kq)("",!0),(0,o._)("tr",y,[A,(0,o._)("td",null,(0,s.zw)(t.auction.productDTO.p_drop_date.slice(0,19)),1)]),(0,o._)("tr",S,[O,(0,o._)("td",null,(0,s.zw)(t.auction.productDTO.size),1)]),(0,o._)("tr",x,[I,(0,o._)("td",null,(0,s.zw)(t.auction.productDTO.p_status),1)])])])]),(0,o._)("div",null,[(0,o._)("p",N,(0,s.zw)(tt.updateDeadlineDate(t.auction.deadline_date))+" 경매 종료",1)]),1===t.auction.bid_status?((0,o.wg)(),(0,o.iD)("div",$,[this.auction.farm_id==t.user.farm_id?((0,o.wg)(),(0,o.iD)("div",M,[(0,o.Wm)(a.T,{class:"delete-button",block:"",onClick:e[0]||(e[0]=t=>tt.deleteAuction())},{default:(0,o.w5)((()=>[z])),_:1}),(0,o.Wm)(a.T,{class:"edit-button",block:"",onClick:e[1]||(e[1]=e=>t.$router.push({name:"auction_reg_patch",params:{id:this.auction.auction_Id}}))},{default:(0,o.w5)((()=>[P])),_:1})])):(0,o.kq)("",!0),t.user.consumer_id?((0,o.wg)(),(0,o.iD)("div",E,[t.isMyConsumerAuction?(0,o.kq)("",!0):(0,o.wy)(((0,o.wg)(),(0,o.iD)("input",{key:0,type:"number",placeholder:"입찰할 가격을 입력(숫자만)해주세요!",id:"bid_price","onUpdate:modelValue":e[2]||(e[2]=e=>t.bid_price=e)},null,512)),[[n.nr,t.bid_price]]),(0,o._)("div",L,[((0,o.wg)(),(0,o.j4)(ot,{class:"like-button",key:t.likeState,onClick:e[3]||(e[3]=t=>tt.likeStateFunc())})),t.isMyConsumerAuction?(0,o.kq)("",!0):((0,o.wg)(),(0,o.j4)(a.T,{key:0,class:"bid-button",block:"",onClick:e[4]||(e[4]=t=>tt.bid())},{default:(0,o.w5)((()=>[U])),_:1})),t.isMyConsumerAuction?((0,o.wg)(),(0,o.j4)(a.T,{key:1,class:"bid-button",block:""},{default:(0,o.w5)((()=>[W])),_:1})):(0,o.kq)("",!0)])])):(0,o.kq)("",!0)])):(0,o.kq)("",!0)]),q,(0,o._)("div",{class:"goods_group",onClick:e[5]||(e[5]=e=>t.$router.push("/auction_detail/farm_intro"))},[(0,o._)("ul",F,[(0,o._)("li",H,[(0,o._)("div",J,[(0,o._)("p",R,[(0,o._)("img",{src:null==t.user.f_profile_img||""==t.user.f_profile_img?"/member_profile_images/base_image.png":`/member_profile_images/${t.auction.f_profile_img}.png`,alt:"",width:"90",height:"90"},null,8,Z)]),(0,o._)("div",B,[(0,o._)("p",K," 농가명 : "+(0,s.zw)(t.auction.f_farm_name),1),(0,o._)("p",j," 대표자 : "+(0,s.zw)(t.auction.f_name),1),(0,o._)("p",V," 연락처 : "+(0,s.zw)(t.auction.f_phonenum),1),(0,o._)("p",Y," 농가 주소 : "+(0,s.zw)(t.auction.f_location),1)])])])])]),(0,o._)("form",G,[(0,o.Wm)(st,{to:`/auction_detail/farm_intro/${t.auction.farm_id}`},{default:(0,o.w5)((()=>[Q])),_:1},8,["to"])])])}var tt=i(3266),et=i.n(tt),it=i(4029),ot=i.n(it),st=i(5140),nt=i(2543),at=i(880),ct=i(6265),lt=i.n(ct),ut={name:"App",components:{Header:st.Z,Slide:at["default"],Like:nt.Z},data:()=>({user:JSON.parse(localStorage.getItem("user")),headerProps:"경매 상세",auction:null,bid_price:null,consumer_id:null,testConsumerId:12,userState:!1,likeState:0,isMaxPrice:0,bidAlertText:"입찰하시겠습니까?",consumerBidDeletedAuctionText:"경매가 삭제되어 입찰 불가합니다.",deleteAuctionConsumerExistText:"이미 입찰이 진행된 경매는 삭제 불가합니다!",deleteAuctionFourHourText:"현재시간으로부터 마감시간까지 4시간 이내 경매는 삭제 불가합니다!",deleteAuctionConfirmText:"해당 경매를 삭제하시겠습니까?",deleteAuctionAlertText:"경매가 삭제되었습니다.",FOUR_HOUR:144e5,imgData:[],now:0,isMyConsumerAuction:!1}),created(){this.connect(),console.log("arr",this.$route.params.auction),void 0==this.$route.params.auction?this.auction=JSON.parse(localStorage.getItem("auction")):(this.auction=JSON.parse(this.$route.params.auction),localStorage.setItem("auction",JSON.stringify(this.auction))),console.log("경매 정보",this.auction);let t=this.auction.productDTO.product_img_name[this.auction.productDTO.product_img_name.length-1];console.log("img",t);for(let e=0;e<t;e++)this.imgData.push(this.auction.productDTO.product_img_name.replace("(0)",`(${e})`));this.isMyConsumerAuction=this.user.consumer_id==this.auction.consumer_id,console.log("pushImg",this.imgData)},mounted(){this.updateNow(),setInterval(this.updateNow.bind(this),1e3)},methods:{likeStateFunc(){0===this.likeState?this.likeState=1:this.likeState=0},bid(){console.log("datas",this.auction.consumer_id),console.log(this.bid_price,this.auction.a_max_price),console.log("auction.auction_Id:"+this.auction.auction_Id),console.log("auction.consumer_id:"+this.auction.consumer_id),console.log("auction.a_starting_price: "+this.auction.a_starting_price),console.log("bid_price:"+this.bid_price),console.log("auction.bid_price: "+this.auction.bid_price),console.log("this.user.consumer_id: "+this.user.consumer_id),console.log("farm_id: "+this.auction.farm_id),console.log(this.stompClient),console.log(this.stompClient.connected),this.auction.consumer_id!=this.user.consumer_id?this.bid_price>this.auction.a_max_price?alert("최대 입찰가를 초과했습니다!"):(this.bid_price==this.auction.a_max_price&&(this.isMaxPrice=1,this.bidAlertText="최고 입찰가로 "+this.bidAlertText),this.bid_price>this.auction.bid_price&&this.stompClient&&this.stompClient.connected?confirm(this.bidAlertText)&&(this.stompClient.send("/receive_bidding",JSON.stringify({auction_Id:this.auction.auction_Id,bid_price:this.bid_price,farm_id:this.auction.farm_id,auction_consumer_id:this.auction.consumer_id,consumer_id:this.user.consumer_id,auction_name:this.auction.auction_name,isMaxPrice:this.isMaxPrice,product_img_name:this.auction.productDTO.product_img_name,f_farm_name:this.auction.f_farm_name,c_name:this.user.c_name}),{}),this.auction.comsumer_id=JSON.parse(localStorage.getItem("user")).consumer_id,this.isMyConsumerAuction=!0,alert("입찰 완료했습니다!")):alert("현재 경매가보다 낮습니다!!")):alert("이미 경매에 참여하셨습니다!")},connect(){const t="/socket";let e=new(ot())(t);this.stompClient=et().over(e),console.log(`소켓 연결을 시도합니다. 서버 주소: ${t}`),console.log(this.$store.state.config.headers),console.log(this.user);let i={TOKEN:this.user.token};console.log(i),this.stompClient.connect(i,(t=>{this.connected=!0,console.log("소켓 연결 성공",t),this.stompClient.subscribe("/send_bidding",(t=>{const e=JSON.parse(t.body);console.log(e),void 0!=e.auction_Id&&(this.$store.commit("UPDATE_BID_PRICE",e,this.user.consumer_id==e.consumer_id),this.auction.auction_Id==e.auction_Id&&(-1==e.bid_price&&(alert(this.consumerBidDeletedAuctionText),this.$router.go(-1)),this.isMyConsumerAuction=!this.isMyConsumerAuction,this.auction.consumer_id=e.consumer_id,this.auction.c_name=e.c_name,this.auction.bid_price=e.bid_price))}))}),(t=>{console.log("소켓 연결 실패",t),this.connected=!1}))},deleteAuction(){return this.auction.consumer_id?alert(this.deleteAuctionConsumerExistText):new Date(this.auction.deadline_date).getTime()<(new Date).getTime()+this.FOUR_HOUR?alert(this.deleteAuctionFourHourText):void(confirm(this.deleteAuctionConfirmText)&&(console.log(this.user.token),lt()["delete"](`/api/auction/${this.auction.auction_Id}/${this.auction.product_id}/${this.auction.productDTO.product_img_name}`,{headers:{TOKEN:this.user.token}}).then((t=>{console.log(t),alert(this.deleteAuctionAlertText),this.$router.go(-1)})).catch((t=>{console.log(t)}))))},updateNow(){this.now=Math.round(Date.now()/1e3)},updateDeadlineDate(t){if(0===this.auction.bid_status)return"";let e=new Date;console.log("date",e),console.log("auction",this.auction),console.log("deadline",t),e.setFullYear(Number(t.substr(0,4))),e.setMonth(Number(t.substr(5,2))-1),e.setDate(Number(t.substr(8,2))),e.setHours(Number(t.substr(11,2))),e.setMinutes(Number(t.substr(14,2))),e.setSeconds(Number(t.substr(17,2)));let i=Math.round(e.getTime()/1e3)-this.now;if(i<0)return"";let o=Math.floor(i/86400),s=Math.floor(i%86400),n=Math.floor(s/3600),a=Math.floor(s%3600/60),c=Math.floor(s%3600%60),l="";return 0!=o&&(l+=o+"일 "),0!=n&&(l+=n+"시간 "),0!=a&&(l+=a+"분 "),0!=c&&(l+=c+"초 후"),l}}},rt=i(89);const dt=(0,rt.Z)(ut,[["render",X],["__scopeId","data-v-731c5b7b"]]);var _t=dt}}]);
//# sourceMappingURL=898.0abe656e.js.map