"use strict";(self["webpackChunkrealvue"]=self["webpackChunkrealvue"]||[]).push([[326,896],{5140:function(t,e,a){a.d(e,{Z:function(){return y}});var i=a(3396),o=a(7139),s=a(3289);const n=t=>((0,i.dD)("data-v-356d296a"),t=t(),(0,i.Cn)(),t),r={class:"header"},l={class:"navLi"},c=(0,i.Uk)("mdi-home"),u={class:"navLi"},d={class:"navLi"},_=n((()=>(0,i._)("i",{class:"fas fa-chevron-left fa-2x","aria-hidden":"true"},null,-1))),h=[_];function m(t,e,a,n,_,m){const p=(0,i.up)("router-link");return(0,i.wg)(),(0,i.iD)("header",r,[(0,i._)("nav",null,[(0,i._)("li",l,[(0,i.Wm)(p,{to:"/"},{default:(0,i.w5)((()=>[(0,i.Wm)(s.t,null,{default:(0,i.w5)((()=>[c])),_:1})])),_:1})]),(0,i._)("li",u,(0,o.zw)(a.headerProps),1),(0,i._)("li",d,[(0,i._)("p",{class:"nav__link",onClick:e[0]||(e[0]=t=>m.goBack())},h)])])])}var p={props:{headerProps:String},methods:{goBack(){this.$router.go(-1)}}},g=a(89);const f=(0,g.Z)(p,[["render",m],["__scopeId","data-v-356d296a"]]);var y=f},9816:function(t,e,a){a.r(e),a.d(e,{default:function(){return H}});var i=a(3396),o=a(7139);const s=(0,i._)("h2",{class:"profile_loc_h2"},"결제 상품",-1),n={class:"aside_area aside_popular"},r=(0,i._)("h3",{class:"h_popular"},[(0,i._)("span")],-1),l={class:"tbl_home"},c=(0,i._)("tr",null,[(0,i._)("th",null,"맛있는 딸기"),(0,i._)("td",null,"10kg")],-1),u=(0,i._)("th",null,"낙과 일자",-1),d=(0,i._)("th",null,"사이즈",-1),_=(0,i._)("th",null,"상태",-1),h=(0,i._)("th",null,"낙찰된 가격",-1),m={class:"aside_area aside_popular"},p={class:"gray_div"},g=(0,i._)("h3",{class:"h_popular"},null,-1),f={class:"tbl_home"},y=(0,i._)("th",{class:"whitefont"},"수수료 (5%)",-1),D={class:"whitefont"},b=(0,i._)("th",{class:"whitefont"},"상품금액",-1),v={class:"whitefont"},O={class:"aside_area aside_popular"},T=(0,i._)("h3",{class:"h_popular"},null,-1),k={class:"tbl_home"},w=(0,i._)("th",{class:"font15"},"최종입금금액",-1),S={class:"font15"},I={class:"main_nav_b_div"},$={class:"main_b_nav"},z={class:"main_m_ui_list"},P={class:"nav__btn"};function C(t,e,a,C,A,L){const j=(0,i.up)("Header"),N=(0,i.up)("Slide");return(0,i.wg)(),(0,i.iD)("div",null,[(0,i.Wm)(j,{headerProps:A.headerProps},null,8,["headerProps"]),(0,i.Wm)(N,{imgData:A.imgData},null,8,["imgData"]),s,(0,i._)("fieldset",null,[(0,i._)("div",n,[r,(0,i._)("table",l,[(0,i._)("tbody",null,[c,(0,i._)("tr",null,[u,(0,i._)("td",null,(0,o.zw)(A.auction.p_drop_date.slice(0,19).replace("T"," ")),1)]),(0,i._)("tr",null,[d,(0,i._)("td",null,(0,o.zw)(A.auction.size),1)]),(0,i._)("tr",null,[_,(0,i._)("td",null,(0,o.zw)(A.auction.p_status),1)]),(0,i._)("tr",null,[h,(0,i._)("td",null,(0,o.zw)(A.auction.bid_price.toLocaleString())+"원",1)])])])])]),(0,i._)("fieldset",null,[(0,i._)("div",m,[(0,i._)("div",p,[g,(0,i._)("table",f,[(0,i._)("tbody",null,[(0,i._)("tr",null,[y,(0,i._)("td",D,(0,o.zw)(A.fee.toLocaleString())+"원",1)]),(0,i._)("tr",null,[b,(0,i._)("td",v,(0,o.zw)(A.auction.bid_price.toLocaleString())+"원",1)])])])])])]),(0,i._)("fieldset",null,[(0,i._)("div",O,[T,(0,i._)("table",k,[(0,i._)("tbody",null,[(0,i._)("tr",null,[w,(0,i._)("td",S,(0,o.zw)(A.paymentAmount.toLocaleString())+"원",1)])])])])]),(0,i._)("div",I,[(0,i._)("nav",$,[(0,i._)("ul",z,[(0,i._)("li",P,[(0,i._)("h4",{class:"user-component__title",onClick:e[0]||(e[0]=t=>L.pay())},"정산하기")])])])])])}var A=a(5140),L=a(880),j=a(6265),N=a.n(j),W={components:{Header:A.Z,Slide:L["default"]},data(){return{headerProps:"경매 결제",items:[{src:"https://images.mypetlife.co.kr/content/uploads/2018/06/09160331/strawberries-red-fruit-royalty-free-70746-1024x768.jpeg"},{src:"https://cdn.vuetifyjs.com/images/carousel/sky.jpg"},{src:"https://cdn.vuetifyjs.com/images/carousel/bird.jpg"},{src:"https://cdn.vuetifyjs.com/images/carousel/planet.jpg"}],user:JSON.parse(localStorage.getItem("user")),auction:null,fee:null,paymentAmount:null,imgData:[],orderDTO:{}}},created(){document.cookie="safeCookie1=foo; SameSite=Lax",document.cookie="safeCookie2=foo",document.cookie="crossCookie=bar; SameSite=None; Secure",console.log("arr",this.$route.params.auction),void 0==this.$route.params.auction&&this.$router.push({name:"farm_mypage_auction"}),this.auction=JSON.parse(this.$route.params.auction),console.log("경매 정보",this.auction),this.fee=this.auction.bid_price/100*5,this.paymentAmount=this.auction.bid_price+this.fee,this.orderDTO.paymentDTO={},this.orderDTO.paymentDTO.auction_Id=this.auction.auction_Id,this.orderDTO.paymentDTO.payment_amount=this.paymentAmount,this.orderDTO.paymentDTO.pay_method="card",this.orderDTO.deliveryDTO={},this.orderDTO.deliveryDTO.zipcode=this.user.c_zipcode,this.orderDTO.deliveryDTO.destination=this.user.c_location+" "+this.user.c_detail_location,this.orderDTO.auction_Id=this.auction.auction_Id,this.orderDTO.bidding={},this.orderDTO.bidding.auction_Id=this.auction.auction_Id,this.orderDTO.bidding.bid_price=this.paymentAmount,this.orderDTO.bidding.farm_id=this.auction.farm_id,this.orderDTO.bidding.consumer_id=this.auction.consumer_id,this.orderDTO.bidding.auction_name=this.auction.auction_name,this.orderDTO.bidding.f_farm_name=this.auction.f_farm_name,this.orderDTO.bidding.c_name=this.auction.c_name,this.orderDTO.bidding.product_img_name=this.auction.product_img_name;let t=this.auction.product_img_name[this.auction.product_img_name.length-1];console.log("img",t);for(let e=0;e<t;e++)this.imgData.push(this.auction.product_img_name.replace("(0)",`(${e})`));console.log("pushImg",this.imgData)},methods:{navigateAuctionPayment(){this.$router.push({name:"payment"})},pay(){IMP.init("imp87328402"),IMP.request_pay({pg:"html5_inicis",pay_method:"card",merchant_uid:(new Date).getTime().toString(),name:"낙과 경매 플랫폼 PACHI",amount:this.paymentAmount,buyer_email:this.user.c_email,buyer_name:this.user.c_name,buyer_tel:this.user.c_phonenum.replace(/[^0-9]/g,"").replace(/(^02|^0505|^1[0-9]{1}|^0[0-9]{4})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--","-"),buyer_addr:this.user.c_location,buyer_postcode:this.user.c_zipcode},(t=>{console.log(t),t.success?(console.log("결제 성공"),N().post("/api/payment",this.orderDTO,{headers:{TOKEN:this.user.token}}).then((t=>{console.log(t.data),this.$router.push({name:"farm_calculate_clear",params:{id:this.orderDTO.paymentDTO.auction_Id,order:JSON.stringify(t.data)}})})).catch((t=>{console.log(t)}))):(console.log("결제 실패"),alert("결제 실패하셨습니다."),this.$router.go(-1))}))}}},Z=a(89);const x=(0,Z.Z)(W,[["render",C]]);var H=x}}]);
//# sourceMappingURL=326.fd6ec2c2.js.map