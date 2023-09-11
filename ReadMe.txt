一、想法：
1.将项目开发为android学习的App。
    功能：知识点(博客，示例，个人demo，个人总结)
    注意点：
    博客（网址+本地存储，防止网站变化或过期）

2.将自己所学应用到项目中。


二、项目总结
1.数据模块
a、数据的访问都是通过缓存数据，不要直接访问数据库或者SP等等。
  原因：每次直接访问是耗时的，影响性能。通过缓存数据。一次访问，多处引用，降低了直接访问的次数。
  注意点：注意缓存与数据源的同步。尽量遵循单一原则。

2.UI模块
a、页面之间的跳转和切换使用Navigation导航栏，减少Activity的使用


三、相关知识点
1.build.gradle.kts的使用
2.Compose与View的交互
3.Room的使用
4.Paging的使用
=================
5.依赖抽象为最外层配置，model引用该配置，更容易维护

6.Compose总结
a.禁止在Compose可组合函数中调用业务逻辑（例如启动Activity，Service），可组合函数中只处理界面相关内容。
原因：可组合函数的周期性和属性，例如重组不可控等等

b.非Compose调用Compose处理
例如：点击button调整页面逻辑
    a、非Compose修改状态，通过状态处理Compose
    b、使用navigation



四、引入相关示例
1.todoapp
architecture-samples branch:main
https://github.com/android/architecture-samples
时间：2023.9.8
注意：测试部分缺失

五、编码总结
1.kotlin中缓存数据的构建，模仿：ViewModelLazy中value
示例：Test1中getUserInfo方法

2.构建委托属性，模仿ViewModel的委托



