/*
 Highcharts JS v5.0.14 (2017-07-28)

 3D features for Highcharts JS

 @license: www.highcharts.com/license
*/
(function(A){"object"===typeof module&&module.exports?module.exports=A:A(Highcharts)})(function(A){(function(d){var t=d.deg2rad,v=d.pick;d.perspective=function(w,x,r){var m=x.options.chart.options3d,f=r?x.inverted:!1,l=x.plotWidth/2,u=x.plotHeight/2,k=m.depth/2,c=v(m.depth,1)*v(m.viewDistance,0),b=x.scale3d||1,e=t*m.beta*(f?-1:1),m=t*m.alpha*(f?-1:1),a=Math.cos(m),g=Math.cos(-e),h=Math.sin(m),n=Math.sin(-e);r||(l+=x.plotLeft,u+=x.plotTop);return d.map(w,function(e){var d,p;p=(f?e.y:e.x)-l;var q=(f?
e.x:e.y)-u,G=(e.z||0)-k;d=g*p-n*G;e=-h*n*p+a*q-g*h*G;p=a*n*p+h*q+a*g*G;q=0<c&&c<Number.POSITIVE_INFINITY?c/(p+k+c):1;d=d*q*b+l;e=e*q*b+u;return{x:f?e:d,y:f?d:e,z:p*b+k}})};d.pointCameraDistance=function(d,t){var r=t.options.chart.options3d,m=t.plotWidth/2;t=t.plotHeight/2;r=v(r.depth,1)*v(r.viewDistance,0)+r.depth;return Math.sqrt(Math.pow(m-d.plotX,2)+Math.pow(t-d.plotY,2)+Math.pow(r-d.plotZ,2))};d.shapeArea=function(d){var t=0,r,m;for(r=0;r<d.length;r++)m=(r+1)%d.length,t+=d[r].x*d[m].y-d[m].x*
d[r].y;return t/2};d.shapeArea3d=function(t,v,r){return d.shapeArea(d.perspective(t,v,r))}})(A);(function(d){function t(a,b,e,c,d,g,h,k){var B=[],q=g-d;return g>d&&g-d>Math.PI/2+.0001?(B=B.concat(t(a,b,e,c,d,d+Math.PI/2,h,k)),B=B.concat(t(a,b,e,c,d+Math.PI/2,g,h,k))):g<d&&d-g>Math.PI/2+.0001?(B=B.concat(t(a,b,e,c,d,d-Math.PI/2,h,k)),B=B.concat(t(a,b,e,c,d-Math.PI/2,g,h,k))):["C",a+e*Math.cos(d)-e*p*q*Math.sin(d)+h,b+c*Math.sin(d)+c*p*q*Math.cos(d)+k,a+e*Math.cos(g)+e*p*q*Math.sin(g)+h,b+c*Math.sin(g)-
c*p*q*Math.cos(g)+k,a+e*Math.cos(g)+h,b+c*Math.sin(g)+k]}var v=Math.cos,w=Math.PI,x=Math.sin,r=d.animObject,m=d.charts,f=d.color,l=d.defined,u=d.deg2rad,k=d.each,c=d.extend,b=d.inArray,e=d.map,a=d.merge,g=d.perspective,h=d.pick,n=d.SVGElement,z=d.SVGRenderer,y=d.wrap,p=4*(Math.sqrt(2)-1)/3/(w/2);y(z.prototype,"init",function(a){a.apply(this,[].slice.call(arguments,1));k([{name:"darker",slope:.6},{name:"brighter",slope:1.4}],function(a){this.definition({tagName:"filter",id:"highcharts-"+a.name,children:[{tagName:"feComponentTransfer",
children:[{tagName:"feFuncR",type:"linear",slope:a.slope},{tagName:"feFuncG",type:"linear",slope:a.slope},{tagName:"feFuncB",type:"linear",slope:a.slope}]}]})},this)});z.prototype.toLinePath=function(a,b){var e=[];k(a,function(a){e.push("L",a.x,a.y)});a.length&&(e[0]="M",b&&e.push("Z"));return e};z.prototype.toLineSegments=function(a){var b=[],e=!0;k(a,function(a){b.push(e?"M":"L",a.x,a.y);e=!e});return b};z.prototype.face3d=function(a){var b=this,e=this.createElement("path");e.vertexes=[];e.insidePlotArea=
!1;e.enabled=!0;y(e,"attr",function(a,e){if("object"===typeof e&&(l(e.enabled)||l(e.vertexes)||l(e.insidePlotArea))){this.enabled=h(e.enabled,this.enabled);this.vertexes=h(e.vertexes,this.vertexes);this.insidePlotArea=h(e.insidePlotArea,this.insidePlotArea);delete e.enabled;delete e.vertexes;delete e.insidePlotArea;var c=g(this.vertexes,m[b.chartIndex],this.insidePlotArea),B=b.toLinePath(c,!0),c=d.shapeArea(c),c=this.enabled&&0<c?"visible":"hidden";e.d=B;e.visibility=c}return a.apply(this,[].slice.call(arguments,
1))});y(e,"animate",function(a,e){if("object"===typeof e&&(l(e.enabled)||l(e.vertexes)||l(e.insidePlotArea))){this.enabled=h(e.enabled,this.enabled);this.vertexes=h(e.vertexes,this.vertexes);this.insidePlotArea=h(e.insidePlotArea,this.insidePlotArea);delete e.enabled;delete e.vertexes;delete e.insidePlotArea;var c=g(this.vertexes,m[b.chartIndex],this.insidePlotArea),B=b.toLinePath(c,!0),c=d.shapeArea(c),c=this.enabled&&0<c?"visible":"hidden";e.d=B;this.attr("visibility",c)}return a.apply(this,[].slice.call(arguments,
1))});return e.attr(a)};z.prototype.polyhedron=function(a){var b=this,e=this.g(),c=e.destroy;e.faces=[];e.destroy=function(){for(var a=0;a<e.faces.length;a++)e.faces[a].destroy();return c.call(this)};y(e,"attr",function(a,c,d,g,h){if("object"===typeof c&&l(c.faces)){for(;e.faces.length>c.faces.length;)e.faces.pop().destroy();for(;e.faces.length<c.faces.length;)e.faces.push(b.face3d().add(e));for(var B=0;B<c.faces.length;B++)e.faces[B].attr(c.faces[B],null,g,h);delete c.faces}return a.apply(this,[].slice.call(arguments,
1))});y(e,"animate",function(a,c,d,g){if(c&&c.faces){for(;e.faces.length>c.faces.length;)e.faces.pop().destroy();for(;e.faces.length<c.faces.length;)e.faces.push(b.face3d().add(e));for(var h=0;h<c.faces.length;h++)e.faces[h].animate(c.faces[h],d,g);delete c.faces}return a.apply(this,[].slice.call(arguments,1))});return e.attr(a)};z.prototype.cuboid=function(a){var e=this.g(),b=e.destroy;a=this.cuboidPath(a);e.front=this.path(a[0]).attr({"class":"highcharts-3d-front"}).add(e);e.top=this.path(a[1]).attr({"class":"highcharts-3d-top"}).add(e);
e.side=this.path(a[2]).attr({"class":"highcharts-3d-side"}).add(e);e.fillSetter=function(a){this.front.attr({fill:a});this.top.attr({fill:f(a).brighten(.1).get()});this.side.attr({fill:f(a).brighten(-.1).get()});this.color=a;e.fill=a;return this};e.opacitySetter=function(a){this.front.attr({opacity:a});this.top.attr({opacity:a});this.side.attr({opacity:a});return this};e.attr=function(a,e,b,c){if("string"===typeof a&&"undefined"!==typeof e){var d=a;a={};a[d]=e}if(a.shapeArgs||l(a.x))a=this.renderer.cuboidPath(a.shapeArgs||
a),this.front.attr({d:a[0]}),this.top.attr({d:a[1]}),this.side.attr({d:a[2]});else return n.prototype.attr.call(this,a,void 0,b,c);return this};e.animate=function(a,e,b){l(a.x)&&l(a.y)?(a=this.renderer.cuboidPath(a),this.front.animate({d:a[0]},e,b),this.top.animate({d:a[1]},e,b),this.side.animate({d:a[2]},e,b),this.attr({zIndex:-a[3]})):a.opacity?(this.front.animate(a,e,b),this.top.animate(a,e,b),this.side.animate(a,e,b)):n.prototype.animate.call(this,a,e,b);return this};e.destroy=function(){this.front.destroy();
this.top.destroy();this.side.destroy();return b.call(this)};e.attr({zIndex:-a[3]});return e};d.SVGRenderer.prototype.cuboidPath=function(a){function b(a){return t[a]}var c=a.x,h=a.y,k=a.z,q=a.height,u=a.width,n=a.depth,p=m[this.chartIndex],y,z,l=p.options.chart.options3d.alpha,f=0,t=[{x:c,y:h,z:k},{x:c+u,y:h,z:k},{x:c+u,y:h+q,z:k},{x:c,y:h+q,z:k},{x:c,y:h+q,z:k+n},{x:c+u,y:h+q,z:k+n},{x:c+u,y:h,z:k+n},{x:c,y:h,z:k+n}],t=g(t,p,a.insidePlotArea);z=function(a,c){var h=[[],-1];a=e(a,b);c=e(c,b);0>d.shapeArea(a)?
h=[a,0]:0>d.shapeArea(c)&&(h=[c,1]);return h};y=z([3,2,1,0],[7,6,5,4]);a=y[0];u=y[1];y=z([1,6,7,0],[4,5,2,3]);q=y[0];n=y[1];y=z([1,2,5,6],[0,7,4,3]);z=y[0];y=y[1];1===y?f+=1E4*(1E3-c):y||(f+=1E4*c);f+=10*(!n||0<=l&&180>=l||360>l&&357.5<l?p.plotHeight-h:10+h);1===u?f+=100*k:u||(f+=100*(1E3-k));f=-Math.round(f);return[this.toLinePath(a,!0),this.toLinePath(q,!0),this.toLinePath(z,!0),f]};d.SVGRenderer.prototype.arc3d=function(e){function d(e){var c=!1,g={};e=a(e);for(var d in e)-1!==b(d,p)&&(g[d]=e[d],
delete e[d],c=!0);return c?g:!1}var g=this.g(),q=g.renderer,p="x y r innerR start end".split(" ");e=a(e);e.alpha*=u;e.beta*=u;g.top=q.path();g.side1=q.path();g.side2=q.path();g.inn=q.path();g.out=q.path();g.onAdd=function(){var a=g.parentGroup,e=g.attr("class");g.top.add(g);k(["out","inn","side1","side2"],function(b){g[b].attr({"class":e+" highcharts-3d-side"}).add(a)})};k(["addClass","removeClass"],function(a){g[a]=function(){var e=arguments;k(["top","out","inn","side1","side2"],function(b){g[b][a].apply(g[b],
e)})}});g.setPaths=function(a){var e=g.renderer.arc3dPath(a),b=100*e.zTop;g.attribs=a;g.top.attr({d:e.top,zIndex:e.zTop});g.inn.attr({d:e.inn,zIndex:e.zInn});g.out.attr({d:e.out,zIndex:e.zOut});g.side1.attr({d:e.side1,zIndex:e.zSide1});g.side2.attr({d:e.side2,zIndex:e.zSide2});g.zIndex=b;g.attr({zIndex:b});a.center&&(g.top.setRadialReference(a.center),delete a.center)};g.setPaths(e);g.fillSetter=function(a){var e=f(a).brighten(-.1).get();this.fill=a;this.side1.attr({fill:e});this.side2.attr({fill:e});
this.inn.attr({fill:e});this.out.attr({fill:e});this.top.attr({fill:a});return this};k(["opacity","translateX","translateY","visibility"],function(a){g[a+"Setter"]=function(a,e){g[e]=a;k(["out","inn","side1","side2","top"],function(b){g[b].attr(e,a)})}});y(g,"attr",function(a,e){var b;"object"===typeof e&&(b=d(e))&&(c(g.attribs,b),g.setPaths(g.attribs));return a.apply(this,[].slice.call(arguments,1))});y(g,"animate",function(e,b,c,g){var k,q=this.attribs,u;delete b.center;delete b.z;delete b.depth;
delete b.alpha;delete b.beta;u=r(h(c,this.renderer.globalAnimation));u.duration&&(k=d(b),b.dummy=1,k&&(u.step=function(e,b){function c(a){return q[a]+(h(k[a],q[a])-q[a])*b.pos}"dummy"===b.prop&&b.elem.setPaths(a(q,{x:c("x"),y:c("y"),r:c("r"),innerR:c("innerR"),start:c("start"),end:c("end")}))}),c=u);return e.call(this,b,c,g)});g.destroy=function(){this.top.destroy();this.out.destroy();this.inn.destroy();this.side1.destroy();this.side2.destroy();n.prototype.destroy.call(this)};g.hide=function(){this.top.hide();
this.out.hide();this.inn.hide();this.side1.hide();this.side2.hide()};g.show=function(){this.top.show();this.out.show();this.inn.show();this.side1.show();this.side2.show()};return g};z.prototype.arc3dPath=function(a){function e(a){a%=2*Math.PI;a>Math.PI&&(a=2*Math.PI-a);return a}var b=a.x,c=a.y,g=a.start,d=a.end-.00001,h=a.r,k=a.innerR,u=a.depth,n=a.alpha,p=a.beta,q=Math.cos(g),y=Math.sin(g);a=Math.cos(d);var z=Math.sin(d),f=h*Math.cos(p),h=h*Math.cos(n),l=k*Math.cos(p),m=k*Math.cos(n),k=u*Math.sin(p),
r=u*Math.sin(n),u=["M",b+f*q,c+h*y],u=u.concat(t(b,c,f,h,g,d,0,0)),u=u.concat(["L",b+l*a,c+m*z]),u=u.concat(t(b,c,l,m,d,g,0,0)),u=u.concat(["Z"]),A=0<p?Math.PI/2:0,p=0<n?0:Math.PI/2,A=g>-A?g:d>-A?-A:g,C=d<w-p?d:g<w-p?w-p:d,D=2*w-p,n=["M",b+f*v(A),c+h*x(A)],n=n.concat(t(b,c,f,h,A,C,0,0));d>D&&g<D?(n=n.concat(["L",b+f*v(C)+k,c+h*x(C)+r]),n=n.concat(t(b,c,f,h,C,D,k,r)),n=n.concat(["L",b+f*v(D),c+h*x(D)]),n=n.concat(t(b,c,f,h,D,d,0,0)),n=n.concat(["L",b+f*v(d)+k,c+h*x(d)+r]),n=n.concat(t(b,c,f,h,d,D,
k,r)),n=n.concat(["L",b+f*v(D),c+h*x(D)]),n=n.concat(t(b,c,f,h,D,C,0,0))):d>w-p&&g<w-p&&(n=n.concat(["L",b+f*Math.cos(C)+k,c+h*Math.sin(C)+r]),n=n.concat(t(b,c,f,h,C,d,k,r)),n=n.concat(["L",b+f*Math.cos(d),c+h*Math.sin(d)]),n=n.concat(t(b,c,f,h,d,C,0,0)));n=n.concat(["L",b+f*Math.cos(C)+k,c+h*Math.sin(C)+r]);n=n.concat(t(b,c,f,h,C,A,k,r));n=n.concat(["Z"]);p=["M",b+l*q,c+m*y];p=p.concat(t(b,c,l,m,g,d,0,0));p=p.concat(["L",b+l*Math.cos(d)+k,c+m*Math.sin(d)+r]);p=p.concat(t(b,c,l,m,d,g,k,r));p=p.concat(["Z"]);
q=["M",b+f*q,c+h*y,"L",b+f*q+k,c+h*y+r,"L",b+l*q+k,c+m*y+r,"L",b+l*q,c+m*y,"Z"];b=["M",b+f*a,c+h*z,"L",b+f*a+k,c+h*z+r,"L",b+l*a+k,c+m*z+r,"L",b+l*a,c+m*z,"Z"];z=Math.atan2(r,-k);c=Math.abs(d+z);a=Math.abs(g+z);g=Math.abs((g+d)/2+z);c=e(c);a=e(a);g=e(g);g*=1E5;d=1E5*a;c*=1E5;return{top:u,zTop:1E5*Math.PI+1,out:n,zOut:Math.max(g,d,c),inn:p,zInn:Math.max(g,d,c),side1:q,zSide1:.99*c,side2:b,zSide2:.99*d}}})(A);(function(d){function t(d,k){var c=d.plotLeft,b=d.plotWidth+c,e=d.plotTop,a=d.plotHeight+e,
g=c+d.plotWidth/2,h=e+d.plotHeight/2,n=Number.MAX_VALUE,u=-Number.MAX_VALUE,f=Number.MAX_VALUE,p=-Number.MAX_VALUE,q,l=1;q=[{x:c,y:e,z:0},{x:c,y:e,z:k}];w([0,1],function(a){q.push({x:b,y:q[a].y,z:q[a].z})});w([0,1,2,3],function(e){q.push({x:q[e].x,y:a,z:q[e].z})});q=r(q,d,!1);w(q,function(a){n=Math.min(n,a.x);u=Math.max(u,a.x);f=Math.min(f,a.y);p=Math.max(p,a.y)});c>n&&(l=Math.min(l,1-Math.abs((c+g)/(n+g))%1));b<u&&(l=Math.min(l,(b-g)/(u-g)));e>f&&(l=0>f?Math.min(l,(e+h)/(-f+e+h)):Math.min(l,1-(e+
h)/(f+h)%1));a<p&&(l=Math.min(l,Math.abs((a-h)/(p-h))));return l}var v=d.Chart,w=d.each,x=d.merge,r=d.perspective,m=d.pick,f=d.wrap;v.prototype.is3d=function(){return this.options.chart.options3d&&this.options.chart.options3d.enabled};v.prototype.propsRequireDirtyBox.push("chart.options3d");v.prototype.propsRequireUpdateSeries.push("chart.options3d");d.wrap(d.Chart.prototype,"isInsidePlot",function(d){return this.is3d()||d.apply(this,[].slice.call(arguments,1))});var l=d.getOptions();x(!0,l,{chart:{options3d:{enabled:!1,
alpha:0,beta:0,depth:100,fitToPlot:!0,viewDistance:25,axisLabelPosition:"default",frame:{visible:"default",size:1,bottom:{},top:{},left:{},right:{},back:{},front:{}}}}});f(v.prototype,"getContainer",function(d){d.apply(this,[].slice.call(arguments,1));this.renderer.definition({tagName:"style",textContent:".highcharts-3d-top{filter: url(#highcharts-brighter)}\n.highcharts-3d-side{filter: url(#highcharts-darker)}\n"})});f(v.prototype,"setClassName",function(d){d.apply(this,[].slice.call(arguments,1));
this.is3d()&&(this.container.className+=" highcharts-3d-chart")});d.wrap(d.Chart.prototype,"setChartSize",function(d){var k=this.options.chart.options3d;d.apply(this,[].slice.call(arguments,1));if(this.is3d()){var c=this.inverted,b=this.clipBox,e=this.margin;b[c?"y":"x"]=-(e[3]||0);b[c?"x":"y"]=-(e[0]||0);b[c?"height":"width"]=this.chartWidth+(e[3]||0)+(e[1]||0);b[c?"width":"height"]=this.chartHeight+(e[0]||0)+(e[2]||0);this.scale3d=1;!0===k.fitToPlot&&(this.scale3d=t(this,k.depth))}});f(v.prototype,
"redraw",function(d){this.is3d()&&(this.isDirtyBox=!0,this.frame3d=this.get3dFrame());d.apply(this,[].slice.call(arguments,1))});f(v.prototype,"render",function(d){this.is3d()&&(this.frame3d=this.get3dFrame());d.apply(this,[].slice.call(arguments,1))});f(v.prototype,"renderSeries",function(d){var k=this.series.length;if(this.is3d())for(;k--;)d=this.series[k],d.translate(),d.render();else d.call(this)});f(v.prototype,"drawChartBox",function(u){if(this.is3d()){var k=this.renderer,c=this.options.chart.options3d,
b=this.get3dFrame(),e=this.plotLeft,a=this.plotLeft+this.plotWidth,g=this.plotTop,h=this.plotTop+this.plotHeight,c=c.depth,n=e-(b.left.visible?b.left.size:0),f=a+(b.right.visible?b.right.size:0),l=g-(b.top.visible?b.top.size:0),p=h+(b.bottom.visible?b.bottom.size:0),q=0-(b.front.visible?b.front.size:0),m=c+(b.back.visible?b.back.size:0),r=this.hasRendered?"animate":"attr";this.frame3d=b;this.frameShapes||(this.frameShapes={bottom:k.polyhedron().add(),top:k.polyhedron().add(),left:k.polyhedron().add(),
right:k.polyhedron().add(),back:k.polyhedron().add(),front:k.polyhedron().add()});this.frameShapes.bottom[r]({"class":"highcharts-3d-frame highcharts-3d-frame-bottom",zIndex:b.bottom.frontFacing?-1E3:1E3,faces:[{fill:d.color(b.bottom.color).brighten(.1).get(),vertexes:[{x:n,y:p,z:q},{x:f,y:p,z:q},{x:f,y:p,z:m},{x:n,y:p,z:m}],enabled:b.bottom.visible},{fill:d.color(b.bottom.color).brighten(.1).get(),vertexes:[{x:e,y:h,z:c},{x:a,y:h,z:c},{x:a,y:h,z:0},{x:e,y:h,z:0}],enabled:b.bottom.visible},{fill:d.color(b.bottom.color).brighten(-.1).get(),
vertexes:[{x:n,y:p,z:q},{x:n,y:p,z:m},{x:e,y:h,z:c},{x:e,y:h,z:0}],enabled:b.bottom.visible&&!b.left.visible},{fill:d.color(b.bottom.color).brighten(-.1).get(),vertexes:[{x:f,y:p,z:m},{x:f,y:p,z:q},{x:a,y:h,z:0},{x:a,y:h,z:c}],enabled:b.bottom.visible&&!b.right.visible},{fill:d.color(b.bottom.color).get(),vertexes:[{x:f,y:p,z:q},{x:n,y:p,z:q},{x:e,y:h,z:0},{x:a,y:h,z:0}],enabled:b.bottom.visible&&!b.front.visible},{fill:d.color(b.bottom.color).get(),vertexes:[{x:n,y:p,z:m},{x:f,y:p,z:m},{x:a,y:h,
z:c},{x:e,y:h,z:c}],enabled:b.bottom.visible&&!b.back.visible}]});this.frameShapes.top[r]({"class":"highcharts-3d-frame highcharts-3d-frame-top",zIndex:b.top.frontFacing?-1E3:1E3,faces:[{fill:d.color(b.top.color).brighten(.1).get(),vertexes:[{x:n,y:l,z:m},{x:f,y:l,z:m},{x:f,y:l,z:q},{x:n,y:l,z:q}],enabled:b.top.visible},{fill:d.color(b.top.color).brighten(.1).get(),vertexes:[{x:e,y:g,z:0},{x:a,y:g,z:0},{x:a,y:g,z:c},{x:e,y:g,z:c}],enabled:b.top.visible},{fill:d.color(b.top.color).brighten(-.1).get(),
vertexes:[{x:n,y:l,z:m},{x:n,y:l,z:q},{x:e,y:g,z:0},{x:e,y:g,z:c}],enabled:b.top.visible&&!b.left.visible},{fill:d.color(b.top.color).brighten(-.1).get(),vertexes:[{x:f,y:l,z:q},{x:f,y:l,z:m},{x:a,y:g,z:c},{x:a,y:g,z:0}],enabled:b.top.visible&&!b.right.visible},{fill:d.color(b.top.color).get(),vertexes:[{x:n,y:l,z:q},{x:f,y:l,z:q},{x:a,y:g,z:0},{x:e,y:g,z:0}],enabled:b.top.visible&&!b.front.visible},{fill:d.color(b.top.color).get(),vertexes:[{x:f,y:l,z:m},{x:n,y:l,z:m},{x:e,y:g,z:c},{x:a,y:g,z:c}],
enabled:b.top.visible&&!b.back.visible}]});this.frameShapes.left[r]({"class":"highcharts-3d-frame highcharts-3d-frame-left",zIndex:b.left.frontFacing?-1E3:1E3,faces:[{fill:d.color(b.left.color).brighten(.1).get(),vertexes:[{x:n,y:p,z:q},{x:e,y:h,z:0},{x:e,y:h,z:c},{x:n,y:p,z:m}],enabled:b.left.visible&&!b.bottom.visible},{fill:d.color(b.left.color).brighten(.1).get(),vertexes:[{x:n,y:l,z:m},{x:e,y:g,z:c},{x:e,y:g,z:0},{x:n,y:l,z:q}],enabled:b.left.visible&&!b.top.visible},{fill:d.color(b.left.color).brighten(-.1).get(),
vertexes:[{x:n,y:p,z:m},{x:n,y:l,z:m},{x:n,y:l,z:q},{x:n,y:p,z:q}],enabled:b.left.visible},{fill:d.color(b.left.color).brighten(-.1).get(),vertexes:[{x:e,y:g,z:c},{x:e,y:h,z:c},{x:e,y:h,z:0},{x:e,y:g,z:0}],enabled:b.left.visible},{fill:d.color(b.left.color).get(),vertexes:[{x:n,y:p,z:q},{x:n,y:l,z:q},{x:e,y:g,z:0},{x:e,y:h,z:0}],enabled:b.left.visible&&!b.front.visible},{fill:d.color(b.left.color).get(),vertexes:[{x:n,y:l,z:m},{x:n,y:p,z:m},{x:e,y:h,z:c},{x:e,y:g,z:c}],enabled:b.left.visible&&!b.back.visible}]});
this.frameShapes.right[r]({"class":"highcharts-3d-frame highcharts-3d-frame-right",zIndex:b.right.frontFacing?-1E3:1E3,faces:[{fill:d.color(b.right.color).brighten(.1).get(),vertexes:[{x:f,y:p,z:m},{x:a,y:h,z:c},{x:a,y:h,z:0},{x:f,y:p,z:q}],enabled:b.right.visible&&!b.bottom.visible},{fill:d.color(b.right.color).brighten(.1).get(),vertexes:[{x:f,y:l,z:q},{x:a,y:g,z:0},{x:a,y:g,z:c},{x:f,y:l,z:m}],enabled:b.right.visible&&!b.top.visible},{fill:d.color(b.right.color).brighten(-.1).get(),vertexes:[{x:a,
y:g,z:0},{x:a,y:h,z:0},{x:a,y:h,z:c},{x:a,y:g,z:c}],enabled:b.right.visible},{fill:d.color(b.right.color).brighten(-.1).get(),vertexes:[{x:f,y:p,z:q},{x:f,y:l,z:q},{x:f,y:l,z:m},{x:f,y:p,z:m}],enabled:b.right.visible},{fill:d.color(b.right.color).get(),vertexes:[{x:f,y:l,z:q},{x:f,y:p,z:q},{x:a,y:h,z:0},{x:a,y:g,z:0}],enabled:b.right.visible&&!b.front.visible},{fill:d.color(b.right.color).get(),vertexes:[{x:f,y:p,z:m},{x:f,y:l,z:m},{x:a,y:g,z:c},{x:a,y:h,z:c}],enabled:b.right.visible&&!b.back.visible}]});
this.frameShapes.back[r]({"class":"highcharts-3d-frame highcharts-3d-frame-back",zIndex:b.back.frontFacing?-1E3:1E3,faces:[{fill:d.color(b.back.color).brighten(.1).get(),vertexes:[{x:f,y:p,z:m},{x:n,y:p,z:m},{x:e,y:h,z:c},{x:a,y:h,z:c}],enabled:b.back.visible&&!b.bottom.visible},{fill:d.color(b.back.color).brighten(.1).get(),vertexes:[{x:n,y:l,z:m},{x:f,y:l,z:m},{x:a,y:g,z:c},{x:e,y:g,z:c}],enabled:b.back.visible&&!b.top.visible},{fill:d.color(b.back.color).brighten(-.1).get(),vertexes:[{x:n,y:p,
z:m},{x:n,y:l,z:m},{x:e,y:g,z:c},{x:e,y:h,z:c}],enabled:b.back.visible&&!b.left.visible},{fill:d.color(b.back.color).brighten(-.1).get(),vertexes:[{x:f,y:l,z:m},{x:f,y:p,z:m},{x:a,y:h,z:c},{x:a,y:g,z:c}],enabled:b.back.visible&&!b.right.visible},{fill:d.color(b.back.color).get(),vertexes:[{x:e,y:g,z:c},{x:a,y:g,z:c},{x:a,y:h,z:c},{x:e,y:h,z:c}],enabled:b.back.visible},{fill:d.color(b.back.color).get(),vertexes:[{x:n,y:p,z:m},{x:f,y:p,z:m},{x:f,y:l,z:m},{x:n,y:l,z:m}],enabled:b.back.visible}]});this.frameShapes.front[r]({"class":"highcharts-3d-frame highcharts-3d-frame-front",
zIndex:b.front.frontFacing?-1E3:1E3,faces:[{fill:d.color(b.front.color).brighten(.1).get(),vertexes:[{x:n,y:p,z:q},{x:f,y:p,z:q},{x:a,y:h,z:0},{x:e,y:h,z:0}],enabled:b.front.visible&&!b.bottom.visible},{fill:d.color(b.front.color).brighten(.1).get(),vertexes:[{x:f,y:l,z:q},{x:n,y:l,z:q},{x:e,y:g,z:0},{x:a,y:g,z:0}],enabled:b.front.visible&&!b.top.visible},{fill:d.color(b.front.color).brighten(-.1).get(),vertexes:[{x:n,y:l,z:q},{x:n,y:p,z:q},{x:e,y:h,z:0},{x:e,y:g,z:0}],enabled:b.front.visible&&!b.left.visible},
{fill:d.color(b.front.color).brighten(-.1).get(),vertexes:[{x:f,y:p,z:q},{x:f,y:l,z:q},{x:a,y:g,z:0},{x:a,y:h,z:0}],enabled:b.front.visible&&!b.right.visible},{fill:d.color(b.front.color).get(),vertexes:[{x:a,y:g,z:0},{x:e,y:g,z:0},{x:e,y:h,z:0},{x:a,y:h,z:0}],enabled:b.front.visible},{fill:d.color(b.front.color).get(),vertexes:[{x:f,y:p,z:q},{x:n,y:p,z:q},{x:n,y:l,z:q},{x:f,y:l,z:q}],enabled:b.front.visible}]})}return u.apply(this,[].slice.call(arguments,1))});v.prototype.retrieveStacks=function(d){var k=
this.series,c={},b,e=1;w(this.series,function(a){b=m(a.options.stack,d?0:k.length-1-a.index);c[b]?c[b].series.push(a):(c[b]={series:[a],position:e},e++)});c.totalStacks=e+1;return c};v.prototype.get3dFrame=function(){var f=this,k=f.options.chart.options3d,c=k.frame,b=f.plotLeft,e=f.plotLeft+f.plotWidth,a=f.plotTop,g=f.plotTop+f.plotHeight,h=k.depth,n=d.shapeArea3d([{x:b,y:g,z:h},{x:e,y:g,z:h},{x:e,y:g,z:0},{x:b,y:g,z:0}],f),l=d.shapeArea3d([{x:b,y:a,z:0},{x:e,y:a,z:0},{x:e,y:a,z:h},{x:b,y:a,z:h}],
f),t=d.shapeArea3d([{x:b,y:a,z:0},{x:b,y:a,z:h},{x:b,y:g,z:h},{x:b,y:g,z:0}],f),p=d.shapeArea3d([{x:e,y:a,z:h},{x:e,y:a,z:0},{x:e,y:g,z:0},{x:e,y:g,z:h}],f),q=d.shapeArea3d([{x:b,y:g,z:0},{x:e,y:g,z:0},{x:e,y:a,z:0},{x:b,y:a,z:0}],f),v=d.shapeArea3d([{x:b,y:a,z:h},{x:e,y:a,z:h},{x:e,y:g,z:h},{x:b,y:g,z:h}],f),x=!1,A=!1,F=!1,H=!1;w([].concat(f.xAxis,f.yAxis,f.zAxis),function(a){a&&(a.horiz?a.opposite?A=!0:x=!0:a.opposite?H=!0:F=!0)});var E=function(a,e,b){for(var c=["size","color","visible"],d={},
g=0;g<c.length;g++)for(var h=c[g],k=0;k<a.length;k++)if("object"===typeof a[k]){var f=a[k][h];if(void 0!==f&&null!==f){d[h]=f;break}}a=b;!0===d.visible||!1===d.visible?a=d.visible:"auto"===d.visible&&(a=0<=e);return{size:m(d.size,1),color:m(d.color,"none"),frontFacing:0<e,visible:a}},c={bottom:E([c.bottom,c.top,c],n,x),top:E([c.top,c.bottom,c],l,A),left:E([c.left,c.right,c.side,c],t,F),right:E([c.right,c.left,c.side,c],p,H),back:E([c.back,c.front,c],v,!0),front:E([c.front,c.back,c],q,!1)};"auto"===
k.axisLabelPosition?(p=function(a,e){return a.visible!==e.visible||a.visible&&e.visible&&a.frontFacing!==e.frontFacing},k=[],p(c.left,c.front)&&k.push({y:(a+g)/2,x:b,z:0}),p(c.left,c.back)&&k.push({y:(a+g)/2,x:b,z:h}),p(c.right,c.front)&&k.push({y:(a+g)/2,x:e,z:0}),p(c.right,c.back)&&k.push({y:(a+g)/2,x:e,z:h}),n=[],p(c.bottom,c.front)&&n.push({x:(b+e)/2,y:g,z:0}),p(c.bottom,c.back)&&n.push({x:(b+e)/2,y:g,z:h}),l=[],p(c.top,c.front)&&l.push({x:(b+e)/2,y:a,z:0}),p(c.top,c.back)&&l.push({x:(b+e)/2,
y:a,z:h}),t=[],p(c.bottom,c.left)&&t.push({z:(0+h)/2,y:g,x:b}),p(c.bottom,c.right)&&t.push({z:(0+h)/2,y:g,x:e}),g=[],p(c.top,c.left)&&g.push({z:(0+h)/2,y:a,x:b}),p(c.top,c.right)&&g.push({z:(0+h)/2,y:a,x:e}),b=function(a,e,b){if(0===a.length)return null;if(1===a.length)return a[0];for(var c=0,d=r(a,f,!1),g=1;g<d.length;g++)b*d[g][e]>b*d[c][e]?c=g:b*d[g][e]===b*d[c][e]&&d[g].z<d[c].z&&(c=g);return a[c]},c.axes={y:{left:b(k,"x",-1),right:b(k,"x",1)},x:{top:b(l,"y",-1),bottom:b(n,"y",1)},z:{top:b(g,
"y",-1),bottom:b(t,"y",1)}}):c.axes={y:{left:{x:b,z:0},right:{x:e,z:0}},x:{top:{y:a,z:0},bottom:{y:g,z:0}},z:{top:{x:F?e:b,y:a},bottom:{x:F?e:b,y:g}}};return c}})(A);(function(d){function t(e,a){if(e.chart.is3d()&&"colorAxis"!==e.coll){var b=e.chart,c=b.frame3d,d=b.plotLeft,k=b.plotWidth+d,f=b.plotTop,b=b.plotHeight+f,p=0,m=0;a=e.swapZ({x:a.x,y:a.y,z:0});if(e.isZAxis)if(e.opposite){if(null===c.axes.z.top)return{};m=a.y-f;a.x=c.axes.z.top.x;a.y=c.axes.z.top.y}else{if(null===c.axes.z.bottom)return{};
m=a.y-b;a.x=c.axes.z.bottom.x;a.y=c.axes.z.bottom.y}else if(e.horiz)if(e.opposite){if(null===c.axes.x.top)return{};m=a.y-f;a.y=c.axes.x.top.y;a.z=c.axes.x.top.z}else{if(null===c.axes.x.bottom)return{};m=a.y-b;a.y=c.axes.x.bottom.y;a.z=c.axes.x.bottom.z}else if(e.opposite){if(null===c.axes.y.right)return{};p=a.x-k;a.x=c.axes.y.right.x;a.z=c.axes.y.right.z}else{if(null===c.axes.y.left)return{};p=a.x-d;a.x=c.axes.y.left.x;a.z=c.axes.y.left.z}a=l([a],e.chart)[0];a.x+=p;a.y+=m}return a}var v,w=d.Axis,
x=d.Chart,r=d.each,m=d.extend,f=d.merge,l=d.perspective,u=d.pick,k=d.splat,c=d.Tick,b=d.wrap;b(w.prototype,"setOptions",function(e,a){e.call(this,a);this.chart.is3d()&&"colorAxis"!==this.coll&&(e=this.options,e.tickWidth=u(e.tickWidth,0),e.gridLineWidth=u(e.gridLineWidth,1))});b(w.prototype,"getPlotLinePath",function(e){var a=e.apply(this,[].slice.call(arguments,1));if(!this.chart.is3d()||"colorAxis"===this.coll||null===a)return a;var b=this.chart,c=b.options.chart.options3d,c=this.isZAxis?b.plotWidth:
c.depth,b=b.frame3d,a=[this.swapZ({x:a[1],y:a[2],z:0}),this.swapZ({x:a[1],y:a[2],z:c}),this.swapZ({x:a[4],y:a[5],z:0}),this.swapZ({x:a[4],y:a[5],z:c})],c=[];this.horiz?(this.isZAxis?(b.left.visible&&c.push(a[0],a[2]),b.right.visible&&c.push(a[1],a[3])):(b.front.visible&&c.push(a[0],a[2]),b.back.visible&&c.push(a[1],a[3])),b.top.visible&&c.push(a[0],a[1]),b.bottom.visible&&c.push(a[2],a[3])):(b.front.visible&&c.push(a[0],a[2]),b.back.visible&&c.push(a[1],a[3]),b.left.visible&&c.push(a[0],a[1]),b.right.visible&&
c.push(a[2],a[3]));c=l(c,this.chart,!1);return this.chart.renderer.toLineSegments(c)});b(w.prototype,"getLinePath",function(b){return this.chart.is3d()?[]:b.apply(this,[].slice.call(arguments,1))});b(w.prototype,"getPlotBandPath",function(b){if(!this.chart.is3d()||"colorAxis"===this.coll)return b.apply(this,[].slice.call(arguments,1));var a=arguments,e=a[2],c=[],a=this.getPlotLinePath(a[1]),e=this.getPlotLinePath(e);if(a&&e)for(var d=0;d<a.length;d+=6)c.push("M",a[d+1],a[d+2],"L",a[d+4],a[d+5],"L",
e[d+4],e[d+5],"L",e[d+1],e[d+2],"Z");return c});b(c.prototype,"getMarkPath",function(b){var a=b.apply(this,[].slice.call(arguments,1)),a=[t(this.axis,{x:a[1],y:a[2],z:0}),t(this.axis,{x:a[4],y:a[5],z:0})];return this.axis.chart.renderer.toLineSegments(a)});b(c.prototype,"getLabelPosition",function(b){var a=b.apply(this,[].slice.call(arguments,1));return t(this.axis,a)});d.wrap(w.prototype,"getTitlePosition",function(b){var a=b.apply(this,[].slice.call(arguments,1));return t(this,a)});b(w.prototype,
"drawCrosshair",function(b){var a=arguments;this.chart.is3d()&&a[2]&&(a[2]={plotX:a[2].plotXold||a[2].plotX,plotY:a[2].plotYold||a[2].plotY});b.apply(this,[].slice.call(a,1))});b(w.prototype,"destroy",function(b){r(["backFrame","bottomFrame","sideFrame"],function(a){this[a]&&(this[a]=this[a].destroy())},this);b.apply(this,[].slice.call(arguments,1))});w.prototype.swapZ=function(b,a){return this.isZAxis?(a=a?0:this.chart.plotLeft,{x:a+b.z,y:b.y,z:b.x-a}):b};v=d.ZAxis=function(){this.init.apply(this,
arguments)};m(v.prototype,w.prototype);m(v.prototype,{isZAxis:!0,setOptions:function(b){b=f({offset:0,lineWidth:0},b);w.prototype.setOptions.call(this,b);this.coll="zAxis"},setAxisSize:function(){w.prototype.setAxisSize.call(this);this.width=this.len=this.chart.options.chart.options3d.depth;this.right=this.chart.chartWidth-this.width-this.left},getSeriesExtremes:function(){var b=this,a=b.chart;b.hasVisibleSeries=!1;b.dataMin=b.dataMax=b.ignoreMinPadding=b.ignoreMaxPadding=null;b.buildStacks&&b.buildStacks();
r(b.series,function(c){if(c.visible||!a.options.chart.ignoreHiddenSeries)b.hasVisibleSeries=!0,c=c.zData,c.length&&(b.dataMin=Math.min(u(b.dataMin,c[0]),Math.min.apply(null,c)),b.dataMax=Math.max(u(b.dataMax,c[0]),Math.max.apply(null,c)))})}});b(x.prototype,"getAxes",function(b){var a=this,c=this.options,c=c.zAxis=k(c.zAxis||{});b.call(this);a.is3d()&&(this.zAxis=[],r(c,function(b,c){b.index=c;b.isX=!0;(new v(a,b)).setScale()}))})})(A);(function(d){var t=d.each,v=d.perspective,w=d.pick,x=d.Series,
r=d.seriesTypes,m=d.inArray,f=d.svg,l=d.wrap;l(r.column.prototype,"translate",function(d){d.apply(this,[].slice.call(arguments,1));if(this.chart.is3d()){var k=this,c=k.chart,b=k.options,e=b.depth||25,a=(b.stacking?b.stack||0:k.index)*(e+(b.groupZPadding||1)),g=k.borderWidth%2?.5:0;c.inverted&&!k.yAxis.reversed&&(g*=-1);!1!==b.grouping&&(a=0);a+=b.groupZPadding||1;t(k.data,function(b){if(null!==b.y){var d=b.shapeArgs,f=b.tooltipPos,h;t([["x","width"],["y","height"]],function(a){h=d[a[0]]-g;0>h&&(d[a[1]]+=
d[a[0]]+g,d[a[0]]=-g,h=0);h+d[a[1]]>k[a[0]+"Axis"].len&&0!==d[a[1]]&&(d[a[1]]=k[a[0]+"Axis"].len-d[a[0]]);if(0!==d[a[1]]&&(d[a[0]]>=k[a[0]+"Axis"].len||d[a[0]]+d[a[1]]<=g))for(var b in d)d[b]=0});b.shapeType="cuboid";d.z=a;d.depth=e;d.insidePlotArea=!0;f=v([{x:f[0],y:f[1],z:a}],c,!0)[0];b.tooltipPos=[f.x,f.y]}});k.z=a}});l(r.column.prototype,"animate",function(d){if(this.chart.is3d()){var k=arguments[1],c=this.yAxis,b=this,e=this.yAxis.reversed;f&&(k?t(b.data,function(a){null!==a.y&&(a.height=a.shapeArgs.height,
a.shapey=a.shapeArgs.y,a.shapeArgs.height=1,e||(a.shapeArgs.y=a.stackY?a.plotY+c.translate(a.stackY):a.plotY+(a.negative?-a.height:a.height)))}):(t(b.data,function(a){null!==a.y&&(a.shapeArgs.height=a.height,a.shapeArgs.y=a.shapey,a.graphic&&a.graphic.animate(a.shapeArgs,b.options.animation))}),this.drawDataLabels(),b.animate=null))}else d.apply(this,[].slice.call(arguments,1))});l(r.column.prototype,"plotGroup",function(d,f,c,b,e,a){this.chart.is3d()&&a&&!this[f]&&(this[f]=a,a.attr(this.getPlotBox()),
this[f].survive=!0);return d.apply(this,Array.prototype.slice.call(arguments,1))});l(r.column.prototype,"setVisible",function(d,f){var c=this,b;c.chart.is3d()&&t(c.data,function(e){b=(e.visible=e.options.visible=f=void 0===f?!e.visible:f)?"visible":"hidden";c.options.data[m(e,c.data)]=e.options;e.graphic&&e.graphic.attr({visibility:b})});d.apply(this,Array.prototype.slice.call(arguments,1))});l(r.column.prototype,"init",function(d){d.apply(this,[].slice.call(arguments,1));if(this.chart.is3d()){var f=
this.options,c=f.grouping,b=f.stacking,e=w(this.yAxis.options.reversedStacks,!0),a=0;if(void 0===c||c){c=this.chart.retrieveStacks(b);a=f.stack||0;for(b=0;b<c[a].series.length&&c[a].series[b]!==this;b++);a=10*(c.totalStacks-c[a].position)+(e?b:-b);this.xAxis.reversed||(a=10*c.totalStacks-a)}f.zIndex=a}});l(x.prototype,"alignDataLabel",function(d){if(this.chart.is3d()&&("column"===this.type||"columnrange"===this.type)){var f=arguments[4],c={x:f.x,y:f.y,z:this.z},c=v([c],this.chart,!0)[0];f.x=c.x;f.y=
c.y}d.apply(this,[].slice.call(arguments,1))});l(d.StackItem.prototype,"getStackBox",function(f,k){var c=f.apply(this,[].slice.call(arguments,1));if(k.is3d()){var b={x:c.x,y:c.y,z:0},b=d.perspective([b],k,!0)[0];c.x=b.x;c.y=b.y}return c})})(A);(function(d){var t=d.deg2rad,v=d.each,w=d.seriesTypes,x=d.svg;d=d.wrap;d(w.pie.prototype,"translate",function(d){d.apply(this,[].slice.call(arguments,1));if(this.chart.is3d()){var m=this,f=m.options,l=f.depth||0,r=m.chart.options.chart.options3d,k=r.alpha,c=
r.beta,b=f.stacking?(f.stack||0)*l:m._i*l,b=b+l/2;!1!==f.grouping&&(b=0);v(m.data,function(e){var a=e.shapeArgs;e.shapeType="arc3d";a.z=b;a.depth=.75*l;a.alpha=k;a.beta=c;a.center=m.center;a=(a.end+a.start)/2;e.slicedTranslation={translateX:Math.round(Math.cos(a)*f.slicedOffset*Math.cos(k*t)),translateY:Math.round(Math.sin(a)*f.slicedOffset*Math.cos(k*t))}})}});d(w.pie.prototype.pointClass.prototype,"haloPath",function(d){var m=arguments;return this.series.chart.is3d()?[]:d.call(this,m[1])});d(w.pie.prototype,
"drawPoints",function(d){d.apply(this,[].slice.call(arguments,1));this.chart.is3d()&&v(this.points,function(d){var f=d.graphic;if(f)f[d.y&&d.visible?"show":"hide"]()})});d(w.pie.prototype,"drawDataLabels",function(d){if(this.chart.is3d()){var m=this.chart.options.chart.options3d;v(this.data,function(d){var f=d.shapeArgs,r=f.r,k=(f.start+f.end)/2,c=d.labelPos,b=-r*(1-Math.cos((f.alpha||m.alpha)*t))*Math.sin(k),e=r*(Math.cos((f.beta||m.beta)*t)-1)*Math.cos(k);v([0,2,4],function(a){c[a]+=e;c[a+1]+=b})})}d.apply(this,
[].slice.call(arguments,1))});d(w.pie.prototype,"addPoint",function(d){d.apply(this,[].slice.call(arguments,1));this.chart.is3d()&&this.update(this.userOptions,!0)});d(w.pie.prototype,"animate",function(d){if(this.chart.is3d()){var m=arguments[1],f=this.options.animation,l=this.center,r=this.group,k=this.markerGroup;x&&(!0===f&&(f={}),m?(r.oldtranslateX=r.translateX,r.oldtranslateY=r.translateY,m={translateX:l[0],translateY:l[1],scaleX:.001,scaleY:.001},r.attr(m),k&&(k.attrSetters=r.attrSetters,k.attr(m))):
(m={translateX:r.oldtranslateX,translateY:r.oldtranslateY,scaleX:1,scaleY:1},r.animate(m,f),k&&k.animate(m,f),this.animate=null))}else d.apply(this,[].slice.call(arguments,1))})})(A);(function(d){var t=d.perspective,v=d.pick,w=d.Point,x=d.seriesTypes,r=d.wrap;r(x.scatter.prototype,"translate",function(d){d.apply(this,[].slice.call(arguments,1));if(this.chart.is3d()){var f=this.chart,l=v(this.zAxis,f.options.zAxis[0]),m=[],k,c,b;for(b=0;b<this.data.length;b++)k=this.data[b],c=l.isLog&&l.val2lin?l.val2lin(k.z):
k.z,k.plotZ=l.translate(c),k.isInside=k.isInside?c>=l.min&&c<=l.max:!1,m.push({x:k.plotX,y:k.plotY,z:k.plotZ});f=t(m,f,!0);for(b=0;b<this.data.length;b++)k=this.data[b],l=f[b],k.plotXold=k.plotX,k.plotYold=k.plotY,k.plotZold=k.plotZ,k.plotX=l.x,k.plotY=l.y,k.plotZ=l.z}});r(x.scatter.prototype,"init",function(d,f,l){f.is3d()&&(this.axisTypes=["xAxis","yAxis","zAxis"],this.pointArrayMap=["x","y","z"],this.parallelArrays=["x","y","z"],this.directTouch=!0);d=d.apply(this,[f,l]);this.chart.is3d()&&(this.tooltipOptions.pointFormat=
this.userOptions.tooltip?this.userOptions.tooltip.pointFormat||"x: \x3cb\x3e{point.x}\x3c/b\x3e\x3cbr/\x3ey: \x3cb\x3e{point.y}\x3c/b\x3e\x3cbr/\x3ez: \x3cb\x3e{point.z}\x3c/b\x3e\x3cbr/\x3e":"x: \x3cb\x3e{point.x}\x3c/b\x3e\x3cbr/\x3ey: \x3cb\x3e{point.y}\x3c/b\x3e\x3cbr/\x3ez: \x3cb\x3e{point.z}\x3c/b\x3e\x3cbr/\x3e");return d});r(x.scatter.prototype,"pointAttribs",function(m,f){var l=m.apply(this,[].slice.call(arguments,1));this.chart.is3d()&&f&&(l.zIndex=d.pointCameraDistance(f,this.chart));return l});
r(w.prototype,"applyOptions",function(d){var f=d.apply(this,[].slice.call(arguments,1));this.series.chart.is3d()&&void 0===f.z&&(f.z=0);return f})})(A)});