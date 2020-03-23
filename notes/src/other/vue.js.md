# Vuejs

## 一，vue基础

### v-if，v-else，v-else-if

 通过指令使用，在DOM中

```html
<div id="app">
    <span v-if="bool"> 
        <!-- v-if后的bool为布尔类型，通过它是false或者true来决定标签内的内容是否显示 
v-if后如果没false的话，就不会渲染其中的内容
-->
        {{msg}}
    </span>
    <span v-else>
        {{msg}},bool为false是显示
    </span>
    <span v-if="grade>90">一级</span>
    <span v-else-if="grade>80">二级</span>
    <span v-else-if="grade>70">三级</span>
    <span v-else="grade>60">四级</span>
</div>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            msg:"liu",
            data:"wen",
            grade:62,
            bool: false
        },
        methods: {}
    });
</script>
```

v-if用于对单个条件符合判断，v-else配合v-if使用，用于对两个条件的判断

v-else-if 配合v-if使用，用于多条件判断，相当于else if 判断结尾可使用 v-else

当v-else-if使用较多时，建议修改为计算属性:

```html
<div id="app">
    <span>{{result}}</span>
</div>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            bool: false,
            msg: 62
        },
        methods: {},
        computed: {
            result(){
                let grade = "";
                if(this.msg > 90){
                    grade = "一级"
                }else if(this.msg > 80){
                    grade = "二级" 
                }else if(this.grade > 70){
                    grade = "三级"
                }else{
                    grade = "四级"
                }
                return grade
            }
        },
    });
</script>
```

### v-show

决定DOM要不要渲染

```html
<div id="app">
    <span v-show="msg">{{data}}</span>
    <!--v-show后如果为false的话是在后面加上style="dispalg:none"来控制是否显示-->
    <span v-if="msg">{{data}}</span>
    <!-- v-if后如果为false则vue在渲染时就会跳过，不会吧他渲染到页面上-->
</div>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            msg:true,
            data:"数据"
        },
        methods: {}
    });
</script>
```

### v-for 遍历

嵌入DOM中进行遍历

循环数组：

```html
<div id="app">
    <ul>
        <li v-for="item in msg">{{item}}</li>
        <!--这个样子循环会获取msg这个字符串的内容，item中放的是字符串内容-->
      <li v-for="(item, i) in msg">{{item}}</li>
        <!--这个样子中item是存放的数组内容，i中存放的是字符串在数组中的下标-->
    </ul>
</div>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            msg:['liu','wen','hao']
        },
        methods: {}
    });
</script>
```

循环对象

~~~html
<body>
  <div id="app">
    <ul>
      <li v-for="item in message">{{item}}</li>
        <!--循环对象，item存放的是对象的value-->
      <li v-for="(value, key) in message">{{value}}----{{key}}</li>
        <!--小括号中先存放value 再存放key-->
    </ul>
  </div>
  <script>
    var vm = new Vue({
      el: '#app',
      data: {
        message: {
          name : 'lwh',
          age : 110,
          sex : 'male'
        }
      },
      methods: {}
    });
~~~

v-for可以绑定一个key，key的选取最好是针对这个元素固定不变的

js几个函数：

filter：对数组等集合进行过滤，

```javascript
let num = [1,2,3,4,5]
let arr = num.filter(function(n){
    return n>0;
})
//根据返回值是true或是false来决定是否将num中当遍历的n放入新的数组arr中
```

map：对数组等集合其中的数据进行操作

```javascript
let num = [1,2,3,4,5]
let arr = num.map(function(n){
    return n * 2;
})
//对num进行遍历，进行一定的操作后把返回值放入新的数组arr中
```

reduce：对数组等集合中的所有元素进行汇总

```javascript
let num = [1,2,3,4,5]
let arr = num.reduce(function(preValue, n){
    return preValue + n;
}, 0)
//第二个参数，初始化值
//第一次循环 perVlaue 为 0; n 为 num 的第一个值
//第二次循环 perValue 为 return 后的值, n 为 num 的第二个值
//.....
```

### v-model

表单的双向绑定

```html
<div id="app">
    <input type="text" v-model="msg">
    {{msg}}
  </div>
  <script>
    var vm = new Vue({
      el: '#app',
      data: {
        msg: "vuevuevue"
      },
      methods: {}
    });
  </script>
```

v-model 和 radio

```html
<div id="app">
    <label for="male">
        <input type="radio" name="sex" id="male" value="男" v-model="sex">男
    </label>
    <label for="female">
        <input type="radio" name="sex" id="female" value="女" v-model="sex">女
    </label>
    <span>{{sex}}</span>
</div>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            sex:'男'
        },
        methods: {}
    });
</script>
```

v-model 结合 select

```html
<div id="app">
    <!-- 选择单个 -->
    <select name="selected" v-model="msg">
        <option value="药">药</option>
        <option value="水">水</option>
        <option value="糖">糖</option>
        <option value="盐">盐</option>
    </select>
    <h3>{{msg}}</h3>
    <!-- 选择多个 -->
    <select name="selected" v-model="data" multiple>
        <option value="药">药</option>
        <option value="水">水</option>
        <option value="糖">糖</option>
        <option value="盐">盐</option>
    </select>
    <h3>{{data}}</h3>
</div>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            msg:'药',
            data:[]
        },
        methods: {}
    });
</script>
```

### 组件

~~~html
<div id="app">
    <a-component></a-component>
    <a-component></a-component>
    <a-component></a-component>
</div>
<script>
    //创建组件构造器对象 
    const component = Vue.extend({
        template:  `<div>
<h1>123455</h1>
    </div>`
    })
    //注册组件
    //全局组件
    Vue.component('a-component',component)
    var vm = new Vue({
        el: '#app',
        data: {},
        methods: {},
       	//局部组件，只能在id为app的作用域中使用
      	components: {}
    });
</script>
~~~

#### 父子组件

```html
 <div id="app">
      <cpn2></cpn2>
  </div>
  <script>
    //创建组件构造器对象 
    const cpnC1 = Vue.extend({
      template:  `<div>
        <h1>组价1</h1>
      </div>`
    })
    //创建组件构造器对象 
    const cpnC2 = Vue.extend({
      template:  `<div>
        <h1>组件2</h1>
        <span>下边是子组件的内容</span>
        <cpn1></cpn1>
        <span>上边是子组件的内容</span>
      </div>`,
      components : {
        cpn1:cpnC1
      }
    })
    var vm = new Vue({
      el: '#app',
      data: {},
      components: {
        cpn2:cpnC2
      }
    });
  </script>
```

#### 父子组件之间的通信

##### 父传子

```html
<div id="app">
    <cpn :child-key=key :child-msg=msg></cpn>
  </div>
  <template id="cpn">
    <div>
      <h1>组件</h1>
      <span>组件的内容</span>
      <ol>
          <li v-for="item in childKey">{{ item }}</li>
      </ol>
      <span>{{ childMsg }}</span>
    </div>
  </template>
  <script>
    const cpn = {
      template : '#cpn',
      // props : ['childKey','childMsg']
      props:{
        childKey:{
          type:String,
          // default:"liu"
          default(){
            return "liu";
          }
        },
        childMsg:{
          type:Array
        }
      }
    } 
    const vm = new Vue({
      el: '#app',
      data: {
        msg:"lwh",
        key:['糖','猫','税','线']
      },
      components: {
        cpn
      }
    });
  </script>
```

##### 子组件传值到父组件

```html
<body>
  <div id="app">
    <cpn @itemclick="cpnClick"></cpn>
  </div>
  <template id="cpn">
    <div>
      <h1>组件</h1>
      <span>组件的内容</span>
      <!-- @click 点击事件，在子组件中向父组件传递信息 -->
      <!-- 在子组件的声明中获取值-->
      <button v-for="item in categories" @click=btnClick(item)>
        {{item.name}}
      </button>
    </div>
  </template>
  <script>
    const cpn = {
      template : '#cpn',
      data(){
        return {
          categories:[
            {id:'aaa',name:'分类一'},
            {id:'bbb',name:'分类二'},
            {id:'ccc',name:'分类三'},
            {id:'ddd',name:'分类四'},
          ]
        }
      },
      methods: {
        btnClick(item){
          // console.log(item);
          //子传父，通过$emit方法传递点击事件
          this.$emit('itemclick', item)
          
        }
      }, 
    } 
    const vm = new Vue({
      el: '#app',
      data: {
        msg:"lwh",
        key:['糖','猫','税','线']
      },
      components: {
        cpn
      },
      methods: {
        cpnClick(item){
          console.log(item);
        }
      },
    });
  </script>
</body>
```

#### 计算属性

1. 计算属性的本质
   - fullname：{set(),get()}
2. 计算属性和method对比
   - 计算属性在多次使用时，只会调用一次
   - 有缓存

#### 事件监听

1. 事件监听基本使用
   - @：语法糖
2. 参数问题
   - btnClick
   - btnClick(event)
   - btnClick(abc，event) ——>$event

#### 修饰符

1. stop
2. prevent
3. .enter
4. .once
5. .native

#### 条件判断

1. v-if
2. v-else-if
3. v-else

#### v-show

v-show和v-if 的区别

## 二，组件化开发

父组件访问子组件，

1. $children     
2. $refs   reference（引用）

子组件访问父组件

1. $parent
2. $root

插槽 slot 

## 三，前端模块化

CommonJS

AMD（ require.js）

CMD ( sea.js )

[以上三个的详细介绍](https://www.jianshu.com/p/d67bc79976e6)

[前端模块化详解](https://blog.csdn.net/ThisOnly/article/details/91454294)

 AMD定义一个模块：使用define来定义，用require来使用模块 

~~~javascript
// AMD
/*
	目录
		admDir
			a.js
			index.js
*/
// AMD定义  a.js
    define ({
        a: 1,
        b: 2,
        add: function(){}
    })
// AMD引用 index.js
	require([./a.js],function( moduleA ){
        //moduleA指的就是定义来的对象
	})
~~~

CMD定义模块：使用define来定义，用require来使用模块

    1//CMD
    /*
    	目录结构
    		b.js
    		index.js
    */
    
    // 模块定义  b.js
    	define(function(require, exports, module) {
    
        	// 模块代码
        
        });
    
    //模块引用 index.js
    	require('./b.js',function( moduleB ){
            //moduleB就是b模块中导出的内容
    	})

Common.js：
Node.js使用了Common.js的规范

易错的理解：
common.js是属于node的 ×
node属于common.js ×

    //common.js
    /*
    	目录结构：
    		name.js
    		index.js
    */
    //模块的定义 name.js
    	const nameObj = {
            name: 'zhan san '
    	}
    //模块的导出  name.js
    	module.exports = nameObj
    //模块的引用
    	const nameObj = require('./name.js')

Node.js中Common.js规范的使用有三种类型：
第一种：内置模块（ 内置模块指的是挂载在Node.js全局对象身上的api ）
内置模块可以直接使用 ( require中直接书写模块名称 )
格式：const/let/var 变量名 = require( 模块名称 )
第二种：自定义模块
1.模块的定义（创建模块）

    //举例
    const student = {
        id: 1,
        name: 'li si'
    }
    const fn = function(){}

2.模块的导出

```
// 第一种导出
module.exports = student // 安全性不高  默认导出一个
//第二种导出
module.exports = { //批量导出，按需引用
    student,fn
}
```

3.模块的引用

    // 这种引用对应第一种导出
    const student = require('./xxx.js')
    // 这种引用对应第二种导出
    const { student , fn } = require( './xxx.js ' )

注意：在自定义模块引用时，require一定要写好路径

第三种：第三方模块（别人封装好的模块）
格式：var/let/const 变量名 = require( 模块名称 )

    $ npm init -y//创建package.json文件



    //-D  === --save-dev 下载模块
    //举例
    
    cnpm  i  jquery -D  === cnpm i jquery --save-dev  // 开发环境下使用
    cnpm i jquery -S ===  cnpm i jquery --save // 生产环境下使用

1. 别人已经封装好的模块
2. 这个模块具备一些特定的功能
3. 这些模块存放在  [www.npmjs.com](http://www.npmjs.com) 这个网站中
    这些模块的文档也记录在内

格式： var/let/const 变量名 = require( 模块名称 )

总结：
第一步，使用npm/cnpm 安装
第二步，在文件中引入
第三步，在www.npmjs.com这个网站中找到这个模块的文档，根据文档来使用

ES6

ES模块化导入导出

- export 和 export default

  两个导出，下面我们讲讲它们的区别

  export与export default均可用于导出常量、函数、文件、模块等
  在一个文件或模块中，export、import可以有多个，export default仅有一个
  通过export方式导出，在导入时要加{ }，export default则不需要
  export能直接导出变量表达式，export default不行。

## 四，webpack

#### 1，什么是webpack？

1. 官方解释：从本质上讲，webpack是一个现代的JavaScript应用的静态**模块打包**工具。
2. 包括两个核心的功能：模块+打包 
3. webpack其中的一个和兴就是让我们可能进行模块化开发，并且会帮助我们处理模块间的依赖关系
4. 不仅仅是JavaScript文件，我们的CSS，图片，josn文件都会被当做模块来使用
5. 打包：将webpack中的各种资源进行打包合并成一个或多个包（Bundle）
6. 并且在打包过程中，还可以进行资源处理，比如压缩图片，将scss转成css，将ES6语法转成EC5语法，将TypeScript转成JavaScript等等操作。

#### 2，和grunt/gulp的对比

1. grunt/gulp的核心是Task任务流
2. 配置一系列的task，并且定义要处理的事务
3. 之后让grunt/gulp来一次执行这些task，而且让整个流程自动化
4. 没有模块化的概念
5. webpack更强调模块化
6. webpack为了可以正常运行，必须依赖node环境，node环境为了可以正常执行很多代码，必须其中包含各种的包，npm工具

#### 3，安装

1. 查看自己的node版本

   ```
   node -v
   ```

2. 查看自己webpack的版本

   ```
   webpack --version
   ```

3. 全局安装webpack

   ~~~
   npm install webpack -g
   ~~~

4. 在终端执行webpack命令，使用的全局安装的webpack

5. 当在package.json中定义lscript时，其中包含了webpack命令，那么使用的是局部安装的webpack

6. ```
   webpack .\src\main.js -o .\dist\bundle.js
   //打包，把src下的main.js打包成bundle.js文件
   ```

7. ~~~
   npm init 生成package.json
   ~~~

8. ~~~
   npm inatall 安装依赖
   ~~~

9. ~~~
   npm run build 也可以生成打包后的文件 和 拥有配置文件后的webpack相同
   ~~~

10. loder安装 ，安装并在webpack-config.json中配置相关信息

11. ~~~
    npm install vue-loader //使用vue
    使用.vue文件
    ~~~

12. 插件：

    - ~~~
      把html文件放入dist文件夹中打包
      使用HtmlWebpackPlugin插件
      安装：npm install html-webpack-plugin --save-dev
      在webpack.config.js中：
      	plugin： [
      		new HtmlWebpackPlugin({
      			template: 'index.html'
      		}),
      	]
      需要删除output中的路径
      注意版本问题
      ~~~

13. 搭建本地服务器

    ​	

    webpack.config.js中配置devServer： [

    ​	contentBase : '/dist',

    ​	inline: true

    ]

    "dev": "webpack-dev-server  --open " -->通过npm run dev来运行 
    
14. runtime-compiler和runtime-only的区别

    - runtime-only性能更高
    - runtime-only下面的代码量更少

## 五，vue CLI3

### 1，和vue-cli2的区别

1. vue-cli3是基于webpack4打造
2. 设计原则是0配置，移除配置文件根目录下的build和config等目录
3. 提供了vue ui命令，提供了可视化配置，更加人性化
4. 移除了static文件夹，新增了public文件夹，并且index.html移动到public中

### 2，vue-cli3

1. 创建项目 vue create 项目名称 

## 六，Vue-Router

### 1，什么是路由

- 路由是一个网络工程里面的术语
- 路由就会说通过互联网的网络把信息从源地址传输到目的地址的活动
- 前端渲染和后端渲染
- 单页面富应用
  - SPA最主要的特点是咋前后端分离的基础上加了一层前端路由
  - 也就是前端来维护一套路由规则
- 前端路由的核心
  - 改变URL，但是页面不进行整体刷新
- 安装：npm install vue-router --save
- 使用（因为是一个插件，所以可以通过Vue.use()来安装路由功能）
  1. 导入路由对象，并且调用Vue.use(VueRouter)
  2. 创建路由实例，并且传入路由映射配置
  3. 在Vue实例中挂载创建的路由实例

```javascript
//配置路由相关信息
import VueRouter from 'vue-router'
import Vue from 'vue'
import HelloWould from '../components/HelloWould'
import index from '../components/index'

//1,通过Vue.use(插件),安装插件
Vue.use(VueRouter)

//2,创建VueRouter对象

const routes = [
    //在这里配置路径和.vue文件的映射关系
    {
        path: '/HelloWould',
        component: HelloWould
    },
    {
        path: '/index',
        component: index
    }
    
]

const router = new VueRouter({
    //配置路由和组件之间的应用关系
    routes
})

//3,将router对象传入到Vue实例
export default router
```

### 2，参数传递

#### （1），传递参数的方式

1. 传递参数主要有两种类型：params和query

2. params的类型

   - 配置路由格式：/router/:id
   - 传递的方式：在path后面跟上对应的值
   - 传递后形成的路径：/router/123,/router/abc

3. query的类型

   - 配置路由格式：/router,也就是普通配置
   - 传递的方式：对象中使用query的key作为传递方式
   - 传递后形成的路径：/router?id = 123, /router?id = abc

4. vue-router-keep-alive

   router-view 也是一个组件，如果直接被包在 keep-alive 里面，所有路径匹配到的视图组件都会被缓存

### 3，promise

1. promise是异步编程的一种解决方案

2. 什么情况下使用

   1. 一般情况下是有异步操作时，使用Promise对这个异步操作进行封装

      new -> 构造函数（1，保存了一些状态信息，2执行的函数）

      在执行传入的回调函数时，会传入两个参数，resolve，reject，本身又是函数

      ~~~javascript
      new Promise((resolve, reject) =>{
          setTimeout((data) => {
              resolve(data)
          }, 1000)
      }).then((data) => {
          //这里是回调方法的逻辑代码
      }), error => {
          console.log('错误')
      }
      ~~~

## 七，Vuex

1. Vuex是一个专门为Vue.js应用程序开发的状态管理模式
2. 响应式

## 八，网络请求封装（axicos）



1. 传统的ajax，一般不使用，配置和调试方式非常混乱
2. jQuery-Ajax，不使用，在vue的开发中是不使用jQuery的
3. Vue-resource，不使用，Vue作者已经不再维护
4. axios 使用
   - axios(config)
   - axios.request(config)
   - axios.get(ur[ config])
   - axios. delete(ur[ config]) 
   - axios.head(url[ config])
   - axios.post(ur[ data[ config])
   - axios.put(url[, data[ config]])
   - axios. patch(url[, data[, config])
5. 

