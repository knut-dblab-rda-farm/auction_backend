"use strict";(self["webpackChunkfront"]=self["webpackChunkfront"]||[]).push([[913],{3913:function(e,l,n){n.r(l),n.d(l,{default:function(){return g}});var t=n(3396),o=n(9242);const r=(0,t._)("header",{class:"welcome-header"},[(0,t._)("h1",{class:"welcome-header__title"},"정 보 입 력")],-1),a=(0,t._)("label",{id:"login-form-label"},"농가명",-1),i=(0,t._)("label",{id:"login-form-label"},"대표자",-1),_=(0,t._)("label",{id:"login-form-label"},"사업자 등록번호",-1),u=(0,t._)("label",{id:"login-form-label"},"사업장 소재지",-1),f=(0,t._)("label",{id:"login-form-label"},"농가 전화번호",-1);function m(e,l,n,m,s,p){return(0,t.wg)(),(0,t.iD)("div",null,[r,(0,t._)("form",{onSubmit:l[6]||(l[6]=(0,o.iM)(((...l)=>e.submitBizForm&&e.submitBizForm(...l)),["prevent"])),class:"login-form"},[a,(0,t.wy)((0,t._)("input",{"onUpdate:modelValue":l[0]||(l[0]=e=>s.f_farm_name=e),class:"information-form__input",type:"text",required:"",placeholder:"농가명"},null,512),[[o.nr,s.f_farm_name]]),i,(0,t.wy)((0,t._)("input",{"onUpdate:modelValue":l[1]||(l[1]=e=>s.f_representative=e),class:"information-form__input",type:"text",required:"",placeholder:"대표자"},null,512),[[o.nr,s.f_representative]]),_,(0,t.wy)((0,t._)("input",{"onUpdate:modelValue":l[2]||(l[2]=e=>s.f_BRN=e),class:"login-form__input",type:"text",required:"",placeholder:"사업자 등록번호"},null,512),[[o.nr,s.f_BRN]]),u,(0,t.wy)((0,t._)("input",{"onUpdate:modelValue":l[3]||(l[3]=e=>s.f_location=e),class:"login-form__input",type:"text",required:"",placeholder:"사업장 소재지"},null,512),[[o.nr,s.f_location]]),f,(0,t.wy)((0,t._)("input",{"onUpdate:modelValue":l[4]||(l[4]=e=>s.f_num=e),class:"login-form__input",type:"text",required:"",placeholder:"농가 전화번호"},null,512),[[o.nr,s.f_num]]),(0,t._)("input",{class:"login-form__btn",type:"submit",onClick:l[5]||(l[5]=e=>p.submitBizSignUp({f_farm_name:s.f_farm_name,f_representative:s.f_representative,f_BRN:s.f_BRN,f_location:s.f_location,f_num:s.f_num})),value:"다음"})],32)])}var s=n(6265),p=n.n(s),c={name:"submitBizForm",data(){return{f_farm_name:null,f_representative:null,f_BRN:null,f_location:null,f_num:null}},methods:{submitBizSignUp(e){console.log(e),p().post("http://localhost:8080/api/signupFarmMember",e).then((e=>{console.log(e)})).catch((e=>{console.log(e)}))}}},d=n(89);const b=(0,d.Z)(c,[["render",m]]);var g=b}}]);
//# sourceMappingURL=913.5a77dbbb.js.map