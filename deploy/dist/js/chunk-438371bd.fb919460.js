(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-438371bd"],{"0433":function(t,e,n){},"23e5":function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"ui reply form"},[n("div",{staticClass:"field"},[n("textarea",{directives:[{name:"model",rawName:"v-model",value:t.content,expression:"content"}],attrs:{name:"content",placeholder:t.placeholderMsg},domProps:{value:t.content},on:{input:function(e){e.target.composing||(t.content=e.target.value)}}})]),n("div",{staticClass:"fields"},[n("div",{staticClass:"field m-mobile-wide m-margin-bottom-small"},[n("div",{staticClass:"ui left icon input",class:{disabled:t.hasUser}},[n("i",{staticClass:"user icon"}),n("input",{directives:[{name:"model",rawName:"v-model",value:t.user.nickname,expression:"user.nickname"}],attrs:{type:"text",name:"nickname",placeholder:"姓名"},domProps:{value:t.user.nickname},on:{input:function(e){e.target.composing||t.$set(t.user,"nickname",e.target.value)}}})])]),n("div",{staticClass:"field m-mobile-wide m-margin-bottom-small"},[n("div",{staticClass:"ui left icon input"},[n("i",{staticClass:"icon mail"}),n("input",{directives:[{name:"model",rawName:"v-model",value:t.user.email,expression:"user.email"}],attrs:{type:"email",name:"email",placeholder:"邮箱"},domProps:{value:t.user.email},on:{input:function(e){e.target.composing||t.$set(t.user,"email",e.target.value)}}})])]),t.parentCommentId?n("div",{staticClass:"field m-mobile-wide m-margin-bottom-small"},[n("div",{staticClass:"ui labeled icon button m-mobile-wide",on:{click:t.cancelReply}},[n("i",{staticClass:"icon close"}),t._v(" 取消 回复 ")])]):t._e(),n("div",{staticClass:"field m-mobile-wide m-margin-bottom-small"},[n("div",{staticClass:"ui blue labeled icon button m-mobile-wide",on:{click:t.submitForm}},[n("i",{staticClass:"icon edit"}),t._v(" 添加 回复 ")])])]),n("div",{staticClass:"ui error message"})])},s=[],i=n("c587"),o=n("afd8"),m=n("c434"),r={name:"ReplyForm",data:function(){return{hasUser:!1,content:"",user:{type:Object,default:function(){return{nickname:"",email:""}}}}},props:{blogId:null,parentCommentId:null,parentCommentNickname:null,parentComment:null},computed:{placeholderMsg:function(){return this.parentCommentNickname?"@"+this.parentCommentNickname+"：":"请输入评论信息..."}},mounted:function(){this.getUserMsg(),$('[name="nickname"]').popup({on:"click",content:"此名称将作为您在本站的常用名，不支持再修改~~",position:"top center"}),$('[name="email"]').popup({on:"click",content:"有回复内容将通过此邮箱通知你",position:"top center"}),$(".ui.form").form({fields:{content:{identifier:"content",rules:[{type:"empty",prompt:"请输入评论内容"}]},nickname:{identifier:"nickname",rules:[{type:"empty",prompt:"请输入名称"}]},email:{identifier:"email",rules:[{type:"email",prompt:"请输入正确的邮箱"}]}}})},methods:{getUserMsg:function(){var t=this;Object(o["b"])().then((function(e){e.data.data&&(t.user=e.data.data,t.hasUser=!0)}))},submitForm:function(){var t=$(".ui.form").form("validate form");t?this.blogId?this.commentSubmit():this.messageSubmit():console.log("校验失败")},commentSubmit:function(){var t=this,e={blogId:this.blogId,parentCommentId:this.parentCommentId,content:this.content,user:this.user};Object(i["b"])(e).then((function(e){t.$emit("scrollToComment"),t.$emit("resetParentComment"),t.$emit("reRequestComment"),t.resetData(),t.getUserMsg()}))},messageSubmit:function(){var t=this,e={parentMsgId:this.parentCommentId,content:this.content,user:this.user};Object(m["b"])(e).then((function(e){t.$emit("scrollToComment"),t.$emit("resetParentComment"),t.$emit("reRequestComment"),t.$emit("openComment",t.parentCommentId),t.resetData(),t.getUserMsg()}))},cancelReply:function(){this.content="",this.$emit("resetParentComment")},resetData:function(){Object.assign(this.$data,this.$options.data())}}},c=r,l=n("2877"),u=Object(l["a"])(c,a,s,!1,null,null,null);e["default"]=u.exports},"2b1d":function(t,e,n){"use strict";var a=n("0433"),s=n.n(a);s.a},"67f1":function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"ui bottom attached segment m-bg"},[n("comment-area",t._b({ref:"commentArea",on:{reply:t.reply}},"comment-area",t.$attrs,!1)),n("reply-form",t._g({ref:"replyForm",attrs:{parentCommentId:t.parentCommentId,parentCommentNickname:t.parentCommentNickname,parentComment:t.parentComment,blogId:t.blogId},on:{scrollToComment:t.scrollToComment,resetParentComment:t.resetParentComment}},t.$listeners))],1)},s=[],i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return 0!==t.commentList.length?n("div",{staticClass:"ui teal segment m-bg",attrs:{id:"comment_area"}},[n("div",{staticClass:"ui threaded comments",staticStyle:{"max-width":"100% !important"}},[n("h3",{staticClass:"ui dividing header"},[t._v("评论")]),t._l(t.commentList,(function(e,a){return n("div",{key:e.id+"-"+a,staticClass:"comment"},[n("a",{staticClass:"avatar"},[n("img",{staticClass:"comment-avatar",attrs:{src:e.user.avatar,alt:""}})]),n("div",{staticClass:"content"},[n("a",{staticClass:"author",on:{click:function(n){return t.reply(e.id,e.user.nickname,e)}}},[n("span",{domProps:{textContent:t._s(e.user.nickname)}}),e.adminComment?n("div",{staticClass:"ui basic left pointing label"},[t._v("御主")]):t._e()]),n("div",{staticClass:"metadata"},[n("span",{staticClass:"date",domProps:{textContent:t._s(t.toFormatDate(e.createTime))}})]),n("pre",{staticClass:"text",domProps:{textContent:t._s(e.content)}}),n("div",{staticClass:"actions"},[n("a",{staticClass:"reply m_reply",on:{click:function(n){return t.reply(e.id,e.user.nickname,e)}}},[t._v("回复")])])]),e.childComments&&0!==e.childComments.length?n("div",{staticClass:"comments"},t._l(e.childComments,(function(a,s){return n("div",{key:a.id+"-"+s,staticClass:"comment"},[n("a",{staticClass:"avatar"},[n("img",{staticClass:"comment-avatar",attrs:{src:a.user.avatar,alt:""}})]),n("div",{staticClass:"content"},[n("span",[n("a",{staticClass:"author",domProps:{textContent:t._s(a.user.nickname)},on:{click:function(e){return t.reply(a.id,a.user.nickname,a)}}}),a.adminComment?n("div",{staticClass:"ui basic left pointing label"},[t._v("御主")]):t._e(),n("span",{staticClass:"parent-comment",domProps:{textContent:t._s(" @ "+e.user.nickname)}})]),n("div",{staticClass:"metadata"},[n("span",{staticClass:"date",domProps:{textContent:t._s(t.toFormatDate(a.createTime))}})]),n("pre",{staticClass:"text",domProps:{textContent:t._s(a.content)}}),n("div",{staticClass:"actions"},[n("a",{staticClass:"reply m_reply",on:{click:function(e){return t.reply(a.id,a.user.nickname,a)}}},[t._v("回复")])])])])})),0):t._e()])}))],2)]):t._e()},o=[],m=n("90b9"),r={name:"CommentArea",props:{commentList:{type:Array,default:function(){return[]}}},methods:{reply:function(t,e,n){this.$emit("reply",{id:t,nickname:e,parentComment:n})},toFormatDate:function(t){return Object(m["b"])(new Date(t),"yyyy-MM-dd hh:mm:ss")}}},c=r,l=(n("2b1d"),n("2877")),u=Object(l["a"])(c,i,o,!1,null,"4831c5bf",null),d=u.exports,p=n("23e5"),C={name:"Comment",data:function(){return{parentCommentId:null,parentCommentNickname:"",parentComment:null}},props:{blogId:null},components:{CommentArea:d,ReplyForm:p["default"]},methods:{scrollToComment:function(){this.$refs.commentArea.$el.scrollIntoView({behavior:"smooth",block:"start",inline:"nearest"})},resetParentComment:function(){Object.assign(this.$data,this.$options.data())},reply:function(t){this.parentCommentId=t.id,this.parentCommentNickname=t.nickname,this.parentComment=t.parentComment,this.$refs.replyForm.$el.scrollIntoView({behavior:"smooth",block:"end",inline:"nearest"}),this.$refs.replyForm.$el.querySelector("textarea").focus()}}},f=C,b=Object(l["a"])(f,a,s,!1,null,null,null);e["default"]=b.exports},c434:function(t,e,n){"use strict";n.d(e,"b",(function(){return i})),n.d(e,"a",(function(){return o}));var a=n("5530"),s=n("1bab");function i(t){return Object(s["b"])({url:"/message/saveMessage",method:"PUT",contentType:"application/json",data:Object(a["a"])({},t)})}function o(){return Object(s["b"])({url:"/message/findMessageList"})}}}]);
//# sourceMappingURL=chunk-438371bd.fb919460.js.map